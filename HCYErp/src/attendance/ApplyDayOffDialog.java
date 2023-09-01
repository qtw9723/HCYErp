package attendance;

import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class ApplyDayOffDialog extends JDialog {

	private JComboBox<Integer> jcbStartYear;
	private JComboBox<Integer> jcbStartMonth;
	private JComboBox<Integer> jcbStartDay;
	private JComboBox<Integer> jcbEndYear;
	private JComboBox<Integer> jcbEndMonth;
	private JComboBox<Integer> jcbEndDay;
	private JComboBox<String> jcbReason;
	private JButton jbtnApply;
	private JButton jbtnCancel;
	private JLabel jlblPeriod;
	private JLabel jlblDuring;
	private JLabel jlblReason;

	public ApplyDayOffDialog() {
		Calendar cal = Calendar.getInstance();
		jcbStartYear = new JComboBox<Integer>();
		jcbStartMonth = new JComboBox<Integer>();
		jcbStartDay = new JComboBox<Integer>();
		jcbEndYear = new JComboBox<Integer>();
		jcbEndMonth = new JComboBox<Integer>();
		jcbEndDay = new JComboBox<Integer>();
		jcbReason = new JComboBox<String>();

		// JButton 선언
		jbtnApply = new JButton();
		jbtnCancel = new JButton();

		// JLabel 선언
		jlblPeriod = new JLabel();
		jlblDuring = new JLabel();
		jlblReason = new JLabel();

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
		
		jcbStartMonth.setSelectedItem(cal.get(Calendar.MONTH)+1);
		
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

		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			jcbStartDay.addItem(i);
			jcbEndDay.addItem(i);
		}

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
		
		setLayout(null);

		jcbStartYear.setBounds(10, 10, 100, 30);
		jcbStartMonth.setBounds(10, 100, 100, 30);
		jcbStartDay.setBounds(10, 200, 100, 30);
		jcbEndYear.setBounds(10, 10, 100, 30);
		jcbEndMonth.setBounds(10, 100, 100, 30);
		jcbEndDay.setBounds(10, 200, 100, 30);

		add(jcbStartYear);
		add(jcbStartMonth);
		add(jcbStartDay);

		setBounds(100, 100, 800, 400);
		setVisible(true);
	}// constructor

	public static void main(String[] arg) {
		new ApplyDayOffDialog();
	}

}// class
