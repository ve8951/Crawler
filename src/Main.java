import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {
	public static DB db = new DB();
	static ArrayList<Document> docList;

	public static void main(String[] args) throws SQLException, IOException,MalformedURLException {

		HashSet<ModelExcell> excelData = new ReadExcelFile().getExcelData();
		ArrayList<ModelExcell> labelList = new ArrayList<ModelExcell>();
		String[] labels = new String[2];
		//		int i = 0;
		//		int count = 0;

		getLabelWritetoExcel(labels, excelData, labelList);

		//		new CommentReader().readComments();
	}

	public static void getLabelWritetoExcel(String[] labels,HashSet<ModelExcell> excelData ,ArrayList<ModelExcell> labelList){

		for (ModelExcell e : excelData) {

			try {
				ModelExcell excell = null;
				String projectLink = e.getIssueLink().toString();

				if (projectLink.substring(0, 4).equals("http")) {

					URL url = new URL(e.getIssueLink());
					HttpURLConnection huc =  ( HttpURLConnection ) url.openConnection (); 
					huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
					huc.connect () ; 
					String code = ""+huc.getResponseCode() ;

					//					System.out.print("Project name: "+e.getProjName()+" ProjectLink:"+e.getProjLink());
					//					System.out.print("/n");

					//-----Below code is used for checking number of non git issue tracker

					System.out.print("\n"+e.getIssueLink());

					if (projectLink.substring(projectLink.indexOf("/")+2, projectLink.indexOf("/")+5).equals("git")) {

						if (!code.substring(0,1).equals("2")) {

							//							System.out.println(""+count+"  "+code+" Project Name:"+e.getProjName()+" Project Link:"+e.getProjLink()+" for "+e.getProjName()+" does not exist");
							//							i++;
							excell = new ModelExcell();
							excell.setProjName(e.getProjName());
							excell.setIssueLink(e.getIssueLink());
							excell.setSourcelink(e.getSourcelink());
							excell.setRepoType(e.getRepoType());
							excell.setSourceDownLoadStatus(e.getSourceDownLoadStatus());
							excell.setLinkWorks("Link does not work: "+code);
							excell.setSourceLinkWorks(e.getSourceLinkWorks());
							excell.setProjectExists(e.getProjectExists());
							excell.setCount("0");
							excell.setComments(e.getComments());
							excell.setLabels("No Label");
						}else{

//							Document doc = Jsoup.parse(url, 10000000);
							Document doc = Jsoup.connect(e.getIssueLink())
							  .userAgent("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.69 Safari/537.36")
							  .maxBodySize(0)
							  .timeout(600000)
							  .get();
							//							docList = new ArrayList<Document>();
							//							docList.add(doc);
							labels = new String[2];
							labels = checkLabelExist(doc,labels);
							labels = docRetriever(doc,labels);

							//							

							if(labels[0] == "" || labels[0] == null || labels[1] == "0"){
								excell = new ModelExcell();
								excell.setProjName(e.getProjName());
								excell.setIssueLink(e.getIssueLink());
								excell.setSourcelink(e.getSourcelink());
								excell.setRepoType(e.getRepoType());
								excell.setSourceDownLoadStatus(e.getSourceDownLoadStatus());
								excell.setLinkWorks("Link works");
								excell.setSourceLinkWorks(e.getSourceLinkWorks());
								excell.setProjectExists(e.getProjectExists());
								excell.setCount("0");
								excell.setComments(e.getComments());
								excell.setLabels("No Label");

							}
							//								System.out.println(""+count+"  "+code+" Project Name:"+e.getProjName()+" No Labels " );
							else{
								//								System.out.println(""+count+"  "+code+" Project Name:"+e.getProjName()+ " Labels: "+labels );
								excell = new ModelExcell();
								excell.setProjName(e.getProjName());
								excell.setIssueLink(e.getIssueLink());
								excell.setSourcelink(e.getSourcelink());
								excell.setRepoType(e.getRepoType());
								excell.setSourceDownLoadStatus(e.getSourceDownLoadStatus());
								excell.setLinkWorks("Link works");
								excell.setSourceLinkWorks(e.getSourceLinkWorks());
								excell.setProjectExists(e.getProjectExists());
								excell.setComments(e.getComments());
								excell.setLabels(labels[0]);
								excell.setCount(labels[1]);
							}

						}
					}
//					Uncomment for project
//					else{
//						excell = new ModelExcell();
//						excell.setProjName(e.getProjName());
//						excell.setIssueLink(e.getIssueLink());
//						excell.setSourcelink(e.getSourcelink());
//						excell.setRepoType(e.getRepoType());
//						excell.setSourceDownLoadStatus(e.getSourceDownLoadStatus());
//						excell.setLinkWorks(e.getLinkWorks());
//						excell.setSourceLinkWorks(e.getSourceLinkWorks());
//						excell.setProjectExists(e.getProjectExists());
//						excell.setComments(e.getComments());
//						excell.setLabels(e.getLabels());
//						excell.setCount("");
//					}
				}

				if (excell != null) {
					labelList.add(excell);	
				}

			} catch (Exception e2) {
				e2.getCause();
			}


		}
		//Uncomment or project
		//		new WriteExcelFile(labelList).writeToFile();
		
		for (int i = 0; i < labelList.size(); i++) {
			System.out.print(""+labelList.get(i).getLabels());
			
		}
	}


	public static String[] checkLabelExist( Document doc,String[] labels) throws IOException{
		String [] labelCount = new String[2];
		int count = 0;
		String labelList = "";

		Iterator<Element> productList = doc.select("div[class=issues-listing]").iterator();

		while (productList.hasNext()) {
			Iterator<Element> element = productList.next().children().select("li").iterator();
			while (element.hasNext()) {
				Iterator<Element> divList = element.next().children().select("div[class=table-list-cell issue-title]").iterator();

				while (divList.hasNext()) {
					Element element2 = divList.next();
					String text = element2.children().select("span[class=labels]").text();

					//					if(text.equals("bug") || text.equals("enhancement") || text.equals("duplicate") || text.equals("invalid")){
					if(text.equals("#")){
						labelList = labelList +", "+ element2.children().select("span[class=labels]").text();
						count++;
					}
				}
			}
		}
		if (labels[0] != null || labels[1] != null) {
			labelCount[0] = labels[0]+labelList;
			labelCount[1] = ""+(count+Integer.parseInt(labels[1]));	
		}else{
			labelCount[0] = labelList;
			labelCount[1] = ""+count;
		}

		return labelCount;
	}

	private static String[] docRetriever(Document doc, String[] labels) throws IOException {

		//		String[] labels = new String[2];

		Elements pageList = doc.select("div[class=pagination]");
		Elements links = pageList.select("a[class=next_page]");
		System.out.print("\n"+links.attr("href"));
		//		System.out.print(""+pageList.select("span[class=next_page disabled]").text());
		if (!links.isEmpty()) {

			Document nextPage = Jsoup.connect("https://github.com"+links.attr("href")).get();
			//			System.out.print(" "+nextPage);
			//			docList.add(nextPage);
			labels = checkLabelExist(nextPage,labels);
			docRetriever(nextPage,labels);	
		}
		return labels;
	}

}