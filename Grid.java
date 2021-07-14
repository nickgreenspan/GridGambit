import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grid extends JPanel implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int height = 20;
	public static int width = 20;
	
	private final int DELAY = 25;
	static int SQUARE_SIZE = 25;
	static final int NUM_LEVELS = 3;
	static final int LEVEL_INFO_LEN = 4;
	private Timer timer;
	public static int board[][] = new int[height][width];
	private Player player;
	private Missile missile;
	public static int level = 0;
	public static int levelInfo[][] = new int [NUM_LEVELS + 1][LEVEL_INFO_LEN];
	
	public Grid() {
		setPreferredSize(new Dimension(SQUARE_SIZE * height, SQUARE_SIZE * width));
		initLevelinfo();
		resetBoard();
		player = new Player();
		setBackground(new Color(250, 250, 250));
		timer = new Timer(DELAY, this);
        timer.start();
        
	}
	
	public void initLevelinfo() {
		levelInfo[1][0] = 2; //key height location
		levelInfo[1][1] = 18;//key width location
		levelInfo[1][2] = 5; //frequency of arrows
		levelInfo[1][3] = -1;//not used for now
		
		levelInfo[2][0] = 17; //key height location
		levelInfo[2][1] = 18;//key width location
		levelInfo[2][2] = 10; //frequency of arrows
		levelInfo[2][3] = -1;//not used for now
		
		levelInfo[3][0] = 18; //key height location
		levelInfo[3][1] = 2;//key width location
		levelInfo[3][2] = 15; //frequency of arrows
		levelInfo[3][3] = -1;//not used for now
	}
	
	
	public void initLevel() {
		if (level <= NUM_LEVELS) {
			player.resetPlayer();
			resetBoard();	
		}		
	}
	
	public void resetBoard() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
					board[i][j] = 1;
				}
				else {
					board[i][j] = 0;
				}
			}
		}
		board[levelInfo[level][0]][levelInfo[level][1]] = 2; //key location
	}
	
	public void checkLevelEnd() {
		if (player.pos.x == levelInfo[Grid.level][0] && 
				player.pos.y == levelInfo[Grid.level][1]) {
			level += 1;
			initLevel();
		}
	}
	
	@Override
	/* Gets called every timer tick */
	public void actionPerformed(ActionEvent e) {
		/* Update player's location on the grid */
		if (level > 0 && level <= NUM_LEVELS) {
			player.checkLocation();
			missile.fire(player.pos.x, player.pos.y);
			checkLevelEnd();	
		}
		repaint(); //calls paintComponent
	}
	
	@Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	if (level == 0) {
    		int key = e.getKeyCode();
    		if (key == KeyEvent.VK_ENTER) {
    			level += 1;
    			initLevel();	
    		}
    	}
    	else {
    		player.keyPressed(e);
    	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (level == 0) {
			Gui.drawStartScreen(g);
		}
        else if (level == 4) {
        	Gui.drawEndScreen(g);
        }
		else {
			drawBackground(g);
	        player.draw(g, this);
		}
	}
		
	/* Board starts at 0,0 on screen */
	private void drawBackground(Graphics g){		
		Color blue = new Color(52, 64, 235);
		Color red = new Color(235, 39, 21);
		Color yellow = new Color(245, 230, 29);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0) { //Empty square
					g.setColor(blue);
					g.drawRect(i * SQUARE_SIZE, j * SQUARE_SIZE, 
							SQUARE_SIZE, SQUARE_SIZE);
				}
				else if (board[i][j] == 1) { //Danger square
					g.setColor(red);
					g.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, 
							SQUARE_SIZE, SQUARE_SIZE);
				}
				else if (board[i][j] == 2) { //Key square
					g.setColor(yellow);
					g.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, 
							SQUARE_SIZE, SQUARE_SIZE);	
				}
			}
		}
	}
	
}
