import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.length;

public class modify {
    static String praBelowkeyPath = "src/main/douding/praBelowkey.txt";
    static String praDeletekeyPath = "src/main/douding/praDeletekey.txt";
    static String replacePath = "src/main/douding/doc_replace.txt";

    public static void main(String[] args) {
        String oldFolderPath = "D:\\文档\\百度活动文档\\test";
        String newFolderPath = "D:\\文档\\百度活动文档\\test1";
        //加载Word⽂档
        LinkedList fileList = GetDirectory(oldFolderPath);
        for (Object filePath : fileList) {
            if (filePath.toString().endsWith(".docx") || filePath.toString().endsWith(".DOCX")) {
                System.out.println(filePath.toString());
                modifyFile(filePath.toString(), newFolderPath);
            }
        }
    }

    public static void modifyFile(String filePath, String oldFolderPath) {
        File tmpFile = new File(filePath);
        String fileName = tmpFile.getName();
        String newFilePath = oldFolderPath + "\\" + fileName;
//        String newFilepathStr = replaceString(newFilePath);
        Document doc = new Document();
        doc.loadFromFile(filePath);
        replaceContent(doc, replacePath);
        //遍历⽂档中的节和段落，获取每个段落的⽂本
        for (int i = 0; i < doc.getSections().getCount(); i++) {
            String content="";
            Section section = doc.getSections().get(i);
            int deleteNumber = -1;
            for (int j = 0; j < section.getParagraphs().getCount(); j++) {
                Paragraph paragraph = section.getParagraphs().get(j);
                if (getPraJudge(paragraph.getText(), praBelowkeyPath) && (paragraph.getText().length() < 100)) {
                    deleteNumber = j;
                    break;
                }
            }
            if (!(deleteNumber == -1)) {
                for (int a = deleteNumber; a < section.getParagraphs().getCount(); ) {
                    //删除第一节的第a段
                    section.getParagraphs().removeAt(a);
                }
            }
            ArrayList<Integer> deletePraNumberList = new ArrayList<Integer>();
            int praNumber = 0;
            for (int j = 0; j < section.getParagraphs().getCount(); j++) {
                Paragraph paragraph = section.getParagraphs().get(j);
                content=content+paragraph.getText();
                if (getPraJudge(paragraph.getText(), praDeletekeyPath)) {
                    deletePraNumberList.add(j);
                }
                if ((j==section.getParagraphs().getCount()) && getpraLastJudge(paragraph.getText())){
                    deletePraNumberList.add(j);
                }
                if(paragraph.getText().equals(")")){
                    deletePraNumberList.add(j);
                }
            }
            for (int number : deletePraNumberList) {
                System.out.println(section.getParagraphs().getCount());
                //删除第一节的第a段
                section.getParagraphs().removeAt(number - praNumber);
                praNumber++;
            }
            //保存文档
            System.out.println(content.length());
            if (content.length()>100){
                doc.saveToFile(newFilePath, FileFormat.Docx_2013);
                tmpFile.delete();
            }
        }
    }
    //判断该内容是否以特殊符号结尾
    public static Boolean getpraLastJudge(String content){
        Boolean panduan = false;
        if (content.endsWith(":")){
            panduan=true;
        }
        return panduan;
    }
    //判断该内容是否包含关键词
    public static Boolean getPraJudge(String content, String praBelowkeyPath) {
        Boolean panduan = false;
        List keys = getPraBelowKeys(praBelowkeyPath);
        for (Object key :
                keys) {
            String keyStr = key.toString();
            if (content.indexOf(keyStr) != -1) {
                panduan = true;
                break;
            }
        }
        return panduan;
    }

    //文档内容替换
    public static void replaceContent(Document doc, String replacePath) {
        List keys = getPraBelowKeys(replacePath);
        for (Object key :
                keys) {
            String keyStr = key.toString();
            String s = keyStr.split("===>")[0];
            String content = keyStr.replace(s, "").replace("===>", "");
            String value = "";
            if (content.equals("")) {
                value = "";
            } else {
                value = keyStr.split("===>")[1];
            }
            doc.replace(s, value, false, true);
        }
    }

    //文档内容包含的年份替换
    public static void replaceContentYear(Document doc) {
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

    public static String replaceString(String newFilePath) {
        String newFilepathStr = newFilePath.replace("2007", "2023")
                .replace("2008", "2023")
                .replace("2009", "2023")
                .replace("2010", "2023")
                .replace("2011", "2023")
                .replace("2012", "2023")
                .replace("2013", "2023")
                .replace("2014", "2023")
                .replace("2015", "2023")
                .replace("2016", "2023")
                .replace("2017", "2023")
                .replace("2018", "2023")
                .replace("2019", "2023")
                .replace("2020", "2023")
                .replace("2021", "2023")
                .replace("2022", "2023")
                .replace("...", "");
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

    //获取txt文件的每一行内容
    public static ArrayList getPraBelowKeys(String praBelowkeyPath) {
        ArrayList res = new ArrayList();
        try {
            File fileKey = new File(praBelowkeyPath);
            String absolutePath = fileKey.getAbsolutePath();
            File file = new File(praBelowkeyPath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");// 编码格式必须和文件的一致
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
//注意文件里面的格式，我的里面是一行一行的，所以不需要再次切割了，直接添加就行
                    res.add(lineTxt);
                }
                read.close();
            } else {
                System.out.println("指定的文件不存在");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }
}
