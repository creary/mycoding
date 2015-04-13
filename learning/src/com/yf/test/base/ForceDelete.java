package com.yf.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.yf.util.FileUtil;

public class ForceDelete {
    public static void main(String[] args) {
    	String s="D:/cache/windows/Desktop/log.txt";
    	try {
    		System.gc();
    		FileUtils.deleteQuietly(new File(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 功 能: 删除文件夹 参 数: strDir 要删除的文件夹名称 返回值: 如果成功true;否则false
     * 
     * @param strDir
     * @return
     */
    public static boolean removeDir(String strDir) {
        File rmDir = new File(strDir);
        if (rmDir.isDirectory() && rmDir.exists()) {
            String[] fileList = rmDir.list();

            for (int i = 0; i < fileList.length; i++) {
                String subFile = strDir + File.separator + fileList[i];
                File tmp = new File(subFile);
                if (tmp.isFile()){
                    tmp.delete();
                }
                if (tmp.isDirectory()){
                    removeDir(subFile);
                }
            }
            rmDir.delete();
        } else {
            return false;
        }
        return true;
    }
}