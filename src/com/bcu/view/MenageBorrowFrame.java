package com.bcu.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
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

import com.bcu.bean.Book;
import com.bcu.bean.Borrow;
import com.bcu.bean.Student;
import com.bcu.dao.BookDao;
import com.bcu.dao.BorrowDao;
import com.bcu.dao.StudentDao;
import com.bcu.model.MenageBorrowModel;

public class MenageBorrowFrame extends JFrame {

	private JPanel contentPane;
	JPanel panel_2 = new JPanel();
	private MenageBorrowModel tableModel;
	private JTable table_1;
	private JTable table;
	private int start;
	private int number = 8;
	private JLabel label;
	private JTextField Bname;
	private JLabel label_1;
	private JButton button_3;
	private JButton button_4;
	private JTextField Snum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenageBorrowFrame frame = new MenageBorrowFrame();
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
	public MenageBorrowFrame() {
		setBounds(300, 250, 863, 614);
		contentPane = new JPanel();
		setTitle("图书借阅记录");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(BorderFactory.createLineBorder(new Color(194,214,232)));
		panel_2.setBounds(29, 37, 777, 50);
		contentPane.add(panel_2);
		
		JLabel label_3 = new JLabel("借阅详情");
		label_3.setBounds(290, 13, 211, 27);
		panel_2.add(label_3);
		label_3.setFont(new Font("方正舒体", Font.PLAIN, 22));
		
		
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(29, 105, 777, 335);
		panel_table.setLayout(null);
		contentPane.add(panel_table);
		
		tableModel = new MenageBorrowModel();
		table = new JTable(tableModel);
		System.out.println(tableModel.getList());
		//设置每列宽占比
		table.getColumnModel().getColumn(0).setPreferredWidth(220);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		//单元格高
		table.setRowHeight(35);
		//单元格居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器  
		tcr.setHorizontalAlignment(JLabel.CENTER);//居中显示  
		table.setDefaultRenderer(Object.class, tcr);//设置渲染器  
		
		//table.setBounds(11, 26, 749, 319);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 775, 319);
		panel_table.add(scrollPane);
		
		label = new JLabel("学生学号：");
		label.setFont(new Font("方正舒体", Font.PLAIN, 22));
		label.setForeground(new Color(0, 153, 255));
		label.setBounds(29, 453, 122, 35);
		contentPane.add(label);
		
		Bname = new JTextField();
		Bname.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Bname.setColumns(10);
		Bname.setBounds(469, 455, 249, 29);
		contentPane.add(Bname);
		
		label_1 = new JLabel("书籍名称：");
		label_1.setForeground(new Color(0, 153, 255));
		label_1.setFont(new Font("方正舒体", Font.PLAIN, 22));
		label_1.setBounds(360, 453, 122, 35);
		contentPane.add(label_1);
		
		button_3 = new JButton("按照学生查询");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int snum = 0;
				if(Snum.getText()!=null){
					snum = Integer.parseInt(Snum.getText());
				}
				Student student = new Student();
				StudentDao sDao = new StudentDao();
				student = sDao.findStudentbyNum(snum);
				if(student==null){
					JOptionPane.showConfirmDialog(null, "没有该学生", "确认对话框", JOptionPane.YES_NO_OPTION);
				}else{
					List<Borrow> blist = tableModel.findbynum(snum);
					tableModel.setList(blist);
					table.updateUI();
				}

			}
		});
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("方正舒体", Font.PLAIN, 19));
		button_3.setBounds(178, 497, 163, 35);
		contentPane.add(button_3);
		
		button_4 = new JButton("书籍查询");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = Bname.getText();
				Book books = new Book();
				BookDao bdao = new BookDao();
				books = bdao.findBookByname(name);
				if(books==null){
					JOptionPane.showConfirmDialog(null, "没有这本书", "确认对话框", JOptionPane.YES_NO_OPTION);
				}else{
					BorrowDao bDao = new BorrowDao();
					List<Borrow> blist =bDao.getBorrowByBid(books.getId());
					tableModel.setList(blist);
					table.updateUI();
				}
			}
		});
		button_4.setForeground(Color.BLACK);
		button_4.setFont(new Font("方正舒体", Font.PLAIN, 19));
		button_4.setBounds(583, 497, 163, 35);
		contentPane.add(button_4);
		
		Snum = new JTextField();
		Snum.setFont(new Font("方正舒体", Font.PLAIN, 21));
		Snum.setColumns(10);
		Snum.setBounds(141, 455, 163, 29);
		contentPane.add(Snum);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dt =new Date();  
		String dateNowStr = simpleDateFormat.format(dt);
		
//		界面加载以后，先判断按钮的状态
		updateTable();
	}
	
	protected void updateTable(){
		List<Borrow> list=new BorrowDao().getBorrow();
		tableModel.setList(list);
//		更新table界面显示
		table.updateUI();
	}

}
