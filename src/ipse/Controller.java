package ipse;

import java.util.ArrayList;

public class Controller {
	// De klasse View is de superklasse van de views (behalve de HoofdView)
	private ArrayList<View> viewListeners;
	private HoofdView hoofdView;
	
	public Controller()
	{
		viewListeners = new ArrayList<View>();
	}
	
	public void reportChange()
	{
		for (View v : viewListeners)
		  v.reportChange();
		if (hoofdView != null)
			hoofdView.reportChange();
	}
	
	public void addListener (View view)
	{
		viewListeners.add (view);
	}
	
	public void removeListener (View v)
	{
		viewListeners.remove (v);
	}

	public void setHoofdView(HoofdView hoofdView) {
		this.hoofdView = hoofdView;
	}
}
