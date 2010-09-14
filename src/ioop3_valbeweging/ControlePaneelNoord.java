package ioop3_valbeweging;

import javax.swing.*;
import java.awt.*;

public class ControlePaneelNoord extends JPanel
{
   //private JTextField bereikXveld;
   private JTextField bereikYveld;
   private JTextField dtVeld;
   

   // geef de velden bereikXveld en bereikYveld default de 
   // waarden 100 (meter)
   // geef het dtVeld (steptime) default de waarde 20 (msec)
   // geef bij verkeerde invoer de default-waarden weer 
   // gebruik daartoe een try-catch-clause 
     
   public ControlePaneelNoord()
   {
      bereikYveld = new JTextField("100",5);
      dtVeld = new JTextField("20", 4);
      
      Label y = new Label("Hoogte(m)");
      Label dt = new Label("dt(ms)");
      
      add(y);
      add(bereikYveld);
      add(dt);
      add(dtVeld);
   }

   public double getYbereik()
   {
     return 
   }

   public double getXbereik()
   {
     return 
   }


   public int getDt()
   {
     return 
   }
   
   // wat zou je kunnen doen om tijdens de animatie het gebruik van
   // de invoervelden onmogelijk te maken? 

}
