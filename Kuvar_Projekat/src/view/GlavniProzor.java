package view;

import controller.KontrolerGlavnogProzora;
import controller.KontrolerProzoraDodavanjaRecepta;
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
	private List<Recept> rezultatPretrage;
	private List<Namirnica> kriterijumPretrageNamirnice;
	private List<Oprema> kriterijumPretrageOprema;
	
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
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel = new JPanel();
	private final JButton btnNamirnice = new JButton("Namirnice");
	private final JButton btnKategorije = new JButton("Kategorije");
	private final JButton btnOprema = new JButton("Oprema");
	private final JMenuItem mntmDodajNamirnice = new JMenuItem("Dodaj namirnice");
	private final JMenuItem mntmDodajOpremu = new JMenuItem("Dodaj opremu");
	private final JMenuItem mntmDodajKnjiguRecepata = new JMenuItem("Dodaj knjigu recepata");
	private final JMenuItem mntmDodajKategoriju = new JMenuItem("Dodaj kategoriju");
	private final JMenuItem mntmBookmarkovi = new JMenuItem("Bookmarkovi");
	private final JMenuItem mntmKnjigeRecepata = new JMenuItem("Knjige recepata");
	private final JMenuItem mntmLicniRecepti = new JMenuItem("Licni recepti");
	private final JMenuItem mntmProfil = new JMenuItem("Profil");
	private final JMenuItem mntmPregledPretplacenih = new JMenuItem("Pregled pretplacenih");
	private final JButton btnOdjaviSe = new JButton("Odjavi se");
	private final JLabel lblDobroDosli = new JLabel("Dobro dosli, ");
	private final JMenuItem mntmNajpopularnijiRecepti = new JMenuItem("Najpopularniji recepti");
	private JTextField textFieldVremePripreme;
	private final JList list = new JList();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GlavniProzor(Aplikacija aplikacija, KontrolerGlavnogProzora kontroler) {
		setResizable(false);
		txtNazivRecepta.setBounds(212, 90, 368, 27);
		txtNazivRecepta.setToolTipText("Search");
		txtNazivRecepta.setColumns(10);
		this.aplikacija = aplikacija;
		this.kontroler = kontroler;
		this.rezultatPretrage = new ArrayList<>();
		this.kriterijumPretrageNamirnice = new ArrayList<>();
		this.kriterijumPretrageOprema = new ArrayList<>();
		initGUI();
	}
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 660);

		setJMenuBar(menuBar);

		menuBar.add(mnDodavanje);

		mnDodavanje.add(mntmDodajRecept);
		mntmDodajRecept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProzorDodavanjaRecepta prozorDodavanjaRecepta = new ProzorDodavanjaRecepta(aplikacija, new KontrolerProzoraDodavanjaRecepta(aplikacija));
			}
		});

		mnDodavanje.add(mntmDodajNamirnice);

		mnDodavanje.add(mntmDodajOpremu);

		mnDodavanje.add(mntmDodajKnjiguRecepata);

		mnDodavanje.add(mntmDodajKategoriju);

		menuBar.add(mnPregled);
		
		mnPregled.add(mntmNajpopularnijiRecepti);
		
		mnPregled.add(mntmBookmarkovi);
		
		mnPregled.add(mntmKnjigeRecepata);
		
		mnPregled.add(mntmLicniRecepti);
		
		mnPregled.add(mntmPregledPretplacenih);
		
		mnPregled.add(mntmProfil);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		btnPrijava.setBounds(54, 9, 75, 23);
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		textFieldVremePripreme = new JTextField();
		textFieldVremePripreme.setToolTipText("vreme pripreme");
		textFieldVremePripreme.setColumns(10);
		
		JLabel lblUnesiVremePripreme = new JLabel("Unesi vreme pripreme:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Tezina.values()));
		
		JLabel lblIzaberiTezinuRecepta = new JLabel("Izaberi tezinu recepta:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(92)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNamirnice)
								.addComponent(btnKategorije, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnOprema, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUnesiVremePripreme)
								.addComponent(lblIzaberiTezinuRecepta))
							.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textFieldVremePripreme, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))))
					.addContainerGap(18, GroupLayout.PREFERRED_SIZE))
		);
		btnNamirnice.addActionListener(new ActionListener() { //pritisnuto dugme namirnice, otvara prozor za izbor namirnica
			public void actionPerformed(ActionEvent e) {
				kriterijumPretrageNamirnice.add(new Namirnica());
				ProzorIzboraNamirnica prozor = new ProzorIzboraNamirnica(kriterijumPretrageNamirnice);

			}
		});
		btnOprema.addActionListener(new ActionListener() {  //pritisnuto dugme opreme, otvara prozor za izbor opreme
			public void actionPerformed(ActionEvent e) {
				ProzorIzboraOpreme.main(null);
			}
		});
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNamirnice)
					.addGap(33)
					.addComponent(btnKategorije)
					.addGap(37)
					.addComponent(btnOprema)
					.addGap(60)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldVremePripreme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUnesiVremePripreme))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIzaberiTezinuRecepta))
					.addContainerGap(219, Short.MAX_VALUE))
		);
		panel.setBounds(585, 128, 254, 482);
		panel.setLayout(gl_panel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("wooooooooooooooooow");
			}
		});
		scrollPane.setBounds(212, 128, 368, 339);
		
		scrollPane.setViewportView(list);
		contentPane.setLayout(null);
		lblUkolikoNemateNalog.setBounds(5, 37, 176, 14);
		contentPane.add(lblUkolikoNemateNalog);
		contentPane.add(scrollPane);
		contentPane.add(panel);
		lblUnesiteNazivRecepta.setBounds(58, 96, 108, 14);
		contentPane.add(lblUnesiteNazivRecepta);
		contentPane.add(txtNazivRecepta);
		btnRegistracija.setBounds(48, 56, 89, 23);
		contentPane.add(btnRegistracija);
		contentPane.add(btnPrijava);
		lblDobroDosli.setBounds(366, 13, 60, 14);
		contentPane.add(lblDobroDosli);
		btnOdjaviSe.setBounds(679, 9, 77, 23);
		contentPane.add(btnOdjaviSe);
		lblKriterijumi.setBounds(685, 96, 84, 14);
		contentPane.add(lblKriterijumi);
	}
	
	
	public void updatePerformed(UpdateEvent e) {
		
	}
}
