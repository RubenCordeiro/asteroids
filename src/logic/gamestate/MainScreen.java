package logic.gamestate;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Random;

import logic.AnimationManager;
import logic.AsteroidSpawnManager;
import logic.GameSettings;
import logic.HUDManager;
import logic.Point2D;
import logic.PowerUpSpawnManager;
import logic.Angle;

import logic.entity.AnimatedSprite;
import logic.entity.Asteroid;
import logic.entity.HealthPowerUp;
import logic.entity.ImageEntity;
import logic.entity.PowerUp;
import logic.entity.Projectile;
import logic.entity.ProjectilePowerUp;
import logic.entity.ShieldPowerUp;
import logic.entity.Ship;
import logic.entity.Sprite;

import logic.event.AnimationType;
import logic.event.WaveSpawnEvent;

import logic.factory.AnimatedSpriteGenerator;

import sound.SoundManager;
import sound.SoundSample;
import sound.SoundType;

/**
 * Represents the main game activity
 * @author Ruben Cordeiro
 *
 */
public class MainScreen extends GameState{

	static int ASTEROIDS = 6;
	static int PROJECTILES = 10; 
	static double ACCELERATION = 0.05;
	static int SPRITE_NORMAL = 0;
	static int SPRITE_COLLIDED = 1;

	protected BufferedImage backBuffer;

	private Ship ship;
	private ImageEntity background;

	private Projectile proj;
	ArrayList<Projectile> projectiles;
	ArrayList<Asteroid> asteroids;
	ArrayList<PowerUp> powerups;

	int currentProjectile = 0;

	public static Random rand = new Random();

	boolean keyDown, thrust, keyLeft, keyRight, keyFire;

	private int numAsteroids = ASTEROIDS;
	long startTime = System.currentTimeMillis();

	private AnimatedSpriteGenerator explosionGen, smokeExplosionGen;
	private SoundSample backgroundMusic, explosionSample, shotSample;

	private AsteroidSpawnManager spawnManager;
	private PowerUpSpawnManager powerUpManager;


	public MainScreen(Component comp, Graphics2D g2d, GameManager context)
	{
		component = comp; this.g2d = g2d;
		this.context = context;
		init();
	}

	public MainScreen() 
	{
		backBuffer =  new BufferedImage(GameSettings.SCREENWIDTH, GameSettings.SCREENHEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();
		init();
	}

	private void init() 
	{
		background = new ImageEntity(component);
		background.load("/resources/myBackground.png");
		ship = new Ship(component, g2d, 10000, 0);
		ship.load("/resources/ship.png");
		ship.setPosition(new Point2D(GameSettings.SCREENWIDTH/2, GameSettings.SCREENHEIGHT/2));
		ship.setAlive(true);
		HUDManager hudManager = new HUDManager(0, ship.health(), 10000);
		SoundManager soundManager = context.getSoundManager();
		AnimationManager animationManager = context.getAnimationManager();

		powerups = new ArrayList<PowerUp>();
		ShieldPowerUp shield = new ShieldPowerUp(component, g2d, 5000);
		ProjectilePowerUp twoShot = new ProjectilePowerUp(component, g2d, Ship.NumProjectiles.TWO);
		ProjectilePowerUp threeShot = new ProjectilePowerUp(component, g2d, Ship.NumProjectiles.THREE);
		ProjectilePowerUp fourShot = new ProjectilePowerUp(component, g2d, Ship.NumProjectiles.FOUR);
		twoShot.load("/resources/Two.png");
		threeShot.load("/resources/Three.png");
		fourShot.load("/resources/Four.png");

		shield.load("/resources/shield.png");
		HealthPowerUp health = new HealthPowerUp(component, g2d, 500);
		health.load("/resources/health.png");
		powerUpManager = new PowerUpSpawnManager(powerups);
		powerUpManager.addPowerUp(shield);
		powerUpManager.addPowerUp(health);
		powerUpManager.addPowerUp(twoShot);
		powerUpManager.addPowerUp(threeShot);
		powerUpManager.addPowerUp(fourShot);

		projectiles = new ArrayList<Projectile>();
		proj = new Projectile(component, g2d);//, currentProj;
		proj.load("/resources/projectile.png");
		proj.setAlive(true);
		/*for(int n=0; n < PROJECTILES; ++n)
		{
			currentProj = proj.clone();
			currentProj.setAlive(false);
			projectiles.add(currentProj);
		}*/

		asteroids = new ArrayList<Asteroid>();
		Asteroid first = new Asteroid(component, g2d, 1000);
		first.setAlive(true);
		first.load("/resources/asteroids4.png");
		first.addObserver(hudManager);
		first.addObserver(soundManager);
		first.addObserver(animationManager);
		first.addObserver(powerUpManager);
		first.setNumSplits(2);
		spawnManager = new AsteroidSpawnManager(asteroids, first);
		first.addObserver(spawnManager);

		spawnManager.handle(new WaveSpawnEvent(numAsteroids, ship, GameSettings.SCREENWIDTH, GameSettings.SCREENHEIGHT));

		AnimatedSprite smokeExplosion = new AnimatedSprite(component, g2d), explosion = new AnimatedSprite(component, g2d);
		explosion.load("/resources/explosions_sprite_sheet_new.png", 8, 6, 150, 150);
		smokeExplosion.load("/resources/contra_explosion.png", 13, 1, 57, 52);
		smokeExplosion.setFrameDelay(2);
		smokeExplosion.setAlive(true);
		explosion.setFrameDelay(2);
		explosion.setAlive(true);
		explosionGen = new AnimatedSpriteGenerator(explosion);
		smokeExplosionGen = new AnimatedSpriteGenerator(smokeExplosion);


		animationManager.add(AnimationType.EXPLOSION_BIG, explosionGen);
		animationManager.add(AnimationType.EXPLOSION_SMALL, smokeExplosionGen);

		ship.addObserver(hudManager);

		backgroundMusic =  new SoundSample("/resources/background_music.wav");
		explosionSample = new SoundSample("/resources/explosion3.wav");
		shotSample = new SoundSample("/resources/laser_shot.wav");

		soundManager.add(SoundType.EXPLOSION, explosionSample, 15);
		soundManager.add(SoundType.SHOT, shotSample, 15);

		context.setHUDManager(hudManager);
		ship.addObserver(soundManager);
		ship.addObserver(animationManager);
		context.getSoundManager().playBackgroundSound(backgroundMusic);
		ship.setChanged();
		ship.notifyObservers();

		keyDown = false;
		thrust =  false;
		keyLeft = false; 
		keyRight = false; 
		keyFire = false;
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {

		if(System.currentTimeMillis() > startTime + 1000)
		{
			startTime =  System.currentTimeMillis();
		}

		g2d.drawImage(background.getImage(), 0, 0, GameSettings.SCREENWIDTH - 1, GameSettings.SCREENHEIGHT - 1, component);

		drawAsteroids();
		drawShip();
		drawProjectiles();
		drawPowerUps();
		drawAnimations();

		context.getHUDManager().draw(g2d);
	}

	private void drawProjectiles()
	{
		for(int n=0; n < projectiles.size(); ++n)
		{
			Sprite currentProj = projectiles.get(n);

			if(!currentProj.isAlive())
				continue;

			currentProj.draw();
		}
	}

	private void drawAsteroids() {
		for(int n=0; n < asteroids.size(); ++n) 
		{
			Sprite currentAst = asteroids.get(n);

			if(!currentAst.isAlive())
				continue;

			currentAst.draw();

		}
	}

	private void drawPowerUps()
	{
		for(int n=0; n < powerups.size(); ++n) 
		{
			Sprite currentPower = powerups.get(n);

			if(!currentPower.isAlive())
				continue;

			currentPower.draw();

		}
	}

	public void drawShip() {
		ship.draw();
	}

	public void drawAnimations() {		
		context.getAnimationManager().draw();
	}

	@Override
	public void update() {
		updateSprites();

		checkCollisions();

		if(!ship.isAlive())
			context.goNext(Screens.GAMEOVER, GameManager.RESTART);

		Asteroid ast;
		for(int n = 0; n < asteroids.size(); ++n)
		{
			ast = asteroids.get(n);
			if(!ast.isAlive())
			{
				asteroids.remove(n);
				ast = null;
			}
		}

		Projectile currentProj;
		for(int n = 0; n < projectiles.size(); ++n)
		{
			currentProj = projectiles.get(n);
			if(!currentProj.isAlive())
			{
				currentProj = null;
				projectiles.remove(n);
			}

		}

		context.getSoundManager().purgeSounds();

		if(asteroids.size() <= 1)
		{
			++numAsteroids;
			spawnManager.handle(new WaveSpawnEvent(numAsteroids, ship, GameSettings.SCREENWIDTH, GameSettings.SCREENHEIGHT));
			context.getHUDManager().incWaveNumber(1);
		}

	}

	private void checkCollisions() {

		for(int m=0; m < asteroids.size(); ++m)
		{
			Asteroid currentAst = asteroids.get(m);

			if(!currentAst.isAlive())
				continue;

			for(int n = 0; n < projectiles.size(); ++n)
			{
				Projectile currentProj = projectiles.get(n);

				if(currentProj == null || !currentProj.isAlive())
					continue;

				if(currentAst.collidesWith(currentProj))
				{
					currentAst.accept(currentProj);
					currentProj.accept(currentAst);
					currentProj.setState(SPRITE_COLLIDED);
					currentAst.setState(SPRITE_COLLIDED);
				}
			}
		}

		for(int m=0; m < asteroids.size(); ++m)
		{
			Asteroid currentAst = asteroids.get(m);

			if(!currentAst.isAlive())
				continue;

			if(ship.collidesWith(currentAst))
			{
				ship.accept(currentAst);
				currentAst.accept(ship);

				currentAst.setState(SPRITE_COLLIDED);
				ship.setState(SPRITE_COLLIDED);

			}
		}

		for(int m=0; m < powerups.size(); ++m)
		{
			PowerUp currentPow = powerups.get(m);

			if(!currentPow.isAlive())
				continue;

			if(ship.collidesWith(currentPow))
			{
				ship.accept(currentPow);
				currentPow.accept(ship);

				currentPow.setState(SPRITE_COLLIDED);
				ship.setState(SPRITE_COLLIDED);
			}
		}

		for(int m=0; m < powerups.size(); ++m)
		{
			PowerUp currentPow = powerups.get(m);

			if(!currentPow.isAlive())
				continue;
			for(int n = 0; n < projectiles.size(); ++n)
			{
				Projectile currentProj = projectiles.get(n);

				if(currentProj.collidesWith(currentPow))
				{
					currentProj.accept(currentPow);
					currentPow.accept(currentProj);

					currentPow.setState(SPRITE_COLLIDED);
					ship.setState(SPRITE_COLLIDED);
				}
			}
		}
	}

	private void updateSprites() 
	{
		checkInput();
		updateShip();
		updateProjectiles();
		updateAsteroids();
	}

	public void checkInput() {

		if(keyLeft) {
			ship.incFaceAngle(-5);
			if(ship.getFaceAngle() < 0) ship.setFaceAngle(355);
		}
		else
			if(keyRight) {
				ship.incFaceAngle(5);
				if(ship.getFaceAngle() > 360) ship.setFaceAngle(5);
			}

		if(thrust)
		{
			accelerate(1);
		}
		else
			if(keyDown)
				accelerate(-1);
	}

	public void accelerate(int dir) {
		ship.setMoveAngle(ship.getFaceAngle() - 90);

		double velX = ship.getVelocity().X();
		double velY = ship.getVelocity().Y();

		if(dir > 0)
		{
			velX += Angle.calcAngleMovementX(ship.getMoveAngle()) * ACCELERATION;
			velY += Angle.calcAngleMovementY(ship.getMoveAngle()) * ACCELERATION;
		}
		else
		{
			velX -= Angle.calcAngleMovementX(ship.getMoveAngle()) * ACCELERATION;
			velY -= Angle.calcAngleMovementY(ship.getMoveAngle()) * ACCELERATION;
		}

		ship.setVelocity(new Point2D(velX, velY));
	}

	public void updateShip() {
		ship.updatePosition();
		double newX = ship.getPosition().X();
		double newY = ship.getPosition().Y(); 

		if(newX < -10)
			newX = GameSettings.SCREENWIDTH + 10;
		else 
			if(newX > GameSettings.SCREENWIDTH + 10)
				newX = -10;

		if(newY < -10)
			newY = GameSettings.SCREENHEIGHT +10;
		else
			if(newY > GameSettings.SCREENHEIGHT + 10)
				newY = -10;

		ship.setPosition(new Point2D(newX, newY));
		ship.setState(SPRITE_NORMAL);
	}

	public void updateProjectiles() {
		double x, y;
		for(int n=0; n < projectiles.size(); ++n)
		{
			Sprite currentProj = projectiles.get(n);

			if(!currentProj.isAlive())
				continue;

			currentProj.updatePosition();

			x = currentProj.getPosition().X();

			if(x < 0 || x > GameSettings.SCREENWIDTH)
				currentProj.setAlive(false);

			currentProj.updatePosition();

			y = currentProj.getPosition().Y();

			if(y < 0 || y > GameSettings.SCREENHEIGHT)
				currentProj.setAlive(false);

			currentProj.setState(SPRITE_NORMAL);
		}
	}

	public void updateAsteroids() {

		for(int n=0; n < asteroids.size(); ++n)
		{
			Sprite currentAst = asteroids.get(n);

			if(!currentAst.isAlive())
				continue;

			currentAst.updatePosition();
			currentAst.updateRotation();

			int width = currentAst.width();
			int height = currentAst.height();
			double x = currentAst.getPosition().X();
			double y = currentAst.getPosition().Y();

			if(x < -width)
				x = GameSettings.SCREENWIDTH + width;
			else
				if(x > GameSettings.SCREENWIDTH + width)
					x = -width;

			if(y < -height)
				y = GameSettings.SCREENHEIGHT + height;
			else
				if(y > GameSettings.SCREENHEIGHT + height)
					y = -height;

			currentAst.setPosition(new Point2D(x, y));
			currentAst.setState(SPRITE_NORMAL);
		}
	}

	public void fireProjectile() {
		/*++currentProjectile;

		if(currentProjectile >= projectiles.size()) currentProjectile = 0;*/

		//Projectile projectile = projectiles.get(currentProjectile);
		ship.fireProjectile(proj, projectiles);
		context.getSoundManager().play(SoundType.SHOT);
	}


	@Override
	public void keyPressed(KeyEvent e) {

		if(context.isMouseEnabled())
			return; 

		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
		{
			keyLeft = true;
			break;
		}
		case KeyEvent.VK_RIGHT:
		{
			keyRight = true;
			break;
		}
		case KeyEvent.VK_UP:
		{
			thrust =  true;
			break;
		}
		case KeyEvent.VK_DOWN: 
		{
			keyDown =  true;
			break;
		}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			context.goNext(Screens.PAUSED, GameManager.RESUME);
			return;
		}

		if(context.isMouseEnabled())
			return; 

		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
		{
			keyLeft = false;
			break;
		}
		case KeyEvent.VK_RIGHT:
		{
			keyRight = false;
			break;
		}
		case KeyEvent.VK_UP:
		{
			thrust =  false;
			break;
		}
		case KeyEvent.VK_CONTROL:
		{
			keyFire = false;
			fireProjectile();
			break;
		}
		case KeyEvent.VK_DOWN: 
		{
			keyDown =  false;
			break;
		}
		case KeyEvent.VK_ESCAPE: 
		{
			context.goNext(Screens.PAUSED, GameManager.RESUME);
			break;
		}
		default:
			break;
		}

	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEntered(MouseEvent e) {


	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent e) {

		if(!context.isMouseEnabled())
			return; 

		switch(e.getButton()){
		case MouseEvent.BUTTON1: 
		{
			fireProjectile();
			break;
		}
		case MouseEvent.BUTTON3: 
		{
			thrust = true;
			break;
		}
		default: 
			break;
		}

	}


	@Override
	public void mouseReleased(MouseEvent e) {

		if(!context.isMouseEnabled())
			return; 

		switch(e.getButton()){
		case MouseEvent.BUTTON3: 
		{
			thrust = false;
			break;
		}
		default: 
			break;
		}

	}

	@Override
	public void exit() {
		context.getSoundManager().stopBackgroundSound();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if(!context.isMouseEnabled())
			return; 

		int mouseX = e.getX(), mouseY = e.getY();

		double angle = Math.toDegrees(Math.atan2(mouseX - ship.getCenterX(), ship.getCenterY() - mouseY));
		ship.setFaceAngle(angle);

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		if(!context.isMouseEnabled())
			return; 

		int mouseX = e.getX(), mouseY = e.getY();

		double angle = Math.toDegrees(Math.atan2(mouseX - ship.getCenterX(), ship.getCenterY() - mouseY));
		ship.setFaceAngle(angle);

	}

	@Override
	public void restart() {
		exit();
		numAsteroids = ASTEROIDS;
		init();
	}

}
