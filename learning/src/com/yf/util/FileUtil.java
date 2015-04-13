package com.yf.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.sun.corba.se.impl.ior.ByteBuffer;

/**
 * 
 * @author IBM
 * 
 */

public class FileUtil {
	
	public static String dirSplit = "\\";//linux windows
	/**
	 * save file accroding to physical directory infomation
	 * 
	 * @param physicalDir
	 *            physical directory
	 * @param fname
	 *            file name of destination
	 * @param istream
	 *            input stream of destination file
	 * @return
	 */

	public static boolean SaveFileByPhysicalDir(String physicalPath,
			InputStream istream) {
		
		boolean flag = false;
		try {
			OutputStream os = new FileOutputStream(physicalPath);
			int readBytes = 0;
			byte buffer[] = new byte[8192];
			while ((readBytes = istream.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, readBytes);
			}
			os.close();
			flag = true;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return flag;
	}
	
	public static boolean CreateDirectory(String dir){
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
		return true;
	}
	
	
	public static void saveAsFileOutputStream(String physicalPath,String content) {
		  File file = new File(physicalPath);
		  boolean b = file.getParentFile().isDirectory();
		  if(!b){
			  File tem = new File(file.getParent());
			  // tem.getParentFile().setWritable(true);
			  tem.mkdirs();// 创建目录
		  }
		  //Log.info(file.getParent()+";"+file.getParentFile().isDirectory());
		  FileOutputStream foutput =null;
		  try {
			  foutput = new FileOutputStream(physicalPath);
			  
			  foutput.write(content.getBytes("UTF-8"));
			  //foutput.write(content.getBytes());
		  }catch(IOException ex) {
			  ex.printStackTrace();
			  throw new RuntimeException(ex);
		  }finally{
			  try {
				  foutput.flush();
				  foutput.close();
			  }catch(IOException ex){
				  ex.printStackTrace();
				  throw new RuntimeException(ex);
			  }
		  }
		  	//Log.info("文件保存成功:"+ physicalPath);
		 }

	
	
	 /**
     * COPY文件
     * @param srcFile String
     * @param desFile String
     * @return boolean
     */
    public boolean copyToFile(String srcFile, String desFile) {
        File scrfile = new File(srcFile);
        if (scrfile.isFile() == true) {
            int length;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(scrfile);
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            File desfile = new File(desFile);

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(desfile, false);
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            desfile = null;
            length = (int) scrfile.length();
            byte[] b = new byte[length];
            try {
                fis.read(b);
                fis.close();
                fos.write(b);
                fos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            scrfile = null;
            return false;
        }
        scrfile = null;
        return true;
    }

    /**
     * COPY文件夹
     * @param sourceDir String
     * @param destDir String
     * @return boolean
     */
    public boolean copyDir(String sourceDir, String destDir) {
        File sourceFile = new File(sourceDir);
        String tempSource;
        String tempDest;
        String fileName;
        File[] files = sourceFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            fileName = files[i].getName();
            tempSource = sourceDir + "/" + fileName;
            tempDest = destDir + "/" + fileName;
            if (files[i].isFile()) {
                copyToFile(tempSource, tempDest);
            } else {
                copyDir(tempSource, tempDest);
            }
        }
        sourceFile = null;
        return true;
    }

    /**
     * 删除指定目录及其中的所有内容。
     * @param dir 要删除的目录
     * @return 删除成功时返回true，否则返回false。
     */
    public boolean deleteDirectory(File dir) {
        File[] entries = dir.listFiles();
        int sz = entries.length;
        for (int i = 0; i < sz; i++) {
            if (entries[i].isDirectory()) {
                if (!deleteDirectory(entries[i])) {
                    return false;
                }
            } else {
                if (!entries[i].delete()) {
                    return false;
                }
            }
        }
        if (!dir.delete()) {
            return false;
        }
        return true;
    }
    
    
    
    /**
     * File exist check
     *
     * @param sFileName File Name
     * @return boolean true - exist<br>
     *                 false - not exist
     */
    public static boolean checkExist(String sFileName) {
     
     boolean result = false;
       
       try {
    	   File f = new File(sFileName);
	   
    	   //if (f.exists() && f.isFile() && f.canRead()) {
	   if (f.exists() && f.isFile()) {
		    result = true;
	   } else {
		    result = false;
	   }
	   } catch (Exception e) {
	        result = false;
	   }
	       
        /* return */
        return result;
    }
   
    /**
     * Get File Size
     *
     * @param sFileName File Name
     * @return long File's size(byte) when File not exist return -1
     */
    public static long getSize(String sFileName) {
       
        long lSize = 0;
       
        try {
		    File f = new File(sFileName);
		           
		            //exist
		    if (f.exists()) {
			    if (f.isFile() && f.canRead()) {
			     lSize = f.length();
			    } else {
			     lSize = -1;
			    }
		            //not exist
		    } else {
		        lSize = 0;
		    }
	   } catch (Exception e) {
	        lSize = -1;
	   }
	   /* return */
	   return lSize;
    }
   
 /**
  * File Delete
  *
  * @param sFileName File Nmae
  * @return boolean true - Delete Success<br>
  *                 false - Delete Fail
  */
    public static boolean deleteFromName(String sFileName) {
  
        boolean bReturn = true;
  
        try {
            File oFile = new File(sFileName);
   
           //exist
           if (oFile.exists()) {
	           //Delete File
	           boolean bResult = oFile.delete();
	           //Delete Fail
	           if (bResult == false) {
	               bReturn = false;
	           }
	   
	           //not exist
           } else {
    
           }
   
	  } catch (Exception e) {
		  bReturn = false;
	  }
  
	  //return
	  return bReturn;
    }
   
 /**
  * File Unzip
  *
  * @param sToPath  Unzip Directory path
  * @param sZipFile Unzip File Name
  */
 @SuppressWarnings("rawtypes")
public static void releaseZip(String sToPath, String sZipFile) throws Exception {
  
  if (null == sToPath ||("").equals(sToPath.trim())) {
	   File objZipFile = new File(sZipFile);
	   sToPath = objZipFile.getParent();
  }
  ZipFile zfile = new ZipFile(sZipFile);
  Enumeration zList = zfile.entries();
  ZipEntry ze = null;
  byte[] buf = new byte[1024];
  while (zList.hasMoreElements()) {

	   ze = (ZipEntry) zList.nextElement();
	   if (ze.isDirectory()) {
	    continue;
	   }
	
	   OutputStream os =
	   new BufferedOutputStream(
	   new FileOutputStream(getRealFileName(sToPath, ze.getName())));
	   InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
	   int readLen = 0;
	   while ((readLen = is.read(buf, 0, 1024)) != -1) {
		   os.write(buf, 0, readLen);
	   }
	   is.close();
	   os.close();
  }
  zfile.close();
 }
 
 /**
  * getRealFileName
  *
  * @param  baseDir   Root Directory
  * @param  absFileName  absolute Directory File Name
  * @return java.io.File     Return file
  */
 @SuppressWarnings({ "rawtypes", "unchecked" })
private static File getRealFileName(String baseDir, String absFileName) throws Exception {
  
  File ret = null;

  List dirs = new ArrayList();
  StringTokenizer st = new StringTokenizer(absFileName, System.getProperty("file.separator"));
  while (st.hasMoreTokens()) {
	  dirs.add(st.nextToken());
  }

  ret = new File(baseDir);
  if (dirs.size() > 1) {
	  for (int i = 0; i < dirs.size() - 1; i++) {
		  ret = new File(ret, (String) dirs.get(i));
	  }
  }
  if (!ret.exists()) {
	  ret.mkdirs();
  }
  ret = new File(ret, (String) dirs.get(dirs.size() - 1));
  return ret;
 }

 /**
  * renameFile
  *
  * @param  srcFile   Source File
  * @param  targetFile   Target file
  */
 static public void renameFile(String srcFile , String targetFile) throws IOException
  {
   try {
	   copyFile(srcFile,targetFile);
	   deleteFromName(srcFile);
   } catch(IOException e){
	   throw e;
   }
  }


 public static void write(String tivoliMsg,String logFileName) {
  try{
	   byte[]  bMsg = tivoliMsg.getBytes();
	   FileOutputStream fOut = new FileOutputStream(logFileName, true);
	   fOut.write(bMsg);
	   fOut.close();
  } catch(IOException e){
   //throw the exception      
  }

 }
 /**
 * This method is used to log the messages with timestamp,error code and the method details
 * @param errorCd String
 * @param className String
 * @param methodName String
 * @param msg String
 */
 public static void writeLog(String logFile, String batchId, String exceptionInfo) {
  
  DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.JAPANESE);
  
  Object args[] = {df.format(new Date()), batchId, exceptionInfo};
  
  String fmtMsg = MessageFormat.format("{0} : {1} : {2}", args);
  
  try {
   
   File logfile = new File(logFile);
   if(!logfile.exists()) {
    logfile.createNewFile();
   }
   
      FileWriter fw = new FileWriter(logFile, true);
      fw.write(fmtMsg);
      fw.write(System.getProperty("line.separator"));

      fw.flush();
      fw.close();

  } catch(Exception e) {
  }
 }
 
 public static String readTextFile(String realPath) throws Exception {
	 File file = new File(realPath);
	 	if (!file.exists()){
	 		System.out.println("File not exist!");
	 		return null;
	  }
	  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(realPath),"UTF-8"));	  
	  String temp = "";
	  String txt="";
	  while((temp = br.readLine()) != null){
		  txt+=temp;
	   }	  
	  br.close();
	 return txt;
 }

 
 /**
  * 获取文件MD5值
  * 
  * @param file
  * @return
  */
 public static String getMd5ByFile(File file) {
     String value = null;
     FileInputStream in = null;
     try {
         in = new FileInputStream(file);
         MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0,
                 file.length());
         MessageDigest md5 = MessageDigest.getInstance("MD5");
         md5.update(byteBuffer);
         BigInteger bi = new BigInteger(1, md5.digest());
         value = bi.toString(16);
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         if (null != in) {
             try {
                 in.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     return value;
 }
 
 /**
  * 获取文件大小
  * 
  * @param file
  * @return
  */
 public static long getFileLength(File file)
         throws IOException {
     FileInputStream fis = null;
     fis = new FileInputStream(file);
     return fis.available();
 }
 
 /**
  * 读取文件到二进制
  * 
  * @author WikerYong Email:<a href="#">yw_312@foxmail.com</a>
  * @version 2012-3-23 上午11:47:06
  * @param file
  * @return
  * @throws IOException
  */
 public static byte[] getBytesFromFile(File file)
         throws IOException {
     InputStream is = new FileInputStream(file);
     
     long length = file.length();
     
     if (length > Integer.MAX_VALUE) {
         // File is too large
     }
     
     byte[] bytes = new byte[(int) length];
     
     // Read in the bytes
     int offset = 0;
     int numRead = 0;
     while (offset < bytes.length
             && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
         offset += numRead;
     }
     
     // Ensure all the bytes have been read in
     if (offset < bytes.length) {
         throw new IOException("不能读取文件: " + file.getName());
     }
     
     is.close();
     return bytes;
 }
 
 /**
  * 获取标准文件大小，如30KB，15.5MB
  * 
  * @param file
  * @return
  * @throws IOException
  */
 public static String getFileSize(File file)
         throws IOException {
     long size = getFileLength(file);
     DecimalFormat df = new DecimalFormat("###.##");
     float f;
     if (size < 1024 * 1024) {
         f = (float) ((float) size / (float) 1024);
         return (df.format(new Float(f).doubleValue()) + " KB");
     } else {
         f = (float) ((float) size / (float) (1024 * 1024));
         return (df.format(new Float(f).doubleValue()) + " MB");
     }
     
 }
 
 /**
  * 检查文件是否存在
  * 
  * @param fileName
  * @return
  * @throws IOException
  */
 public static boolean existFile(String fileName)
         throws IOException {
     File file = new File(fileName);
     if (!file.exists()) {
         throw new IOException("文件未找到:" + fileName);
     }
     return file.exists();
 }
 
 /**
  * 删除文件
  * 
  * @param fileName
  */
 public static void deleteFile(String fileName)
         throws IOException {
     File file = new File(fileName);
     if (!file.exists()) {
         throw new IOException("文件未找到:" + fileName);
     }
     file.delete();
 }
 
 /**
  * 读取文件到字符串
  * 
  * @param fileName
  * @return
  * @throws IOException
  */
 public static String readFile(String fileName)
         throws IOException {
     File file = new File(fileName);
     if (!file.exists()) {
         throw new IOException("文件未找到:" + fileName);
     }
     
     BufferedReader in = new BufferedReader(new FileReader(file));
     StringBuffer sb = new StringBuffer();
     String str = "";
     while ((str = in.readLine()) != null) {
         sb.append(str);
     }
     in.close();
     return sb.toString();
 }
 
 /**
  * 获取目录所有所有文件和文件夹
  * 
  * @param fileName
  * @return
  * @throws IOException
  */
 public static List<File> listFiles(String fileName)
         throws IOException {
     File file = new File(fileName);
     if (!file.exists()) {
         throw new IOException("文件未找到:" + fileName);
     }
     return Arrays.asList(file.listFiles());
 }
 
 /**
  * 创建目录
  * 
  * @param dir
  */
 public static void mkdir(String dir) {
     String dirTemp = dir;
     File dirPath = new File(dirTemp);
     if (!dirPath.exists()) {
         dirPath.mkdir();
     }
 }
 
 /**
  * 新建文件
  * 
  * @param fileName
  *            String 包含路径的文件名 如:E:\phsftp\src\123.txt
  * @param content
  *            String 文件内容
  */
 public static void createNewFile(String fileName, String content)
         throws IOException {
     String fileNameTemp = fileName;
     File filePath = new File(fileNameTemp);
     if (!filePath.exists()) {
         filePath.createNewFile();
     }
     FileWriter fw = new FileWriter(filePath);
     PrintWriter pw = new PrintWriter(fw);
     String strContent = content;
     pw.println(strContent);
     pw.flush();
     pw.close();
     fw.close();
     
 }
 
 /**
  * 删除文件夹
  * 
  * @param folderPath
  *            文件夹路径
  */
 public static void delFolder(String folderPath) {
     // 删除文件夹里面所有内容
     delAllFile(folderPath);
     String filePath = folderPath;
     java.io.File myFilePath = new java.io.File(filePath);
     // 删除空文件夹
     myFilePath.delete();
 }
 
 /**
  * 删除文件夹里面的所有文件
  * 
  * @param path
  *            文件夹路径
  */
 public static void delAllFile(String path) {
     File file = new File(path);
     if (!file.exists()) {
         return;
     }
     if (!file.isDirectory()) {
         return;
     }
     String[] childFiles = file.list();
     File temp = null;
     for (int i = 0; i < childFiles.length; i++) {
         // File.separator与系统有关的默认名称分隔符
         // 在UNIX系统上，此字段的值为'/'；在Microsoft Windows系统上，它为 '\'。
         if (path.endsWith(File.separator)) {
             temp = new File(path + childFiles[i]);
         } else {
             temp = new File(path + File.separator + childFiles[i]);
         }
         if (temp.isFile()) {
             temp.delete();
         }
         if (temp.isDirectory()) {
             delAllFile(path + File.separatorChar + childFiles[i]);// 先删除文件夹里面的文件
             delFolder(path + File.separatorChar + childFiles[i]);// 再删除空文件夹
         }
     }
 }
 
 /**
  * 复制单个文件，传统方式
  * 
  * @param srcFile
  *            包含路径的源文件 如：E:/phsftp/src/abc.txt
  * @param dirDest
  *            目标文件目录；若文件目录不存在则自动创建 如：E:/phsftp/dest
  * @throws IOException
  */
 public static void copyFile(String srcFile, String dirDest)
         throws IOException {
     FileInputStream in = new FileInputStream(srcFile);
     mkdir(dirDest);
     FileOutputStream out = new FileOutputStream(dirDest + "/" + new File(srcFile).getName());
     int len;
     byte buffer[] = new byte[1024];
     while ((len = in.read(buffer)) != -1) {
         out.write(buffer, 0, len);
     }
     out.flush();
     out.close();
     in.close();
 }
 
 /**
  * 复制文件夹
  * 
  * @param oldPath
  *            String 源文件夹路径 如：E:/phsftp/src
  * @param newPath
  *            String 目标文件夹路径 如：E:/phsftp/dest
  * @return boolean
  */
 public static void copyFolder(String oldPath, String newPath)
         throws IOException {
     // 如果文件夹不存在 则新建文件夹
     mkdir(newPath);
     File file = new File(oldPath);
     String[] files = file.list();
     File temp = null;
     for (int i = 0; i < files.length; i++) {
         if (oldPath.endsWith(File.separator)) {
             temp = new File(oldPath + files[i]);
         } else {
             temp = new File(oldPath + File.separator + files[i]);
         }
         
         if (temp.isFile()) {
             FileInputStream input = new FileInputStream(temp);
             FileOutputStream output = new FileOutputStream(newPath + "/"
                     + (temp.getName()).toString());
             byte[] buffer = new byte[1024 * 2];
             int len;
             while ((len = input.read(buffer)) != -1) {
                 output.write(buffer, 0, len);
             }
             output.flush();
             output.close();
             input.close();
         }
         if (temp.isDirectory()) {// 如果是子文件夹
             copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
         }
     }
 }
 
 /**
  * 移动文件到指定目录
  * 
  * @param oldPath
  *            包含路径的文件名 如：E:/phsftp/src/ljq.txt
  * @param newPath
  *            目标文件目录 如：E:/phsftp/dest
  */
 public static void moveFile(String oldPath, String newPath)
         throws IOException {
     copyFile(oldPath, newPath);
     deleteFile(oldPath);
 }
 
 /**
  * 移动文件到指定目录，不会删除文件夹
  * 
  * @param oldPath
  *            源文件目录 如：E:/phsftp/src
  * @param newPath
  *            目标文件目录 如：E:/phsftp/dest
  */
 public static void moveFiles(String oldPath, String newPath)
         throws IOException {
     copyFolder(oldPath, newPath);
     delAllFile(oldPath);
 }
 
 /**
  * 移动文件到指定目录，会删除文件夹
  * 
  * @param oldPath
  *            源文件目录 如：E:/phsftp/src
  * @param newPath
  *            目标文件目录 如：E:/phsftp/dest
  */
 public static void moveFolder(String oldPath, String newPath)
         throws IOException {
     copyFolder(oldPath, newPath);
     delFolder(oldPath);
 }
 
 /**
  * 解压zip文件
  * 说明:本程序通过ZipOutputStream和ZipInputStream实现了zip压缩和解压功能. 
  * 问题:由于java.util.zip包并不支持汉字,当zip文件中有名字为中文的文件时, 
  * 就会出现异常:"Exception  in thread "main " java.lang.IllegalArgumentException  
  * at java.util.zip.ZipInputStream.getUTF8String(ZipInputStream.java:285) 
  * @param srcDir
  *            解压前存放的目录
  * @param destDir
  *            解压后存放的目录
  * @throws Exception
  */
 public static void unZip(String srcDir, String destDir)
         throws IOException {
     int leng = 0;
     byte[] b = new byte[1024 * 2];
     /** 获取zip格式的文件 **/
     File[] zipFiles = new ExtensionFileFilter("zip").getFiles(srcDir);
     if (zipFiles != null && !"".equals(zipFiles)) {
         for (int i = 0; i < zipFiles.length; i++) {
             File file = zipFiles[i];
             /** 解压的输入流 * */
             ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
             ZipEntry entry = null;
             while ((entry = zis.getNextEntry()) != null) {
                 File destFile = null;
                 if (destDir.endsWith(File.separator)) {
                     destFile = new File(destDir + entry.getName());
                 } else {
                     destFile = new File(destDir + File.separator + entry.getName());
                 }
                 /** 把解压包中的文件拷贝到目标目录 * */
                 FileOutputStream fos = new FileOutputStream(destFile);
                 while ((leng = zis.read(b)) != -1) {
                     fos.write(b, 0, leng);
                 }
                 fos.close();
             }
             zis.close();
         }
     }
 }
 
 /**
  * 压缩文件
  * 说明:本程序通过ZipOutputStream和ZipInputStream实现了zip压缩和解压功能. 
  * 问题:由于java.util.zip包并不支持汉字,当zip文件中有名字为中文的文件时, 
  * 就会出现异常:"Exception  in thread "main " java.lang.IllegalArgumentException  
  * at java.util.zip.ZipInputStream.getUTF8String(ZipInputStream.java:285) 
  * @param srcDir
  *            压缩前存放的目录
  * @param destDir
  *            压缩后存放的目录
  * @throws Exception
  */
 public static void zip(String srcDir, String destDir)
         throws IOException {
     String tempFileName = null;
     byte[] buf = new byte[1024 * 2];
     int len;
     // 获取要压缩的文件
     File[] files = new File(srcDir).listFiles();
     if (files != null) {
         for (File file : files) {
             if (file.isFile()) {
                 FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis);
                 if (destDir.endsWith(File.separator)) {
                     tempFileName = destDir + file.getName() + ".zip";
                 } else {
                     tempFileName = destDir + File.separator + file.getName() + ".zip";
                 }
                 FileOutputStream fos = new FileOutputStream(tempFileName);
                 BufferedOutputStream bos = new BufferedOutputStream(fos);
                 ZipOutputStream zos = new ZipOutputStream(bos);// 压缩包
                 
                 ZipEntry ze = new ZipEntry(file.getName());// 压缩包文件名
                 zos.putNextEntry(ze);// 写入新的ZIP文件条目并将流定位到条目数据的开始处
                 
                 while ((len = bis.read(buf)) != -1) {
                     zos.write(buf, 0, len);
                     zos.flush();
                 }
                 bis.close();
                 zos.close();
                 
             }
         }
     }
 }
 
 /**
  * 读取数据
  * 
  * @param inSream
  * @param charsetName
  * @return
  * @throws Exception
  */
 public static String readData(InputStream inSream, String charsetName)
         throws IOException {
     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
     byte[] buffer = new byte[1024];
     int len = -1;
     while ((len = inSream.read(buffer)) != -1) {
         outStream.write(buffer, 0, len);
     }
     byte[] data = outStream.toByteArray();
     outStream.close();
     inSream.close();
     return new String(data, charsetName);
 }
 
 /**
  * 一行一行读取文件，适合字符读取，若读取中文字符时会出现乱码
  * 
  * @param path
  * @return
  * @throws Exception
  */
 public static Set<String> readFileLine(String path)
         throws IOException {
     Set<String> datas = new HashSet<String>();
     FileReader fr = new FileReader(path);
     BufferedReader br = new BufferedReader(fr);
     String line = null;
     while ((line = br.readLine()) != null) {
         datas.add(line);
     }
     br.close();
     fr.close();
     return datas;
 }
 
 public static void main(String[] args) {
     try {
         unZip("c:/test", "c:/test");
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
 }
 
}


class ExtensionFileFilter
     implements FileFilter {
 
 private String extension;
 
 public ExtensionFileFilter(String extension) {
     this.extension = extension;
 }
 
 public File[] getFiles(String srcDir) throws IOException {
     return (File[]) FileUtil.listFiles(srcDir).toArray();
 }
 
 public boolean accept(File file) {
     if (file.isDirectory()) {
         return false;
     }
     
     String name = file.getName();
     // find the last
     int idx = name.lastIndexOf(".");
     if (idx == -1) {
         return false;
     } else if (idx == name.length() - 1) {
         return false;
     } else {
         return this.extension.equals(name.substring(idx + 1));
     }
 }
 /**
  *	创建时间：   2014年12月25日
  *	创建人： liting 
  * getFileSplit(这里用一句话描述这个方法的作用)    
  * @param   name    filepaht【文件的位置】  b[是否需要文件名的点]  
  * @Exception 异常对象    
  * @since  CodingExample　Ver(编码范例查看) 1.1
  */
 public static String getFileSplit(String filepath,boolean b){
	 File f=new File(filepath);
	 String name= f.getName();
	 String s="";
	 if(b){
		 s=name.substring(name.lastIndexOf("."));//要后缀名的点
	 }else{
		 s=name.substring(name.lastIndexOf(".")+1);//不要后缀名的点
	 }
	 return s;
}
 
 


}