import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelFile {

	static ModelExcell modelExcell;

	public HashSet<ModelExcell> getExcelData(){
		try
		{
			FileInputStream file = new FileInputStream(new File(Environment.filePath));

			HashSet<ModelExcell> excelContent = new HashSet<ModelExcell>();

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				modelExcell = new ModelExcell();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();

					//Check the cell type and format accordingly
					
					switch (cell.getColumnIndex()) {
					case 0:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							modelExcell.setProjName(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							modelExcell.setProjName(""+cell.getStringCellValue());
							break;
						}
						break;
						
					case 1:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setIssueLink(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setIssueLink(""+cell.getStringCellValue());
							break;
						}
						break;
						
					case 2:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setSourcelink(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setSourcelink(""+cell.getStringCellValue());
							break;
						}
						break;
						
					case 3:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setRepoType(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setRepoType(""+cell.getStringCellValue());
							break;
						}
						break;	

					case 4:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setSourceDownLoadStatus(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setSourceDownLoadStatus(""+cell.getStringCellValue());
							break;
						}
						break;	
					
					case 5:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setLinkWorks(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setLinkWorks(""+cell.getStringCellValue());
							break;
						}
						break;	
					
					case 6:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setSourceLinkWorks(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setSourceLinkWorks(""+cell.getStringCellValue());
							break;
						}
						break;	
						
					case 7:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setProjectExists(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setProjectExists(""+cell.getStringCellValue());
							break;
						}
						break;	
						
					case 9:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setCount(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setCount(""+cell.getStringCellValue());
							break;
						}
						break;	
						
					case 10:
						switch (cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							//							System.out.print(cell.getNumericCellValue());
							if (cell.getNumericCellValue() != 0) 
								modelExcell.setComments(""+cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							//							System.out.print(cell.getStringCellValue());
							if (cell.getStringCellValue() != null)
								modelExcell.setComments(""+cell.getStringCellValue());
							break;
						}
						break;	
						
							
					
					
					
					default:
						break;
					}
					
//					if (cell.getColumnIndex() == 1 ) {
//
//						switch (cell.getCellType())
//						{
//						case Cell.CELL_TYPE_NUMERIC:
//							//							System.out.print(cell.getNumericCellValue());
//							if (cell.getNumericCellValue() != 0) 
//								modelExcell.setIssueLink(""+cell.getNumericCellValue());
//							break;
//						case Cell.CELL_TYPE_STRING:
//							//							System.out.print(cell.getStringCellValue());
//							if (cell.getStringCellValue() != null)
//								modelExcell.setIssueLink(""+cell.getStringCellValue());
//							break;
//						}
//					}
//
//					if (cell.getColumnIndex() == 0){
//						switch (cell.getCellType())
//						{
//						case Cell.CELL_TYPE_NUMERIC:
//							//							System.out.print(cell.getNumericCellValue());
//							modelExcell.setProjName(""+cell.getNumericCellValue());
//							break;
//						case Cell.CELL_TYPE_STRING:
//							//							System.out.print(cell.getStringCellValue());
//							modelExcell.setProjName(""+cell.getStringCellValue());
//							break;
//						}
//					}
					//					if(modelExcell.getProjLink() != null || modelExcell.getProjName() !=null)
					excelContent.add(modelExcell);
				}

			}
			file.close();
			//			int i = 0;
			//			List<ModelExcell> temp = new ArrayList<ModelExcell>(excelContent);
			//			Collections.sort(temp,new SortingComparator());
			//			for (ModelExcell excell : excelContent) {
			//				if(excelContent.get(i).getProjName() != null || excelContent.get(i).getProjName() != null)
			//					System.out.println("Name: "+excell.getProjName()  +"  Link: "+excell.getProjLink());
			//					i++;
			//			}

			//			System.out.print(i);
			return excelContent;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
} 
