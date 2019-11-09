package lambda.cart;

/**
 * Sku选择谓词接口
 */
public interface SkuPredicate {
    /**
     * 选择标准判断
     * @param sku
     * @return
     */
    boolean test(Sku sku);
}
