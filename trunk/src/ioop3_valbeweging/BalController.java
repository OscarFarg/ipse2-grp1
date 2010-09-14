package ioop3_valbeweging;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BalController implements Runnable, MouseWheelListener
{
   private Bal bal;
   private BalView balview;
   private ControlePaneelNoord noordpaneel;
   
   private boolean doorgaan_thread; // thread aan of uit
   private boolean doorgaan_wheel;  // 'verplaatsen bal mbv het wieltje' aan of uit
   
   private int dt;  // steptime & sleeptime in msec
                    // wat betekent steptime en sleeptime?
                    // en wat betekent het dat ze gelijk zijn? 
                    
   private double valhoogte; // in meter 
   
   private Thread draad;
   
   

   public BalController (ValBewegingPaneel valpaneel, ControlePaneelNoord noordpaneel)
   {
      // gebruik valpaneel om this.bal te initialiseren
      // gebruik valpaneel om this.balview te initialiseren 
      // initialiseer this.noordpaneel 
      // geef valpaneel een MouseWheelListener en laat
      // het BalController (dit) object de events afhandelen 
      // zet de thread uit en
      // zet 'het verplaatsen van de bal mbv het wieltje' uit
      
      // initialiseer this.dt mbv het noordpaneel
      // initialiseer this.valhoogte mbv het noordpaneel 
   }

   public void run()
   {      
      while (doorgaan_thread)
      {      
         if // laat de thread stoppen als de bal de bodem bereikt 
         {
            pleaseStop();
            return;
         }
         else
         {
            // las een pauze in van 'dt' msec            
            // gebruik de methode adjust van de
            // bal om de tijd aan te passen met 'dt' 
         }
         balview.repaint();// teken balview opnieuw ... waarom? 
      }
   }


   public void mouseWheelMoved (MouseWheelEvent event)
   {      
      if       // deze methode alleen uitvoeren als de thread uitstaat EN
      {        // 'het verplaatsen met het wieltje' aan 
      
        int ticks = event.getWheelRotation(); // wat levert dit op?
        
        if ((bal.getY() <  valhoogte) && (bal.getT() > 0) ) // waarom deze conditie? 
           // hoe pas je nu de bal aan?  gebruik 'adjust', 'ticks' en 'dt' 
        else
          return;
           
        // teken balview opnieuw 
      }
   }

   public void pleaseStart()
   {
       if (draad != null)
          return;       // waarom? 
          
       // initialiseer this.dt mbv noordpaneel
       // initialiseer this.valhoogte mbv noordpaneel 
       
       doorgaan_thread = true;  // zet de thread aan 
       doorgaan_wheel = false;  // zet de 'verplaatsing mbv het wieltje' uit 
           
       draad = // creeer een nieuwe thread 
       draad.start(); // welke methode roept dit statement aan? 
   }

   public void pleaseStop()
   {
      // zet de thread uit 
      // zet de 'verplaatsing mbv het wieltje' aan     
      draad = null;   // waarom?
   }

   private void slaap (int msec)
   {
      try
      {
         Thread.sleep (msec);
      }
      catch (InterruptedException ex)
      {
      }
   }
   
}








