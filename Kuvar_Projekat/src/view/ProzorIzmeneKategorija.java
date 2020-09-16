package view;

import controller.KontrolerProzorIzmeneKategorija;
import event.Observer;
import event.UpdateEvent;
import model.Aplikacija;
import model.Kategorija;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ProzorIzmeneKategorija implements Observer {

    private JFrame frame;
    private Aplikacija aplikacija;
    private KontrolerProzorIzmeneKategorija kontroler;
    private ArrayList<Kategorija> kategorije;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode selectedNode;
    private JTree treeKategorija;

    /**
     * Create the application.
     */
    public ProzorIzmeneKategorija(Aplikacija aplikacija, KontrolerProzorIzmeneKategorija kontroler) {
        kategorije = aplikacija.getMenadzerKategorija().getKategorije();
        this.kontroler = kontroler;
        this.aplikacija = aplikacija;
        initialize();
        this.aplikacija.getMenadzerKategorija().addObserver(this);
    }

    public void parseKategorije(DefaultMutableTreeNode currentNode, Kategorija currentKategorija) {
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(currentKategorija);
        currentNode.add(child);
        if (!currentKategorija.getPotkategorije().isEmpty())
            for (Kategorija k : currentKategorija.getPotkategorije()) parseKategorije(child, k);
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 364, 522);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Kategorije");
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 338, 422);
        frame.getContentPane().add(scrollPane);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        treeModel = new DefaultTreeModel(root);
        treeKategorija = new JTree(treeModel);
        treeKategorija.setEditable(true);
        treeKategorija.setComponentPopupMenu(getPopUpMenu());
        treeKategorija.addMouseListener(getMouseListener());
        for (Kategorija k : kategorije) parseKategorije(root, k);
        scrollPane.setViewportView(treeKategorija);

        JButton btnZavrsi = new JButton("Zavrsi");
        btnZavrsi.setBounds(259, 444, 89, 23);
        frame.getContentPane().add(btnZavrsi);
        btnZavrsi.addActionListener(e -> frame.dispose());

        expand();
        frame.setVisible(true);
    }

    private JPopupMenu getPopUpMenu() {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem mnDodaj = new JMenuItem("Dodaj potkategoriju");
        mnDodaj.addActionListener(e -> {
            Kategorija parent = null;
            String naziv = JOptionPane.showInputDialog("Unesite naziv nove kategorije");
            if (selectedNode != null) {
                if (!selectedNode.isRoot()) parent = (Kategorija) selectedNode.getUserObject();
            }
            try {
                if (naziv != null) kontroler.dodajKategoriju(parent, naziv);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Unesite neprazan naziv");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Potkategorija sa tim nazivom vec postoji");
            }
        });
        menu.add(mnDodaj);

        JMenuItem mnObrisi = new JMenuItem("Obrisi");
        mnObrisi.addActionListener(e -> {
            if (selectedNode != null) kontroler.obrisiKategoriju((Kategorija) selectedNode.getUserObject());
        });
        menu.add(mnObrisi);

        JMenuItem mnPreimenuj = new JMenuItem("Preimenuj");
        mnPreimenuj.addActionListener(e -> {
            if (selectedNode != null) {
                try {
                    String s = JOptionPane.showInputDialog("Unesite novi naziv");
                    if (s != null) kontroler.preimenujKategoriju((Kategorija) selectedNode.getUserObject(), s);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Potkategorija sa tim nazivom vec postoji");
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Unesite neprazan naziv");
                }
            }
        });
        menu.add(mnPreimenuj);

        return menu;
    }

    private MouseListener getMouseListener() {
        return new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent arg0) {
                if (arg0.getButton() == MouseEvent.BUTTON3) {
                    TreePath pathForLocation = treeKategorija.getPathForLocation(arg0.getPoint().x, arg0.getPoint().y);
                    if (pathForLocation != null) {
                        selectedNode = (DefaultMutableTreeNode) pathForLocation.getLastPathComponent();
                    } else {
                        selectedNode = null;
                    }

                }
                super.mousePressed(arg0);
            }
        };
    }


    private void expand() {
        for (int i = 0; i < treeKategorija.getRowCount(); i++) {
            treeKategorija.expandRow(i);
        }
    }

    @Override
    public void updatePerformed(UpdateEvent e) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        treeModel = new DefaultTreeModel(root);
        for (Kategorija k : kategorije) parseKategorije(root, k);
        treeKategorija.setModel(treeModel);
        expand();
    }
}
