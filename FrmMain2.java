package cn.edu.zucc.fresh.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.fresh.ui.FrmLogin1;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.FreshUtil;
import cn.edu.zucc.fresh.control.KindManager;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanProduct;
import cn.edu.zucc.fresh.ui.FrmAddAdmin;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class FrmMain2 extends JFrame {

	private JPanel contentPane;
	
	private Object tblPlanTitle[]=BeanFreshKind.tableTitles;
	private Object tblPlanData[][];
	DefaultTableModel tabPlanModel=new DefaultTableModel();
	private JTable dataTablePlan=new JTable(tabPlanModel);
	
	
	private Object tblStepTitle[]=BeanProduct.tblStepTitle;
	private Object tblStepData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataTableStep=new JTable(tabStepModel);
	
	private BeanFreshKind curPlan=null;
	List<BeanFreshKind> allPlan=null;
	List<BeanProduct> planSteps=null;
	
	private void reloadPlanTable(){//���ǲ������ݣ���Ҫ��ʵ�����滻
		try {
			allPlan=FreshUtil.kindManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPlanData =  new Object[allPlan.size()][BeanFreshKind.tableTitles.length];
		for(int i=0;i<allPlan.size();i++){
			for(int j=0;j<BeanFreshKind.tableTitles.length;j++)
				tblPlanData[i][j]=allPlan.get(i).getCell(j);
		}
		tabPlanModel.setDataVector(tblPlanData,tblPlanTitle);
		this.dataTablePlan.validate();
		this.dataTablePlan.repaint();
	}
	private void reloadPlanStepTabel(int planIdx){
		if(planIdx<0) return;
		curPlan=allPlan.get(planIdx);
		try {
			planSteps=FreshUtil.productManager.loadProducts(curPlan);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
					FrmMain2 frame = new FrmMain2();
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
	public FrmMain2() {
		
		setVisible(true);
		setTitle("����Աϵͳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1084, 551);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JMenu mnNewMenu0 = new JMenu("Ա������");
		menuBar.add(mnNewMenu0);
		
		JMenuItem mntmNewMenuItem0_0 = new JMenuItem("��ѯԱ��");
		mnNewMenu0.add(mntmNewMenuItem0_0);
		
		JMenuItem mntmNewMenuItem0_1 = new JMenuItem("����Ա��");
		mnNewMenu0.add(mntmNewMenuItem0_1);
		
		JMenuItem mntmNewMenuItem0_2 = new JMenuItem("ɾ��Ա��");
		mnNewMenu0.add(mntmNewMenuItem0_2);
		
		mntmNewMenuItem0_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem0_1) {
					FrmAddAdmin dlg=new FrmAddAdmin();
					dlg.setVisible(true);
				}
			}
		});
		
		JMenu mnNewMenu1 = new JMenu("�û�����");
		menuBar.add(mnNewMenu1);
		
		JMenuItem mntmNewMenuItem1_0 = new JMenuItem("��ѯ�û�");
		mnNewMenu1.add(mntmNewMenuItem1_0);
		
		
		JMenu mnNewMenu2 = new JMenu("����������");
		menuBar.add(mnNewMenu2);
		
//		JMenuItem mntmNewMenuItem2_0 = new JMenuItem("��ѯ���");
//		mnNewMenu2.add(mntmNewMenuItem2_0);
		
		JMenuItem mntmNewMenuItem2_1 = new JMenuItem("�������");
		mnNewMenu2.add(mntmNewMenuItem2_1);
		
		JMenuItem mntmNewMenuItem2_2 = new JMenuItem("ɾ�����");
		mnNewMenu2.add(mntmNewMenuItem2_2);
		
		JMenuItem mntmNewMenuItem2_3 = new JMenuItem("�޸����");
		mnNewMenu2.add(mntmNewMenuItem2_3);
		
		mntmNewMenuItem2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem2_1) {
					FrmAddKind dlg=new FrmAddKind();
					dlg.setVisible(true);
				}
			}
		});
		mntmNewMenuItem2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem2_2) {
					if(curPlan==null) {
						JOptionPane.showMessageDialog(null, "��ѡ�����", "����",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						FreshUtil.kindManager.deleteKind(curPlan);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		
		JMenu mnNewMenu3 = new JMenu("��Ʒ����");
		menuBar.add(mnNewMenu3);
		
//		JMenuItem mntmNewMenuItem3_0 = new JMenuItem("��ѯ��Ʒ");
//		mnNewMenu3.add(mntmNewMenuItem3_0);
		
		JMenuItem mntmNewMenuItem3_1 = new JMenuItem("������Ʒ");
		mnNewMenu3.add(mntmNewMenuItem3_1);
		
		JMenuItem mntmNewMenuItem3_2 = new JMenuItem("ɾ����Ʒ");
		mnNewMenu3.add(mntmNewMenuItem3_2);
		
		JMenuItem mntmNewMenuItem3_3 = new JMenuItem("�޸���Ʒ");
		mnNewMenu3.add(mntmNewMenuItem3_3);
		
		mntmNewMenuItem3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem3_1) {
					FrmAddProduct dlg=new FrmAddProduct();
					dlg.kind=curPlan;
					dlg.setVisible(true);
				}
			}
		});
		mntmNewMenuItem3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem3_2) {
					int i=FrmMain2.this.dataTableStep.getSelectedRow();
					if(i<0) {
						JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						FreshUtil.productManager.deleteProduct(planSteps.get(i));
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		
		
		JMenu mnNewMenu4 = new JMenu("���׹���");
		menuBar.add(mnNewMenu4);
		
		JMenuItem mntmNewMenuItem4_0 = new JMenuItem("��ѯ����");
		mnNewMenu4.add(mntmNewMenuItem4_0);
		
		JMenuItem mntmNewMenuItem4_1 = new JMenuItem("��������");
		mnNewMenu4.add(mntmNewMenuItem4_1);
		
		JMenuItem mntmNewMenuItem4_2 = new JMenuItem("ɾ������");
		mnNewMenu4.add(mntmNewMenuItem4_2);
		
		JMenuItem mntmNewMenuItem4_3 = new JMenuItem("�޸Ĳ���");
		mnNewMenu4.add(mntmNewMenuItem4_3);
		
		
		JMenu mnNewMenu5 = new JMenu("��������");
		menuBar.add(mnNewMenu5);
		
		JMenuItem mntmNewMenuItem5_0 = new JMenuItem("��ѯ����");
		mnNewMenu5.add(mntmNewMenuItem5_0);
		
		JMenuItem mntmNewMenuItem5_1 = new JMenuItem("��������");
		mnNewMenu5.add(mntmNewMenuItem5_1);
		
		JMenuItem mntmNewMenuItem5_2 = new JMenuItem("ɾ������");
		mnNewMenu5.add(mntmNewMenuItem5_2);
		
		JMenuItem mntmNewMenuItem5_3 = new JMenuItem("�޸�����");
		mnNewMenu5.add(mntmNewMenuItem5_3);
	
		
		JMenu mnNewMenu_5 = new JMenu("�Ż�ȯ����");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("��ѯ�Ż�ȯ");
		mnNewMenu_5.add(mntmNewMenuItem_12);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("�����Ż�ȯ");
		mnNewMenu_5.add(mntmNewMenuItem_13);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("ɾ���Ż�ȯ");
		mnNewMenu_5.add(mntmNewMenuItem_14);
		
		JMenuItem mntmNewMenuItem_18 = new JMenuItem("�޸��Ż�ȯ");
		mnNewMenu_5.add(mntmNewMenuItem_18);
		
		
		this.getContentPane().add(new JScrollPane(this.dataTablePlan), BorderLayout.WEST);
	    this.dataTablePlan.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMain2.this.dataTablePlan.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMain2.this.reloadPlanStepTabel(i);
			}
	    	
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);
	    
	    this.reloadPlanTable();
		
	}
}
