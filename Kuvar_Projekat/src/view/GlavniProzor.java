package view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GlavniProzor extends JFrame {

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
	private final JLabel lblKriterijumi = new JLabel("             Kriterijumi:             ");
	private final JList list = new JList();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel = new JPanel();
	private final JButton btnNamirnice = new JButton("Namirnice");
	private final JButton btnKategorije = new JButton("Kategorije");
	private final JButton btnOprema = new JButton("Oprema");
	private final JButton btnTezina = new JButton("Tezina");
	private final JButton btnVremePripreme = new JButton("Vreme pripreme");
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor frame = new GlavniProzor();
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
	public GlavniProzor() {
		txtNazivRecepta.setToolTipText("Search");
		txtNazivRecepta.setColumns(10);
		initGUI();
	}
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 660);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnDodavanje);
		
		mnDodavanje.add(mntmDodajRecept);
		
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
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{207, 373, 86, 0};
		gbl_contentPane.rowHeights = new int[]{26, 16, 26, 32, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		GridBagConstraints gbc_btnPrijava = new GridBagConstraints();
		gbc_btnPrijava.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnPrijava.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrijava.gridx = 0;
		gbc_btnPrijava.gridy = 0;
		contentPane.add(btnPrijava, gbc_btnPrijava);
		
		GridBagConstraints gbc_lblDobroDosli = new GridBagConstraints();
		gbc_lblDobroDosli.insets = new Insets(0, 0, 5, 5);
		gbc_lblDobroDosli.gridx = 1;
		gbc_lblDobroDosli.gridy = 0;
		contentPane.add(lblDobroDosli, gbc_lblDobroDosli);
		
		GridBagConstraints gbc_btnOdjaviSe = new GridBagConstraints();
		gbc_btnOdjaviSe.insets = new Insets(0, 0, 5, 0);
		gbc_btnOdjaviSe.gridx = 2;
		gbc_btnOdjaviSe.gridy = 0;
		contentPane.add(btnOdjaviSe, gbc_btnOdjaviSe);
		
		GridBagConstraints gbc_lblUkolikoNemateNalog = new GridBagConstraints();
		gbc_lblUkolikoNemateNalog.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblUkolikoNemateNalog.insets = new Insets(0, 0, 5, 5);
		gbc_lblUkolikoNemateNalog.gridx = 0;
		gbc_lblUkolikoNemateNalog.gridy = 1;
		contentPane.add(lblUkolikoNemateNalog, gbc_lblUkolikoNemateNalog);
		
		GridBagConstraints gbc_btnRegistracija = new GridBagConstraints();
		gbc_btnRegistracija.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRegistracija.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistracija.gridx = 0;
		gbc_btnRegistracija.gridy = 2;
		contentPane.add(btnRegistracija, gbc_btnRegistracija);
		
		GridBagConstraints gbc_lblUnesiteNazivRecepta = new GridBagConstraints();
		gbc_lblUnesiteNazivRecepta.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnesiteNazivRecepta.anchor = GridBagConstraints.EAST;
		gbc_lblUnesiteNazivRecepta.gridx = 0;
		gbc_lblUnesiteNazivRecepta.gridy = 3;
		contentPane.add(lblUnesiteNazivRecepta, gbc_lblUnesiteNazivRecepta);
		
		GridBagConstraints gbc_txtNazivRecepta = new GridBagConstraints();
		gbc_txtNazivRecepta.insets = new Insets(0, 0, 5, 5);
		gbc_txtNazivRecepta.fill = GridBagConstraints.BOTH;
		gbc_txtNazivRecepta.gridx = 1;
		gbc_txtNazivRecepta.gridy = 3;
		contentPane.add(txtNazivRecepta, gbc_txtNazivRecepta);
		
		GridBagConstraints gbc_lblKriterijumi = new GridBagConstraints();
		gbc_lblKriterijumi.insets = new Insets(0, 0, 5, 0);
		gbc_lblKriterijumi.gridx = 2;
		gbc_lblKriterijumi.gridy = 3;
		contentPane.add(lblKriterijumi, gbc_lblKriterijumi);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Korisnik već postoji!", "Greška", JOptionPane.ERROR_MESSAGE);
			}
		});
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 4;
		contentPane.add(panel, gbc_panel);
		/*panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));*/
		
		panel.add(btnNamirnice, "2, 2");
		
		panel.add(btnKategorije, "2, 4");
		
		panel.add(btnOprema, "2, 6");
		
		panel.add(btnTezina, "2, 8");
		
		panel.add(btnVremePripreme, "2, 10");
	}

}
