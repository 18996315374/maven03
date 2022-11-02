public class AllModifyDocx2 {
    public static void main(String[] args) throws InterruptedException {
        //爬取下来的word文档存放的文件夹
        String oldFolderPath = "D:\\文档\\百度活动文档2\\百度html\\其他网站";
        //中介文件夹
        String interFolderPath = "D:\\文档\\百度活动文档2\\中介";
        //最终存放word的文件夹
        String newFolderPath="D:\\文档\\百度活动文档2\\修改后百度活动文档";
        //存放有问题的word文件的文件夹
        String failurePath="D:\\文档\\百度活动文档2\\有问题文档";
        //修改，删除，替换文档内容
        Modify.setFileModify(oldFolderPath, interFolderPath);
        //修改文档格式
        ModifyDocument.setFileStyle(interFolderPath,newFolderPath,failurePath);
    }
}
