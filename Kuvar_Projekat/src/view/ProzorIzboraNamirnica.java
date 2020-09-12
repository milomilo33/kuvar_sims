package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

public class ProzorIzboraNamirnica {

	private JFrame frmIzborNamirnica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProzorIzboraNamirnica window = new ProzorIzboraNamirnica();
					window.frmIzborNamirnica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProzorIzboraNamirnica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzborNamirnica = new JFrame();
		frmIzborNamirnica.setTitle("Izbor namirnica");
		frmIzborNamirnica.setResizable(false);
		frmIzborNamirnica.setBounds(100, 100, 266, 555);
		frmIzborNamirnica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIzborNamirnica.getContentPane().setLayout(null);
		
		JButton btnDodajNamirnicuU = new JButton("Dodaj namirnicu u izbor");
		btnDodajNamirnicuU.setBounds(24, 271, 209, 23);
		frmIzborNamirnica.getContentPane().add(btnDodajNamirnicuU);
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.setBounds(50, 475, 162, 23);
		frmIzborNamirnica.getContentPane().add(btnPrihvati);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 24, 209, 223);
		frmIzborNamirnica.getContentPane().add(scrollPane_1);
		
		JList list = new JList();
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
			}
		});
	}
}
