package com.bcu.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bcu.bean.Borrow;
import com.bcu.bean.Return;
import com.bcu.bean.Student;
import com.bcu.dao.BorrowDao;
import com.bcu.dao.ReturnDao;
import com.bcu.dao.StudentDao;
import com.bcu.model.MenageBorrowModel;

public class MenageMainFrame extends JFrame {

	private JPanel contentPane;
	JPanel panel_2 = new JPanel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenageMainFrame frame = new MenageMainFrame();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenageMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 464);
		contentPane = new JPanel();
		setTitle("个人中心");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(BorderFactory.createLineBorder(new Color(194,214,232)));
		panel_2.setBounds(83, 35, 433, 63);
		contentPane.add(panel_2);
		
		StudentDao dao = new StudentDao();
		Student student = dao.findStudentbyId(Login_student.Userid);
		
		JLabel label_3 = new JLabel("书籍和学生管理中心");
		label_3.setForeground(new Color(0, 153, 255));
		label_3.setBounds(94, 13, 268, 40);
		panel_2.add(label_3);
		label_3.setFont(new Font("方正舒体", Font.PLAIN, 25));
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(36, 111, 555, 293);
		panel_table.setBorder(BorderFactory.createLineBorder(new Color(194,214,232)));
		panel_table.setLayout(null);
		contentPane.add(panel_table);
		
		JButton button = new JButton("学生管理");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenageUserFrame frame = new MenageUserFrame();
				frame.setVisible(true);
				//新的关闭，就窗口仍保留
				frame.setDefaultCloseOperation(UserMainFrame.HIDE_ON_CLOSE);
			}
		});
		
		button.setForeground(new Color(0, 153, 255));
		button.setFont(new Font("方正舒体", Font.PLAIN, 22));
		button.setIcon(new ImageIcon(Login.class.getResource("/images/用户组.png")));
		button.setBounds(173, 41, 186, 39);
		panel_table.add(button);
		
		JButton button_1 = new JButton("书籍管理");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenageBookFrame frame = new MenageBookFrame();
				frame.setVisible(true);
				//新的关闭，就窗口仍保留
				frame.setDefaultCloseOperation(UserMainFrame.HIDE_ON_CLOSE);
			}
		});
		button_1.setForeground(new Color(0, 153, 255));
		button_1.setFont(new Font("方正舒体", Font.PLAIN, 22));
		button_1.setIcon(new ImageIcon(Login.class.getResource("/images/书3.png")));
		button_1.setBounds(173, 108, 186, 39);
		panel_table.add(button_1);
		
		JButton button_2 = new JButton("借阅详情");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenageBorrowFrame frame = new MenageBorrowFrame();
				frame.setVisible(true);
				//新的关闭，就窗口仍保留
				frame.setDefaultCloseOperation(UserMainFrame.HIDE_ON_CLOSE);
			}
		});
		button_2.setForeground(new Color(0, 153, 255));
		button_2.setFont(new Font("方正舒体", Font.PLAIN, 22));
		button_2.setIcon(new ImageIcon(Login.class.getResource("/images/记录3.png")));
		button_2.setBounds(173, 170, 186, 39);
		panel_table.add(button_2);
		
		JButton button_3 = new JButton("管理员操作");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenageMgerFrame frame = new MenageMgerFrame();
				frame.setVisible(true);
				//新的关闭，就窗口仍保留
				frame.setDefaultCloseOperation(UserMainFrame.HIDE_ON_CLOSE);
			}
		});
		button_3.setForeground(new Color(0, 153, 255));
		button_3.setFont(new Font("方正舒体", Font.PLAIN, 22));
		button_3.setIcon(new ImageIcon(Login.class.getResource("/images/管理员.png")));
		button_3.setBounds(173, 227, 186, 39);
		panel_table.add(button_3);
		
		List<Borrow> bbook = new ArrayList<Borrow>();
		BorrowDao bdao = new BorrowDao();
		
		List<Return> rbook = new ArrayList<Return>();
		ReturnDao rdao = new ReturnDao();
		bbook = bdao.getBorrowByUid(Login_student.Userid);
		rbook = rdao.getReturnByUid(Login_student.Userid);
		
		int num=bbook.size()-rbook.size();
		
		bbook = bdao.getBorrowByState(Login_student.Userid, 0, 1);
		Borrow book=null;
		System.out.println("bdao"+bdao);
		if(bbook!=null){
			book = bbook.get(0);
		}
		String datestr=new SimpleDateFormat("yyyy-MM-dd").format(book.getBorrowTime());
	}

}
