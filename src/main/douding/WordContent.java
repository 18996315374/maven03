import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


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
