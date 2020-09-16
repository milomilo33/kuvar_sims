package view;

import java.awt.EventQueue;
import java.util.Date;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import controller.KontrolerProzoraKomentarisanjaRecepta;
import event.Observer;
import event.UpdateEvent;
import model.Aplikacija;
import model.Komentar;
import model.Recept;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProzorKomentarisanjaRecepta implements Observer{

	private Aplikacija aplikacija;
	private Recept recept;
	private KontrolerProzoraKomentarisanjaRecepta kontroler;
	private DefaultTableModel modelKomentari;
	private StringBuilder ocena;
	private boolean tip;
	
	private JDialog frame;
	private JTable tableKomentari;
	private JTextField textFieldOcena;
	private JTextArea textAreaKomentar;
	private JButton btnDodajKomentar = new JButton("Dodaj komentar");
	private JLabel lblUnesiKomentar = new JLabel("Unesi komentar:");
	private JLabel lblOceni = new JLabel("Oceni:");
	private JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ProzorKomentarisanjaRecepta(Aplikacija aplikacija, Recept recept, KontrolerProzoraKomentarisanjaRecepta kontroler, boolean tip) {
		this.aplikacija = aplikacija;
		this.recept = recept;
		this.kontroler = kontroler;
		this.tip = tip;
		this.ocena = new StringBuilder("");
		this.aplikacija.menadzerRecepata.addObserver(this);
		initialize();
		postaviPogled();
		frame.setModal(true);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 519);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPaneKomentari = new JScrollPane();
		scrollPaneKomentari.setBounds(10, 76, 774, 193);
		frame.getContentPane().add(scrollPaneKomentari);
		
		tableKomentari = new JTable();
		tableKomentari.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Komentar", "Ocena", "Autor"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableKomentari.getColumnModel().getColumn(0).setPreferredWidth(580);
		tableKomentari.getColumnModel().getColumn(0).setMinWidth(580);
		tableKomentari.getColumnModel().getColumn(0).setMaxWidth(580);
		tableKomentari.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableKomentari.getColumnModel().getColumn(1).setMinWidth(50);
		tableKomentari.getColumnModel().getColumn(1).setMaxWidth(50);
		tableKomentari.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableKomentari.getColumnModel().getColumn(2).setMinWidth(150);
		tableKomentari.getColumnModel().getColumn(2).setMaxWidth(150);
		scrollPaneKomentari.setViewportView(tableKomentari);
		modelKomentari = (DefaultTableModel) tableKomentari.getModel();
		
		updateTableKomentari();
		
		JLabel lblProsecnaOcenaRecepta = new JLabel("Prosecna ocena recepta:");
		lblProsecnaOcenaRecepta.setBounds(10, 22, 156, 14);
		frame.getContentPane().add(lblProsecnaOcenaRecepta);
		
		textFieldOcena = new JTextField();
		textFieldOcena.setEditable(false);
		textFieldOcena.setBounds(167, 19, 86, 20);
		frame.getContentPane().add(textFieldOcena);
		textFieldOcena.setColumns(10);
		if(recept.getKomentari()!=null)
			textFieldOcena.setText(recept.getSrednjaOcena().toString());
		
		textAreaKomentar = new JTextArea();
		textAreaKomentar.setBounds(10, 301, 428, 145);
		frame.getContentPane().add(textAreaKomentar);
		
		lblUnesiKomentar.setBounds(10, 280, 96, 14);
		frame.getContentPane().add(lblUnesiKomentar);
		
		lblOceni.setBounds(492, 280, 58, 14);
		frame.getContentPane().add(lblOceni);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox.setBounds(560, 276, 67, 22);
		frame.getContentPane().add(comboBox);
		
		
		btnDodajKomentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // dodaje unet komentar i ocenu
				try {
					kontroler.dodajKomentar(textAreaKomentar.getText(), new Date(), aplikacija.getTrenutniKorisnik(),Integer.parseInt((String)comboBox.getSelectedItem()), recept, ocena);
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "Polje komentara nije popunjeno!");
				}
			}
		});
		btnDodajKomentar.setBounds(560, 340, 185, 23);
		frame.getContentPane().add(btnDodajKomentar);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnOdustani.setBounds(560, 423, 185, 23);
		frame.getContentPane().add(btnOdustani);
		
		JLabel lblKomentari = new JLabel("Komentari:");
		lblKomentari.setBounds(10, 51, 96, 14);
		frame.getContentPane().add(lblKomentari);
	}
	
	public void updateTableKomentari() {
		for( int i = modelKomentari.getRowCount() - 1; i >= 0; i-- )
		{
		    modelKomentari.removeRow(i);
		}
		for(Komentar k: this.recept.getKomentari()) {
			Vector row = new Vector();
			row.add(k.getTekst());
			row.add(k.getOcena());
			row.add(k.getAutor().getUsername());
			modelKomentari.addRow(row);
		}
	}
	
	public void updateTextFieldOcena() {
		textFieldOcena.setText(ocena.toString());
	}
	
	public void postaviPogled() {
		if(this.tip) {
			btnDodajKomentar.setVisible(false);
			comboBox.setVisible(false);
			textAreaKomentar.setVisible(false);
			lblUnesiKomentar.setVisible(false);
			lblOceni.setVisible(false);
		}
	}
	
	public void updatePerformed(UpdateEvent e) {
		updateTableKomentari();
		updateTextFieldOcena();
		textAreaKomentar.setText("");
	}
}
