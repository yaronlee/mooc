package guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *  Lists / Sets使用
 */
public class SetsTest {

    /**
     *  Sets工具类的常用方法：
     *  并集、交集、差集、分解集合中的所有子集、求两个集合的笛卡尔积
     *
     *  Lists工具类的常用方法：
     *  反转、拆分
     */

    //Sets工具类，newHashSet无参可以创建空集合，有参可以创建各种形式的集合
    private static final Set set1 =
            Sets.newHashSet(1, 2, 3);
    private static final Set set2 =
            Sets.newHashSet(4, 5, 6, 3, 2);

    /********************************* Sets工具类 *******************************/
    @Test
    public void union(){    //并集，集合合并比较常用
        Set<Integer> set = Sets.union(set1, set2);
        System.out.println(set);
    }

    @Test
    public void intersection(){    //交集
        Set<Integer> set = Sets.intersection(set1, set2);
        System.out.println(set);
    }

    @Test
    public void difference(){    //差集：如果元素属于A，而且不属于B
        Set<Integer> set = Sets.difference(set1, set2);
        System.out.println(set);

        //相对差集：属于A，而且不属于B；[或者]属于B，而且不属于A
        System.out.println(Sets.symmetricDifference(set1, set2));
    }

    @Test
    public void powerSet(){    //将结合拆解很多不同的子集
        Set<Integer> set = Sets.powerSet(set1);
        System.out.println(JSON.toJSONString(set, true));
    }

    @Test
    public void cartesianProduct(){    //笛卡尔积：即set1中的元素与set2中的元素的所有可能的组合
        Set<List<Integer>> set = Sets.cartesianProduct(set1, set2);    //返回Set<List>
        System.out.println(JSON.toJSONString(set));
    }

    /********************************* Lists工具类 ************************************* */

    @Test
     public void partition(){
         ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);

         List<List<Integer>> partition =
                 Lists.partition(list, 3); //返回一个List<List>，拆分条件为3

         System.out.println(JSON.toJSONString(partition));
     }

    @Test
    public void reverse(){    //反转
        List<Integer> list = Lists.newLinkedList();    //有序的List
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> reverse = Lists.reverse(list);//返回一个List<List>，拆分条件为3

        System.out.println(JSON.toJSONString(reverse));
    }

}
