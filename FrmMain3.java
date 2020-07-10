package cn.edu.zucc.fresh.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.fresh.FreshUtil;
import cn.edu.zucc.fresh.model.BeanAddress;
import cn.edu.zucc.fresh.model.BeanProduct;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

import java.awt.BorderLayout;

public class FrmMain3 extends JFrame {

	private JPanel contentPane;

	private Object tblStepTitle[]=BeanAddress.tblStepTitle;
	private Object tblStepData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataTableStep=new JTable(tabStepModel);
	
	private BeanUser curPlan=null;
	List<BeanUser> allPlan=null;
	List<BeanAddress> planSteps=null;
	
	private void reloadPlanStepTabel(){
		curPlan=BeanUser.currentLoginUser;
		try {
			planSteps=FreshUtil.addressManager.loadAll(curPlan);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblStepData =new Object[planSteps.size()][BeanProduct.tblStepTitle.length];
		for(int i=0;i<planSteps.size();i++){
			for(int j=0;j<BeanProduct.tblStepTitle.length;j++)
				tblStepData[i][j]=planSteps.get(i).getCell(j);
		}
		
		tabStepModel.setDataVector(tblStepData,tblStepTitle);
		this.dataTableStep.validate();
		this.dataTableStep.repaint();
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain3 frame = new FrmMain3();
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
	public FrmMain3() {
		
		setVisible(true);
		setTitle("用户系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 463);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JMenu mnNewMenu = new JMenu("用户管理");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改密码");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("修改个人信息");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem) {
					FrmChangePwd dlg=new FrmChangePwd();
					dlg.setVisible(true);
				}
			}
		});
		
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem_1) {
					FrmChangeInfor dlg=new FrmChangeInfor();
					dlg.setVisible(true);
				}
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("地址管理");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem1_0 = new JMenuItem("查看所有地址");
		mnNewMenu_1.add(mntmNewMenuItem1_0);
		
		JMenuItem mntmNewMenuItem1_1 = new JMenuItem("新增地址");
		mnNewMenu_1.add(mntmNewMenuItem1_1);
		
		JMenuItem mntmNewMenuItem1_2 = new JMenuItem("删除地址");
		mnNewMenu_1.add(mntmNewMenuItem1_2);
		
		JMenuItem mntmNewMenuItem1_3 = new JMenuItem("修改地址");
		mnNewMenu_1.add(mntmNewMenuItem1_3);
		
		mntmNewMenuItem1_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem1_0) {
					getContentPane().add(new JScrollPane(dataTableStep), BorderLayout.WEST);
					reloadPlanStepTabel();
				}
			}
		});
		
		mntmNewMenuItem1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem1_1) {
					FrmAddAddress dlg=new FrmAddAddress();
					dlg.user=BeanUser.currentLoginUser;
					dlg.setVisible(true);
				}
			}
		});
		
		
		
		JMenu mnNewMenu_2 = new JMenu("购物车");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem2_1 = new JMenuItem("查看购物车");
		mnNewMenu_2.add(mntmNewMenuItem2_1);
		
		JMenuItem mntmNewMenuItem2_2 = new JMenuItem("购买商品");
		mnNewMenu_2.add(mntmNewMenuItem2_2);

		
		
		
		
		JMenu mnNewMenu_3 = new JMenu("菜谱");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem3_0 = new JMenuItem("菜谱推荐");
		mnNewMenu_3.add(mntmNewMenuItem3_0);
		
		
		JMenu mnNewMenu_4 = new JMenu("优惠券");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem4_0 = new JMenuItem("查看优惠券");
		mnNewMenu_4.add(mntmNewMenuItem4_0);
	}
}
