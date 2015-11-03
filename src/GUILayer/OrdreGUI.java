package GUILayer;
import CtrLayer.*;
import ModelLayer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.io.IOException;

public class OrdreGUI extends JPanel
{
	private OrderCtr orderCtr;
	
	private JButton btnFindItem;
	private JButton btnSogEfterKunde;
	private JButton btnAfslutOrdre;
	private JButton btnOpdaterAntal;
	private JButton btnTilfojVare;
	
	private JTextField textBarcode;
	private JTextField textAntal;
	private JTextField textKundeID;
	private JTextField textOpdaterAntal;
	
	private JTable table_ordre;
		
	private JScrollPane scrollPane;
	
	private JLabel lblAktuelVare;
	private JLabel lblPris;
	private JLabel lbl_registreretKunde;
	private JLabel lblAntal_1;
	private JLabel lblOpdatevare;
	private JLabel lblVareOpdater;
	
	private SalesLineItem aktuelSli;
	private Order aktuelOrder;
	private int aktuelBarcode;
	private int aktuelID;
	
	private boolean registreretVare;
	private boolean registreretKunde;
	private JLabel lblDdk;

	public OrdreGUI()
	{
		orderCtr = new OrderCtr();
		setLayout(null);
		aktuelOrder = null;
		aktuelSli = null;
		registreretVare = false;
		registreretKunde = false;
		opdaterPris();

		JPanel panel_vare = new JPanel();
		panel_vare.setBorder(new TitledBorder(null, "Tilf\u00F8j vare(r)",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_vare.setBounds(12, 13, 453, 134);
		add(panel_vare);
		panel_vare.setLayout(null);

		textBarcode = new JTextField();
		textBarcode.setBounds(88, 25, 199, 22);
		panel_vare.add(textBarcode);
		textBarcode.setColumns(10);

		btnFindItem = new JButton("Scan stregkode");
		btnFindItem.addActionListener(new ScanBarcodeListener());
		btnFindItem.setBounds(299, 25, 142, 22);
		panel_vare.add(btnFindItem);

		JLabel lblVare = new JLabel("Vare:");
		lblVare.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVare.setBounds(12, 60, 106, 23);
		panel_vare.add(lblVare);

		JLabel lblAntal = new JLabel("Antal:");
		lblAntal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAntal.setBounds(230, 61, 83, 20);
		panel_vare.add(lblAntal);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 85, 429, 2);
		panel_vare.add(separator);

		lblAktuelVare = new JLabel("");
		lblAktuelVare.setEnabled(false);
		lblAktuelVare.setBounds(12, 96, 211, 23);
		panel_vare.add(lblAktuelVare);

		textAntal = new JTextField();
		textAntal.setEnabled(false);
		textAntal.setBounds(230, 94, 57, 22);
		panel_vare.add(textAntal);
		textAntal.setColumns(10);

		btnTilfojVare = new JButton("Tilf\u00F8j vare");
		btnTilfojVare.addActionListener(new TilfojVareListener());
		btnTilfojVare.setEnabled(false);
		btnTilfojVare.setBounds(299, 94, 139, 22);
		panel_vare.add(btnTilfojVare);

		JLabel lblStregkode = new JLabel("Stregkode:");
		lblStregkode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStregkode.setBounds(12, 25, 63, 22);
		panel_vare.add(lblStregkode);

		JPanel panel_kunde = new JPanel();
		panel_kunde.setBorder(new TitledBorder(null, "Tilf\u00F8j kunde",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_kunde.setBounds(477, 13, 493, 134);
		add(panel_kunde);
		panel_kunde.setLayout(null);

		JLabel lblKundeNr = new JLabel("Kunde nr.:");
		lblKundeNr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKundeNr.setBounds(10, 23, 81, 21);
		panel_kunde.add(lblKundeNr);

		textKundeID = new JTextField();
		textKundeID.setBounds(95, 22, 116, 22);
		panel_kunde.add(textKundeID);
		textKundeID.setColumns(10);

		btnSogEfterKunde = new JButton("Tilf\u00F8j kunde");
		btnSogEfterKunde.setBounds(350, 21, 131, 22);
		btnSogEfterKunde.addActionListener(new SogEfterKundeListener());
		panel_kunde.add(btnSogEfterKunde);

		JPanel panelOrdre = new JPanel();
		panelOrdre.setBorder(new TitledBorder(null, "Ordre", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelOrdre.setBounds(12, 151, 958, 382);
		add(panelOrdre);
		panelOrdre.setLayout(null);
		
		//Tabellen med alle items:
		table_ordre = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}};
		table_ordre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_ordre.getSelectionModel().addListSelectionListener(
				new ListSelectionListener()
				{
					public void valueChanged(ListSelectionEvent event)
					{
						if (table_ordre.getSelectedRow() != -1)
						{
							tilfojEllerFjernVare(aktuelOrder.getSaleLine().get(table_ordre.convertRowIndexToModel(table_ordre.getSelectedRow())));
						}
					}
				});
		
		
		scrollPane = new JScrollPane(table_ordre);
		scrollPane.setBounds(12, 25, 602, 298);
		panelOrdre.add(scrollPane);
		
		JLabel lblTotal = new JLabel("Total pris:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(22, 336, 75, 16);
		panelOrdre.add(lblTotal);
		
		lblPris = new JLabel("");
		lblPris.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPris.setBounds(483, 336, 83, 16);
		panelOrdre.add(lblPris);
		
		JLabel lblVare_1 = new JLabel("Opdater antal af vare");
		lblVare_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVare_1.setBounds(626, 25, 193, 16);
		panelOrdre.add(lblVare_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(626, 54, 320, 2);
		panelOrdre.add(separator_3);
		
		JLabel lblKunde = new JLabel("Registreret kunde");
		lblKunde.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKunde.setBounds(626, 161, 193, 16);
		panelOrdre.add(lblKunde);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(626, 190, 320, 2);
		panelOrdre.add(separator_4);
		
		lbl_registreretKunde = new JLabel("Ingen kunde er registreret!");
		lbl_registreretKunde.setEnabled(false);
		lbl_registreretKunde.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_registreretKunde.setBounds(626, 205, 320, 16);
		panelOrdre.add(lbl_registreretKunde);
		
		JLabel lblOrder = new JLabel("Afslut ordre");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOrder.setBounds(626, 256, 193, 16);
		panelOrdre.add(lblOrder);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(626, 285, 320, 2);
		panelOrdre.add(separator_5);
		
		btnAfslutOrdre = new JButton("Afslut ordre");
		btnAfslutOrdre.setEnabled(false);
		btnAfslutOrdre.addActionListener(new AfslutOrdreListener());
		btnAfslutOrdre.setBounds(818, 301, 128, 22);
		panelOrdre.add(btnAfslutOrdre);
		
		lblAntal_1 = new JLabel("Antal:");
		lblAntal_1.setEnabled(false);
		lblAntal_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAntal_1.setBounds(626, 110, 41, 16);
		panelOrdre.add(lblAntal_1);
		
		textOpdaterAntal = new JTextField();
		textOpdaterAntal.setEnabled(false);
		textOpdaterAntal.setBounds(671, 107, 75, 22);
		panelOrdre.add(textOpdaterAntal);
		textOpdaterAntal.setColumns(10);
		
		btnOpdaterAntal = new JButton("Opdater");
		btnOpdaterAntal.addActionListener(new OpdaterVareListener());
		btnOpdaterAntal.setEnabled(false);
		btnOpdaterAntal.setBounds(818, 69, 128, 22);
		panelOrdre.add(btnOpdaterAntal);
		
		lblVareOpdater = new JLabel("Vare:");
		lblVareOpdater.setEnabled(false);
		lblVareOpdater.setBounds(625, 72, 41, 16);
		panelOrdre.add(lblVareOpdater);
		
		lblOpdatevare = new JLabel("");
		lblOpdatevare.setBounds(671, 72, 132, 16);
		panelOrdre.add(lblOpdatevare);
		
		lblDdk = new JLabel("Dk ,-");
		lblDdk.setBounds(578, 336, 48, 16);
		panelOrdre.add(lblDdk);
	}
	
	private void updateTable()
	{
		ArrayList<SalesLineItem> sliArray = aktuelOrder.getSaleLine();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Varenavn");
		model.addColumn("Antal");
		model.addColumn("Pris");
		int i = 0;
		for(SalesLineItem sli : sliArray)
		{
			Item item = sli.getItem();
			model.addRow(new Object[] { item.getName(), sli.getAmount(), item.getPrice() });
		}
		table_ordre.setModel(model);
		opdaterPris();
	}
	
	private void clearTable()
	{
		DefaultTableModel model = new DefaultTableModel();
		int rowCount = model.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		table_ordre.setModel(model);
		opdaterPris();
	}
	
	private class ScanBarcodeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			scanStregkode();
		}
	}
	
	private void aktiverTilfojVare()
	{
		textAntal.setEnabled(true);
		lblAktuelVare.setEnabled(true);
		btnTilfojVare.setEnabled(true);
	}
	
	private void deaktiverTilfojVare()
	{
		textAntal.setEnabled(false);
		lblAktuelVare.setEnabled(false);
		btnTilfojVare.setEnabled(false);
	}
	
	
	private void scanStregkode()
	{
		if (!textBarcode.getText().trim().isEmpty())
		{
			if (MainGUI.isNumeric(textBarcode.getText()))
			{
				try 
				{
					int barcode = Integer.parseInt(textBarcode.getText());
						if (orderCtr.findItem(barcode) != null)
						{
							Item itemFound = orderCtr.findItem(barcode);
							lblAktuelVare.setText(itemFound.getName());
							aktuelBarcode = barcode;
							aktiverTilfojVare();
							MainGUI.setLastAction("Der blev fundet en varer med angivet stregkode!");
							
						}
						else
						{
							MainGUI.setLastAction("Der findes ingen vare med denne stregkode!");
							deaktiverTilfojVare();
						}
				}
				catch (NumberFormatException e)
				{
					MainGUI.setLastAction("Stregkode er ikke et tal!");
					deaktiverTilfojVare();
				}
			}
			else
			{
				MainGUI.setLastAction("Stregkode er ikke et tal!");
				deaktiverTilfojVare();
			}
		}
		else
		{
			MainGUI.setLastAction("Stregkode felt er tomt!");
			deaktiverTilfojVare();
		}
	}
	
	private class TilfojVareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tilfojVare();
		}
	}
	
	private void tilfojVare()
	{
		if (!textAntal.getText().trim().isEmpty())
		{
			if (MainGUI.isNumeric(textBarcode.getText()))
			{
				try 
				{
					int amount = Integer.parseInt(textAntal.getText());
					if(aktuelOrder == null)
					{
						aktuelOrder = orderCtr.createOrder();
					}
					orderCtr.addItem(aktuelBarcode, amount, aktuelOrder.getID());
					MainGUI.setLastAction(orderCtr.findItem(aktuelBarcode).getName() + " er blevet tilføjet til ordren!");
					deaktiverTilfojVare();
					lblAktuelVare.setText("");
					textBarcode.setText("");
					textAntal.setText("");
					updateTable();
					registreretVare = true;
					aktiverAfslutOrder();

				}
				catch (NumberFormatException e)
				{
					MainGUI.setLastAction("Antal er ikke et tal!");
				}
			}
			else
			{
				MainGUI.setLastAction("Antal er ikke et tal!");
			}
		}
		else
		{
			MainGUI.setLastAction("Antal felt er tomt!");
		}
		
	}
	
	private class SogEfterKundeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sogEfterKunde();
		}
	}
	
	private void deaktiverSogEfterKunde()
	{
		textKundeID.setEnabled(false);
		btnSogEfterKunde.setEnabled(false);
	}
	
	private void aktiverSogEfterKunde()
	{
		textKundeID.setEnabled(true);
		btnSogEfterKunde.setEnabled(true);
	}
	
	private void sogEfterKunde()
	{
		if (!textKundeID.getText().trim().isEmpty())
		{
			if (MainGUI.isNumeric(textKundeID.getText()))
			{
				try 
				{
					int kundeID = Integer.parseInt(textKundeID.getText());
					if (orderCtr.findPerson(kundeID) != null)
					{
						if(aktuelOrder == null)
						{
							aktuelOrder = orderCtr.createOrder();
						}
						Person kunde = orderCtr.findPerson(kundeID);
						deaktiverSogEfterKunde();
						MainGUI.setLastAction("Kunde: " + kunde.getName() + " med ID: " + kundeID + " er blevet tilføjet til ordren!");
						aktuelOrder.addPersonToOrder(kunde);
						lbl_registreretKunde.setText(kunde.getName());
						registreretKunde = true;
						aktiverAfslutOrder();
						aktiverRegistreretKunde();
					}
					else
					{
						MainGUI.setLastAction("Der findes ingen kunde med indtastet ID!");
					}
				}
				catch (NumberFormatException e)
				{
					MainGUI.setLastAction("Kunde ID er ikke et tal!");
				}
			}
			else
			{
				MainGUI.setLastAction("Kunde ID er ikke et tal!");
			}
		}	
		else
		{
			MainGUI.setLastAction("Kunde ID felt er tomt!");
		}
	}
	
	private void aktiverRegistreretKunde()
	{
		lbl_registreretKunde.setEnabled(true);
	}
	
	private void deaktiverRegistreretKunde()
	{
		lbl_registreretKunde.setEnabled(false);
		lbl_registreretKunde.setText("Ingen kunde er registreret!");
	}
	
	private void aktiverOpdaterEllerFjernVare()
	{
		lblAntal_1.setEnabled(true);
		textOpdaterAntal.setEnabled(true);
		btnOpdaterAntal.setEnabled(true);
		lblOpdatevare.setEnabled(true);
		lblVareOpdater.setEnabled(true);
	}
	
	private void deaktiverOpdaterEllerFjernVare()
	{
		lblAntal_1.setEnabled(false);
		textOpdaterAntal.setEnabled(false);
		textOpdaterAntal.setText("");
		btnOpdaterAntal.setEnabled(false);
		lblOpdatevare.setEnabled(false);
		lblOpdatevare.setText("");
		lblVareOpdater.setEnabled(false);
	}
	
	private void tilfojEllerFjernVare(SalesLineItem sli)
	{
		aktuelSli = sli;
		aktiverOpdaterEllerFjernVare();
		textOpdaterAntal.setText(sli.getAmount() + "");
		lblOpdatevare.setText(sli.getItem().getName());
	}
	
	private class OpdaterVareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			opdaterVare();
		}
	}
	
	private void opdaterVare()
	{
		if (!textOpdaterAntal.getText().trim().isEmpty())
		{
			if (MainGUI.isNumeric(textOpdaterAntal.getText()))
			{
				try 
				{
					int nytAntal = Integer.parseInt(textOpdaterAntal.getText());
					if (nytAntal > 0)
					{
						aktuelSli.setAmount(nytAntal);
						updateTable();
						deaktiverOpdaterEllerFjernVare();
						MainGUI.setLastAction("Antal af: " + aktuelSli.getItem().getName() + " er blevet opdateret til: " + nytAntal);
					}
					else
					{
						MainGUI.setLastAction("Du skal mindst have én vare!");
					}
				}
				catch (NumberFormatException e)
				{
					MainGUI.setLastAction("Nyt antal er ikke et tal!");
				}
			}
			else
			{
				MainGUI.setLastAction("Nyt antal er ikke et tal!");
			}
		}
		else
		{
			MainGUI.setLastAction("Nyt antal feltet er tomt!");
		}
	}
	
	private void aktiverAfslutOrder()
	{
		if (registreretKunde == true && registreretVare == true)
		{
			btnAfslutOrdre.setEnabled(true);
		}
	}
	
	private class AfslutOrdreListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			afslutOrdre();
		}
	}
	
	private void opdaterPris()
	{
		int pris = 0;
		if(aktuelOrder != null)
		{
			for(SalesLineItem item : aktuelOrder.getSaleLine())
			{
				pris += item.getAmount() * item.getItem().getPrice();
			}
			lblPris.setText("" + pris);
		}
		else
		{
			//lblPris.setText("");
		}
	}
	
	private void afslutOrdre()
	{
		JOptionPane.showMessageDialog(null, "Ordre er succesfuldt oprettet!");
		aktuelOrder = null;
		aktuelSli = null;
		registreretVare = false;
		registreretKunde = false;
		btnAfslutOrdre.setEnabled(false);
		clearTable();
		deaktiverRegistreretKunde();
		aktiverSogEfterKunde();
		lblPris.setText("");
		try
		{
			SaveLoadCtr.save();
			MainGUI.setLastAction("Ordre er succesfuld oprettet!");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

