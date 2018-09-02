package shoppingMart;

public class Product {

    private int price;
    private String description;
    private String itemName;
    private int id;
    private int count;

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getItemName() {
        return itemName;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
