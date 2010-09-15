package ioop3_valbeweging;

import javax.swing.*;
import java.awt.event.*;

public class ControlePaneelZuid extends JPanel implements ActionListener
{
   private JButton animate;
   private JButton stop;

   private ValBewegingPaneel valpaneel;


   // bij het drukken op de stop-button wordt de
   // methode pleaseStop van de balController aangeroepen
   
   // bij het drukken op de animate-button wordt de reset-methode van de bal
   // aangeroepen, de balview wordt opnieuw getekend en de methode pleaseStart van de balController
   // wordt aangeroepen 
   
   public ControlePaneelZuid (ValBewegingPaneel paneel) 
   {
      animate = new JButton("Start");
      animate.addActionListener(this);
      stop = new JButton("Stop");
      stop.addActionListener(this);
      
      this.valpaneel = paneel;
      
      add(animate);
      add(stop);
   }

   public void actionPerformed (ActionEvent ae)
   {
      if (ae.getSource() == animate)
      {
    	  
      }
      else
      {
    	  
      }
   }

}
