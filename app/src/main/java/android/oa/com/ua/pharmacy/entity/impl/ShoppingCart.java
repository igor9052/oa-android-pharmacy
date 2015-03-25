package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Igor Kuzmenko on 25.03.2015.
 */
public class ShoppingCart extends Observable {

    public static final String TAG = ShoppingCart.class.getSimpleName();

    private static final int UNIQUE_PRODUCT_CODE = -1;
    private static ShoppingCart shoppingCart = null;
    private List<ShoppingCartEntity> productList;

    private ShoppingCart() {
        productList = new ArrayList<>();
    }

    public static ShoppingCart getInstance() {
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }

    public List<ShoppingCartEntity> getShoppingCartEntities() {
        return productList;
    }

    public void addProductToCart(IMedicineProduct product) {
        int index = isProductAlreadyAdded(product);
        if (index == UNIQUE_PRODUCT_CODE) {
            productList.add(new ShoppingCartEntity(product));
        } else {
            int amount = productList.get(index).getAmount();
            productList.get(index).setAmount(amount + 1);
        }
        Log.i(TAG, "Add New Product");
        setChanged();
        notifyObservers();
    }

    public void removeProductFromCart(IMedicineProduct product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProduct().equals(product)) {
                productList.remove(i);
            }
        }
        setChanged();
        notifyObservers();
    }

    public void removeOneProductInstanceFromCart(IMedicineProduct product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProduct().equals(product)) {
                if (productList.get(i).getAmount() == 1) {
                    productList.remove(i);
                } else {
                    int amount = productList.get(i).getAmount();
                    productList.get(i).setAmount(amount - 1);
                }
            }
        }
        setChanged();
        notifyObservers();
    }

    public int getTotalAmountOfProducts() {
        int result = 0;
        for (ShoppingCartEntity item : productList) {
            result += item.getAmount();
        }
        Log.i(TAG, String.valueOf(result));
        return result;
    }

    private int isProductAlreadyAdded(IMedicineProduct product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProduct().equals(product)) {
                return i;
            }
        }
        Log.i(TAG, product.toString());
        return UNIQUE_PRODUCT_CODE;
    }

    public static class ShoppingCartEntity {
        private IMedicineProduct product;
        private Integer amount;

        private ShoppingCartEntity(IMedicineProduct product) {
            this.product = product;
            this.amount = 1;
        }

        public IMedicineProduct getProduct() {
            return product;
        }

        public Integer getAmount() {
            return amount;
        }

        private void setAmount(Integer amount) {
            this.amount = amount;
        }
    }
}
