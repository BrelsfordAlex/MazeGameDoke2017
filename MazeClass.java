package ProjectThings1;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



/**
 * In this class, we'll use the 2-dimensional array 
 * to implement the maze itself
 */
public class MazeClass extends JFrame
{
	// we defined an instance variable named maze[row][column]
	// the value 0 means: not visited node
	// the value 1 means: wall (borders)
	// all borders will be filled with 1 to avoid ArrayIndexOutOfBounds
	// the value 2 means: visited node
	// the value 3 means: the target node
	// we'll define the start position at (1,1) and the target position at (7,6)
		
	private int [][] maze = 
	  { {1,1,1,1,1,1,1,1,1},
	    {1,0,1,0,1,0,1,0,1},
        {1,0,1,0,0,0,1,0,1},
        {1,0,0,0,1,1,1,0,1},
        {1,0,1,0,0,0,0,0,1},
	    {1,0,1,0,1,1,1,0,1},
	    {1,0,1,0,1,1,1,3,1},
	    {1,1,1,1,1,1,1,1,1}};
	    
	// constructor
	public MazeClass()
    {
		setTitle("The Maze");
	    setSize(800, 640);
	    setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	}

	// This method to draw the maze
	// @Override
	/**
	 * Paint method will paint the 
	 * given maze. 
	 */
	public void paint(Graphics g) 
	{
		super.paint(g);
	    g.translate(80, 80);
	        
	    for (int row = 0; row < maze.length; row++) {
	    	for (int col = 0; col < maze[0].length; col++) 
			{
	    		Color color;
	            switch (maze[row][col])
				{
	            case 1 : 
	            	color = Color.BLACK; // means wall (borders)
					break;
	            case 3 : 
					color = Color.RED; // means target node
					break;
	            default : 
					color = Color.WHITE;
	            }
	            g.setColor(color);
	            g.fillRect(30 * col, 30 * row, 30, 30);
	            g.setColor(Color.BLACK);
	            g.drawRect(30 * col, 30 * row, 30, 30);
	        }
	   }
     }	
    @SuppressWarnings("unused")
	private void initMazeClass() 
    {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);


    }
    /**
     * 
     * @param g
     */
	public void paintComponent(Graphics g) {
        super.paintComponents(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }
	/**
	 * 
	 * @param g
	 */
    private void doDrawing(Graphics g) 
    {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage( null, getX(), getY(), this);        
    }
    /**
     * 
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        PlayerClass pc = new PlayerClass();
        pc.move();
        repaint();  
    }
    /**
     * This is used to  use the methods key pressed and key released to move the player.
     * @author Alex
     *
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyPressed(e);
        }
    }
 
  
}	    
}

