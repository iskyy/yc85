package d0725.homeworkbysocketSendFile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	@Override
    public void run() {

    }

    public static void main(String[] args) {
        try {
            final ServerSocket server = new ServerSocket(8080);
            Thread th = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        try {
                           System.out.println("开始监听。。。");
                           Socket socket = server.accept();
                           System.out.println("有链接");
                           receiveFile(socket);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

            });
            th.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
	public static void receiveFile(Socket socket) throws IOException {
        byte[] inputByte = null;
        int length = 0;
        DataInputStream din = null;
        FileOutputStream fout = null;
        try {
            din = new DataInputStream(socket.getInputStream());
            
            fout = new FileOutputStream(new File("D:\\"+din.readUTF()));
            inputByte = new byte[1024];
            System.out.println("开始接收数据...");
            int count = 0;
            while (true) {
                if (din != null) {
                    length = din.read(inputByte, 0, inputByte.length);
                    
                  
                }
                if (length == -1) {
                    break;
                }
                if(fout !=null) {
                	count=din.read(inputByte);
                }
                System.out.println(length);
                System.out.println(new String(inputByte,count));
                fout.write(inputByte, 0, length);
                fout.flush();
            }
            System.out.println("完成接收");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null)
                fout.close();
            if (din != null)
                din.close();
            if (socket != null)
                socket.close();
        }
    }
  

}