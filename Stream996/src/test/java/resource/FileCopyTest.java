package resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  JDK7之前的文件拷贝功能
 */
public class FileCopyTest {

    @Test
    public void copyFile() {
        /**
         * 1. 创建输入、输出流
         * 2. 执行文件拷贝，读取文件内容，写入到另一个文件中
         * 3. **关闭文件流资源**
         */
        //定义输入路径，和输出路径
        //将lib文件夹下的文件copy到targetTest文件夹下
        String originalUrl = "lib/FileCopyTest.java";
        String targetUrl = "targetTest/target.txt";

        //声明文件输入流，文件输出流
        FileInputStream originalFileInputStream = null;
        FileOutputStream targetFileOutputStream = null;

        try {
            // 实例化文件流对象
            originalFileInputStream =
                    new FileInputStream(originalUrl);
            targetFileOutputStream =
                    new FileOutputStream(targetUrl);

            //读取的字节信息
            int content;  //为什么是int类型？？来接受读取的字节？？
            //迭代，读取/写入字节
            while((content = originalFileInputStream.read()) != -1){
                targetFileOutputStream.write(content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {    //关闭流资源
            //遵循先打开后关闭的原则
            if (targetFileOutputStream != null){
                try {
                    //关闭流的时候还要捕获异常，导致了代码量的激增
                    targetFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (originalFileInputStream != null){
                try {
                    originalFileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
