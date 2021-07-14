import javax.swing.*;
import java.awt.*;
/* Main game class */
public class Gui {
	private static int fontSize = 20;

	public static void initGame() {
		JFrame frame = new JFrame("Game Window");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Grid grid = new Grid();
	    frame.add(grid);
	    frame.addKeyListener(grid);
	    frame.setResizable(false);
	    frame.pack();
	    frame.setLocationRelativeTo(null); //Sets to screen center
	    frame.setVisible(true);
	}
	
	
	public static void drawStartScreen(Graphics g) {
		String startMessage = "Press Enter to Begin Game!";
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
		g.setColor(new Color(0, 0, 0));
		g.drawString(startMessage, Grid.width * Grid.SQUARE_SIZE/4, 
				Grid.height * Grid.SQUARE_SIZE/2);
	}
	
	public static void drawEndScreen(Graphics g) {
		String endMessage = "You won!!";
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
		g.setColor(new Color(0, 0, 0));
		g.drawString(endMessage, Grid.width * Grid.SQUARE_SIZE/3, 
				Grid.height * Grid.SQUARE_SIZE/2);
	}
	
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGame();	
			}
		});
	}
}
