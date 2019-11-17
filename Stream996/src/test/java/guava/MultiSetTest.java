package guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Chars;
import org.junit.Test;

/**
 *  实现：使用MultiSet统计一首古诗的文字出现频率
 */
public class MultiSetTest {

    private static final String text = "南陵别儿童入京\n" +
            "唐代：李白\n" +
            "\n" +
            "白酒新熟山中归，黄鸡啄黍秋正肥。\n" +
            "呼童烹鸡酌白酒，儿女嬉笑牵人衣。\n" +
            "高歌取醉欲自慰，起舞落日争光辉。\n" +
            "游说万乘苦不早，著鞭跨马涉远道。\n" +
            "会稽愚妇轻买臣，余亦辞家西入秦。\n" +
            "仰天大笑出门去，我辈岂是蓬蒿人。";

    @Test
    public void handle(){
        //multiset创建
        Multiset<Character> multiSet = HashMultiset.create();

        //string转换为 char数组
        char[] chars = text.toCharArray();

        //遍历数组，添加到multiset中
        Chars.asList(chars)
                .stream()
                .forEach(charItem -> {    //charItem为每一个字符
                    multiSet.add(charItem);
                });

        System.out.println
                ("size: " + multiSet.size());   //multiset共有多个个字符
        System.out.println
                ("count: " + multiSet.count('人'));   //“人”出现的频率，注意是单引号

    }
}
