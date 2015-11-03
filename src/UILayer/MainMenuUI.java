package UILayer;
import java.util.Scanner;

import CtrLayer.*;
import GUILayer.MainGUI;
import ModelLayer.*;

import java.awt.EventQueue;
import java.io.*;

/**
 * Dette er MainMenuUI klassen
 * som er hoved menuen af programmet
 * 
 * @author Gruppe 4
 */

public class MainMenuUI
{
    private PersonUI personUI;
    private ItemUI itemUI;
    private OrderUI orderUI;
    private InputManager inputManager;
    private SaveLoadCtr saveLoadCtr;

    public static void main(String[] args)
    {
    	new MainMenuUI();
	}
    /**
     * Kontruktøren der kalder inputManageren samt de andre menu'er
     */
    public MainMenuUI()
    {
        inputManager = InputManager.getInstance();
        personUI = new PersonUI();
        itemUI = new ItemUI();
        orderUI = new OrderUI();
        saveLoadCtr = new SaveLoadCtr();
        loadDatabase();
        startMenu();
    }

    /**
     * Overskriver Person, Item- og OrderContainer med containere fra Data.bin. Hvis Data.bin ikke eksisterer, opretter en Data.bin
     */
    private void loadDatabase()
    {
        File f = new File(saveLoadCtr.getFileName());
        boolean fileLoaded = false;
        boolean newDatabase = false;
        while (!fileLoaded)
        {
            if (f.isFile())
            {
                try
                {
                    saveLoadCtr.load();
                }
                catch(IOException e)
                { 
                    e.printStackTrace();
                }
                catch(ClassNotFoundException e)
                { 
                    e.printStackTrace();
                }
                
                if (newDatabase)
                {
                    //Der fandtes ikke nogen Data.bin-fil - Så nu er der oprettet en
                    inputManager.setLastAction("Database er succesfuldt oprettet!");
                }
                else
                {
                    //Der fandtes en Data.bin-fil - og dataerne er succesfuldt hentet
                    inputManager.setLastAction("Data er succesfuldt hentet!"); 
                }
                fileLoaded = true;
            }
            else
            {
                try
                {
                    saveLoadCtr.save();
                    newDatabase = true;
                }
                catch(IOException e)
                { 
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Printer Hovedmenuen og returnere dit valg
     */
    private int printMainMenu()
    {
        inputManager.clearConsole();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Sidste handling: " + inputManager.getLastAction());
        System.out.println(" Main Menu");
        System.out.println("");
        System.out.println(" (1) Person");
        System.out.println(" (2) Varer");
        System.out.println(" (3) Ordre");
        System.out.println(" (0) Afslut");
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
     * kalder metoden printMainMenu og sender dig videre baseret på dit valg
     */
    public void startMenu()
    {
        boolean exit = false;
        while(!exit)
        {
            int choise = printMainMenu();
            switch (choise)
            {
                //Person CRUD
                case 1:
                personUI.personMenu();
                break;

                case 2:
                itemUI.itemMenu();
                break;

                case 3:
                orderUI.orderMenu();
                break;

                case 0:
                System.out.println("Tak for denne gang");
                try
                {
                    saveLoadCtr.save();
                }
                catch(IOException e)
                { 
                    e.printStackTrace();
                }
                exit = true;
                break;

                default:
                System.out.println("Forstår ikke hvad du mener!");
                break;
            }
        } 
    }
}
