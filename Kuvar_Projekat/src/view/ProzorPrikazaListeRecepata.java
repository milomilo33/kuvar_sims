package view;


import controller.KontrolerProzoraPrikazaRecepta;
import event.Observer;
import event.UpdateEvent;
import model.Aplikacija;
import model.Recept;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class ProzorPrikazaListeRecepata implements Observer {

    private JDialog dialog;
    private JTable tableRecepti;
    private ArrayList<Recept> recepti;
    private Aplikacija aplikacija;
    private MyTableModelRecepti modelRecepti;

    public ProzorPrikazaListeRecepata(Aplikacija aplikacija, Boolean licni) {
        this.aplikacija = aplikacija;
        this.aplikacija.getMenadzerRecepata().addObserver(this);
        if (licni) this.recepti = (ArrayList<Recept>) this.aplikacija.getTrenutniKorisnik().getRecepti();
        else {
            this.aplikacija.getMenadzerRecepata().sortiraj();
            this.recepti = (ArrayList<Recept>) this.aplikacija.getMenadzerRecepata().getRecepti();
        }
        initialize();
        dialog.setVisible(true);
    }

    @Override
    public void updatePerformed(UpdateEvent e) {
        modelRecepti.fireTableDataChanged();
        tableRecepti.repaint();
    }

    private void initialize() {
        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setBounds(100, 100, 546, 606);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 538, 535);
        dialog.getContentPane().add(scrollPane);

        modelRecepti = new MyTableModelRecepti(recepti);
        tableRecepti = new JTable(modelRecepti);
        tableRecepti.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                MyTableModelRecepti tableModel = (MyTableModelRecepti) table.getModel();
                int row = table.rowAtPoint(point);
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    new ProzorPrikazaRecepta(tableModel.getReceptAtRow(row), aplikacija, new KontrolerProzoraPrikazaRecepta(aplikacija));
                }
            }
        });
        scrollPane.setViewportView(tableRecepti);

        JButton btnPovratak = new JButton("Povratak");
        btnPovratak.setBounds(441, 543, 89, 23);
        dialog.getContentPane().add(btnPovratak);
        btnPovratak.addActionListener(e -> dialog.dispose());
    }

    class MyTableModelRecepti extends AbstractTableModel {
        private String[] columnNames = {"Naziv", "Autor", "Srednja ocena", "Broj ocena"};
        private ArrayList<Recept> recepti;

        MyTableModelRecepti(ArrayList<Recept> recepti) {
            this.recepti = recepti;
        }

        public Recept getReceptAtRow(int rowIndex) {
            return recepti.get(rowIndex);
        }

        @Override
        public int getRowCount() {
            return this.recepti.size();
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }


        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < 0 || rowIndex >= this.recepti.size()) return null;
            switch (columnIndex) {
                case 0:
                    return this.recepti.get(rowIndex).getNaziv();
                case 1:
                    return this.recepti.get(rowIndex).getAutor();
                case 2:
                    return String.format("%.2f", this.recepti.get(rowIndex).getSrednjaOcena());
                case 3:
                    return this.recepti.get(rowIndex).getKomentari().size();
                default:
                    return null;
            }
        }
    }

}