import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Point;


import javax.swing.JPanel;

public class Player extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public Point pos; //x and y coords in terms of grid squares

	public Player() {
        pos = new Point(0, 0);
        resetPlayer();
        
    }
	public void resetPlayer() {
		pos.x = 5;
		pos.y = 5;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
            pos.translate(0, -1);
        }
        if (key == KeyEvent.VK_RIGHT) {
            pos.translate(1, 0);
        }
        if (key == KeyEvent.VK_DOWN) {
            pos.translate(0, 1);
        }
        if (key == KeyEvent.VK_LEFT) {
            pos.translate(-1, 0);
        }   
	}
	
	public void checkLocation() {
		if (pos.x < 0) {
			pos.x = 0;
		}
		if (pos.y < 0) {
			pos.y = 0;
		}
		if (pos.x >= Grid.height) {
			pos.x = Grid.height - 1;
		}
		if (pos.y >= Grid.width) {
			pos.y = Grid.width - 1;
		}
		if (Grid.board[pos.x][pos.y] == 1) {
			resetPlayer();
		}
	}
	
	public void draw(Graphics g, ImageObserver observer) {
		Color green = new Color(17, 240, 50);
		g.setColor(green);
		g.fillRect(pos.x * Grid.SQUARE_SIZE, pos.y * Grid.SQUARE_SIZE,
				Grid.SQUARE_SIZE, Grid.SQUARE_SIZE);
	}
}
