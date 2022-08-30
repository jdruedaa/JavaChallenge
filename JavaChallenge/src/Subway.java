import java.util.Scanner;

public class Subway {

    //private Product products;

    public static void main(String[] args) {
        Subway store = new Subway();
        store.populate();
        store.processOrder();
    }

    public void populate()
    {

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
                        "2. Ver orden \n3.Finalizar orden");
                inputOpcion = in.nextLine();
                opcion = validateInput(inputOpcion, 3);
            }
            while(opcion == -1);
            if(opcion == 1)
            {
                
            }
            else if(opcion == 2)
            {

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
}