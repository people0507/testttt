package MainFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bill.GUI_Bill;
import Bill.GUI_Statistic;
import Client.GUI_Client;
import Room.GUI_Room;
import Service.GUI_Service;
import Staff.GUI_Staff;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Color;

public class MainFrame extends JFrame {
	Icon checkIcon = new ImageIcon("icon\\checkicon.png");
	Icon serviceIcon = new ImageIcon("icon\\service.png");
	Icon staffIcon = new ImageIcon("icon\\staff.png");
	Icon clientIcon = new ImageIcon("icon\\clienticon.png");
	Icon roomIcon = new ImageIcon("icon\\room.png");
	Icon statisticIcon = new ImageIcon("icon\\statistic.png");

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setBackground(new Color(255, 255, 255));
		setTitle("HOTEL MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 765);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton(checkIcon);
		btnNewButton.setText("Check in/out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUI_Bill gb = new GUI_Bill();
					gb.setVisible(true);
					gb.setLocationRelativeTo(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(10, 82, 196, 57);
		contentPane.add(btnNewButton);
		
		JButton btnClient = new JButton(clientIcon);
		btnClient.setText("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Client gc = new GUI_Client();
				gc.setVisible(true);
				gc.setLocationRelativeTo(null);
			}
		});
		btnClient.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClient.setBounds(10, 162, 196, 57);
		contentPane.add(btnClient);
		
		JButton btnStaff = new JButton(staffIcon);
		btnStaff.setText("Staff");
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Staff gs = new GUI_Staff();
				gs.setVisible(true);
				gs.setLocationRelativeTo(null);
			}
		});
		btnStaff.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStaff.setBounds(225, 162, 196, 57);
		contentPane.add(btnStaff);
		
		JButton btnRooms = new JButton(roomIcon);
		btnRooms.setText("Room");
		btnRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Room gr = new GUI_Room();
				gr.setVisible(true);
				gr.setLocationRelativeTo(null);
			}
		});
		btnRooms.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRooms.setBounds(225, 82, 196, 57);
		contentPane.add(btnRooms);
		
		JButton btnServices = new JButton(serviceIcon);
		btnServices.setText("Service");
		btnServices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Service gs = new GUI_Service();
				gs.setVisible(true);
				gs.setLocationRelativeTo(null);
			}
		});
		btnServices.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnServices.setBounds(10, 242, 196, 57);
		contentPane.add(btnServices);
		
		JLabel lblNewLabel = new JLabel("Hotel Manager");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(129, 23, 163, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnStatistic = new JButton(statisticIcon);
		btnStatistic.setText("Statistic");
		btnStatistic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Statistic gs = new GUI_Statistic();
				gs.setVisible(true);
				gs.setLocationRelativeTo(null);
			}
		});
		btnStatistic.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStatistic.setBounds(225, 242, 196, 57);
		contentPane.add(btnStatistic);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBackground(new Color(247, 248, 249));
		ImageIcon img = new ImageIcon("icon\\logo.jpg");
		lblNewLabel_1.setIcon(img);
		lblNewLabel_1.setBounds(26, 322, 380, 380);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
	}
}
