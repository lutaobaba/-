package cn.edu.zucc.fresh.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.fresh.FreshUtil;
import cn.edu.zucc.fresh.model.BeanAdmin;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class FrmLogin3 extends JFrame {

	private JPanel contentPane;
	private JTextField userId;
	private JPasswordField userPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin3 frame = new FrmLogin3();
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
	public FrmLogin3() {
		setBackground(new Color(240, 240, 240));
		setTitle("ÓÃ»§µÇÂ¼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ID = new JLabel("ÕËºÅ£º");
		ID.setFont(new Font("ËÎÌå", Font.PLAIN, 18));
		ID.setBounds(91, 46, 58, 15);
		contentPane.add(ID);
		
		JLabel PWD = new JLabel("ÃÜÂë£º");
		PWD.setFont(new Font("ËÎÌå", Font.PLAIN, 18));
		PWD.setBounds(91, 118, 58, 15);
		contentPane.add(PWD);
		
		userId = new JTextField();
		userId.setBounds(159, 38, 190, 35);
		contentPane.add(userId);
		userId.setColumns(10);
		
		userPwd = new JPasswordField();
		userPwd.setBounds(159, 110, 190, 35);
		contentPane.add(userPwd);
		
		
		JButton btnCreat = new JButton("×¢²á");
		btnCreat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnCreat) {
					new FrmRegister();
				}
				setVisible(false);
			}
		});
		btnCreat.setBounds(106, 199, 100, 40);
		contentPane.add(btnCreat);
		
		JButton Login = new JButton("µÇÂ¼");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Login) {
					String userid=userId.getText();
					String pwd=new String(userPwd.getPassword());
					try {
						BeanUser.currentLoginUser= FreshUtil.userManager.login(userid, pwd);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
						return;
					}
					new FrmMain3();
					setVisible(false);
				}
			}
		});
		Login.setBounds(216, 199, 100, 40);
		contentPane.add(Login);
		
		JButton  LastStep= new JButton("·µ»ØÉÏÒ»²½");
		LastStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == LastStep) {
					new FrmLogin1();
					setVisible(false);
				}
			}
		});
		LastStep.setBounds(326, 199, 100, 40);
		contentPane.add(LastStep);
		
		this.setVisible(true);
	}
}
