package lambda.file;

import org.junit.Test;

import java.io.IOException;

public class FileServiceTest {

    @Test
    public void fileHandler() throws IOException {

        FileService fileService = new FileService();

        //url参数右键java文件copy path就可以了；第二个参数是一个函数式接口，可以写成lambda表达式
        //通过lambda表达式，打印文件内容
        fileService.fileHandle("D:\\ideaProject\\mooc\\Stream996\\src" +
                        "\\main\\java\\lambda\\file\\FileConsumer.java",
                fileContent -> {
                    System.out.println(fileContent);    //先打印出来
                });
    }
}
