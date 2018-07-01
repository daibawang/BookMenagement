package com.bcu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bcu.bean.Student;
import com.bcu.dao.StudentDao;
import com.bcu.model.StudentTableModel;

public class MenageUserFrame extends JFrame {

	private JPanel contentPane;
	JPanel panel_2 = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private StudentTableModel tableModel;
	private JTable table_1;
	private JTable table;
	private JTextField textField_2;
	private JTextField Snum;
	private JTextField Sname;
	private JTextField Ssex;
	private JTextField Sgrade;
	private JTextField Sclasses;
	private List<Student> findlist;
	private JTextField Spassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenageUserFrame frame = new MenageUserFrame();
					frame.setVisible(true);
//					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenageUserFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 638);
		contentPane = new JPanel();
		setTitle("图书借阅");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(BorderFactory.createLineBorder(new Color(194,214,232)));
		panel_2.setBounds(29, 37, 777, 87);
		contentPane.add(panel_2);
		
		JLabel label_3 = new JLabel("条件");
		label_3.setBounds(14, 0, 51, 27);
		panel_2.add(label_3);
		label_3.setFont(new Font("方正舒体", Font.PLAIN, 18));
		
		JLabel label_4 = new JLabel("学生学号：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_4.setBounds(24, 34, 97, 26);
		panel_2.add(label_4);
		
		textField = new JTextField();
		textField.setBounds(115, 37, 118, 24);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel label_5 = new JLabel("学生姓名：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_5.setBounds(247, 34, 97, 26);
		panel_2.add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(334, 37, 118, 24);
		panel_2.add(textField_1);
		
		JLabel label_6 = new JLabel("学生班级：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_6.setBounds(473, 34, 97, 26);
		panel_2.add(label_6);
		
		JButton button_7 = new JButton("查询");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				student.setStudentNum(textField.getText());
				student.setStudentName(textField_1.getText());
				student.setStudentClass(textField_2.getText());;
				StudentDao dao = new StudentDao();
				findlist = dao.find(student);
				tableModel.setList(findlist);
//				更新table界面显示
				table.updateUI();
			}
		});

		button_7.setBounds(672, 30, 91, 38);
		button_7.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/搜索探讨.png")));
		panel_2.add(button_7);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(561, 37, 97, 24);
		panel_2.add(textField_2);
		
		JLabel label_7 = new JLabel("支持不定条件查询，请输入任意条件查询书籍，全为空显示全部");
		label_7.setForeground(new Color(0, 153, 255));
		label_7.setFont(new Font("方正舒体", Font.PLAIN, 16));
		label_7.setBounds(67, 1, 464, 26);
		panel_2.add(label_7);
		
		
		
		JLabel label_2 = new JLabel("搜索");
		label_2.setBounds(29, 13, 137, 27);
		contentPane.add(label_2, BorderLayout.NORTH);
		label_2.setFont(new Font("方正舒体", Font.PLAIN, 18));
		
		
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(29, 137, 777, 247);
		panel_table.setLayout(null);
		contentPane.add(panel_table);
		
		tableModel = new StudentTableModel();
		table = new JTable(tableModel);
		//设置每列宽占比
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		//单元格高
		table.setRowHeight(35);
		//单元格居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器  
		tcr.setHorizontalAlignment(JLabel.CENTER);//居中显示  
		table.setDefaultRenderer(Object.class, tcr);//设置渲染器  
		
		//table.setBounds(11, 26, 749, 319);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(2, 3, 775, 231);
		panel_table.add(scrollPane);
		
		JLabel label = new JLabel("学生学号:");
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label.setBounds(39, 397, 99, 41);
		contentPane.add(label);
		
		Snum = new JTextField();
		Snum.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Snum.setBounds(137, 405, 137, 27);
		contentPane.add(Snum);
		Snum.setColumns(10);
		
		JLabel label_1 = new JLabel("学生姓名:");
		label_1.setForeground(new Color(0, 153, 255));
		label_1.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_1.setBounds(39, 469, 99, 41);
		contentPane.add(label_1);
		
		Sname = new JTextField();
		Sname.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Sname.setColumns(10);
		Sname.setBounds(137, 477, 137, 27);
		contentPane.add(Sname);
		
		JLabel label_8 = new JLabel("学生性别:");
		label_8.setForeground(new Color(0, 153, 255));
		label_8.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_8.setBounds(39, 537, 99, 41);
		contentPane.add(label_8);
		
		Ssex = new JTextField();
		Ssex.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Ssex.setColumns(10);
		Ssex.setBounds(137, 545, 137, 27);
		contentPane.add(Ssex);
		
		JLabel label_9 = new JLabel("年级:");
		label_9.setForeground(new Color(0, 153, 255));
		label_9.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_9.setBounds(356, 405, 99, 41);
		contentPane.add(label_9);
		
		Sgrade = new JTextField();
		Sgrade.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Sgrade.setColumns(10);
		Sgrade.setBounds(416, 413, 137, 27);
		contentPane.add(Sgrade);
		
		JLabel label_10 = new JLabel("班级:");
		label_10.setForeground(new Color(0, 153, 255));
		label_10.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_10.setBounds(356, 469, 99, 41);
		contentPane.add(label_10);
		
		Sclasses = new JTextField();
		Sclasses.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Sclasses.setColumns(10);
		Sclasses.setBounds(416, 477, 137, 27);
		contentPane.add(Sclasses);
		
		JButton button = new JButton("增加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentDao sdao = new StudentDao();
				Student stu = new Student();
				int num=Integer.parseInt(Snum.getText());
				stu=sdao.findStudentbyNum(num);
				if(stu==null){
					Student student = getStudents();
					sdao.addStudent(student);
					updateTable();
				}else{
					int n = JOptionPane.showConfirmDialog(null, "该学号学生已存在\n不能重复添加", "温馨提示", JOptionPane.YES_OPTION);
				}
				
			}
		});
		button.setFont(new Font("方正舒体", Font.PLAIN, 22));
		button.setForeground(new Color(0, 153, 255));
		button.setBounds(672, 426, 113, 41);
		contentPane.add(button);
		
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index != -1){
					Student student = getStudents();
						tableModel.updateStudent(index, student);
						updateTable();
				}
			}
		});
		button_1.setForeground(new Color(255, 102, 51));
		button_1.setFont(new Font("方正舒体", Font.PLAIN, 22));
		button_1.setBounds(672, 531, 113, 41);
		contentPane.add(button_1);
		
		JLabel label_11 = new JLabel("输入信息后点击增加");
		label_11.setFont(new Font("方正舒体", Font.PLAIN, 18));
		label_11.setBounds(580, 397, 180, 27);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("输入信息后选取行数替换修改");
		label_12.setFont(new Font("方正舒体", Font.PLAIN, 18));
		label_12.setBounds(580, 491, 240, 27);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("初始密码:");
		label_13.setForeground(new Color(0, 153, 255));
		label_13.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_13.setBounds(329, 537, 99, 41);
		contentPane.add(label_13);
		
		Spassword = new JTextField(111111);
		Spassword.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Spassword.setColumns(10);
		Spassword.setBounds(427, 545, 137, 27);
		contentPane.add(Spassword);
	}
	protected void updateTable(){
		List<Student> list = new StudentDao().findStudentAll();
		tableModel.setList(list);
//		更新table界面显示
		table.updateUI();
	}
	protected Student getStudents(){
		Student student = new Student();
		student.setStudentNum(Snum.getText());
		student.setStudentName(Sname.getText());
		student.setPassword(Spassword.getText());
		student.setGrade(Sgrade.getText());
		student.setSex(Ssex.getText());
		student.setStudentClass(Sclasses.getText());
		return student;
		
	}


}
