package com.bcu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bcu.bean.Book;
import com.bcu.bean.Borrow;
import com.bcu.bean.Collection;
import com.bcu.bean.Student;
import com.bcu.dao.BookDao;
import com.bcu.dao.BorrowDao;
import com.bcu.dao.CollectionDao;
import com.bcu.dao.StudentDao;
import com.bcu.model.BookTableModel;
import com.bcu.model.StudentTableModel;
import com.bcu.utils.StringUtil;
import com.bcu.utils.Time;

public class MenageBookFrame extends JFrame {

	private JPanel contentPane;
	JPanel panel_2 = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private BookTableModel tableModel;
	private JTable table_1;
	private JTable table;
	private JTextField textField_2;
	private JTextField Bname;
	private JTextField Bauthor;
	private JTextField Bcode;
	private JTextField Bpress;
	private JTextField Bwhere;
	private List<Book> findlist;
	private JTextField Snumber;
	private JTextField Btime;
	private JTextField Btype;
	/**
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenageBookFrame frame = new MenageBookFrame();
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
	public MenageBookFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 250, 863, 638);
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
		
		JLabel label_6 = new JLabel("图书类型：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		label_6.setBounds(473, 34, 97, 26);
		panel_2.add(label_6);
		
		JButton button_7 = new JButton("查询");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Book book = new Book();
				
				book.setBookName(textField.getText());
				book.setBookAuthor(textField_1.getText());
				book.setBookType(textField_2.getText());
				
				BookDao dao = new BookDao();
				findlist = dao.findbook(book);
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
		
		tableModel = new BookTableModel();
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
		
		JLabel label = new JLabel("图书名称:");
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label.setBounds(29, 397, 99, 41);
		contentPane.add(label);
		
		Bname = new JTextField();
		Bname.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Bname.setBounds(127, 405, 137, 27);
		contentPane.add(Bname);
		Bname.setColumns(10);
		
		JLabel label_1 = new JLabel("图书作者:");
		label_1.setForeground(new Color(0, 153, 255));
		label_1.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_1.setBounds(29, 445, 99, 41);
		contentPane.add(label_1);
		
		Bauthor = new JTextField();
		Bauthor.setFont(new Font("图书编号", Font.PLAIN, 19));
		Bauthor.setColumns(10);
		Bauthor.setBounds(127, 453, 137, 27);
		contentPane.add(Bauthor);
		
		JLabel label_8 = new JLabel("图书编号:");
		label_8.setForeground(new Color(0, 153, 255));
		label_8.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_8.setBounds(29, 493, 99, 41);
		contentPane.add(label_8);
		
		Bcode = new JTextField();
		Bcode.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Bcode.setColumns(10);
		Bcode.setBounds(127, 501, 137, 27);
		contentPane.add(Bcode);
		
		JLabel label_9 = new JLabel("出版社:");
		label_9.setForeground(new Color(0, 153, 255));
		label_9.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_9.setBounds(317, 445, 99, 41);
		contentPane.add(label_9);
		
		Bpress = new JTextField();
		Bpress.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Bpress.setColumns(10);
		Bpress.setBounds(404, 453, 137, 27);
		contentPane.add(Bpress);
		
		JLabel label_10 = new JLabel("所在书架:");
		label_10.setForeground(new Color(0, 153, 255));
		label_10.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_10.setBounds(303, 493, 99, 41);
		contentPane.add(label_10);
		
		Bwhere = new JTextField();
		Bwhere.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Bwhere.setColumns(10);
		Bwhere.setBounds(404, 501, 137, 27);
		contentPane.add(Bwhere);
		
		JButton button = new JButton("增加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookDao bdao = new BookDao();
				Book book = new Book();
				int num=Integer.parseInt(Bcode.getText());
				book=bdao.findBookBycode(num);
				if(book==null){
					Book addbook = getBooks();
					bdao.addbook(addbook);
					updateTable();
				}else{
					int n = JOptionPane.showConfirmDialog(null, "该图书编号已存在\n不能重复添加", "温馨提示", JOptionPane.YES_OPTION);
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
					Book newbook = getBooks();
					tableModel.updateBook(index, newbook);
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
		
		JLabel label_13 = new JLabel("库存:");
		label_13.setForeground(new Color(0, 153, 255));
		label_13.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_13.setBounds(327, 537, 99, 41);
		contentPane.add(label_13);
		
		Snumber = new JTextField();
		Snumber.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Snumber.setColumns(10);
		Snumber.setBounds(399, 545, 137, 27);
		contentPane.add(Snumber);
		
		JLabel label_14 = new JLabel("出版日期:");
		label_14.setForeground(new Color(0, 153, 255));
		label_14.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_14.setBounds(303, 397, 99, 41);
		contentPane.add(label_14);
		
		Btime = new JTextField();
		Btime.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Btime.setColumns(10);
		Btime.setBounds(401, 405, 137, 27);
		contentPane.add(Btime);
		
		JLabel label_15 = new JLabel("图书类型:");
		label_15.setForeground(new Color(0, 153, 255));
		label_15.setFont(new Font("方正舒体", Font.PLAIN, 21));
		label_15.setBounds(29, 547, 99, 41);
		contentPane.add(label_15);
		
		Btype = new JTextField();
		Btype.setFont(new Font("方正舒体", Font.PLAIN, 19));
		Btype.setColumns(10);
		Btype.setBounds(127, 555, 137, 27);
		contentPane.add(Btype);
		
		updateTable();//先加载一下数据
	}
	protected void updateTable(){
		
		List<Book> list = new BookDao().getBooks();
		tableModel.setList(list);
//		更新table界面显示
		table.updateUI();
	}
	protected Book getBooks(){
		Book book = new Book();
		book.setBookAuthor(Bauthor.getText());
		book.setBookCode(Bcode.getText());
		book.setBookName(Bname.getText());
		book.setBookWhere(Bwhere.getText());
		book.setBookType(Btype.getText());
		System.out.println("number"+Integer.parseInt(Snumber.getText()));
		book.setNumber(Integer.parseInt(Snumber.getText()));
		book.setPress(Bpress.getText());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date=simpleDateFormat.parse(Btime.getText());
			Date intime = new Date(date.getTime());
			book.setInTime(intime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return book;
		
	}

}
