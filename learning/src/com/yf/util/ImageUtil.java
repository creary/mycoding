package com.yf.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;

import com.yf.util.hutool.Log;
/**
 * 此类实现 图片的处理：
 * 包括图片的写入
 * 1.网络上的图片保存到本地
 * 
 */
public class ImageUtil {
	public static Logger log =Log.get();//写入日志自动判断日志的发出者

	/**
	 * 
	   
	 * writerImageTOdisk( 传入要保存的 图片byte【】数组 和要保存的位置【包含要保存的图片的名称和图片的后缀】)    
	   
	 * @Exception 异常对象    
	   
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public static void  writerImageTOdisk(byte[] img,String filename){
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
/**
 * 
 * writerImageFromNet(传入图片位置  【必须是网络上的】   filename 是本地的图片存放地址)    
 * @param   name    
 * @param  @return    设定文件    
 * @return String    DOM对象    
 * @Exception 异常对象    
 * @since  CodingExample　Ver(编码范例查看) 1.1
 */
	
	public static void  writerImageFromNet(String netInmURL,String filename){
		try {
			File f=new File(filename);
			FileOutputStream file=new FileOutputStream(f);
			file.write(getImgForNet(netInmURL));
			file.flush();
			file.close();
			log.info("图片已将写入");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * getImgForNet(从网络上的地址【URL】分析出文件的byte[])    
	 * @param  String s   RUL图片的地址    
	 * @param  @return     byte[] 字节数组
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	
	public static byte[] getImgForNet(String s){
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
			e.printStackTrace();
		}
 		return null;
	}
	
	public static byte[] readInput(InputStream inStream) throws IOException{
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
