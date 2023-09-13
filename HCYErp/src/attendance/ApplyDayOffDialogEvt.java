package attendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import javax.swing.JOptionPane;

import VO.DayOffApplyVO;

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

	@Override
	public void actionPerformed(ActionEvent e) {

		// 이유-직접입력 눌렀을 때
		if (e.getSource() == adod.getJcbReason()) {
			String selectedReason = (String) adod.getJcbReason().getSelectedItem();
			if ("기타(직접입력)".equals(selectedReason)) {
				adod.getJtaWriteReason().setEditable(true);
				adod.getJtaWriteReason().setText("");
				return;
			}
			adod.getJtaWriteReason().setEditable(false);

		} // if

		// 날짜 콤보박스 이벤트
		if (e.getSource() == adod.getJcbStartMonth()) {
			startUpdateDays();
		} // if
		if (e.getSource() == adod.getJcbEndMonth()) {
			endUpdateDays();
		} // if

		// 취소버튼
		if (e.getSource() == adod.getJbtnCancel()) {
			cancelDayOffApply();
		} // if

		// 신청버튼
		if (e.getSource() == adod.getJbtnApply()) {
			try {
				dayOffApply();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(adod, "휴가를 신청하는 과정에서 문제가 발생했습니다. 기술팀에 문의해주세요. (곽부장님)");
			} // catch
		} // if
	}// actionPerformed

	public void startUpdateDays() {

		int selectedYear = (int) adod.getJcbStartYear().getSelectedItem();
		int selectedMonth = (int) adod.getJcbStartMonth().getSelectedItem();

		Calendar cal = Calendar.getInstance();

		cal.set(selectedYear, selectedMonth - 1, 1); // 월은 0부터 시작하므로 -1 자꾸 잊어먹냐 왜
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		adod.getJcbStartDay().removeAllItems();
		for (int i = 1; i <= maxDay; i++) {
			adod.getJcbStartDay().addItem(i);
		} // end for
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
		} // end for
	}// endUpdateDays

	public void dayOffApply() throws SQLException {
		String selectedReason = (String) adod.getJcbReason().getSelectedItem();
		StringBuilder sbTemp = new StringBuilder();
		DayOffApplyVO doaVO = new DayOffApplyVO();
		// 사번 추가
		doaVO.setEmpNo(adod.getAd().getHcyE().getUser());

		// 시작날짜 추가
		StringBuilder tempMonth = new StringBuilder();
		StringBuilder tempDay = new StringBuilder();
		if (adod.getJcbStartMonth().getSelectedItem().toString().length() == 1) {
			tempMonth.append("0");
		} // if
		if (adod.getJcbStartDay().getSelectedItem().toString().length() == 1) {
			tempDay.append("0");
			System.out.println(tempDay);
		} // if
		tempMonth.append(adod.getJcbStartMonth().getSelectedItem().toString());
		tempDay.append(adod.getJcbStartDay().getSelectedItem().toString());
		sbTemp.append(adod.getJcbStartYear().getSelectedItem()).append("-").append(tempMonth).append("-")
				.append(tempDay);
		System.out.println(sbTemp.toString());
		doaVO.setStartDate(sbTemp.toString());

		// 마지막 날짜 추가
		sbTemp.replace(0, sbTemp.length(), "");
		tempMonth.replace(0, tempMonth.length(), "");
		tempDay.replace(0, tempDay.length(), "");
		if (adod.getJcbEndMonth().getSelectedItem().toString().length() == 1) {
			tempMonth.append("0");
		} // if
		if (adod.getJcbEndDay().getSelectedItem().toString().length() == 1) {
			tempDay.append("0");
		} // if
		tempMonth.append(adod.getJcbEndMonth().getSelectedItem().toString());
		tempDay.append(adod.getJcbEndDay().getSelectedItem().toString());
		sbTemp.append(adod.getJcbEndYear().getSelectedItem()).append("-").append(tempMonth).append("-").append(tempDay);
		doaVO.setEndDate(sbTemp.toString());

		// 휴가 일수
		LocalDate startDate = LocalDate.of(Integer.parseInt(adod.getJcbStartYear().getSelectedItem().toString()),
				Integer.parseInt(adod.getJcbStartMonth().getSelectedItem().toString()),
				Integer.parseInt(adod.getJcbStartDay().getSelectedItem().toString()));
		LocalDate endDate = LocalDate.of(Integer.parseInt(adod.getJcbEndYear().getSelectedItem().toString()),
				Integer.parseInt(adod.getJcbEndMonth().getSelectedItem().toString()),
				Integer.parseInt(adod.getJcbEndDay().getSelectedItem().toString()));

		long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		doaVO.setDayOffDays((int) daysBetween);

		// 휴가 사유 추가
		String reason = "";
		if ("기타(직접입력)".equals(selectedReason)) {
			reason = adod.getJtaWriteReason().getText();
		} else {
			reason = (String) adod.getJcbReason().getSelectedItem();
		} // else
		doaVO.setReason(reason);

		AttendanceDAO.getInstance().insertDayOffApply(doaVO);

		JOptionPane.showMessageDialog(adod, "휴가가 성공적으로 신청되었습니다. 인사팀에 보고해주세요!");
	}// dayOffApply

	public void cancelDayOffApply() {
		adod.dispose();
	}// cancelDayOffApply

}// class