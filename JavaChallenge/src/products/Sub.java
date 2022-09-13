package products;

import products.ingredients.Ingredient;

import java.util.ArrayList;

public class Sub extends Product
{
    private final ArrayList<Ingredient> ingredients;
    private boolean toasted;

    public Sub(String name, double price)
    {
        super(name, price);
        ingredients = new ArrayList<>();
        toasted = false;
    }

    public Sub(String name, double price, ArrayList<Ingredient> ingredients)
    {
        super(name, price);
        this.ingredients = ingredients;
        toasted = false;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void addIngredient(Ingredient ingredient)
    {
        ingredients.add(ingredient);
        if(ingredient.isAddition())
        {
            price += ingredient.getPrice();
        }
    }
}
