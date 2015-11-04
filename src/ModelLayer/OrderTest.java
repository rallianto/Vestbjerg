package ModelLayer;

import static org.junit.Assert.*;

import org.junit.Test;

import ModelLayer.Customer;
import ModelLayer.Customer.Type;
import ModelLayer.Item;

public class OrderTest {
	private Order o;
	@Test
	public void testCreateOrder() {
		Item item = new Item(321, "koben", 59.50, "Koben af Jern", Item.Location.TRAELAST, 2, 15);
		Customer customer = new Customer("ole", "Hansvej 32", "Aalborg", 23232323, Type.PRIVATE);
		
		Order oTest = new Order();
		oTest.addPersonToOrder(customer);
		SalesLineItem sli = new SalesLineItem(item, 1);
		oTest.addSalesLineItem(sli);
		Order expect = oTest;
		Order result = oTest;
		
		assertEquals(expect, result);
	}

}
