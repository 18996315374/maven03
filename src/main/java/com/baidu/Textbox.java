package com.baidu;

import com.spire.doc.*;
import com.spire.doc.Document;
import com.spire.doc.documents.*;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextBox;
import com.spire.doc.fields.TextRange;
//https://www.e-iceblue.cn/Introduce/Free-Spire-Doc-JAVA.html
import java.awt.*;

public class Textbox {
    public static void main(String[]args){
        //创建文档
        Document doc = new Document();

        //添加指定大小的文本框
        TextBox tb = doc.addSection().addParagraph().appendTextBox(380, 275);
        //设置文字环绕方式
        tb.getFormat().setTextWrappingStyle(TextWrappingStyle.Square);

        //设置文本框的相对位置
        tb.getFormat().setHorizontalOrigin(HorizontalOrigin.Left_Margin_Area);
        tb.getFormat().setHorizontalPosition(120f);
        tb.getFormat().setVerticalOrigin(VerticalOrigin.Page);
        tb.getFormat().setVerticalPosition(100f);

        //设置文本框边框样式
        tb.getFormat().setLineStyle(TextBoxLineStyle.Thin_Thick);
        tb.getFormat().setLineColor(Color.gray);

        //插入图片到文本框
        Paragraph para = tb.getBody().addParagraph();
        DocPicture picture = para.appendPicture("E:\\验证码图片\\85a0c6165b162e385bd9de8fb12712a2.jpeg");
        picture.setHeight(120f);
        picture.setWidth(180f);
        para.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        para.getFormat().setAfterSpacing(13f);

        //插入文字到文本框
        para = tb.getBody().addParagraph();
        TextRange textRange = para.appendText("中美贸易争端，又称中美贸易战，也叫中美贸易摩擦，是中美经济关系中的重要问题。 "
                + "贸易争端主要发生在两个方面：一是中国具有比较优势的出口领域；"
                + "二是中国没有优势的进口和技术知识领域。中美贸易争端，又称中美贸易战，也叫中美贸易摩擦，是中美经济关系中的重要问题。 " +
                "贸易争端主要发生在两个方面：一是中国具有比较优势的出口领域；二是中国没有优势的进口和技术知识领域");
        textRange.getCharacterFormat().setFontName("楷体");
        textRange.getCharacterFormat().setFontSize(11f);
        para.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        Paragraph para1 = tb.getBody().addParagraph();
        TextRange textRange1 = para1.appendText("中美贸易争端，又称中美贸易战，也叫中美贸易摩擦，是中美经济关系中的重要问题。 "
                + "贸易争端主要发生在两个方面：一是中国具有比较优势的出口领域；"
                + "二是中国没有优势的进口和技术知识领域。中美贸易争端，又称中美贸易战，也叫中美贸易摩擦，是中美经济关系中的重要问题。 " +
                "贸易争端主要发生在两个方面：一是中国具有比较优势的出口领域；二是中国没有优势的进口和技术知识领域");

        //添加表格到文本框
        //声明数组内容
        String[][] data = new String[][]{
                new String[]{"中美进出口差额"},
                new String[]{"国家", "年份", "出口额（美元）", "进口额（美元）"},
                new String[]{"中国", "2017", "125468", "101109"},
                new String[]{"美国", "2017", "86452", "124298"},
        };
        //添加表格
        Table table = tb.getBody().addTable();
        //指定表格行数、列数
        table.resetCells(4,4);
        //将数组内容填充到表格
        for (int i = 0; i < data.length; i++) {
            TableRow dataRow = table.getRows().get(i);
            dataRow.getCells().get(i).setWidth(70);
            dataRow.setHeight(22);
            dataRow.setHeightType(TableRowHeightType.Exactly);
            for (int j = 0; j < data[i].length; j++) {
                dataRow.getCells().get(j).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                TextRange range2 = dataRow.getCells().get(j).addParagraph().appendText(data[i][j]);
                range2.getCharacterFormat().setFontName("楷体");
                range2.getCharacterFormat().setFontSize(11f);
                range2.getOwnerParagraph().getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
                range2.getCharacterFormat().setBold(true);
            }
        }
        TableRow row = table.getRows().get(1);
        for (int z = 0; z < row.getCells().getCount(); z++) {
            row.getCells().get(z).getCellFormat().setBackColor(new Color(176,224,238));
        }
        //横向合并单元格
        table.applyHorizontalMerge(0,0,3);
        //应用表格样式
        table.applyStyle(DefaultTableStyle.Table_Grid_5);
        //保存文档
        doc.saveToFile("E:\\文档\\自查报告网\\hello.docx", FileFormat.Docx_2013);
        doc.dispose();
    }
}