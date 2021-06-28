package pack;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFunctions extends Constants{
	final static String dir = System.getProperty("user.dir");
	final static File outputFile=new File(dir+"\\Result.xlsx");
	static Workbook wb;
	static Sheet sheet;
	static FileOutputStream fos;
	static FileInputStream fis;
	
	public static void main(String[] args)  {
		 
		//excelData();
		}
	
	public void createOutputFile()
	{
					
	     if(!outputFile.exists()) {
			 System.out.println("Is Output Excel sheet exists: "+outputFile.exists());
			 wb = new XSSFWorkbook();
			 Font font= wb.createFont();
			 font.setBold(true);
			 CellStyle style = wb.createCellStyle();
		     style.setFont(font);
			 sheet = wb.createSheet("Result");
			 sheet.setColumnWidth(0, 5000);
			 sheet.setColumnWidth(1, 5000);
			 //sheet.setColumnWidth(2, 1000);
			 
			 sheet.createRow(0).createCell(0).setCellValue("Adb File Name");
			 sheet.getRow(0).getCell(0).setCellStyle(style);
			 sheet.getRow(0).createCell(1).setCellValue("Logcat Result");
			 sheet.getRow(0).getCell(1).setCellStyle(style);
			 sheet.getRow(0).createCell(2).setCellValue("ADB command result");
			 sheet.getRow(0).getCell(2).setCellStyle(style);
			 
			try {
				fos = new FileOutputStream(outputFile);
				wb.write(fos);
			    fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			
		 }
/*		
	     else if(file.exists()) {
	    	System.out.println("Is Output Excel sheet exists: "+file.exists());
	    	wb = new XSSFWorkbook();
	    	Font font= wb.createFont();
			font.setBold(true);
		    CellStyle style = wb.createCellStyle();
	        style.setFont(font);
		   //Sheet sheet = wb.getSheetAt(0);
	        System.out.println(wb.getNumberOfSheets());
	        if(wb.getNumberOfSheets()==0) {
	        	sheet = wb.createSheet("Result");
	        	sheet.setColumnWidth(0, 5000);
				sheet.setColumnWidth(1, 5000);
	        }else {
	        	sheet=wb.getSheet("Result");
	        	sheet.setColumnWidth(0, 5000);
				sheet.setColumnWidth(1, 5000);
	        }
		
	        sheet.createRow(0).createCell(0).setCellValue("Adb File Name");
	        sheet.getRow(0).getCell(0).setCellStyle(style);
	        sheet.getRow(0).createCell(1).setCellValue("ADB Logcat Result");
	        sheet.getRow(0).getCell(1).setCellStyle(style);
	        //sheet.getRow(0).createCell(2).setCellValue("Logcat result");
	        //sheet.getRow(0).getCell(2).setCellStyle(style);
	     
				 try {
	 		      fos = new FileOutputStream(file);
	 		      wb.write(fos);
	 	          fos.close();
	 		 }catch(Exception e) {}
	     };
*/	    
	}

	
    public void inputResults(String adbFileName, String LogcatResult, String adbCommandResult) {
    	try {
    	FileInputStream fis = new FileInputStream(outputFile);
		
		@SuppressWarnings("resource")
		Workbook wb = new XSSFWorkbook(fis);
		 Sheet sheet = wb.getSheet("Result");
		
		int lastRow = sheet.getLastRowNum();
 		System.out.println("row count: "+lastRow);
 		
 		sheet.createRow(lastRow+1).createCell(0).setCellValue(adbFileName);
 			
 		int columnCount = sheet.getRow(lastRow+1).getLastCellNum();
 		System.out.println("Column count: "+columnCount);
 			
 		sheet.getRow(lastRow+1).createCell(1).setCellValue(LogcatResult);
 			
 		sheet.getRow(lastRow+1).createCell(2).setCellValue(adbCommandResult);
 		
 		fos = new FileOutputStream(outputFile);
 		wb.write(fos);
 		fis.close(); 
 	    fos.close();
    	}catch(Exception e) {}
    }
    
    public String[][] excelData() {
    	String[][] arr = new String[3][];
    	try {
    	fis = new FileInputStream(outputFile);
		@SuppressWarnings("resource")
		Workbook wb=new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
		int columnCount = sheet.getRow(0).getLastCellNum();
		int rowNum = sheet.getLastRowNum()+1;
		System.out.println("Column Count: "+columnCount+"\nRow Count: "+rowNum);
		
		arr[0]=new String[rowNum];
		arr[1]=new String[rowNum];
		arr[2]=new String[rowNum];
		
		for(int i=0;i<arr.length;i++) {
			for(int j=1;j<arr[i].length;j++) {
				String columndata = sheet.getRow(j).getCell(i).getStringCellValue();
				System.out.println(columndata);
				arr[i][j]=columndata;
			}
		}
		fis.close();
    	}catch(Exception e) {e.printStackTrace();}		
		return arr;
	
    }

//Method to get number of column in the excel
    public int columnCount() {
    	int numOfColumns = 0;
    	try {
    	fis = new FileInputStream(outputFile);
    	Workbook wb=new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
    	numOfColumns = sheet.getRow(0).getLastCellNum();
    	fis.close();
    	}catch(Exception e) {e.printStackTrace();}
		return numOfColumns;
    }
    
//CSV file handling
    public String[][] csvData(){
    	int j=0;
    	String csvFile = dir+"\\Result.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
        	j++;
        }
        }catch(Exception e) {e.printStackTrace();}
        System.out.println("the num of rows: "+j);
        
        String[][] arr = new String[2][];
		arr[0]=new String[j];
		arr[1]=new String[j];
		
		
        int i=0;
        try {
        	 br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                System.out.println(country[0] + " " + country[1]);
                arr[0][i]=country[0];
                arr[1][i]=country[1];
             i++;
            }
            System.out.println(arr[1][1]);
        } catch (Exception e) {e.printStackTrace();}
          finally {if (br != null) { try {br.close();} catch (IOException e) {e.printStackTrace();}}
        }
		return arr;

    }
    
    public void openExcel() {
    	try {
    	File file=new File(dir+"Result.xlsx");
    	if(!Desktop.isDesktopSupported()){//check if Desktop is supported by Platform or not   
    		System.out.println("not supported");  
    	} 
    	
    	Desktop desktop = Desktop.getDesktop();  
    	if(file.exists())         //checks file exists or not  
    	desktop.open(file);              //opens the specified file  
    	}catch(Exception e) {e.printStackTrace();}

    }
}
