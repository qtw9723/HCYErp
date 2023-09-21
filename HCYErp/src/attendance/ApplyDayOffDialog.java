package attendance;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ApplyDayOffDialog extends JDialog {

	private JComboBox<Integer> jcbStartMonth;
	private JComboBox<Integer> jcbStartYear;
	private JComboBox<Integer> jcbStartDay;
	private JComboBox<Integer> jcbEndMonth;
	private JComboBox<Integer> jcbEndYear;
	private JComboBox<Integer> jcbEndDay;
	private JComboBox<String> jcbReason;
	private JTextArea jtaWriteReason;
	private JButton jbtnCancel;
	private JButton jbtnApply;
	private JLabel jlblPeriod;
	private JLabel jlblDuring;
	private JLabel jlblReason;
	private Attendance ad;

	public ApplyDayOffDialog(Attendance ad) {
		this.ad = ad;
		
		//다이얼로그 배경색
		getContentPane().setBackground(new Color(255,245,245));
				
		//Calendar선언
		Calendar cal = Calendar.getInstance();
		
		//콤보박스 선언
		jcbStartMonth = new JComboBox<Integer>();
		jcbStartYear = new JComboBox<Integer>();
		jcbStartDay = new JComboBox<Integer>();
		jcbEndMonth = new JComboBox<Integer>();
		jcbEndYear = new JComboBox<Integer>();
		jcbEndDay = new JComboBox<Integer>();
		jcbReason = new JComboBox<String>();

		// JButton 선언
		jbtnCancel = new JButton("취소");
		jbtnApply = new JButton("신청"); 

		// JLabel 선언
		jlblPeriod = new JLabel("신청기간 : ");
		jlblReason = new JLabel("신청사유 : ");
		jlblDuring = new JLabel("~");

		JLabel jlblStartYear=new JLabel("년");
		JLabel jlblStartMonth=new JLabel("월");
		JLabel jlblStartDay=new JLabel("일");
		JLabel jlblEndYear=new JLabel("년");
		JLabel jlblEndMonth=new JLabel("월");
		JLabel jlblEndDay=new JLabel("일");
		
		//JTextField 선언
		jtaWriteReason=new JTextArea("사유를 입력해주세요");
		jtaWriteReason.setEditable(false);
		
		//폰트
		Font jcbFont = new Font("맑은 고딕", Font.PLAIN, 15);
		Font jlblFont = new Font("맑은 고딕", Font.BOLD, 15);
		Font jtaFont = new Font("맑은 고딕", Font.PLAIN, 14);
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		
		//폰트설정
		jlblStartMonth.setFont(jlblFont);
		jlblStartYear.setFont(jlblFont);
		jlblStartDay.setFont(jlblFont);
		jlblEndMonth.setFont(jlblFont);
		jlblEndYear.setFont(jlblFont);
		jlblPeriod.setFont(jlblFont);
		jlblReason.setFont(jlblFont);
		jlblEndDay.setFont(jlblFont);
		jlblDuring.setFont(jlblFont);
		
		jcbStartYear.setFont(jcbFont);
		jcbStartMonth.setFont(jcbFont);
		jcbStartDay.setFont(jcbFont);
		jcbEndYear.setFont(jcbFont);
		jcbEndMonth.setFont(jcbFont);
		jcbEndDay.setFont(jcbFont);
		jcbReason.setFont(jcbFont);
		
		jbtnApply.setFont(jbtnFont);
		jbtnCancel.setFont(jbtnFont);
		
		jtaWriteReason.setFont(jtaFont);
		
		//콤보박스 배경색
		jcbStartYear.setBackground(Color.white);
		jcbStartMonth.setBackground(Color.white);
		jcbStartDay.setBackground(Color.white);
		jcbEndYear.setBackground(Color.white);
		jcbEndMonth.setBackground(Color.white);
		jcbEndDay.setBackground(Color.white);
		jcbReason.setBackground(Color.white);
		
		// 현재 년을 설정
		int year = cal.get(Calendar.YEAR);
		// 연도 콤보박스 생성
		for (int i = year; i < year + 2; i++) {
			jcbStartYear.addItem(i);
			jcbEndYear.addItem(i);
		}//for
		
		//월 콤보박스 생성
		for (int i = 1; i <= 12; i++) {
			jcbStartMonth.addItem(i);
			jcbEndMonth.addItem(i);
		}//for

		// Month콤보박스 기본값설정
		jcbStartMonth.setSelectedItem(cal.get(Calendar.MONTH) + 1);
		jcbEndMonth.setSelectedItem(cal.get(Calendar.MONTH) + 2);

		// 현재달의 일을 콤보박스에 설정
		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			jcbStartDay.addItem(i);
		}//for
		int nextday=cal.get(Calendar.MONTH)+1;
		
		// 현재일을 기본값으로 설정
		jcbStartDay.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
		
		//다음달 일수 설정
		cal.set(Calendar.MONTH, nextday);
		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			jcbEndDay.addItem(i);
		}//for
		jcbEndDay.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
		
		//ResonComboBox에 사유 선언
		jcbReason.addItem("연차 유급휴가");
		jcbReason.addItem("경조휴가");
		jcbReason.addItem("공 가");
		jcbReason.addItem("병 가");
		jcbReason.addItem("특별휴가");
		jcbReason.addItem("기타(직접입력)");
		
		//레이아웃 설정
		setLayout(null);

		//버튼색
		jbtnApply.setBackground(new Color(0x6D47B0));
		jbtnCancel.setBackground(new Color(0x919191));
		jbtnApply.setForeground(Color.white);
		jbtnCancel.setForeground(Color.white);
		
		// 컴포넌트 크기 및 위치 설정
		//콤박
		jcbStartYear.setBounds(115, 20, 70, 30);
		jcbStartMonth.setBounds(220, 20, 50, 30);
		jcbStartDay.setBounds(305, 20, 50, 30);
		jcbEndYear.setBounds(425, 20, 70, 30);
		jcbEndMonth.setBounds(530, 20, 50, 30);
		jcbEndDay.setBounds(615, 20, 50, 30);
		jcbReason.setBounds(115, 70, 170, 30);
		//라벨
		jlblPeriod.setBounds(35, 20, 100, 30);
		jlblDuring.setBounds(390, 20, 50, 30);
		jlblReason.setBounds(35, 70, 100, 30);
		
		jlblStartYear.setBounds(190, 20, 100, 30);
		jlblStartMonth.setBounds(275, 20, 100, 30);
		jlblStartDay.setBounds(360, 20, 40, 30);
		jlblEndYear.setBounds(500, 20, 100, 30);
		jlblEndMonth.setBounds(585, 20, 100, 30);
		jlblEndDay.setBounds(670, 20, 100, 30);
		//텍스트에리어
		jtaWriteReason.setBounds(115,120,420,200);
		//버튼
		jbtnApply.setBounds(565,220,125,40);
		jbtnCancel.setBounds(565,280,125,40);

		// 컴포넌트 배치
		//콤박
		add(jcbStartYear);
		add(jcbStartMonth);
		add(jcbStartDay);
		add(jcbEndYear);
		add(jcbEndMonth);
		add(jcbEndDay);
		add(jcbReason);
		//라벨
		add(jlblPeriod);
		add(jlblDuring);
		add(jlblReason);
		add(jlblStartYear);
		add(jlblStartMonth);
		add(jlblStartDay);
		add(jlblEndYear);
		add(jlblEndMonth);
		add(jlblEndDay);
		//텍스트에리어
		add(jtaWriteReason);
		//버튼
		add(jbtnApply);
		add(jbtnCancel);
		
		//이벤트 등록
		ApplyDayOffDialogEvt event=new ApplyDayOffDialogEvt(this);
		jcbReason.addActionListener(event);
		jcbStartMonth.addActionListener(event);
		jcbStartDay.addActionListener(event);
		jcbEndMonth.addActionListener(event);
		jbtnApply.addActionListener(event);
		jbtnCancel.addActionListener(event);
		
		//페널 설정
		setTitle("휴가 신청");
		setResizable(false);
		setBounds(ad.getHcyE().getX()+100, ad.getHcyE().getY()+100, 740, 390);
		setVisible(true);
	}// constructor

	public JComboBox<Integer> getJcbStartMonth() {
		return jcbStartMonth;
	}
	public JComboBox<Integer> getJcbStartYear() {
		return jcbStartYear;
	}
	public JComboBox<Integer> getJcbEndMonth() {
		return jcbEndMonth;
	}
	public JComboBox<Integer> getJcbStartDay() {
		return jcbStartDay;
	}
	public JComboBox<Integer> getJcbEndYear() {
		return jcbEndYear;
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
	public JButton getJbtnCancel() {
		return jbtnCancel;
	}
	public JButton getJbtnApply() {
		return jbtnApply;
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
	public Attendance getAd() {
		return ad;
	}
}// class
