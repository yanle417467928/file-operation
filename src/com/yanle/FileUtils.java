package com.yanle;

import java.io.File;

/**
 * 文件操作工具类
 *
 * @author Richard
 * Created on 2017-08-28 10:37
 **/
public class FileUtils {

    /**
     * 列出指定目录下（包括其子目录）的所有文件
     * @param dir
     */
    public static void listDirectory(File dir){
        if (!dir.exists()){
            throw new IllegalArgumentException("目录"+ dir +"不存在");
        }
        if(! dir.isDirectory()){
            throw new IllegalArgumentException(dir+"不是目录");
        }
        File files [] = dir.listFiles();
        if(null != files && files.length>0){
            for (File f: files){
                System.out.println(f);
                if ( f.isDirectory()){
                    listDirectory(f);
                }
            }
        }

    }
}
