package com.yf.test.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;

import com.yf.util.hutool.Log;

public class ImageTest {
	public Logger log =Log.get();
	
	
	
	@Test
	public void test(){
		String imgurl="http://dl2.iteye.com/upload/attachment/0103/0351/ad5156ab-b922-3fd0-af21-12b7c01e717f.png";
		String baseurl="D:\\test.png";
		writerImageTOdisk(getImgForNet(imgurl), baseurl);
	}
	
	public void  writerImageTOdisk(byte[] img,String filename){
		try {
			File f=new File(filename);
			FileOutputStream file=new FileOutputStream(f);
			file.write(img);
			file.flush();
			file.close();
			log.info("图片已将写入");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public byte[] getImgForNet(String s){
		URL url;
		try {
			url = new URL(s);
			HttpURLConnection huc=(HttpURLConnection) url.openConnection();
			huc.setRequestMethod("GET");
			huc.setConnectTimeout(5*1000);
			InputStream in =huc.getInputStream();
			 byte[] btImg=	readInput(in);
			 return btImg;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return null;
	}
	
	public byte[] readInput(InputStream inStream) throws IOException{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
	}
}
