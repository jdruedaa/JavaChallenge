import java.util.ArrayList;

public class Sub extends Product
{
    private ArrayList<Ingredient> ingredientes;
    private boolean toasted;

    public Sub(String name, double price)
    {
        super(name, price);
        ingredientes = new ArrayList<Ingredient>();
        toasted = false;
    }

    public Sub(String name, double price, ArrayList<Ingredient> ingredientes)
    {
        super(name, price);
        this.ingredientes = ingredientes;
        toasted = false;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void addIngredient(Ingredient ingrediente)
    {
        ingredientes.add(ingrediente);
        if(ingrediente.isAddition())
        {
            price += ingrediente.getPrice();
        }
    }
}
