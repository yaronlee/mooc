package lambda.file;

import java.io.*;

/**
 * 文件服务类
 */
public class FileService {

    //第一个参数是url，第二个参数是函数式接口

    /**
     * 通过url获取本地文件内容，调用函数式接口处理
     * @param url
     * @param fileConsumer 自定义的函数式接口
     */
    public void fileHandle(String url, FileConsumer fileConsumer) throws IOException {
        //创建文件读取流，读取本地文件（IO流就是一层套一层..）
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(url)));

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
    }

}
