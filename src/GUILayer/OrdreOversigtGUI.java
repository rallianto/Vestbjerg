package GUILayer;
import CtrLayer.*;
import ModelLayer.*;

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

public class OrdreOversigtGUI extends JPanel
{
	private OrderCtr orderCtr;

	private JTable table;
	private JScrollPane scrollPane;

	// Konstrukter
	public OrdreOversigtGUI()
	{
		setLayout(null);

		orderCtr = new OrderCtr();

		// Tabelen der viser det forskellige kunder
		table = new JTable()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (table.getSelectedRow() != -1)
						{
							//When something gets selected
						}
					}
				});
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(375, 11, 602, 512);
		add(scrollPane);

	}

	
	//og den her også for at updatere tabel
	public void updateTable() {
		ArrayList<Order> orders = orderCtr.getContainer().getContainer();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Order ID");
		model.addColumn("Kunde");
		model.addColumn("Antal delordre");
		model.addColumn("Dato for oprettelse");
		for (Order item : orders)
		{
			model.addRow(new Object[] { item.getID(), item.getPerson().getName(), item.getItemCount(), item.getDate() });
		}
		table.setModel(model);
	}

}
