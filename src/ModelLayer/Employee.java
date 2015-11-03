package ModelLayer;

import java.io.*;

/**
 * Dette er Employee klassen, som er en subklasse til super klassen Person
 * 
 * @author Gruppe 4
 */
public class Employee extends Person implements Serializable {
	/**
	 * Enum Type bliver sat med følgende muligheder: EMPLOYEE, MANAGER
	 */
	public static enum Type {
		EMPLOYEE, MANAGER
	}

	private Type type;

	/**
	 * Kontruktøren der tager følgende parametre fra superklassen: name,
	 * address, city, phone
	 */
	public Employee(String name, String address, String city, int phone,
			Type type) {
		super(name, address, city, phone);
		this.type = type;
	}
}
