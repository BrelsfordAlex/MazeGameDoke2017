package ProjectThings1;
import javax.swing.SwingUtilities;

public class MazeDriver
{
	// instance variables or values needed for app
	
	// main function to start the app
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
		    public void run()
			{
				MazeClass mc = new MazeClass();
		        mc.setVisible(true);
		    }
		 });	
	} // end main
	
	// public methods
	
} // end MazeDriver
