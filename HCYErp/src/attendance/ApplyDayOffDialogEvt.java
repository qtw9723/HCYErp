package attendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class ApplyDayOffDialogEvt extends MouseAdapter implements ActionListener {

	private ApplyDayOffDialog adod;

	public ApplyDayOffDialogEvt(ApplyDayOffDialog adod) {
		this.adod = adod;

		adod.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				adod.dispose();
			}

		});// addWindowListener
	}// constructor

	public void startUpdateDays() {

		int selectedYear = (int) adod.getJcbStartYear().getSelectedItem();
		int selectedMonth = (int) adod.getJcbStartMonth().getSelectedItem();

		Calendar cal = Calendar.getInstance();

		cal.set(selectedYear, selectedMonth - 1, 1); // 월은 0부터 시작하므로 -1 자꾸 잊어먹냐 왜
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		adod.getJcbStartDay().removeAllItems();
		for (int i = 1; i <= maxDay; i++) {
			adod.getJcbStartDay().addItem(i);
		}
	}// startUpdateDays

	public void endUpdateDays() {
		int selectedYear = (int) adod.getJcbEndYear().getSelectedItem();
		int selectedMonth = (int) adod.getJcbEndMonth().getSelectedItem();

		Calendar cal = Calendar.getInstance();

		cal.set(selectedYear, selectedMonth - 1, 1); // 월은 0부터 시작하므로 -1 자꾸 잊어먹냐 왜
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		adod.getJcbEndDay().removeAllItems();
		for (int i = 1; i <= maxDay; i++) {
			adod.getJcbEndDay().addItem(i);
		}
	}// endUpdateDays

	public void dayOffApply() {
		// 신청 눌렀을 때 가야겠지?
	}// dayOffApply

	public void cancelDayOffApply() {
		adod.dispose();
	}// cancelDayOffApply

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adod.getJcbReason()) {// 이유-직접입력 눌렀을 때
			String selectedReason = (String) adod.getJcbReason().getSelectedItem();
			if ("기타(직접입력)".equals(selectedReason)) {
				adod.getJtaWriteReason().setEditable(true);
				adod.getJtaWriteReason().setText("");
			} else {
				adod.getJtaWriteReason().setEditable(false);
			}
		}
		if (e.getSource() == adod.getJcbStartMonth()) {
			startUpdateDays();
		}
		if (e.getSource() == adod.getJcbEndMonth()) {
			endUpdateDays();
		}
		if (e.getSource() == adod.getJbtnCancel()) {
			cancelDayOffApply();
		}
		if (e.getSource() == adod.getJbtnApply()) {
			dayOffApply();
		}
	}// actionPerformed

}