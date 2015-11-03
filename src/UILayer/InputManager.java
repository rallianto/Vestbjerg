package UILayer;
import java.util.Scanner;

/**
 * Dette er InputManager klassen
 * som er en singleton og håndtere de inputs man får fra brugeren
 * 
 * @author Gruppe 4
 */
public class InputManager
{
    private String lastAction;
    private static InputManager instance = null;
    /**
     * Kontruktøren der kalder sætter variablen lastAction
     */
    public InputManager()
    {
        lastAction = "Du har intet fortaget dig endnu";
    }
    
    /**
     * Denne metode kalder kontruktøren og følger singleton mønstret
     */
    public static InputManager getInstance()
    {
        if(instance == null)
        {
            instance = new InputManager();
        }
        return instance;
    }

    /**
     * Denne metode beskriver hvad brugeren skal indtaste og tager imod brugerens svar for alle Strings
     */
    public String inputString(String request, boolean empty)
    {
        System.out.println(request);
        Scanner keyboard = new Scanner(System.in);
        String output = keyboard.nextLine();
        if(!empty)
        {
            while(output.trim().isEmpty())
            {
                System.out.println("Der skal tastes noget ind her");
                output = keyboard.nextLine();
            }
        }
        return output;
    }

    /**
     * Denne metode beskriver hvad brugeren skal indtaste og tager imod brugerens svar for alle ints
     */
    public int inputInteger(String request)
    {
        System.out.println(request);
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt())
        {
            System.out.println("Det er ikke et tal");
            keyboard.next();
        }
        int output = keyboard.nextInt();
        return output;
    }
    
    /**
     * Denne metode beskriver hvad brugeren skal indtaste og tager imod brugerens svar for alle doubles
     */
    public double inputDouble(String request)
    {
        System.out.println(request);
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt())
        {
            System.out.println("Det er ikke et tal");
            keyboard.next();
        }
        int output = keyboard.nextInt();
        return output;
    }

    /**
     * Denne metode ryder konsollen
     */
    public void clearConsole()
    {
        System.out.print("\f");//Clear the console
    }

    /**
     * Denne metode returnere variblen lastAction
     */
    public String getLastAction()
    {
        return lastAction;
    }

    /**
     * Denne metode sætter variablen lastAction
     */
    public void setLastAction(String lastAction)
    {
        this.lastAction = lastAction;
    }
}
