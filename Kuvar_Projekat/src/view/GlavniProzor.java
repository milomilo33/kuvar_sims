package view;

import controller.*;
import event.Observer;
import event.UpdateEvent;
import model.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class GlavniProzor extends JFrame implements Observer{

	private Aplikacija aplikacija;
	private KontrolerGlavnogProzora kontroler;
	private List<Recept> rezultatiPretrage;
	private List<Namirnica> kriterijumPretrageNamirnice;
	private List<Oprema> kriterijumPretrageOprema;
	private List<Kategorija> kriterijumPretrageKategorije;
	
	private DefaultListModel prikazaniRecepti = new DefaultListModel();
	private DefaultListModel filterNamirnice = new DefaultListModel();
	private DefaultListModel filterOprema = new DefaultListModel();
	private DefaultListModel filterKategorije = new DefaultListModel();
	private JList listNamirnice = new JList(filterNamirnice);
	private JList listKategorije = new JList(filterKategorije);
	private JList listOprema = new JList(filterOprema);
	
	private JPanel contentPane;
	private final JButton btnPrijava = new JButton("Prijavi se");
	private final JButton btnRegistracija = new JButton("Registracija");
	private final JLabel lblUkolikoNemateNalog = new JLabel("Ukoliko nemate nalog, registrujte se:");
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnDodavanje = new JMenu("Dodavanje");
	private final JMenuItem mntmDodajRecept = new JMenuItem("Dodaj recept");
	private final JMenu mnPregled = new JMenu("Pregled");
	private final JTextField txtNazivRecepta = new JTextField();
	private final JLabel lblUnesiteNazivRecepta = new JLabel("Unesite naziv recepta:");
	private final JLabel lblKriterijumi = new JLabel("Kriterijumi:");
	private final JScrollPane scrollPaneRecepti = new JScrollPane();
	private final JPanel panel = new JPanel();
	private final JButton btnNamirnice = new JButton("Namirnice");
	private final JButton btnKategorije = new JButton("Kategorije");
	private final JButton btnOprema = new JButton("Oprema");
	private final JMenuItem mntmDodajNamirnice = new JMenuItem("Dodaj namirnice");
	private final JMenuItem mntmDodajOpremu = new JMenuItem("Dodaj opremu");
	private final JMenuItem mntmDodajKnjiguRecepata = new JMenuItem("Dodaj knjigu recepata");
	private final JMenuItem mntmDodajKategoriju = new JMenuItem("Izmeni kategorije");
	private final JMenuItem mntmBookmarkovi = new JMenuItem("Bookmarkovi");
	private final JMenuItem mntmKnjigeRecepata = new JMenuItem("Knjige recepata");
	private final JMenuItem mntmLicniRecepti = new JMenuItem("Licni recepti");
	private final JMenuItem mntmProfil = new JMenuItem("Profil");
	private final JMenuItem mntmPregledPretplacenih = new JMenuItem("Pregled pretplacenih");
	private final JButton btnOdjaviSe = new JButton("Odjavi se");
	private final JLabel lblDobroDosli = new JLabel("Dobro dosli, ");
	private final JMenuItem mntmNajpopularnijiRecepti = new JMenuItem("Najpopularniji recepti");
	private JTextField textFieldVremePripreme;
	private final JList listRecepata = new JList(prikazaniRecepti);
	
	
	public GlavniProzor(Aplikacija aplikacija, KontrolerGlavnogProzora kontroler) {
		setResizable(false);
		txtNazivRecepta.setBounds(212, 90, 368, 27);
		txtNazivRecepta.setToolTipText("Search");
		txtNazivRecepta.setColumns(10);
		this.aplikacija = aplikacija;
		this.aplikacija.menadzerRecepata.addObserver(this);
		this.kontroler = kontroler;
		this.rezultatiPretrage = new ArrayList<Recept>();
		this.kriterijumPretrageNamirnice = new ArrayList<Namirnica>();
		this.kriterijumPretrageOprema = new ArrayList<Oprema>();
		this.kriterijumPretrageKategorije = new ArrayList<Kategorija>();
		initGUI();
	}
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 861, 660);
		
		aplikacija.setTrenutniKorisnik(null);

		setJMenuBar(menuBar);

		menuBar.add(mnDodavanje);

		mnDodavanje.add(mntmDodajRecept);
		mntmDodajRecept.addActionListener(e -> {
			if (proveriUlogovan())
				new ProzorDodavanjaRecepta(aplikacija, new KontrolerProzoraDodavanjaRecepta(aplikacija));
		});

		mnDodavanje.add(mntmDodajNamirnice).addActionListener(e -> {
			//if (proveriUlogovan())
			
		});

		mnDodavanje.add(mntmDodajOpremu).addActionListener(e -> {
			//if (proveriUlogovan())
			
		});

		mnDodavanje.add(mntmDodajKnjiguRecepata).addActionListener(e -> {
			if (proveriUlogovan()) {
				ProzorDodavanjaKnjigeRecepata prozorDodavanjaKnjigeRecepata = new ProzorDodavanjaKnjigeRecepata(aplikacija, null, new KontrolerProzoraDodavanjaKnjigeRecepata(aplikacija));
			}
		});

		mnDodavanje.add(mntmDodajKategoriju);
		mntmDodajKategoriju.addActionListener(e -> {
			if (proveriUlogovan())
				new ProzorIzmeneKategorija(aplikacija, new KontrolerProzorIzmeneKategorija(aplikacija));
		});

		menuBar.add(mnPregled);

		mnPregled.add(mntmNajpopularnijiRecepti);
		mntmNajpopularnijiRecepti.addActionListener(e -> {
			if (proveriUlogovan())
				new ProzorPrikazaListeRecepata(aplikacija, false);
		});

		mnPregled.add(mntmBookmarkovi).addActionListener(e -> {
			if (proveriUlogovan()) {
				aplikacija.menadzerKorisnika.removeObserver(GlavniProzor.this);
				ProzorBookmarkovanja prozorBookmarkovanja = new ProzorBookmarkovanja(aplikacija, new KontrolerBookmarkovanja(aplikacija), null, false);
				prozorBookmarkovanja.setModal(true);
				prozorBookmarkovanja.setVisible(true);
				aplikacija.menadzerKorisnika.addObserver(GlavniProzor.this);
			}
		});

		mnPregled.add(mntmKnjigeRecepata).addActionListener(e -> {
			if (proveriUlogovan()) {
				ProzorPregledaKnjigaRecepata prozorPregledaKnjigaRecepata = new ProzorPregledaKnjigaRecepata(aplikacija);
			}
		});

		mnPregled.add(mntmLicniRecepti);
		mntmLicniRecepti.addActionListener(e -> {
			if (proveriUlogovan())
				new ProzorPrikazaListeRecepata(aplikacija, true);
		});

		mnPregled.add(mntmPregledPretplacenih);
		mntmPregledPretplacenih.addActionListener(e -> {// prikaz liste pretplacenih
			if (proveriUlogovan()) {
				ProzorPregledaPretplacenih prozorPregledaPretplacenih = new ProzorPregledaPretplacenih(aplikacija);
			}
		});

		mnPregled.add(mntmProfil);
		mntmProfil.addActionListener(e -> {
			if (proveriUlogovan())
				new ProzorProfilaKorisnika(aplikacija, new KontrolerProzorProfilaKorisnika(aplikacija));
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		btnPrijava.setBounds(41, 9, 102, 23);
		btnPrijava.addActionListener(arg0 -> {
		});

		textFieldVremePripreme = new JTextField();
		textFieldVremePripreme.setToolTipText("vreme pripreme");
		textFieldVremePripreme.setColumns(10);

		JLabel lblUnesiVremePripreme = new JLabel("Unesi vreme pripreme:");

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Tezina.values()));
		
		JLabel lblIzaberiTezinuRecepta = new JLabel("Izaberi tezinu recepta:");
		
		JButton btnUkloniFiltere = new JButton("Ukloni filtere");
		btnUkloniFiltere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterNamirnice.clear();
				filterOprema.clear();
				filterKategorije.clear();
				kriterijumPretrageNamirnice.clear();;
				kriterijumPretrageOprema.clear();
				kriterijumPretrageKategorije.clear();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(34)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUnesiVremePripreme)
										.addComponent(lblIzaberiTezinuRecepta))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox, 0, 97, Short.MAX_VALUE)
										.addComponent(textFieldVremePripreme, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
								.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap(79, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnNamirnice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnOprema, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnKategorije, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
								.addGap(59))
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap(97, Short.MAX_VALUE)
								.addComponent(lblKriterijumi, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGap(73))
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap(52, Short.MAX_VALUE)
								.addComponent(btnUkloniFiltere, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addGap(42))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(36)
								.addComponent(lblKriterijumi)
								.addGap(37)
								.addComponent(btnNamirnice)
								.addGap(18)
								.addComponent(btnKategorije)
								.addGap(18)
								.addComponent(btnOprema)
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUnesiVremePripreme)
										.addComponent(textFieldVremePripreme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(17)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblIzaberiTezinuRecepta)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(151)
								.addComponent(btnUkloniFiltere)
								.addContainerGap(41, Short.MAX_VALUE))
		);
		btnNamirnice.addActionListener(new ActionListener() { //pritisnuto dugme namirnice, otvara prozor za izbor namirnica
			public void actionPerformed(ActionEvent e) {
				ProzorIzboraNamirnica prozor = new ProzorIzboraNamirnica(kriterijumPretrageNamirnice, aplikacija, filterNamirnice);
			}
		});
		btnOprema.addActionListener(new ActionListener() {  //pritisnuto dugme opreme, otvara prozor za izbor opreme
			public void actionPerformed(ActionEvent e) {
				ProzorIzboraOpreme prozor = new ProzorIzboraOpreme(kriterijumPretrageOprema, aplikacija, filterOprema);
			}
		});
		panel.setBounds(585, 128, 254, 482);
		panel.setLayout(gl_panel);
		listRecepata.addMouseListener(new MouseAdapter() {//klik na selektovan recept, prikazi recept
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(e.getPoint());
		            ProzorPikazaRecepta prozor = new ProzorPikazaRecepta((Recept)prikazaniRecepti.getElementAt(index), aplikacija, new KontrolerProzoraPrikazaRecepta(aplikacija));
		        }
			}
		});
		btnPrijava.addActionListener(new ActionListener() {//pritisnuto dugme prijava
			public void actionPerformed(ActionEvent e) {
				aplikacija.menadzerKorisnika.removeObserver(GlavniProzor.this);
				ProzorZaPrijavu prozorZaPrijavu = new ProzorZaPrijavu(aplikacija, new KontrolerPrijave(aplikacija));
				prozorZaPrijavu.setModal(true);
				prozorZaPrijavu.setVisible(true);
				aplikacija.menadzerKorisnika.addObserver(GlavniProzor.this);
				
				if (aplikacija.getTrenutniKorisnik() != null) {
					lblDobroDosli.setText(lblDobroDosli.getText() + aplikacija.getTrenutniKorisnik());
					lblUkolikoNemateNalog.setVisible(false);
					btnOdjaviSe.setVisible(true);
					btnPrijava.setVisible(false);
					btnRegistracija.setVisible(false);
				}
			}
		});
		btnRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplikacija.menadzerKorisnika.removeObserver(GlavniProzor.this);
				ProzorZaRegistraciju prozorZaRegistraciju = new ProzorZaRegistraciju(aplikacija, new KontrolerRegistracije(aplikacija));
				prozorZaRegistraciju.setModal(true);
				prozorZaRegistraciju.setVisible(true);
				aplikacija.menadzerKorisnika.addObserver(GlavniProzor.this);
			}
		});
		scrollPaneRecepti.setBounds(212, 128, 368, 339);
		
		scrollPaneRecepti.setViewportView(listRecepata);
		contentPane.setLayout(null);
		lblUkolikoNemateNalog.setBounds(10, 41, 236, 14);
		contentPane.add(lblUkolikoNemateNalog);
		contentPane.add(scrollPaneRecepti);
		contentPane.add(panel);
		lblUnesiteNazivRecepta.setBounds(58, 96, 144, 14);
		contentPane.add(lblUnesiteNazivRecepta);
		contentPane.add(txtNazivRecepta);
		btnRegistracija.setBounds(41, 66, 102, 23);
		contentPane.add(btnRegistracija);
		contentPane.add(btnPrijava);
		lblDobroDosli.setBounds(249, 13, 177, 14);
		contentPane.add(lblDobroDosli);
		btnOdjaviSe.setBounds(667, 9, 102, 23);
		btnOdjaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplikacija.setTrenutniKorisnik(null);
				lblDobroDosli.setText("Dobro dosli, ");
				lblUkolikoNemateNalog.setVisible(true);
				btnOdjaviSe.setVisible(false);
				btnPrijava.setVisible(true);
				btnRegistracija.setVisible(true);
			}
		});
		contentPane.add(btnOdjaviSe);
		
		JButton btnPretrazi = new JButton("Pretrazi");
		btnPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // salje kontroleru pretragu
				try {
					kontroler.pretraziRecepte(rezultatiPretrage, txtNazivRecepta.getText(), kriterijumPretrageKategorije,
							kriterijumPretrageNamirnice, (Tezina)comboBox.getSelectedItem(), kriterijumPretrageOprema, textFieldVremePripreme.getText());
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Vreme pripreme mora biti decimalni broj!");
				}
			}
		});
		btnPretrazi.setBounds(615, 92, 120, 23);
		contentPane.add(btnPretrazi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 128, 213, 482);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filteri:");
		lblNewLabel.setBounds(79, 11, 66, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNamirnice = new JLabel("Namirnice:");
		lblNamirnice.setVerticalAlignment(SwingConstants.TOP);
		lblNamirnice.setBounds(10, 37, 66, 14);
		panel_1.add(lblNamirnice);
		
		JScrollPane scrollPaneNamirnice = new JScrollPane();
		scrollPaneNamirnice.setBounds(10, 66, 193, 100);
		panel_1.add(scrollPaneNamirnice);
		
		
		scrollPaneNamirnice.setViewportView(listNamirnice);
		
		JLabel lblOprema = new JLabel("Kategorije:");
		lblOprema.setBounds(10, 187, 77, 14);
		panel_1.add(lblOprema);
		
		JScrollPane scrollPaneKategorije = new JScrollPane();
		scrollPaneKategorije.setBounds(10, 210, 193, 100);
		panel_1.add(scrollPaneKategorije);
		
		
		scrollPaneKategorije.setViewportView(listKategorije);
		
		JLabel lblOprema_1 = new JLabel("Oprema:");
		lblOprema_1.setBounds(10, 338, 77, 14);
		panel_1.add(lblOprema_1);
		
		JScrollPane scrollPaneOprema = new JScrollPane();
		scrollPaneOprema.setBounds(10, 363, 193, 100);
		panel_1.add(scrollPaneOprema);
		
		
		scrollPaneOprema.setViewportView(listOprema);
	}
	
	public Boolean proveriUlogovan() {
		if (aplikacija.getTrenutniKorisnik() == null) {
			JOptionPane.showMessageDialog(null, "Morate biti prijavljeni na sistem!", "Greska", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void updatePerformed(UpdateEvent e) {
		prikazaniRecepti.clear();
		prikazaniRecepti.addAll(rezultatiPretrage);
		rezultatiPretrage.clear();
	}
}
