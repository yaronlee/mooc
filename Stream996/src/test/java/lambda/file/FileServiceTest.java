package lambda.file;

import org.junit.Test;

import java.io.IOException;

public class FileServiceTest {

    @Test
    public void fileHandler() throws IOException {

        FileService fileService = new FileService();

        //url参数右键java文件copy path就可以了；第二个参数是一个函数式接口，可以写成lambda表达式
        //通过lambda表达式，打印文件内容
        fileService.fileHandle("D:\\ideaProject\\mooc\\Stream996\\src\\test\\java" +
                        "\\lambda\\file\\FileServiceTest.java",
                fileContent -> {
                    System.out.println(fileContent);//先打印出来
                    System.out.println("文本长度为：" + fileContent.length());
                    //当然还可以进行更复杂的操作，比如统计词频、检查错词什么的
                });
    }
}
