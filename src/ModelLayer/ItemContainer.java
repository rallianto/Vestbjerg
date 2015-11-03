package ModelLayer;

import java.util.ArrayList;
import java.io.*;

/**
 * Dette er ItemContainer klassen, som er en singleton og bliver brugt til at
 * holde på vare.
 *
 * @author Gruppe 4
 */
public class ItemContainer implements Serializable {
	private ArrayList<Item> iCon;
	private static ItemContainer instance = null;

	/**
	 * Kontruktøren der laver et nyt ArrayList object
	 */
	private ItemContainer() {
		iCon = new ArrayList<Item>();
	}

	/**
	 * Denne metode kalder konstruktøren. Dette benytter singleton mønstrert
	 */
	public static ItemContainer getInstance() {
		if (instance == null) {
			instance = new ItemContainer();
		}
		return instance;
	}

	/**
	 * Tilføjer et Person objekt til arraylisten iCon
	 */
	public void addItem(Item i) {
		iCon.add(i);
	}

	/**
	 * Returnere size på ArrayListen iCon
	 */
	public int getSize() {
		return iCon.size();
	}

	/**
	 * Returnere container som er af typen ArrayList<Item>
	 */
	public ArrayList<Item> getContainer() {
		return iCon;
	}

	/**
	 * Sætter ArrayListen der holder på varer
	 */
	public void setContainer(ArrayList<Item> iCon) {
		this.iCon = iCon;
	}

}