package CtrLayer;

import java.util.ArrayList;
import java.util.ListIterator;

import ModelLayer.*;

/**
 * Dette er PersonCtr klassen som bliver brugt til at kalde person metoder fra
 * Model laget og sende informationerne op til UILaget
 * 
 * @author Gruppe 4
 */
public class PersonCtr {
	private PersonContainer pCon;

	/**
	 * Kontruktøren der henter ArrayListen der holder på personer
	 */
	public PersonCtr() {
		pCon = PersonContainer.getInstance();
	}

	
	// bruger brugt til Jtable
	public ArrayList<Person> getPersons() {
		return pCon.getContainer();
	}

	/**
	 * Opretter et Employee objekt og tilføjer det til containeren. Retunere
	 * personens ID
	 */
	public Person createEmployee(String name, int phone, String address,
			String city, Employee.Type type) {
		Person p = new Employee(name, address, city, phone, type);
		pCon.addPerson(p);
		return p;
	}

	/**
	 * Opretter et Customer objekt og tilføjer det til containeren. Retunere
	 * personens ID
	 */
	public Person createCustomer(String name, int phone, String address,
			String city, Customer.Type type) {
		Person p = new Customer(name, address, city, phone, type);
		pCon.addPerson(p);
		return p;
	}

	/**
	 * Returnere et Person objekt baseret på ID, returnere null hvis objektet
	 * ikke findes
	 */
	public Person findPerson(int ID) {
		Person returnPerson = null;
		Person iterPerson = null;
		int index = 0;
		boolean found = false;
		while (index < pCon.getSize() && !found) {
			iterPerson = pCon.getContainer().get(index);
			if (iterPerson.getID() == ID) {
				returnPerson = iterPerson;
				found = true;
			} else {
				index++;
			}
		}
		return returnPerson;
	}

	/**
	 * Updater Person information baseret på ID. Returner person objektet.
	 * Retuner null hvis person ikke findes
	 */
	public Person updatePerson(String name, int phone, String address,
			String city, Object type, int ID) {
		Person person = null;
		if (findPerson(ID) != null) {
			person = findPerson(ID);
			person.setName(name);
			person.setPhone(phone);
			person.setAddress(address);
			person.setCity(city);
			if (person instanceof Customer) {
				((Customer) person).setType((Customer.Type) type);
			}
		}
		return person;
	}

	/**
	 * Fjerner et Person objekt fra Person container
	 */
	public boolean deletePerson(int ID) {
		boolean status = false;
		boolean found = false;
		ListIterator<Person> list = pCon.getContainer().listIterator();
		while (list.hasNext() && !found) {
			if (list.next().getID() == ID) {
				list.remove();
				status = true;
				found = true;
			}
		}
		return status;
	}
}