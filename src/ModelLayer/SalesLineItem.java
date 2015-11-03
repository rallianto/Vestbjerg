package ModelLayer;
import java.io.*;


/**
 * Dette er Person klassen, som bliver brugt til håndtering af delordre.
 * 
 * @author Gruppe 4
 */
public class SalesLineItem implements Serializable
{
    // instance variables - replace the example below with your own
    private Item item;
    private int amount;

    /**
     * Kontruktøren der sætter følgende parametre: item, amount
     */
    public SalesLineItem(Item item, int amount)
    {
        this.item = item;
        this.amount = amount;
    }
    
    /**
     * Returnere varen
     */
    public Item getItem()
    {
        return item;
    }
    
    /**
     * Returnere antallet
     */
    public int getAmount()
    {
        return amount;
    }
    
    public void setAmount(int i)
    {
    	amount = i;
    }
}
