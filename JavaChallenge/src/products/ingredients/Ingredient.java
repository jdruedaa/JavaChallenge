package products.ingredients;

import products.Product;

public class Ingredient extends Product
{
    private boolean isAddition;

    public Ingredient(String name, double price)
    {
        super(name, price);
        isAddition = false;
    }

    public Ingredient(Ingredient ingredient)
    {
        super(ingredient.name, ingredient.price);
        this.isAddition = ingredient.isAddition;

    }

    @Override
    public String toString() {
        String ingredient = name;
        if(isAddition)
        {
            ingredient += " " + price + " COP";
        }
        return ingredient;
    }

    public boolean isAddition() {
        return isAddition;
    }

    public void setAddition(boolean addition) {
        isAddition = addition;
    }
}
