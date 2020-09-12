package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ProzorIzboraOpreme {

	private JFrame frmIzborOpreme;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProzorIzboraOpreme window = new ProzorIzboraOpreme();
					window.frmIzborOpreme.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProzorIzboraOpreme() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzborOpreme = new JFrame();
		frmIzborOpreme.setTitle("Izbor opreme");
		frmIzborOpreme.setResizable(false);
		frmIzborOpreme.setBounds(100, 100, 256, 395);
		frmIzborOpreme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIzborOpreme.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(24, 29, 198, 224);
		frmIzborOpreme.getContentPane().add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 29, 198, 224);
		frmIzborOpreme.getContentPane().add(scrollPane);
		
		JButton btnDodajIzabranuOpremu = new JButton("Dodaj izabranu opremu");
		btnDodajIzabranuOpremu.setBounds(23, 263, 199, 23);
		frmIzborOpreme.getContentPane().add(btnDodajIzabranuOpremu);
		
		JButton btnPrihvati = new JButton("Prihvati");
		btnPrihvati.setBounds(50, 310, 144, 23);
		frmIzborOpreme.getContentPane().add(btnPrihvati);
		
		JLabel lblIzaberiOpremu = new JLabel("Izaberi opremu:");
		lblIzaberiOpremu.setBounds(74, 4, 109, 14);
		frmIzborOpreme.getContentPane().add(lblIzaberiOpremu);
	}

}
