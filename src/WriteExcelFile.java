import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class WriteExcelFile {

	ArrayList<ModelExcell> excelContent = new ArrayList<ModelExcell>();

	public WriteExcelFile(ArrayList<ModelExcell> excelContent){
		this.excelContent = excelContent;
	}

	public void writeToFile(){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		int rownum = 0;

		for (ModelExcell modelExcel : excelContent) {
			if (modelExcel != null) {

				int cellnum = 0;
				String[] objArr = new String[12];
				objArr[0] = modelExcel.getProjName();
				objArr[1] = modelExcel.getIssueLink();
				objArr[2] = modelExcel.getSourcelink();
				objArr[3] = modelExcel.getRepoType();
				objArr[4] = modelExcel.getSourceDownLoadStatus();
				objArr[5] = modelExcel.getLinkWorks();
				objArr[6] = modelExcel.getSourceLinkWorks();
				objArr[7] = modelExcel.getProjectExists();
				objArr[8] = modelExcel.getCount();
				objArr[9] = modelExcel.getComments();
				objArr[10] = modelExcel.getLabels();
				
				Row row = sheet.createRow(rownum++);

				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					if(obj instanceof Date) 
						cell.setCellValue((Date)obj);
					else if(obj instanceof Boolean)
						cell.setCellValue((Boolean)obj);
					else if(obj instanceof String)
						cell.setCellValue((String)obj);
					else if(obj instanceof Double)
						cell.setCellValue((Double)obj);
				}
			}
		}

		try {
			FileOutputStream out = 
					new FileOutputStream(new File("C://Users/VIVEK/Masters_SE/Evolution/Project/Content/new.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
