package UILayer;
import java.util.Scanner;
import CtrLayer.*;
import ModelLayer.*;

/**
 * Dette er PersonUI klassen
 * som er person menuen
 * 
 * @author Gruppe 4
 */
public class OrderUI
{
    private OrderCtr orderCtr;
    private InputManager inputManager;

    /**
     * Kontruktøren der kalder inputManageren samt ordre controlleren
     */
    public OrderUI()
    {
        orderCtr = new OrderCtr();
        inputManager = InputManager.getInstance();
    }
    
    /**
     * Printer ordre menuen og returnere dit valg
     */
    private int printOrderMenu()
    {
        inputManager.clearConsole();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Sidste handling: " + inputManager.getLastAction());
        System.out.println(" Ordre Menu");
        System.out.println("");
        System.out.println(" (1) Opret Ordre");
        System.out.println(" (2) Find Ordre");
        System.out.println(" (0) Gå tilbage til hovedmenu");
        System.out.println("\n Vælg en af disse: ");
        System.out.println("");

        int choise;

        while (!keyboard.hasNextInt())
        {
            System.out.println("Det er ikke et tal!");
            keyboard.next();
        }
        choise = keyboard.nextInt();
        return choise;
    }    
    
    /**
     * kalder metoden printOrderMenu og navigere dig videre baseret på det valg den returnere
     */
    public void orderMenu()
    {
        boolean exit = false;
        while(!exit)
        {
            int choise = printOrderMenu();
            switch (choise)
            {
                case 1:
                //Opret ordre
                createOrder();
                break;

                case 2:
                //Find ordre
                findOrder();
                break;

                case 0:
                exit = true;
                break;

                default:
                System.out.println("Forstår ikke hvad du mener!");
                break;
            }
        }
    }

    /**
     * opretter en ordre
     */
    private void createOrder()
    {
        //Opret ordre og hent ID
        Order order = orderCtr.createOrder();
        int orderID = order.getID();
        addItem(orderID);
        addCustomer(orderID);
        inputManager.setLastAction("Ordre med ID: " + orderID + " oprettet");
    }

    /**
     * Tilføjer en eller flere vare til ordren
     */
    private void addItem(int orderID)
    {
        boolean done = false;
        while(done == false)
        {
            int barcode = inputManager.inputInteger("Indtast stregkode for vare:");
            if(orderCtr.findItem(barcode) != null)
            {
                int amount = inputManager.inputInteger("Indtast antal:");
                orderCtr.addItem(barcode, amount, orderID);
                int choice = inputManager.inputInteger("Vil du tilføje flere varer? \n 1. Ja \n 2. nej \n Vælg en af disse.");
                if(choice == 2)
                {
                    done = true;
                }
            }
            else
            {
                inputManager.setLastAction("Vare med ID: " + orderID + " blev ikke fundet!");
            }
        }
    }    

    /**
     * Tilføjer en kunde til ordren
     */
    private void addCustomer(int orderID)
    {
        boolean done = false;
        while(done == false)
        {
            int personID = inputManager.inputInteger("Indtast kunde ID:");
            if(orderCtr.findPerson(personID) != null)
            {
                orderCtr.addCustomer(personID, orderID);
                inputManager.setLastAction("Person med ID: " + personID + " blev tilføjet til ordren");
                done = true;
            }
            else
            {
                inputManager.setLastAction("Kunne ikke finde Person med ID: " + personID);
            }
        }
    }

    /**
     * Finder en ordre baseret på ID
     */
    private void findOrder()
    {
        int ID = inputManager.inputInteger("Ordre ID:");
        if(orderCtr.findOrder(ID) != null)
        {
            Order order = orderCtr.findOrder(ID);
            if (order.getSaleLine().size() != 0)
            {
                System.out.println(String.format("Kunde: %-25s", order.getPerson().getName()));
                System.out.println("Vare:                         Antal:      ");
                System.out.println("------------------------------------------");
                for(SalesLineItem item : order.getSaleLine())
                {
                    System.out.println(String.format("%-30s %-5d", item.getItem().getName(), item.getAmount()));
                    inputManager.setLastAction("Der blev fundet en ordre med ID: " + ID);

                }
            }
        }
        else
        {
            inputManager.setLastAction("Der blev ikke fundet en ordre med ID: " + ID);
        }

        boolean done = false;
        while(!done)
        {
            int choice = inputManager.inputInteger("\n Tryk 0 for at gå tilbage");
            if(choice == 0)
            {
                done = true;
            }
        }
    }
}
