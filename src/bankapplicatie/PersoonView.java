package associaties.database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PersoonView extends View implements ActionListener, ListSelectionListener {

    private ArrayList<Persoon> persoonList;
    private JList bsnList;
    private JTextField bsnVeld, naamVeld;
    private JButton nieuwKnop;
    private DefaultListModel bsnListContents;
    private JTextArea infoVak;
    private JPanel infoPane, listPane;
    private Controller deController;
    private Database db;

    public PersoonView(Database db, Controller controller)
    {
	this.db = db;
	this.persoonList = db.getPersonen();
	
	this.deController = controller;
	deController.addListener(this);

	JPanel contentPane = new JPanel();
	infoPane = new JPanel();
	listPane = new JPanel();

	listPane.setLayout(null);
	listPane.setBorder(BorderFactory.createTitledBorder("Personen"));
	listPane.setBounds(5, 5, 150, 260);

	infoPane.setLayout(null);
	infoPane.setBorder(BorderFactory.createTitledBorder("Informatie"));
	infoPane.setBounds(160,5,200,260);

	contentPane.setLayout(null);


	//Klantenlijst
	createPersoonList();

	//Naam en nummer veld
	bsnVeld = new JTextField("BSN", 10);
	naamVeld = new JTextField("Naam", 10);
	bsnVeld.setBounds(10, 20, 80, 20);
	naamVeld.setBounds(100, 20, 80, 20);

	//Knop
	nieuwKnop = new JButton("Nieuw");
	nieuwKnop.setBounds(10, 230, 80, 20);
	nieuwKnop.addActionListener(this);

	//Informatievak.
	infoVak = new JTextArea(30,100);
	infoVak.setBounds(10, 50, 185, 170);
	infoVak.setEditable(false);

	listPane.add(bsnList);

	infoPane.add(naamVeld);
	infoPane.add(bsnVeld);
	infoPane.add(infoVak);
	infoPane.add(nieuwKnop);

	contentPane.add(listPane);
	contentPane.add(infoPane);

	this.setTitle("Personen");
	this.setLocationRelativeTo(null);
	this.setSize(375, 300);

	this.setContentPane(contentPane);
	this.setResizable(false);
	this.setVisible(true);

    }

    public DefaultListModel createListContents(ArrayList<Persoon> persoonList)
    {
	DefaultListModel listModel = new DefaultListModel();
	for(Persoon p : persoonList)
	{
	    listModel.addElement(p.getNaam());
	}
	return listModel;
    }

    public void createPersoonList()
    {
	db.refresh();
	persoonList = db.getPersonen();
	bsnListContents = this.createListContents(persoonList);
	bsnList = new JList(bsnListContents);
	bsnList.setBounds(5, 20, 140, 230);
	bsnList.addListSelectionListener(this);
    }

    public void reportChange()
    {
	int i = bsnList.getSelectedIndex();
	this.listPane.remove(bsnList);
	createPersoonList();
	this.listPane.add(bsnList);
	bsnList.setSelectedIndex(i);
	this.listPane.repaint();
	
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
	if (ae.getSource() == nieuwKnop)
	{
	    String bsn = this.bsnVeld.getText();
	    String naam = this.naamVeld.getText();

	    //this.persoonList.add(new Persoon(bsn, naam));
	    db.insertPersoon(new Persoon(bsn, naam));
	    deController.reportChange();


	}
    }

    @Override
    public void valueChanged(ListSelectionEvent lse)
    {
	if (lse.getSource() == bsnList)
	{
	    naamVeld.setText(persoonList.get(bsnList.getSelectedIndex()).getNaam());
	    bsnVeld.setText(persoonList.get(bsnList.getSelectedIndex()).getBsn());
	    infoVak.setText(null);
	    for (Rekening r : persoonList.get(bsnList.getSelectedIndex()).getRekeningen())
	    {
		infoVak.append("Rekening: " + r.getNummer() + "\n");
		infoVak.append("Saldo: " + r.getSaldo() + "\n");
		infoVak.append("------------------------\n");

	    }

	}	
    }

    public void windowClosing (WindowEvent we)
    {
	deController.removeListener(this);
	this.dispose();
    }
}
