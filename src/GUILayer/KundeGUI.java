package GUILayer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import CtrLayer.PersonCtr;
import CtrLayer.SaveLoadCtr;
import ModelLayer.Customer;
import ModelLayer.Item;
import ModelLayer.Customer.Type;
import ModelLayer.Person;
import ModelLayer.Item.Location;

import javax.swing.border.TitledBorder;

public class KundeGUI extends JPanel {
	private PersonCtr personCtr;

	private int id_found;

	private JTextField textId;
	private JTextField textNavn;
	private JTextField textAdr;
	private JTextField textBy;
	private JTextField textTlf;

	private JButton btnOpret;
	private JButton btnOpdater;
	private JButton btnSlet;
	private JLabel lblKundenr;
	private JTextField textKundeNr;
	private JComboBox<Type> comboBox;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblType;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_2;

	// Konstrukter
	public KundeGUI() {
		setLayout(null);

		personCtr = new PersonCtr();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "S\u00F8g efter kunde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 340, 101);
		add(panel);
		panel.setLayout(null);
		
				JLabel lblFindKunde = new JLabel("Kunde nr.:");
				lblFindKunde.setBounds(12, 63, 67, 22);
				panel.add(lblFindKunde);
				lblFindKunde.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
						textId = new JTextField();
						textId.setBounds(125, 63, 116, 22);
						panel.add(textId);
						textId.setColumns(10);
						
								JButton btnFind = new JButton("Find");
								btnFind.setBounds(253, 62, 75, 25);
								panel.add(btnFind);
								
								JLabel lblIndtastKundeNr = new JLabel("Indtast kunde nr. for at s\u00F8ge efter en kunde ");
								lblIndtastKundeNr.setBounds(12, 22, 316, 31);
								panel.add(lblIndtastKundeNr);
								
								panel_1 = new JPanel();
								panel_1.setBorder(new TitledBorder(null, "Liste over alle kunder i systemet", TitledBorder.LEADING, TitledBorder.TOP, null, null));
								panel_1.setBounds(364, 12, 614, 508);
								add(panel_1);
								panel_1.setLayout(null);
								
										// Tabelen der viser det forskellige kunder
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
															findKunde(table.getValueAt(table.getSelectedRow(),
																	0).toString());
														}
													}
												});
										scrollPane = new JScrollPane(table);
										scrollPane.setBounds(12, 56, 590, 439);
										panel_1.add(scrollPane);
										
										lblNewLabel = new JLabel("Tryk p\u00E5 en kunde for opdatere eller slette kunden");
										lblNewLabel.setBounds(12, 22, 322, 31);
										panel_1.add(lblNewLabel);
										
										panel_2 = new JPanel();
										panel_2.setBorder(new TitledBorder(null, "Opret, opdater eller slet kunde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
										panel_2.setBounds(12, 126, 340, 395);
										add(panel_2);
										panel_2.setLayout(null);
										
												lblKundenr = new JLabel("Kundenr:");
												lblKundenr.setBounds(12, 30, 74, 22);
												panel_2.add(lblKundenr);
												lblKundenr.setFont(new Font("Tahoma", Font.PLAIN, 13));
												
														textKundeNr = new JTextField();
														textKundeNr.setBounds(124, 30, 116, 22);
														panel_2.add(textKundeNr);
														textKundeNr.setEditable(false);
														textKundeNr.setColumns(10);
														
																JLabel lblNavn = new JLabel("Navn:");
																lblNavn.setBounds(12, 65, 74, 22);
																panel_2.add(lblNavn);
																lblNavn.setFont(new Font("Tahoma", Font.PLAIN, 13));
																
																		textNavn = new JTextField();
																		textNavn.setBounds(124, 65, 116, 22);
																		panel_2.add(textNavn);
																		textNavn.setColumns(10);
																		
																				lblType = new JLabel("Type:");
																				lblType.setBounds(12, 100, 74, 22);
																				panel_2.add(lblType);
																				lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
																				
																						
																						// dropdown boxe til Type
																						comboBox = new JComboBox<Type>();
																						comboBox.setBounds(124, 101, 116, 20);
																						panel_2.add(comboBox);
																						comboBox.setModel(new DefaultComboBoxModel<Type>(Type.values()));
																						
																								JLabel lblAdresse = new JLabel("Adresse:");
																								lblAdresse.setBounds(12, 135, 103, 22);
																								panel_2.add(lblAdresse);
																								lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 13));
																								
																										textAdr = new JTextField();
																										textAdr.setBounds(124, 135, 116, 22);
																										panel_2.add(textAdr);
																										textAdr.setColumns(10);
																										
																												JLabel lblBy = new JLabel("By:");
																												lblBy.setBounds(12, 170, 103, 22);
																												panel_2.add(lblBy);
																												lblBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
																												
																														textBy = new JTextField();
																														textBy.setBounds(124, 170, 116, 22);
																														panel_2.add(textBy);
																														textBy.setColumns(10);
																														
																																JLabel lblTelefon = new JLabel("Telefon:");
																																lblTelefon.setBounds(12, 205, 103, 22);
																																panel_2.add(lblTelefon);
																																lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 13));
																																
																																		textTlf = new JTextField();
																																		textTlf.setBounds(124, 205, 116, 22);
																																		panel_2.add(textTlf);
																																		textTlf.setColumns(10);
																																		
																																				btnOpret = new JButton("Opret kunde");
																																				btnOpret.setBounds(45, 310, 120, 25);
																																				panel_2.add(btnOpret);
																																				
																																						btnOpdater = new JButton("Opdater kunde");
																																						btnOpdater.setBounds(177, 310, 120, 25);
																																						panel_2.add(btnOpdater);
																																						btnOpdater.addActionListener(new OpdaterListener());
																																						btnOpdater.setEnabled(false);
																																						
																																								btnSlet = new JButton("Slet kunde");
																																								btnSlet.setBounds(177, 345, 120, 25);
																																								panel_2.add(btnSlet);
																																								btnSlet.addActionListener(new SletListener());
																																								btnSlet.setEnabled(false);
																																								
																																										JButton btnRydFelterne = new JButton("Ryd felterne");
																																										btnRydFelterne.setBounds(45, 345, 120, 25);
																																										panel_2.add(btnRydFelterne);
																																										btnRydFelterne.addActionListener(new ActionListener() {
																																											public void actionPerformed(ActionEvent arg0) {
																																												rydFelter();
																																											}
																																										});
																																				btnOpret.addActionListener(new OpretListener());
								btnFind.addActionListener(new FindListener());

	}

	
	//og den her også for at updatere tabel
	public void updateTable() {
		ArrayList<Person> persons = personCtr.getPersons();
		int index = 0;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Kundenr");
		model.addColumn("Navn");
		model.addColumn("Type");
		model.addColumn("Addresse");
		model.addColumn("By");
		model.addColumn("Telefon nr.");
		while (index < persons.size()) {
			Person person = persons.get(index);
			if (person instanceof Customer) {
				model.addRow(new Object[] { person.getID(), person.getName(),
						((Customer) person).getType(), person.getAddress(),
						person.getCity(), person.getPhone() });
			}
			index++;
		}
		table.setModel(model);
	}

	private class FindListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			findKunde(textId.getText());
		}
	}

	private class OpretListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			opretKunde();
		}
	}

	private class OpdaterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			opdaterKunde();
		}
	}

	private class SletListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sletKunde();
		}
	}

	private void sletKunde() {
		if (personCtr.deletePerson(id_found))
		{
			MainGUI.setLastAction("Der er slettet en person med ID: " + id_found);
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
			MainGUI.setLastAction("Personen kunne af mystiske årsager ikke slettes!");
		}
	}

	private void opdaterKunde() {
		if (!textNavn.getText().trim().isEmpty()
				&& !textAdr.getText().trim().isEmpty()
				&& !textBy.getText().trim().isEmpty()
				&& !textTlf.getText().trim().isEmpty()) {
			if (MainGUI.isNumeric(textTlf.getText())) {
				try {
					int tlf = Integer.parseInt(textTlf.getText());
					Person p = personCtr.updatePerson(textNavn.getText(), tlf,
							textAdr.getText(), textBy.getText(),
							comboBox.getSelectedItem(), id_found);
					MainGUI.setLastAction("Der er opdateret en person med ID: "
							+ p.getID());
					try {
						SaveLoadCtr.save();
					} catch (IOException e) {
						e.printStackTrace();
					}
					rydFelter();
				} catch (NumberFormatException e) {
					MainGUI.setLastAction("Telefon nr. er ikke et tal!");
				}
			} else {
				MainGUI.setLastAction("Telefon nr. er ikke et numerisk!");
			}
		} else {
			MainGUI.setLastAction("Alle felter skal være udfyldt for at opdater en person!");
		}
	}

	private void findKunde(String input) {
		if (input != null) {
			if (MainGUI.isNumeric(input)) {
				try {
					int id = Integer.parseInt(input);
					Customer p = (Customer) personCtr.findPerson(id);
					if (p != null) {
						MainGUI.setLastAction("Person med ID: " + p.getID()
								+ " blev fundet!");
						textKundeNr.setText("" + p.getID());
						textNavn.setText(p.getName());
						textAdr.setText(p.getAddress());
						textBy.setText(p.getCity());
						textTlf.setText("" + p.getPhone());
						comboBox.setSelectedItem(p.getType());
						btnOpdater.setEnabled(true);
						btnSlet.setEnabled(true);
						id_found = p.getID();
						textId.setText("");
					} else {
						MainGUI.setLastAction("Der blev ikke fundet nogen person med ID: "
								+ id);
						rydFelter();
					}

				} catch (NumberFormatException e) {
					MainGUI.setLastAction("ID skal udfyldes!");
					rydFelter();
				}
			} else {
				MainGUI.setLastAction("ID skal være et tal!");
				rydFelter();
			}
		} else {
			MainGUI.setLastAction("Intet ID indtastet!");
			rydFelter();
		}
	}

	private void opretKunde() {
		if (!textNavn.getText().trim().isEmpty()
				&& !textAdr.getText().trim().isEmpty()
				&& !textBy.getText().trim().isEmpty()
				&& !textTlf.getText().trim().isEmpty()) {
			if (MainGUI.isNumeric(textTlf.getText())) {
				try {
					int tlf = Integer.parseInt(textTlf.getText());
					Person p = personCtr.createCustomer(textNavn.getText(),
							tlf, textAdr.getText(), textBy.getText(),
							(Type) comboBox.getSelectedItem());
					MainGUI.setLastAction("Der er oprettet en person med ID: "
							+ p.getID());
					try {
						SaveLoadCtr.save();
					} catch (IOException e) {
						e.printStackTrace();
					}
					rydFelter();
				} catch (NumberFormatException e) {
					MainGUI.setLastAction("Telefon nr. er ikke et tal!");

				}
			} else {
				MainGUI.setLastAction("Telefon nr. er ikke et numerisk!");
			}
		} else {
			MainGUI.setLastAction("Alle felter skal udfyldes for at oprette en person!");
		}

	}

	private void rydFelter() {
		textNavn.setText("");
		textKundeNr.setText("");
		textAdr.setText("");
		textBy.setText("");
		textTlf.setText("");
		textId.setText("");
		btnOpdater.setEnabled(false);
		btnSlet.setEnabled(false);
		updateTable();
	}
}
