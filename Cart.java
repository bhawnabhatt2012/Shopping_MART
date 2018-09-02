package shoppingMart;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Integer> productID = new ArrayList<>();

    public ArrayList<Integer> getList() {
        return productID;
    }

    public void addProductID(int productID) {
        this.productID.add(productID);
    }

    public void removeProductID(int productID) {
        this.productID.remove(productID);
    }
}
