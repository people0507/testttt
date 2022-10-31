package Room;

public class Room {
  public String roomID, roomType;
  public double price;
  public boolean status;
  public Room(String roomID, String roomType, double price, boolean status)
  {
	  this.roomID = roomID; this.roomType = roomType;
	  this.price = price; this.status = status;
  }
  public Room() {}
public String getRoomID() {
	return roomID;
}
public void setRoomID(String roomID) {
	this.roomID = roomID;
}
public String getRoomType() {
	return roomType;
}
public void setRoomType(String roomType) {
	this.roomType = roomType;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
  
}
