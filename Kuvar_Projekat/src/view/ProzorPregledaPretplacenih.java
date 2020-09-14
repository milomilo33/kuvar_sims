package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.Aplikacija;
import model.Korisnik;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ProzorPregledaPretplacenih {

	private Aplikacija aplikacija;
	
	private JDialog frame;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public ProzorPregledaPretplacenih(Aplikacija aplikacija) {
		this.aplikacija = aplikacija;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setBounds(100, 100, 306, 475);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPanePretplaceni = new JScrollPane();
		scrollPanePretplaceni.setBounds(10, 11, 270, 328);
		frame.getContentPane().add(scrollPanePretplaceni);
		
		List<String> listDisplay = new ArrayList<>();
		listDisplay.add("Niste nikome pretplaceni.");
		if(aplikacija.getTrenutniKorisnik().getJePretplacen() != null) {
			listDisplay.clear();
			for(Korisnik k: aplikacija.getTrenutniKorisnik().getJePretplacen())
				listDisplay.add(k.toString());
		}
		JList listPretplaceni = new JList(listDisplay.toArray());
		scrollPanePretplaceni.setViewportView(listPretplaceni);
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnPrihvati.setBounds(10, 380, 270, 23);
		frame.getContentPane().add(btnPrihvati);
	}
}
