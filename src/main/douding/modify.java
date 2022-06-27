import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;

import java.io.File;
import java.util.LinkedList;

public class modify {
    public static void main(String[] args) {
        String oldFolderPath="E:\\文档\\test";
        String newFolderPath="E:\\文档\\jieguo";
        //加载Word⽂档
        LinkedList fileList=GetDirectory(oldFolderPath);
        for (Object filePath:fileList){
            if (filePath.toString().endsWith(".docx") || filePath.toString().endsWith(".DOCX")){
                System.out.println(filePath.toString());
                modifyFile(filePath.toString(),newFolderPath);
            }
        }
    }

    public static void  modifyFile(String filePath,String oldFolderPath){
        File tmpFile=new File(filePath);
        String fileName=tmpFile.getName();
        String newFilePath=oldFolderPath+"\\"+fileName;
        String newFilepathStr=replaceString( newFilePath);
        Document doc = new Document();
        doc.loadFromFile(filePath);
        replaceContent(doc);
        //遍历⽂档中的节和段落，获取每个段落的⽂本
        int deleteNumber =-1;
        for(int i = 0; i < doc.getSections().getCount(); i++) {
            Section section = doc.getSections().get(i);
            for (int j = 0; j < section.getParagraphs().getCount(); j++) {
                Paragraph paragraph = section.getParagraphs().get(j);
                if (paragraph.getText().equals("点击加载更多")) {
                    deleteNumber=j;
                }
            }
            if (!(deleteNumber==-1)){
                for (int a = deleteNumber; a < section.getParagraphs().getCount(); ){
                        //删除第一节的第二段
                        section.getParagraphs().removeAt(a);
                }
            }

            //保存文档
            doc.saveToFile(newFilepathStr, FileFormat.Docx_2013);
            tmpFile.delete();
        }
    }
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
    public static String replaceString(String newFilePath){
        String newFilepathStr=newFilePath.replace("2007","2023")
                .replace("2008","2023")
                .replace("2009","2023")
                .replace("2010","2023")
                .replace("2011","2023")
                .replace("2012","2023")
                .replace("2013","2023")
                .replace("2014","2023")
                .replace("2015","2023")
                .replace("2016","2023")
                .replace("2017","2023")
                .replace("2018","2023")
                .replace("2019","2023")
                .replace("2020","2023")
                .replace("2021","2023")
                .replace("2022","2023")
                .replace("...","");
        return newFilepathStr;
    }
    public static LinkedList GetDirectory(String folderPath) {

        File file = new File(folderPath);

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
