package com.baidu;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.fields.TextRange;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.awt.*;
import java.util.List;

public class MakeWord {
    public static void main(String[] args) {
        Document doc = new Document();
        String filePath="E:\\文档\\道客范文网\\2020年技术经理工作总结.docx";
        List<XWPFParagraph> paras = WordContent.gatParas(filePath);
        for (int i = 0;i < paras.size();i++) {
            String content=paras.get(i).getText().trim();
            System.out.println(content);
            System.out.println(i);
            System.out.println(paras.size());
            Paragraph para = fillContent(doc, content);
//            if(i==0 && ){
//
//            }else if(){
//
//            }

        }
        doc.saveToFile("E:\\文档\\自查报告网\\hello.docx");
        doc.dispose();
    }
    //给段落填充内容
    public static Paragraph fillContent(Document doc,String content){
        Section sec = doc.addSection();
        Paragraph para1 = sec.addParagraph();
        TextRange textRange = para1.appendText(content);
        return para1;
    }
    //将第一段作为标题，设置标题格式
    public static void firstParaStyle(Document doc,Paragraph para1){
        ParagraphStyle style1 = new ParagraphStyle(doc);
        style1.setName("titleStyle");
        style1.getCharacterFormat().setBold(true);
        style1.getCharacterFormat().setTextColor(Color.RED);
        style1.getCharacterFormat().setFontName("宋体");
        style1.getCharacterFormat().setFontSize(16f);
        doc.getStyles().add(style1);
        para1.applyStyle("titleStyle");
        //设置第一个段落的对齐方式
        para1.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        para1.getFormat().setAfterSpacing(15f);
    }
    //设置小标题格式
    public static void titleParaStyle(Document doc,Paragraph para1){
        ParagraphStyle style1 = new ParagraphStyle(doc);
        style1.setName("titleStyle");
        style1.getCharacterFormat().setBold(true);
        style1.getCharacterFormat().setTextColor(Color.BLUE);
        style1.getCharacterFormat().setFontName("宋体");
        style1.getCharacterFormat().setFontSize(14f);
        doc.getStyles().add(style1);
        para1.applyStyle("titleStyle");
        para1.getFormat().setAfterSpacing(10f);
    }
    //设置正文格式
    public static void otherParaStyle(Document doc,Paragraph para1){
        //设置其余两个段落的格式
        ParagraphStyle style2 = new ParagraphStyle(doc);
        style2.setName("paraStyle");
        style2.getCharacterFormat().setFontName("宋体（正文）");
        style2.getCharacterFormat().setFontSize(14f);
        doc.getStyles().add(style2);
        para1.applyStyle("titleStyle");
        para1.getFormat().setFirstLineIndent(25f);
        para1.getFormat().setAfterSpacing(8f);
    }

}
