package associaties.database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HoofdView extends JFrame implements ActionListener, WindowListener {

    private Database db;
    private Controller deController;
    
    private JButton persoonKnop, rekeningKnop;

    public HoofdView()
    {
	JPanel contentPane = new JPanel();
	persoonKnop = new JButton("Persoon");
	rekeningKnop = new JButton("Rekening");

	persoonKnop.addActionListener(this);
	rekeningKnop.addActionListener(this);

	deController = new Controller();
	
	db = new Database();
	
	contentPane.add(persoonKnop);
	contentPane.add(rekeningKnop);

	this.setTitle("Bank - Luc, Lennard, Oscar, Theo");
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(contentPane);
	this.pack();
	this.setResizable(false);
	this.setVisible(true);
    }

    public Database getDb()
    {
        return db;
    }

    public static void main(String[] args)
    {
	new HoofdView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == persoonKnop)
	    new PersoonView(db, deController);
	if (e.getSource() == rekeningKnop)
	    new RekeningView(db, deController);
    }

    @Override
    public void windowActivated(WindowEvent arg0)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void windowClosed(WindowEvent arg0)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void windowClosing(WindowEvent we)
    {
	db.close();
    }

    @Override
    public void windowDeactivated(WindowEvent arg0)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void windowDeiconified(WindowEvent arg0)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void windowIconified(WindowEvent arg0)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void windowOpened(WindowEvent arg0)
    {
	// TODO Auto-generated method stub
	
    }
}
