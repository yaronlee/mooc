package lambda.stream;


import com.alibaba.fastjson.JSON;
import lambda.cart.CartService;
import lambda.cart.Sku;
import lambda.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class StreamOperator {

    List<Sku> list;

    @Before
    public void init(){
        //先初始化一个购物车列表
        list = CartService.getCartSkuList();
    }

    /**
     * ****************************************常用的中间操作*************************
     */

    /**
     *  filter使用：过滤掉不符合断言判断的数据
     */
    @Test
    public void filterTest(){
        list.stream()
                //过滤，点击去看发现参数需要一个Predicate函数式接口；
                // 再点开Predicate，发现里面有一个test方法
                .filter(sku ->
                        SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                //终端操作
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    /**
     *  map使用：将一个元素转换成另一个元素
     */
    @Test
    public void mapTest(){
        list.stream()
                //点进去看发现需要传入一个Function函数接口；
                //点开Function发现apply方法是将一个T型参数转换为R型参数
                //这里将Sku对象，转换为一个字符串对象
                .map(sku -> sku.getSkuName())
                //终端操作
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));

    }

    /**
     *  flatmap使用：将一个对象转换成流
     */
    @Test
    public void flatMapTest(){
        list.stream()
                //扁平化map，点进去发现接受一个元素，返回一个新的流
                //这里是将商品名称进行切分，返回一个字符流
                .flatMap(sku -> Arrays.stream(
                        sku.getSkuName().split("")))
                //终端操作
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));

    }

    /**
     *  peek使用：对流中元素进行遍历操作，与forEach类似，但不会销毁流元素
     */
    @Test
    public void peekTest(){
        list.stream()
                //peek与forEach类似，都是查看每一个元素
                //区别是peek是中间操作，查看完流还可用；forEach是终端操作，查看完之后流就不可用了
                //接受一个Consumer
                .peek(sku -> System.out.println(sku.getSkuName()))
                //终端操作
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
        //注意 peek和forEach是交替执行的；流是惰性的。。TODO://没太懂，有状态，无状态？还是和终端操作有关系？


    }

    /**
     *  sort使用：对流中元素进行排序，可选择自然排序或者指定排序规则
     *  有状态操作
     */
    @Test
    public void sortTest(){
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                //无参就是自然排序
                //有参可以接受一个Comparator
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                //终端操作
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
        //注意与上面的区别，添加了sorted操作之后（有状态操作），peek就是先遍历forEach是后遍历了
    }

    /**
     *  distinct使用：对流元素进行去重
     *  有状态操作
     */
    @Test
    public void distinctTest(){
        list.stream()
                //先使用map将sku转换为类别字符串，再进行去重操作
                .map(sku -> sku.getSkuCategory())
                .distinct()
                //终端操作
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    /**
     *  skip使用：跳过前N条记录。
     *  有状态操作
     */
    @Test
    public void skipTest(){
        list.stream()
                //先根据总价进行排序，默认从小到大
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                //再跳过前三条数据
                .skip(3)
                //终端操作
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    /**
     *  limit使用：截断前N条记录
     *  有状态操作
     */
    @Test
    public void limitTest(){
        list.stream()
                //先根据总价进行排序，默认从小到大
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                //skip是跳过前三条数据，limit是只取前三条

                //使用skip和limit可以实现【分页功能】
                //比如如果是第一页，跳过0条数据，显示3条数据；
                // 如果是第二页，就是跳过1 * 3条数据，显示3条数据
                .skip(0 * 3)
                .limit(3)
                //终端操作
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }


    /**
     * ****************************************常用的终端操作*************************
     */


    /**
     *  allMatch使用：(接受一个Predicate参数）测试是否所有元素都满足断言，如果有一个不满足就返回false
     *  终端操作，短路操作，找到第一个不满足的就直接返回false，可以使用peek验证
     */
    @Test
    public void allMatchTest(){
        boolean match =  list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                //是否所有商品总价都大于100
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }
    /**
     *  anyMatch使用：(接受一个Predicate参数）只要有一个满足就返回true
     *  终端操作，短路操作，找到第一个满足的就直接返回，可以使用peek验证
     */
    @Test
    public void anyMatchTest(){
        boolean match =  list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                //是否存在总价大于100的商品
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    /**
     *  noneMatch使用：(接受一个Predicate参数）所有元素都不满足才返回true
     *  终端操作，非短路操作
     */
    @Test
    public void noneMatchTest(){
        boolean match =  list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                //是否总价都不大于10_000
                .noneMatch(sku -> sku.getTotalPrice() > 10_000);
        System.out.println(match);
    }

    /**
     *  findFirst：获取第一个元素
     *  终端操作，短路操作，
     */
    @Test
    public void findFirstTest(){
        //返回一个optional
        Optional<Sku> optional =  list.stream()
                .findFirst();
        System.out.println(
                JSON.toJSONString(
                        optional.get(),true));
    }

    /**
     *  findAny：获取任何一个
     *  findAny与findFirst主要区别在串行和并行上面，如果是并行findAny要快一点，但返回的值可能不一致
     *  终端操作，短路操作
     */
    @Test
    public void findAnyTest(){
        //返回一个optional, since 1.8
        Optional<Sku> optional =  list.stream()
                .findAny();

        System.out.println(
                JSON.toJSONString(
                        optional.get(),true));
    }

    /**
     *  max：获取最大值
     *  终端操作，非短路操作
     */
    @Test
    public void maxTest(){
        //max返回一个OptionalDouble方法
        OptionalDouble max = list.stream()
                //先将总价数据提取出来，调用mapToDouble方法
                //mapToDouble就是将一个元素映射为Double元素
                .mapToDouble(Sku::getTotalPrice)
                .max();

        System.out.println(max.getAsDouble());
    }

    /**
     *  min：与max类似
     *  终端操作，非短路操作
     */
    @Test
    public void minTest(){
        //min返回一个OptionalDouble方法
        OptionalDouble min = list.stream()
                //先将总价数据提取出来，调用mapToDouble方法
                //mapToDouble就是将一个元素映射为Double元素
                .mapToDouble(Sku::getTotalPrice)
                .min();

        System.out.println(min.getAsDouble());
    }

    /**
     *  count：个数
     *  终端操作，非短路操作
     */
    @Test
    public void countTest(){
        //max返回一个OptionalDouble方法
        Long count = list.stream()
                .count();
        System.out.println(count);
    }
}
