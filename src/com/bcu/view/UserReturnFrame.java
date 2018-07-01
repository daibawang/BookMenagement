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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bcu.bean.Book;
import com.bcu.bean.Borrow;
import com.bcu.dao.BookDao;
import com.bcu.dao.BorrowDao;
import com.bcu.model.BookTableModel;
import com.bcu.model.BorrowTableModel;
import com.bcu.utils.StringUtil;

public class UserReturnFrame extends JFrame {

	private JPanel contentPane;
	JPanel panel_2 = new JPanel();
	private BorrowTableModel tableModel;
	private JTable table_1;
	private JTable table;
	private int start;
	private int number = 8;
	private JButton first; 
	private JButton next;
	private JButton previous;
	private JButton last;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserReturnFrame frame = new UserReturnFrame();
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
	public UserReturnFrame() {
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
		
		first = new JButton("首页");
		first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = 0;
				//调用dao新的获取数据
				updateTable();
				updateButton();
			}
		});
		first.setFont(new Font("方正舒体", Font.PLAIN, 17));
		first.setBounds(251, 488, 101, 34);
		contentPane.add(first);
		
		next = new JButton("下一页");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start+=number;
				updateTable();
				updateButton();
				
			}
		});
		next.setFont(new Font("方正舒体", Font.PLAIN, 17));
		next.setBounds(396, 488, 101, 34);
		contentPane.add(next);
		
		previous = new JButton("上一页");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start-=number;
				updateTable();
				updateButton();
			}
		});
		previous.setFont(new Font("方正舒体", Font.PLAIN, 17));
		previous.setBounds(537, 488, 101, 34);
		contentPane.add(previous, BorderLayout.SOUTH);
		
		last = new JButton("尾页");
		last.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = last();
				updateTable();
				updateButton();
			}
		});
		last.setFont(new Font("方正舒体", Font.PLAIN, 17));
		last.setBounds(674, 488, 101, 34);
		contentPane.add(last, BorderLayout.SOUTH);
		
		
		
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
		
		button = new JButton("归还书籍");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index != -1){
					if(tableModel.isreturn(index)){
						int n = JOptionPane.showConfirmDialog(null, "这本书已经归还?", "温馨提示", JOptionPane.YES_OPTION);
					}else{
						tableModel.returnbook(index);
						updateTable();
						//库存加1
						tableModel.addBoonNume(index);
					}
					
				}
			}
		});
		button.setForeground(new Color(0, 153, 255));
		button.setFont(new Font("方正舒体", Font.PLAIN, 20));
		button.setBounds(61, 483, 121, 39);
		contentPane.add(button);
		
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
	
	protected void stopAllButton() {
		first.setEnabled(false);
		previous.setEnabled(false);
		last.setEnabled(false);
		next.setEnabled(false);
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
