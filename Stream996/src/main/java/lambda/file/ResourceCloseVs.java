package lambda.file;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  资源关闭优化前与优化后代码量对比
 */
public class ResourceCloseVs {

    @Test
    public void newFileHandle(String url,
                              FileConsumer fileConsumer){

    }

    /**
     * 与FileService中的方法相同，并关闭了流，但是代码量比较多
     * @param url
     * @param fileConsumer
     */
    @Test
    public void oldFileHandle(String url,
                              FileConsumer fileConsumer){
        //声明
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        //创建文件读取流，读取本地文件（IO流就是一层套一层..）
        try{
            fileInputStream = new FileInputStream(url);

            inputStreamReader =
                    new InputStreamReader(fileInputStream);
            bufferedReader =
                    new BufferedReader(inputStreamReader);

            //定义 行变量（存储每一行），和 内容的StringBuilder（存储整个文件）
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            //循环读取文件内容
            //从bufferedReader中读取一行，看是否为空
            while ((line = bufferedReader.readLine()) != null){
                //不为空就加到stringBuilder中
                stringBuilder.append(line + "\n");
            }

            //调用函数式接口方法，将文件内容（一个字符串）传递给Lambda表达式，实现业务逻辑
            fileConsumer.fileHandler(stringBuilder.toString());
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            //关闭流资源，但是超级麻烦

            //判断reader是否为空，不为空就尝试关闭，还有catch可能的IO异常
            //代码量增长了很多
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
