package ioop3_valbeweging;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class DataView extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener
{

	private Bal bal;  
	private ValBewegingPaneel paneel;
	private final int MINHOOGTE = 17;// mininale hoogte van dit view in pixels
	private int hoogte;              // actuele hoogte van dit view

	private int x, y;
	private boolean dragged = false;
	private int dragX, dragY;



	public DataView(ValBewegingPaneel paneel)
	{
		// zet de achtergrond van deze view op oranje 
		// initialiseer bal, paneel en hoogte 
		//
		// voeg de verschillende MouseListeners toe aan dit view   
		//
		this.paneel = paneel;
		this.bal = paneel.getBal();
		hoogte = 100;

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		// geef dit view een rand met een titel ("bal-data")
		// zie de methode createTitledBorder van de klasse BorderFactory
		// details?  zie API 
		Border titelrand = BorderFactory.createTitledBorder("Bal-data"); 

		this.setBorder(titelrand);
		this.setBackground(Color.ORANGE);
		setSize (180, hoogte);
		setLocation (0, 0);

		// voeg dit view toe aan het paneel 
	}



	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		if ((x != 0) && (y != 0))
			setLocation (x, y);

		String st_t = String.format ("%.2f", bal.getT() / 1000.0); 
		// wat doet dit statement precies?
		// waarom wordt er door 1000.0 gedeeld? 

		String st_y = String.format("%.2f", bal.getY()); // idem maar nu met de actuele afgelegde afstand van de bal/puntmassa  
		String st_vy = String.format("%.2f", bal.getVy());// idem maar nu met de actuele snelheid van de bal/puntmassa  

		// zet de tekenkleur op blauw 
		// druk tijd (in sec) , afgelegde weg (in meter)  en snelheid (in meter/sec) netjes af in deze view      
		//

		g.setColor(Color.BLUE);
		g.drawString("Tijd = " + st_t, 10, 10);
		g.drawString("Afgelegde weg = " + st_y, 10, 20);
		g.drawString("Snelheid = " + st_vy, 10, 30);

	}

	// MouseWheelListener-method
	public void mouseWheelMoved (MouseWheelEvent ev)
	{
		int ticks = ev.getWheelRotation();

		// pas de hoogte van deze view aan mbv de waarde 'ticks'
		// mocht de hoogte < MINHOOGTE dan mag de view niet smaller worden dan MINHOOGTE
		// zie ook de methode setSize
		if (MINHOOGTE < (hoogte + ticks))
		{
			this.hoogte += ticks;
			this.setSize(this.getWidth(), hoogte);
		}
	}

	// MouseListener-methods
	public void mouseClicked (MouseEvent me)
	{
	}

	public void mouseEntered (MouseEvent me)
	{
	}

	public void mouseExited (MouseEvent me)
	{
	}
	// waarom staan de vorige drie methoden hier? 
	// als ze niets doen dan kan ik ze toch beter weglaten? 
	// Moeten er zijn omdat ze bij de mouselistener horen.

	public void mouseReleased (MouseEvent me)
	{
		//.........................// zie ook mouseDragged 
		dragged = false;
	}

	public void mousePressed (MouseEvent me) // wat doet deze methode hier?
	{}

	// MouseMotionListener-methods   
	public void mouseDragged (MouseEvent me)
	{
		// zie aan de hand van het Vierkanten voorbeeld
		// hoe je de view kan verplaatsen
		// maak gebruik van de eigenschappen 'dragX'en 'dragY'en
		// natuurlijk de eigenschap 'dragged' 
		//   
		if (!dragged)
		{
			dragged = true;
			dragX = me.getX();
			dragY = me.getY();
		}
		int x = this.getX() + me.getX() - dragX;
		int y = this.getY() + me.getY() - dragY;
		this.setLocation( x, y );
	}

	public void mouseMoved (MouseEvent me) // wat doet deze methode hier? 
	{}       

}


