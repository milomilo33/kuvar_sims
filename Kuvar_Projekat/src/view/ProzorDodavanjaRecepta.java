package view;

import controller.KontrolerProzoraDodavanjaRecepta;
import model.*;

import javax.swing.*;
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
    private ArrayList<Oprema> oprema;
    private Aplikacija aplikacija;
    private KontrolerProzoraDodavanjaRecepta kontroler;
    private MyListModelNamirnice modelNamirnice;
    private MyListModelOprema modelOprema;
    private ArrayList<Kategorija> kategorije;
    private JTextPane txKategorije;


    public ProzorDodavanjaRecepta(Aplikacija aplikacija, KontrolerProzoraDodavanjaRecepta kontroler) {
        namirnice = new ArrayList<>();
        oprema = new ArrayList<>();
        merneJedinice = new ArrayList<>();
        kolicine = new ArrayList<>();
        kategorije = new ArrayList<>();
        this.aplikacija = aplikacija;
        this.kontroler = kontroler;
        initialize();
    }

    public void osveziNamirnice() {
        modelNamirnice.osvezi();
    }

    public void osveziOpremu() {
        modelOprema.osvezi();
    }

    public void osveziKategorije(ArrayList<Kategorija> kategorije) {
        this.kategorije = new ArrayList<>(kategorije);
        StringBuilder s = new StringBuilder();
        for (Kategorija k : this.kategorije) s.append(" ").append(k.toString()).append(";");
        txKategorije.setText(String.valueOf(s));
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

        modelNamirnice = new MyListModelNamirnice(this.namirnice, this.merneJedinice, this.kolicine);
        JList listNaminica = new JList();
        listNaminica.setModel(modelNamirnice);
        scrollPane.setViewportView(listNaminica);

        JButton btnNamirnica = new JButton("Izmeni Namirnice");
        btnNamirnica.setBounds(28, 167, 134, 23);
        frmDodavanjeRecepta.getContentPane().add(btnNamirnica);
        btnNamirnica.addActionListener(e -> {
            ProzorIzboraNamirnicaDodavanjeRecepta prozor = new ProzorIzboraNamirnicaDodavanjeRecepta(aplikacija,
                    namirnice, merneJedinice, kolicine);
            osveziNamirnice();
        });

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(492, 105, 313, 150);
        frmDodavanjeRecepta.getContentPane().add(scrollPane_1);

        modelOprema = new MyListModelOprema(oprema);
        JList listOprema = new JList(modelOprema);
        scrollPane_1.setViewportView(listOprema);

        JButton btnOprema = new JButton("Izmeni Opremu");
        btnOprema.setBounds(815, 167, 120, 23);
        frmDodavanjeRecepta.getContentPane().add(btnOprema);
        btnOprema.addActionListener(e -> {
            ProzorIzboraOpremeDodavanjeRecepta prozor = new ProzorIzboraOpremeDodavanjeRecepta(aplikacija, oprema);
            osveziOpremu();
        });

        JButton btnOK = new JButton("Napravi Recept");
        btnOK.setBounds(815, 642, 120, 23);
        frmDodavanjeRecepta.getContentPane().add(btnOK);
        btnOK.addActionListener(e -> {
            try {
                kontroler.dodajRecept(txtNaziv.getText(), txtVreme.getText(), cmbTezina.getSelectedItem().toString(), namirnice, merneJedinice, kolicine, oprema, kategorije, txOpis.getText());
                JOptionPane.showMessageDialog(null, "Recept uspesno dodat");
            } catch (IllegalArgumentException | NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Neispravni uneti elementi");
            }

        });

        JButton btnOdustanak = new JButton("Odustanak");
        btnOdustanak.setBounds(25, 642, 120, 23);
        frmDodavanjeRecepta.getContentPane().add(btnOdustanak);
        btnOdustanak.addActionListener(e -> frmDodavanjeRecepta.dispose());

        JButton btnKategorije = new JButton("Kategorije");
        btnKategorije.setBounds(169, 642, 105, 23);
        frmDodavanjeRecepta.getContentPane().add(btnKategorije);
        btnKategorije.addActionListener(e -> {
            ProzorIzboraKategorijeDodavanjeRecepta prozor = new ProzorIzboraKategorijeDodavanjeRecepta(this, aplikacija, this.kategorije);
        });

        txKategorije = new JTextPane();
        txKategorije.setEditable(false);
        txKategorije.setBounds(278, 645, 527, 20);
        frmDodavanjeRecepta.getContentPane().add(txKategorije);
        btnOdustanak.addActionListener(e -> frmDodavanjeRecepta.dispose());

        frmDodavanjeRecepta.setVisible(true);
    }

    class MyListModelNamirnice extends AbstractListModel {
        private ArrayList<Namirnica> namirnice;
        private ArrayList<MernaJedinica> merneJedinice;
        private ArrayList<Float> kolicine;

        public MyListModelNamirnice(ArrayList<Namirnica> namirnice, ArrayList<MernaJedinica> merneJedinice, ArrayList<Float> kolicine) {
            this.namirnice = namirnice;
            this.merneJedinice = merneJedinice;
            this.kolicine = kolicine;
        }

        public void osvezi() {
            this.fireContentsChanged(this, 0, getSize());
        }


        @Override
        public int getSize() {
            return namirnice.size();
        }

        @Override
        public Object getElementAt(int index) {
            return namirnice.get(index).getNaziv() + " " + merneJedinice.get(index) + " " + kolicine.get(index);
        }
    }

    class MyListModelOprema extends AbstractListModel {
        private ArrayList<Oprema> opremas;

        public MyListModelOprema(ArrayList<Oprema> opremas) {
            this.opremas = opremas;
        }

        public void osvezi() {
            this.fireContentsChanged(this, 0, getSize());
        }

        @Override
        public int getSize() {
            return opremas.size();
        }

        @Override
        public Object getElementAt(int index) {
            return opremas.get(index);
        }
    }
}
