package LucasMello_c3pme;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * C3pmello - a robot by (your name here)
 */
public class C3pmello extends AdvancedRobot
{
	boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move

	/**
	 * run: C3pmello's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setBodyColor(new Color(255, 200, 00));
		setGunColor(new Color(200, 200, 200));
		setRadarColor(new Color(255, 200, 00));
		setScanColor(Color.white);
		setBulletColor(Color.red);
		
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight())- 10;
		peek = false;
		
		turnLeft(getHeading() % 90);
		// Robot main loop
		while(true) {
		
			ahead(getBattleFieldWidth()/2.5);
			turnRight(90);
			ahead(getBattleFieldHeight()/2.5);
			turnRight(90);
			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
			// Calculate exact location of the robot
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			// We check gun heat here, because calling fire()
			// uses a turn, which could cause us to lose track
			// of the other robot.
			if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		} // otherwise just set the gun to turn.
		// Note:  This will have no effect until we call scan()
		else {
			turnGunRight(bearingFromGun);
		}
		// Generates another scan event if we see a robot.
		// We only need to call this if the gun (and therefore radar)
		// are not turning.  Otherwise, scan is called automatically.
		if (bearingFromGun == 0) {
			scan();
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		setTurnRight(90);
		ahead(200);
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		int cont = 0;
		int modcont = cont%2;
		back(20);
		turnRight(180);
		
		if(modcont!= 1)
			back(300);
			turnLeft(90);
		

	}	
}
