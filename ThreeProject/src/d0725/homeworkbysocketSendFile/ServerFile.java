package d0725.homeworkbysocketSendFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {
	 public static void main(String[] args) throws IOException {
	        ServerSocket ss=new ServerSocket(9999);
	        Socket s = ss.accept();
	        System.out.println("Server Ok");
	        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(s.getInputStream()));
	       // FileWriter fw=new FileWriter("D:\\xkh.txt");
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:\\kyy.txt"));
	        String line;
	        while ((line=bufferedReader.readLine())!=null){//等待读取数据
	           // if ("886".equals(line))break;
	            bufferedWriter.write(line);
	            bufferedWriter.newLine();
	            bufferedWriter.flush();
	        }

	        //给出反馈
	        BufferedWriter bwServer=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
	        bwServer.write("文件上传成功！！！");
	        bwServer.newLine();
	        bwServer.flush();

	        ss.close();
	        bufferedReader.close();
	        bufferedWriter.close();
	    }
}
