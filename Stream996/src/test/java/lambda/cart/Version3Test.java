package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class Version3Test {
    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        //查找购物者中读书类的集合
        List<Sku> skus = CartService.filterSkus(cartSkuList, SkuCategoryEnum.BOOKS,null, true);
        //根据商品总价过滤超过2000元的列表
        List<Sku> skus2 = CartService.filterSkus(cartSkuList, null,2000.00, false);

        System.out.println(JSON.toJSONString(skus, true));
        System.out.println(JSON.toJSONString(skus2, true));
    }
}
