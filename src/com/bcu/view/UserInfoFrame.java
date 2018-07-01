package com.bcu.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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

public class UserInfoFrame extends JFrame {

	private JPanel contentPane;
	JPanel panel_2 = new JPanel();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoFrame frame = new UserInfoFrame();
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
	public UserInfoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 250, 663, 464);
		contentPane = new JPanel();
		setTitle("图书借阅记录");
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
		
		JLabel label_3 = new JLabel("个人信息");
		label_3.setForeground(new Color(0, 153, 255));
		label_3.setBounds(165, 13, 137, 40);
		panel_2.add(label_3);
		label_3.setFont(new Font("方正舒体", Font.PLAIN, 25));
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(36, 111, 555, 293);
		panel_table.setLayout(null);
		contentPane.add(panel_table);
		
		JLabel Lnumber = new JLabel("学号：");
		Lnumber.setForeground(new Color(0, 153, 255));
		Lnumber.setFont(new Font("方正舒体", Font.PLAIN, 20));
		Lnumber.setBounds(35, 23, 60, 38);
		panel_table.add(Lnumber);
		
		JLabel Tnumber = new JLabel(student.getStudentNum());
		Tnumber.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Tnumber.setBounds(98, 27, 168, 29);
		panel_table.add(Tnumber);
		
		JLabel Lname = new JLabel("姓名：");
		Lname.setForeground(new Color(0, 153, 255));
		Lname.setFont(new Font("方正舒体", Font.PLAIN, 20));
		Lname.setBounds(303, 23, 60, 38);
		panel_table.add(Lname);
		
		JLabel Tname = new JLabel(student.getStudentName());
		Tname.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Tname.setBounds(366, 27, 168, 29);
		panel_table.add(Tname);
		
		JLabel Lgrade = new JLabel("年级：");
		Lgrade.setForeground(new Color(0, 153, 255));
		Lgrade.setFont(new Font("方正舒体", Font.PLAIN, 20));
		Lgrade.setBounds(35, 86, 60, 38);
		panel_table.add(Lgrade);
		
		JLabel Tgrade = new JLabel(student.getGrade());
		Tgrade.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Tgrade.setBounds(98, 90, 168, 29);
		panel_table.add(Tgrade);
		
		JLabel Lclasses = new JLabel("班级：");
		Lclasses.setForeground(new Color(0, 153, 255));
		Lclasses.setFont(new Font("方正舒体", Font.PLAIN, 20));
		Lclasses.setBounds(303, 86, 60, 38);
		panel_table.add(Lclasses);
		
		JLabel Tclasses = new JLabel(student.getStudentClass());
		Tclasses.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Tclasses.setBounds(366, 90, 168, 29);
		panel_table.add(Tclasses);
		
		List<Borrow> bbook = new ArrayList<Borrow>();
		BorrowDao bdao = new BorrowDao();
		
		List<Return> rbook = new ArrayList<Return>();
		ReturnDao rdao = new ReturnDao();
		bbook = bdao.getBorrowByUid(Login_student.Userid);
		rbook = rdao.getReturnByUid(Login_student.Userid);
		
		
		JLabel Lbbooknum = new JLabel("借阅书籍总数：");
		Lbbooknum.setForeground(new Color(0, 153, 255));
		Lbbooknum.setFont(new Font("方正舒体", Font.PLAIN, 20));
		Lbbooknum.setBounds(35, 159, 155, 38);
		panel_table.add(Lbbooknum);
		
		JLabel Tbbooknum = new JLabel(bbook.size()+"本");
		Tbbooknum.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Tbbooknum.setBounds(189, 163, 88, 29);
		panel_table.add(Tbbooknum);
		
		JLabel Lnor = new JLabel("未归还书籍：");
		Lnor.setForeground(new Color(0, 153, 255));
		Lnor.setFont(new Font("方正舒体", Font.PLAIN, 20));
		Lnor.setBounds(291, 159, 155, 38);
		panel_table.add(Lnor);
		
		int num=bbook.size()-rbook.size();
		JLabel Tnor = new JLabel(num+"本");
		Tnor.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Tnor.setBounds(445, 163, 88, 29);
		panel_table.add(Tnor);
		
		bbook = bdao.getBorrowByState(Login_student.Userid, 0, 1);
		Borrow book=null;
		System.out.println("bdao"+bdao);
		if(bbook!=null){
			book = bbook.get(0);
		}
		String datestr=new SimpleDateFormat("yyyy-MM-dd").format(book.getBorrowTime());
		
		JLabel Ltime = new JLabel("最近借阅时间：");
		Ltime.setForeground(new Color(0, 153, 255));
		Ltime.setFont(new Font("方正舒体", Font.PLAIN, 20));
		Ltime.setBounds(35, 224, 174, 38);
		panel_table.add(Ltime);
		
		JLabel Ttime = new JLabel(datestr);
		Ttime.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Ttime.setBounds(176, 228, 174, 29);
		panel_table.add(Ttime);
		
		
	}

}
