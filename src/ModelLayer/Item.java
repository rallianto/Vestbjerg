package ModelLayer;

import java.io.Serializable;

/**
 * Dette er Item klassen, som bliver brugt til håndtering af varer.
 * 
 * @author Gruppe 4
 */
public class Item implements Serializable {
	public enum Location {
		TRAELAST, BYGGEMARKED
	}

	private int amount;
	private int barcode;
	private String name;
	private double price;
	private String description;
	private Location warehouse;
	private int minInStock;
	private int maxInStock;

	/**
	 * Kontruktøren der sætter følgende parametre: barcode, name, price,
	 * description, warehouse, minInStock, maxInStock
	 */
	public Item(int barcode, String name, double price, String description,
			Location warehouse, int minInStock, int maxInStock) {
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.description = description;
		this.warehouse = warehouse;
		this.minInStock = minInStock;
		this.maxInStock = maxInStock;
	}

	/**
	 * Returnere strejkoden på varen
	 */
	public int getBarcode() {
		return barcode;
	}

	/**
	 * Sætter strejkoden på varen
	 */
	public void setBarcode(int Barcode) {
		this.barcode = Barcode;
	}

	/**
	 * Returnere navnet på varen
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sætter navnet på varen
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returnere prisen på varen
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sætter prisen på varen
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Returnere beskrivelse på varen
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sætter beskrivelsen på varen
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returnere afdelingen varen er i
	 */
	public Location getWarehouse() {
		return warehouse;
	}

	/**
	 * Sætter afdelingen varen er i
	 */
	public void setWarehouse(Location warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * Returnere hvad der minimum kan være på lageret
	 */
	public int getMinInStock() {
		return minInStock;
	}

	/**
	 * Sætter hvad der minimum kan være på lageret
	 */
	public void setMinInStock(int minInStock) {
		this.minInStock = minInStock;
	}

	/**
	 * Returnere hvad der maximum kan være på lageret
	 */
	public int getMaxInStock() {
		return maxInStock;
	}

	/**
	 * Sætter hvad der maximum kan være på lageret
	 */
	public void setMaxInStock(int maxInStock) {
		this.maxInStock = maxInStock;
	}

	/**
	 * Returnere antallet af denne vare der er
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sætter antallet af denne vare der er
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Returnere antallet af denne vare der er
	 */
	public int getStock() {
		return amount;
	}
}
