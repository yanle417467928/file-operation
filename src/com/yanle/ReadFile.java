package com.yanle;

import javax.annotation.processing.FilerException;
import java.io.*;

/**
 * java 读取文件多种方式
 *
 * @author Richard
 * Created on 2017-08-30 9:36
 **/
public class ReadFile {

    public static void main(String[] args) throws IOException {
        String fileName = "e:\\addfile.txt";
        //readFileByBytes(fileName);
        //readFileByChars(fileName);
        //readFileByLines(fileName);
        readFileByRandomAccess(fileName);
    }
    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     *
     * @param fileName
     *            文件的名
     */
    public static void readFileByBytes(String fileName) throws IOException {
        File file = new File(fileName);
        InputStream in = null;
        System.out.println("以字节为单位读取文件内容，一次读一个字节:");
        //一次读一个字节
        in = new FileInputStream(file);
        int tempByte;
        while ((tempByte = in.read())!=-1){
            System.out.println(tempByte);
        }
        in.close();
        System.out.println();
        System.out.println("以字节为单位读取文件内容，一次读多个字节");
        //一次读多个字节
        byte []tempBytes = new byte[100];
        int byteRead = 0;
        in = new FileInputStream(fileName);
        showAvailableBytes(in);
        while ((byteRead = in.read(tempBytes))!=-1){
            System.out.println(tempBytes);
            System.out.println(byteRead);
        }

        in.close();
    }

    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     *
     * @param fileName
     *            文件名
     */
    public static void readFileByChars(String fileName) throws IOException {
        File file = new File(fileName);
        Reader reader = null;
        System.out.println("以字符为单位读取文件内容，一次读一个字符:");
        //一次读一个字符
        reader = new InputStreamReader(new FileInputStream(file));
        int tempChar;
        while ((tempChar = reader.read())!=-1){
            if (((char)tempChar)!='r'){
                System.out.println((char)tempChar);
            }
        }
        reader.close();
        System.out.println("以字符为单位读取文件内容，一次读多个字节:");
        //一次读多个字符
        char []tempChars = new char[30];
        int charRead = 0;
        reader = new InputStreamReader(new FileInputStream(fileName));
        while ((charRead = reader.read(tempChars)) != -1){
            if (charRead==tempChars.length && (tempChars[tempChars.length-1] !='r' )){
                System.out.println(tempChars);
            }else {
                for (int i = 0; i < charRead; i++) {
                    if (tempChars[i] == 'r') {
                        continue;
                    } else {
                        System.out.print(tempChars[i]);
                    }
                }
            }
        }
        reader.close();
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param fileName
     *            文件名
     */
    public static void readFileByLines(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader reader = null;
        System.out.println("以行为单位读取文件内容，一次读取一整行:");
        reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        int line = 1;
        //一次读入一行，直到读入null为文件结束
        while((tempString = reader.readLine())!= null){
            //显示行号
            System.out.println("line"+line+":"+tempString);
            line++;
        }
        reader.close();
    }

    /**
     * 随机读取文件内容
     *
     * @param fileName
     *            文件名
     */
    public static void readFileByRandomAccess(String fileName) throws IOException {
        RandomAccessFile randomAccessFile = null;
        System.out.println("随机读取一段文件内容:");
        //打开一个随机访问文件流，按只读方式
        randomAccessFile = new RandomAccessFile(fileName,"r");
        //文件长度，字节数
        long fileLength = randomAccessFile.length();
        //读文件的起始位置
        // 将读文件的开始位置移到beginIndex位置。
       /* int beginIndex = (fileLength>4)?4:0;
        randomAccessFile.seek(beginIndex);*/
        byte[] bytes = new byte[10];
        int byteread = 0;
        // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
        // 将一次读取的字节数赋给byteread
        while ((byteread = randomAccessFile.read(bytes)) != -1) {
            System.out.write(bytes, 0, byteread);
        }
        randomAccessFile.close();
    }
}
