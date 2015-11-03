package GUILayer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CtrLayer.ItemCtr;
import CtrLayer.SaveLoadCtr;
import ModelLayer.Item;
import ModelLayer.Item.Location;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

public class LagerGUI extends JPanel {
	
	private int barcode_found;

	private ItemCtr itemCtr;
	private JTextField textBarcode;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textMinInStock;
	private JTextField textMaxInStock;
	private JTextArea textDescription;

	private JButton btnOpret;
	private JButton btnOpdater;
	private JButton btnSlet;
	private JScrollPane scrollPane;
	private JTable table;
	private JComboBox<Location> comboBox;
	private JLabel label;
	private JPanel panel;
	private JLabel lblIndtastEnStregkode;
	private JPanel panel_1;
	private JLabel lblStregkode;
	private JTextField textStregkodeInput;
	private JLabel lblTrykPEn;

	// Konstruktør
	public LagerGUI()
	{
		setLayout(null);

		itemCtr = new ItemCtr();
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "S\u00F8g efter vare", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 340, 101);
		add(panel);
		panel.setLayout(null);
		
		// FIND VARE ////////
		JLabel lblFindItem = new JLabel("Stregkode:");
		lblFindItem.setBounds(12, 63, 103, 22);
		panel.add(lblFindItem);
		lblFindItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textBarcode = new JTextField();
		textBarcode.setBounds(125, 63, 116, 22);
		panel.add(textBarcode);
		textBarcode.setColumns(10);
						
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(253, 62, 75, 25);
		panel.add(btnFind);
		
		lblIndtastEnStregkode = new JLabel("Indtast en stregkode for at s\u00F8ge p\u00E5 en vare");
		lblIndtastEnStregkode.setBounds(12, 22, 320, 31);
		panel.add(lblIndtastEnStregkode);
								
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Opret, opdater eller slet vare", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 126, 340, 395);
		add(panel_1);
										panel_1.setLayout(null);
								
		// NAVN PÅ VARE //////
		JLabel lblNavn = new JLabel("Navn:");
		lblNavn.setBounds(12, 30, 33, 22);
		panel_1.add(lblNavn);
		lblNavn.setFont(new Font("Tahoma", Font.PLAIN, 13));
										
		// TEXT FELTER ////
		textName = new JTextField();
		textName.setBounds(124, 30, 116, 22);
		panel_1.add(textName);
		textName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Pris:");
		lblPrice.setBounds(12, 65, 103, 22);
		panel_1.add(lblPrice);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		label = new JLabel("Lokation:");
		label.setBounds(12, 100, 103, 22);
		panel_1.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblMinInStock = new JLabel("Min. p\u00E5 lager:");
		lblMinInStock.setBounds(12, 135, 121, 22);
		panel_1.add(lblMinInStock);
		lblMinInStock.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
		JLabel lblMaxInStock = new JLabel("Max. p\u00E5 lager:");
		lblMaxInStock.setBounds(12, 170, 121, 22);
		panel_1.add(lblMaxInStock);
		lblMaxInStock.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
		JLabel lblDescription = new JLabel("Beskrivelese:");
		lblDescription.setBounds(12, 239, 103, 22);
		panel_1.add(lblDescription);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
								
		textPrice = new JTextField();
		textPrice.setBounds(124, 65, 116, 22);
		panel_1.add(textPrice);
		textPrice.setColumns(10);
		
		comboBox = new JComboBox<Location>();
		comboBox.setBounds(124, 101, 116, 20);
		panel_1.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<Location>(Location.values()));
		
		textMinInStock = new JTextField();
		textMinInStock.setBounds(124, 135, 116, 22);
		panel_1.add(textMinInStock);
		textMinInStock.setColumns(10);
						
		textMaxInStock = new JTextField();
		textMaxInStock.setBounds(124, 170, 116, 22);
		panel_1.add(textMaxInStock);
		textMaxInStock.setColumns(10);
																		
		textDescription = new JTextArea();
		textDescription.setBounds(124, 240, 116, 50);
		panel_1.add(textDescription);
		textDescription.setBorder(new LineBorder(SystemColor.controlShadow));
		textDescription.setRows(3);
		textDescription.setColumns(10);
				
		btnOpret = new JButton("Opret vare");
		btnOpret.setBounds(45, 310, 120, 25);
		panel_1.add(btnOpret);
		
		btnOpdater = new JButton("Opdater vare");
		btnOpdater.setBounds(177, 310, 120, 25);
		panel_1.add(btnOpdater);
		btnOpdater.addActionListener(new OpdaterListener());
		btnOpdater.setEnabled(false);
																		
		btnSlet = new JButton("Slet vare");
		btnSlet.setBounds(177, 345, 120, 25);
		panel_1.add(btnSlet);
		btnSlet.addActionListener(new deleteListener());
		btnSlet.setEnabled(false);
		
		JButton btnRydFelterne = new JButton("Ryd felterne");
		btnRydFelterne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rydFelter();
			}
		});
		btnRydFelterne.setBounds(45, 345, 120, 25);
		panel_1.add(btnRydFelterne);
		
		lblStregkode = new JLabel("Stregkode:");
		lblStregkode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStregkode.setBounds(12, 204, 103, 22);
		panel_1.add(lblStregkode);
		
		textStregkodeInput = new JTextField();
		textStregkodeInput.setBounds(124, 205, 116, 22);
		panel_1.add(textStregkodeInput);
		textStregkodeInput.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Liste over alle vare(r) i systemet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(364, 12, 614, 509);
		add(panel_2);
		panel_2.setLayout(null);
																																						
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (table.getSelectedRow() != -1) {
							findBarcode(table.getValueAt(
									table.getSelectedRow(), 0).toString());
						}
					}
				});
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 56, 590, 439);
		panel_2.add(scrollPane);
		
		lblTrykPEn = new JLabel("Tryk p\u00E5 en vare for at opdatere eller slette varen");
		lblTrykPEn.setBounds(12, 22, 337, 31);
		panel_2.add(lblTrykPEn);
		btnOpret.addActionListener(new OpretListener());
		btnFind.addActionListener(new FindListener());

	}

	/*
	 * Opdaterer tabellen med varer
	 */
	public void updateTable() {
		ArrayList<Item> items = itemCtr.getItems();
		int index = 0;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Stregkode");
		model.addColumn("Navn");
		model.addColumn("Lokation");
		model.addColumn("Pris");
		model.addColumn("Min. lager");
		model.addColumn("Max lager");
		while (index < items.size()) {
			Item item = items.get(index);
			model.addRow(new Object[] { item.getBarcode(), item.getName(),
					item.getWarehouse(), item.getPrice(), item.getMinInStock(),
					item.getMaxInStock() });
			index++;
		}
		table.setModel(model);
	}

	// Find Barcode Listener
	private class FindListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (textBarcode.getText() != null) {
				findBarcode(textBarcode.getText());
			} else {
				MainGUI.setLastAction("Ingen stregkode indtastet!");
				textName.setText("");
				textPrice.setText("");
				textMinInStock.setText("");
				textMaxInStock.setText("");
				textDescription.setText("");
			}
		}
	}

	// Find Barcode Metode
	private void findBarcode(String input) {
		boolean clear = true;
		if (textBarcode.getText() != null) {
			if (MainGUI.isNumeric(input)) {
				try
				{
					int barcode = Integer.parseInt(input);
					if (itemCtr.findItem(barcode) != null) {
						Item i = itemCtr.findItem(barcode);
						MainGUI.setLastAction("Varen med stregkoden: " + i.getBarcode() + " blev fundet!");
						clear = false;
						textStregkodeInput.setText(new Integer(i.getBarcode()).toString());
						textName.setText(i.getName());
						textPrice.setText(new Double(i.getPrice()).toString());
						textMinInStock.setText(new Integer(i.getMinInStock()).toString());
						comboBox.setSelectedItem(i.getWarehouse());
						textMaxInStock.setText(new Integer(i.getMaxInStock()).toString());
						textDescription.setText(i.getDescription());
						barcode_found = i.getBarcode();
						btnOpdater.setEnabled(true);
						btnSlet.setEnabled(true);
					}
					else
					{
						MainGUI.setLastAction("Der blev ikke fundet nogen vare med stregkoden: " + barcode);
					}
				} catch (NumberFormatException e) {
					MainGUI.setLastAction("Stregkoden er ikke et tal!");
				}
			} else {
				MainGUI.setLastAction("Stregkoden skal være et tal!");
			}
		}
		if (clear) {
			textName.setText("");
			textPrice.setText("");
			textMinInStock.setText("");
			textMaxInStock.setText("");
			textDescription.setText("");
			btnOpdater.setEnabled(false);
			btnSlet.setEnabled(false);
		}
	}

	// herfra starter opret vare
	// kan du læse det jeg skriver her
	// herfra hvis du laver det han har lige optimeret koden for opret vare
	private class OpretListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			opretVare();
		}
	}

	private void opretVare() {
		if (!textName.getText().trim().isEmpty()
				&& !textPrice.getText().trim().isEmpty()
				&& !textDescription.getText().trim().isEmpty()
				&& !textMinInStock.getText().trim().isEmpty()
				&& !textMaxInStock.getText().trim().isEmpty()) {
			if (MainGUI.isNumeric(textMinInStock.getText())
					&& MainGUI.isNumeric(textMaxInStock.getText())) {
				try {
					int barcode = Integer.parseInt(textStregkodeInput.getText());
					double price = Double.parseDouble(textPrice.getText());
					int minInStock = Integer.parseInt(textMinInStock.getText());
					int maxInStock = Integer.parseInt(textMaxInStock.getText());
					Location location = (Location) comboBox.getSelectedItem();
					Item i = itemCtr.createItem(barcode, textName.getText(),
							price, textDescription.getText(), location,
							minInStock, maxInStock);
					MainGUI.setLastAction("Der er oprettet en vare med stregkoden: "
							+ i.getBarcode());
					try {
						SaveLoadCtr.save();
					} catch (IOException e) {
						MainGUI.setLastAction("Databasen kunne ikke gemmes");
					}
					rydFelter();
				} catch (NumberFormatException e) {
					MainGUI.setLastAction("Stregkoden er ikke et tal!");
				}
			} else {
				MainGUI.setLastAction("Min og max på lager er ikke numerisk!");
			}
		} else {
			MainGUI.setLastAction("Alle felter skal udfyldes for at oprette en vare!");
		}
	}

	// Listener til Opdatere knappen
	private class OpdaterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			opdaterVare();
		}
	}

	// Metoden til Opdatere vare
	private void opdaterVare() {
		if (!textName.getText().trim().isEmpty()
				&& !textPrice.getText().trim().isEmpty()
				&& !textDescription.getText().trim().isEmpty()
				&& !textMinInStock.getText().trim().isEmpty()
				&& !textMaxInStock.getText().trim().isEmpty()) {
			if (MainGUI.isNumeric(textMinInStock.getText())
					&& MainGUI.isNumeric(textMaxInStock.getText())) {
				try {
					double price = Double.parseDouble(textPrice.getText());
					int minInStock = Integer.parseInt(textMinInStock.getText());
					int maxInStock = Integer.parseInt(textMaxInStock.getText());
					int barcode = Integer.parseInt(textStregkodeInput.getText());
					Location location = (Location) comboBox.getSelectedItem();
					Item i = itemCtr.updateItem(barcode, textName.getText(), price, textDescription.getText(), location, minInStock, maxInStock);
					MainGUI.setLastAction("Der er opdateret en vare med stregkoden: " + i.getBarcode());
					try {
						SaveLoadCtr.save();
					} catch (IOException e) {
						MainGUI.setLastAction("Databasen kunne ikke gemmes");
					}
					rydFelter();
				} catch (NumberFormatException e) {
					MainGUI.setLastAction("barcode nr. er ikke et tal!");
				}
			} else {
				MainGUI.setLastAction("Mininstock eller maxinstock er ikke et numerisk!");
			}
		} else {
			MainGUI.setLastAction("Alle felter skal være udfyldt for at opdater en vare!");
		}
	}
	private class deleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteItem();
		}
	}
	
	public void deleteItem()
	{
		if (itemCtr.deleteItem(barcode_found))
		{
			MainGUI.setLastAction("Der er slettet en vare med stregkode: " + barcode_found);
			rydFelter();
			try
			{
				SaveLoadCtr.save();
			}
			catch (IOException e)
			{
				MainGUI.setLastAction("Databasen kunne ikke gemmes");
			}
		}
		else
		{
			MainGUI.setLastAction("Varen kunne af mystiske årsager ikke slettes!");
		}
	}
	
	private void rydFelter() {
		textBarcode.setText("");
		textStregkodeInput.setText("");
		textName.setText("");
		textPrice.setText("");
		textDescription.setText("");
		textMinInStock.setText("");
		textMaxInStock.setText("");
		btnOpdater.setEnabled(false);
		btnSlet.setEnabled(false);
		updateTable();
	}
	
	public JTable getTabel()
	{
		return table;
	}
}
