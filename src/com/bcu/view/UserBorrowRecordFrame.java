package com.bcu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bcu.bean.Borrow;
import com.bcu.dao.BookDao;
import com.bcu.dao.BorrowDao;
import com.bcu.model.BorrowTableModel;

public class UserBorrowRecordFrame extends JFrame {


	private JPanel contentPane;
	JPanel panel_2 = new JPanel();
	private BorrowTableModel tableModel;
	private JTable table_1;
	private JTable table;
	private int start;
	private int number = 8;
	private JButton button;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserBorrowRecordFrame frame = new UserBorrowRecordFrame();
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
	public UserBorrowRecordFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel label_3 = new JLabel("我借阅过的书籍");
		
		label_3.setBounds(14, 13, 211, 27);
		panel_2.add(label_3);
		label_3.setFont(new Font("方正舒体", Font.PLAIN, 22));
		
		
		
		JPanel panel_table = new JPanel();
		panel_table.setBounds(29, 105, 777, 360);
		panel_table.setLayout(null);
		contentPane.add(panel_table);
		
		tableModel = new BorrowTableModel();
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
		scrollPane.setBounds(2, 3, 775, 350);
		panel_table.add(scrollPane);
		
		button = new JButton("借阅记录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowDao bdao =new BorrowDao();
				start=0;number=8;
				tableModel.setList(bdao.getBorrow());
				updateTable();
				label_3.setText("我借阅过的书籍");
				label_3.setForeground(new Color(0, 0, 0));
				
			}
		});
		button.setForeground(new Color(0, 153, 255));
		button.setFont(new Font("方正舒体", Font.PLAIN, 20));
		button.setBounds(274, 483, 123, 43);
		contentPane.add(button);
		
		button_1 = new JButton("未还书籍");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowDao bdao =new BorrowDao();
				start=0;number=8;
				tableModel.setList(bdao.getBorrowNocount(Login_student.Userid,start, number));
				table.updateUI();
				label_3.setText("未归还书籍");
				label_3.setForeground(new Color(255, 102, 51));
			}
		});
		button_1.setForeground(new Color(255, 102, 51));
		button_1.setFont(new Font("方正舒体", Font.PLAIN, 20));
		button_1.setBounds(478, 482, 132, 44);
		contentPane.add(button_1);
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
		List<Borrow> list = new BorrowDao().getBorrowByState(Login_student.Userid, start, number);
		tableModel.setList(list);
//		更新table界面显示
		table.updateUI();
	}
}
