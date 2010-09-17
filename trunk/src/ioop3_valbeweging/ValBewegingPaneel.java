package ioop3_valbeweging;
import javax.swing.*;
import java.awt.*;

// De code van ValBewegingPaneel is compleet
// Bestudeer de code goed (in relatie met de andere klassen)
// Raadpleeg desnoods de API 
// beantwoord de vragen 


public class ValBewegingPaneel extends JPanel 
{
   private Bal deBal;
   private BalView balview;
   private BalController controller;

   private int startX, startY;
   private int eindX, eindY;
   private final int PARTS = 12; // hoezo 12? 
  
   
   private Stroke s_new;

   public ValBewegingPaneel(ControlePaneelNoord noordpaneel)
   {
     setLayout (null); // waarom? 

     float [] dashes = {3.0f,7.0f}; // wat gebeurt hier?
     s_new = new BasicStroke(0.5f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,1.0f,dashes,0);
     // leg uit! 
     
     deBal = new Bal ();
     balview = new BalView (this, noordpaneel);
     controller = new BalController (this, noordpaneel);
     add (balview);  
     add (balview.getDataview());
   }


   public void paintComponent (Graphics gr)
   {
      super.paintComponent(gr);
      Graphics2D g = (Graphics2D) gr; // wat gebeurt hier?
                                      // waarom?
      
      startX = getWidth() / PARTS;   // in pixels 
      startY = getHeight() / PARTS;  // idem
      eindX = (PARTS - 1) * startX;  // idem
      eindY = (PARTS - 1) * startY;  // idem 
      
      
      g.setColor (Color.BLACK);
      g.drawLine (startX, startY, eindX, startY);
 
      g.setStroke(s_new);
      for (int te = 2; te <= (PARTS - 2); te++)
      {
         g.drawLine (startX, te * startY, eindX, te * startY);
         g.drawLine (te * startX, startY, te * startX, eindY);         
      } 
      g.setStroke(new BasicStroke());      
      g.drawLine (startX, eindY, eindX, eindY);

      
      g.setColor (Color.BLUE);     
      g.drawString ("0", startX - 10, startY + 10);
      
   }

   // start getters
   
   public Bal getBal()
   {
      return (deBal);
   }
   
   public BalView getBalView()
   {
      return (balview);
   }
   
   public BalController getController()
   {
      return (controller);
   }
   
  
   public int getStartX()
   {
     return (startX);
   }
   
   public int getStartY()
   {
      return (startY);
   }
   
   public int getEindX()
   {
      return (eindX);
   }
   
   public int getEindY()
   {
      return (eindY);
   }
   
   public int getPARTS()
   {
     return (PARTS);
   }    
   // end getters 
   
}










