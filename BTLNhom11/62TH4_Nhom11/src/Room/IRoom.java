package Room;


import java.sql.SQLException;
import java.util.ArrayList;

public interface IRoom {
 public ArrayList<Room> get_ListRoom();
 public ArrayList<Room> get_ListRoombyRoomType(String roomType);
 public boolean add_Room(String roomID, String roomType, double price, boolean status);
 public boolean update_Room(String roomID, String roomType, double price, boolean status);
 public boolean delete_Room(String roomID);
 
 public double getRoomPriceByID(String roomID) throws SQLException;
}
