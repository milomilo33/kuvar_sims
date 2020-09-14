package view;

import model.Aplikacija;
import model.MernaJedinica;
import model.Namirnica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class ProzorIzboraNamirnicaDodavanjeRecepta {

    private Aplikacija aplikacija;

    private JDialog frame;
    private JTextField txKolicina;
    private String[] merneJedinice = {"mg", "g", "kg"};
    private ArrayList<DodatiSastojci> dodatiSastojci;
    private Vector<Namirnica> sveNamirnice;
    private MyListModel dodateModel;
    private ArrayList<Namirnica> dodateNamirnice;
    private ArrayList<MernaJedinica> dodateMerneJedinice;
    private ArrayList<Float> dodateKolicine;
    private ProzorDodavanjaRecepta parent;

    /**
     * Create the application.
     */
    public ProzorIzboraNamirnicaDodavanjeRecepta(ProzorDodavanjaRecepta parent, Aplikacija aplikacija, ArrayList<Namirnica> dodateNamirnice,
                                                 ArrayList<MernaJedinica> dodateMerneJedinice, ArrayList<Float> dodateKolicine) {
        dodatiSastojci = new ArrayList<>();
        for (int i = 0; i < dodateNamirnice.size(); ++i)
            dodatiSastojci.add(new DodatiSastojci(dodateNamirnice.get(i), dodateMerneJedinice.get(i), dodateKolicine.get(i)));
        this.aplikacija = aplikacija;
        this.sveNamirnice = new Vector<>(this.aplikacija.getMenadzerNamirnica().getNamirnice());
        this.dodateNamirnice = dodateNamirnice;
        this.dodateMerneJedinice = dodateMerneJedinice;
        this.dodateKolicine = dodateKolicine;
        this.parent = parent;
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

        dodateModel = new MyListModel(dodatiSastojci);

        JList listDodateNamirnice = new JList();
        listDodateNamirnice.setModel(dodateModel);
        scrollPane_1.setViewportView(listDodateNamirnice);

        JButton btnDodaj = new JButton("Dodaj");
        btnDodaj.setBounds(384, 404, 89, 23);
        frame.getContentPane().add(btnDodaj);
        btnDodaj.addActionListener(e -> {
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
            if (!dodateModel.addElement(new DodatiSastojci((Namirnica) listSveNamirnice.getSelectedValue(), mj,
                    Float.valueOf(txKolicina.getText()))))
                JOptionPane.showMessageDialog(null, "Namirnica je vec u listi dodatih namirnica");

        });

        JButton btnKraj = new JButton("Zavrsi Dodavanje");
        btnKraj.setBounds(599, 404, 156, 23);
        frame.getContentPane().add(btnKraj);
        btnKraj.addActionListener(e -> {
            this.dodateKolicine.clear();
            this.dodateMerneJedinice.clear();
            this.dodateNamirnice.clear();
            for (DodatiSastojci ds : dodatiSastojci) {
                this.dodateNamirnice.add(ds.dodataNamirnica);
                this.dodateMerneJedinice.add(ds.dodataMernaJedinica);
                this.dodateKolicine.add(ds.dodataKolicina);
            }
            parent.osveziNamirnice();
            frame.dispose();

        });

        JButton btnUkloni = new JButton("Ukloni");
        btnUkloni.setBounds(493, 404, 89, 23);
        frame.getContentPane().add(btnUkloni);
        btnUkloni.addActionListener(e -> {
            if (listDodateNamirnice.getSelectedValue() != null)
                dodateModel.removeElement((DodatiSastojci) listDodateNamirnice.getSelectedValue());
        });

        JLabel lblNewLabel_2 = new JLabel("Dodate namirnice");
        lblNewLabel_2.setBounds(10, 208, 233, 14);
        frame.getContentPane().add(lblNewLabel_2);

        frame.setModal(true);
        frame.setVisible(true);
    }

    class MyListModel extends AbstractListModel {
        private ArrayList<DodatiSastojci> dodatiSastojci;


        public MyListModel(ArrayList<DodatiSastojci> dodatiSastojci) {
            this.dodatiSastojci = dodatiSastojci;
        }

        public boolean removeElement(DodatiSastojci ds) {
            if (!this.dodatiSastojci.remove(ds)) return false;
            this.fireContentsChanged(this, 0, getSize());
            return true;
        }

        public boolean addElement(DodatiSastojci ds) {
            for (DodatiSastojci item : dodatiSastojci)
                if (item.dodataNamirnica.getSifraNamirnice().equals(ds.dodataNamirnica.getSifraNamirnice()))
                    return false;
            this.dodatiSastojci.add(ds);
            this.fireContentsChanged(this, 0, getSize());
            return true;
        }

        @Override
        public int getSize() {
            return dodatiSastojci.size();
        }

        @Override
        public Object getElementAt(int index) {
            return dodatiSastojci.get(index);
        }
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
