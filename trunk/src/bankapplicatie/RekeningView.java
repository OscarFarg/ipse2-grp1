package bankapplicatie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RekeningView extends View implements ActionListener
{

    private JTextField nummerVeld;
    private JComboBox persoonBox, rekeningBox;
    private JButton nieuwKnop;
    private JTextArea infoVak;

    private ArrayList<Persoon> persoonList;
    private ArrayList<Rekening> rekeningList;

    private Persoon currentPersoon;

    private Controller deController;

    private Database db;


    public RekeningView(Database db, Controller controller)
    {
	this.db = db;
	this.rekeningList = db.getRekeningen();
	this.persoonList = db.getPersonen();

	//System.out.println(rekeningList.size());

	this.deController = controller;
	deController.addListener(this);

	JPanel contentPane = new JPanel();
	nummerVeld = new JTextField(12);

	// Personen box
	persoonBox = new JComboBox();
	laadPersonen();

	persoonBox.addActionListener(this);

	// Rekeningbox
	rekeningBox = new JComboBox();
	laadRekeningen();
	rekeningBox.addActionListener(this);

	infoVak = new JTextArea(12, 15);

	nieuwKnop = new JButton("Nieuw");
	nieuwKnop.addActionListener(this);

	contentPane.add(new JLabel("Nr:"));
	contentPane.add(nummerVeld);
	contentPane.add(persoonBox);
	contentPane.add(rekeningBox);
	contentPane.add(infoVak);
	contentPane.add(nieuwKnop);

	this.setTitle("Rekening");
	this.setLocationRelativeTo(null);
	this.setSize(220, 300);
	this.setContentPane(contentPane);
	this.setResizable(false);
	this.setVisible(true);
    }

    public void laadPersonen()
    {
	db.refresh();
	persoonList = db.getPersonen();
	persoonBox.removeAllItems();
	for (Persoon p : persoonList)
	{
	    persoonBox.addItem(p.getNaam());
	}
	if (persoonList.size() > 0)
	    currentPersoon = persoonList.get(0);

    }

    public void laadRekeningen()
    {
	if (currentPersoon != null)
	{
	    db.refresh();
	    rekeningList = db.getRekeningen();
	    rekeningBox.removeAllItems();
	    for (Rekening r : rekeningList)
	    {
		if (r.getPersoon().getBsn().equals(currentPersoon.getBsn()))
		    rekeningBox.addItem(r.getNummer());
	    }
	}

    }

    public Rekening getRekening(String nummer)
    {
	Rekening rekening = null;
	for (Rekening r : rekeningList)
	{
	    if (r.getNummer().equals(nummer))
		rekening = r;
	}
	return rekening;
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
	if (ae.getSource() == persoonBox)
	{
	    if (persoonBox.getItemCount() > 0)
	    {
		currentPersoon = persoonList.get(persoonBox.getSelectedIndex());
		laadRekeningen();
	    }
	}

	if (ae.getSource() == rekeningBox)
	{
	    if (rekeningBox.getItemCount() > 0)
	    {
		Rekening r = getRekening(rekeningBox.getSelectedItem().toString());
		infoVak.setText("");
		infoVak.append("Rekeninghouder:\n" + currentPersoon.getNaam() + "\n" + currentPersoon.getBsn() + "\n\n");		
		infoVak.append(r.getNummer() + "\t" + r.getSaldo());
	    }

	}
	if (ae.getSource() == nieuwKnop)
	{
	    Rekening r = new Rekening(nummerVeld.getText(), currentPersoon);
	    db.insertRekening(r);
	    //rekeningList.add(r);
	    laadRekeningen();
	    deController.reportChange();
	}
    }

    public void reportChange()
    {
	laadPersonen();
	laadRekeningen();
    }

    public void windowClosing (WindowEvent we)
    {
	deController.removeListener(this);
	this.dispose();
    }

}
