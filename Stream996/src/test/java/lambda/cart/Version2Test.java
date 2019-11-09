package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class Version2Test {

    @Test
    public void filterSkusByCategory() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        //查找购物者中读书类的集合
        List<Sku> skus = CartService.filterSkusByCategory(cartSkuList, SkuCategoryEnum.BOOKS);

        System.out.println(JSON.toJSONString(skus, true));
    }
}
