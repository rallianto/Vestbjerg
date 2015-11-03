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
public class ItemUI
{
    private ItemCtr itemCtr;
    private InputManager inputManager;

    /**
     * Kontruktøren der kalder inputManageren samt vare controlleren
     */
    public ItemUI()
    {
        itemCtr = new ItemCtr();
        inputManager = InputManager.getInstance();
    }

    /**
     * Printer vare menuen og returnere dit valg
     */
    private int printItemMenu()
    {
        inputManager.clearConsole();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Sidste handling: " + inputManager.getLastAction());
        System.out.println(" Produkt menu");
        System.out.println("");
        System.out.println(" (1) Opret produkt");
        System.out.println(" (2) Find produkt");
        System.out.println(" (3) Opdater produkt");
        System.out.println(" (4) Slet produkt");
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
     * kalder metoden printItemMenu og navigere dig videre baseret på det valg den returnere
     */
    public void itemMenu()
    {
        boolean exit = false;
        while(!exit)
        {
            int choise = printItemMenu();
            switch (choise)
            {
                case 1:
                //Opret produkt
                createItem();
                break;

                case 2:
                //Find produkt
                findItem();
                break;

                case 3:
                //Opdater et produkt
                updateItem();
                break;

                case 4:
                //Slette et produkt
                deleteItem();
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
     * Opretter en vare
     */
    private void createItem()
    {
        int choseLocation = inputManager.inputInteger("Tryk 1 for trælasten og 2 for byggemarkedet :");
        int barcode = inputManager.inputInteger("Stregkode: ");
        String name = inputManager.inputString("Navn: ", false);
        double price = inputManager.inputDouble("Pris :");
        String description = inputManager.inputString("Beskrivelse: ", false);
        int minInStock = inputManager.inputInteger("Minimumbeholdning: ");
        int maxInStock = inputManager.inputInteger("Maximumbeholdning :");
        //Create a new product
        Item item = itemCtr.createItem(barcode, name, price, description, (choseLocation == 1) ? ModelLayer.Item.Location.TRAELAST : ModelLayer.Item.Location.BYGGEMARKED, minInStock, maxInStock);
        if (item != null)
        {
            inputManager.setLastAction("\n" + 
                "Der er oprettet et nyt produkt: " + item.getName() + "\n" +
                "Lokation: " + item.getWarehouse() + "\n" +
                "Stregkode: " + barcode + "\n" +
                "Pris: " + price + "\n" + "Beskrivelse: " + description + "\n" +
                "Minimumbeholdning: " + minInStock + 
                "\n" + "Maximumbeholdning: " + maxInStock + "\n");            
        }
        else
        {
            inputManager.setLastAction("Det lykkedes ikke at oprette et nyt produkt");
        }
    }

    /**
     * Finder en vare baseret på strejkoden på varen
     */
    private void findItem()
    {
        int barcode = inputManager.inputInteger("Indtast stregkoden på varen");        
        if (itemCtr.findItem(barcode) != null)
        {
            Item item = itemCtr.findItem(barcode);
            inputManager.setLastAction("\n" + 
                "Der er fundet et produkt: " + item.getName() + "\n" +
                "Lokation: " + item.getWarehouse() + "\n" +
                "Stregkode: " + item.getBarcode() + "\n" +
                "Pris: " + item.getPrice() + "\n" + "Beskrivelse: " + item.getDescription() + "\n" +
                "Minimumbeholdning: " + item.getMinInStock() + 
                "\n" + "Maximumbeholdning: " + item.getMaxInStock() + "\n");            
        }
        else
        {
            inputManager.setLastAction("Det lykkedes ikke at finde et produkt");
        }
    }

    /**
     * Denne metode bruges til at ændre i variablerne knyttet til en vare i itemContainer
     */
    private void updateItem()
    {
        int barcode = inputManager.inputInteger("Stregkode: ");
        if (itemCtr.findItem(barcode) != null)
        {
            Item item = itemCtr.findItem(barcode);
            System.out.println("\n" + 
                "Der er fundet et produkt: " + item.getName() + "\n" +
                "Lokation: " + item.getWarehouse() + "\n" +
                "Stregkode: " + item.getBarcode() + "\n" +
                "Pris: " + item.getPrice() + "\n" + "Beskrivelse: " + item.getDescription() + "\n" +
                "Minimumbeholdning: " + item.getMinInStock() + 
                "\n" + "Maximumbeholdning: " + item.getMaxInStock() + "\n");
            
            
            String name = inputManager.inputString("Navn ændres til: ", false);
            int choseLocation = inputManager.inputInteger("Tryk 1 for at ændre lokation til trælasten og 2 for byggemarkedet:");
            double price = inputManager.inputDouble("Pris ændres til:");
            String description = inputManager.inputString("Beskrivelse ændres til: ", false);
            int minInStock = inputManager.inputInteger("Minimumbeholdning ændres til: ");
            int maxInStock = inputManager.inputInteger("Maximumbeholdning ændres til:");
            
            itemCtr.updateItem(barcode, name, price, description, (choseLocation == 1) ? ModelLayer.Item.Location.TRAELAST : ModelLayer.Item.Location.BYGGEMARKED, minInStock, maxInStock);

            inputManager.setLastAction("\n" + 
                "Der er opdateret et produkt: " + item.getName() + "\n" +
                "Lokation: " + item.getWarehouse() + "\n" +
                "Stregkode: " + barcode + "\n" +
                "Pris: " + price + "\n" + "Beskrivelse: " + description + "\n" +
                "Minimumbeholdning: " + minInStock + 
                "\n" + "Maximumbeholdning: " + maxInStock + "\n");            
        }
        else
        {
            inputManager.setLastAction("Det lykkedes ikke at ændre et produkt");
        }
    }

    /**
     * Slet en vare baseret på varen strejkode
     */
    private void deleteItem()
    {
        int barcode = inputManager.inputInteger("Stregkode: ");
        if(itemCtr.findItem(barcode) != null)
        {
            Item item = itemCtr.findItem(barcode);
            inputManager.setLastAction("\n" + 
                "Produktet der slettes er: " + item.getName() + "\n" +
                "Lokation: " + item.getWarehouse() + "\n" +
                "Stregkode: " + item.getBarcode() + "\n" +
                "Pris: " + item.getPrice() + "\n" + "Beskrivelse: " + item.getDescription() + "\n" +
                "Minimumbeholdning: " + item.getMinInStock() + 
                "\n" + "Maximumbeholdning: " + item.getMaxInStock() + "\n" +
                "Produktet er succesfuldt slettet" + "\n");
            itemCtr.deleteItem(barcode);            
        }
        else
        {
            inputManager.setLastAction("Det lykkedes ikke at slette et produktet");
        }
    }
}
