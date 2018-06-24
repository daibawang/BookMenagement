package com.bcu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin;
import org.pushingpixels.substance.api.skin.CremeSkin;

import com.bcu.bean.Student;
import com.bcu.dao.StudentDao;

import java.awt.FlowLayout;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class LoginFrame extends JFrame {


	private JPanel contentPane;
	private JTextField UserField;
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

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LoginFrame() {
		//设置布局
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label_2 = new JLabel("学号：");
		label_2.setFont(new Font("方正舒体", Font.PLAIN, 20));
		label_2.setBounds(94, 62, 72, 24);
		contentPane.add(label_2);
		
		NumField = new JTextField();
		NumField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//"用户名框失去焦点时调用"
				String usernum = NumField.getText();
				if(usernum==null ||usernum.trim().length()==0){
					Check.setText("学号不能为空");
				}else{
					Check.setText("");
				}
			}
		});
		NumField.setBounds(149, 65, 159, 24);
		contentPane.add(NumField);
		NumField.setColumns(10);
		
		JLabel label = new JLabel("姓名：");
		label.setFont(new Font("方正舒体", Font.PLAIN, 19));
		label.setBounds(94, 111, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("方正舒体", Font.PLAIN, 19));
		label_1.setBounds(94, 154, 72, 18);
		contentPane.add(label_1);
		
		UserField = new JTextField();
		UserField.setBounds(149, 111, 159, 24);
		contentPane.add(UserField);
		UserField.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setText("");
		pwd.setBounds(149, 151, 159, 24);
		contentPane.add(pwd);
		
		JButton button = new JButton("登录");
		button.setFont(new Font("方正舒体", Font.PLAIN, 19));
		final LoginFrame loginFrame = this;
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1. 获取用户名和密码
				String studentNum=NumField.getText();
				String studentName=UserField.getText();
				String password=new String(pwd.getPassword());
				System.out.println(studentNum+"\n"+studentName+"\n"+password);
				//2. 查询数据库是否有此用户,dao完成
				StudentDao dao = new StudentDao();
				Student student = dao.findStudent(studentNum, studentName, password);
				if(student ==null){
					Wrong.setText("学号、姓名或者密码错误");
				}else{
					System.out.println("登录成功");
					//当前登录窗口销毁
					loginFrame.dispose();
//					显示主窗口
					MainUserFrame frame = new MainUserFrame();
					frame.setVisible(true);
				}
			}
		});
		button.setBounds(166, 207, 100, 37);
		contentPane.add(button);
		
		Check = new JLabel("");
		Check.setForeground(Color.RED);
		Check.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Check.setBounds(318, 68, 126, 18);
		contentPane.add(Check);
		
		Wrong = new JLabel("");
		Wrong.setFont(new Font("方正舒体", Font.PLAIN, 17));
		Wrong.setBounds(115, 188, 221, 18);
		contentPane.add(Wrong);
		Wrong.setForeground(Color.red);
		
		label_3 = new JLabel("学生登录");
		label_3.setFont(new Font("方正舒体", Font.PLAIN, 28));
		label_3.setBounds(148, 16, 118, 39);
		contentPane.add(label_3);
		
		
		

		
	}
}
