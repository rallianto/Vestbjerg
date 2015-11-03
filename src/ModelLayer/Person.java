package ModelLayer;
import java.io.*;

/**
 * Dette er Person klassen, som er en superklasse og bliver brugt til håndtering af personer.
 * 
 * @author Gruppe 4
 */
public class Person implements Serializable
{
    private String name;
    private String address;
    private String city;
    private int phone;
    private int ID;

    /**
     * Kontruktøren der sætter følgende parametre: name, address, city, phone
     */
    public Person(String name, String address, String city, int phone)
    {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }
    
    /**
     * Sætter variablen name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Sætter variablen address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    /**
     * Sætter variablen city
     */
    public void setCity(String city)
    {
        this.city = city;
    }
    
    /**
     * Sætter variablen phone
     */
    public void setPhone(int phone)
    {
        this.phone = phone;
    }
    
    /**
     * Sætter ID'et på personen
     */
    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    /**
     * Returnere variablen name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returnere variablen address
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * Returnere variablen city
     */
    public String getCity()
    {
        return city;
    }
    
    /**
     * Returnere variablen phone
     */
    public int getPhone()
    {
        return phone;
    }
    /**
     * Returnere ID'et på personen
     */
    public int getID()
    {
        return ID;
    }
    
}