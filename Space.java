package mazeProject;

public class Space
{
	 private boolean typeRoom; // type of room (false==wall / true==room)
	 private int roomNum;      // room number in the maze
  
	 public Space()
	 {
		 this.typeRoom = false; // space initially a wall
		 this.roomNum = -1;     // -1 signifies a wall

	 }
	 
	 public Space(boolean typeRoom, int roomNum)
	 {
		 this.typeRoom = typeRoom;
		 
		 // if a room, give room number, else wall is -1
		 if(typeRoom == true)
			 this.roomNum = roomNum;
		 else
			 this.roomNum = -1;

	 }
	public boolean isTypeRoom()
	{
		return typeRoom;
	}

	public void setTypeRoom(boolean typeRoom)
	{
		this.typeRoom = typeRoom;
	}

	public int getRoomNum()
	{
		return roomNum;
	}

	public void setRoomNum(int roomNum)
	{
		this.roomNum = roomNum;
	}
}
