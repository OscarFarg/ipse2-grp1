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
      // zet de 'ondoorzichtbaarheid' van de view op false .. zie 'setOpaque' in de API 
      // initialiseer this.bal mbv valpaneel
      
      // initialiseer this.valpaneel
      // initialiseer this.noordpaneel
      
      // geef deze view een size van 12 pixles breed en 12 hoog
      // creeer het object dataview (wat geef je als parameter mee?)
   }
  
 
   
   
   public void paintComponent (Graphics g)
   {
      super.paintComponent(g);
      
      double schaalfactor_y = (valpaneel.getEindY() - valpaneel.getStartY()) / noordpaneel.getYbereik();
      double schaalfactor_x = .........................................................................;
      
      // wat stelt de schaalfactor nou precies voor?
      // waarom staan deze instructies in de paintComponent-methode en niet bv
      // eenmalig in de constructor?
      
      
      int x = (int) (valpaneel.getStartX() + bal.getX() * schaalfactor_x); 
      int y = ...........................................................; 
      // wat doen deze instructies?
 
      // plaats de view op lokatie (x, y) 

      // zet de tekenkleur op de kleur van de bal
      // teken de bal
      // 
      // zorg dat de dataview opnieuw getekend wordt iedere keer dat de balview 
      // opnieuw getekend wordt ... waarom? 
   }

}

