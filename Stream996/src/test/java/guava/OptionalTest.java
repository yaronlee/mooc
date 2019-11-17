package guava;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *  学习Java8中的Optional使用方法
 */
public class OptionalTest {

    @Test
    public void test(){
        /**
         * 提供了三种创建Optional对象方式
         */

        //创建空的Optional对象
        Optional<Object> empty = Optional.empty();

        //of方法，使用非null值创建Optional对象
        Optional.of("not null");
        //Optional.of(null);

        //使用任意值创建Optional对象
        Optional<Object> optional = Optional.ofNullable(null);

        /**
         *  判断是否引用缺失的方法（建议不直接使用，这种判空方式和以前没有区别）
         */
        optional.isPresent();  //点进去看，发现就是一个简单的判断是否为空的方法

        /**
         * 当optional引用存在时执行
         * 类似的方法：map filter flatMap
         */
        Optional<Object> optional2 = Optional.ofNullable("test ifPresent");
        //传递一个Consumer实例，比如可传入一个方法引用，如果值不为null，就调用方法；否则就什么都不做
        optional.ifPresent(System.out::println);
        optional2.ifPresent(System.out::println);

        /**
         * 当optional引用缺失时执行的方法
         */
        optional.orElse("引用缺失");    //当optional为空就赋值“引用缺失”
        optional.orElseGet(() -> {
            //当optional为空时返回自定义引用缺失值
            return "自定义引用缺失";
        });

        //当optional引用缺失时，可以抛出一个异常。
        try{
            optional.orElseThrow(() -> {
                throw new RuntimeException("引用缺失");
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

    public static void streamTest(List<String> list) {
        //思考如果stream的值为null会怎么样？？——空指针异常
        //list.stream().forEach(System.out::println);

        //使用optional解决stream为null的异常问题
        Optional.ofNullable(list)
                //传入的list不为空，就之间生成一个流
                .map(List::stream)
                //orElse如果传入为空，就使用Stream的empty()方法创建一个空的流
                .orElseGet(Stream::empty)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        streamTest(null);
    }

}
