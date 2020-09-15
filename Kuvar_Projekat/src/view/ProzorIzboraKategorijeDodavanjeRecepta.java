package view;

import model.Aplikacija;
import model.Kategorija;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.HashMap;

public class ProzorIzboraKategorijeDodavanjeRecepta {

    private JFrame frame;
    private Aplikacija aplikacija;
    private ArrayList<Kategorija> kategorije;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode selectedNode;
    private JTree treeKategorija;
    private HashMap<Kategorija, Kategorija> linKategorije;
    private ArrayList<ElementListeDodatihKategorija> elementListeDodatihKategorija;
    private ProzorDodavanjaRecepta parent;
    private MyListModelKategorije modelKategorije;


    public ProzorIzboraKategorijeDodavanjeRecepta(ProzorDodavanjaRecepta parent, Aplikacija aplikacija, ArrayList<Kategorija> dodateKategorije) {
        this.aplikacija = aplikacija;
        this.parent = parent;
        this.linKategorije = new HashMap<Kategorija, Kategorija>();
        this.kategorije = this.aplikacija.getMenadzerKategorija().getKategorije();
        for (Kategorija k : this.kategorije) {
            this.linKategorije.put(k, null);
        }

        this.elementListeDodatihKategorija = new ArrayList<ElementListeDodatihKategorija>();
        modelKategorije = new MyListModelKategorije(elementListeDodatihKategorija);
        for (Kategorija k : dodateKategorije)
            modelKategorije.addElement(new ElementListeDodatihKategorija(k, linKategorije));

        initialize();
    }

    public void parseKategorije(DefaultMutableTreeNode currentNode, Kategorija currentKategorija) {
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(currentKategorija);
        currentNode.add(child);
        if (!currentKategorija.getPotkategorije().isEmpty()) for (Kategorija k : currentKategorija.getPotkategorije()) {
            this.linKategorije.put(k, currentKategorija);
            parseKategorije(child, k);
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 366, 792);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 338, 422);
        frame.getContentPane().add(scrollPane);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        treeModel = new DefaultTreeModel(root);
        treeKategorija = new JTree(treeModel);
        treeKategorija.setEditable(true);
        for (Kategorija k : kategorije) parseKategorije(root, k);
        scrollPane.setViewportView(treeKategorija);
        treeKategorija.addTreeSelectionListener(e -> {
            selectedNode = (DefaultMutableTreeNode) treeKategorija.getLastSelectedPathComponent();
        });


        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 475, 338, 219);
        frame.getContentPane().add(scrollPane_1);

        JList listDodateKategorije = new JList(modelKategorije);
        scrollPane_1.setViewportView(listDodateKategorije);

        JLabel lblNewLabel = new JLabel("Dodate kategorije");
        lblNewLabel.setBounds(10, 450, 207, 14);
        frame.getContentPane().add(lblNewLabel);

        JButton btnZavrsi = new JButton("Zavrsi");
        btnZavrsi.setBounds(261, 716, 89, 23);
        frame.getContentPane().add(btnZavrsi);
        btnZavrsi.addActionListener(e -> {
            ArrayList<Kategorija> kategorije = new ArrayList<>();
            for (ElementListeDodatihKategorija el : this.elementListeDodatihKategorija) {
                kategorije.add(el.kategorija);
            }
            parent.osveziKategorije(kategorije);
            frame.dispose();
        });

        JButton btnUkloni = new JButton("Ukloni");
        btnUkloni.setBounds(128, 716, 89, 23);
        frame.getContentPane().add(btnUkloni);
        btnUkloni.addActionListener(e -> {
            if (listDodateKategorije.getSelectedValue() != null)
                modelKategorije.removeElement((ElementListeDodatihKategorija) listDodateKategorije.getSelectedValue());
        });

        JButton btnDodaj = new JButton("Dodaj");
        btnDodaj.setBounds(10, 716, 89, 23);
        frame.getContentPane().add(btnDodaj);
        btnDodaj.addActionListener(e -> {
            if (selectedNode != null) {
                modelKategorije.addElement(new ElementListeDodatihKategorija((Kategorija) selectedNode.getUserObject(), linKategorije));
            }
        });

        expand();
        frame.setVisible(true);
    }

    public class ElementListeDodatihKategorija {
        private Kategorija kategorija;
        private HashMap<Kategorija, Kategorija> linKategorije;

        public ElementListeDodatihKategorija(Kategorija kategorija, HashMap<Kategorija, Kategorija> linKategorije) {
            this.kategorija = kategorija;
            this.linKategorije = linKategorije;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder(kategorija.toString());
            Kategorija parent = linKategorije.get(kategorija);
            while (parent != null) {
                s.insert(0, parent.toString() + "->");
                parent = linKategorije.get(parent);
            }
            return s.toString();
        }
    }

    private void expand() {
        for (int i = 0; i < treeKategorija.getRowCount(); i++) {
            treeKategorija.expandRow(i);
        }
    }

    class MyListModelKategorije extends AbstractListModel {
        private ArrayList<ElementListeDodatihKategorija> elementListeDodatihKategorija;

        public MyListModelKategorije(ArrayList<ElementListeDodatihKategorija> elementListeDodatihKategorija) {
            this.elementListeDodatihKategorija = elementListeDodatihKategorija;
        }

        public void addElement(ElementListeDodatihKategorija e) {
            for (ElementListeDodatihKategorija elem : this.elementListeDodatihKategorija)
                if (e.kategorija.getSifraKategorije().equals(elem.kategorija.getSifraKategorije())) {
                    JOptionPane.showMessageDialog(null, "Izabrana kategorija vec dodata");
                    return;
                }
            this.elementListeDodatihKategorija.add(e);
            this.fireContentsChanged(this, 0, getSize());
        }

        public void removeElement(ElementListeDodatihKategorija e) {
            elementListeDodatihKategorija.remove(e);
            this.fireContentsChanged(this, 0, getSize());
        }

        @Override
        public int getSize() {
            return this.elementListeDodatihKategorija.size();
        }

        @Override
        public Object getElementAt(int index) {
            return elementListeDodatihKategorija.get(index);
        }
    }
}
