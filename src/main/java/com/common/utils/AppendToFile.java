package com.common.utils;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件追加内容
 *
 * @author：Kyle.yangkg
 * @create：2018-01-26 下午 4:29
 * ©copyright：www.aisino.com
 */
public class AppendToFile
{
    /**
     * 第一种方法追加文件：使用RandomAccessFile
     * @param fileName 文件名
     * @param content   追加内容
     */
    public static void appendMethod1(String fileName, String content)
    {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");

            // 文件长度，字节数
            long fileLength = randomFile.length();

            //将写文件指针移到文件尾
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 第二种方法追加文件：使用FileWriter
     * @param fileName  文件名
     * @param content   追加内容
     */
    public static void appendMethod2(String fileName, String content)
    {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        String fileName = "C:\\Users\\10412\\Desktop\\1.txt";       //文本文件
        String content = "new append!";

        //按方法1追加文件
//        AppendToFile.appendMethod1(fileName, content);
//        AppendToFile.appendMethod1(fileName, "\new append. 第一种方法\n");

        //按照方法2追加文件
        AppendToFile.appendMethod2(fileName, content);
        AppendToFile.appendMethod2(fileName, "\nnew append. 第二种方法\n");


        //显示文件内容
        ReadFromFile.readFileByLines(fileName);
    }
}
