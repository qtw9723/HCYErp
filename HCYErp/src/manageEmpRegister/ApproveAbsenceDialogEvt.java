//package manageEmpRegister;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.Calendar;
//
//import javax.swing.JOptionPane;
//
//import VO.AbsenceApplyVO;
//import attendance.AttendanceDAO;
//
//public class ApproveAbsenceDialogEvt extends MouseAdapter implements ActionListener {
//
//	private ApproveAbsenceDialog aad;
//
//	public ApproveAbsenceDialogEvt(ApproveAbsenceDialog aad) {
//		this.aad = aad;
//
//		aad.addWindowListener(new WindowAdapter() {
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				aad.dispose();
//			}
//
//		});// addWindowListener
//	}// constructor
//
//	public void startUpdateDays() {
//
//		int selectedYear = (int) aad.getJcbStartYear().getSelectedItem();
//		int selectedMonth = (int) aad.getJcbStartMonth().getSelectedItem();
//
//		Calendar cal = Calendar.getInstance();
//
//		cal.set(selectedYear, selectedMonth - 1, 1); // 월은 0부터 시작하므로 -1 자꾸 잊어먹냐 왜
//		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//		aad.getJcbStartDay().removeAllItems();
//		for (int i = 1; i <= maxDay; i++) {
//			aad.getJcbStartDay().addItem(i);
//		}//end for
//	}// startUpdateDays
//	
//	public void endUpdateDays() {
//		int selectedYear = (int) aad.getJcbEndYear().getSelectedItem();
//		int selectedMonth = (int) aad.getJcbEndMonth().getSelectedItem();
//
//		Calendar cal = Calendar.getInstance();
//
//		cal.set(selectedYear, selectedMonth - 1, 1); // 월은 0부터 시작하므로 -1 자꾸 잊어먹냐 왜
//		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//		aad.getJcbEndDay().removeAllItems();
//		for (int i = 1; i <= maxDay; i++) {
//			aad.getJcbEndDay().addItem(i);
//		}//end for
//	}// endUpdateDays
//	
//	public void cancel() {
//		aad.dispose();
//	}// cancelDayOffApply
//	
//	public void approveAbsence() throws SQLException {
//		String selectedReason = (String) aad.getJcbReason().getSelectedItem();
//		StringBuilder sbTemp = new StringBuilder();
//		AbsenceApplyVO aaVO = new AbsenceApplyVO();
//		//사번 추가
//		aaVO.setEmpNo(aad.getMer().getHcyE().getUser());
//		
//		//시작날짜 추가
//		StringBuilder tempMonth=new StringBuilder();
//		StringBuilder tempDay=new StringBuilder();
//		if( aad.getJcbStartMonth().getSelectedItem().toString().length() == 1) {
//			tempMonth.append("0");
//		}//if
//		if( aad.getJcbStartDay().getSelectedItem().toString().length() == 1) {
//			tempDay.append("0");
//			System.out.println(tempDay);
//		}//if
//		tempMonth.append(aad.getJcbStartMonth().getSelectedItem().toString());
//		tempDay.append(aad.getJcbStartDay().getSelectedItem().toString());
//		sbTemp.append(aad.getJcbStartYear().getSelectedItem()).append("-").append(tempMonth).append("-").append(tempDay);
//		System.out.println(sbTemp.toString());
//		aaVO.setStartDate(sbTemp.toString());//Date형식임
//		
//		//마지막 날짜 추가
//		sbTemp.replace(0, sbTemp.length(), "");
//		tempMonth.replace(0, tempMonth.length(), "");
//		tempDay.replace(0, tempDay.length(), "");
//		if( aad.getJcbEndMonth().getSelectedItem().toString().length() == 1) {
//			tempMonth.append("0");
//		}//if
//		if( aad.getJcbEndDay().getSelectedItem().toString().length() == 1) {
//			tempDay.append("0");
//		}//if
//		tempMonth.append(aad.getJcbEndMonth().getSelectedItem().toString());
//		tempDay.append(aad.getJcbEndDay().getSelectedItem().toString());
//		sbTemp.append(aad.getJcbEndYear().getSelectedItem()).append("-").append(tempMonth).append("-").append(tempDay);
//		aaVO.setEndDate(sbTemp.toString());//얘도
//		
//		//휴가 일수
//		LocalDate startDate = LocalDate.of(Integer.parseInt(aad.getJcbStartYear().getSelectedItem().toString()), Integer.parseInt(aad.getJcbStartMonth().getSelectedItem().toString()), Integer.parseInt(aad.getJcbStartDay().getSelectedItem().toString()));
//		LocalDate endDate = LocalDate.of(Integer.parseInt(aad.getJcbEndYear().getSelectedItem().toString()), Integer.parseInt(aad.getJcbEndMonth().getSelectedItem().toString()), Integer.parseInt(aad.getJcbEndDay().getSelectedItem().toString()));
//		
//		long daysBetween = ChronoUnit.DAYS.between(startDate,endDate);
//		aaVO.setAbsenceDays((int)daysBetween);
//		
//		//휴가 사유 추가
//		String reason = "";
//		if ("기타(직접입력)".equals(selectedReason)) {
//			reason = aad.getJtaWriteReason().getText();
//		}else {
//			reason = (String)aad.getJcbReason().getSelectedItem();
//		}//else
//		aaVO.setReason(reason);
//		
//		AttendanceDAO.getInstance().insertDayOffApply(aaVO);//얘도 다시
//		
//		JOptionPane.showMessageDialog(aad, "휴가가 성공적으로 신청되었습니다. 인사팀에 보고해주세요!");
//	}// dayOffApply
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// 이유-직접입력 눌렀을 때
//		if (e.getSource() == aad.getJcbReason()) {
//			String selectedReason = (String) aad.getJcbReason().getSelectedItem();
//			if ("기타(직접입력)".equals(selectedReason)) {
//				aad.getJtaWriteReason().setEditable(true);
//				aad.getJtaWriteReason().setText("");
//				return;
//			} // end if
//			aad.getJtaWriteReason().setEditable(false);
//		} // end if
//			// 날짜 콤보박스 이벤트
//		if (e.getSource() == aad.getJcbStartMonth()) {
//			startUpdateDays();
//		} // if
//		if (e.getSource() == aad.getJcbEndMonth()) {
//			endUpdateDays();
//		} // if
//			// 취소버튼
//		if (e.getSource() == aad.getJbtnCancel()) {
//			cancel();
//		} // if
//			// 신청버튼
//		if (e.getSource() ==aad.getJbtnApprove()) {
//			try {
//				approveAbsence();
//			} catch (SQLException e1) {
//				JOptionPane.showMessageDialog(aad, "휴직신청을 신청하는 과정에서 문제가 발생했습니다. 기술팀에 문의해주세요. (곽부장님)");
//			} // catch
//		} // if
//	}
//
//}// class
