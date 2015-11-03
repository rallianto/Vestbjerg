package GUILayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JMenu;

import CtrLayer.SaveLoadCtr;
import ModelLayer.OrderContainer;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JPanel kundeGUI;
	private JPanel vareGUI;
	private JPanel ordreGUI;
	private JPanel ordreOversigtGUI;

	private SaveLoadCtr saveLoadCtr;

	static JLabel label_LastAction;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {

		setResizable(false);
		// Set window
		setTitle("Vestbjerg byggecenter");
		setMinimumSize(new Dimension(1024, 768));
		setMaximumSize(new Dimension(1024, 768));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		// Set referrences to other GUIs
		kundeGUI = new KundeGUI();
		vareGUI = new LagerGUI();
		ordreGUI = new OrdreGUI();
		ordreOversigtGUI = new OrdreOversigtGUI();
		saveLoadCtr = new SaveLoadCtr();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new MatteBorder(0, 0, 0, 0,
				(Color) new Color(0, 0, 0)));
		setJMenuBar(menuBar);

		JMenu mnFiler = new JMenu("Filer");
		mnFiler.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnFiler);

		JMenuItem mntmAfslut = new JMenuItem("Afslut");
		mntmAfslut.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnFiler.add(mntmAfslut);

		JMenu mnHjlp = new JMenu("Hj\u00E6lp");
		mnHjlp.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnHjlp);

		JMenuItem mntmBrugervejledning = new JMenuItem("Brugervejledning");
		mntmBrugervejledning.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnHjlp.add(mntmBrugervejledning);

		JMenuItem mntmOm = new JMenuItem("Om");
		mntmOm.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnHjlp.add(mntmOm);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabs_Hovedmenu = new JTabbedPane(JTabbedPane.TOP);
		tabs_Hovedmenu.setBounds(10, 89, 996, 574);
		tabs_Hovedmenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contentPane.add(tabs_Hovedmenu);

		// Tabs i hovedmenuen
		tabs_Hovedmenu.add("Kunder", kundeGUI);
		tabs_Hovedmenu.add("Lager", vareGUI);
		tabs_Hovedmenu.add("Ordre", ordreGUI);
		tabs_Hovedmenu.add("Ordre oversigt", ordreOversigtGUI);

		tabs_Hovedmenu.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabs_Hovedmenu.getSelectedIndex() == 1)
				{
					((LagerGUI) vareGUI).updateTable();
				}
				else if (tabs_Hovedmenu.getSelectedIndex() == 3)
				{
					((OrdreOversigtGUI) ordreOversigtGUI).updateTable();
				}
			}
		});

		JPanel panel_Top = new JPanel();
		panel_Top.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel_Top.setBackground(Color.WHITE);
		panel_Top.setBounds(0, 0, 1018, 76);
		contentPane.add(panel_Top);
		panel_Top.setLayout(null);

		JLabel lblNewLabel = new JLabel("Vestbjerg byggecenter A/S");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(12, 13, 381, 50);
		panel_Top.add(lblNewLabel);

		JPanel panel_Bund = new JPanel();
		panel_Bund.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0,
				0, 0)));
		panel_Bund.setBackground(Color.LIGHT_GRAY);
		panel_Bund.setBounds(0, 676, 1018, 33);
		contentPane.add(panel_Bund);
		panel_Bund.setLayout(null);

		JLabel label_LoggetIndSom = new JLabel(
				"Logget ind som: Anders Andersen");
		label_LoggetIndSom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_LoggetIndSom.setBorder(new MatteBorder(0, 1, 0, 0,
				(Color) new Color(0, 0, 0)));
		label_LoggetIndSom.setHorizontalAlignment(SwingConstants.CENTER);
		label_LoggetIndSom.setBounds(774, 0, 244, 34);
		panel_Bund.add(label_LoggetIndSom);

		JLabel label_SenesteSystemHandling = new JLabel(
				"Seneste system handling:");
		label_SenesteSystemHandling.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_SenesteSystemHandling.setForeground(Color.BLACK);
		label_SenesteSystemHandling
				.setHorizontalAlignment(SwingConstants.CENTER);
		label_SenesteSystemHandling.setBounds(0, 0, 197, 34);
		panel_Bund.add(label_SenesteSystemHandling);

		label_LastAction = new JLabel("Seneste handling!");
		label_LastAction.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_LastAction.setForeground(Color.BLACK);
		label_LastAction.setBounds(202, 0, 523, 34);
		panel_Bund.add(label_LastAction);

		loadDatabase();
		((KundeGUI) kundeGUI).updateTable();
	}

	public static void setLastAction(String string) {
		label_LastAction.setText(string);
	}

	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	private void loadDatabase() {
		File f = new File(saveLoadCtr.getFileName());
		boolean fileLoaded = false;
		boolean newDatabase = false;
		while (!fileLoaded) {
			if (f.isFile()) {
				try {
					SaveLoadCtr.load();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				if (newDatabase) {
					// Der fandtes ikke nogen Data.bin-fil - Så nu er der
					// oprettet en
					label_LastAction
							.setText("Database er succesfuldt oprettet!");
				} else {
					// Der fandtes en Data.bin-fil - og dataerne er succesfuldt
					// hentet
					label_LastAction.setText("Data er succesfuldt hentet!");
				}
				fileLoaded = true;
			} else {
				try {
					SaveLoadCtr.save();
					newDatabase = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
