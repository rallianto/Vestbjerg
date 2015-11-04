package CtrLayer;

import static org.junit.Assert.*;

import org.junit.Test;

import ModelLayer.Customer;
import ModelLayer.Customer.Type;
import ModelLayer.Item;

public class OrderCtrTest {
	private OrderCtr oCtr;
	@Test
	public void testCreateOrder() {
		Item item = new Item(321, "koben", 59.50, "Koben af Jern", Item.Location.TRAELAST, 2, 15);
		Customer customer = new Customer("ole", "Hansvej 32", "Aalborg", 23232323, Type.PRIVATE);
		
		oCtr.createOrder();
		Item expect = item;
		Item result;
		assertEquals(expect, result);
	}

}
