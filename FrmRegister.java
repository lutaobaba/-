package cn.edu.zucc.fresh.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.fresh.FreshUtil;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FrmRegister extends JFrame {

	private JPanel contentPane;
	private JTextField userid;
	private JTextField username;
	private JTextField usersex;
	private JTextField phone;
	private JTextField email;
	private JTextField city;
	private JPasswordField userpwd;
	private JPasswordField userrepwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegister frame = new FrmRegister();
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
	public FrmRegister() {
		setVisible(true);
		
		setTitle("用户注册系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 449, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ID = new JLabel("用户账号：");
		ID.setFont(new Font("宋体", Font.PLAIN, 18));
		ID.setBounds(74, 30, 95, 15);
		contentPane.add(ID);
		
		JLabel NAME = new JLabel("用户姓名：");
		NAME.setFont(new Font("宋体", Font.PLAIN, 18));
		NAME.setBounds(74, 80, 95, 15);
		contentPane.add(NAME);
		
		JLabel SEX = new JLabel("用户性别：");
		SEX.setFont(new Font("宋体", Font.PLAIN, 18));
		SEX.setBounds(74, 130, 95, 15);
		contentPane.add(SEX);
		
		JLabel PWD = new JLabel("用户密码：");
		PWD.setFont(new Font("宋体", Font.PLAIN, 18));
		PWD.setBounds(74, 180, 95, 15);
		contentPane.add(PWD);
		
		JLabel REPWD = new JLabel("确认密码：");
		REPWD.setFont(new Font("宋体", Font.PLAIN, 18));
		REPWD.setBounds(74, 230, 95, 15);
		contentPane.add(REPWD);
		
		JLabel PHONE = new JLabel("电话：");
		PHONE.setFont(new Font("宋体", Font.PLAIN, 18));
		PHONE.setBounds(74, 280, 95, 15);
		contentPane.add(PHONE);
		
		JLabel EMAIL = new JLabel("邮箱：");
		EMAIL.setFont(new Font("宋体", Font.PLAIN, 18));
		EMAIL.setBounds(74, 330, 95, 15);
		contentPane.add(EMAIL);
		
		JLabel CITY = new JLabel("所在城市：");
		CITY.setFont(new Font("宋体", Font.PLAIN, 18));
		CITY.setBounds(74, 380, 95, 15);
		contentPane.add(CITY);
		
		userid = new JTextField();
		userid.setBounds(185, 25, 180, 30);
		contentPane.add(userid);
		userid.setColumns(10);
		
		username = new JTextField();
		username.setBounds(185, 75, 180, 30);
		contentPane.add(username);
		username.setColumns(10);
		
		usersex = new JTextField();
		usersex.setBounds(185, 125, 180, 30);
		contentPane.add(usersex);
		usersex.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(185, 275, 180, 30);
		contentPane.add(phone);
		phone.setColumns(10);
		
		email = new JTextField();
		email.setBounds(185, 325, 180, 30);
		contentPane.add(email);
		email.setColumns(10);
		
		city = new JTextField();
		city.setBounds(185, 375, 180, 30);
		contentPane.add(city);
		city.setColumns(10);
		
		userpwd = new JPasswordField();
		userpwd.setBounds(185, 175, 180, 30);
		contentPane.add(userpwd);
		
		userrepwd = new JPasswordField();
		userrepwd.setBounds(185, 225, 180, 30);
		contentPane.add(userrepwd);
		
		JButton Register = new JButton("注册");
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==Register){
					String uId=userid.getText();
					String uNmae=username.getText();
					String uSex=usersex.getText();
					String uPhone=phone.getText();
					String uEmail=email.getText();
					String uCity=city.getText();
					String pwd1=new String(userpwd.getPassword());
					String pwd2=new String(userrepwd.getPassword());
					try {
						BeanUser user=FreshUtil.userManager.reg(uId,uNmae,uSex,pwd1,pwd2,uPhone,uEmail,uCity);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					new FrmLogin3();
					setVisible(false);
				}
			}
		});
		Register.setBounds(188, 513, 100, 40);
		contentPane.add(Register);
		
		JButton  LastStep= new JButton("返回上一步");
		LastStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == LastStep) {
					new FrmLogin3();
					setVisible(false);
				}
			}
		});
		LastStep.setBounds(325, 513, 100, 40);
		contentPane.add(LastStep);
		
		this.setVisible(true);
	}
}
