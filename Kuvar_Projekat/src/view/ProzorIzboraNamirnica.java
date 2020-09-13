package view;

<<<<<<< Updated upstream
=======
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;

import model.Aplikacija;
>>>>>>> Stashed changes
import model.Namirnica;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
<<<<<<< Updated upstream
import javax.swing.event.ListSelectionListener;
import java.util.List;
=======
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
>>>>>>> Stashed changes

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
		frmIzborNamirnica.setBounds(100, 100, 266, 555);
<<<<<<< Updated upstream
		frmIzborNamirnica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
=======
		frmIzborNamirnica.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
>>>>>>> Stashed changes
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
		btnPrihvati.setBounds(50, 475, 162, 23);
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
		
		JCheckBox chckbxUkljuciPersonalnePreference = new JCheckBox("Ukljuci personalne preference");
		chckbxUkljuciPersonalnePreference.setBounds(6, 327, 227, 23);
		frmIzborNamirnica.getContentPane().add(chckbxUkljuciPersonalnePreference);
		
		JCheckBox chckbxPrikaziRecepteKoji = new JCheckBox("Ukljuci samo izabrane sastojke");
		chckbxPrikaziRecepteKoji.setActionCommand("");
		chckbxPrikaziRecepteKoji.setBounds(6, 387, 220, 47);
		frmIzborNamirnica.getContentPane().add(chckbxPrikaziRecepteKoji);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanaNamirnica = (Namirnica) list.getSelectedValue();
			}
		});
<<<<<<< Updated upstream

	}
=======
		
		frmIzborNamirnica.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				filterNamirnice.clear();
			    namirnice.clear();
			}
		});
		}
>>>>>>> Stashed changes
}
