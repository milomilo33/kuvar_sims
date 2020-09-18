package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

public class ProzorDodavanjaKnjigeRecepata implements Observer{

	private JDialog frame;
	private JTextField textFieldNazivPoglavlja;
	private JTextField textFieldNaziv;
	
	private Aplikacija aplikacija;
	private KontrolerProzoraDodavanjaKnjigeRecepata kontroler;
	private KnjigaRecepata knjigaRecepata;
	private Map<String, List<Recept>> kriterijumDodavanja;
	
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
	public ProzorDodavanjaKnjigeRecepata(Aplikacija aplikacija, KnjigaRecepata knjigaRecepata, KontrolerProzoraDodavanjaKnjigeRecepata kontroler) {
		this.aplikacija = aplikacija;
		this.kontroler = kontroler;
		this.knjigaRecepata = knjigaRecepata;
		this.kriterijumDodavanja = new HashMap<>();
		this.aplikacija.menadzerKorisnika.addObserver(this);
		initAll();
		initPregled();
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
				prikazRecepataPoglavlja.clear();
				if(selektovanoPoglavlje!=null)
					if(!kriterijumDodavanja.get(selektovanoPoglavlje).isEmpty())
						prikazRecepataPoglavlja.addAll(kriterijumDodavanja.get(selektovanoPoglavlje));
			}
		});
		
		listPoglavljeRecepti.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanRecept = (Recept)listPoglavljeRecepti.getSelectedValue();
			}
		});
		
		listPoglavljeRecepti.addMouseListener(new MouseAdapter() {//klik na selektovan recept, prikazi recept
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            ProzorPrikazaRecepta prozor = new ProzorPrikazaRecepta((Recept)prikazRecepataPoglavlja.getElementAt(index), aplikacija, new KontrolerProzoraPrikazaRecepta(aplikacija));
		        }
			}
		});
		
		listSviRecepti.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanReceptSvih = (Recept)listSviRecepti.getSelectedValue();
			}
		});
		
		listSviRecepti.addMouseListener(new MouseAdapter() {//klik na selektovan recept, prikazi recept
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            ProzorPrikazaRecepta prozor = new ProzorPrikazaRecepta((Recept)prikazSviRecepti.getElementAt(index), aplikacija, new KontrolerProzoraPrikazaRecepta(aplikacija));
		        }
			}
		});
		
		JLabel lblPoglavljaKnjigeRecepata = new JLabel("Poglavlja knjige recepata:");
		lblPoglavljaKnjigeRecepata.setBounds(10, 11, 238, 14);
		frame.getContentPane().add(lblPoglavljaKnjigeRecepata);
		
		JLabel lblReceptiUPoglavlju = new JLabel("Recepti u poglavlju:");
		lblReceptiUPoglavlju.setBounds(290, 11, 238, 14);
		frame.getContentPane().add(lblReceptiUPoglavlju);
		
		JLabel lblSviVasiRecepti = new JLabel("Svi vasi recepti:");
		lblSviVasiRecepti.setBounds(566, 11, 238, 14);
		frame.getContentPane().add(lblSviVasiRecepti);
		
		JButton btnDodajPoglavlje = new JButton("Dodaj poglavlje");
		btnDodajPoglavlje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldNazivPoglavlja.getText().equals("")) {
					if(!prikazPoglavlja.contains(textFieldNazivPoglavlja.getText())) {
						prikazPoglavlja.addElement(textFieldNazivPoglavlja.getText());
						kriterijumDodavanja.put(textFieldNazivPoglavlja.getText(), new ArrayList<>());
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Naziv poglavlja nije unet!");
			}
		});
		btnDodajPoglavlje.setBounds(10, 363, 238, 23);
		frame.getContentPane().add(btnDodajPoglavlje);
		
		textFieldNazivPoglavlja = new JTextField();
		textFieldNazivPoglavlja.setToolTipText("Unesi naziv poglavlja");
		textFieldNazivPoglavlja.setBounds(10, 332, 238, 20);
		frame.getContentPane().add(textFieldNazivPoglavlja);
		textFieldNazivPoglavlja.setColumns(10);
		
		JButton btnIzbaciPoglavlje = new JButton("Izbaci poglavlje");
		btnIzbaciPoglavlje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selektovanoPoglavlje != null) {
					String tmp = selektovanoPoglavlje;
					listPoglavlja.clearSelection();
					listPoglavljeRecepti.clearSelection();
					listSviRecepti.clearSelection();
					kriterijumDodavanja.remove(tmp);
					prikazPoglavlja.removeElement(tmp);
				}
				else
					JOptionPane.showMessageDialog(null, "Niste odabrali poglavlje za brisanje!");
			}
		});
		btnIzbaciPoglavlje.setBounds(10, 397, 238, 23);
		frame.getContentPane().add(btnIzbaciPoglavlje);
		
		JButton btnNewButton_1 = new JButton("Izmeni naziv poglavlja");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selektovanoPoglavlje != null) {
					if(!textFieldNazivPoglavlja.getText().equals(""))
						if(!prikazPoglavlja.contains(textFieldNazivPoglavlja.getText())) {
							kriterijumDodavanja.put(textFieldNazivPoglavlja.getText(), kriterijumDodavanja.remove(selektovanoPoglavlje));
							prikazPoglavlja.removeElement(selektovanoPoglavlje);
							prikazPoglavlja.addElement(textFieldNazivPoglavlja.getText());
						}
					else
						JOptionPane.showMessageDialog(null, "Naziv poglavlja nije unet!");
				}
				else
					JOptionPane.showMessageDialog(null, "Niste odabrali poglavlje za izmenu!");
			}
		});
		btnNewButton_1.setBounds(10, 431, 238, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnIzbaciReceptIz = new JButton("Izbaci recept iz poglavlja");
		btnIzbaciReceptIz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selektovanoPoglavlje!=null) {
					if(selektovanRecept != null) {
						List<Recept> tmp = kriterijumDodavanja.get(selektovanoPoglavlje);
						tmp.remove(selektovanRecept);
						kriterijumDodavanja.put(selektovanoPoglavlje, tmp);
						prikazRecepataPoglavlja.removeElement(selektovanRecept);
					}
					else
						JOptionPane.showMessageDialog(null, "Niste odabrali recept za izbacivanje!");
				}
			}
		});
		btnIzbaciReceptIz.setBounds(290, 331, 238, 23);
		frame.getContentPane().add(btnIzbaciReceptIz);
		
		JButton btnUbaciReceptU = new JButton("Ubaci recept u poglavlje");
		btnUbaciReceptU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //klik ubacuje recept u poglavlje
				if(selektovanoPoglavlje!=null) {
					if(selektovanReceptSvih != null) {
						if(!kriterijumDodavanja.get(selektovanoPoglavlje).contains(selektovanReceptSvih)) {
							List<Recept> tmp = kriterijumDodavanja.get(selektovanoPoglavlje);
							tmp.add(selektovanReceptSvih);
							kriterijumDodavanja.put(selektovanoPoglavlje, tmp);
							prikazRecepataPoglavlja.addElement(selektovanReceptSvih);
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Niste odabrali recept za dodavanje!");
				}
			}
		});
		btnUbaciReceptU.setBounds(566, 331, 238, 23);
		frame.getContentPane().add(btnUbaciReceptU);
		
		JButton btnZavrsi = new JButton("Zavrsi");
		btnZavrsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//kreiranje knjige recepata zavrseno, dodaje aktivnom korisniku
				try {
					kontroler.dodajKnjiguRecepata(textFieldNaziv.getText(), kriterijumDodavanja);
					ro();
					frame.dispose();
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "Niste uneli naziv/sadrzaj knjige recepata!");
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Imate knjigu recepata sa istim nazivom!\nDodavanje neuspesno!");
				}
			}
		});
		btnZavrsi.setBounds(566, 470, 238, 23);
		frame.getContentPane().add(btnZavrsi);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ro();
				frame.dispose();
			}
		});
		btnOdustani.setBounds(566, 504, 238, 23);
		frame.getContentPane().add(btnOdustani);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setBounds(222, 485, 306, 20);
		frame.getContentPane().add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		JLabel lblUnesiteNazivKnjige = new JLabel("Unesite naziv knjige recepata:");
		lblUnesiteNazivKnjige.setBounds(10, 488, 181, 14);
		frame.getContentPane().add(lblUnesiteNazivKnjige);
		
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
	
	private void initPregled(){
		if(this.knjigaRecepata != null) {
			this.kriterijumDodavanja = this.knjigaRecepata.getSekcijeRecepti();
			for(Entry e: this.knjigaRecepata.getSekcijeRecepti().entrySet()) {
				prikazPoglavlja.addElement(e.getKey().toString());
			}
			textFieldNaziv.setText(this.knjigaRecepata.getNaziv());
		}
		else {
			prikazPoglavlja.addElement("Poglavlje1");
			kriterijumDodavanja.put("Poglavlje1", new ArrayList<>());
		}
	}
	
	private void initAll() {
		if(this.aplikacija.getTrenutniKorisnik().getRecepti() != null)
			prikazSviRecepti.addAll(this.aplikacija.getTrenutniKorisnik().getRecepti());
	}
	
	private void ro() {
		aplikacija.menadzerKorisnika.removeObserver(this);
	}
	
	public void updatePerformed(UpdateEvent e) {
		JOptionPane.showMessageDialog(null, "Knjiga recepata je uspesno kreirana!");
		//ro();
		//frame.dispose();
	}
}
