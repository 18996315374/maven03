import com.baidu.WordContent;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.fields.TextRange;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class ModifyDocument {
    static String titleKeyPath = "src/main/douding/titleKey.txt";

    public static void main(String[] args) throws InterruptedException, IOException {

        String folderPath = "D:\\文档\\百度活动文档\\test1";
        String newFolderPath="D:\\文档\\百度活动文档\\test2";
        String failurePath="D:\\文档\\百度活动文档\\test4";
        LinkedList fileList = GetDirectory(folderPath);
        for (Object filePath : fileList) {
            Document doc = new Document();
            Section sec = doc.addSection();
            String filePathx = filePath.toString();
            File file = new File(filePathx);
            String fileName = file.getName();
            System.out.println(fileName);
            if (panduanFileName(fileName)) {
                //写入并设置标题
                String title = fileName.replace(".docx", "");
                Paragraph paraTitle = fillContent(sec, title);
                firstParaStyle(doc, paraTitle);
                //写入并设置其他段落
                List<XWPFParagraph> paras = WordContent.gatParas(filePathx);
                Boolean bool=true;
                if (paras.size() != 0) {
                    for (int i = 0; i < paras.size(); i++) {
                        String content = paras.get(i).getText().trim();
                        if(content.indexOf("⼯")!=-1){
                            bool=false;
                            break;
                        }
                        System.out.println(content);
                        if ((!(i == 0 && content.equals(title))) && !(content==null || content.equals(""))) {
                            String pattern = "[a-z]{2}";
                            Boolean panduan =Pattern.matches(pattern,content);
                            if (!panduan){
                                content=content.replace("　　","").replace(" ", "").replace("\n","");
                            }
                            String patternx = "#\\d+";
                            content=content.replaceAll(patternx,"");
                            Paragraph para = fillContent(sec, content);
                            makeParagraphType(doc,para,content);
                        }
                    }
                    if(bool){
                        doc.saveToFile(newFolderPath+"\\"+fileName);
                        doc.dispose();
                        if(file.delete()){
                            System.out.println("Deleted the file: " + file.getName());
                        }else {
                            System.out.println("Failed to delete the file.");
                        }
                    }else {
                        try {
                            Files.copy(Paths.get(filePathx), Paths.get(failurePath + "\\" + fileName));
                        } catch (IOException e) {
                            System.out.println(e);
                        }

                        if (file.delete()) {
                            System.out.println("Deleted the file: " + file.getName());
                        } else {
                            System.out.println("Failed to delete the file.");
                        }
                    }
                } else {
                    file.delete();
                }
            }
        }
    }
    public static void makeParagraphType(Document doc,Paragraph para,String content) throws InterruptedException {
        if(getBooleanBylittleTitle(content)){
            titleParaStyle(doc, para);
        } else if (getBooleanByMail(content)) {
            xinjianParaStyle(doc, para);
        } else if (getBooleanByOrder(content)) {
            orderParaStyle(doc, para);
        }else {
            otherParaStyle(doc, para);
        }
    }
    //判断该段是否含有大的序号
    public static Boolean getBooleanByOrder(String content){
        Boolean puanduan=false;
        String[] orderKeys={"一、","二、","'三、","四、","五、","六、","七、","八、","九、","十、"};
        if(content.length()<45){
            for (String orderKey:
            orderKeys) {
                if(content.startsWith(orderKey)){
                    puanduan=true;
                }
            }
        }
        return puanduan;
    }
    //判断该段是否是信件的尊称
    public static Boolean getBooleanByMail(String content){
        Boolean puanduan=false;
        String[] mailKeys={"尊敬的","亲爱的","各位","朋友"};
        if(content.endsWith("：") && content.length()<12){
            for (String mailKey:
            mailKeys) {
                if(content.indexOf(mailKey)!=-1){
                    puanduan=true;
                }
            }
        }
        return puanduan;
    }
    //判断该段是否是小标题
    public static Boolean getBooleanBylittleTitle(String content){
        Boolean puanduan=false;
        String[] startKeys= {"第1篇","第2篇","第3篇","第4篇","第5篇","第6篇","第7篇","第8篇","第9篇","第10篇",};
        String[] endKeys={"第1篇","第2篇","第3篇","第4篇","第5篇","第6篇","第7篇","第8篇","第9篇","第10篇",
                "一：","二：","三：","四：","五：","六：","七：","八：","九：","十：","（一）","（二）","（三）",
                "（四）","（五）","（六）","（七）","（八）","（九）","（十）","(一)","(二)","(三)","(四)","(五)","(六)",
                "(七)","(八)","(九)","(十)","〔一〕","〔二〕","〔三〕","〔四〕","〔五〕","〔六〕","〔七〕","〔八〕","〔九〕",
                "〔十〕","【一】","【二】","【三】","【四】","【五】","【六】","【七】","【八】","【九】","【十】","【篇一】","【篇二】","【篇三】","【篇四】",
                "【篇五】","【篇六】","【篇七】","【篇八】","【篇九】","【篇十】","第1篇","第2篇","第3篇","第4篇","第5篇","第6篇","第7篇","第8篇","第9篇",
                "第10篇","篇一","篇二","篇三","篇四","篇五","篇六","篇七","篇八","篇九","篇十",
                "篇1","篇2","篇3","篇4","篇5","篇6","篇7","篇8","篇9","篇10"};
        if(content.length() < 35){
            for (String startKey:
                    startKeys) {
                if(content.startsWith(startKey)){
                    puanduan=true;
                }
            }
            for (String endKey:
            endKeys) {
                if(content.endsWith(endKey)){
                    puanduan=true;
                }
            }
        }
        return puanduan;
    }
    //判断标题是否符合条件
    public static Boolean panduanFileName(String fileName) {
        Boolean puanduan = true;
        if (!fileName.endsWith(".docx")) {
            puanduan = false;
        }
        if (fileName.length() < 8) {
            puanduan = false;
        }
        ArrayList titleKeys = getPraBelowKeys(titleKeyPath);
        for (Object key :
                titleKeys) {
            String titleKey = key.toString();
            if (fileName.indexOf(titleKey) != -1) {
                puanduan = false;
                break;
            }
        }
        return puanduan;
    }

    //给段落填充内容
    public static Paragraph fillContent(Section sec, String content) {
        Paragraph para1 = sec.addParagraph();
        TextRange textRange = para1.appendText(content);
        return para1;
    }

    //将第一段作为标题，设置标题格式
    public static void firstParaStyle(Document doc, Paragraph para1) throws InterruptedException {
        Thread.sleep(100);
        String paraStyleName="paraStyle"+System.currentTimeMillis();
        ParagraphStyle style1 = new ParagraphStyle(doc);
        style1.setName(paraStyleName);
        style1.getCharacterFormat().setBold(true);
        style1.getCharacterFormat().setTextColor(Color.RED);
        style1.getCharacterFormat().setFontName("宋体");
        style1.getCharacterFormat().setFontSize(16f);
        doc.getStyles().add(style1);
        para1.applyStyle(paraStyleName);
        //设置第一个段落的对齐方式
        para1.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        para1.getFormat().setAfterSpacing(15f);
    }

    //设置小标题格式
    public static void titleParaStyle(Document doc, Paragraph para1) throws InterruptedException {
        Thread.sleep(100);
        String paraStyleName="paraStyle"+System.currentTimeMillis();
        ParagraphStyle style1 = new ParagraphStyle(doc);
        style1.setName(paraStyleName);
        style1.getCharacterFormat().setBold(true);
        style1.getCharacterFormat().setTextColor(Color.BLUE);
        style1.getCharacterFormat().setFontName("宋体");
        style1.getCharacterFormat().setFontSize(14f);
        doc.getStyles().add(style1);
        para1.applyStyle(paraStyleName);
        para1.getFormat().setAfterSpacing(10f);
        para1.getFormat().setAfterSpacing(15f);
    }
    //设置信件格式
    public static void xinjianParaStyle(Document doc, Paragraph para1) throws InterruptedException {
        Thread.sleep(100);
        String paraStyleName="paraStyle"+System.currentTimeMillis();
        //设置其余两个段落的格式
        ParagraphStyle style2 = new ParagraphStyle(doc);
        style2.setName(paraStyleName);
        style2.getCharacterFormat().setFontName("宋体（正文）");
        style2.getCharacterFormat().setFontSize(14f);
        doc.getStyles().add(style2);
        para1.applyStyle(paraStyleName);
        para1.getFormat().setAfterSpacing(8f);
        para1.getFormat().setAfterSpacing(10f);
    }
    //设置order格式
    public static void orderParaStyle(Document doc, Paragraph para1) throws InterruptedException {
        Thread.sleep(100);
        String paraStyleName="paraStyle"+System.currentTimeMillis();
        //设置其余两个段落的格式
        ParagraphStyle style2 = new ParagraphStyle(doc);
        style2.setName(paraStyleName);
        style2.getCharacterFormat().setFontName("宋体（正文）");
        style2.getCharacterFormat().setFontSize(14f);
        doc.getStyles().add(style2);
        para1.applyStyle(paraStyleName);
        para1.getFormat().setFirstLineIndent(25f);
        para1.getFormat().setAfterSpacing(8f);
        para1.getFormat().setAfterSpacing(10f);
    }

    //设置正文格式
    public static void otherParaStyle(Document doc, Paragraph para1) throws InterruptedException {
        Thread.sleep(100);
        String paraStyleName="paraStyle"+System.currentTimeMillis();
        //设置其余两个段落的格式
        ParagraphStyle style2 = new ParagraphStyle(doc);
        style2.setName(paraStyleName);
        style2.getCharacterFormat().setFontName("宋体（正文）");
        style2.getCharacterFormat().setFontSize(14f);
        doc.getStyles().add(style2);
        para1.applyStyle(paraStyleName);
        para1.getFormat().setFirstLineIndent(25f);
        para1.getFormat().setAfterSpacing(8f);
        para1.getFormat().setAfterSpacing(10f);
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
