package com.bcu.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.CremeSkin;

import com.bcu.bean.Menage;
import com.bcu.bean.Student;
import com.bcu.dao.MenageDao;
import com.bcu.dao.StudentDao;

public class LoginFrame2 extends JFrame {


	private JPanel contentPane;
	private JLabel Check;
	private JLabel Wrong;
	private JLabel label_2;
	private JTextField NumField;
	private JPasswordField pwd;
	private JLabel label_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					SubstanceLookAndFeel.setSkin(new CremeSkin());
					
				}
			});
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame2 frame = new LoginFrame2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LoginFrame2() {
		//设置布局
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_2 = new JLabel("账号：");
		label_2.setFont(new Font("方正舒体", Font.PLAIN, 20));
		label_2.setBounds(94, 75, 72, 24);
		contentPane.add(label_2);
		
		NumField = new JTextField();
		NumField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//"用户名框失去焦点时调用"
				String usernum = NumField.getText();
				if(usernum==null ||usernum.trim().length()==0){
					Check.setText("账号不能为空");
				}else{
					Check.setText("");
				}
			}
		});
		NumField.setBounds(149, 78, 159, 24);
		contentPane.add(NumField);
		NumField.setColumns(10);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("方正舒体", Font.PLAIN, 19));
		label_1.setBounds(94, 131, 72, 24);
		contentPane.add(label_1);
		
		pwd = new JPasswordField();
		pwd.setText("");
		pwd.setBounds(149, 131, 159, 24);
		contentPane.add(pwd);
		
		JButton button = new JButton("登录");
		button.setFont(new Font("方正舒体", Font.PLAIN, 19));
		final LoginFrame2 loginFrame = this;
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1. 获取用户名和密码
				String Num=NumField.getText();
				String password=new String(pwd.getPassword());
				System.out.println(Num+"\n"+password);
				//2. 查询数据库是否有此用户,dao完成
				MenageDao dao = new MenageDao();
				Menage menage = dao.findMenage(Num, password);
				if(menage ==null){
					Wrong.setText("账号或者密码错误");
				}else{
					System.out.println("登录成功");
//					当前登录窗口销毁
//					self.dispose();
////					显示主窗口
//					MainFrame frame = new MainFrame();
//					frame.setVisible(true);
				}
			}
		});
		button.setBounds(166, 199, 100, 37);
		contentPane.add(button);
		
		Check = new JLabel("");
		Check.setForeground(Color.RED);
		Check.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Check.setBounds(322, 78, 126, 24);
		contentPane.add(Check);
		
		Wrong = new JLabel("");
		Wrong.setFont(new Font("方正舒体", Font.PLAIN, 17));
		Wrong.setBounds(116, 168, 221, 18);
		contentPane.add(Wrong);
		Wrong.setForeground(Color.red);
		
		label_3 = new JLabel("管理员登录");
		label_3.setFont(new Font("方正舒体", Font.PLAIN, 28));
		label_3.setBounds(148, 16, 160, 39);
		contentPane.add(label_3);
		
		
		

		
	}
}
