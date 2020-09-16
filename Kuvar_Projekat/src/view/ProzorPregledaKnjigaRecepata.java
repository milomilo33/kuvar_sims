package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import model.Aplikacija;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProzorPregledaKnjigaRecepata {

	private Aplikacija aplikacija;
	
	private DefaultListModel listaPrikaza = new DefaultListModel();
	
	private JDialog frame;
	private JTextField textFieldNoviNaziv;
	private JList listKnjiga = new JList(listaPrikaza);

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ProzorPregledaKnjigaRecepata(Aplikacija aplikacija) {
		this.aplikacija = aplikacija;
		initialize();
		initVals();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setTitle("Knjige recepata");
		frame.setModal(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 712, 423);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPaneKnjiga = new JScrollPane();
		scrollPaneKnjiga.setBounds(10, 45, 236, 338);
		frame.getContentPane().add(scrollPaneKnjiga);
		
		scrollPaneKnjiga.setViewportView(listKnjiga);
		
		JLabel lblVaseKnjigeRecepata = new JLabel("Vase knjige recepata:");
		lblVaseKnjigeRecepata.setBounds(10, 20, 236, 14);
		frame.getContentPane().add(lblVaseKnjigeRecepata);
		
		JButton btnPreimenujKnjigu = new JButton("Preimenuj knjigu");
		btnPreimenujKnjigu.setBounds(502, 43, 166, 23);
		frame.getContentPane().add(btnPreimenujKnjigu);
		
		textFieldNoviNaziv = new JTextField();
		textFieldNoviNaziv.setToolTipText("Unesi novi naziv knjige");
		textFieldNoviNaziv.setBounds(275, 44, 217, 20);
		frame.getContentPane().add(textFieldNoviNaziv);
		textFieldNoviNaziv.setColumns(10);
		
		JButton btnIzbrisiKnjigu = new JButton("Izbrisi knjigu");
		btnIzbrisiKnjigu.setBounds(502, 95, 166, 23);
		frame.getContentPane().add(btnIzbrisiKnjigu);
		
		JButton btnIzmeniKnjigu = new JButton("Izmeni knjigu");
		btnIzmeniKnjigu.setBounds(502, 152, 166, 23);
		frame.getContentPane().add(btnIzmeniKnjigu);
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnPrihvati.setBounds(502, 360, 166, 23);
		frame.getContentPane().add(btnPrihvati);
	}
	
	private void initVals() {
		if(this.aplikacija.getTrenutniKorisnik().getKnjigeRecepata()!=null && !this.aplikacija.getTrenutniKorisnik().getKnjigeRecepata().isEmpty())
			this.listaPrikaza.addAll(this.aplikacija.getTrenutniKorisnik().getKnjigeRecepata());
	}
}
