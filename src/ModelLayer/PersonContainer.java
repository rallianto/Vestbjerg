package ModelLayer;

import java.util.ArrayList;
import java.io.*;

/**
 * Dette er PersonContainer klassen, som er en singleton og bliver brugt til at
 * holde på personer.
 *
 * @author Gruppe 4
 */
public class PersonContainer implements Serializable
{
	private ArrayList<Person> pCon;
	private static PersonContainer instance = null;

	/**
	 * Kontruktøren der laver et nyt ArrayList object
	 */
	private PersonContainer() {
		pCon = new ArrayList<Person>();
	}

	/**
	 * Denne metode kalder konstruktøren. Dette benytter singleton mønstrert
	 */
	public static PersonContainer getInstance() {
		if (instance == null) {
			instance = new PersonContainer();
		}
		return instance;
	}

	/**
	 * Tilføjer et Person objekt til arraylisten pCon
	 */
	public void addPerson(Person p)
	{
		int i = 0;
		for(Person item : pCon)
		{
			if (item.getID() > i)
			{
				i = item.getID();
			}
		}
		p.setID(i + 1);
		pCon.add(p);
	}

	/**
	 * Returnere size på ArrayListen pCon
	 */
	public int getSize() {
		return pCon.size();
	}

	/**
	 * Returnere container som er af typen ArrayList<Person>
	 */
	public ArrayList<Person> getContainer() {
		return pCon;
	}

	/**
	 * Sætter ArrayListen der holder på personer
	 */
	public void setContainer(ArrayList<Person> pCon) {
		this.pCon = pCon;
	}

}