package com.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



/**
 * socket服务端
 *
 * @author：Kyle.yangkg
 * @create：2017-09-26 上午 11:12
 * ©copyright：www.aisino.com
 */
public class SocketService {
    static ServerSocket serverSocket = null;

    public static void main(String[] args) throws IOException {
        try {
            //1创建服务端对象。
            serverSocket = new ServerSocket(9999);
            //2,获取连接过来的客户端对象。
            Socket socket = serverSocket.accept();

            System.out.println("来自客户端【" + socket.getInetAddress().getHostAddress() + "】的连接");

            BufferedReader bufferedReader = null;
            BufferedReader bufferedReader_SystemIn = null;
            BufferedWriter bufferedWriter = null;
            //3，通过socket对象获取输入流，要读取客户端发来的数据
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //4，通过字符输入流获取键盘输入，要读取控制台写给客户端的数据
            bufferedReader_SystemIn = new BufferedReader(new InputStreamReader(System.in));
            //5.使用客户端socket对象的输出流给客户端返回数据
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String message = "";
            while ((message = bufferedReader.readLine()) != null){
                System.out.println("来自客户端【" + socket.getInetAddress().getHostAddress() + "】说:" + message);
                System.out.print("请输入：");
                String responseMsg = bufferedReader_SystemIn.readLine();
                bufferedWriter.write(responseMsg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            serverSocket.close();
        }


    }
}
