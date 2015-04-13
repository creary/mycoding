package com.yf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import sun.misc.BASE64Decoder;

public class GpsToBaiduMapUtil {

	public static void main(String args[]) throws Exception {  
        System.out.println(changgeXY("116.397428", "39.90923"));;  
    }  
	
	  /** GPS坐标转换百度地图坐标
	   * @author liting
	   *  xx ：gps横坐标
	   *  yy ：gps 纵坐标
	   * @return 百度地图坐标
	   */
    public static String changgeXY(String xx, String yy) {  
        try {  
            Socket s = new Socket("api.map.baidu.com", 80);  
            BufferedReader br = new BufferedReader(new InputStreamReader(  
                    s.getInputStream(), "UTF-8"));  
            OutputStream out = s.getOutputStream();  
            StringBuffer sb = new StringBuffer(  
                    "GET /ag/coord/convert?from=0&to=4");  
            sb.append("&x=" + xx + "&y=" + yy);  
            sb.append("&callback=BMap.Convertor.cbk_3976 HTTP/1.1\r\n");  
            sb.append("User-Agent: Java/1.6.0_20\r\n");  
            sb.append("Host: api.map.baidu.com:80\r\n");  
            sb.append("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n");  
            sb.append("Connection: Close\r\n");  
            sb.append("\r\n");  
            out.write(sb.toString().getBytes());  
            String json = "";  
            String tmp = "";  
            while ((tmp = br.readLine()) != null) {  
                // System.out.println(tmp);  
                json += tmp;  
            }  
            int start = json.indexOf("cbk_3976");  
            int end = json.lastIndexOf("}");  
            if (start != -1 && end != -1&& json.contains("\"x\":\"")) {  
                json = json.substring(start, end);  
                String[] point = json.split(",");  
                String x = point[1].split(":")[1].replace("\"", "");  
                String y = point[2].split(":")[1].replace("\"", "");  
                return (new String(decode(x)) + "," + new String(decode(y)));  
            } else {  
                System.out.println("gps坐标无效！！");  
            }  
            out.close();  
            br.close();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    /**  
     * 解码  
     *   
     * @param str  
     * @return string  
     */  
    public static byte[] decode(String str) {  
        byte[] bt = null;  
        try {  
            BASE64Decoder decoder = new BASE64Decoder();  
            bt = decoder.decodeBuffer(str);  
            // System.out.println(new String (bt));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return bt;  
    } 
}
