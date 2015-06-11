package com.yf.test.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;

import org.junit.Test;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class ExcelTest {

	
	 public static void writeExcel(File f) throws Exception {
		  jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(f);
		  jxl.write.WritableSheet ws = wwb.createSheet("TestSheet1", 0);
		  jxl.write.Label labelC = new jxl.write.Label(0, 0, "我爱中国");
		  ws.addCell(labelC);
		  jxl.write.WritableFont wfc = new jxl.write.WritableFont(
		    WritableFont.ARIAL, 20, WritableFont.BOLD, false,
		    UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.GREEN);
		  jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
		    wfc);
		  wcfFC.setBackground(jxl.format.Colour.RED);
		  labelC = new jxl.write.Label(6, 0, "中国爱我a", wcfFC);
		  ws.addCell(labelC);
		  // 写入Exel工作表
		  wwb.write();
		  // 关闭Excel工作薄对象
		  wwb.close();
		 }
		 public static void readExcel(File os) throws Exception {
		  Workbook wb = Workbook.getWorkbook(os);
		  Sheet s = wb.getSheet("Sheet1");
		  Cell c = s.getCell(0,0);
		  System.out.println(c.getContents());
		 }

		 // 最好写一个这样的main方法来测试一下你的这个class是否写好了。
		 public static void main(String[] args) throws Exception {
		  File f = new File("D:\\kk1.xls");
		//  f.createNewFile();
		  writeExcel(f);
//		  readExcel(f);
		 }
	
		 @Test
		 public void pdfTest(){
			  
		        String fileName = "D:\\helloWorld.pdf";  
		        String content = "Hello World!";  
		        try {
					create(fileName, content);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (DocumentException e) {
					e.printStackTrace();
				}  
		 }
		  private static void create(String fileName, String content) throws DocumentException, FileNotFoundException {  
		          
		        //step1  
		        Document document = new Document();  
		        OutputStream os = new FileOutputStream(fileName);  
		        //step2  
		        PdfWriter.getInstance(document, os);  
		        //step3  
		        document.open();  
		        //step4  
		        document.add(new Paragraph(content));  
		        //step5  
		        document.close();  
		    }  
	
}
