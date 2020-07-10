package cn.edu.zucc.fresh.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.fresh.FreshUtil;
import cn.edu.zucc.fresh.control.KindManager;
import cn.edu.zucc.fresh.model.BeanAdmin;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanProduct;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

public class FrmAddProduct extends JFrame {

	public BeanFreshKind kind=null;
	private JPanel contentPane;
	private JTextField productid;
	private JTextField productname;
	private JTextField price;
	private JTextField vipprice;
	private JTextField number;
	private JTextField norm;
	private JTextField details;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmAddAdmin frame = new FrmAddAdmin();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public FrmAddProduct() {
		setVisible(true);
		
		setTitle("新增商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 452, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel PID = new JLabel("商品编号：");
		PID.setBounds(53, 80, 95, 15);
		contentPane.add(PID);
		
		JLabel NAME = new JLabel("商品名称：");
		NAME.setBounds(53, 130, 95, 15);
		contentPane.add(NAME);
		
		JLabel PRICE = new JLabel("价格：");
		PRICE.setBounds(53, 180, 95, 15);
		contentPane.add(PRICE);
		
		JLabel VPRICE = new JLabel("会员价：");
		VPRICE.setBounds(53, 230, 95, 15);
		contentPane.add(VPRICE);
		
		JLabel NUM = new JLabel("数量：");
		NUM.setBounds(53, 280, 95, 15);
		contentPane.add(NUM);
		
		JLabel NORM = new JLabel("规格：");
		NORM.setBounds(54, 330, 95, 15);
		contentPane.add(NORM);
		
		JLabel DETAIL = new JLabel("详情：");
		DETAIL.setBounds(54, 380, 95, 15);
		contentPane.add(DETAIL);
		
		productid = new JTextField();
		productid.setBounds(158, 73, 180, 30);
		contentPane.add(productid);
		productid.setColumns(10);
		
		productname = new JTextField();
		productname.setBounds(158, 123, 180, 30);
		contentPane.add(productname);
		productname.setColumns(10);
		
		price = new JTextField();
		price.setBounds(158, 173, 180, 30);
		contentPane.add(price);
		price.setColumns(10);
		
		vipprice = new JTextField();
		vipprice.setBounds(158, 223,180, 30);
		contentPane.add(vipprice);
		vipprice.setColumns(10);
		
		number = new JTextField();
		number.setBounds(158, 273, 180, 30);
		contentPane.add(number);
		number.setColumns(10);
		
		norm = new JTextField();
		norm.setBounds(159, 323, 180, 30);
		contentPane.add(norm);
		norm.setColumns(10);
		
		details = new JTextField();
		details.setBounds(159, 373, 180, 30);
		contentPane.add(details);
		details.setColumns(10);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnNewButton){
					String pID=productid.getText();
					//String kID=kindid.getText();
					String pNAME=productname.getText();
					String Price=price.getText();
					String VPrice=vipprice.getText();
					String Number=number.getText();
					String Norm=norm.getText();
					String Details=details.getText();
					try {
						BeanProduct product=FreshUtil.productManager.addProduct(pID, kind, pNAME, Price, VPrice, Number, Norm, Details);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(187, 448, 97, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(331, 448, 97, 42);
		contentPane.add(btnNewButton_1);
		
		
	}
}
