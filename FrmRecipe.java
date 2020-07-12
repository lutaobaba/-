package cn.edu.zucc.fresh.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.fresh.FreshUtil;
import cn.edu.zucc.fresh.model.BeanFoldInfor;
import cn.edu.zucc.fresh.model.BeanProduct;
import cn.edu.zucc.fresh.model.BeanProductRecipe;
import cn.edu.zucc.fresh.model.BeanRecipe;
import cn.edu.zucc.fresh.util.BaseException;

public class FrmRecipe extends JFrame {

	private JPanel contentPane;

	private Object tblRecipeTitle[]=BeanRecipe.tblTitle;
	private Object tblRecipeData[][];
	DefaultTableModel tabRecipeModel=new DefaultTableModel();
	private JTable dataRecipe=new JTable(tabRecipeModel);
	
	private Object tblStepTitle[]=BeanProductRecipe.tblStepTitle;
	private Object tblStepData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataTableStep=new JTable(tabStepModel);
	
	private BeanRecipe curRecipe=null;
	List<BeanRecipe> allRecipes=null;
	List<BeanProductRecipe> planSteps=null;
	
	private void reloadRecipe(){//’‚ «≤‚ ‘ ˝æ›£¨–Ë“™”√ µº  ˝ÃÊªª
		try {
			allRecipes=FreshUtil.recipeManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRecipeData =  new Object[allRecipes.size()][BeanRecipe.tblTitle.length];
		for(int i=0;i<allRecipes.size();i++){
			for(int j=0;j<BeanRecipe.tblTitle.length;j++)
				tblRecipeData[i][j]=allRecipes.get(i).getCell(j);
		}
		tabRecipeModel.setDataVector(tblRecipeData,tblRecipeTitle);
		this.dataRecipe.validate();
		this.dataRecipe.repaint();
	}
	private void reloadPlanStepTabel(int planIdx){
		if(planIdx<0) return;
		curRecipe=allRecipes.get(planIdx);
		try {
			planSteps=FreshUtil.productManager.loadRecipe(curRecipe);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblStepData =new Object[planSteps.size()][BeanProductRecipe.tblStepTitle.length];
		for(int i=0;i<planSteps.size();i++){
			for(int j=0;j<BeanProductRecipe.tblStepTitle.length;j++)
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
					FrmRecipe frame = new FrmRecipe();
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
	public FrmRecipe() {
		setTitle("≤À∆◊Õ∆ºˆ");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 538);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("≤À∆◊π‹¿Ì");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("À¢–¬");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("–¬‘ˆ≤À∆◊");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("…æ≥˝≤À∆◊");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("ÃÌº”≤À∆◊…Ã∆∑");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("…æ≥˝≤À∆◊…Ã∆∑");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem) {
					reloadRecipe();
				}
			}
		});
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem_1) {
					FrmAddRecipe dlg=new FrmAddRecipe();
					dlg.setVisible(true);
				}
			}
		});
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem_2) {
					int i=dataRecipe.getSelectedRow();
					if(i<0) {
						JOptionPane.showMessageDialog(null, "«Î—°‘Ò≤À∆◊", "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						FreshUtil.recipeManager.deleteRecipe(allRecipes.get(i));
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem_3) {
					FrmAddProductRecipe dlg=new FrmAddProductRecipe();
					dlg.pR=curRecipe;
					dlg.setVisible(true);
				}
			}
		});
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNewMenuItem_4) {
					int i=dataTableStep.getSelectedRow();
					if(i<0) {
						JOptionPane.showMessageDialog(null, "«Î—°‘Ò≤À∆◊", "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						FreshUtil.productManager.deleteRecipe(planSteps.get(i));
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		
		this.getContentPane().add(new JScrollPane(this.dataRecipe), BorderLayout.WEST);
		this.dataRecipe.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmRecipe.this.dataRecipe.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmRecipe.this.reloadPlanStepTabel(i);
			}
	    	
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);
	    this.reloadRecipe();
	}

}
