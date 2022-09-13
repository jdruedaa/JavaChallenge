package subway;
//no spanglish, composición (hay que ver)

import products.Cookie;
import products.Drink;
import products.Product;
import products.Sub;
import products.ingredients.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Subway {

    private final ArrayList<Product> products;

    public static void main(String[] args) {
        Subway store = new Subway();
        store.populate();
        store.processOrder();
    }

    public Subway()
    {
        products = new ArrayList<>();
    }

    public void populate()
    {
        Drink drink = new Drink("Coca-cola 400 ml", 3400);
        products.add(drink);
        drink = new Drink("Pepsi 400 ml", 3400);
        products.add(drink);
        Cookie cookie = new Cookie("Macadamia", 3000);
        products.add(cookie);
        cookie = new Cookie("Chips de Chocolate", 3000);
        products.add(cookie);
        cookie = new Cookie("Cheesecake", 3000);
        products.add(cookie);
        Bread bread = new Bread("Pan Blanco", 300);
        products.add(bread);
        bread = new Bread("Pan Integral", 200);
        products.add(bread);
        bread = new Bread("Pan de avena", 400);
        products.add(bread);
        Filling filling = new Filling("Carne", 2500);
        products.add(filling);
        filling = new Filling("Pollo", 2300);
        products.add(filling);
        Veggie veggie = new Veggie("Tomate", 800);
        products.add(veggie);
        veggie = new Veggie("Lechuga", 500);
        products.add(veggie);
        Cheese cheese = new Cheese("Queso Probolone", 800);
        products.add(cheese);
        cheese = new Cheese("Queso Mozarella", 800);
        products.add(cheese);
        Sauce sauce = new Sauce("Salsa de Tomate", 200);
        products.add(sauce);
        sauce = new Sauce("Mayonesa", 200);
        products.add(sauce);
        Sub sub = new Sub("Sub personalizado", 8000);
        products.add(sub);
    }

    public void processOrder()
    {
        Order orden = new Order();
        Scanner in = new Scanner(System.in);
        String inputOption;
        int option;
        boolean processingOrder = true;
        while(processingOrder)
        {
            do
            {
                System.out.println("""
                        Elija la opción que desea:
                        1. Añadir producto
                        2. Ver orden
                        3. Finalizar orden""");
                inputOption = in.nextLine();
                option = validateInput(inputOption, 3);
            }
            while(option == -1);
            if(option == 1)
            {
                do
                {
                    System.out.println("""
                            Qué tipo de product desea añadir?
                            1. Sub
                            2. Bebida
                            3. Galleta""");
                    inputOption = in.nextLine();
                    option = validateInput(inputOption, 3);
                }
                while(option == -1);
                Product product = selectProduct(option);
                orden.addProduct(product);
                System.out.println("Product añadido");
            }
            else if(option == 2)
            {
                System.out.println(orden.generateReceipt());
            }
            else if(option == 3)
            {
                printReceipt(orden.generateReceipt());
                processingOrder = false;
            }
        }
        in.close();
    }

    public int validateInput(String input, int options)
    {
        int option = -1;
        try
        {
            option = Integer.parseInt(input);
            if(!(option > 0 && option <= options))
            {
                System.out.println("Opción no válida, debe ingresar un número entre 1 y " + options +
                        " para elegir una opción.");
                option = -1;
            }
        }
        catch(Exception e)
        {
            System.out.println("Opción no válida, debe ingresar un número entre 1 y " + options +
                    " para elegir una opción.");
        }
        return option;
    }

    public void printReceipt(String receipt)
    {
        System.out.println(receipt);
    }

    public Product selectProduct(int option)
    {
        Product product = null;
        Scanner in = new Scanner(System.in);
        String inputOption;
        if(option == 1)
        {
            List<Sub> subs = products.stream()
                    .filter(productSub -> productSub instanceof Sub)
                    .map(sub -> (Sub) sub).toList();
            int i = 0;
            int numOption = 1;
            StringBuilder question = new StringBuilder("Qué sub quisiera elegir?");
            while(i < subs.size())
            {
                question.append("\n").append(numOption).append(" ").append(subs.get(i).toString());
                i++;
                numOption++;
            }
            do
            {
                System.out.println(question);
                inputOption = in.nextLine();
                option = validateInput(inputOption, subs.size());
            }
            while(option == -1);
            product = makeSub(subs.get(option-1));
        }
        else if(option == 2)
        {
            List<Drink> drinks = products.stream()
                    .filter(productDrink -> productDrink instanceof Drink)
                    .map(drink -> (Drink) drink).toList();
            int i = 0;
            int numOption = 1;
            StringBuilder question = new StringBuilder("Qué bebida quisiera elegir?");
            while(i < drinks.size())
            {
                question.append("\n").append(numOption).append(" ").append(drinks.get(i).toString());
                i++;
                numOption++;
            }
            do
            {
                System.out.println(question);
                inputOption = in.nextLine();
                option = validateInput(inputOption, drinks.size());
            }
            while(option == -1);
            product = drinks.get(option-1);
        }
        else if(option == 3)
        {
            List<Cookie> cookies = products.stream()
                    .filter(productCookie -> productCookie instanceof Cookie)
                    .map(cookie -> (Cookie) cookie).toList();
            int i = 0;
            int numOption = 1;
            StringBuilder question = new StringBuilder("Qué galleta quisiera elegir?");
            while(i < cookies.size())
            {
                question.append("\n").append(numOption).append(" ").append(cookies.get(i).toString());
                i++;
                numOption++;
            }
            do
            {
                System.out.println(question);
                inputOption = in.nextLine();
                option = validateInput(inputOption, cookies.size());
            }
            while(option == -1);
            product = cookies.get(option-1);
        }
        //in.close();
        return product;
    }

    public Sub makeSub(Sub baseSub)
    {
        Scanner in = new Scanner(System.in);
        String inputOption;
        int option;
        boolean addingIngredients = true;
        List<Ingredient> ingredients = products.stream()
                .filter(product -> product instanceof Ingredient)
                .map(ingredient -> (Ingredient) ingredient).toList();
        int i = 0;
        int numOption = 1;
        StringBuilder question = new StringBuilder("Qué ingredientes quisieras añadir?");
        while(i < ingredients.size())
        {
            question.append("\n").append(numOption).append(" ").append(ingredients.get(i).toString());
            i++;
            numOption++;
        }
        question.append("\n").append(numOption).append(". No quiero añadir más ingredientes");
        do
        {
            System.out.println(question);
            inputOption = in.nextLine();
            option = validateInput(inputOption, ingredients.size() + 1);
            if(option == ingredients.size() + 1)
            {
                addingIngredients = false;
            }
            else
            {
                baseSub.addIngredient(ingredients.get(option - 1));
            }
        }
        while(option == -1 || addingIngredients);
        do
        {
            System.out.println("""
                    Deseas adiciones?
                    1. Sí
                    2. No""");
            inputOption = in.nextLine();
            option = validateInput(inputOption, 2);
            if(option == 1)
            {
                addingIngredients = true;
                do
                {
                    System.out.println(question);
                    inputOption = in.nextLine();
                    option = validateInput(inputOption, ingredients.size() + 1);
                    if(option == ingredients.size() + 1)
                    {
                        addingIngredients = false;
                    }
                    else
                    {
                        Ingredient ingredient = ingredients.get(option - 1);
                        Ingredient addition = new Ingredient(ingredient.getName(),ingredient.getPrice(),true);
                        baseSub.addIngredient(addition);
                    }
                }
                while(option == -1 || addingIngredients);
            }
        }
        while(option == -1);
        do
        {
            System.out.println("""
                    Lo deseas tostado?
                    1. Sí
                    2. No""");
            inputOption = in.nextLine();
            option = validateInput(inputOption, 2);
            if(option == 1)
            {
                baseSub.setToasted(true);
            }
        }
        while(option == -1);
        return baseSub;
    }
}

