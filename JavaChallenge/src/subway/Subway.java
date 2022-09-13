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
        String productName;
        double productPrice;
        productName = "Coca-cola 400 ml";
        productPrice = 3400;
        Drink drink = new Drink(productName, productPrice);
        products.add(drink);
        productName = "Pepsi 400 ml";
        drink = new Drink(productName, productPrice);
        products.add(drink);
        productName = "Macadamia";
        productPrice = 3000;
        Cookie cookie = new Cookie(productName, productPrice);
        products.add(cookie);
        productName = "Chips de Chocolate";
        cookie = new Cookie(productName, productPrice);
        products.add(cookie);
        productName = "Cheesecake";
        cookie = new Cookie(productName, productPrice);
        products.add(cookie);
        productName = "Pan Blanco";
        productPrice = 300;
        Bread bread = new Bread(productName, productPrice);
        products.add(bread);
        productName = "Pan Integral";
        productPrice = 200;
        bread = new Bread(productName, productPrice);
        products.add(bread);
        productName = "Pan de avena";
        productPrice = 400;
        bread = new Bread(productName, productPrice);
        products.add(bread);
        productName = "Carne";
        productPrice = 2500;
        Filling filling = new Filling(productName, productPrice);
        products.add(filling);
        productName = "Pollo";
        productPrice = 2300;
        filling = new Filling(productName, productPrice);
        products.add(filling);
        productName = "Tomate";
        productPrice = 800;
        Veggie veggie = new Veggie(productName, productPrice);
        products.add(veggie);
        productName = "Lechuga";
        productPrice = 500;
        veggie = new Veggie(productName, productPrice);
        products.add(veggie);
        productName = "Queso Probolone";
        productPrice = 800;
        Cheese cheese = new Cheese(productName, productPrice);
        products.add(cheese);
        productName = "Queso Mozarella";
        cheese = new Cheese(productName, productPrice);
        products.add(cheese);
        productName = "Salsa de Tomate";
        productPrice = 200;
        Sauce sauce = new Sauce(productName, productPrice);
        products.add(sauce);
        productName = "Mayonesa";
        sauce = new Sauce(productName, productPrice);
        products.add(sauce);
        productName = "Sub personalizado";
        productPrice = 8000;
        Sub sub = new Sub(productName, productPrice);
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
                            Qué tipo de producto desea añadir?
                            1. Sub
                            2. Bebida
                            3. Galleta""");
                    inputOption = in.nextLine();
                    option = validateInput(inputOption, 3);
                }
                while(option == -1);
                Product product = selectProduct(option, in);
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

    public Product selectProduct(int option, Scanner in)
    {
        Product product = null;
        String inputOption;
        if(option == 1)
        {
            List<Sub> subs = products.stream()
                    .filter(Sub.class::isInstance)
                    .map(Sub.class::cast).toList();
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
            product = makeSub(subs.get(option-1), in);
        }
        else if(option == 2)
        {
            List<Drink> drinks = products.stream()
                    .filter(Drink.class::isInstance)
                    .map(Drink.class::cast).toList();
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
                    .filter(Cookie.class::isInstance)
                    .map(Cookie.class::cast).toList();
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
        return product;
    }

    public Sub makeSub(Sub baseSub, Scanner in)
    {
        String inputOption;
        int option;
        boolean addingIngredients = true;
        List<Ingredient> ingredients = products.stream()
                .filter(Ingredient.class::isInstance)
                .map(Ingredient.class::cast).toList();
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
                        Ingredient addition = new Ingredient(ingredient);
                        addition.setAddition(true);
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

