package view;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import controller.KontrolerBookmarkovanja;
import controller.KontrolerProzoraPrikazaRecepta;
import event.Observer;
import event.UpdateEvent;
import model.Aplikacija;
import model.Bookmark;
import model.ElementBookmarkovanja;
import model.Folder;
import model.Recept;
import utility.DataGenerator;
import utility.IDGenerator;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ProzorBookmarkovanja extends JDialog implements Observer, TreeSelectionListener {

	private JPanel contentPane;
	
	private Aplikacija aplikacija;
	private KontrolerBookmarkovanja kontroler;
	private Recept izabraniRecept;
	private Boolean dodavanjeMode;
	
	private JTree tree;
	private JButton btnDodajDirektorijum;
	private JButton btnDodajUIzabrani;
	private JButton btnObrisi;
	private JButton btnPreimenuj;
	private JButton btnOtvoriRecept;
	
	private JButton lastBtnPressed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDGenerator.INSTANCE.deserialize();
					Aplikacija aplikacija = new Aplikacija();
					DataGenerator.generisiPodatke(aplikacija);
					aplikacija.sacuvajStanje();
					IDGenerator.INSTANCE.serialize();
					aplikacija.setTrenutniKorisnik(aplikacija.getMenadzerKorisnika().getKorisnici().get(0));
					KontrolerBookmarkovanja kontroler = new KontrolerBookmarkovanja(aplikacija);
					Recept recept = aplikacija.getMenadzerRecepata().getRecepti().get(0);
					ProzorBookmarkovanja frame = new ProzorBookmarkovanja(aplikacija, kontroler, recept, true);
					//System.out.println(aplikacija.getMenadzerKorisnika().getKorisnici().get(0));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProzorBookmarkovanja(Aplikacija aplikacija, KontrolerBookmarkovanja kontroler, Recept izabraniRecept, Boolean dodavanjeMode) {
		this.aplikacija = aplikacija;
		this.kontroler = kontroler;
		this.izabraniRecept = izabraniRecept;
		this.dodavanjeMode = dodavanjeMode;
		this.aplikacija.getMenadzerKorisnika().addObserver(this);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //?????
		setBounds(100, 100, 670, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 247, 345);
		contentPane.add(scrollPane);
		
		Folder rootDir = this.aplikacija.getMenadzerKorisnika().getRootDir();
		//System.out.println(this.aplikacija.getTrenutniKorisnik().getElementiBookmarkovanja());
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootDir);
		kreirajCvorove(rootNode);
		
		tree = new JTree(rootNode);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		scrollPane.setViewportView(tree);
		
		btnDodajDirektorijum = new JButton("Dodaj u novi direktorijum");
		btnDodajDirektorijum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastBtnPressed = (JButton) e.getSource();
				String nazivNoviDir = JOptionPane.showInputDialog("Unesite naziv novog direktorijuma:");
				try {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					Folder trenutniDir = (Folder) selectedNode.getUserObject();
					kontroler.kreirajDirektorijumDodajRecept(trenutniDir, nazivNoviDir, izabraniRecept);
					/*DefaultMutableTreeNode test = new DefaultMutableTreeNode((Folder) trenutniDir.getElementi().get(0));
					selectedNode.add(test);
					tree.treeDidChange();*/	
					aplikacija.getMenadzerKorisnika().removeObserver(ProzorBookmarkovanja.this);
					dispose();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDodajDirektorijum.setBounds(267, 9, 201, 34);
		contentPane.add(btnDodajDirektorijum);
		
		btnDodajUIzabrani = new JButton("Dodaj u izabrani direktorijum");
		btnDodajUIzabrani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastBtnPressed = (JButton) e.getSource();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Folder trenutniDir = (Folder) selectedNode.getUserObject();
				try {
					kontroler.dodajReceptUDirektorijum(trenutniDir, izabraniRecept);
					aplikacija.getMenadzerKorisnika().removeObserver(ProzorBookmarkovanja.this);
					dispose();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDodajUIzabrani.setBounds(267, 322, 201, 34);
		contentPane.add(btnDodajUIzabrani);
		
		btnObrisi = new JButton("Obrisi");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastBtnPressed = (JButton) e.getSource();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				ElementBookmarkovanja izabraniElement = (ElementBookmarkovanja) selectedNode.getUserObject();
				if (selectedNode.isRoot()) {
					JOptionPane.showMessageDialog(null, "Ne mozete obrisati opsti direktorijum!", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
				Folder sadrzeciFolder = (Folder) parentNode.getUserObject();
				DefaultMutableTreeNode parentOfParentNode = null;
				Folder folderSadrzecegFoldera = null;
				DefaultMutableTreeNode rootTreeNode = ((DefaultMutableTreeNode) tree.getModel().getRoot());
				if (parentNode != rootTreeNode) {
					parentOfParentNode = (DefaultMutableTreeNode) parentNode.getParent();
					folderSadrzecegFoldera = (Folder) parentOfParentNode.getUserObject();
				}
				try {
					kontroler.obrisiElementBookmarkovanja(izabraniElement, sadrzeciFolder, folderSadrzecegFoldera);
					parentNode.remove(selectedNode);
					if (parentNode.getChildCount() == 0 && parentNode != rootNode)
						parentOfParentNode.remove(parentNode);
					((DefaultTreeModel) tree.getModel()).nodeStructureChanged(rootNode);
					sakrijDugmad();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnObrisi.setBounds(10, 367, 89, 23);
		contentPane.add(btnObrisi);
		
		btnPreimenuj = new JButton("Preimenuj");
		btnPreimenuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastBtnPressed = (JButton) e.getSource();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				ElementBookmarkovanja izabraniElement = (ElementBookmarkovanja) selectedNode.getUserObject();
				if (selectedNode.isRoot()) {
					JOptionPane.showMessageDialog(null, "Ne mozete preimenovati opsti direktorijum!", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
				Folder sadrzeciFolder = (Folder) parentNode.getUserObject();
				String noviNaziv = JOptionPane.showInputDialog("Unesite zeljeni naziv:");
				try {
					kontroler.preimenujElementBookmarkovanja(izabraniElement, noviNaziv, sadrzeciFolder);
					sakrijDugmad();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPreimenuj.setBounds(109, 367, 148, 23);
		contentPane.add(btnPreimenuj);
		
		btnOtvoriRecept = new JButton("Otvori recept");
		btnOtvoriRecept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastBtnPressed = (JButton) e.getSource();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Bookmark trenutniBookmark = (Bookmark) selectedNode.getUserObject();
				Recept recept = trenutniBookmark.getRecept();
				KontrolerProzoraPrikazaRecepta kontroler = new KontrolerProzoraPrikazaRecepta(aplikacija);
				//	possibly:
				aplikacija.getMenadzerKorisnika().removeObserver(ProzorBookmarkovanja.this);
				ProzorPikazaRecepta prozor = new ProzorPikazaRecepta(recept, aplikacija, kontroler);
				aplikacija.getMenadzerKorisnika().addObserver(ProzorBookmarkovanja.this);
				//dispose();
			}
		});
		btnOtvoriRecept.setBounds(267, 167, 201, 34);
		contentPane.add(btnOtvoriRecept);
		
		sakrijDugmad();

		this.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 ProzorBookmarkovanja prozor = (ProzorBookmarkovanja) windowEvent.getSource();
	             prozor.aplikacija.getMenadzerKorisnika().removeObserver(prozor);
	         }        
	    }); 
	}
	
	public void sakrijDugmad() {
		btnOtvoriRecept.setVisible(false);
		btnDodajDirektorijum.setVisible(false);
		btnDodajUIzabrani.setVisible(false);
		btnObrisi.setVisible(false);
		btnPreimenuj.setVisible(false);
	}
	
	public void kreirajCvorove(DefaultMutableTreeNode dirNode) {
		Folder dir = (Folder) dirNode.getUserObject();
		if (dir.getElementi() != null) {
			for (ElementBookmarkovanja eb : dir.getElementi()) {
				DefaultMutableTreeNode subnode = new DefaultMutableTreeNode(eb);
				dirNode.add(subnode);
				if (eb instanceof Folder)
					kreirajCvorove(subnode);
			}
		}
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		// TODO Auto-generated method stub
		if (lastBtnPressed == btnDodajDirektorijum)
			JOptionPane.showMessageDialog(null, "Uspesno ste dodali recept u novi direktorijum.", "", JOptionPane.INFORMATION_MESSAGE);
		else if (lastBtnPressed == btnDodajUIzabrani)
			JOptionPane.showMessageDialog(null, "Uspesno ste dodali recept u izabrani direktorijum.", "", JOptionPane.INFORMATION_MESSAGE);
		else if (lastBtnPressed == btnPreimenuj) {
			JOptionPane.showMessageDialog(null, "Uspesno preimenovano.", "", JOptionPane.INFORMATION_MESSAGE);
			tree.treeDidChange();
		}
		else if (lastBtnPressed == btnObrisi) {
			JOptionPane.showMessageDialog(null, "Uspesno obrisano.", "", JOptionPane.INFORMATION_MESSAGE);
			tree.treeDidChange();
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (node == null)
			return;

		Object nodeObject = node.getUserObject();
		if (dodavanjeMode) {
			if (nodeObject instanceof Bookmark) {
				btnDodajDirektorijum.setVisible(false);
				btnDodajUIzabrani.setVisible(false);
			} else if (nodeObject instanceof Folder) {
				btnDodajDirektorijum.setVisible(true);
				btnDodajUIzabrani.setVisible(true);
			}
		} else {
			if (nodeObject instanceof Bookmark) {
				btnObrisi.setVisible(true);
				btnPreimenuj.setVisible(true);
				btnOtvoriRecept.setVisible(true);
			} else if (nodeObject instanceof Folder) {
				btnObrisi.setVisible(true);
				btnPreimenuj.setVisible(true);
				btnOtvoriRecept.setVisible(false);
			}
		}
	}
}
