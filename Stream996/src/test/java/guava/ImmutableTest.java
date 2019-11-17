package guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  学习不可变集合的用法
 */
public class ImmutableTest {

    public static void test(List<Integer> list){
        list.remove(0);//小方法，移除list第一个元素
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);

        //list引用传递，将list传递到方法中，方法对list操作也会对外面的list产生影响
        //即list是可变的
        //引用传递有风险，因为你不知道方法会对你的集合做出什么样的操作

        List<Integer> newList =
                Collections.unmodifiableList(list);

        test(list);
        test(newList);    //抛出异常

        System.out.println(list);
    }

    @Test
    public void immutable(){
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);

        /**
         *  构造不可变集合对象的三种方式
         */
        ImmutableSet<Integer> immutableSet = ImmutableSet.copyOf(list);

        immutableSet.remove(0);

        //通过初始值，直接创建不可变集合
        ImmutableSet<Integer> of =
                ImmutableSet.of(1, 2, 3);

        // 以builder方式创建
        ImmutableSet.builder()
                .add(1)
                .addAll(Sets.newHashSet(2,3))
                .add(4)
                .build();

        //不可变集合可以直接使用流等方法
        immutableSet.
                stream().
                forEach(System.out::println);
    }

}
