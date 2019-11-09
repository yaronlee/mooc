package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;


public class Version1Test {

    @Test
    public void filterElectronicSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        //购物车中数码类商品
        List<Sku> result = CartService.filterElectronicSkus(cartSkuList);

        //如果设置为true，以json的格式输出，否则以字符串输出
        System.out.println(
                JSON.toJSONString(result, true));
    }
}
