package LucasMello;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * C3PMello - a robot by (your name here)
 */
public class C3PMello extends Robot
{
boolean linedup; //-> Variavel que vai dizer quando o Tanque esta alinhado (Reto)
double moveAmount;	
int contDeath = 0; // Contador de mortes
   
    public void run() {

        //O Codigo abaixo garante que o tanque esteja alinhado com a parede, e vá para se encostar na paredee aponta a arma para outro lado
        moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
        linedup = false;
        turnLeft(getHeading() % 90);
        ahead(moveAmount);
        linedup = true;
        turnGunRight(90);
        turnRight(90);
        //fim do alinhamento com a parede

        //Height = ALTURA
        //Width = LARGURA
        double width = getBattleFieldHeight();  //Criando Variaveis e definindo os paramentros de tamanho do campo
        double height = getBattleFieldHeight();

		// Laço de repetição 
        while(true) {
				
	            	if (height >= width){
						
		                ahead(width-100);
		                back(width-100);
						
		            }
		            else{
						
		                ahead(height-100);
		                back(height-100);
						
		            }
    }
}
   
    public void onScannedRobot(ScannedRobotEvent e) {
	
		//Enquanto o numero de mortes for baixo ele irá Scanear e atirar em qualquer um na sua frente 
       	if(contDeath<6){
        fire(1);
		}
		
		//Quando o numero de robos mortos aumentar ele irá focar em apenas um alvo e atirar ate o alvo morrer
		else {
			double absoluteBearing = getHeading() + e.getBearing();
			double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
			if (Math.abs(bearingFromGun) <= 3) {
				turnGunRight(bearingFromGun);
				if (getGunHeat() == 0) {
					fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
				}
			}
			else {
				turnGunRight(bearingFromGun);
			}
			if (bearingFromGun == 0) {
				scan();
			}
		}
    }
	
	public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(100);
		}
	}
    

	
	public void onRobotDeath(RobotDeathEvent e){
		contDeath++;	//--> Contador de mortes no round	
	}
	

}
