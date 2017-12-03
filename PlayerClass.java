package ProjectThings1;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 * Methods that hold player Data like the answers to questions and 
 * it holds their movement and where they are in the board.
 * 
 * @author Alex
 *
 */
public class PlayerClass
{

		/**
		 * Variables
		 */
	    private int dx;
	    private int dy;
	    private int x;
	    private int y;
	    
	    /**
	     * Default  Constructor
	     */
	    public PlayerClass() {
	        
	        initPlayerClass();
	    }
	    
	    private void initPlayerClass() {
	        

	        x = 40;//the initial point x where the start is. Set the number to where the player starts
	        y = 60; //the initial point y where the start is. Set the number to where the player starts     
	    }

	    /**
	     * This method moves the 
	     * character where DX and DY are
	     */
	    public  void move() {
	        x += dx;
	        y += dy;
	    }

	    public  int getX() {
	        return x;
	    }

	    public  int getY() {
	        return y;
	    }

	    /**
	     * This is a method
	     *  that  when certain keys are 
	     *  pressed it 
	     *  will move the person certain ways. 
	     * @param e
	     */
	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();
	        /**
	         * The if statements below
	         * are saying if this key is pressed
	         * then it will  move the player in the direxrion
	         */

	        if (key == KeyEvent.VK_LEFT) {
	            dx = -1;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            dx = 1;
	        }

	        if (key == KeyEvent.VK_UP) {
	            dy = -1;
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            dy = 1;
	        }
	    }
	    /**
	     * This is the keyReleased method that 
	     * will stop the player movement 
	     * when the left,right,up, and down  buttons are
	     * released.
	     * @param e
	     */
	    public void keyReleased(KeyEvent e) {
	        
	        int key = e.getKeyCode();
	        /**
	         * The if statements below
	         * are saying if this key is released
	         * then it will  stop moving
	         */

	        if (key == KeyEvent.VK_LEFT) {
	            dx = 0;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            dx = 0;
	        }

	        if (key == KeyEvent.VK_UP) {
	            dy = 0;
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            dy = 0;
	        }
	    }


} // end PlayerClass
/**
 * This class is used to get the key movements. 
 * @author Alex
 *
 */
class TAdapter extends KeyAdapter {

    @Override
    /**
     * refers to the keyReleased method above.
     */
    public void keyReleased(KeyEvent e) {
        keyReleased(e);
    }
    /**
     * Refers to the method keyPressed method above
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed(e);
    }
}
