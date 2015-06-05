package com.yf.test.base.safety;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Base64 {
		/**		加密字符串
		 * 
		 * @param s ：需要加密的字符串
		 * @return 加密过后的字符串
		 */
	public String getContent(String s) {
		String content = "";
		try {
			FileInputStream in = new FileInputStream(s);
			byte[] by = new byte[in.available()];
			if (by.length < 102400) {
				System.out.println(by.length);
			}
			in.read(by);
			content = new sun.misc.BASE64Encoder().encode(by);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
