package lambda.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的四种构建形式
 */
public class StreamConstructor {

    /**
     *  由数值直接构建流
     */
    @Test
    public void streamFromValue(){
        //of方法接受任意多个T类型的参数   T... values
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        integerStream.forEach(System.out::println);
    }

    /**
     *  通过数组构建流
     */
    @Test
    public void streamFromArray(){
        int[] numbers = {1, 2, 3, 4, 5};
        //Arrays数组类型有个stream方法
        IntStream stream = Arrays.stream(numbers);
        stream.forEach(System.out::println);
    }

    /**
     *  通过文件构建流
     */
    @Test
    public void streamFromFile() throws IOException {
        //Files文件类有很多静态方法都会返回一个流
        //lines接受一个路径，返回一个由指定文件中的各行构成的字符串流
        Stream<String> lines = Files.lines(Paths.get("D:\\ideaProject" +
                "\\mooc\\Stream996\\src\\main\\java\\lambda\\stream\\StreamOperator.java"));
        lines.forEach(System.out::println);
    }

    /**
     *  通过函数构建流
     */
    @Test
    public void streamFromFunction(){
        //两种方法 iterate，赋一个初始值，第二个参数是一个函数，我们可以创建一个无限的流
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 2);

        //没有初始值
        Stream<Double> generate = Stream.generate(Math::random);

        iterate.limit(100).forEach(System.out::println);
        generate.limit(100).forEach(System.out::println);
    }

}
