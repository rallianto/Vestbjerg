package CtrLayer;
import ModelLayer.*;
/**
 * Dette er OrderCtr klassen
 * som bliver brugt til kalde ordre metoder fra Model laget og sende informationerne op til UILaget
 * 
 * @author Gruppe 4
 */
public class OrderCtr
{
    private OrderContainer oCon;
    private ItemCtr itemCtr;
    private PersonCtr personCtr;
    
    /**
     * Kontruktøren der henter ArrayListen der holder på ordre, samt ItemCtr klassen og PersonCtr klassen
     */
    public OrderCtr()
    {
        oCon = OrderContainer.getInstance();
        itemCtr = new ItemCtr();
        personCtr = new PersonCtr();
    }

    /**
     * Opretter en ny tom ordre, og putter ordre i container og returnere order.
     */
    public Order createOrder()
    {
        Order order = new Order();        
        oCon.addOrder(order);
        return order;
    }

    /**
     * Tilføj vare(r) til en ordre
     */
    public Order addItem(int barcode, int amount, int ID)
    {
        Order order = null;
        if(itemCtr.findItem(barcode) != null) 
        {                
            if(findOrder(ID) != null)
            {
                SalesLineItem sli = new SalesLineItem(itemCtr.findItem(barcode), amount);
                order = findOrder(ID);
                order.addSalesLineItem(sli);
            }
        }        
        return order;
    }

    /**
     * Returnere et ordre objekt baseret på ID, returnere null hvis objektet ikke findes
     */
    public Order findOrder(int ID)
    {
        Order returnOrder = null;
        Order iterOrder = null;
        int index = 0;
        boolean found = false;
        while(index < oCon.getSize() && !found)
        {
            iterOrder = oCon.getContainer().get(index);
            if(iterOrder.getID() == ID)
            {
                returnOrder = iterOrder;
                found = true;
            }
            else
            {
                index++;
            }
        }
        return returnOrder;
    }

    /**
     * Tilføj et person objekt til denne ordre
     */
    public Order addCustomer(int personID, int orderID)
    {
        Order order = null;
        if(findOrder(orderID) != null && personCtr.findPerson(personID) != null)
        {
            order = findOrder(orderID);
            order.addPersonToOrder(personCtr.findPerson(personID));
        }
        return order;
    }
    
    /**
     * Returner et Item objekt baseret på stregkode, returner NULL hvis det ikke findes.
     */
    public Item findItem(int barcode)
    {
        return itemCtr.findItem(barcode);
    }
    
    /**
     * Returner et Person objekt baseret på ID, returner NULL hvis det ikke findes.
     */
    public Person findPerson(int ID)
    {
        return personCtr.findPerson(ID);
    }
    
    public OrderContainer getContainer()
    {
    	return oCon;
    }
    
    
}




