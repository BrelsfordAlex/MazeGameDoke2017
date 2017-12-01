package mazeProject;
 
import java.awt.Color;
import java.awt.Font; 
import java.awt.Dimension;
import java.awt.EventQueue; 
import javax.swing.*; 
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;

public class MazeGame
{ 
	private JFrame frame;   // outer frame of gui
	private JTable table;   // table to store maze
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					// initialize a maze
					MazeGame window = new MazeGame();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MazeGame()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		// create new maze on game load 
		//(using size 21 now but should allow user to choose difficulty)
		RandomMazeClass newMaze = new RandomMazeClass(21, 21);
		Space[][] map = newMaze.getMap(); // list of rooms
 
		// main application frame
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBackground(new Color(90, 80, 150));
		frame.setTitle("Escape the Maze!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new MigLayout("wrap 1", "[grow,fill]", "[grow,fill]"));

		// main panel to display game contents
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(90, 80, 150));
		panel.setLayout(new MigLayout("wrap 1", "[grow,fill]",
				"[grow,fill][grow,fill]"));

		// panel to display maze
		JPanel mazeMap = new JPanel();
		mazeMap.setBackground(new Color(90, 80, 150));
		mazeMap.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));

		// create table to display maze 
		//(should be mazeSize+2 to include borders, not static 23)
		table = new JTable(23, 23)
		{
			/**
			 * 
			 */
			// add default serialID to remove warning
			private static final long serialVersionUID = 1L;

			// override the changeSelection method to allow user to only
			// select valid rooms within the maze bounds that are 1 away from
			// the currently selected cell
			@Override
			public void changeSelection(int row, int col, boolean toggle, boolean expand)
			{
				// get the currently selected cell position values
				int currRow = table.getSelectedRow();
				int currCol = table.getSelectedColumn();

				// if new space selection is a valid, contiguous space in maze
				// then update to the new selected cell position
				if((row >= 1 && col >= 1) && (row < table.getRowCount() - 1 && col < table.getColumnCount() - 1))
					if ((map[col - 1][row - 1].getRoomNum() != -1)
						&& ((Math.abs(currRow - row) == 1 || Math.abs(currRow - row) == 0)
						&&  (Math.abs(currCol - col) == 1 || Math.abs(currCol - col) == 0)))
							super.changeSelection(row, col, toggle, expand);

			}  // end changeSelection

			// make all cells non-editable
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}

		}; // end table cell selection configuration

		// set table selection to single cell selection
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// start at cell 1,1 in table (start position)
		table.setRowSelectionInterval(1, 1);
		table.setColumnSelectionInterval(1, 1);
		table.setUpdateSelectionOnSort(false);
		table.setRowSelectionAllowed(false);
		table.setIgnoreRepaint(true);

		// set table cell design, height, margins 
		table.setForeground(Color.WHITE);
		table.setRowHeight(30);						    
		table.setIntercellSpacing(new Dimension(0, 0)); 
	 
		// set the column width size for each column in table
		for (int i = 0; i < 23; i++)
		{
			table.getColumnModel().getColumn(i).setPreferredWidth(30);
			table.getColumnModel().getColumn(i)
					.setCellRenderer(new CustomRenderer(newMaze, map));
		}

		// add maze map table to maze panel
		mazeMap.add(table, "cell 0 0,alignx center,aligny center");

		// player info will go here add ....
		JLabel lblPlayerName = new JLabel("PLAYER!");

		lblPlayerName.setFont(new Font("Calibri", Font.BOLD, 28));
		JLabel lblScore = new JLabel("Score is 20");

		// button to restart game
		JButton btnRestart = new JButton("Restart");
		btnRestart.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		btnRestart.setFont(new Font("Calibri", Font.PLAIN, 24));

		// information panel to display player info and buttons
		JPanel displayText = new JPanel();
		displayText.setLayout(
				new MigLayout("wrap 2", "[grow,fill]", "[grow,fill]"));

		// add info buttons/labels to display panel
		displayText.add(lblPlayerName);
		displayText.add(lblScore);
		displayText.add(btnRestart);

		// add maze and info panels to main panel
		panel.add(mazeMap, "alignx center");
		panel.add(displayText,
				"cell 0 1,growx,sizegroupx ,sizegroupy ,aligny top");

		// add panel to frame and size to screen
		frame.getContentPane().add(panel, "cell 0 0,growx,aligny top");
		frame.pack();
		frame.setLocationRelativeTo(null);

	} // end initialize

} // end MazeGame
