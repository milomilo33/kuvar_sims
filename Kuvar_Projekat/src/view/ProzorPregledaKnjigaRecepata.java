package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.KontrolerProzoraDodavanjaKnjigeRecepata;
import controller.KontrolerProzoraPregledaKnjigaRecepata;
import controller.KontrolerProzoraPrikazaRecepta;
import event.Observer;
import event.UpdateEvent;
import model.Aplikacija;
import model.KnjigaRecepata;
import model.Recept;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ProzorPregledaKnjigaRecepata implements Observer{

	private Aplikacija aplikacija;
	private KontrolerProzoraPregledaKnjigaRecepata kontroler;
	private KnjigaRecepata selektovanaKnjiga;
	
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
	public ProzorPregledaKnjigaRecepata(Aplikacija aplikacija, KontrolerProzoraPregledaKnjigaRecepata kontroler) {
		this.aplikacija = aplikacija;
		this.kontroler = kontroler;
		this.aplikacija.menadzerKorisnika.addObserver(this);
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
		btnPreimenujKnjigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kontroler.preimenujKnjiguRecepata(textFieldNoviNaziv.getText(), selektovanaKnjiga);
				}catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, "Unesite naziv za izmenu!");
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "Knjiga recepata nije odabrana!");
				}
			}
		});
		btnPreimenujKnjigu.setBounds(502, 43, 166, 23);
		frame.getContentPane().add(btnPreimenujKnjigu);
		
		textFieldNoviNaziv = new JTextField();
		textFieldNoviNaziv.setToolTipText("Unesi novi naziv knjige");
		textFieldNoviNaziv.setBounds(275, 44, 217, 20);
		frame.getContentPane().add(textFieldNoviNaziv);
		textFieldNoviNaziv.setColumns(10);
		
		JButton btnIzbrisiKnjigu = new JButton("Izbrisi knjigu");
		btnIzbrisiKnjigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kontroler.obrisiKnjiguRecepata(selektovanaKnjiga);
					listaPrikaza.removeElement(selektovanaKnjiga);
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "Knjiga recepata nije odabrana!");
				}
			}
		});
		btnIzbrisiKnjigu.setBounds(502, 95, 166, 23);
		frame.getContentPane().add(btnIzbrisiKnjigu);
		
		JButton btnIzmeniKnjigu = new JButton("Izmeni knjigu");
		btnIzmeniKnjigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(selektovanaKnjiga!=null) {
						ProzorDodavanjaKnjigeRecepata p = new ProzorDodavanjaKnjigeRecepata(aplikacija, selektovanaKnjiga, new KontrolerProzoraDodavanjaKnjigeRecepata(aplikacija));
						listaPrikaza.clear();
						initVals();
					}
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "Knjiga recepata nije odabrana!");
				}
			}
		});
		btnIzmeniKnjigu.setBounds(502, 152, 166, 23);
		frame.getContentPane().add(btnIzmeniKnjigu);
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ro();
				frame.dispose();
			}
		});
		btnPrihvati.setBounds(502, 360, 166, 23);
		frame.getContentPane().add(btnPrihvati);
		
		listKnjiga.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanaKnjiga = (KnjigaRecepata)listKnjiga.getSelectedValue();
			}});
		
		listKnjiga.addMouseListener(new MouseAdapter() {//klik na selektovanu njigu recepata, prikazi knjigu recepata
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            ProzorPrikazaKnjigeRecepata prozor = new ProzorPrikazaKnjigeRecepata(aplikacija,(KnjigaRecepata)listaPrikaza.getElementAt(index));
		        }
			}
		});
		
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                ro();
                frame.dispose();
            }
        });
	}
	
	private void initVals() {
		if(this.aplikacija.getTrenutniKorisnik().getKnjigeRecepata()!=null && !this.aplikacija.getTrenutniKorisnik().getKnjigeRecepata().isEmpty())
			this.listaPrikaza.addAll(this.aplikacija.getTrenutniKorisnik().getKnjigeRecepata());
	}
	
	public void ro() {
		aplikacija.menadzerKorisnika.removeObserver(this);
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		listaPrikaza.clear();
		initVals();
	}
}
