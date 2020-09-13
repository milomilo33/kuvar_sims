package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Aplikacija;
import model.Namirnica;
import model.Oprema;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ProzorIzboraOpreme {

	private List<Oprema> oprema;
	private Aplikacija aplikacija;
	private Oprema selektovanaOprema;
	private DefaultListModel filterOprema = new DefaultListModel();
	
	private JDialog frmIzborOpreme;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ProzorIzboraOpreme(List<Oprema> oprema, Aplikacija aplikacija, DefaultListModel filterOprema) {
		this.oprema = oprema;
		this.aplikacija = aplikacija;
		this.filterOprema = filterOprema;
		initialize();
		this.frmIzborOpreme.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzborOpreme = new JDialog();
		frmIzborOpreme.setTitle("Izbor opreme");
		frmIzborOpreme.setResizable(false);
		frmIzborOpreme.setBounds(100, 100, 256, 395);
		frmIzborOpreme.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmIzborOpreme.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 29, 198, 224);
		frmIzborOpreme.getContentPane().add(scrollPane);
		
		List<Oprema> showOprema = this.aplikacija.menadzerOpreme.getOprema();
		JList list = new JList(showOprema.toArray());
		list.setBounds(24, 29, 198, 224);
		scrollPane.setViewportView(list);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selektovanaOprema = (Oprema) list.getSelectedValue();
			}
		});
		
		JButton btnDodajIzabranuOpremu = new JButton("Dodaj izabranu opremu");
		btnDodajIzabranuOpremu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selektovanaOprema!=null && !oprema.contains(selektovanaOprema)) {
					filterOprema.addElement(selektovanaOprema);
					oprema.add(selektovanaOprema);
				}
			}
		});
		btnDodajIzabranuOpremu.setBounds(23, 263, 199, 23);
		frmIzborOpreme.getContentPane().add(btnDodajIzabranuOpremu);
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIzborOpreme.dispose();
			}
		});
		btnPrihvati.setBounds(50, 310, 144, 23);
		frmIzborOpreme.getContentPane().add(btnPrihvati);
		
		JLabel lblIzaberiOpremu = new JLabel("Izaberi opremu:");
		lblIzaberiOpremu.setBounds(74, 4, 109, 14);
		frmIzborOpreme.getContentPane().add(lblIzaberiOpremu);
		
		frmIzborOpreme.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				filterOprema.clear();
			    oprema.clear();
			}
		});
	}

}
