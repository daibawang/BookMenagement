package com.bcu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bcu.bean.Book;
import com.bcu.bean.Borrow;
import com.bcu.bean.Collection;
import com.bcu.dao.BookDao;
import com.bcu.dao.BorrowDao;
import com.bcu.dao.CollectionDao;
import com.bcu.model.BookTableModel;
import com.bcu.utils.StringUtil;
import com.bcu.utils.Time;

	public class UserBorrowFrame extends JFrame {
		
		private JPanel contentPane;
		JPanel panel_2 = new JPanel();
		private JTextField textField;
		private JTextField textField_1;
		private BookTableModel tableModel;
		private JTable table_1;
		private JTable table;
		private int start;
		private int number = 8;
		private JButton first; 
		private JButton next;
		private JButton previous;
		private JButton last;
		private JTextField textField_2;
		private List<Book> findlist;
		private List<Collection> collections=null;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						UserBorrowFrame frame = new UserBorrowFrame();
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
		public UserBorrowFrame() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 863, 614);
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
			
			JButton button_7 = new JButton("查询");
			button_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Book sbooks = new Book();
					sbooks.setBookName(textField.getText());
					sbooks.setBookAuthor(textField_1.getText());
					sbooks.setBookType(textField_2.getText());
//					System.out.println(sbooks);
					BookDao dao = new BookDao();
					findlist = dao.findbook(sbooks);
					tableModel.setList(findlist);
					if(StringUtil.isNotEmpty(sbooks.getBookAuthor())||StringUtil.isNotEmpty(sbooks.getBookName())||StringUtil.isNotEmpty(sbooks.getBookType())){
						stopAllButton();
					}else{
						updateTable();
						updateButton();
					}
//					更新table界面显示
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
			first.setBounds(360, 495, 101, 27);
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
			next.setBounds(475, 495, 101, 27);
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
			previous.setBounds(590, 495, 101, 27);
			contentPane.add(previous);
			
			last = new JButton("尾页");
			last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					start = last();
					updateTable();
					updateButton();
				}
			});
			last.setFont(new Font("方正舒体", Font.PLAIN, 17));
			last.setBounds(705, 495, 101, 27);
			contentPane.add(last, BorderLayout.SOUTH);
			
			
			
			JPanel panel_table = new JPanel();
			panel_table.setBounds(29, 137, 777, 345);
			panel_table.setLayout(null);
			contentPane.add(panel_table);
			
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
			
			JButton button = new JButton("借阅此书");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//获取到当前选中的行
					int index = table.getSelectedRow();
					//点击书才能继续操作
					if(index != -1){
//						System.out.println(index);
						BookDao dao = new BookDao();
						Book thisbook=null;//选择的书的书
						Book findbook=null;//要借的书
						//有查询到书籍
						if(findlist!=null){
							thisbook= findlist.get(index); //搜索出来的书
						}else{
							thisbook=tableModel.getList().get(index); //显示的列表中的书
							}
						findbook = dao.findbookbyid(thisbook.getId());
						System.out.println(findbook);
						String name=findbook.getBookName();
						if(findbook.getNumber()==0){
							int m = JOptionPane.showConfirmDialog(null, name +"库存为0~暂时不可借阅。\n\t\t您可以选择收藏此书", "", JOptionPane.YES_NO_OPTION);
							if(m == JOptionPane.YES_OPTION) {
								Collectionbook(findbook);
								JOptionPane.showMessageDialog(new JFrame(),"收藏成功");
								
							}else if (m== JOptionPane.NO_OPTION) {
							 JOptionPane.showMessageDialog(new JFrame(),"已取消借阅");
							}
						}else{
							int n = JOptionPane.showConfirmDialog(null, "确认借阅这本书吗?", "", JOptionPane.YES_NO_OPTION);
							if (n == JOptionPane.YES_OPTION) { 
								JOptionPane.showMessageDialog(new JFrame(),"借阅成功");
								//借书操作
								Borrowbook(findbook,thisbook);
								updateTable();
							} else if (n == JOptionPane.NO_OPTION) { 
								JOptionPane.showMessageDialog(new JFrame(),"已取消借阅");
							}
						}
						
						
					}
					
				}
			});
			button.setFont(new Font("方正舒体", Font.PLAIN, 21));
			button.setForeground(new Color(0, 0, 255));
			button.setBounds(52, 490, 121, 39);
			contentPane.add(button);
			
			JButton button_1 = new JButton("收藏此书");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					//获取到当前选中的行
					int index = table.getSelectedRow();
					//点击书才能继续操作
					if(index != -1){
//						System.out.println(index);
						BookDao dao = new BookDao();
						Book thisbook=null;//选择的书的书
						Book findbook=null;//要收藏的书
						//有查询到书籍
						if(findlist!=null){
							thisbook= findlist.get(index); //搜索出来的书
						}else{
							thisbook=tableModel.getList().get(index); //显示的列表中的书
							}
						findbook = dao.findbookbyid(thisbook.getId());
//						System.out.println(findbook);
						String name=findbook.getBookName();
						CollectionDao cdao = new  CollectionDao();
						collections =cdao.getCollection(Login_student.Userid,findbook.getId());
						System.out.println(collections);
						if(collections.size()==0){
							int m = JOptionPane.showConfirmDialog(null, "确认收藏"+ name +"这本书吗？", "", JOptionPane.YES_NO_OPTION);
							if(m == JOptionPane.YES_OPTION) {
								Collectionbook(findbook);
								JOptionPane.showMessageDialog(new JFrame(),"收藏成功");
								
							}else if (m== JOptionPane.NO_OPTION) {
							 JOptionPane.showMessageDialog(new JFrame(),"已取消收藏");
							}
						}else{
							int n = JOptionPane.showConfirmDialog(null, "您已经收藏过此书。不能重复收藏", "", JOptionPane.YES_OPTION);
							
						}
						
						
					}
					
				
				}
			});
			button_1.setFont(new Font("方正舒体", Font.PLAIN, 21));
			button_1.setForeground(new Color(255, 102, 0));
			button_1.setBounds(199, 490, 121, 39);
			contentPane.add(button_1);
			
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
//			计算最后一页的起始值
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
//			更新table界面显示
			table.updateUI();
		}
		//收藏书
		public void Collectionbook(Book findbook){
			CollectionDao cdao = new  CollectionDao();
			Collection cbook = new Collection();
			cbook.setBookid(findbook.getId());
			cbook.setStudentid(Login_student.Userid);
			cdao.add(cbook);	
		}
		//借书
		public void Borrowbook(Book findbook,Book thisbook){
			BorrowDao bdao = new BorrowDao();
			Borrow bbook = new Borrow();
			bbook.setBookid(findbook.getId());
			bbook.setStudentid(Login_student.Userid);
			//插入当前时间
			try {
				bbook.setBorrowTime(Time.getNowTime());
				System.out.println(Time.getNowTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bbook.setBookCode(findbook.getBookCode());
			bbook.setBookName(findbook.getBookName());
			bbook.setBookType(findbook.getBookType());
			bbook.setPress(findbook.getPress());
			bbook.setReturnTime(null);
			bdao.add(bbook);	
			//数量减1
			BookDao dao = new BookDao();
			dao.changeBookNum(thisbook.getId(),findbook.getNumber()-1);
		}
		
	}
