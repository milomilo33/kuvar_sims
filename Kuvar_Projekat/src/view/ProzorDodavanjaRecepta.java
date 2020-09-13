package view;

import controller.KontrolerProzoraDodavanjaRecepta;
import model.Aplikacija;
import model.MernaJedinica;
import model.Namirnica;
import model.Oprema;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProzorDodavanjaRecepta {

    private JFrame frmDodavanjeRecepta;
    private JTextField txtNaziv;
    private JTextField txtVreme;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JScrollPane scrollPane;
    private String[] Tezine = {"Lako", "Srednje", "Tesko"};
    private ArrayList<Namirnica> namirnice;
    private ArrayList<MernaJedinica> merneJedinice;
    private ArrayList<Float> kolicine;
    private ArrayList<Float> vremenaPripreme;
    private ArrayList<Oprema> oprema;
    private Aplikacija aplikacija;
    private KontrolerProzoraDodavanjaRecepta kontroler;


    /**
     * Create the application.
     */
    public ProzorDodavanjaRecepta(Aplikacija aplikacija, KontrolerProzoraDodavanjaRecepta kontroler) {
        namirnice = new ArrayList<>();
        oprema = new ArrayList<>();
        merneJedinice = new ArrayList<>();
        oprema = new ArrayList<>();
        kolicine = new ArrayList<>();
        this.aplikacija = aplikacija;
        this.kontroler = kontroler;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmDodavanjeRecepta = new JFrame();
        frmDodavanjeRecepta.setTitle("Dodavanje Recepta");
        frmDodavanjeRecepta.setResizable(false);
        frmDodavanjeRecepta.setBounds(100, 100, 970, 739);
        frmDodavanjeRecepta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmDodavanjeRecepta.getContentPane().setLayout(null);

        txtNaziv = new JTextField();
        txtNaziv.setBounds(99, 62, 186, 20);
        frmDodavanjeRecepta.getContentPane().add(txtNaziv);
        txtNaziv.setColumns(10);

        txtVreme = new JTextField();
        txtVreme.setBounds(474, 62, 186, 20);
        frmDodavanjeRecepta.getContentPane().add(txtVreme);
        txtVreme.setColumns(10);

        JComboBox cmbTezina = new JComboBox(Tezine);
        cmbTezina.setBounds(768, 61, 186, 22);
        frmDodavanjeRecepta.getContentPane().add(cmbTezina);


        JLabel lblNewLabel = new JLabel("Naziv recepta");
        lblNewLabel.setBounds(20, 65, 86, 14);
        frmDodavanjeRecepta.getContentPane().add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Vreme pripreme u min.");
        lblNewLabel_1.setBounds(314, 65, 139, 14);
        frmDodavanjeRecepta.getContentPane().add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Tezina");
        lblNewLabel_2.setBounds(693, 65, 46, 14);
        frmDodavanjeRecepta.getContentPane().add(lblNewLabel_2);

        JTextPane txOpis = new JTextPane();
        txOpis.setBounds(169, 320, 636, 305);
        frmDodavanjeRecepta.getContentPane().add(txOpis);

        JLabel lblNewLabel_3 = new JLabel("Opis");
        lblNewLabel_3.setBounds(169, 305, 46, 14);
        frmDodavanjeRecepta.getContentPane().add(lblNewLabel_3);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(169, 105, 313, 150);
        frmDodavanjeRecepta.getContentPane().add(scrollPane);

        JList listNaminica = new JList();
        scrollPane.setViewportView(listNaminica);

        JButton btnNamirnica = new JButton("Dodaj Namirnicu");
        btnNamirnica.setBounds(28, 167, 134, 23);
        frmDodavanjeRecepta.getContentPane().add(btnNamirnica);

        btnNamirnica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProzorIzboraNamirnicaDodavanjeRecepta prozor = new ProzorIzboraNamirnicaDodavanjeRecepta(aplikacija,
                        namirnice, merneJedinice, kolicine, oprema);

            }
        });

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(492, 105, 313, 150);
        frmDodavanjeRecepta.getContentPane().add(scrollPane_1);

        JList listOprema = new JList();
        scrollPane_1.setViewportView(listOprema);

        JButton btnDodajOpremu = new JButton("Dodaj Opremu");
        btnDodajOpremu.setBounds(815, 167, 120, 23);
        frmDodavanjeRecepta.getContentPane().add(btnDodajOpremu);

        JButton btnOK = new JButton("Napravi Recept");
        btnOK.setBounds(815, 642, 120, 23);
        frmDodavanjeRecepta.getContentPane().add(btnOK);

        JButton btnOdustanak = new JButton("Odustanak");
        btnOdustanak.setBounds(25, 642, 120, 23);
        frmDodavanjeRecepta.getContentPane().add(btnOdustanak);

        frmDodavanjeRecepta.setVisible(true);
    }
}
