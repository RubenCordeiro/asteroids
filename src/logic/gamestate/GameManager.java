package logic.gamestate;

import java.awt.Component;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.HashMap;
import java.util.Map;

import logic.AnimationManager;
import logic.HUDManager;
import logic.statistics.Leaderboard;
import sound.SoundManager;

/**
 * The GameManager controls the overall flow of the game and stores all the options and Rendering managers.
 * It also redirects the Mouse and Keyboard events to the current state, acting as a mediator.
 * 
 * @author Ruben Cordeiro
 *
 */
public class GameManager implements KeyListener, MouseListener, MouseMotionListener
{
	public static final int RESTART = 0, RESUME = 1;
	private Component comp;
	private Graphics2D g2d;
	Map<Screens, GameState> states;
	GameState currentState;
	
	private SoundManager _soundManager;
	private AnimationManager _animationManager;
	private HUDManager _hudManager;
	private Leaderboard _leaderboard;
	
	private boolean mouseEnabled;

	public GameManager(GameState initial)
	{
		currentState = initial;
		states = new HashMap<Screens, GameState>();
		
		_soundManager = new SoundManager();
		_animationManager = new AnimationManager();
		_hudManager = new HUDManager();
		
		setLeaderboard(Leaderboard.load("leaderboard.ast"));
		if(getLeaderboard() == null)
			setLeaderboard(new Leaderboard());
		
		setMouseEnabled(false);
	}

	public GameManager(GameState initial, Component comp, Graphics2D g2d)
	{
		this(initial);
		this.comp = comp; this.g2d = g2d; 
		_leaderboard.setGraphics(g2d);
		initStates();
	}
	
	public void initStates()
	{
		states.put(Screens.MAIN, new MainScreen(comp, g2d, this));
		states.put(Screens.PAUSED, new PauseScreen(comp,g2d,this));
		states.put(Screens.START, new StartScreen(comp, g2d, this));
		states.put(Screens.OPTIONS, new SettingsScreen(comp, g2d, this));
		states.put(Screens.GAMEOVER, new GameOverScreen(comp, g2d, this));
		states.put(Screens.LEADERBOARDS, new LeaderboardScreen(comp, g2d, this));
		states.put(Screens.HELP, new HelpScreen(comp, g2d, this));
	}
	
	/**
	 * Switches the current state with a given action. 
	 * The action can be either RESTART (restarts the nextScreen state) 
	 * or RESUME (switches to the next screen and resumes it's activity)
	 * 
	 * @param nextScreen new state to be activated
	 * @param action action to be taken
	 */
	public void goNext(Screens nextScreen, int action)
	{
		GameState state = states.get(nextScreen);

		if(state == null)
			return;
		
		if(action == RESTART)
			state.restart(); 
		
		currentState = state;
	}
	
	public void setState(GameState state) 
	{
		currentState = state;
	}

	public GameState getCurrentState()
	{
		return currentState;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		currentState.mouseClicked(e);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		currentState.mouseEntered(e);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		currentState.mouseExited(e);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		currentState.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentState.mouseReleased(e);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		currentState.keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentState.keyReleased(e);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		currentState.keyTyped(e);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		currentState.mouseDragged(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		currentState.mouseMoved(e);

	}

	public SoundManager getSoundManager() {
		return _soundManager;
	}

	public void setSoundManager(SoundManager _soundManager) {
		this._soundManager = _soundManager;
	}

	public AnimationManager getAnimationManager() {
		return _animationManager;
	}

	public void setAnimationManager(AnimationManager _animationManager) {
		this._animationManager = _animationManager;
	}

	public HUDManager getHUDManager() {
		return _hudManager;
	}

	public void setHUDManager(HUDManager _hudManager) {
		this._hudManager = _hudManager;
	}

	public boolean isMouseEnabled() {
		return mouseEnabled;
	}

	public void setMouseEnabled(boolean mouseEnabled) {
		this.mouseEnabled = mouseEnabled;
	}

	public Leaderboard getLeaderboard() {
		return _leaderboard;
	}

	public void setLeaderboard(Leaderboard _leaderboard) {
		this._leaderboard = _leaderboard;
	}
}
