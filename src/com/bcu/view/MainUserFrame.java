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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bcu.bean.Book;
import com.bcu.dao.BookDao;
import com.bcu.model.BookTableModel;

public class MainUserFrame extends JFrame {
	
	private JPanel contentPane;
	JPanel pRight = new JPanel();
	JPanel pLeft = new JPanel();
	private final JPanel panel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private String[] type = {"艺术" , "小说", "童话"};  
	private BookTableModel tableModel;
	private JTable table_1;
	private JTable table;
	private int start;
	private int number = 8;
	private JButton first; 
	private JButton next;
	private JButton previous;
	private JButton last;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUserFrame frame = new MainUserFrame();
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
	public MainUserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1044, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
		splitPane.setDividerLocation(190);
		pLeft.setLayout(null);
		panel.setBounds(13, 26, 161, 198);
		pLeft.add(panel);
		contentPane.add(splitPane, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createLineBorder(new Color(194,214,232)));
		panel.setLayout(null);
		
		JLabel label = new JLabel("图书查询与借阅");
		label.setFont(new Font("方正舒体", Font.PLAIN, 18));
		label.setBounds(14, 13, 137, 27);
		panel.add(label);
		
		JButton button = new JButton("图书查询");
		button.setFont(new Font("方正舒体", Font.PLAIN, 15));
		button.setBounds(14, 53, 133, 33);
		button.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/搜索2.png")));
		panel.add(button);
		
		JButton button_1 = new JButton("图书借阅");
		button_1.setFont(new Font("方正舒体", Font.PLAIN, 15));
		button_1.setBounds(14, 99, 133, 34);
		button_1.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/借出.png")));
		panel.add(button_1);
		
		JButton button_2 = new JButton("图书归还");
		button_2.setFont(new Font("方正舒体", Font.PLAIN, 15));
		button_2.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/借入.png")));
		button_2.setBounds(14, 151, 133, 34);
		panel.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(BorderFactory.createLineBorder(new Color(194,214,232)));
		panel_1.setBounds(14, 252, 161, 198);
		pLeft.add(panel_1);
		
		JLabel label_1 = new JLabel("个人信息中心");
		label_1.setFont(new Font("方正舒体", Font.PLAIN, 18));
		label_1.setBounds(14, 13, 133, 27);
		panel_1.add(label_1);
		
		JButton button_3 = new JButton("个人信息");
		button_3.setFont(new Font("方正舒体", Font.PLAIN, 15));
		button_3.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/个人信息.png")));
		button_3.setBounds(14, 53, 133, 34);
		panel_1.add(button_3);
		
		JButton button_4 = new JButton("借阅记录");
		button_4.setFont(new Font("方正舒体", Font.PLAIN, 15));
		button_4.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/记录.png")));
		button_4.setBounds(14, 100, 133, 38);
		panel_1.add(button_4);
		
		JButton button_5 = new JButton("我的收藏");
		button_5.setFont(new Font("方正舒体", Font.PLAIN, 15));
		button_5.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/收 藏.png")));
		button_5.setBounds(14, 151, 133, 34);
		panel_1.add(button_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(BorderFactory.createLineBorder(new Color(194,214,232)));
		panel_3.setBounds(15, 460, 160, 72);
		pLeft.add(panel_3);
		
		JButton button_8 = new JButton("我的收藏");
		button_8.setFont(new Font("方正舒体", Font.PLAIN, 15));
		button_8.setBounds(14, 151, 133, 34);
		panel_3.add(button_8);
		
		JButton button_6 = new JButton("退出系统");
		button_6.setFont(new Font("方正舒体", Font.PLAIN, 15));
		button_6.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/退出 (2).png")));
		button_6.setBounds(14, 19, 133, 34);
		panel_3.add(button_6);
		
		pRight.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(BorderFactory.createLineBorder(new Color(194,214,232)));
		panel_2.setBounds(29, 37, 777, 87);
		pRight.add(panel_2);
		
		JLabel label_3 = new JLabel("条件");
		label_3.setBounds(14, 0, 137, 27);
		panel_2.add(label_3);
		label_3.setFont(new Font("方正舒体", Font.PLAIN, 18));
		
		JLabel label_4 = new JLabel("图书名称：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_4.setBounds(24, 34, 97, 26);
		panel_2.add(label_4);
		
		textField = new JTextField();
		textField.setBounds(115, 37, 118, 24);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel label_5 = new JLabel("图书作者：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_5.setBounds(247, 34, 97, 26);
		panel_2.add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(334, 37, 118, 24);
		panel_2.add(textField_1);
		
		JLabel label_6 = new JLabel("图书类别：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_6.setBounds(473, 34, 97, 26);
		panel_2.add(label_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(558, 37, 91, 24);
		for(int i=0; i<type.length;i++){
			comboBox.addItem(type[i]);
		}
		
		panel_2.add(comboBox);
		
		JButton button_7 = new JButton("搜索");

		button_7.setBounds(672, 30, 91, 38);
		button_7.setIcon(new ImageIcon(LoginFrame2.class.getResource("/images/搜索探讨.png")));
		panel_2.add(button_7);
		
		
		
		JLabel label_2 = new JLabel("搜索");
		label_2.setBounds(29, 13, 137, 27);
		pRight.add(label_2);
		label_2.setFont(new Font("方正舒体", Font.PLAIN, 18));
		
		first = new JButton("第一页");
		first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = 0;
				//调用dao新的获取数据
				updateTable();
				updateButton();
			}
		});
		first.setFont(new Font("方正舒体", Font.PLAIN, 17));
		first.setBounds(143, 495, 101, 27);
		pRight.add(first);
		
		next = new JButton("下一页");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start+=number;
				updateTable();
				updateButton();
				
			}
		});
		next.setFont(new Font("方正舒体", Font.PLAIN, 17));
		next.setBounds(281, 495, 101, 27);
		pRight.add(next);
		
		previous = new JButton("上一页");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start-=number;
				updateTable();
				updateButton();
			}
		});
		previous.setFont(new Font("方正舒体", Font.PLAIN, 17));
		previous.setBounds(420, 495, 101, 27);
		pRight.add(previous);
		
		last = new JButton("最后一页");
		last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = last();
				updateTable();
				updateButton();
			}
		});
		last.setFont(new Font("方正舒体", Font.PLAIN, 17));
		last.setBounds(563, 495, 101, 27);
		pRight.add(last);
		
		
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(29, 137, 777, 345);
		panel_table.setLayout(null);
		pRight.add(panel_table);
		
		tableModel = new BookTableModel();
		table = new JTable(tableModel);
		//设置每列宽占比
		table.getColumnModel().getColumn(0).setPreferredWidth(220);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(155);
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
		scrollPane.setBounds(2, 3, 775, 319);
		panel_table.add(scrollPane);
		
//		tableModel = new BookTableModel();
//		table_1 = new JTable(tableModel);
//		//JScrollPane显示表头
//		JScrollPane scrollPane = new JScrollPane(table_1);
//		panel_table.add(scrollPane,BorderLayout.CENTER);
//		table_1.setBounds(0, 0, 777, 332);
		
//		界面加载以后，先判断按钮的状态
		updateButton();
	}
	//判断按钮是否禁用
	protected void updateButton() {
		// TODO Auto-generated method stub
		if (start == 0) {
			first.setEnabled(false);
			previous.setEnabled(false);
		}else{
			first.setEnabled(true);
			previous.setEnabled(true);
		}
		
		if(start == last()){
			last.setEnabled(false);
			next.setEnabled(false);
		}else {
			last.setEnabled(true);
			next.setEnabled(true);
		}
	}

	protected int last() {
		// TODO Auto-generated method stub
//		计算最后一页的起始值
		int last =0;
		int total = new BookDao().getCount();
		if(total %number ==0){
			last = total-number;
		}else{
			last = total-total%number;
		}
		return last;
	}

	protected void updateTable(){
		List<Book> list = new BookDao().getBooks(start, number);
		tableModel.setList(list);
//		更新table界面显示
		table.updateUI();
	}
}
