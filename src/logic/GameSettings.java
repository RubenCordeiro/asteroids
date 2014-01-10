package logic;

/**
 * Stores all the game settings variables.
 * @author Ruben Cordeiro
 *
 */
public class GameSettings {
	
	public static int FRAMERATE = 60, SCREENWIDTH = 1280, SCREENHEIGHT = 720, ASTEROIDS = 10,
			PROJECTILES = 10, PROJECTILES_SPEED = 4, SPRITE_NORMAL = 0, SPRITE_COLLIDED = 1, WAVESCOREBONUS = 200;
	
	public static int SPLIT_INCREASE_INTERVAL = 3; 
	public static double ACCELERATION = 0.05; 
	public static double FRICTION = 0.5;
	public static String LEADERBOARD_PATH = "leaderboard.ast";
}
