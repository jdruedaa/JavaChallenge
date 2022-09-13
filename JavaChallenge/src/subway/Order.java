package subway;

import products.Product;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private double totalPrice;
    private final ArrayList<Product> products;

    public Order()
    {
        totalPrice = 0;
        products = new ArrayList<>();
    }

    public List<Product> getProducts()
    {
        return products;
    }

    public void addProduct(Product product)
    {
        products.add(product);
        increaseTotalPrice(product.getPrice());
    }

    private void increaseTotalPrice(double amount)
    {
        totalPrice += amount;
    }

    public String generateReceipt()
    {
        return "subway.Order{" +
                "totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}
