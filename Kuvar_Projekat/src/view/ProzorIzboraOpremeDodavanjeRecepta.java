package view;

import model.Aplikacija;
import model.Oprema;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class ProzorIzboraOpremeDodavanjeRecepta {

    private JDialog dialog;
    private Vector<Oprema> svaOprema;
    private ArrayList<Oprema> dodataOprema;
    private MyListModelOprema modelOprema;

    /**
     * Create the application.
     */
    public ProzorIzboraOpremeDodavanjeRecepta(Aplikacija aplikacija, ArrayList<Oprema> dodataOprema) {
        this.svaOprema = new Vector<>(aplikacija.getMenadzerOpreme().getOprema());
        this.dodataOprema = dodataOprema;

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Oprema");
        dialog.setResizable(false);
        dialog.setBounds(100, 100, 412, 497);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 386, 211);
        dialog.getContentPane().add(scrollPane);

        JList listSveOpreme = new JList(svaOprema);
        scrollPane.setViewportView(listSveOpreme);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 258, 386, 128);
        dialog.getContentPane().add(scrollPane_1);

        modelOprema = new MyListModelOprema(dodataOprema);
        JList listDodateOpreme = new JList(modelOprema);
        scrollPane_1.setViewportView(listDodateOpreme);

        JLabel lblNewLabel = new JLabel("Dodata oprema");
        lblNewLabel.setBounds(10, 233, 130, 14);
        dialog.getContentPane().add(lblNewLabel);

        JButton btnDodaj = new JButton("Dodaj");
        btnDodaj.setBounds(10, 414, 89, 23);
        dialog.getContentPane().add(btnDodaj);
        btnDodaj.addActionListener(e -> {
            if (listSveOpreme.getSelectedValue() != null) {
                if (!modelOprema.addElement((Oprema) listSveOpreme.getSelectedValue())) {
                    JOptionPane.showMessageDialog(null, "Oprema je vec u listi dodate opreme");
                }
            }
        });

        JButton btnUkloni = new JButton("Ukloni");
        btnUkloni.setBounds(160, 414, 89, 23);
        dialog.getContentPane().add(btnUkloni);
        btnUkloni.addActionListener(e -> {
            if (listDodateOpreme.getSelectedValue() != null)
                modelOprema.removeElement((Oprema) listDodateOpreme.getSelectedValue());
        });

        JButton btnZavrsi = new JButton("Zavrsi");
        btnZavrsi.setBounds(307, 414, 89, 23);
        dialog.getContentPane().add(btnZavrsi);
        btnZavrsi.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    class MyListModelOprema extends AbstractListModel {
        private ArrayList<Oprema> oprema;

        public MyListModelOprema(ArrayList<Oprema> oprema) {
            this.oprema = oprema;
        }

        public ArrayList<Oprema> getOprema() {
            return oprema;
        }

        public boolean addElement(Oprema o) {
            if (oprema.contains(o)) return false;
            oprema.add(o);
            fireContentsChanged(this, 0, getSize());
            return true;
        }

        public boolean removeElement(Oprema o) {
            if (oprema.contains(o)) {
                oprema.remove(o);
                fireContentsChanged(this, 0, getSize());
                return true;
            }
            return false;
        }

        @Override
        public int getSize() {
            return oprema.size();
        }

        @Override
        public Object getElementAt(int index) {
            return oprema.get(index);
        }
    }

}
