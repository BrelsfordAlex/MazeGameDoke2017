package ProjectThings1;

import java.awt.Color;
import java.awt.Graphics;
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
}

