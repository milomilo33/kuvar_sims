package view;

import controller.KontrolerProzorProfilaKorisnika;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProzorProfilaKorisnika {

    private JDialog frmProfilnaStrana;
    private JTextField txtIme;
    private JTextField txtPrezime;
    private JTextField txtDatumRodjenja;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtBrojTelefona;
    private JTextField txtAdresa;
    private JTextField txtUloga;
    private Aplikacija aplikacija;
    private KontrolerProzorProfilaKorisnika kontroler;
    private HashMap<Namirnica, Posedovanje> namirnice;
    private ArrayList<Oprema> oprema;
    private MyNamirniceProfilModel namirniceModel;
    private MyListModelOprema modelOprema;
    private ArrayList<Namirnica> listNamirnice;
    private ArrayList<MernaJedinica> listMerneJedinice;
    private ArrayList<Float> listKolicine;


    public ProzorProfilaKorisnika(Aplikacija aplikacija, KontrolerProzorProfilaKorisnika kontroler) {
        this.aplikacija = aplikacija;
        this.kontroler = kontroler;
        this.namirnice = new HashMap<>(aplikacija.getTrenutniKorisnik().getNamirnice());
        this.oprema = new ArrayList<>(aplikacija.getTrenutniKorisnik().getOprema());
        this.listNamirnice = new ArrayList<>();
        this.listKolicine = new ArrayList<>();
        this.listMerneJedinice = new ArrayList<>();
        initialize();
        popuniText();
        frmProfilnaStrana.setVisible(true);
    }


    public void popuniText() {
        if (this.aplikacija.getTrenutniKorisnik().getUloga() != null)
            txtUloga.setText(this.aplikacija.getTrenutniKorisnik().getUloga().toString());
        if (this.aplikacija.getTrenutniKorisnik().getAdresa() != null)
            txtAdresa.setText(this.aplikacija.getTrenutniKorisnik().getAdresa());
        if (this.aplikacija.getTrenutniKorisnik().getBrojTelefona() != null)
            txtBrojTelefona.setText(this.aplikacija.getTrenutniKorisnik().getBrojTelefona());
        if (this.aplikacija.getTrenutniKorisnik().getPassword() != null)
            txtPassword.setText(this.aplikacija.getTrenutniKorisnik().getPassword());
        if (this.aplikacija.getTrenutniKorisnik().getUsername() != null)
            txtUsername.setText(this.aplikacija.getTrenutniKorisnik().getUsername());
        if (this.aplikacija.getTrenutniKorisnik().getDatumRodjenja() != null)
            txtDatumRodjenja.setText(this.aplikacija.getTrenutniKorisnik().getDatumRodjenja().toString());
        if (this.aplikacija.getTrenutniKorisnik().getPrezime() != null)
            txtPrezime.setText(this.aplikacija.getTrenutniKorisnik().getPrezime());
        if (this.aplikacija.getTrenutniKorisnik().getIme() != null)
            txtIme.setText(this.aplikacija.getTrenutniKorisnik().getIme());
    }


    private void initialize() {
        frmProfilnaStrana = new JDialog();
        frmProfilnaStrana.setModal(true);
        frmProfilnaStrana.setResizable(false);
        frmProfilnaStrana.setTitle("Profilna strana");
        frmProfilnaStrana.setBounds(100, 100, 1007, 757);
        frmProfilnaStrana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmProfilnaStrana.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(600, 60, 391, 245);
        frmProfilnaStrana.getContentPane().add(scrollPane);


        namirniceModel = new MyNamirniceProfilModel(namirnice);
        JList listNamirnice = new JList(namirniceModel);
        scrollPane.setViewportView(listNamirnice);

        JLabel lblNewLabel = new JLabel("Namirnice koje posedujete");
        lblNewLabel.setBounds(600, 35, 217, 14);
        frmProfilnaStrana.getContentPane().add(lblNewLabel);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(600, 364, 391, 245);
        frmProfilnaStrana.getContentPane().add(scrollPane_1);

        modelOprema = new MyListModelOprema(oprema);
        JList listOpreme = new JList(modelOprema);
        scrollPane_1.setViewportView(listOpreme);

        JLabel lblNewLabel_1 = new JLabel("Oprema koju posedujete");
        lblNewLabel_1.setBounds(600, 339, 178, 14);
        frmProfilnaStrana.getContentPane().add(lblNewLabel_1);

        JButton btnOprema = new JButton("Izmeni opremu");
        btnOprema.setBounds(728, 620, 137, 23);
        frmProfilnaStrana.getContentPane().add(btnOprema);
        btnOprema.addActionListener(e -> {
            new ProzorIzboraOpremeDodavanjeRecepta(aplikacija, this.oprema);
            modelOprema.osvezi();
        });

        JButton btnNamirnice = new JButton("Izmeni namirnice");
        btnNamirnice.setBounds(728, 316, 137, 23);
        frmProfilnaStrana.getContentPane().add(btnNamirnice);
        btnNamirnice.addActionListener(e -> {
            for (Namirnica n : namirnice.keySet()) {
                this.listNamirnice.add(n);
                this.listMerneJedinice.add(namirnice.get(n).getMernaJedinica());
                this.listKolicine.add(namirnice.get(n).getKolicina());
            }
            new ProzorIzboraNamirnicaDodavanjeRecepta(aplikacija, this.listNamirnice, this.listMerneJedinice, this.listKolicine);
            this.namirnice.clear();
            for (int i = 0; i < this.listNamirnice.size(); i++) {
                this.namirnice.put(this.listNamirnice.get(i), new Posedovanje(this.listKolicine.get(i), this.listMerneJedinice.get(i)));
            }
            namirniceModel.osvezi();
            this.listNamirnice.clear();
            this.listKolicine.clear();
            this.listMerneJedinice.clear();
        });

        JButton btnZavrsi = new JButton("Zavrsi i sacuvaj promene");
        btnZavrsi.setBounds(761, 673, 230, 23);
        frmProfilnaStrana.getContentPane().add(btnZavrsi);
        btnZavrsi.addActionListener(e -> {
            try {
                kontroler.izmeniProfil(this.aplikacija.getTrenutniKorisnik(), txtIme.getText(), txtPrezime.getText(), txtPassword.getText(), txtBrojTelefona.getText(), txtAdresa.getText(), oprema, namirnice);
                frmProfilnaStrana.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Popunite sva polja");
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Korisnik nije u sistemu");
            }
        });

        JButton btnOdustanak = new JButton("Odustanak");
        btnOdustanak.setBounds(600, 673, 109, 23);
        frmProfilnaStrana.getContentPane().add(btnOdustanak);
        btnOdustanak.addActionListener(e -> {
            frmProfilnaStrana.dispose();
        });

        JPanel panel = new JPanel();
        panel.setBounds(10, 60, 391, 549);
        frmProfilnaStrana.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel_2 = new JLabel("ime");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 0;
        panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

        txtIme = new JTextField();
        GridBagConstraints gbc_txtIme = new GridBagConstraints();
        gbc_txtIme.insets = new Insets(0, 0, 5, 0);
        gbc_txtIme.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtIme.gridx = 1;
        gbc_txtIme.gridy = 0;
        panel.add(txtIme, gbc_txtIme);
        txtIme.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("prezime");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 2;
        panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

        txtPrezime = new JTextField();
        GridBagConstraints gbc_txtPrezime = new GridBagConstraints();
        gbc_txtPrezime.insets = new Insets(0, 0, 5, 0);
        gbc_txtPrezime.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPrezime.gridx = 1;
        gbc_txtPrezime.gridy = 2;
        panel.add(txtPrezime, gbc_txtPrezime);
        txtPrezime.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("datum rodjenja");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 4;
        panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

        txtDatumRodjenja = new JTextField();
        txtDatumRodjenja.setEditable(false);
        GridBagConstraints gbc_txtDatumRodjenja = new GridBagConstraints();
        gbc_txtDatumRodjenja.insets = new Insets(0, 0, 5, 0);
        gbc_txtDatumRodjenja.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDatumRodjenja.gridx = 1;
        gbc_txtDatumRodjenja.gridy = 4;
        panel.add(txtDatumRodjenja, gbc_txtDatumRodjenja);
        txtDatumRodjenja.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("username");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 0;
        gbc_lblNewLabel_5.gridy = 6;
        panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

        txtUsername = new JTextField();
        txtUsername.setEditable(false);
        GridBagConstraints gbc_txtUsername = new GridBagConstraints();
        gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
        gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtUsername.gridx = 1;
        gbc_txtUsername.gridy = 6;
        panel.add(txtUsername, gbc_txtUsername);
        txtUsername.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("lozinka");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_6.gridx = 0;
        gbc_lblNewLabel_6.gridy = 8;
        panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

        txtPassword = new JTextField();
        GridBagConstraints gbc_txtPassword = new GridBagConstraints();
        gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
        gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPassword.gridx = 1;
        gbc_txtPassword.gridy = 8;
        panel.add(txtPassword, gbc_txtPassword);
        txtPassword.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("broj telefona");
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
        gbc_lblNewLabel_7.anchor = GridBagConstraints.SOUTHEAST;
        gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_7.gridx = 0;
        gbc_lblNewLabel_7.gridy = 10;
        panel.add(lblNewLabel_7, gbc_lblNewLabel_7);

        txtBrojTelefona = new JTextField();
        GridBagConstraints gbc_txtBrojTelefona = new GridBagConstraints();
        gbc_txtBrojTelefona.insets = new Insets(0, 0, 5, 0);
        gbc_txtBrojTelefona.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtBrojTelefona.gridx = 1;
        gbc_txtBrojTelefona.gridy = 10;
        panel.add(txtBrojTelefona, gbc_txtBrojTelefona);
        txtBrojTelefona.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("adresa");
        GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
        gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_8.gridx = 0;
        gbc_lblNewLabel_8.gridy = 12;
        panel.add(lblNewLabel_8, gbc_lblNewLabel_8);

        txtAdresa = new JTextField();
        GridBagConstraints gbc_txtAdresa = new GridBagConstraints();
        gbc_txtAdresa.insets = new Insets(0, 0, 5, 0);
        gbc_txtAdresa.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtAdresa.gridx = 1;
        gbc_txtAdresa.gridy = 12;
        panel.add(txtAdresa, gbc_txtAdresa);
        txtAdresa.setColumns(10);

        JLabel lblNewLabel_9 = new JLabel("uloga");
        GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
        gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_9.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_9.gridx = 0;
        gbc_lblNewLabel_9.gridy = 14;
        panel.add(lblNewLabel_9, gbc_lblNewLabel_9);

        txtUloga = new JTextField();
        txtUloga.setEditable(false);
        GridBagConstraints gbc_txtUloga = new GridBagConstraints();
        gbc_txtUloga.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtUloga.gridx = 1;
        gbc_txtUloga.gridy = 14;
        panel.add(txtUloga, gbc_txtUloga);
        txtUloga.setColumns(10);
    }

    class MyNamirniceProfilModel extends AbstractListModel {
        private HashMap<Namirnica, Posedovanje> namirnices;
        private ArrayList<Namirnica> listNamirnica;

        public MyNamirniceProfilModel(HashMap<Namirnica, Posedovanje> namirnice) {
            this.listNamirnica = new ArrayList<>();
            this.namirnices = namirnice;
            this.listNamirnica.addAll(namirnice.keySet());
        }

        public void osvezi() {
            this.listNamirnica.clear();
            this.listNamirnica.addAll(this.namirnices.keySet());
            this.fireContentsChanged(this, 0, getSize());
        }

        @Override
        public int getSize() {
            return namirnice.size();
        }

        @Override
        public Object getElementAt(int index) {
            return listNamirnica.get(index).toString() + " " + namirnices.get(listNamirnica.get(index)).toString();
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
