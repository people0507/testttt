package Service;

import java.util.ArrayList;

public interface IService {
	public boolean add_Service(String serviceID, String name, double price);
	public boolean update_Service(String serviceID, String name, double price);
	public boolean delete_Service(String serviceID);
	public ArrayList<Service> get_ListService();
	public Service get_ServiceID(String serviceID);
}
