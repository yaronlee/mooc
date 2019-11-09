package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class Version4Test {

    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();

        //过滤商品大于2000的商品
        List<Sku> skus = CartService.filterSkus(cartSkuList, new SkuTotalPricePredicate());
        //过滤图书商品
        List<Sku> skus2 = CartService.filterSkus(cartSkuList, new SkuBooksCategoryPredicate());

        System.out.println(JSON.toJSONString(skus, true));
        System.out.println(JSON.toJSONString(skus2, true));
    }

}
