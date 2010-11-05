package ipse;

import java.util.ArrayList;

import bankapplicatie.View;

public class Controller {
	// De klasse View is de superklasse van de views (behalve de HoofdView)
	private ArrayList<View> viewListeners;
	
	public Controller()
	{
		viewListeners = new ArrayList<View>();
	}
	
	public void reportChange()
	{
		for (View v : viewListeners)
		  v.reportChange();		
	}
	
	public void addListener (View v)
	{
		viewListeners.add (v);
	}
	
	public void removeListener (View v)
	{
		viewListeners.remove (v);
	}	
}
