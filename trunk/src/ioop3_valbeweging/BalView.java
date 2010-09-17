package ioop3_valbeweging;

import javax.swing.*;
import java.awt.*;


public class BalView extends JPanel 
{
	private Bal bal;

	private ValBewegingPaneel valpaneel;
	private ControlePaneelNoord noordpaneel;
	private DataView dataview;


	public BalView (ValBewegingPaneel valpaneel, ControlePaneelNoord noordpaneel)
	{
		this.valpaneel = valpaneel;
		this.noordpaneel = noordpaneel;

		setOpaque ( false );
		setBounds(0, 0, 12, 12);

		//Door Oscar. Bal werd niet geinitialiseerd.


		// zet de 'ondoorzichtbaarheid' van de view op false .. zie 'setOpaque' in de API 
		// initialiseer this.bal mbv valpaneel
		this.bal = valpaneel.getBal();


		// initialiseer this.valpaneel
		// initialiseer this.noordpaneel

		// geef deze view een size van 12 pixles breed en 12 hoog
		// creeer het object dataview (wat geef je als parameter mee?)
		dataview = new DataView ( valpaneel );
		dataview.setLocation( 300, 20 );

	}




	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		double schaalfactor_y = (valpaneel.getEindY() - valpaneel.getStartY()) / noordpaneel.getYbereik();
		double schaalfactor_x = (valpaneel.getEindX() - valpaneel.getStartX()) / noordpaneel.getXbereik();

		// wat stelt de schaalfactor nou precies voor?
		// waarom staan deze instructies in de paintComponent-methode en niet bv
		// eenmalig in de constructor?


		int x = (int) (valpaneel.getStartX() + bal.getX() * schaalfactor_x); 
		int y = (int) (valpaneel.getStartY() + bal.getY() * schaalfactor_y); 
		// wat doen deze instructies?

		// plaats de view op lokatie (x, y) 
		setBounds(x, y, 12, 12);

		// zet de tekenkleur op de kleur van de bal      	
		g.setColor( bal.getKleur());


		// teken de bal
		g.fillOval(0, 0, 12, 12 );
		g.setColor( Color.black);
		g.drawOval(0, 0, 12, 12);

		dataview.repaint();
		// 
		// zorg dat de dataview opnieuw getekend wordt iedere keer dat de balview 
		// opnieuw getekend wordt ... waarom? 
	}




	public DataView getDataview() {
		return dataview;
	}

}

