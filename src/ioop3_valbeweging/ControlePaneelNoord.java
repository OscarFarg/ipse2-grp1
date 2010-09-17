package ioop3_valbeweging;

import javax.swing.*;
import java.awt.*;

public class ControlePaneelNoord extends JPanel
{
   private JTextField bereikXveld;
   private JTextField bereikYveld;
   private JTextField dtVeld;
   private int breedte, hoogte, tijdverschil;

   // geef de velden bereikXveld en bereikYveld default de 
   // waarden 100 (meter)
   // geef het dtVeld (steptime) default de waarde 20 (msec)
   // geef bij verkeerde invoer de default-waarden weer 
   // gebruik daartoe een try-catch-clause 
     
   public ControlePaneelNoord()
   {
      resetInvoer();
	  
      bereikXveld = new JTextField("" + breedte, 4);
	  bereikYveld = new JTextField("" + hoogte, 4);
      dtVeld = new JTextField("" + tijdverschil, 4);
      
      Label x = new Label("Breedte(m)");
      Label y = new Label("Hoogte(m)");
      Label dt = new Label("dt(ms)");
      
      add(x);
      add(bereikXveld);      
      add(y);
      add(bereikYveld);
      add(dt);
      add(dtVeld);
   }
   
   public void resetInvoer()
   {
	   breedte = 100;
	   hoogte = 100;
	   tijdverschil = 20;
   }

   public double getYbereik()
   {
	   hoogte = Integer.parseInt(bereikYveld.getText());
	   return hoogte;
   }

   public double getXbereik()
   {
	   breedte = Integer.parseInt(bereikXveld.getText());
	   return breedte;
   }

   public int getDt()
   {
	   tijdverschil = Integer.parseInt(dtVeld.getText());
	   return tijdverschil;
   }
   
   // wat zou je kunnen doen om tijdens de animatie het gebruik van
   // de invoervelden onmogelijk te maken? 
   public void running(boolean running)
   {
	   if (running)
	   {
		   bereikXveld.setEditable(false);
		   bereikYveld.setEditable(false);
		   dtVeld.setEditable(false);
	   }
	   else
	   {
		   bereikXveld.setEditable(true);
		   bereikYveld.setEditable(true);
		   dtVeld.setEditable(true);
	   }
   }
}
