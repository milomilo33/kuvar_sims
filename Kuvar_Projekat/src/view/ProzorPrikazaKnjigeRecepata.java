package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

import model.Aplikacija;
import model.KnjigaRecepata;
import model.Recept;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.KontrolerProzoraPrikazaRecepta;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class ProzorPrikazaKnjigeRecepata {

	private Aplikacija aplikacija;
	private KnjigaRecepata knjigaRecepata;
	
	private DefaultListModel prikazPoglavlja = new DefaultListModel();
	private DefaultListModel prikazRecepata = new DefaultListModel();
	private JList listPoglavlja = new JList(prikazPoglavlja);
	private JList listRecepti = new JList(prikazRecepata);
	
	private JDialog frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ProzorPrikazaKnjigeRecepata(Aplikacija aplikacija, KnjigaRecepata knjigaRecepata) {
		this.aplikacija = aplikacija;
		this.knjigaRecepata = knjigaRecepata;
		initialize();
		initVals();
		frame.setModal(true);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setBounds(100, 100, 650, 395);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPanePoglavlja = new JScrollPane();
		scrollPanePoglavlja.setBounds(10, 82, 248, 218);
		frame.getContentPane().add(scrollPanePoglavlja);
		
		scrollPanePoglavlja.setViewportView(listPoglavlja);
		
		JScrollPane scrollPaneRecepti = new JScrollPane();
		scrollPaneRecepti.setBounds(371, 82, 248, 218);
		frame.getContentPane().add(scrollPaneRecepti);
		
		scrollPaneRecepti.setViewportView(listRecepti);
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnPrihvati.setBounds(371, 332, 248, 23);
		frame.getContentPane().add(btnPrihvati);
		
		JLabel lblNazivKnjigeRecepata = new JLabel("Naziv knjige recepata:");
		lblNazivKnjigeRecepata.setBounds(10, 11, 169, 14);
		frame.getContentPane().add(lblNazivKnjigeRecepata);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(189, 8, 284, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPoglavlja = new JLabel("Poglavlja:");
		lblPoglavlja.setBounds(10, 57, 87, 14);
		frame.getContentPane().add(lblPoglavlja);
		
		JLabel lblReceptiUPoglavlju = new JLabel("Recepti u poglavlju:");
		lblReceptiUPoglavlju.setBounds(371, 57, 157, 14);
		frame.getContentPane().add(lblReceptiUPoglavlju);
		
		listPoglavlja.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!knjigaRecepata.getReceptiSekcije(listPoglavlja.getSelectedValue().toString()).isEmpty() && knjigaRecepata.getReceptiSekcije(listPoglavlja.getSelectedValue().toString()) != null) {
					prikazRecepata.clear();
					prikazRecepata.addAll(knjigaRecepata.getReceptiSekcije(listPoglavlja.getSelectedValue().toString()));
				}
			}
		});
		
		listRecepti.addMouseListener(new MouseAdapter() {//klik na selektovan recept, prikazi recept
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            ProzorPrikazaRecepta prozor = new ProzorPrikazaRecepta((Recept)prikazRecepata.getElementAt(index), aplikacija, new KontrolerProzoraPrikazaRecepta(aplikacija));
		        }
			}
		});
	}
	
	private void initVals() {
		for(Entry entry:this.knjigaRecepata.getSekcijeRecepti().entrySet())
			prikazPoglavlja.addElement(entry.getKey());
		textField.setText(this.knjigaRecepata.getNaziv());
	}

}
