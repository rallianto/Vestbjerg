package UILayer;

import java.util.Scanner;

import CtrLayer.*;
import ModelLayer.*;

/**
 * Dette er PersonUI klassen som er person menuen
 * 
 * @author Gruppe 4
 */
public class PersonUI {
	private PersonCtr personCtr;
	private InputManager inputManager;

	/**
	 * Kontruktøren der kalder inputManageren samt person controlleren
	 */
	public PersonUI() {
		personCtr = new PersonCtr();
		inputManager = InputManager.getInstance();
	}

	/**
	 * Printer Person menuen og returnere dit valg
	 */
	private int printPersonMenu() {
		inputManager.clearConsole();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Sidste handling: " + inputManager.getLastAction());
		System.out.println(" Person Menu");
		System.out.println("");
		System.out.println(" (1) Opret Person");
		System.out.println(" (2) Find Person");
		System.out.println(" (3) Opdater Person");
		System.out.println(" (4) Slet Person");
		System.out.println(" (0) Gå tilbage til hovedmenu");
		System.out.println("\n Vælg en af disse: ");
		System.out.println("");

		int choise;

		while (!keyboard.hasNextInt()) {
			System.out.println("Det er ikke et tal!");
			keyboard.next();
		}
		choise = keyboard.nextInt();
		return choise;
	}

	/**
	 * kalder metoden printPersonMenu og navigere dig videre baseret på det
	 * valg den returnere
	 */
	public void personMenu() {
		boolean exit = false;
		while (!exit) {
			int choise = printPersonMenu();
			switch (choise) {
			case 1:
				// Opret kunde
				createPerson();
				break;

			case 2:
				// Find person
				findPerson();
				break;

			case 3:
				updatePerson();
				break;

			case 4:
				deletePerson();
				break;

			case 0:
				exit = true;
				break;

			default:
				System.out.println("Forstår ikke hvad du mener!");
				break;
			}
		}

	}

	/**
	 * Opretter en Person
	 */
	private void createPerson() {
		String name = inputManager.inputString("Navn: ", false);
		int phone = inputManager.inputInteger("Telefon nr.:");
		String address = inputManager.inputString("Adresse: ", false);
		String city = inputManager.inputString("By:", false);
		// Opretter en ny person i model laget igennem kontrol laget
		Person p = personCtr.createCustomer(name, phone, address, city,
				ModelLayer.Customer.Type.PRIVATE);
		if (p != null) {
			inputManager.setLastAction("Der er oprettet en ny kunde med ID: "
					+ p.getID());
		} else {
			System.out.println("Det lykkedes ikke at oprette en ny kunde");
		}
	}

	/**
	 * Finder en Person baseret på ID
	 */
	private void findPerson() {
		int requestID = inputManager
				.inputInteger("Indtast ID for person du ønsker at finde: ");
		if (personCtr.findPerson(requestID) != null) {
			Person person = personCtr.findPerson(requestID);
			inputManager.setLastAction("Der er fundet en person med navn: "
					+ person.getName());
		} else {
			inputManager
					.setLastAction("Det lykkedes ikke at finde en person med ID: "
							+ requestID);
		}
	}

	/**
	 * Denne metode bruges til at ændre i variablerne knyttet til en person i
	 * personContainer
	 */
	private void updatePerson() {
		int ID = inputManager.inputInteger("Indtast ID for person: ");
		if (personCtr.findPerson(ID) != null) {
			Person person = personCtr.findPerson(ID);
			// Print nuv�rerende info om person
			System.out.println("\n" + "Der er fundet en person: "
					+ person.getName() + "\n" + "Telefon: " + person.getPhone()
					+ "\n" + "Adresse: " + person.getAddress() + "\n" + "By: "
					+ person.getCity());
			// Modtag input for nye info om person
			String name = inputManager.inputString("Navn ændres til: ", false);
			int tlf = inputManager.inputInteger("Telefon nr ændres til: ");
			String adresse = inputManager.inputString("Adresse ændres til:",
					false);
			String by = inputManager.inputString("By ændres til: ", false);
			// Opdater person info
			Person updatePerson = personCtr.updatePerson(name, tlf, adresse,
					by, person, ID);
			// Skriv i konsollen
			inputManager.setLastAction("Der er opdateret en kunde: "
					+ updatePerson.getName() + "\n" + "Telefon: "
					+ updatePerson.getPhone() + "\n" + "Adresse: "
					+ updatePerson.getAddress() + "\n" + "By: "
					+ updatePerson.getCity());
		} else {
			inputManager
					.setLastAction("Det lykkedes ikke at opdater info om en kunde!");
		}
	}

	/**
	 * Slet en person baseret på ID
	 */
	private void deletePerson() {
		int ID = inputManager.inputInteger("Indtast ID: ");
		if (personCtr.deletePerson(ID)) {
			inputManager.setLastAction("Person med ID " + ID + " er slettet");
		} else {
			inputManager.setLastAction("Person med ID " + ID
					+ " blev ikke fundet");
		}
	}
}