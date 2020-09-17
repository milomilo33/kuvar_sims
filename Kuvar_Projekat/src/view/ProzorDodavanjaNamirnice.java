package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.KontrolerDodavanjaNamirnice;
import model.Aplikacija;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProzorDodavanjaNamirnice extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNaziv;
	
	private Aplikacija aplikacija;
	private KontrolerDodavanjaNamirnice kontroler;

	/**
	 * Create the dialog.
	 */
	public ProzorDodavanjaNamirnice(Aplikacija aplikacija, KontrolerDodavanjaNamirnice kontroler) {
		this.aplikacija = aplikacija;
		this.kontroler = kontroler;
		
		setResizable(false);
		setBounds(100, 100, 335, 153);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNaziv = new JTextField();
		txtNaziv.setBounds(10, 42, 299, 20);
		contentPanel.add(txtNaziv);
		txtNaziv.setColumns(10);
		
		JLabel lblUnesiteNaziv = new JLabel("Unesite naziv namirnice:");
		lblUnesiteNaziv.setBounds(10, 17, 164, 14);
		contentPanel.add(lblUnesiteNaziv);
		
		JButton btnDodaj = new JButton("Dodaj namirnicu");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv = txtNaziv.getText();
				try {
					kontroler.dodajNamirnicu(naziv);
					JOptionPane.showMessageDialog(null, "Uspesno dodata namirnica.", "", JOptionPane.INFORMATION_MESSAGE);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				dispose();
			}
		});
		btnDodaj.setBounds(90, 73, 144, 23);
		contentPanel.add(btnDodaj);
	}
}
