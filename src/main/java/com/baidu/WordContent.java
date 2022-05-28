package com.baidu;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class WordContent {

    //    public static void main(String[] args) {
//
//    }
    public static List<XWPFParagraph> gatParas(String filePath) {
        // TODO Auto-generated method stub
        try {
            List list = new ArrayList<>();
            InputStream is = new FileInputStream(new File(filePath));  //需要将文件路更改为word文档所在路径。
            XWPFDocument xdoc = new XWPFDocument(is);
            List<XWPFParagraph> paras = xdoc.getParagraphs();
            return paras;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
