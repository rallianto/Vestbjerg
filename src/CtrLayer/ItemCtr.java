package CtrLayer;

import java.util.ArrayList;
import java.util.ListIterator;

import ModelLayer.*;

/**
 * Dette er ItemCtr klassen som bliver brugt til at kalde vare metoder fra Model
 * laget og sende informationerne op til UILaget
 * 
 * @author Gruppe 4
 */
public class ItemCtr {
	private ItemContainer iCon;

	/**
	 * Kontruktøren der henter ArrayListen der holder på varer
	 */
	public ItemCtr() {
		iCon = ItemContainer.getInstance();
	}

	public ArrayList<Item> getItems() {
		return iCon.getContainer();
	}

	/**
	 * Opretter et vare objekt og tilføjer det til containeren. Retunere varen
	 */
	public Item createItem(int barcode, String name, double price,
			String description, Item.Location warehouse, int minInStock,
			int maxInStock) {
		Item i = new Item(barcode, name, price, description, warehouse,
				minInStock, maxInStock);
		iCon.addItem(i);
		return i;
	}

	/**
	 * Returnere et Item objekt baseret på barcode, returnere null hvis
	 * objektet ikke findes
	 */
	public Item findItem(int barcode) {
		Item returnItem = null;
		Item iterItem = null;
		int index = 0;
		boolean found = false;
		while (index < iCon.getSize() && !found) {
			iterItem = iCon.getContainer().get(index);
			if (iterItem.getBarcode() == barcode) {
				returnItem = iterItem;
				found = true;
			} else {
				index++;
			}
		}
		return returnItem;
	}

	/**
	 * Updater vare information
	 */
	public Item updateItem(int barcode, String name, double price,
			String description, Item.Location warehouse, int minInStock,
			int maxInStock) {
		Item item = null;
		if (findItem(barcode) != null) {
			Item currentItem = findItem(barcode);
			currentItem.setBarcode(barcode);
			currentItem.setName(name);
			currentItem.setPrice(price);
			currentItem.setDescription(description);
			currentItem.setWarehouse(warehouse);
			currentItem.setMinInStock(minInStock);
			currentItem.setMaxInStock(maxInStock);
			item = findItem(barcode);
		}
		return item;
	}

	/**
	 * Fjerner et vare objekt fra vare containeren
	 */
	public boolean deleteItem(int barcode) {
		boolean status = false;
		boolean found = false;
		ListIterator<Item> list = iCon.getContainer().listIterator();
		while (list.hasNext() && !found) {
			if (list.next().getBarcode() == barcode) {
				list.remove();
				status = true;
				found = true;
			}
		}
		return status;
	}

	/**
	 * Tilføj/fjern vare(r) Returner -1 hvis varer ikke findes med stregkode
	 * Returner 0 hvis der forsøges at fjerne flere varer end der er på lager
	 * Returner 1 hvis varer er succesfuldt fjernet Returner 2 hvis varer
	 * succesfuldt er tilføjet
	 */
	public int updateStock(int barcode, int amount) {
		int status = -1;
		// Hvis item findes
		if (findItem(barcode) != null) {
			Item item = findItem(barcode);
			// Hvis amount er negativ
			if (Math.signum(amount) == Math.signum(-1)) {
				// Hvis der fjernes flere varer end der er på lager
				if (Math.abs(amount) > item.getAmount()) {
					status = 0;
				}
				// Hvis der er varer på lager
				else if (Math.abs(amount) <= item.getAmount()) {
					item.setAmount(item.getAmount() - Math.abs(amount));
					status = 1;
				}
			}
			// Hvis amount er positiv
			else if (Math.signum(amount) == Math.signum(1)) {
				item.setAmount(item.getAmount() + amount);
				status = 2;
			}
		}
		return status;
	}
}