package ModelLayer;

import static org.junit.Assert.*;

import org.junit.Test;

import ModelLayer.Customer.Type;

public class OrderContainerTest {
	private OrderContainer oCon = OrderContainer.getInstance();

	@Test
	public void testAddOrder() {
		Item item = new Item(321, "koben", 59.50, "Koben af Jern", Item.Location.TRAELAST, 2, 15);
		Customer customer = new Customer("ole", "Hansvej 32", "Aalborg", 23232323, Type.PRIVATE);
		
		Order oTest = new Order();
		oTest.addPersonToOrder(customer);
		SalesLineItem sli = new SalesLineItem(item, 1);
		oTest.addSalesLineItem(sli);
		oCon.addOrder(oTest);
		
		Order expect = oTest;
		Order result = null;
		
		for(int i = 0; i < oCon.getSize(); i++)
		{
			if(oCon.getContainer().get(i).equals(oTest))
			{
				result = oCon.getContainer().get(i);
			}
		}
		assertEquals(expect, result);	
	}
}
