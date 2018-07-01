package com.bcu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.CremeSkin;

import javax.swing.JLabel;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private static int pifu = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		try {
//			SwingUtilities.invokeAndWait(new Runnable() {
//				
//				@Override
//				public void run() {
//					SubstanceLookAndFeel.setSkin(new CremeSkin());
//					
//				}
//			});
//		} catch (InvocationTargetException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		//设置布局
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 316);
		contentPane = new JPanel();
		setTitle("图书借阅管理系统");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final Login self = this;
		
		JLabel label = new JLabel("图书借阅管理系统");
		label.setFont(new Font("方正舒体", Font.PLAIN, 25));
		label.setBounds(86, 27, 316, 56);
		label.setIcon(new ImageIcon(Login.class.getResource("/images/图书馆.png")));
		contentPane.add(label);
		
		JButton button = new JButton("管理员登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.dispose();
				LoginFrame2 frame2 = new LoginFrame2();
				frame2.setVisible(true);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(137, 108, 171, 33);
		button.setIcon(new ImageIcon(Login.class.getResource("/images/管理员.png")));
		contentPane.add(button);
		
		JButton button_1 = new JButton("借阅者登录");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.dispose();
				Login_student frame = new Login_student();
				frame.setVisible(true);
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(137, 174, 171, 33);
		button_1.setIcon(new ImageIcon(Login.class.getResource("/images/用户组.png")));
		contentPane.add(button_1);
		
	}
}
