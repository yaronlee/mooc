package resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  基于JDK7之后，实现正确关闭流资源的方法
 *  try - with - resource
 */
public class NewFileCopyTest {

    @Test
    public void copyFile(){
        //先定义输入、输出路径
        String originalUrl = "lib/FileCopyTest.java";
        String targetUrl = "targetTest/targetNew.txt";

        //初始化输入、输出流对象
        try(    //不再需要显式地关闭流
                FileInputStream originalFileInputStream =
                        new FileInputStream(originalUrl);

                FileOutputStream targetFileOutputStream =
                        new FileOutputStream(targetUrl);
                ) {
            int content;
            while ((content = originalFileInputStream.read()) != -1){
                targetFileOutputStream.write(content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
