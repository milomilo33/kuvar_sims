package view;

import model.Aplikacija;
import model.MernaJedinica;
import model.Namirnica;
import model.Oprema;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class ProzorIzboraNamirnicaDodavanjeRecepta {

    private Aplikacija aplikacija;

    private JDialog frame;
    private JTextField txKolicina;
    private String[] merneJedinice = {"mg", "g", "kg"};
    private ArrayList<DodatiSastojci> dodatiSastojci;
    private Vector<Oprema> oprema;
    private Vector<Namirnica> sveNamirnice;
    private DefaultListModel dodateModel;

    /**
     * Create the application.
     */
    public ProzorIzboraNamirnicaDodavanjeRecepta(Aplikacija aplikacija, ArrayList<Namirnica> dodateNamirnice,
                                                 ArrayList<MernaJedinica> dodateMerneJedinice, ArrayList<Float> dodateKolicine, ArrayList<Oprema> oprema) {
        dodatiSastojci = new ArrayList<>();
        for (int i = 0; i < dodateNamirnice.size(); ++i)
            dodatiSastojci.add(new DodatiSastojci(dodateNamirnice.get(i), dodateMerneJedinice.get(i), dodateKolicine.get(i)));
        this.oprema = new Vector<>(oprema);
        this.aplikacija = aplikacija;
        this.sveNamirnice = new Vector<>(this.aplikacija.getMenadzerNamirnica().getNamirnice());
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JDialog();
        frame.setResizable(false);
        frame.setBounds(100, 100, 771, 483);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 745, 186);
        frame.getContentPane().add(scrollPane);

        JList listSveNamirnice = new JList(this.sveNamirnice);
        scrollPane.setViewportView(listSveNamirnice);

        JLabel lblNewLabel = new JLabel("Merna Jedinica");
        lblNewLabel.setBounds(10, 408, 97, 14);
        frame.getContentPane().add(lblNewLabel);

        JComboBox cmbMernaJedinica = new JComboBox(merneJedinice);
        cmbMernaJedinica.setBounds(117, 404, 77, 22);
        frame.getContentPane().add(cmbMernaJedinica);

        JLabel lblNewLabel_1 = new JLabel("Kolicina");
        lblNewLabel_1.setBounds(209, 408, 62, 14);
        frame.getContentPane().add(lblNewLabel_1);

        txKolicina = new JTextField();
        txKolicina.setBounds(292, 405, 54, 20);
        frame.getContentPane().add(txKolicina);
        txKolicina.setColumns(10);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 226, 745, 171);
        frame.getContentPane().add(scrollPane_1);

        dodateModel = new DefaultListModel();
        for (DodatiSastojci ds : dodatiSastojci) dodateModel.addElement(ds);

        JList listDodateNamirnice = new JList();
        listDodateNamirnice.setModel(dodateModel);
        scrollPane_1.setViewportView(listDodateNamirnice);

        JButton btnDodaj = new JButton("Dodaj");
        btnDodaj.setBounds(384, 404, 89, 23);
        frame.getContentPane().add(btnDodaj);
        btnDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txKolicina.getText().matches("[0-9]*.?[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Neispravan unos za kolicinu");
                    return;
                }
                if (listSveNamirnice.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Namirnica nije izabrana");
                }
                MernaJedinica mj = MernaJedinica.G;
                if (cmbMernaJedinica.getSelectedItem().equals("mg")) mj = MernaJedinica.MG;
                if (cmbMernaJedinica.getSelectedItem().equals("kg")) mj = MernaJedinica.KG;
                dodatiSastojci.add(new DodatiSastojci((Namirnica) listSveNamirnice.getSelectedValue(), mj,
                        Float.valueOf(txKolicina.getText())));
                dodateModel.addElement(dodatiSastojci.get(dodatiSastojci.size() - 1));
            }
        });

        JButton btnKraj = new JButton("Zavrsi Dodavanje");
        btnKraj.setBounds(599, 404, 156, 23);
        frame.getContentPane().add(btnKraj);

        JButton btnUkloni = new JButton("Ukloni");
        btnUkloni.setBounds(493, 404, 89, 23);
        frame.getContentPane().add(btnUkloni);

        JLabel lblNewLabel_2 = new JLabel("Dodate namirnice");
        lblNewLabel_2.setBounds(10, 208, 233, 14);
        frame.getContentPane().add(lblNewLabel_2);

        frame.setModal(true);
        frame.setVisible(true);
    }

    class DodatiSastojci {
        private Namirnica dodataNamirnica;
        private MernaJedinica dodataMernaJedinica;
        private Float dodataKolicina;

        public DodatiSastojci(Namirnica dodataNamirnica, MernaJedinica dodataMernaJedinica, Float dodataKolicina) {
            this.dodataNamirnica = dodataNamirnica;
            this.dodataMernaJedinica = dodataMernaJedinica;
            this.dodataKolicina = dodataKolicina;
        }

        @Override
        public String toString() {
            return dodataNamirnica.getNaziv() + " " +
                    dodataMernaJedinica.toString() + " " +
                    dodataKolicina;
        }
    }

}
