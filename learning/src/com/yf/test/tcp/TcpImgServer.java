package com.yf.test.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpImgServer {
	public static void main(String[] args) throws IOException {
		

		ServerSocket ss = new ServerSocket(10005);//监听10005端口
		Socket s = ss.accept(); //获取客户端的Socket对象，ServerSocket没有IO流
		
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"......connected");
		
		//读取socket读取流中的数据。源。
		BufferedReader bufIn = 
			new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//目的：socket输出流。将大写数据写入到socket输出流，发送给客户端。
		BufferedWriter bufOut = 
			new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
		//打印流的println自动刷新缓冲区，而且打印流可接收字节流和字符流。
	//  PrintWriter bufOut = new PrintWriter(s.getOutputStream, true);
		
		String line = null;
		while((line=bufIn.readLine())!=null){
			System.out.println(line);
			bufOut.write(line.toUpperCase());//toUpperCase()字符串转大写。
			bufOut.newLine();
			bufOut.flush();
		}
		s.close();
		ss.close();
	}

}
