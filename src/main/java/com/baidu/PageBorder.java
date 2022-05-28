package com.baidu;

import com.spire.doc.*;

import com.spire.doc.documents.BorderStyle;

import java.awt.*;
import java.io.File;

public class PageBorder {
    public static void main(String[] args){
        String filePath="E:\\文档\\上传\\上传\\2022嘲笑别人的话.docx";
        String folderPath="E:\\文档\\上传\\上传1";
        makeBorder(filePath,folderPath);
    }
    public static void makeBorder(String filePath,String folderPath){
        //加载测试文档
        File file=new File(filePath);
        String fileName=file.getName();
        Document doc= new Document(filePath);

//设置边框样式

        Section sec = doc.getSections().get(0);

        sec.getPageSetup().getBorders().setBorderType(BorderStyle.Single);

//设置边框在页面中的位置(靠近正文位置)

        sec.getPageSetup().setPageBorderOffsetFrom(PageBorderOffsetFrom.Text);//边框靠近正文内容

//设置边框紧挨正文内容时，可设置边框是否包含进页眉或页脚

        sec.getPageSetup().setPageBorderIncludeHeader(false);

        sec.getPageSetup().setPageBorderIncludeFooter(false);

//设置边框线条宽度、颜色、距离等

        sec.getPageSetup().getBorders().setLineWidth(0.5f);

        sec.getPageSetup().getBorders().getLeft().setColor(Color.black);

        sec.getPageSetup().getBorders().getRight().setColor(Color.black);

        sec.getPageSetup().getBorders().getTop().setColor(Color.black);

        sec.getPageSetup().getBorders().getBottom().setColor(Color.black);

//保存文档

        doc.saveToFile(folderPath+"\\"+fileName,FileFormat.Docx_2010);
    }

}