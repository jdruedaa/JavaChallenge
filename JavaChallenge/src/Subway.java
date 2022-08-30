import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Subway {

    private ArrayList<Product> products;

    public static void main(String[] args) {
        Subway store = new Subway();
        store.populate();
        store.processOrder();
    }

    public Subway()
    {
        products = new ArrayList<Product>();
    }

    public void populate()
    {
        Drink bebida = new Drink("Coca-cola 400 ml", 3400);
        products.add(bebida);
        bebida = new Drink("Pepsi 400 ml", 3400);
        products.add(bebida);
        Cookie galleta = new Cookie("Macadamia", 3000);
        products.add(galleta);
        galleta = new Cookie("Chips de Chocolate", 3000);
        products.add(galleta);
        galleta = new Cookie("Cheesecake", 3000);
        products.add(galleta);
        Sub sub = new Sub("Sub de Carne", 8000);
        products.add(sub);
        Ingredient ingrediente = new Ingredient("Pan Blanco", 300);
        products.add(ingrediente);
        ingrediente = new Ingredient("Carne", 2500);
        products.add(ingrediente);
        sub.addIngredient(ingrediente);
        ingrediente = new Ingredient("Tomate", 2500);
        products.add(ingrediente);
        ingrediente = new Ingredient("Queso Probolone", 800);
        products.add(ingrediente);
        ingrediente = new Ingredient("Salsa de Tomate", 200);
        products.add(ingrediente);
    }

    public void processOrder()
    {
        Order orden = new Order();
        Scanner in = new Scanner(System.in);
        String inputOpcion;
        int opcion;
        boolean processingOrder = true;
        while(processingOrder)
        {
            do
            {
                System.out.println("Elija la opción que desea:\n1. Añadir producto.\n" +
                        "2. Ver orden \n3. Finalizar orden");
                inputOpcion = in.nextLine();
                opcion = validateInput(inputOpcion, 3);
            }
            while(opcion == -1);
            if(opcion == 1)
            {
                do
                {
                    System.out.println("Qué tipo de producto desea añadir?\n1. Sub\n" +
                            "2. Bebida \n3. Galleta");
                    inputOpcion = in.nextLine();
                    opcion = validateInput(inputOpcion, 3);
                }
                while(opcion == -1);
                Product producto = selectProduct(opcion);
                orden.addProduct(producto);
                System.out.println("Producto añadido");
            }
            else if(opcion == 2)
            {
                System.out.println(orden.generateReceipt());
            }
            else if(opcion == 3)
            {
                printReceipt(orden.generateReceipt());
                processingOrder = false;
            }
        }
        in.close();
    }

    public int validateInput(String input, int options)
    {
        int opcion = -1;
        try
        {
            opcion = Integer.parseInt(input);
            if(!(opcion > 0 && opcion <= options))
            {
                System.out.println("Opción no válida, debe ingresar un número entre 1 y " + options +
                        " para elegir una opción.");
                opcion = -1;
            }
        }
        catch(Exception e)
        {
            System.out.println("Opción no válida, debe ingresar un número entre 1 y " + options +
                    " para elegir una opción.");
        }
        return opcion;
    }

    public void printReceipt(String receipt)
    {
        System.out.println(receipt);
    }

    public Product selectProduct(int opcion)
    {
        Product producto = null;
        Scanner in = new Scanner(System.in);
        String inputOpcion;
        if(opcion == 1)
        {
            List<Sub> subs = products.stream()
                    .filter(product -> product instanceof Sub)
                    .map(sub -> (Sub) sub)
                    .collect(Collectors.toList());
            int i = 0;
            int numOpcion = 1;
            String pregunta = "Qué sub quisiera elegir?";
            while(i < subs.size())
            {
                pregunta += "\n" + numOpcion + " " + subs.get(i).toString();
                i++;
                numOpcion++;
            }
            do
            {
                System.out.println(pregunta);
                inputOpcion = in.nextLine();
                opcion = validateInput(inputOpcion, subs.size());
            }
            while(opcion == -1);
            producto = makeSub(subs.get(opcion-1));
        }
        else if(opcion == 2)
        {
            List<Drink> bebidas = products.stream()
                    .filter(product -> product instanceof Drink)
                    .map(drink -> (Drink) drink)
                    .collect(Collectors.toList());
            int i = 0;
            int numOpcion = 1;
            String pregunta = "Qué bebida quisiera elegir?";
            while(i < bebidas.size())
            {
                pregunta += "\n" + numOpcion + " " + bebidas.get(i).toString();
                i++;
                numOpcion++;
            }
            do
            {
                System.out.println(pregunta);
                inputOpcion = in.nextLine();
                opcion = validateInput(inputOpcion, bebidas.size());
            }
            while(opcion == -1);
            producto = bebidas.get(opcion-1);
        }
        else if(opcion == 3)
        {
            List<Cookie> galletas = products.stream()
                    .filter(product -> product instanceof Cookie)
                    .map(cookie -> (Cookie) cookie)
                    .collect(Collectors.toList());
            int i = 0;
            int numOpcion = 1;
            String pregunta = "Qué galleta quisiera elegir?";
            while(i < galletas.size())
            {
                pregunta += "\n" + numOpcion + " " + galletas.get(i).toString();
                i++;
                numOpcion++;
            }
            do
            {
                System.out.println(pregunta);
                inputOpcion = in.nextLine();
                opcion = validateInput(inputOpcion, galletas.size());
            }
            while(opcion == -1);
            producto = galletas.get(opcion-1);
        }
        //in.close();
        return producto;
    }

    public Sub makeSub(Sub subBase)
    {
        Sub sub = subBase;
        return sub;
    }
}

