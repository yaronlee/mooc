package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class Version6Test {

    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();

        //过滤商品大于2000的商品
        List<Sku> skus = CartService.filterSkus(cartSkuList,
                (Sku sku) -> sku.getSkuPrice() > 1000);

        System.out.println(JSON.toJSONString(skus, true));
    }

}
