package Staff;

import java.util.ArrayList;


public interface IStaff {
	public ArrayList<Staff> getListStaff();
	public Staff get_StaffID(String staffId);
	public boolean insert_Staff(String staffId, String name, double salary, String birthDate, boolean gender);
	public boolean update_Staff(String staffId, String name, double salary, String birthDate, boolean gender);
	public boolean delete_Staff(String id);
}
