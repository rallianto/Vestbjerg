package ModelLayer;

import java.io.*;

/**
 * Dette er Customer klassen, som er en subklasse til super klassen Person
 *
 * @author Gruppe 4
 */
public class Customer extends Person implements Serializable {
	/**
	 * Enum Type bliver sat med følgende muligheder: PRIVATE, ENTERPRICE
	 */
	public static enum Type {
		PRIVATE, ENTERPRICE
	}

	private Type type;

	/**
	 * Kontruktøren der tager følgende parametre fra superklassen: name,
	 * address, city, phone
	 */
	public Customer(String name, String address, String city, int phone,
			Type type) {
		super(name, address, city, phone);
		this.type = type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
}
