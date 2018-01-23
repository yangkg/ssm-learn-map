package com.socket;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.Socket;

/**
 * socket客户端
 *
 * @author：Kyle.yangkg
 * @create：2017-09-26 上午 11:12
 * ©copyright：www.aisino.com
 */
public class SocketClient {
    public static void main(String[] args) {

        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader_Server = null;
        BufferedWriter bufferedWriter = null;

        try {
            //1,创建socket客户端对象。
            socket = new Socket("127.0.0.1", 9999);
            //2，获取键盘录入。获取控制台用户输入的信息
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            //3,socket输入流。读取服务端返回的数据
            bufferedReader_Server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //4,socket输出流。将控制台信息数据输出给服务端
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.print("请输入：");
            String message = "";

            while ((message = bufferedReader.readLine()) != null){
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                String getMessage = bufferedReader_Server.readLine();
                System.out.println("来自服务器说：" + getMessage);
                System.out.print("请输入：");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
