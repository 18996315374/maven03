import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.fields.TextRange;

import java.io.File;
import java.util.LinkedList;

public class test {
    public static void main(String[] args) {
        Document document = new Document();
        Section sec = document.addSection();
        Document doc = new Document("E:\\文档\\jieguo\\2019.docx");
        replaceContent( doc);
////        LinkedList fileList=GetDirectory("E:\\文档\\豆丁文档\\豆丁word");
////        File tmpFile=new File("E:\\文档\\豆丁文档\\豆丁word\\(完整版)公务员考试数学试题.docx");
////        String fileName=tmpFile.getName();
////        System.out.println(fileName);
////        for (Object file:fileList){
//////            System.out.println(file.toString());
////        }
//        //遍历⽂档中的节和段落，获取每个段落的⽂本
//        int deleteNumber =-1;
//        for(int i = 0; i < doc.getSections().getCount(); i++) {
//            Section section = doc.getSections().get(i);
//            for (int j = 0; j < section.getParagraphs().getCount(); j++) {
//                Paragraph paragraph = section.getParagraphs().get(j);
//                Paragraph paragraphw = sec.addParagraph();
//                TextRange tr=paragraphw.appendText(paragraph.getText());
//                tr.getCharacterFormat().setFontSize(20);
//                ParagraphStyle style1 = new ParagraphStyle(document);
////                style1.setName("style");
//                style1.getCharacterFormat().setFontName("宋体");
//                document.getStyles().add(style1);
//                paragraphw.applyStyle(style1.getName());
////                if (paragraph.getText().equals("点击加载更多")) {
////                    deleteNumber=j;
////                }
//            }
////            if (!(deleteNumber==-1)){
////                for (int a = deleteNumber; a < section.getParagraphs().getCount(); ){
////                    //删除第一节的第二段
////                    section.getParagraphs().removeAt(a);
////                }
////            }

            //保存文档
        doc.saveToFile("E:\\文档\\test\\!!!论图形创意在平面广告设计中的运用.docx", FileFormat.Docx);
        }
//    }
    public static  void replaceContent(Document doc){
        doc.replace("2005", "2023", false, true);
        doc.replace("2006", "2023", false, true);
        doc.replace("2007", "2023", false, true);
        doc.replace("2008", "2023", false, true);
        doc.replace("2009", "2023", false, true);
        doc.replace("2010", "2023", false, true);
        doc.replace("2011", "2023", false, true);
        doc.replace("2012", "2023", false, true);
        doc.replace("2013", "2023", false, true);
        doc.replace("2014", "2023", false, true);
        doc.replace("2015", "2023", false, true);
        doc.replace("2016", "2023", false, true);
        doc.replace("2017", "2023", false, true);
        doc.replace("2018", "2023", false, true);
        doc.replace("2019", "2023", false, true);
        doc.replace("2020", "2023", false, true);
        doc.replace("2021", "2023", false, true);
        doc.replace("2022", "2023", false, true);
    }
    public static LinkedList GetDirectory(String path) {

        File file = new File(path);

        LinkedList Dirlist = new LinkedList(); // 保存待遍历文件夹的列表

        LinkedList fileList = new LinkedList();

        GetOneDir(file, Dirlist, fileList);// 调用遍历文件夹根目录文件的方法

        File tmp;

        while (!Dirlist.isEmpty()) {

            tmp = (File) Dirlist.removeFirst();

// 从文件夹列表中删除第一个文件夹，并返回该文件夹赋给tmp变量

// 遍历这个文件夹下的所有文件，并把

            GetOneDir(tmp, Dirlist, fileList);

        }

        return fileList;

    }

// 遍历指定文件夹根目录下的文件

    private static void GetOneDir(File file, LinkedList Dirlist,

                                  LinkedList fileList) {

// 每个文件夹遍历都会调用该方法

        File[] files = file.listFiles();

        if (files == null || files.length == 0) {

            return;

        }

        for (File f : files) {

            if (f.isDirectory()) {

                Dirlist.add(f);

            } else {

// 这里列出当前文件夹根目录下的所有文件,并添加到fileList列表中

                fileList.add(f);

// System.out.println("file==>" + f);

            }

        }

    }

}
