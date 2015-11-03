package ModelLayer;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Dette er Order klassen, som bliver brugt til ordre håndtering.
 * 
 * @author Gruppe 4
 */
public class Order implements Serializable
{ 
    private ArrayList<SalesLineItem> saleLineCon;
    private String date;
    private int ID;
    private DateFormat dateFormat;
    private Date currentDate;
    private Person person;
    
    /**
     * Kontruktøren der sætter følgende parametre: person, currentDate, dateFormat, date, saleLineCon
     */
    public Order()
    {
        person = null;
        currentDate = new Date();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(currentDate);
        saleLineCon = new ArrayList<SalesLineItem>();
    }
    
    /**
     * Definere ID'et på ordren
     */
    public void setID(int ID)
    {
        this.ID = ID;
    }

    /**
     * returnere et ID
     */
    public int getID()
    {
        return ID;
    }

    /**
     * Printer datoen
     */
    public void printDate()
    {
        System.out.println(date);
    }
    
    public String getDate()
    {
    	return date;
    }
    
    public int getItemCount()
    {
    	int count = 0;
    	for(SalesLineItem item: saleLineCon)
    	{
    		count++;
    	}
    	return count;
    }
    
    /**
     * Tilføj en delordre til denne ordre
     */
    public void addSalesLineItem(SalesLineItem sli)
    {
        saleLineCon.add(sli);
    }
    
    /**
     * Tilføjer et person objekt til denne ordre
     */
    public void addPersonToOrder(Person person)
    {
        this.person = person;
    }
    
    /**
     * Returnere ArrayListen saleLineCon
     */
    public ArrayList<SalesLineItem> getSaleLine()
    {
        return saleLineCon;
    }
    
    /**
     * Returnere et Person object
     */
    public Person getPerson()
    {
        return person;
    }
}
