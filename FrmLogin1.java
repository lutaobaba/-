package cn.edu.zucc.fresh.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin1 extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin1 frame = new FrmLogin1();
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

	public FrmLogin1() {
		setVisible(true);
		setTitle("生鲜网超");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 464, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	   
		   
		JButton btnNewButton = new JButton("管理员登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnNewButton) {
					new FrmLogin2();
				}
				setVisible(false);
			}
		});
		btnNewButton.setBounds(32, 51, 180, 75);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("用户登录");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource()==btnNewButton_1) {
					new FrmLogin3();
				}
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(241, 51, 180, 75);
		contentPane.add(btnNewButton_1);
	}
	
}
