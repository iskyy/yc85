package d0727;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
	public static void main(String[] args) throws IOException {
		ServerSocket tomcat=new ServerSocket(8080);
		System.out.println("tomcat 服务器启动完成，监听端口：8080");
		boolean running=true;
		while(running) {
			Socket socket=tomcat.accept();
			new Thread() {
				public void run(){
					try {
						System.out.println("接收到请求");
						InputStream in=socket.getInputStream();
						OutputStream out=socket.getOutputStream();
						
						byte[]buffer=new byte[1024];
						int count;
						count=in.read(buffer);
						if(count>0) {
							//打印请求报文
							String requestText=new String(buffer, 0, count);
							System.out.println(requestText);
							
							/**
							 * 解析出请求资源路径
							 */
							String[] lines=requestText.split("\\n");
							String[] requestLines=lines[0].split("\\s");
							String webpath=requestLines[1];
							String contentType;
							//结果码
							int statusCode= 200;
							//定义磁盘文件路径
							String path="D:/三期/"+webpath;
							File file=new File(path);
							
							if(!file.exists()) {
								statusCode=404;
								path="D:/三期/photo/404.html";
							}
							if(webpath.endsWith(".js")) {
								contentType="application/javascript; charset=uft-8";
							}else if(webpath.endsWith(".css")) {
								contentType="text/css; charset=uft-8";
							}else {
								contentType="text/html; charset=uft-8";
							}
							
							//响应头行
							out.write(("HTTP/1.1 "+ statusCode + "OK\n").getBytes());
							//响应头域
							out.write(("Content-Type: "+ contentType + "\n").getBytes());
							//空行 CRLF
							out.write("\n".getBytes());
							//实体
							//out.write("<h1>Hello World</h1>".getBytes());
							System.out.println(webpath);
							/**
							 * 问题：
							 *  1.只能回复一次
							 *  2.回复的内容永远不变
							 *  	1）
							 */
							 FileInputStream fis=new FileInputStream(path);
							  while((count=fis.read(buffer))>0) { 
								  out.write(buffer, 0, count);
							 }
							fis.close(); 
						}
						
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
		}
		
		
		tomcat.close();
		
		
		
	}

}
