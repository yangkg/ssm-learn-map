package com.common.utils;


import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * java读取文件
 * 以字节为单位读取文件
 * 以字符为单位读取文件
 * 以行为单位读取文件
 * 随机读取文件内容
 *
 * @author：Kyle.yangkg
 * @create：2018-01-26 下午 4:27
 * ©copyright：www.aisino.com
 */

public class ReadFromFile {
    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件
     *
     * @param fileName 文件名
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;

        try {
            System.out.println("以字节为单位读取文件内容，一次读取一个字节");
            //一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.println(tempbyte);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        try {
            System.out.println("以字节为单位读取文件内容，一次读取多个字节");
            //一次读取多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            ReadFromFile.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     *
     * @param fileName 文件名
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;

        try {

            System.out.println("以字符为单位读取文件内容，一次读一个字符：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。

                // 但如果这两个字符分开显示时，会换两次行。

                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。

                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            System.out.println("以字符为单位读取文件内容，一次读多个字符：");

            //一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));

            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length) && (tempchars[tempchars.length - 1]) != '\r') {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param fileName 文件名
     */
    public static List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String tempString = null;
        List<String> result = new ArrayList<>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");

            reader = new BufferedReader(new FileReader(file));

            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String[] strings = tempString.split("- 线");
                String[] strings1 = strings[1].split(",发票");
                result.add(strings1[1]);

                line++;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }


    /**
     * 随机读取文件内容
     *
     * @param fileName 文件名
     */
    public static void readFileBRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;

        try {

            System.out.println("随机读取一段文件内容：");

            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置
            randomFile.seek(beginIndex);

            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。

            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 显示输入流中剩余的字节数
     *
     * @param in
     */
    public static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节流输入流中剩余的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //文本文件
        String fileName = "D:\\warn.2018-01-25-1.log";
        //图片文件
        //String fileName = "C:\\Users\\10412\\Desktop\\sp20161227_204413.png";
        //readFileByBytes(fileName);
        //readFileByChars(fileName);
        //readFileBRandomAccess(fileName);

    }
}
