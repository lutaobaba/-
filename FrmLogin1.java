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
	static JFrame jf = new JFrame("ÉúÏÊÍø³¬µÇÂ¼Ñ¡Ôñ");
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
		setTitle("\u751F\u9C9C\u7F51\u8D85");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	   
		   
		JButton btnNewButton = new JButton("\u7BA1\u7406\u5458\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnNewButton) {
					new FrmLogin2();
				}
			}
		});
		btnNewButton.setBounds(32, 51, 180, 75);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u7528\u6237\u767B\u5F55");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource()==btnNewButton_1) {
					new FrmLogin3();
				}
			}
		});
		btnNewButton_1.setBounds(241, 51, 180, 75);
		contentPane.add(btnNewButton_1);
	}
	
	public static void closeThis(){
		jf.dispose();
	}
}
