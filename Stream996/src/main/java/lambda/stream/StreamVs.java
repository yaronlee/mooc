package lambda.stream;

import com.alibaba.fastjson.JSON;
import lambda.cart.CartService;
import lambda.cart.Sku;
import lambda.cart.SkuCategoryEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 *  对比：原始集合操作与Stream集合操作
 */


public class StreamVs {
    /**
     *  购物车案例要求
     *  1，先看看购物车中都有什么东西
     *  2，图书类商品都给买（提升作用）
     *  3，其余的商品买两件最贵的
     *  4，最后只要这两件商品名字和总价
     */

    /**
     *  以原始集合操作实现需求
     */
    @Test
    public void oldCartHandle(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        /**
         *  1,打印所有商品
         */
        for (Sku sku: cartSkuList) {
            System.out.println(JSON.toJSONString(sku, true));
        }

        /**
         *  2，图书类过滤掉
         */
        List<Sku> noBooksSkuList = new ArrayList<Sku>();
        for (Sku sku: cartSkuList) {
            if (!sku.getSkuCategory().equals(SkuCategoryEnum.BOOKS)){
                noBooksSkuList.add(sku);
            }
        }

        /**
         *  3, 非图书类排序，选top2
         */
        noBooksSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku sku1, Sku sku2) {
                if (sku1.getTotalPrice() > sku2.getTotalPrice()){
                    return -1;
                } else if (sku1.getTotalPrice() < sku2.getTotalPrice()){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        List<Sku> top2SkuList = new ArrayList<Sku>();
        //已排好序，只需要把非图书商品的前两位放到列表中就行了
        for (int i = 0; i < 2; i++) {
            top2SkuList.add(noBooksSkuList.get(i));
        }

        /**
         *  4 求两件商品的总价
         */
        Double money = 0.0;
        for (Sku sku: top2SkuList){
            money += sku.getTotalPrice();
        }

        /**
         *  获取两件商品的名称
         */
        List<String> resultSkuNameList = new ArrayList<String>();
        for (Sku sku: top2SkuList) {
            resultSkuNameList.add(sku.getSkuName());
        }

        /**
         *  打印输出结果
         */
        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价" + money);
    }

    /**
     *  以Stream流的方式实现需求
     */
    @Test
    public void newCartHandle(){
        //原子引用类，线程安全
        AtomicReference<Double> money = new AtomicReference<>(Double.valueOf(0.0));

        //调用stream()方法，建立一个流
        List<String> resultSkuNameList = CartService.getCartSkuList().stream()
                /**
                 *  1,打印所有商品
                 */
                .peek(sku -> System.out.println(JSON.toJSONString(sku, true)))
                /**
                 *  2，图书类过滤掉
                 */
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                /**
                 *  3, 非图书类排序
                 */
                //默认排序是从小到大，使用reversed就是从大到小
                .sorted(Comparator
                        .comparing(Sku::getTotalPrice).reversed())
                /**
                 * 选top2
                 */
                .limit(2)
                /**
                 *  4 求两件商品的总价
                 */
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                /**
                 *  获取两件商品的名称
                 */
                .map(sku -> sku.getSkuName())
                /**
                 *  定义resultSkuNameList收集结果
                 */
                .collect(Collectors.toList());

        /**
         *  打印输出结果
         */
        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价" + money.get());


    }

}
