package ioop3_valbeweging;

import javax.swing.*;
import java.awt.*;

public class ControlePaneelNoord extends JPanel
{
   //private JTextField bereikXveld;
   private JTextField bereikYveld;
   private JTextField dtVeld;
   private int hoogte, tijdverschil;

   // geef de velden bereikXveld en bereikYveld default de 
   // waarden 100 (meter)
   // geef het dtVeld (steptime) default de waarde 20 (msec)
   // geef bij verkeerde invoer de default-waarden weer 
   // gebruik daartoe een try-catch-clause 
     
   public ControlePaneelNoord()
   {
      resetInvoer();
	   
	  bereikYveld = new JTextField("" + hoogte,5);
      dtVeld = new JTextField("" + tijdverschil, 4);
      
      Label y = new Label("Hoogte(m)");
      Label dt = new Label("dt(ms)");
      
      add(y);
      add(bereikYveld);
      add(dt);
      add(dtVeld);
   }
   
   public void resetInvoer()
   {
	   hoogte = 100;
	   tijdverschil = 20;
   }

   public double getYbereik()
   {
     return hoogte;
   }

   //public double getXbereik()
   //{
   //  return 
   //}

   public int getDt()
   {
     return tijdverschil;
   }
   
   // wat zou je kunnen doen om tijdens de animatie het gebruik van
   // de invoervelden onmogelijk te maken? 
   public void running(boolean running)
   {
	   if (running)
	   {
		   bereikYveld.setEditable(false);
		   dtVeld.setEditable(false);
	   }
	   else
	   {
		   bereikYveld.setEditable(true);
		   dtVeld.setEditable(true);
	   }
   }
}
