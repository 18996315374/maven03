package com.baidu;

import java.io.File;
import java.util.List;

public class AddBorder {
    public static void main(String[] args) {
        String folderPath="E:\\文档\\上传\\上传";
        String folderPathOther="E:\\文档\\上传\\上传1";
        getBanli( folderPath, folderPathOther);
    }
    public static void getBanli(String folderPath,String folderPathOther) {
        List<String> filePathList=Bianli.getFilePathList(new File(folderPath));
        for (String filePath:filePathList
        ) {
            System.out.println(filePath);
            PageBorder.makeBorder(filePath,folderPathOther);
        }
    }
}
