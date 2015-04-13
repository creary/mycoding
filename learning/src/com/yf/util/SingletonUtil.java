package com.yf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SingletonUtil {
private static String FILE_URL="/conf/file.properties";
private SingletonUtil (){}


private static class SingletonHolder {  
     private static final SingletonUtil INSTANCE = new SingletonUtil();  
}
public static final SingletonUtil getInstance() {
    return SingletonHolder.INSTANCE;  
}  

public  FileInputStream getFileInputS(){
	
	try {
		return new FileInputStream(FILE_URL);
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	return null;
	}
 }
