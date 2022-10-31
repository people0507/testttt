package Client;

import java.util.ArrayList;

public interface IClient {
	public ArrayList<Client> get_listClient();
	public boolean add_Client(String clientID,String name,String idIC,String num,boolean gender,int age);
	public boolean update_Client(String clientID,String name,String idIC,String num,boolean gender,int age);
	public boolean delete_Client(String clientID);
}
