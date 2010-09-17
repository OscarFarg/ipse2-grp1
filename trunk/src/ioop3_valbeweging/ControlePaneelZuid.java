package ioop3_valbeweging;

import javax.swing.*;
import java.awt.event.*;

public class ControlePaneelZuid extends JPanel implements ActionListener
{
   private JButton animate;
   private JButton stop;
   private JButton reset;

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
      reset = new JButton("Reset");
      reset.addActionListener(this);
      
      this.valpaneel = paneel;
      
      add(animate);
      add(stop);
      add(reset);
   }

   public void actionPerformed (ActionEvent ae)
   {
      if (ae.getSource() == animate)
      {
    	  
      }
      if (ae.getSource() == stop)
      {
    	  
      }
      if (ae.getSource() == reset)
      {
    	  
      }
   }

}
