package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.KontrolerPrijave;
import controller.KontrolerRegistracije;
import event.Observer;
import event.UpdateEvent;
import model.Aplikacija;
import utility.DataGenerator;
import utility.IDGenerator;

public class ProzorZaPrijavu extends JDialog implements Observer {
	private Aplikacija aplikacija;
	private KontrolerPrijave kontroler;
	
	private final JLabel lblKorisnickoIme = new JLabel("Korisnicko ime:");
	public final JTextField korisnickoImePolje = new JTextField();
	private final JLabel lblLozinka = new JLabel("Lozinka:");
	public final JPasswordField lozinkaPolje = new JPasswordField();
	private final JButton btnLogin = new JButton("Login");
	
	public static void main(String[] args) {
		try {
			File f = new File("data");
	        if(!f.exists())
	            f.mkdir();
			IDGenerator.INSTANCE.deserialize();
			Aplikacija aplikacija = new Aplikacija();
			DataGenerator.generisiPodatke(aplikacija);
			aplikacija.sacuvajStanje();
			IDGenerator.INSTANCE.serialize();
			KontrolerPrijave kontr = new KontrolerPrijave(aplikacija);
			ProzorZaPrijavu dialog = new ProzorZaPrijavu(aplikacija, kontr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProzorZaPrijavu(Aplikacija aplikacija, KontrolerPrijave kontroler) {
		this.aplikacija = aplikacija;
		this.kontroler = kontroler;
		this.aplikacija.getMenadzerKorisnika().addObserver(this);
		
		korisnickoImePolje.setBounds(104, 56, 228, 20);
		korisnickoImePolje.setColumns(10);
		initGUI();
	}
	
	private void initGUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		lblKorisnickoIme.setHorizontalAlignment(SwingConstants.CENTER);
		lblKorisnickoIme.setBounds(165, 31, 104, 14);
		
		getContentPane().add(lblKorisnickoIme);
		
		getContentPane().add(korisnickoImePolje);
		lblLozinka.setHorizontalAlignment(SwingConstants.CENTER);
		lblLozinka.setBounds(165, 102, 104, 14);
		
		getContentPane().add(lblLozinka);
		lozinkaPolje.setBounds(104, 127, 228, 20);
		
		getContentPane().add(lozinkaPolje);
		
		SubmitListener submitListener = new SubmitListener(this);
		btnLogin.addActionListener(submitListener);
		btnLogin.setBounds(175, 199, 89, 23);
		
		getContentPane().add(btnLogin);
		
		JRootPane rootPane = SwingUtilities.getRootPane(btnLogin); 
		rootPane.setDefaultButton(btnLogin);
		
		this.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 ProzorZaPrijavu prozor = (ProzorZaPrijavu) windowEvent.getSource();
	             prozor.aplikacija.getMenadzerKorisnika().removeObserver(prozor);
	         }        
	    });
	}
	
	public void updatePerformed(UpdateEvent e) {
		JOptionPane.showMessageDialog(null, "Uspesno ste prijavljeni.", "", JOptionPane.INFORMATION_MESSAGE);
	}
	
	class SubmitListener implements ActionListener {
		public ProzorZaPrijavu prozor;
		public SubmitListener(ProzorZaPrijavu prozor) {
			this.prozor = prozor;
		}
		public void actionPerformed(ActionEvent e) {
			String korisnickoIme = this.prozor.korisnickoImePolje.getText();
			String lozinka = String.valueOf(this.prozor.lozinkaPolje.getPassword());
			try {
				prozor.kontroler.prijaviKorisnika(korisnickoIme, lozinka);
				ProzorZaPrijavu.this.aplikacija.getMenadzerKorisnika().removeObserver(ProzorZaPrijavu.this);
				ProzorZaPrijavu.this.dispose();
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}