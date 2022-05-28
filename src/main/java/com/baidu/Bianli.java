package com.baidu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 递归实现遍历文件夹，获得子文件夹的路径
 */

public class Bianli {
    public static void main(String[] ssd) {
        //这里的文件路径是绝对路径（即必须写明具体在哪儿）
        getFilePathList(new File("E:\\文档\\上传\\上传"));
    }
    public static List getFilePathList(File file) {
        List filePathList = new ArrayList<String>();
        isDirectory(file, filePathList);
        System.out.println(filePathList.toString());
        return filePathList;
    }
    public static void isDirectory(File file, List filePathList) {
        if(file.exists()){
            if (file.isFile()) {
                System.out.println("file is ==>>" + file.getAbsolutePath());
                filePathList.add(file.getAbsolutePath());
            }else{
                File[] list = file.listFiles();
                if (list.length == 0) {
                        System.out.println(file.getAbsolutePath() + " is null");
                } else {
                    for (int i = 0; i < list.length; i++) {
                        isDirectory(list[i],filePathList);//递归调用
                    }
                }
            }
        }else{
            System.out.println("文件不存在！");
        }
    }

}

