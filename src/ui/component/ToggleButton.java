package ui.component;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.event.ActionEvent;

/**
 * Button variation that has two states: pressed(toggled) and released(untoggled)
 * 
 * @author Ruben Cordeiro
 *
 */
public class ToggleButton extends Button{
	private Image toggled, untoggled;
	private boolean isToggled;
	private ActionEvent toggledAction, untoggledAction;

	public ToggleButton(Component comp)
	{
		super(comp);
		isToggled = true;
	}

	@Override
	public void doClick()
	{
		
	}
	
	public boolean isToggled() {return isToggled;}

	public void loadToggledImage(String filepath)
	{
		try
		{
			toggled = ImageIO.read(getClass().getResource(filepath));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		setImage(toggled);
	}

	public void loadUntoggledImage(String filepath)
	{
		try
		{
			untoggled = ImageIO.read(getClass().getResource(filepath));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(getBounds().contains(x, y))
		{
			if(isToggled)
			{
				action = untoggledAction;
				isToggled = false; 
				setImage(untoggled);
			}
			else
			{
				action = toggledAction;
				isToggled = true;
				setImage(toggled);
			}
			
			notifyObservers();
		}
	}
	
	public void setToggledAction(String msg)
	{
		toggledAction = new ActionEvent(msg);
	}
	
	public void setUntoggledAction(String msg)
	{
		untoggledAction = new ActionEvent(msg);
	}
}
