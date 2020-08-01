package d0725.homeworkbysocketSendFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientFile {
	public static void main(String[] args) throws IOException {
        Socket s=new Socket("127.0.0.1",9999);
        //用户输入
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //文件读取
        BufferedReader bufferedReader=new BufferedReader(new FileReader("D:\\a.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            //if("over".equals(line))break;
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

        /*//自定义结束标记
        bufferedWriter.write("886");
        bufferedWriter.newLine();
        bufferedWriter.flush();*/
        s.shutdownOutput();

        //接受反馈
        BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String result=brClient.readLine();//等待读取数据
        System.out.println(result);

        bufferedReader.close();
        bufferedWriter.close();
        s.close();
    }
}
