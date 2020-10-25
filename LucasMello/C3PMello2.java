package LucasMello;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;




//C3PMello2 - a robot by (your name here)
 
public class C3PMello2 extends Robot
{
	//Variaveis Globais
	int count = 0; // Keeps track of how long we've
	// been searching for our target
	double gunTurnAmt; // How much to turn our gun when searching
	String trackName; // Name of the robot we're currently tracking
	int contDeath = 0; // Contador de mortes
	int inWall=0; //variavel que vai dizer se o Robo esta na parede
	boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move
	double heading; //-> Sentido da arma

	public void run() {

		//CORES DO ROBO
		setBodyColor(new Color(255, 200, 000)); //Corpo
		setGunColor(new Color(200, 200, 200));//Arma
		setRadarColor(new Color(255, 200, 000));//Radar
		setScanColor(Color.white);//Scan
		setBulletColor(Color.yellow);//Tiro
		


		//CODIGO PARA O ROBO FICAR ALINHADO COM A PAREDE
		 // WIDTH -> Largura
		 //HEIGHT -> Altura
		 //getHEading() -> Retorna o lugar aonde o copo do robo esta apontando 
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		peek = false;
		turnLeft(getHeading() % 90); 
		ahead(moveAmount);
		peek = true;
		turnGunRight(90);
		turnRight(90);

		while(inWall == 0) {
			back(300);
		}
		
		while(inWall == 1) {
			heading = getHeading();
			while(contDeath<6){
				//Esse if movimenta o rabo no caso dele estar com o canhão voltado para Leste ou Oeste 
				if(heading == 0 || heading ==180){
					ahead(getBattleFieldHeight() - 37);
					back(getBattleFieldHeight() -37);	
				}
				
				//Esse if movimenta o rabo no caso dele estar com o canhão voltado para Norte ou Sul 
				if(heading == 90 || heading == 270) {
					ahead(getBattleFieldWidth() - 37);
					back(getBattleFieldWidth() -37);		
				}	
			
			}
		
		}
		
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		count = 0;
		// If our target is too far away, turn and move toward it.
		if (e.getDistance() > 150) {
			
			fire(2);
		}

		// Our target is close.
		
		fire(3);

		// Our target is too close!  Back up.
		if (e.getDistance() < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				fire(2);
			} 
		}
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {		
		
		if(inWall==0){
			inWall++;
			out.println("Heading:" + getHeading());
		}
	}
	
	public void onRobotDeath(RobotDeathEvent e){
		contDeath++;	//--> Contador de mortes no round	
	}
}
