// added random maze list to generate drawing of maze

package maze;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mazeProject.Space;

public class MazeClass extends JPanel 
{

	public static final char ROOM_CHAR = ' ';
	public static final String WALL_CHAR = "\u2588";

	// instance variables
	private Space map[][];
	private boolean isWall;
	private boolean isRoom;
	private int width;
	private int height;
	private int sizeX;
	private int sizeY;
	private int numRooms;

	private JFrame frame;
 

	public MazeClass(int width, int height)
	{

		this.isWall = false;
    	this.isRoom = !isWall;
    	
        this.width = width;
        this.height = height;
        this.sizeX = width*40 + 240;
        this.sizeY= height*40 + 280;
        
        this.numRooms = -1;
       
        this.map = new Space[width][height];

        for(int i = 0; i < height; i++)
        	for(int j = 0; j < width; j++)
        		map[i][j] = new Space();
        		 
        LinkedList<int[]> frontiers = new LinkedList<>();
        
        Random rand = new Random();
        
        int x = 0; 
        int y = 0; 
         
        frontiers.add(new int[]{x,y,x,y});
         
        while ( !frontiers.isEmpty() )
        {
        	int randNum = rand.nextInt( frontiers.size() );
            int[] f = frontiers.remove( randNum );
            
            x = f[2];
            y = f[3];
             
            if ( map[x][y].isTypeRoom() == isWall )
            { 
                map[x][y].setTypeRoom(isRoom);
                map[f[0]][f[1]].setTypeRoom(map[x][y].isTypeRoom());
                 
                map[f[0]][f[1]].setRoomNum(numRooms++);
                map[x][y].setRoomNum(numRooms++);
                
                if ( x >= 2 && map[x-2][y].isTypeRoom() == isWall )
                	frontiers.add( new int[]{x-1,y,x-2,y} );
                	
                if ( y >= 2 && map[x][y-2].isTypeRoom() == isWall )
                	frontiers.add( new int[]{x,y-1,x,y-2} );
                
                if ( x < width-2 && map[x+2][y].isTypeRoom() == isWall )
                	frontiers.add( new int[]{x+1,y,x+2,y} );
                
                if ( y < height-2 && map[x][y+2].isTypeRoom() == isWall )
                	frontiers.add( new int[]{x,y+1,x,y+2} );
                 
            } // end if
            
        } // end while 
	} // end non-default constructor
	
    private static final int DIM_WIDTH = 710;
    private static final int DIM_HEIGHT = 740;
    private static final int SQ_SIZE = 80;

    boolean black = true;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.translate(10, 10);
        
		for (int row = 0; row < map.length+2; row++)
		{
			for (int col = 0; col < map.length+2; col++)
			{
				Color color = Color.BLUE;
				
				if ((col == 0 || col == map.length+ 1) 
						|| (row == 0 || row == map.length+1))
					color = Color.BLACK;
				 
				else if((row > 0 && col > 0) && (row <= map.length && col <= map.length))
				{
					switch (map[col-1][row-1].getRoomNum())
					{
						case -1:
							color = Color.BLACK; // means wall (borders)
							break;
						case 0:
							color = Color.RED; // means target node
							break;
						default:
							color = Color.WHITE;
					}
				}
				
				g.setColor(color);
				g.fillRect(30 * col, 30 * row, 30, 30);
				g.setColor(Color.BLACK);
				g.drawRect(30 * col, 30 * row, 30, 30);
			}
		}
    }

    public static void createAndShowGui() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JButton btn1 = new JButton("hi");
        panel.add(new MazeClass(21,21));
        panel2.add(btn1);
        frame.setLayout(new BorderLayout());
       
        
        frame.add(panel);
        frame.add(panel2,BorderLayout.SOUTH);
       // frame.add(btn1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);

    }

    public Dimension getPreferredSize() {
        return new Dimension(DIM_WIDTH, DIM_HEIGHT);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}

















