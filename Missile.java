import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class Missile {
	int timeLastFired = 0;
	int currTime;
	
	public void tick(Timer timer, int frequency) {
		currTime = timer.getTime();
	}
	
	public void fire(int player_x, int player_y) {
		
		
	}
	
	
	public void decay() {
		
	}

}
