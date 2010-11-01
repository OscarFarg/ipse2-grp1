package bankapplicatie;

import java.awt.event.*;
import javax.swing.*;

/*
 * Laat alle views (behalve de HoofdView) erven van deze klasse.
 * Implementeer in de (sub-)views de methode 'windowClosing' 
 * Bijvoorbeeld in een (sub-)view:
 *
 *     public void windowClosing (WindowEvent we)
 *     {
 *	      deController.removeListener(this);
 *	      this.dispose();
 *	   }
 *
 *
*/
public class View extends JFrame implements WindowListener
{
	
	public View()
	{
	    addWindowListener(this);
	}
		
	// nodig vanwege polymorfisme .. zie de klasse Controller
	public void reportChange()
	{}
	
	// alle methoden van de interface WindowListener .. 
	// implementatie in de betreffende (sub-)view .. 
    public void windowActivated (WindowEvent we)
	{}
	
	public void windowClosed (WindowEvent we)
	{}
	
	public void windowClosing (WindowEvent we)
	{}
	
	public void windowDeactivated (WindowEvent we)
	{}
	
	public void windowDeiconified (WindowEvent we)
	{}
	
	public void windowIconified (WindowEvent we)
	{}
	
	public void windowOpened (WindowEvent we)
	{}			
}