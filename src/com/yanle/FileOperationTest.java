package com.yanle;

import java.io.*;

/**
 * 文件操作测试
 *
 * @author Richard
 * Created on 2017-08-28 9:30
 **/
public class FileOperationTest {
    public static void main(String[] args) throws IOException {
        //easyFileReadWrite();
    }

    public static void easyFileReadWrite(){
        File file = new File("e:\\","addfile.txt");
        try {
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        String str = "亲爱的小南瓜！";
        byte bt[] = new byte[1024];
        bt = str.getBytes();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(bt,0,bt.length);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(in);
        int ch = 0;
        try {
            while((ch=isr.read())!=-1){
                System.out.println((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
