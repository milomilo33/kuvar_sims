package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Aplikacija;
import model.Recept;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ProzorPikazaRecepta {

	private Recept recept;
	private Aplikacija aplikacija;
	
	private JDialog frmRecept;
	private JTextField textFieldNaziv;
	private JTextField textFieldTezina;
	private JTextField textFieldVremePripreme;
	private JTextField textFieldAutor;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ProzorPikazaRecepta(Recept recept, Aplikacija aplikacija) {
		this.recept = recept;
		this.aplikacija = aplikacija;
		initialize();
		frmRecept.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRecept = new JDialog();
		frmRecept.setResizable(false);
		frmRecept.setTitle("Recept");
		frmRecept.setBounds(100, 100, 805, 536);
		frmRecept.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmRecept.getContentPane().setLayout(null);
		
		JLabel lblNaziv = new JLabel("Naziv recepta:");
		lblNaziv.setBounds(10, 11, 102, 14);
		frmRecept.getContentPane().add(lblNaziv);
		
		JLabel lblTezinaRecepta = new JLabel("Tezina recepta: ");
		lblTezinaRecepta.setBounds(10, 34, 102, 14);
		frmRecept.getContentPane().add(lblTezinaRecepta);
		
		JLabel lblVremePripremeRecepta = new JLabel("Vreme pripreme recepta:");
		lblVremePripremeRecepta.setBounds(10, 59, 162, 14);
		frmRecept.getContentPane().add(lblVremePripremeRecepta);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setEditable(false);
		textFieldNaziv.setBounds(182, 8, 427, 20);
		frmRecept.getContentPane().add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		textFieldNaziv.setText(this.recept.getNaziv());
		
		textFieldTezina = new JTextField();
		textFieldTezina.setEditable(false);
		textFieldTezina.setBounds(182, 31, 123, 20);
		frmRecept.getContentPane().add(textFieldTezina);
		textFieldTezina.setColumns(10);
		textFieldTezina.setText(this.recept.getTezina().toString());
		
		textFieldVremePripreme = new JTextField();
		textFieldVremePripreme.setEditable(false);
		textFieldVremePripreme.setBounds(182, 56, 123, 20);
		frmRecept.getContentPane().add(textFieldVremePripreme);
		textFieldVremePripreme.setColumns(10);
		textFieldVremePripreme.setText(this.recept.getVremePripreme().toString());
		
		JScrollPane scrollPaneNamirnice = new JScrollPane();
		scrollPaneNamirnice.setBounds(10, 125, 231, 159);
		frmRecept.getContentPane().add(scrollPaneNamirnice);
		
		JList listNamirnice = new JList();
		scrollPaneNamirnice.setViewportView(listNamirnice);
		
		JScrollPane scrollPaneOprema = new JScrollPane();
		scrollPaneOprema.setBounds(278, 125, 241, 159);
		frmRecept.getContentPane().add(scrollPaneOprema);
		
		JList listOprema = new JList();
		scrollPaneOprema.setViewportView(listOprema);
		
		JScrollPane scrollPaneKategorije = new JScrollPane();
		scrollPaneKategorije.setBounds(555, 125, 224, 159);
		frmRecept.getContentPane().add(scrollPaneKategorije);
		
		JList listKategorije = new JList();
		scrollPaneKategorije.setViewportView(listKategorije);
		
		JLabel lblPotrebneNamirnice = new JLabel("Potrebne namirnice:");
		lblPotrebneNamirnice.setBounds(64, 100, 153, 14);
		frmRecept.getContentPane().add(lblPotrebneNamirnice);
		
		JLabel lblPotrebnaOprema = new JLabel("Potrebna oprema:");
		lblPotrebnaOprema.setBounds(344, 100, 144, 14);
		frmRecept.getContentPane().add(lblPotrebnaOprema);
		
		JLabel lblKategorijeRecepta = new JLabel("Kategorije recepta:");
		lblKategorijeRecepta.setBounds(623, 100, 134, 14);
		frmRecept.getContentPane().add(lblKategorijeRecepta);
		
		JLabel lblOpisRecepta = new JLabel("Opis recepta:");
		lblOpisRecepta.setBounds(10, 302, 123, 14);
		frmRecept.getContentPane().add(lblOpisRecepta);
		
		JTextArea textAreaOpis = new JTextArea();
		textAreaOpis.setEditable(false);
		textAreaOpis.setBounds(10, 327, 511, 159);
		frmRecept.getContentPane().add(textAreaOpis);
		textAreaOpis.setText(this.recept.getOpis());
		
		textFieldAutor = new JTextField();
		textFieldAutor.setEditable(false);
		textFieldAutor.setBounds(477, 56, 302, 20);
		frmRecept.getContentPane().add(textFieldAutor);
		textFieldAutor.setColumns(10);
		textFieldAutor.setText(this.recept.getAutor().getUsername());
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(424, 59, 64, 14);
		frmRecept.getContentPane().add(lblAutor);
		
		JButton btnKomentarisi = new JButton("Komentarisi");
		btnKomentarisi.setBounds(576, 328, 181, 23);
		frmRecept.getContentPane().add(btnKomentarisi);
		
		JButton btnPretplatiSe = new JButton("Pretplati se");
		btnPretplatiSe.setBounds(576, 378, 181, 23);
		frmRecept.getContentPane().add(btnPretplatiSe);
		
		JButton btnNewButton = new JButton("Potvrdi");
		btnNewButton.setBounds(576, 431, 181, 23);
		frmRecept.getContentPane().add(btnNewButton);
	}
}
