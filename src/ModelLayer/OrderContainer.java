package ModelLayer;

import java.util.ArrayList;
import java.io.*;

/**
 * Dette er OrderContainer klassen, som er en singleton og bliver brugt til at
 * holde på ordre.
 * 
 * @author Gruppe 4
 */
public class OrderContainer implements Serializable {
	private ArrayList<Order> oCon;
	private static OrderContainer instance = null;

	/**
	 * Kontruktøren der laver et nyt ArrayList object
	 */
	private OrderContainer() {
		oCon = new ArrayList<Order>();
	}

	/**
	 * Denne metode kalder konstruktøren. Dette benytter singleton mønstrert
	 */
	public static OrderContainer getInstance() {
		if (instance == null) {
			instance = new OrderContainer();
		}
		return instance;
	}

	/**
	 * Tilføjer et Order objekt til arraylisten oCon
	 */
	public void addOrder(Order o)
	{
		int i = 0;
		for(Order item : oCon)
		{
			if (item.getID() > i)
			{
				i = item.getID();
			}
		}
		o.setID(i + 1);
		oCon.add(o);
	}

	/**
	 * Returnere size på ArrayListen oCon
	 */
	public int getSize() {
		return oCon.size();
	}

	/**
	 * Returnere container som er af typen ArrayList<Order>
	 */
	public ArrayList<Order> getContainer() {
		return oCon;
	}

	/**
	 * Definere ArrayListen oCon
	 */
	public void setContainer(ArrayList<Order> oCon) {
		this.oCon = oCon;
	}

}