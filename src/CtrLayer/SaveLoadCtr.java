package CtrLayer;

import ModelLayer.*;
import java.net.URISyntaxException;
import java.io.*;

public class SaveLoadCtr {
	private static SaveLoad saveLoad;
	private static DataContainer dataCon;

	/**
	 * Constructor for objects of class SaveLoadCtr
	 */
	public SaveLoadCtr() {
		saveLoad = new SaveLoad();
	}

	/**
	 * Gemmer alle containere i et @DataContainer objekt, som gemmes i en
	 * Data.bin-fil
	 */
	public static void save() throws IOException {
		SaveLoad saveLoad = new SaveLoad();
		DataContainer dataCon = new DataContainer(saveLoad.getPersonContainer()
				.getContainer(), saveLoad.getItemContainer().getContainer(),
				saveLoad.getOrderContainer().getContainer());
		saveLoad.saveData(dataCon);
	}

	/**
	 * Henter containere fra Data.bin-filen, som overskriver de nuværende
	 * containere
	 */
	public static void load() throws IOException, ClassNotFoundException,
			FileNotFoundException {
		DataContainer loadData;
		loadData = saveLoad.loadData();
		saveLoad.getPersonContainer().setContainer(
				loadData.getPersonContainer());
		saveLoad.getItemContainer().setContainer(loadData.getItemContainer());
		saveLoad.getOrderContainer().setContainer(loadData.getOrderContainer());
	}

	/**
	 * Returnerer navnet på filen som @DataContainer objektet gemmes i
	 */
	public String getFileName() {
		return saveLoad.getFileName();
	}
}
