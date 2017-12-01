package mazeProject;
 
import javax.swing.JLabel;
import javax.swing.JTable; 
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class CustomRenderer extends DefaultTableCellRenderer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Space[][] map;  // the list of rooms/walls in the maze
	private RandomMazeClass maze; // the mazeclass
	//private int num;
	
	public CustomRenderer()
	{
		maze = null;
		map = null;
		//num = 0;
	}
	
	public CustomRenderer(RandomMazeClass maze, Space[][] map)
	{
		this.maze = maze;
		this.map = map;
		//num = 255;
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(
	 * javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col)
	{  
		Component rendererComp = super.getTableCellRendererComponent(table, value, 
				isSelected, hasFocus, row, col);

		// Set alignment and design of cells
		rendererComp.setForeground(Color.white);
		setHorizontalAlignment(JLabel.CENTER); 
		setVerticalAlignment(JLabel.CENTER); 
		 
		table.setCellSelectionEnabled(true);
		 
		// if cell is a border wall, disable selection and paint wall color
		if ((col == 0 || col == table.getColumnCount() - 1) 
				|| (row == 0 || row == table.getRowCount() - 1))
		{
			table.setCellSelectionEnabled(false);
    		rendererComp.setBackground(new Color(204, 204, 255));
		}
		// else if the start or end cell in the maze bounds
		else if((row >= 1 && col >= 1) 
			&& (row <  table.getRowCount()-1 && col < table.getColumnCount()-1))
		{ 
			if(map[col-1][row-1].getRoomNum() == 1)
			{
				rendererComp.setBackground(Color.magenta);
				setText("S");
			}
			// end	
        	else if(map[col-1][row-1].getRoomNum() == maze.getNumRooms()-1)
        	{
        		rendererComp.setBackground(Color.magenta);
        		setText("E");
        	}
			// else if an interior wall cell, disable selection
        	else if(map[col-1][row-1].getRoomNum() == -1)
			{
        		table.setCellSelectionEnabled(false);
        		rendererComp.setBackground(new Color(204, 204, 255));
			}
			// else an empty room cell
			else
			{ 
				rendererComp.setForeground(Color.magenta);
				rendererComp.setBackground(new Color(245, 245, 245));
			}
			// if the current cell is selected, paint cell pink
			if(table.isCellSelected(row, col))
        	{ 
				rendererComp.setBackground(Color.pink);
        	}
		} // end if in maze bounds
		
		//if(num > 1)
		//	num = num-2;
		//else
		//	num = 255;
		 
		return rendererComp;

	} // end Component getTableCellRendererComponent

} // end CustomerRenderer
