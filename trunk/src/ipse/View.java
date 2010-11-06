package ipse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class View extends JFrame implements ActionListener, WindowListener
{
	//protected zodat die in alle subklasses gebruikt kan worden.
	protected JPanel mainPanel;

	private JButton opslaanKnop, annulerenKnop;

	protected Database database;
	protected Controller controller;
	protected boolean updateMode = false;

	public View( Database database, Controller controller )
	{
		this.database = database;
		this.controller = controller;
		this.addWindowListener(this);
		controller.addListener(this);
		
		ImageIcon logoIcon = new ImageIcon("src/ipse/images/logo.png");
		JLabel imageLabel = new JLabel( logoIcon );
		imageLabel.setBounds( 150, 10, 300, 100 );

		//De knoppen voor alle views

		opslaanKnop = new JButton( "Opslaan" );
		opslaanKnop.addActionListener( this );
		opslaanKnop.setBounds( 350, 250, 100, 30 );

		annulerenKnop = new JButton( "Annuleren" );
		annulerenKnop.addActionListener( this );
		annulerenKnop.setBounds( 460, 250, 100, 30 );

		//Dit geld voor alle views.
		setSize( 600,400 );
		setLocationRelativeTo(null);
		//setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable( false );

		mainPanel = new JPanel();
		setContentPane( mainPanel );

		mainPanel.add( imageLabel );
		mainPanel.add( opslaanKnop );
		mainPanel.add( annulerenKnop );

		mainPanel.setLayout( null );

	}

	public void opslaan(){
		this.dispose();
	}

	public void annuleren(){
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == opslaanKnop)
			opslaan();
		else if (e.getSource() == annulerenKnop)
			annuleren();
		controller.reportChange();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		controller.removeListener(this);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void reportChange() {
	}
}
