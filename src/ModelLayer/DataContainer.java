package ModelLayer;

import java.util.ArrayList;
import java.io.*;

public class DataContainer implements Serializable {
	private ArrayList<Person> personsData;
	private ArrayList<Item> itemData;
	private ArrayList<Order> orderData;

	public DataContainer(ArrayList<Person> personsData,
			ArrayList<Item> itemData, ArrayList<Order> orderData) {
		this.personsData = personsData;
		this.itemData = itemData;
		this.orderData = orderData;
	}

	public ArrayList<Person> getPersonContainer() {
		return personsData;
	}

	public ArrayList<Item> getItemContainer() {
		return itemData;
	}

	public ArrayList<Order> getOrderContainer() {
		return orderData;
	}
}
