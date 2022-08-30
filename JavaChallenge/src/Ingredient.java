public class Ingredient extends Product
{
    private boolean isAddition;

    public Ingredient(String name, double price)
    {
        super(name, price);
        isAddition = false;
    }

    public Ingredient(String name, double price, boolean isAddition)
    {
        super(name, price);
        this.isAddition = isAddition;
    }

    @Override
    public String toString() {
        String ingrediente = name;
        if(isAddition)
        {
            ingrediente += " " + price + " COP";
        }
        return ingrediente;
    }

    public boolean isAddition() {
        return isAddition;
    }

    public void setAddition(boolean addition) {
        isAddition = addition;
    }

    public String additionString()
    {
        return name + " " + price + " COP";
    }
}
