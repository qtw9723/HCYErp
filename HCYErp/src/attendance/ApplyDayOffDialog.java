package attendance;

import java.awt.Font;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

@SuppressWarnings("serial")
public class ApplyDayOffDialog extends JDialog {

	private JComboBox<Integer> jcbStartYear;
	private JComboBox<Integer> jcbStartMonth;
	private JComboBox<Integer> jcbStartDay;
	private JComboBox<Integer> jcbEndYear;
	private JComboBox<Integer> jcbEndMonth;
	private JComboBox<Integer> jcbEndDay;
	private JComboBox<String> jcbReason;
	private JTextArea jtaWriteReason;
	private JButton jbtnApply;
	private JButton jbtnCancel;
	private JLabel jlblPeriod;
	private JLabel jlblDuring;
	private JLabel jlblReason;

	public ApplyDayOffDialog() {
		//Calendar선언
		Calendar cal = Calendar.getInstance();
		//폰트 설정
		Font periodFont=new Font("바탕체",Font.BOLD,15);
		Font duringFont=new Font("궁서체",Font.BOLD,20);
		//콤보박스 선언
		jcbStartYear = new JComboBox<Integer>();
		jcbStartMonth = new JComboBox<Integer>();
		jcbStartDay = new JComboBox<Integer>();
		jcbEndYear = new JComboBox<Integer>();
		jcbEndMonth = new JComboBox<Integer>();
		jcbEndDay = new JComboBox<Integer>();
		jcbReason = new JComboBox<String>();

		// JButton 선언
		jbtnApply = new JButton("신청"); 
		jbtnCancel = new JButton("취소");

		// JLabel 선언
		jlblPeriod = new JLabel("신청기간 : ");
		jlblDuring = new JLabel("~");
		jlblReason = new JLabel("신청사유 : ");

		JLabel jlblStartYear=new JLabel("년");
		JLabel jlblStartMonth=new JLabel("월");
		JLabel jlblStartDay=new JLabel("일");
		JLabel jlblEndYear=new JLabel("년");
		JLabel jlblEndMonth=new JLabel("월");
		JLabel jlblEndDay=new JLabel("일");
		
		//JTextField 선언
		jtaWriteReason=new JTextArea("사유를 입력해주세요");
		jtaWriteReason.setEditable(false);
		
		//폰트설정
		jlblPeriod.setFont(periodFont);
		jlblReason.setFont(periodFont);
		jlblStartYear.setFont(periodFont);
		jlblStartMonth.setFont(periodFont);
		jlblStartDay.setFont(periodFont);
		jlblEndYear.setFont(periodFont);
		jlblEndMonth.setFont(periodFont);
		jlblEndDay.setFont(periodFont);
		jlblDuring.setFont(duringFont);
		
		// 현재 년을 설정
		int year = cal.get(Calendar.YEAR);
		// JComboBox에 값 추가
		for (int i = year; i < year + 2; i++) {
			jcbStartYear.addItem(i);
			jcbEndYear.addItem(i);
		}

		for (int i = 1; i <= 12; i++) {
			jcbStartMonth.addItem(i);
			jcbEndMonth.addItem(i);
		}

		// Month콤보박스 기본값설정
		jcbStartMonth.setSelectedItem(cal.get(Calendar.MONTH) + 1);
		jcbEndMonth.setSelectedItem(cal.get(Calendar.MONTH) + 2);
		jcbStartMonth.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				int selectedIndex = (int) jcbStartMonth.getSelectedItem() - 1;
				jcbStartMonth.setSelectedIndex(selectedIndex);
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {

			}
		});

		jcbEndMonth.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				int selectedIndex = (int) jcbEndMonth.getSelectedItem() - 1;
				jcbEndMonth.setSelectedIndex(selectedIndex);
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {

			}
		});
		// 현재달의 일을 콤보박스에 설정
		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			jcbStartDay.addItem(i);
		}
		
		int nextday=cal.get(Calendar.MONTH)+1;
		// 현재일을 기본값으로 설정
		jcbStartDay.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
		
		
		
		jcbStartDay.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				int selectedIndex = (int) jcbStartDay.getSelectedItem() - 1;
				jcbStartDay.setSelectedIndex(selectedIndex);
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {

			}
		});
		
		//다음달 일수 설정
		cal.set(Calendar.MONTH, nextday);
		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			jcbEndDay.addItem(i);
		}
		jcbEndDay.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
		
		jcbEndDay.addPopupMenuListener(new PopupMenuListener() {

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				int selectedIndex = (int) jcbEndDay.getSelectedItem() - 1;
				jcbEndDay.setSelectedIndex(selectedIndex);
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {

			}
		});
		
		
		//ResonComboBox에 사유 선언
		jcbReason.addItem("연차 유급휴가");
		jcbReason.addItem("경조휴가");
		jcbReason.addItem("공 가");
		jcbReason.addItem("병 가");
		jcbReason.addItem("특별휴가");
		jcbReason.addItem("기타(직접입력)");

		setLayout(null);

		// 컴포넌트 크기 및 위치 설정
		jcbStartYear.setBounds(120, 20, 100, 30);
		jcbStartMonth.setBounds(270, 20, 100, 30);
		jcbStartDay.setBounds(420, 20, 100, 30);
		jcbEndYear.setBounds(170, 100, 100, 30);
		jcbEndMonth.setBounds(320, 100, 100, 30);
		jcbEndDay.setBounds(470, 100, 100, 30);
		jcbReason.setBounds(120, 150, 120, 30);
		
		jlblPeriod.setBounds(35, 20, 100, 30);
		jlblDuring.setBounds(320, 50, 50, 50);
		jlblReason.setBounds(35, 150, 100, 30);
		
		jlblStartYear.setBounds(230, 20, 100, 30);
		jlblStartMonth.setBounds(380, 20, 100, 30);
		jlblStartDay.setBounds(530, 20, 100, 30);
		jlblEndYear.setBounds(280, 100, 100, 30);
		jlblEndMonth.setBounds(430, 100, 100, 30);
		jlblEndDay.setBounds(580, 100, 100, 30);
		
		jtaWriteReason.setBounds(250,150,420,200);
		
		jbtnApply.setBounds(80,200,100,30);
		jbtnCancel.setBounds(80,300,100,30);

		// 컴포넌트 배치
		add(jcbStartYear);
		add(jcbStartMonth);
		add(jcbStartDay);
		add(jcbEndYear);
		add(jcbEndMonth);
		add(jcbEndDay);
		add(jcbReason);
		
		add(jlblPeriod);
		add(jlblDuring);
		add(jlblReason);
		add(jlblStartYear);
		add(jlblStartMonth);
		add(jlblStartDay);
		add(jlblEndYear);
		add(jlblEndMonth);
		add(jlblEndDay);
		
		add(jtaWriteReason);
		
		add(jbtnApply);
		add(jbtnCancel);
		
		ApplyDayOffDialogEvt adode=new ApplyDayOffDialogEvt(this);
		jcbReason.addActionListener(adode);
		jcbStartMonth.addActionListener(adode);
		jcbStartDay.addActionListener(adode);
		jcbEndMonth.addActionListener(adode);
		
		
		setBounds(100, 100, 800, 400);
		setVisible(true);
		
	}// constructor

	public JComboBox<Integer> getJcbStartYear() {
		return jcbStartYear;
	}

	public JComboBox<Integer> getJcbStartMonth() {
		return jcbStartMonth;
	}

	public JComboBox<Integer> getJcbStartDay() {
		return jcbStartDay;
	}

	public JComboBox<Integer> getJcbEndYear() {
		return jcbEndYear;
	}

	public JComboBox<Integer> getJcbEndMonth() {
		return jcbEndMonth;
	}

	public JComboBox<Integer> getJcbEndDay() {
		return jcbEndDay;
	}

	public JComboBox<String> getJcbReason() {
		return jcbReason;
	}

	public JTextArea getJtaWriteReason() {
		return jtaWriteReason;
	}

	public JButton getJbtnApply() {
		return jbtnApply;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public JLabel getJlblPeriod() {
		return jlblPeriod;
	}

	public JLabel getJlblDuring() {
		return jlblDuring;
	}

	public JLabel getJlblReason() {
		return jlblReason;
	}

	public static void main(String[] arg) {
		new ApplyDayOffDialog();
	}

}// class
