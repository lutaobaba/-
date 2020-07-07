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
import cn.edu.zucc.fresh.control.*;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class FrmLogin2 extends JFrame {
	
	private JPanel contentPane;
	private JTextField adminId;
	private JPasswordField adminPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin2 frame = new FrmLogin2();
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
	public FrmLogin2() {
		setTitle("管理员登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		adminId = new JTextField();
		adminId.setBounds(167, 47, 190, 35);
		contentPane.add(adminId);
		adminId.setColumns(10);
		
		adminPwd = new JPasswordField();
		adminPwd.setBounds(167, 103, 190, 35);
		contentPane.add(adminPwd);
		
		JLabel ID = new JLabel("账号：");
		ID.setFont(new Font("宋体", Font.PLAIN, 18));
		ID.setBounds(99, 55, 58, 15);
		contentPane.add(ID);
		
		JLabel PWD = new JLabel("密码：");
		PWD.setFont(new Font("宋体", Font.PLAIN, 18));
		PWD.setBounds(99, 111, 58, 15);
		contentPane.add(PWD);
		
		JButton Login = new JButton("登录");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Login) {
					String adminid=adminId.getText();
					String pwd=new String(adminPwd.getPassword());
					try {
						BeanAdmin.currentLoginAdmin= FreshUtil.adminManager.login(adminid, pwd);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					new FrmMain2();
					setVisible(false);
				}
			}
		});
		Login.setBounds(189, 208, 100, 40);
		contentPane.add(Login);
		
		JButton  LastStep= new JButton("返回上一步");
		LastStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == LastStep) {
					new FrmLogin1();
					setVisible(false);
				}
			}
		});
		LastStep.setBounds(326, 208, 100, 40);
		contentPane.add(LastStep);
		
		this.setVisible(true);
	}
}
