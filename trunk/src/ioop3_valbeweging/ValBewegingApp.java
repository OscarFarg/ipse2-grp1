package ioop3_valbeweging;
import javax.swing.*;
import java.awt.*;

public class ValBewegingApp extends JFrame
{

   public ValBewegingApp()  // constructor
   {
      setSize (500, 600);
      setTitle ("valbeweging");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      ControlePaneelNoord noordpaneel = new ControlePaneelNoord();
      ValBewegingPaneel valpaneel = new ValBewegingPaneel (noordpaneel);
      ControlePaneelZuid zuidpaneel = new ControlePaneelZuid(valpaneel);

      add (noordpaneel, BorderLayout.NORTH);
      add (valpaneel, BorderLayout.CENTER);
      add (zuidpaneel, BorderLayout.SOUTH);
      setLocationRelativeTo(null);
      setVisible(true);
   }

   public static void main (String [] args)
   {
      new ValBewegingApp();
   }

}
