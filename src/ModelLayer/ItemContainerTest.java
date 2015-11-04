package ModelLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemContainerTest {
	private ItemContainer itemCon = ItemContainer.getInstance();

	@Test
	public void testAddItem() {
		Item item = new Item(321, "koben", 59.50, "Koben af Jern", Item.Location.TRAELAST, 2, 15); 
		itemCon.addItem(item);
		Item expect = item;
		Item result = null;
		for(int i = 0; i < itemCon.getContainer().size(); i++)
		{
			if(itemCon.getContainer().get(i).equals(item))
			{
				result = itemCon.getContainer().get(i);
			}
		}
		assertEquals(expect, result);
	}

	@Test
	public void testGetSize() {
		int expect = 1;
		int result = itemCon.getSize();
		assertEquals(expect, result);
	}

}
