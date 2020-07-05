package cn.edu.zucc.fresh.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmCreat extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCreat frame = new FrmCreat();
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
	public FrmCreat() {
		setTitle("\u7528\u6237\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u8D26\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblNewLabel.setBounds(74, 38, 95, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(111, 95, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\u786E\u8BA4\uFF1A");
		lblNewLabel_2.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(74, 153, 103, 15);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(188, 30, 190, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 87, 190, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(187, 145, 190, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.setBounds(188, 219, 100, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u9000\u51FA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnNewButton_1) {
					System.exit(0);
				}
			}
		});
		btnNewButton_1.setBounds(312, 219, 100, 40);
		contentPane.add(btnNewButton_1);
		
		this.setVisible(true);
	}

}
