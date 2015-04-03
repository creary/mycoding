package com.yf.util.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	private static Properties properties;

	static {
		properties = new Properties();
		try {
			properties.load(ConfigManager.class.getResourceAsStream("/config.properties"));
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
		}

	}

	/**
	 * 获取配置文件属性值
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		if (properties != null) {
			return properties.getProperty(key);
		}
		return null;
	}
}
