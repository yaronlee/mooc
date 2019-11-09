package lambda.cart;

import java.util.ArrayList;
import java.util.List;

;

/**
 * 购物车服务类
 */
public class CartService {
    //加入到购物车的商品信息
    private static List<Sku> cartSkuList = new ArrayList<Sku>(){
        {
            add(new Sku(644032, "无人机",
                    4999.00,1,
                    4999.00, SkuCategoryEnum.ELECTRONICS));
            add(new Sku(642934, "VR一体机",
                2299.00,1,
                2299.00, SkuCategoryEnum.ELECTRONICS));
            add(new Sku(645321, "格子衬衫",
                    409.00,3,
                    1227.00, SkuCategoryEnum.CLOTHING));
            add(new Sku(644327, "牛仔裤",
                    528.00,1,
                    528.00, SkuCategoryEnum.CLOTHING));
            add(new Sku(675489, "跑步机",
                    2699.00,1,
                    2699.00, SkuCategoryEnum.SPORTS));
            add(new Sku(6644564, "Java编程思想",
                    79.80,1,
                    79.80, SkuCategoryEnum.BOOKS));
            add(new Sku(697894, "算法",
                78.20,1,
                78.20, SkuCategoryEnum.BOOKS));
            add(new Sku(697129, "理想国",
                    60.00,1,
                    60.00, SkuCategoryEnum.BOOKS));
            add(new Sku(691994, "论蛋炒饭怎么做才好吃",
                    1550.00,1,
                    1550.00, SkuCategoryEnum.BOOKS));
        }
    };

    /**
     * 获取商品列表信息
     * @return
     */
    public static List<Sku> getCartSkuList(){
        return cartSkuList;
    }

    /**
     * Version 1.0.0
     * 找出购物车中的所有电子产品
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterElectronicSkus(List<Sku> cartSkuList){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku: cartSkuList){
            //如果商品类型等于电子类
            if (SkuCategoryEnum.ELECTRONICS.equals(sku.getSkuCategory())){
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 2.0.0
     * 根据传入商品类型参数，找出购物车中同种商品类型的商品列表
     * @param cartSkuList 所有商品列表
     * @param category 传入的商品类型
     * @return
     */
    public static List<Sku> filterSkusByCategory(
            List<Sku> cartSkuList, SkuCategoryEnum category) {
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku: cartSkuList){
            //如果商品类型等于传入的商品类型
            if (category.equals(sku.getSkuCategory())){
                result.add(sku);
            }
        }
        return result;
    }

    /**
     *  Version 3.0.0
     *  支持通过商品类型或总价来过滤商品
     * @param cartSkuList
     * @param category
     * @param totalPrice
     * @param categoryOrPrice -true: 根据商品类型，false：根据商品总价
     * @return
     */
    public static List<Sku> filterSkus(List<Sku> cartSkuList, SkuCategoryEnum category,
                                       Double totalPrice, Boolean categoryOrPrice){
        //都是在原有的基础上改
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku: cartSkuList){
            // -true 如果商品类型等于传入的商品类型
            // -false 总价大于传入的价格
            if ( categoryOrPrice && category.equals(sku.getSkuCategory())
                || (!categoryOrPrice && sku.getTotalPrice() > totalPrice)){
                result.add(sku);
            }
        }
        return result;
    }

    //类似于策略模式

    /**
     * Version 4.0.0
     * 根据不同的Sku判断标准，对Sku列表进行过滤
     * @param cartSkuList
     * @param predicate - 不同的Sku判断逻辑，或称判断策略
     * @return
     */
    public static List<Sku> filterSkus(List<Sku> cartSkuList, SkuPredicate predicate){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku: cartSkuList){
            //根据不同的Sku判断策略，对sku进行判断
            if (predicate.test(sku)){
                result.add(sku);
            }
        }
        return result;
    }
}
