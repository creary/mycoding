package com.yf.util;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import net.contentobjects.jnotify.JNotifyListener;

/**
 * 监控文件变化
 * @author mycode
 *
 */
public class FileNotifyer {

	public static void main(String[] args) {
		FileNotifyer.createNotifyer("/home/upload/");
		while (true) {
		}
	}

	private static int watchId = 0;
	private static boolean watched = false;

	public static boolean isWatched() {
		return watched;
	}
	
	public static boolean createNotifyer() {
		return createNotifyer("/home/upload/");
	}
	
	public static boolean createNotifyer(String cachePath) {
		if (watched) return true;
		
		//int mask = JNotify.FILE_CREATED | JNotify.FILE_DELETED | JNotify.FILE_MODIFIED | JNotify.FILE_RENAMED;
		int mask = JNotify.FILE_DELETED | JNotify.FILE_RENAMED;
		boolean watchSubtree = true;
		try {
			watchId = JNotify.addWatch(cachePath, mask, watchSubtree, new JNotifyListener() {
				public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
					//LoggerHanlder.info("file modified " + rootPath + "---->" + oldName);
					//sendEmail();
				}

				public void fileModified(int wd, String rootPath, String name) {
					//LoggerHanlder.info("file modified " + rootPath + "/" + name);
				}

				public void fileDeleted(int wd, String rootPath, String name) {
					//LoggerHanlder.info("file deleted " + rootPath + "/" + name);
				}

				public void fileCreated(int wd, String rootPath, String name) {
					//LoggerHanlder.info("file created " + rootPath + "," + name);
				}
			});
		} catch (JNotifyException e) {
			///LoggerHanlder.handle("FileNotifyer", "word文件监控出错", e);
			return false;
		}

		//LoggerHanlder.info("file notify start with id:" + watchId);
		watched = true;
		return watchSubtree;
	}

	public static boolean destory() {
		if (!watched) return true;
		boolean res;
		try {
			res = JNotify.removeWatch(watchId);
			//LoggerHanlder.info("file notify end with id:" + watchId);
		} catch (JNotifyException e) {
			//LoggerHanlder.error(e);
			return false;
		}
		watched = false;
		return res;
	}

}

