package ioop3_valbeweging;

import java.awt.*;

public class Bal
{
   private double x;   // x, y wereld-coordinaten 
   private double y;   // van de puntmassa (een bal)
                       // de omzetting in screen-coordinaten gebeurt in BalView 
                       
   private double vy;  // snelheid van de bal in meter/sec
   private int t;      // verstreken tijd in msec
   
   private Color kleur;// kleur van de bal
   
   private final double VALVERSNELLING = 9.81; // natuurkundige constante
                                               // nodig bij het bepalen van
                                               // afgelegde weg en snelheid
                                               // van de puntmassa (bal) 


   public Bal ()
   {
      this.kleur = Color.RED;
      reset();
   }

   public void adjust (int dt) // dt in msec
   {
      t = t + dt; // t en dt in msec(!) 
      
      
      // valbeweging formule over de afgelegde weg .. zie een basis natuurkunde boek.
      // y (afgelegde weg in meters) = 1/2 * VALVERSNELLING * tijd * tijd (tijd in seconden) 
       
      y = 1/2 * VALVERSNELLING * t * t;
 
      	
      // vy is de snelheid (in meter/sec) van de puntmassa
      // vy = VALVERSNELLING * tijd (tijd in seconden) 
      
      vy = VALVERSNELLING * t;
      
      // waarom verandert hier (bij deze animatie) de x-waarde niet? 
      
   }
   
   public void reset() // zet de startwaarden 
   {
      t = 0;
      x = 10; 
      y = 0;
      vy = 0; // snelheid op tijdstip t == 0.
   }
   
   // getters
   public double getX()
   {
      return x;
   }

   public double getY()
   {
      return y;
   }

   public double getVy()
   {
      return vy;
   }

   public int getT()
   {
      return t;
   }
   
   public Color getKleur()
   {
      return kleur;
   }

   // setters .. zo nodig bij toekomstige uitbreidingen .. 
   public void setY (double y)
   {
      this.y = y;
   } 

   public void setX (double x)
   {
      this.x = x;
   }
} 
