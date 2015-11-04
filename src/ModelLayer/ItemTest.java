package ModelLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {
	private Item item = new Item(321, "koben", 59.50, "Koben af Jern", Item.Location.TRAELAST, 2, 15);
	private ItemContainer itemCon;

	@Test
	public void testItem() {
	}

	@Test
	public void testGetBarcode() {
		int expect = 321;
		int result = item.getBarcode();
		assertEquals(expect, result);
	}

	@Test
	public void testSetBarcode() {
		item.setBarcode(322);
		int expect = 322;
		int result = item.getBarcode();
		assertEquals(expect, result);
	}

	@Test
	public void testGetName() {
		String expect = "koben";
		String result = item.getName();
		assertEquals(expect, result);
	}

	@Test
	public void testSetName() {
		item.setName("Jern koben");
		String expect = "Jern koben";
		String result = item.getName();
		assertEquals(expect, result);
	}

	@Test
	public void testGetPrice() {
		double expect = 59.50;
		double result = item.getPrice();
		assertEquals(expect, result,0);
	}

	@Test
	public void testSetPrice() {
		item.setPrice(65.50);
		double expect = 65.50;
		double result = item.getPrice();
		assertEquals(expect, result,0);
	}

	@Test
	public void testGetDescription() {
		String expect = "Koben af Jern";
		String result = item.getDescription();
		assertEquals(expect, result);
	}

	@Test
	public void testSetDescription() {
		item.setDescription("Koben lavet af Jern");
		String expect = "Koben lavet af Jern";
		String result = item.getDescription();
		assertEquals(expect, result);
	}

	@Test
	public void testGetWarehouse() {
		ModelLayer.Item.Location expect = ModelLayer.Item.Location.TRAELAST;
		ModelLayer.Item.Location result = item.getWarehouse();
		assertEquals(expect, result);
	}

	@Test
	public void testSetWarehouse() {
		item.setWarehouse(ModelLayer.Item.Location.BYGGEMARKED);
		ModelLayer.Item.Location expect = ModelLayer.Item.Location.BYGGEMARKED;
		ModelLayer.Item.Location result = item.getWarehouse();
		assertEquals(expect, result);
	}

	@Test
	public void testGetMinInStock() {
		int expect = 2;
		int result = item.getMinInStock();
		assertEquals(expect, result);
	}

	@Test
	public void testSetMinInStock() {
		item.setMinInStock(3);
		int expect = 3;
		int result = item.getMinInStock();
		assertEquals(expect, result);;
	}

	@Test
	public void testGetMaxInStock() {
		int expect = 15;
		int result = item.getMaxInStock();
		assertEquals(expect, result);
	}

	@Test
	public void testSetMaxInStock() {
		item.setMaxInStock(20);
		int expect = 20;
		int result = item.getMaxInStock();
		assertEquals(expect, result);
	}

	@Test
	public void testSetAmount() {
		item.setAmount(9);
		int expect = 9;
		int result = item.getAmount();
		assertEquals(expect, result);
	}

	@Test
	public void testGetAmount() {
		item.setAmount(9);
		int expect = 9;
		int result = item.getAmount();
		assertEquals(expect, result);
	}

	@Test
	public void testGetStock() {
		item.setAmount(9);
		int expect = 9;
		int result = item.getStock();
		assertEquals(expect, result);
	}

}
