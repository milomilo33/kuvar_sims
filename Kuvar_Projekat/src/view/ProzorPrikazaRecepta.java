package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.KontrolerBookmarkovanja;
import controller.KontrolerProzoraKomentarisanjaRecepta;
import controller.KontrolerProzoraPrikazaRecepta;
import event.Observer;
import event.UpdateEvent;
import model.Aplikacija;
import model.Recept;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.event.ActionEvent;

public class ProzorPrikazaRecepta implements Observer{

	private Recept recept;
	private Aplikacija aplikacija;
	private AtomicBoolean porukaPretplate = new AtomicBoolean();
	private KontrolerProzoraPrikazaRecepta kontroler;
	private boolean tip;
	
	private JDialog frmRecept;
	private JTextField textFieldNaziv;
	private JTextField textFieldTezina;
	private JTextField textFieldVremePripreme;
	private JTextField textFieldAutor;
	private JButton btnPretplatiSe = new JButton("Pretplati se");
	private JButton btnKomentarisi = new JButton("Komentarisi");

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ProzorPrikazaRecepta(Recept recept, Aplikacija aplikacija, KontrolerProzoraPrikazaRecepta kontroler) {
		this.recept = recept;
		this.aplikacija = aplikacija;
		this.kontroler = kontroler;
		this.aplikacija.menadzerKorisnika.addObserver(this);
		initialize();
		this.tip = false;
		if(this.kontroler.verifikacijaRecepta(recept))
			this.tip = true;
		postaviPogled();
		frmRecept.setModal(true);
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
		
		JList listNamirnice = new JList(this.recept.getNamirniceSaSastojanjem().keySet().toArray());
		scrollPaneNamirnice.setViewportView(listNamirnice);
		
		JScrollPane scrollPaneOprema = new JScrollPane();
		scrollPaneOprema.setBounds(278, 125, 241, 159);
		frmRecept.getContentPane().add(scrollPaneOprema);
		
		JList listOprema = new JList(this.recept.getOprema().toArray());
		scrollPaneOprema.setViewportView(listOprema);
		
		JScrollPane scrollPaneKategorije = new JScrollPane();
		scrollPaneKategorije.setBounds(555, 125, 224, 159);
		frmRecept.getContentPane().add(scrollPaneKategorije);
		
		JList listKategorije = new JList(this.recept.getKategorije().toArray());
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
		
		btnKomentarisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // otvara prozor za komentarisanje
				System.out.println(recept.getKomentari());
				ProzorKomentarisanjaRecepta prozorKomentarisanjeRecepta = new ProzorKomentarisanjaRecepta(aplikacija, recept, new KontrolerProzoraKomentarisanjaRecepta(aplikacija), tip);
			}
		});
		btnKomentarisi.setBounds(576, 328, 181, 23);
		frmRecept.getContentPane().add(btnKomentarisi);
		
		
		btnPretplatiSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontroler.pretplatiSe(recept.getAutor(), porukaPretplate);
			}
		});
		btnPretplatiSe.setBounds(576, 419, 181, 23);
		frmRecept.getContentPane().add(btnPretplatiSe);
		
		JButton btnNewButton = new JButton("Potvrdi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ro();
				frmRecept.dispose();
			}
		});
		btnNewButton.setBounds(576, 463, 181, 23);
		frmRecept.getContentPane().add(btnNewButton);
		
		JButton btnBookmarkuj = new JButton("Bookmarkuj");
		btnBookmarkuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//OVDE DODAJ ZA BOOKMARKE STVAR, imas atribute [aplikacija] i [recept]-recept koji se bookmarkuje
				AtomicBoolean pronadjen = new AtomicBoolean(false);
				aplikacija.getTrenutniKorisnik().proveriReceptBookmarkovan(recept, pronadjen);
				if (pronadjen.get()) {
					JOptionPane.showMessageDialog(null, "Recept je vec bookmarkovan!", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				aplikacija.menadzerKorisnika.removeObserver(ProzorPrikazaRecepta.this);
				KontrolerBookmarkovanja kontrolerBookmarkovanja = new KontrolerBookmarkovanja(aplikacija);
				ProzorBookmarkovanja prozorBookmarkovanja = new ProzorBookmarkovanja(aplikacija, kontrolerBookmarkovanja, recept, true);
				prozorBookmarkovanja.setModal(true);
				prozorBookmarkovanja.setVisible(true);
				aplikacija.menadzerKorisnika.addObserver(ProzorPrikazaRecepta.this);
			}
		});
		btnBookmarkuj.setBounds(576, 372, 181, 23);
		frmRecept.getContentPane().add(btnBookmarkuj);
		
		frmRecept.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                ro();
                frmRecept.dispose();
            }
        });
	}
	
	private void ro() {
		aplikacija.menadzerKorisnika.removeObserver(this);
	}
	
	private void postaviPogled() {
		if(tip) {
			btnPretplatiSe.setVisible(false);
			btnKomentarisi.setText("Komentari");
		}
	}
	
	public void updatePerformed(UpdateEvent e) {
		if(porukaPretplate!=null)
			if(porukaPretplate.get())
				JOptionPane.showMessageDialog(null, "Pretplata kuvaru " + this.recept.getAutor() + "izvrsena!");
			else
				JOptionPane.showMessageDialog(null, "Vec ste pretplaceni kuvaru " + this.recept.getAutor() + "!\nPretplata neuspesna!");
	}
}
