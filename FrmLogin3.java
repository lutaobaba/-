package cn.edu.zucc.fresh.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class FrmLogin3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		setTitle("\u7528\u6237\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblNewLabel.setBounds(91, 46, 58, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(91, 118, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(159, 38, 190, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(159, 110, 190, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnNewButton) {
					new FrmCreat();
				}
			}
		});
		btnNewButton.setBounds(106, 199, 100, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u767B\u5F55");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(216, 199, 100, 40);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u9000\u51FA");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnNewButton_2) {
					System.exit(0);
				}
			}
		});
		btnNewButton_2.setBounds(326, 199, 100, 40);
		contentPane.add(btnNewButton_2);
		
		this.setVisible(true);
	}

}
