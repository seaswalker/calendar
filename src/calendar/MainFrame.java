package calendar;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import util.CalendarUtil;
import util.FrameUtil;

/**
 * 主界面
 * @author skywalker
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -7966889788221625912L;
	private JPanel contentPane;
	/**结果显示区域**/
	private JTextArea content;
	/**选择的值**/
	private JComboBox<Integer> year;
	private JComboBox<Integer> month;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		try {
			//windows样式
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/calendar/calendar.png")));
		setTitle("\u4E07\u5E74\u5386");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//剧中
		Point point = FrameUtil.getMiddlePoint(450, 300);
		setBounds(point.x, point.y, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u5E74\u4EFD:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(34, 32, 98, 24);
		contentPane.add(lblNewLabel);
		
		//1900-2100年
		Integer []years = new Integer[200];
		for(int i = 0;i < 200;i ++) {
			years[i] = 1900 + i;
		}
		
		Calendar calendar = Calendar.getInstance();
		int currectYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		
		//年份选择框
		year = new JComboBox<Integer>(years);
		year.setFont(new Font("宋体", Font.PLAIN, 14));
		year.setBounds(120, 34, 60, 21);
		year.setSelectedItem(currectYear);
		contentPane.add(year);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u9009\u62E9\u6708\u4EFD:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(200, 35, 77, 19);
		contentPane.add(lblNewLabel_1);
		
		//月份
		Integer []months = new Integer[12];
		for(int i = 0;i < 12;i ++) {
			months[i] = i + 1;
		}
		
		//月份选择框
		month = new JComboBox<Integer>(months);
		month.setFont(new Font("宋体", Font.PLAIN, 14));
		month.setBounds(285, 34, 40, 21);
		month.setSelectedItem(currentMonth);
		contentPane.add(month);
		
		//结果显示区
		JPanel result = new JPanel();
		result.setBounds(34, 82, 372, 166);
		contentPane.add(result);
		content = new JTextArea();
		content.setEditable(false);
		content.setFont(new Font("宋体", Font.PLAIN, 17));
		content.setSize(370, 150);
		content.setText(CalendarUtil.getCalendar(currectYear, currentMonth + 1));
		result.add(content);
		
		
		//确定按钮
		JButton confirm = new JButton("确定");
		confirm.setBounds(340, 34, 60, 21);
		//绑定事件
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取选中的值
				int selectYear = (int) year.getSelectedItem();
				int selectMonth = (int) month.getSelectedItem();
				content.setText(CalendarUtil.getCalendar(selectYear, selectMonth));
			}
		});
		contentPane.add(confirm);
	}
}
