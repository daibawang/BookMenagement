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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bcu.bean.Menage;
import com.bcu.bean.Student;
import com.bcu.dao.MenageDao;
import com.bcu.dao.StudentDao;
import com.bcu.model.MenageModel;

public class MenageMgerFrame extends JFrame {

	private JPanel contentPane;
	JPanel panel_2 = new JPanel();
	private JTextField textField_1;
	private MenageModel tableModel;
	private JTable table_1;
	private JTable table;
	private JTextField Snum;
	private JTextField Sname;
	private List<Student> findlist;
	private JTextField Gpass;
	private JTextField Gnumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenageMgerFrame frame = new MenageMgerFrame();
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
	public MenageMgerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 250, 863, 638);
		contentPane = new JPanel();
		setTitle("图书借阅");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(29, 0, 777, 384);
		panel_table.setLayout(null);
		contentPane.add(panel_table);
		
		tableModel = new MenageModel();
		table = new JTable(tableModel);

		//单元格高
		table.setRowHeight(35);
		//单元格居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器  
		tcr.setHorizontalAlignment(JLabel.CENTER);//居中显示  
		table.setDefaultRenderer(Object.class, tcr);//设置渲染器  
		
		//table.setBounds(11, 26, 749, 319);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 13, 775, 345);
		panel_table.add(scrollPane);
		
		JLabel label = new JLabel("管理员账号:");
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label.setBounds(39, 397, 127, 41);
		contentPane.add(label);
		
		Snum = new JTextField();
		Snum.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Snum.setBounds(180, 405, 137, 27);
		contentPane.add(Snum);
		Snum.setColumns(10);
		
		JLabel label_1 = new JLabel("管理员姓名:");
		label_1.setForeground(new Color(0, 153, 255));
		label_1.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_1.setBounds(39, 469, 127, 41);
		contentPane.add(label_1);
		
		Sname = new JTextField();
		Sname.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Sname.setColumns(10);
		Sname.setBounds(180, 477, 137, 27);
		contentPane.add(Sname);
		
		JButton button = new JButton("增加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menage m = new Menage();
				MenageDao mdao = new MenageDao();
				m.setMenageName(Sname.getText());
				m.setMenageNum(Snum.getText());
				m.setMenagePassword(Gpass.getText());
				m.setPhone(Snum.getText());
				mdao.add(m);
				updateTable();
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
					Menage mengsMenage = getsall();
					MenageDao mdao = new MenageDao();
					mdao.update(mengsMenage,mengsMenage.getId());
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
		label_13.setBounds(49, 530, 99, 41);
		contentPane.add(label_13);
		
		Gpass = new JTextField();
		Gpass.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Gpass.setColumns(10);
		Gpass.setBounds(180, 542, 137, 27);
		contentPane.add(Gpass);
		
		JLabel label_8 = new JLabel("手机号:");
		label_8.setForeground(new Color(0, 153, 255));
		label_8.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_8.setBounds(331, 437, 127, 41);
		contentPane.add(label_8);
		
		Gnumber = new JTextField();
		Gnumber.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Gnumber.setColumns(10);
		Gnumber.setBounds(472, 445, 137, 27);
		contentPane.add(Gnumber);
	}
	protected void updateTable(){
		List<Menage> list = new MenageDao().findAll();
		tableModel.setList(list);
//		更新table界面显示
		table.updateUI();
	}
	protected Menage getsall(){
		Menage m = new Menage();
		m.setMenageName(Sname.getText());
		m.setMenageNum(Snum.getText());
		m.setMenagePassword(Gpass.getText());
		m.setPhone(Snum.getText());
		return m;
		
	}
}
