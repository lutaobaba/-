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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAddAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField adminid;
	private JTextField adminname;
	private JPasswordField pwd;
	private JPasswordField repwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAddAdmin frame = new FrmAddAdmin();
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
	public FrmAddAdmin() {
		setVisible(true);
		
		setTitle("新增员工");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 450, 300);
		setAlwaysOnTop(false);//置顶
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ID = new JLabel("员工账号：");
		ID.setBounds(53, 38, 95, 15);
		contentPane.add(ID);
		
		JLabel NAME = new JLabel("员工姓名：");
		NAME.setBounds(53, 77, 95, 15);
		contentPane.add(NAME);
		
		JLabel PWD = new JLabel("密码：");
		PWD.setBounds(53, 115, 95, 15);
		contentPane.add(PWD);
		
		JLabel REPWD = new JLabel("确认密码：");
		REPWD.setBounds(53, 150, 95, 15);
		contentPane.add(REPWD);
		
		adminid = new JTextField();
		adminid.setBounds(158, 30, 180, 30);
		contentPane.add(adminid);
		adminid.setColumns(10);
		
		adminname = new JTextField();
		adminname.setBounds(158, 70, 180, 30);
		contentPane.add(adminname);
		adminname.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(158, 110, 180, 30);
		contentPane.add(pwd);
		
		repwd = new JPasswordField();
		repwd.setBounds(158, 150, 180, 30);
		contentPane.add(repwd);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnNewButton){
					String aId=adminid.getText();
					String aNmae=adminname.getText();
					String pwd1=new String(pwd.getPassword());
					String pwd2=new String(repwd.getPassword());
					try {
						BeanAdmin user=FreshUtil.adminManager.reg(aId,aNmae,pwd1,pwd2);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(200, 230, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(316, 230, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}
