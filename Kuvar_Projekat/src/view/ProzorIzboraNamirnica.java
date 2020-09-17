package view;


import model.Aplikacija;
import model.Namirnica;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class ProzorIzboraNamirnica {

	private List<Namirnica> namirnice;
	private Aplikacija aplikacija;
	private Namirnica selektovanaNamirnica;
	private JDialog frmIzborNamirnica;
	private DefaultListModel filterNamirnice;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ProzorIzboraNamirnica(List<Namirnica> izlazNamirnice, Aplikacija aplikacija,DefaultListModel filterNamirnice) {
		this.namirnice = izlazNamirnice;
		this.aplikacija = aplikacija;
		this.selektovanaNamirnica = null;
		this.filterNamirnice = filterNamirnice;
		initialize();
		this.frmIzborNamirnica.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmIzborNamirnica = new JDialog();
		frmIzborNamirnica.setTitle("Izbor namirnica");
		frmIzborNamirnica.setResizable(false);
		frmIzborNamirnica.setBounds(100, 100, 266, 413);

		frmIzborNamirnica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frmIzborNamirnica.getContentPane().setLayout(null);

		JButton btnDodajNamirnicuU = new JButton("Dodaj namirnicu u izbor");
		btnDodajNamirnicuU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selektovanaNamirnica!=null && !namirnice.contains(selektovanaNamirnica)) {
					namirnice.add(selektovanaNamirnica);
					filterNamirnice.addElement(selektovanaNamirnica);
				}
			}
		});
		btnDodajNamirnicuU.setBounds(24, 271, 209, 23);
		frmIzborNamirnica.getContentPane().add(btnDodajNamirnicuU);

		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIzborNamirnica.dispose();
				
			}
		});
		btnPrihvati.setBounds(24, 330, 209, 23);
		frmIzborNamirnica.getContentPane().add(btnPrihvati);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 24, 209, 223);
		frmIzborNamirnica.getContentPane().add(scrollPane_1);
		
		List<Namirnica> showNamirnice = this.aplikacija.menadzerNamirnica.getNamirnice();
		JList list = new JList(showNamirnice.toArray());
		scrollPane_1.setViewportView(list);
	
		
		JLabel lblIzaberiNamirnicu = new JLabel("Izaberi namirnicu:");
		lblIzaberiNamirnicu.setBounds(88, 11, 138, 14);
		frmIzborNamirnica.getContentPane().add(lblIzaberiNamirnicu);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanaNamirnica = (Namirnica) list.getSelectedValue();
			}
		});

		
		frmIzborNamirnica.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				filterNamirnice.clear();
			    namirnice.clear();
			}
		});
		}

}
