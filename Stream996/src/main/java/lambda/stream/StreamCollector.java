package lambda.stream;

import com.alibaba.fastjson.JSON;
import lambda.cart.CartService;
import lambda.cart.Sku;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  常见的预定义收集器使用
 */
public class StreamCollector {

    @Test
    public void toList(){
        List<Sku> list = CartService.getCartSkuList();
        List<Sku> collect = list.stream()
                //过滤总价大于100的商品
                .filter(sku -> sku.getTotalPrice() > 100)
                //collect方法接受一个Collector接口的实现类
                .collect(Collectors.toList());

        System.out.println(
                JSON.toJSONString(collect, true));
    }

    @Test
    public void group(){
        List<Sku> list = CartService.getCartSkuList();
        //根据商品的类别进行分组
        Map<Enum, List<Sku>> group = list.stream()
                //groupingBy接受一个Function类型的对象
                //返回一个Map，key就是条件，value是相应条件下元素的集合
                .collect(Collectors.groupingBy(Sku::getSkuCategory));

        System.out.println(
                JSON.toJSONString(group, true));
    }

    /**
     * 分区，是分组的特殊情况
     * 由一个谓词作为分区的函数，返回一个布尔值，将数据分为两组，布尔值为true时和为false时
     */
    @Test
    public void partition(){
        List<Sku> list = CartService.getCartSkuList();

        Map<Boolean, List<Sku>> collect = list.stream()
                //注意map的key为布尔值类型
                .collect(Collectors.partitioningBy(
                        sku -> sku.getTotalPrice() > 100));

        System.out.println(
                JSON.toJSONString(collect, true));
    }

    //更多的功能可以去Collectors实现类去自己看
}
