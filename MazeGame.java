package mazeProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*; 

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 
import java.awt.Insets;
 

public class MazeGame
{
	private JFrame frame;   // outer frame of gui
	private JTable table;   // table to store maze
	private JPanel panel;
	private JPanel mazeMap;
	private JLabel lblTop;
	private RandomMazeClass newMaze;
	private Space[][] map;
	 
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
		generateMaze();
		generateTable();
		initialize();
	}

	

	public void generateMaze()
	{
		newMaze = new RandomMazeClass(21, 21);
		map = newMaze.getMap(); // list of rooms

	} // end generateMaze

	public void generateTable()
	{
		// table = new JTable(23, 23);
		table = new JTable(new MyTableModel(23, 23))
		// create table to display maze
		// (should be mazeSize+2 to include borders, not static 23)
		// table = new JTable(23, 23)
		{

			// setColCount(23);
			// add default serialID to remove warning
			private static final long serialVersionUID = 1L;

			// override the changeSelection method to allow user to only
			// select valid rooms within the maze bounds that are 1 away from
			// the currently selected cell
			@Override
			public void changeSelection(int row, int col, boolean toggle,
					boolean expand)
			{
				
				// get the currently selected cell position values
				int currRow = table.getSelectedRow();
				int currCol = table.getSelectedColumn();
				if ((row >= 1 && col >= 1) 
						&& (row < table.getRowCount() - 1
						&& col < table.getColumnCount() - 1) 
						&& map[col - 1][row - 1].getRoomNum() == newMaze.getNumRooms()-1)
					{ 
						winGame();
						 
					}
				// if new space selection is a valid, contiguous space in maze
				// then update to the new selected cell position
				if ((row >= 1 && col >= 1) && (row < table.getRowCount() - 1
						&& col < table.getColumnCount() - 1))
					if ((map[col - 1][row - 1].getRoomNum() != -1)
							&& ((Math.abs(currRow - row) == 1
									|| Math.abs(currRow - row) == 0)
									&& (Math.abs(currCol - col) == 1
											|| Math.abs(currCol - col) == 0)))
						super.changeSelection(row, col, toggle, expand);

				 
				
			}  // end changeSelection

		}; // end table cell selection configuration
		table.setBackground(new Color(255, 255, 255));
		// table.getSelectionModel().addListSelectionListener(yourListener);
		// set table selection to single cell selection
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// start at cell 1,1 in table (start position)
		table.setRowSelectionInterval(1, 1);
		table.setColumnSelectionInterval(1, 1);
		table.setUpdateSelectionOnSort(false);
		table.setRowSelectionAllowed(false);
		table.setIgnoreRepaint(false);

		// set table cell design, height, margins
		table.setForeground(Color.WHITE);
		table.setRowHeight(30);
		table.setIntercellSpacing(new Dimension(0, 0));
		
		// set the column width size for each column in table
		for (int i = 0; i < 23; i++)
		{
			table.getColumnModel().getColumn(i).setPreferredWidth(30);
			table.getColumnModel().getColumn(i)
					.setCellRenderer(new CustomRenderer(newMaze, map, newMaze.isGameEnd()));

		}

	} // end generateTable

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		Font btnFont = new Font("Segoe UI Historic", Font.PLAIN, 28);

		// create new maze on game load
		// (using size 21 now but should allow user to choose difficulty)
		// generateMaze();

		// main application frame
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBackground(new Color(90, 80, 150));
		frame.setTitle("Escape the Maze!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new MigLayout("wrap 1", "[grow,fill]", "[grow,fill]"));

		// main panel to display game contents
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(90, 80, 150));
		panel.setLayout(new MigLayout("wrap 1", "[grow][grow,fill]",
				"[grow][grow,fill][grow,fill]"));

		// panel to display maze
		mazeMap = new JPanel();
		mazeMap.setBackground(new Color(90, 80, 150));
		mazeMap.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));

		// add maze map table to maze panel
		mazeMap.add(table, "cell 0 0,alignx center,aligny center");

		JButton btnRestart = new JButton("New Maze");
		btnRestart.setVisible(false);
		btnRestart.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				mazeMap.remove(table);
				 
				generateMaze();
				generateTable();
 
				mazeMap.add(table);
				table.setIntercellSpacing(new Dimension(0, 0));
 
				btnRestart.transferFocusBackward();
			}

		}); // end btnStart mouseListener
		
		JButton btnStart = new JButton("Start!");
		
		btnStart.setFont(new Font("Segoe UI Historic", Font.PLAIN, 28));
		btnStart.setMargin(new Insets(20, 20, 20, 20));
		btnStart.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				
				btnStart.setVisible(false);
				btnRestart.setVisible(true);
				table.setVisible(true);
				panel.add(mazeMap, "cell 0 1,alignx center");
				panel.add(btnRestart, "cell 0 2,alignx center");
				for (Component comp : panel.getComponents())
				{
					// remove text focus border and add hover effect
					if (comp instanceof JButton)
					{
						((JButton) comp).setFocusPainted(false);
						createNewButton((JButton) comp);
					}
				} // end for each button in buttons
				panel.remove(btnStart);
				btnRestart.transferFocusBackward();
				//System.out.print(table.getValueAt(1, 1));
				frame.pack();
				frame.setLocationRelativeTo(null);
			}

		}); // end btnStart mouseListener
		 
		// player info will go here add ....
		JLabel lblPlayerName = new JLabel("PLAYER!");

		lblPlayerName.setFont(new Font("Calibri", Font.BOLD, 28));
		JLabel lblScore = new JLabel("Score is 20");
 
		// information panel to display player info and buttons
		JPanel displayText = new JPanel();
		displayText.setLayout(
				new MigLayout("wrap 2", "[grow,fill]", "[grow,fill]"));

		// add info buttons/labels to display panel
		displayText.add(lblPlayerName);
		displayText.add(lblScore);

		lblTop = new JLabel("ESCAPE THE MAZE!");
		lblTop.setFont(new Font("Segoe UI Historic", Font.BOLD, 28));
		lblTop.setForeground(Color.white);
		panel.add(lblTop, "cell 0 0,alignx center");
		 
		// table initially not visible to screen
		table.setVisible(false);
		// add maze and info panels to main panel
		panel.add(btnStart, "cell 0 1,alignx center");
		//btnStart.setMargin(new Insets(20, 20, 20, 20));
		for (Component comp : panel.getComponents())
		{
			// remove text focus border and add hover effect
			if (comp instanceof JButton)
			{
				((JButton) comp).setFocusPainted(false);
				createNewButton((JButton) comp);
			}
		} // end for each button in buttons
		 
		// add panel to frame and size to screen
		frame.getContentPane().add(panel, "cell 0 0,growx,aligny top");
		frame.pack();
		frame.setLocationRelativeTo(null);

	} // end initialize

	public void createNewButton(JButton btn)
	{
		Font btnFont = new Font("Segoe UI Historic", Font.BOLD, 28);
 
		btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(246, 61, 162));
		btn.setFont(btnFont);
		btn.setMargin(new Insets(10,20,10,20));
		btn.setBorderPainted(false);
		String btnText = btn.getText();

		btn.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(java.awt.event.MouseEvent e)
			{
				JButton button = (JButton) e.getComponent();
				button.setBackground(new Color(230, 10, 130));
				
			}

			public void mouseExited(java.awt.event.MouseEvent e)
			{
				JButton button = (JButton) e.getComponent();
				button.setBackground(new Color(246, 61, 162));
			}
		});
 
	} // end createNewButton

	public void winGame()
	{
		lblTop.setText("YOU WON!");
		
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
	}
	
} // end MazeGame
