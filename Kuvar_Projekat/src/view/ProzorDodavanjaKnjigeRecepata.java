package view;

import java.awt.EventQueue;
import java.util.Map.Entry;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Aplikacija;
import model.KnjigaRecepata;
import model.Recept;

public class ProzorDodavanjaKnjigeRecepata {

	private JDialog frame;
	private JTextField textFieldNazivPoglavlja;
	private JTextField textFieldNaziv;
	
	private Aplikacija aplikacija;
	private KnjigaRecepata knjigaRecepata;
	
	private DefaultListModel prikazPoglavlja = new DefaultListModel();
	private DefaultListModel prikazRecepataPoglavlja = new DefaultListModel();
	private DefaultListModel prikazSviRecepti = new DefaultListModel();
	private JList listPoglavlja = new JList(prikazPoglavlja);
	private JList listPoglavljeRecepti = new JList(prikazRecepataPoglavlja);
	private JList listSviRecepti = new JList(prikazSviRecepti);
	private String selektovanoPoglavlje;
	private Recept selektovanRecept;
	private Recept selektovanReceptSvih;
	
	
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ProzorDodavanjaKnjigeRecepata(Aplikacija aplikacija, KnjigaRecepata knjigaRecepata) {
		this.aplikacija = aplikacija;
		this.knjigaRecepata = knjigaRecepata;
		if(knjigaRecepata != null) { //ako se pregleda knjiga recepata
			
		}
		initialize();
		frame.setModal(true);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setTitle("Knjiga recepata");
		frame.setResizable(false);
		frame.setBounds(100, 100, 838, 567);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPanePoglavlja = new JScrollPane();
		scrollPanePoglavlja.setBounds(10, 37, 238, 276);
		frame.getContentPane().add(scrollPanePoglavlja);
		scrollPanePoglavlja.setViewportView(listPoglavlja);
		
		JScrollPane scrollPanePoglavljeRecepti = new JScrollPane();
		scrollPanePoglavljeRecepti.setBounds(290, 37, 238, 276);
		frame.getContentPane().add(scrollPanePoglavljeRecepti);
		scrollPanePoglavljeRecepti.setViewportView(listPoglavljeRecepti);
		
		JScrollPane scrollPaneSviRecepti = new JScrollPane();
		scrollPaneSviRecepti.setBounds(566, 37, 238, 276);
		frame.getContentPane().add(scrollPaneSviRecepti);
		scrollPaneSviRecepti.setViewportView(listSviRecepti);
		
		listPoglavlja.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanoPoglavlje = (String)listPoglavlja.getSelectedValue();
			}
			//dodaj prikaz recepata iz poglavlja
		});
		
		listPoglavljeRecepti.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanRecept = (Recept)listPoglavljeRecepti.getSelectedValue();
			}
		});
		
		listSviRecepti.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanReceptSvih = (Recept)listSviRecepti.getSelectedValue();
			}
		});
		
		JLabel lblPoglavljaKnjigeRecepata = new JLabel("Poglavlja knjige recepata:");
		lblPoglavljaKnjigeRecepata.setBounds(10, 11, 238, 14);
		frame.getContentPane().add(lblPoglavljaKnjigeRecepata);
		
		JLabel lblReceptiUPoglavlju = new JLabel("Recepti u poglavlju");
		lblReceptiUPoglavlju.setBounds(290, 11, 238, 14);
		frame.getContentPane().add(lblReceptiUPoglavlju);
		
		JLabel lblSviVasiRecepti = new JLabel("Svi vasi recepti:");
		lblSviVasiRecepti.setBounds(566, 11, 238, 14);
		frame.getContentPane().add(lblSviVasiRecepti);
		
		JButton btnDodajPoglavlje = new JButton("Dodaj poglavlje");
		btnDodajPoglavlje.setBounds(10, 363, 238, 23);
		frame.getContentPane().add(btnDodajPoglavlje);
		
		textFieldNazivPoglavlja = new JTextField();
		textFieldNazivPoglavlja.setToolTipText("Unesi naziv poglavlja");
		textFieldNazivPoglavlja.setBounds(10, 332, 238, 20);
		frame.getContentPane().add(textFieldNazivPoglavlja);
		textFieldNazivPoglavlja.setColumns(10);
		
		JButton btnIzbaciPoglavlje = new JButton("Izbaci poglavlje");
		btnIzbaciPoglavlje.setBounds(10, 397, 238, 23);
		frame.getContentPane().add(btnIzbaciPoglavlje);
		
		JButton btnNewButton_1 = new JButton("Izmeni naziv poglavlja");
		btnNewButton_1.setBounds(10, 431, 238, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnIzbaciReceptIz = new JButton("Izbaci recept iz poglavlja");
		btnIzbaciReceptIz.setBounds(290, 331, 238, 23);
		frame.getContentPane().add(btnIzbaciReceptIz);
		
		JButton btnUbaciReceptU = new JButton("Ubaci recept u poglavlje");
		btnUbaciReceptU.setBounds(566, 331, 238, 23);
		frame.getContentPane().add(btnUbaciReceptU);
		
		JButton btnZavrsi = new JButton("Zavrsi");
		btnZavrsi.setBounds(566, 470, 238, 23);
		frame.getContentPane().add(btnZavrsi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(566, 504, 238, 23);
		frame.getContentPane().add(btnOdustani);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setBounds(222, 485, 306, 20);
		frame.getContentPane().add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		JLabel lblUnesiteNazivKnjige = new JLabel("Unesite naziv knjige recepata:");
		lblUnesiteNazivKnjige.setBounds(10, 488, 181, 14);
		frame.getContentPane().add(lblUnesiteNazivKnjige);
	}
	
	private void initValList(){
		for(Entry e: this.knjigaRecepata.getSekcijeRecepti().entrySet()) {
			prikazPoglavlja.addElement(e.getKey());
		}
	}
}
