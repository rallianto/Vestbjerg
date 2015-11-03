package ModelLayer;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Write a description of class SaveLoad here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SaveLoad
{
    private String fileName;
    private PersonContainer personCon;
    private ItemContainer itemCon;
    private OrderContainer orderCon;

    /**
     * Constructor for objects of class SaveLoad
     */
    public SaveLoad()
    {
        this.fileName = "Data.bin";
        personCon = PersonContainer.getInstance();
        itemCon = ItemContainer.getInstance();
        orderCon = OrderContainer.getInstance();
    }

    /**
     * Gemmer et objekt af typen @DataContainer som sendes med i parameteren, til en Data.bin-fil
     */
    public boolean saveData(DataContainer dataContainer) throws IOException
    {
        boolean status = false;
        try
        {
            ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outStream.writeObject(dataContainer);
            outStream.close();
            status = true;
        }
        catch(IOException e)
        { 
            e.printStackTrace();
        }
        return status;
    }

    /**
     * Returnerer et objekt af typen @DataContainer, som er gemt i Data.bin-filen
     */
    public DataContainer loadData() throws IOException, ClassNotFoundException, FileNotFoundException
    {
        DataContainer returnData = new DataContainer(PersonContainer.getInstance().getContainer(), ItemContainer.getInstance().getContainer(), OrderContainer.getInstance().getContainer());
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            returnData = (DataContainer)inputStream.readObject();
            inputStream.close();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
          catch(IOException e)
        {
            e.printStackTrace();
        }
        return returnData;
    }
    
    /**
     * Returner navnet på filen som dataerne gemmes i.
     */
    public String getFileName()
    {
        return fileName;
    }
    
    public PersonContainer getPersonContainer ()
    {
        return personCon;
    }
    
    public ItemContainer getItemContainer ()
    {
        return itemCon;
    }
    
    public OrderContainer getOrderContainer ()
    {
        return orderCon;
    }
}
