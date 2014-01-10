package ui.swing;

import javax.swing.JFrame;

import logic.GameSettings;

import ui.applet.AsteroidsApplet;

/**
 * Swing based interface implementation
 * @author Ruben Cordeiro
 *
 */
public class AsteroidsSwing extends JFrame{
	AsteroidsApplet gameApplet;
	private static final long serialVersionUID = 1L;

	public AsteroidsSwing() {
		super("Asteroids");
		gameApplet = new AsteroidsApplet();
		gameApplet.init();
		gameApplet.start();
		getContentPane().add(gameApplet);
		this.setSize(GameSettings.SCREENWIDTH, GameSettings.SCREENHEIGHT);
		addMouseListener(gameApplet);
		addKeyListener(gameApplet);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	 public static void main(String args[]) {
	{
		AsteroidsSwing a = new AsteroidsSwing(); 
		a.setVisible(true);
	}
}}
