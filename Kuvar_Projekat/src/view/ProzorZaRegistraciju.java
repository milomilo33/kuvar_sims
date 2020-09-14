package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controller.KontrolerRegistracije;
import event.Observer;
import event.UpdateEvent;
import model.Aplikacija;
import model.Korisnik;
import model.Mesec;
import utility.DataGenerator;
import utility.IDGenerator;
import utility.KorisneMetode;

@SuppressWarnings("serial")
public class ProzorZaRegistraciju extends JFrame implements Observer {

	private JPanel contentPane;
	
	private Aplikacija aplikacija;
	KontrolerRegistracije kontroler;

	private final JLabel lblIme = new JLabel("Ime:");
	final JTextField imePolje = new JTextField();
	private final JLabel lblPrezime = new JLabel("Prezime:");
	final JTextField prezimePolje = new JTextField();
	private final JLabel lblKorisnickoIme = new JLabel("Korisnicko ime:");
	final JTextField korisnickoImePolje = new JTextField();
	private final JLabel lblLozinka = new JLabel("Lozinka:");
	final JPasswordField lozinkaPolje = new JPasswordField();
	private final JLabel lblKucnaAdresa = new JLabel("Kucna adresa:");
	final JTextField adresaPolje = new JTextField();
	private final JLabel lblBrojTelefona = new JLabel("Broj telefona:");
	final JTextField brojTelefonaPolje = new JTextField();
	private final JButton btnRegistruj = new JButton("Registruj se");
	private final JLabel lblDan = new JLabel("Dan rodjenja:");
	final JComboBox comboBoxDan = new JComboBox();
	private final JLabel lblMesec = new JLabel("Mesec rodjenja:");
	final JComboBox comboBoxMesec = new JComboBox();
	private final JLabel lblNewLabel = new JLabel("Godina rodjenja:");
	final JComboBox comboBoxGodina = new JComboBox();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File f = new File("data");
			        if(!f.exists())
			            f.mkdir();
					IDGenerator.INSTANCE.deserialize();
					Aplikacija aplikacija = new Aplikacija();
					DataGenerator.generisiPodatke(aplikacija);
					aplikacija.sacuvajStanje();
					IDGenerator.INSTANCE.serialize();
					KontrolerRegistracije kontr = new KontrolerRegistracije(aplikacija);
					ProzorZaRegistraciju frame = new ProzorZaRegistraciju(aplikacija, kontr);
					frame.setVisible(true);
					aplikacija.sacuvajStanje();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProzorZaRegistraciju(Aplikacija aplikacija, KontrolerRegistracije kontroler) {
		this.aplikacija = aplikacija;
		this.kontroler = kontroler;
		this.aplikacija.getMenadzerKorisnika().addObserver(this);
		
		prezimePolje.setColumns(10);
		imePolje.setColumns(10);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 710);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_lblIme = new GridBagConstraints();
		gbc_lblIme.insets = new Insets(0, 0, 5, 5);
		gbc_lblIme.gridwidth = 3;
		gbc_lblIme.gridx = 0;
		gbc_lblIme.gridy = 1;
		contentPane.add(lblIme, gbc_lblIme);
		
		GridBagConstraints gbc_imePolje = new GridBagConstraints();
		gbc_imePolje.insets = new Insets(0, 0, 5, 0);
		gbc_imePolje.fill = GridBagConstraints.HORIZONTAL;
		gbc_imePolje.gridx = 4;
		gbc_imePolje.gridy = 1;
		contentPane.add(imePolje, gbc_imePolje);
		
		GridBagConstraints gbc_lblPrezime = new GridBagConstraints();
		gbc_lblPrezime.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrezime.gridx = 1;
		gbc_lblPrezime.gridy = 3;
		contentPane.add(lblPrezime, gbc_lblPrezime);
		
		GridBagConstraints gbc_prezimePolje = new GridBagConstraints();
		gbc_prezimePolje.insets = new Insets(0, 0, 5, 0);
		gbc_prezimePolje.fill = GridBagConstraints.HORIZONTAL;
		gbc_prezimePolje.gridx = 4;
		gbc_prezimePolje.gridy = 3;
		contentPane.add(prezimePolje, gbc_prezimePolje);
		
		GridBagConstraints gbc_lblKorisnickoIme = new GridBagConstraints();
		gbc_lblKorisnickoIme.insets = new Insets(0, 0, 5, 5);
		gbc_lblKorisnickoIme.gridx = 1;
		gbc_lblKorisnickoIme.gridy = 5;
		contentPane.add(lblKorisnickoIme, gbc_lblKorisnickoIme);
		korisnickoImePolje.setColumns(10);
		
		GridBagConstraints gbc_korisnickoImePolje = new GridBagConstraints();
		gbc_korisnickoImePolje.insets = new Insets(0, 0, 5, 0);
		gbc_korisnickoImePolje.fill = GridBagConstraints.HORIZONTAL;
		gbc_korisnickoImePolje.gridx = 4;
		gbc_korisnickoImePolje.gridy = 5;
		contentPane.add(korisnickoImePolje, gbc_korisnickoImePolje);
		
		GridBagConstraints gbc_lblLozinka = new GridBagConstraints();
		gbc_lblLozinka.insets = new Insets(0, 0, 5, 5);
		gbc_lblLozinka.gridx = 1;
		gbc_lblLozinka.gridy = 7;
		contentPane.add(lblLozinka, gbc_lblLozinka);
		
		GridBagConstraints gbc_lozinkaPolje = new GridBagConstraints();
		gbc_lozinkaPolje.insets = new Insets(0, 0, 5, 0);
		gbc_lozinkaPolje.fill = GridBagConstraints.HORIZONTAL;
		gbc_lozinkaPolje.gridx = 4;
		gbc_lozinkaPolje.gridy = 7;
		contentPane.add(lozinkaPolje, gbc_lozinkaPolje);
		
		GridBagConstraints gbc_lblKucnaAdresa = new GridBagConstraints();
		gbc_lblKucnaAdresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblKucnaAdresa.gridx = 1;
		gbc_lblKucnaAdresa.gridy = 9;
		contentPane.add(lblKucnaAdresa, gbc_lblKucnaAdresa);
		adresaPolje.setColumns(10);
		
		GridBagConstraints gbc_adresaPolje = new GridBagConstraints();
		gbc_adresaPolje.insets = new Insets(0, 0, 5, 0);
		gbc_adresaPolje.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresaPolje.gridx = 4;
		gbc_adresaPolje.gridy = 9;
		contentPane.add(adresaPolje, gbc_adresaPolje);
		
		GridBagConstraints gbc_lblBrojTelefona = new GridBagConstraints();
		gbc_lblBrojTelefona.insets = new Insets(0, 0, 5, 5);
		gbc_lblBrojTelefona.gridx = 1;
		gbc_lblBrojTelefona.gridy = 11;
		contentPane.add(lblBrojTelefona, gbc_lblBrojTelefona);
		brojTelefonaPolje.setColumns(10);
		
		GridBagConstraints gbc_brojTelefonaPolje = new GridBagConstraints();
		gbc_brojTelefonaPolje.insets = new Insets(0, 0, 5, 0);
		gbc_brojTelefonaPolje.fill = GridBagConstraints.HORIZONTAL;
		gbc_brojTelefonaPolje.gridx = 4;
		gbc_brojTelefonaPolje.gridy = 11;
		contentPane.add(brojTelefonaPolje, gbc_brojTelefonaPolje);
		
		GridBagConstraints gbc_lblDan = new GridBagConstraints();
		gbc_lblDan.insets = new Insets(0, 0, 5, 5);
		gbc_lblDan.gridx = 1;
		gbc_lblDan.gridy = 13;
		contentPane.add(lblDan, gbc_lblDan);
		
		GridBagConstraints gbc_comboBoxDan = new GridBagConstraints();
		gbc_comboBoxDan.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxDan.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDan.gridx = 4;
		gbc_comboBoxDan.gridy = 13;
		comboBoxDan.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		contentPane.add(comboBoxDan, gbc_comboBoxDan);
		
		GridBagConstraints gbc_lblMesec = new GridBagConstraints();
		gbc_lblMesec.insets = new Insets(0, 0, 5, 5);
		gbc_lblMesec.gridx = 1;
		gbc_lblMesec.gridy = 15;
		contentPane.add(lblMesec, gbc_lblMesec);
		
		GridBagConstraints gbc_comboBoxMesec = new GridBagConstraints();
		gbc_comboBoxMesec.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxMesec.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMesec.gridx = 4;
		gbc_comboBoxMesec.gridy = 15;
		comboBoxMesec.setModel(new DefaultComboBoxModel(Mesec.values()));
		contentPane.add(comboBoxMesec, gbc_comboBoxMesec);
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 17;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_comboBoxGodina = new GridBagConstraints();
		gbc_comboBoxGodina.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxGodina.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGodina.gridx = 4;
		gbc_comboBoxGodina.gridy = 17;
		comboBoxGodina.setModel(new DefaultComboBoxModel(new String[] {"1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019"}));
		contentPane.add(comboBoxGodina, gbc_comboBoxGodina);
		
		GridBagConstraints gbc_btnRegistruj = new GridBagConstraints();
		gbc_btnRegistruj.gridwidth = 5;
		gbc_btnRegistruj.gridx = 0;
		gbc_btnRegistruj.gridy = 23;
		SubmitListener submitListener = new SubmitListener(this);
		btnRegistruj.addActionListener(submitListener);
		contentPane.add(btnRegistruj, gbc_btnRegistruj);
		JRootPane rootPane = SwingUtilities.getRootPane(btnRegistruj); 
		rootPane.setDefaultButton(btnRegistruj);
	}

	public void updatePerformed(UpdateEvent e) {
		Boolean uspesnoRegistrovan = (Boolean) e.getSource();
		if (!uspesnoRegistrovan)
			JOptionPane.showMessageDialog(null, "Korisnik vec postoji!", "Greska", JOptionPane.ERROR_MESSAGE);
		else {
			JOptionPane.showMessageDialog(null, "Uspesno ste se registrovali.", "", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
		
		/*if (uspesnoRegistrovan) {
			List<Korisnik> lista = aplikacija.getMenadzerKorisnika().getKorisnici();
			Korisnik noviKorisnik = lista.get(lista.size() - 1);
			JOptionPane.showMessageDialog(null, "Vi ste: " + noviKorisnik.getUsername(), "", JOptionPane.INFORMATION_MESSAGE);
		}*/
	}
}

class SubmitListener implements ActionListener {
	public ProzorZaRegistraciju prozorZaRegistraciju;
	
	public SubmitListener(ProzorZaRegistraciju prozor) {
		this.prozorZaRegistraciju = prozor;
	}
	
	public void actionPerformed(ActionEvent e) {
		boolean shouldSubmit = true;
		
		String ime = prozorZaRegistraciju.imePolje.getText();
		String prezime = prozorZaRegistraciju.prezimePolje.getText();
		String korisnickoIme = prozorZaRegistraciju.korisnickoImePolje.getText();
		String lozinka = String.valueOf(prozorZaRegistraciju.lozinkaPolje.getPassword());
		String adresa = prozorZaRegistraciju.adresaPolje.getText();
		String brojTelefona = prozorZaRegistraciju.brojTelefonaPolje.getText();
		
		String danRodjenja = prozorZaRegistraciju.comboBoxDan.getSelectedItem().toString();
		String mesecRodjenja = String.valueOf(((Mesec) prozorZaRegistraciju.comboBoxMesec.getSelectedItem()).ordinal() + 1);
		String godinaRodjenja = prozorZaRegistraciju.comboBoxGodina.getSelectedItem().toString();
		StringBuilder strb = new StringBuilder();
		strb.append(godinaRodjenja);
		strb.append('-');
		if (Integer.valueOf(mesecRodjenja) < 10) strb.append(0);
		strb.append(mesecRodjenja);
		strb.append('-');
		if (Integer.valueOf(danRodjenja) < 10) strb.append(0);
		strb.append(danRodjenja);
		String datumRodjenjaStr = strb.toString();
		System.out.println(datumRodjenjaStr);
		LocalDate datumRodjenja = null;
		try {
			datumRodjenja = LocalDate.parse(datumRodjenjaStr);
		} catch (DateTimeParseException ex) {
			JOptionPane.showMessageDialog(null, "Nevalidan datum unesen!", "Greska", JOptionPane.ERROR_MESSAGE);
			shouldSubmit = false;
		}
		
		if (!KorisneMetode.isNameValid(ime)) {
			prozorZaRegistraciju.imePolje.setBackground(Color.RED);
			shouldSubmit = false;
		} else
			prozorZaRegistraciju.imePolje.setBackground(Color.WHITE);
		
		if (!KorisneMetode.isNameValid(prezime)) {
			prozorZaRegistraciju.prezimePolje.setBackground(Color.RED);
			shouldSubmit = false;
		} else
			prozorZaRegistraciju.prezimePolje.setBackground(Color.WHITE);
		
		if (!KorisneMetode.isNotAnEmptyOrNullString(korisnickoIme)) {
			prozorZaRegistraciju.korisnickoImePolje.setBackground(Color.RED);
			shouldSubmit = false;
		} else
			prozorZaRegistraciju.korisnickoImePolje.setBackground(Color.WHITE);
		
		if (!KorisneMetode.isNotAnEmptyOrNullString(lozinka)) {
			prozorZaRegistraciju.lozinkaPolje.setBackground(Color.RED);
			shouldSubmit = false;
		} else
			prozorZaRegistraciju.lozinkaPolje.setBackground(Color.WHITE);
		
		if (!KorisneMetode.isNotAnEmptyOrNullString(adresa)) {
			prozorZaRegistraciju.adresaPolje.setBackground(Color.RED);
			shouldSubmit = false;
		} else
			prozorZaRegistraciju.adresaPolje.setBackground(Color.WHITE);
		
		if (!KorisneMetode.isNotAnEmptyOrNullString(brojTelefona)) {
			prozorZaRegistraciju.brojTelefonaPolje.setBackground(Color.RED);
			shouldSubmit = false;
		} else
			prozorZaRegistraciju.brojTelefonaPolje.setBackground(Color.WHITE);
		
		if (!shouldSubmit)
			JOptionPane.showMessageDialog(null, "Pogresno uneseni podaci!", "Greska", JOptionPane.ERROR_MESSAGE);
		else 
			prozorZaRegistraciju.kontroler.dodajNovogKorisnika(ime, prezime, datumRodjenja, korisnickoIme, lozinka, brojTelefona, adresa);
	}
}
