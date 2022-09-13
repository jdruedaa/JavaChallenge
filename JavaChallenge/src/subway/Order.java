package subway;

import products.Product;

import java.util.ArrayList;

public class Order
{
    private double totalPrice;
    private ArrayList<Product> products;

    public Order()
    {
        totalPrice = 0;
        products = new ArrayList<Product>();
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public ArrayList<Product> getProducts()
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

    private void decreaseTotalPrice(double amount)
    {
        totalPrice -= amount;
    }

    public String generateReceipt()
    {
        return "subway.Order{" +
                "totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}
