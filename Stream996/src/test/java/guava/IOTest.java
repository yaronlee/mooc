package guava;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 *  演示如何使用源（Source）与汇（Sink）来对文件进行操作
 */

public class IOTest {

    @Test
    public void copyFile() throws IOException {
        /**
         *  1，创建对应的Source和Sink
         */
        //需要两个参数，一个是File对象，一个是编码集（这里用了guava预定义的UTF_8编码集）
        //创建源
        String sourcePath = "D:\\ideaProject\\mooc\\Stream996\\lib\\FileCopyTest.java";
        String sinkPath = "D:\\ideaProject\\mooc\\Stream996\\targetTest\\guavaIOTest.txt";

        CharSource charSource = Files.asCharSource(
                new File(sourcePath), Charsets.UTF_8);
        //创建汇
        CharSink charSink = Files.asCharSink(
                new File(sinkPath), Charsets.UTF_8);

        /**
         *  2，拷贝，从源copy到汇就可以了，不需要手动关闭流
         */
        charSource.copyTo(charSink);

    }

}
