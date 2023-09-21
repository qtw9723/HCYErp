package manageEmpRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import VO.EmpVO;

public class AddEmpDialogEvt extends MouseAdapter implements ActionListener {

	private AddEmpDialog aed;

	public AddEmpDialogEvt(AddEmpDialog aed) {
		this.aed = aed;
		aed.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				aed.dispose();
			}// windowClosing
		});// addWindowListener
	}// constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 입사자 추가 버튼
		if (e.getSource() == aed.getJbtnAddEmp()) {
			try {
				okAdd();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // catch
		} // if

		// 취소 버튼
		if (e.getSource() == aed.getJbtnCancel()) {
			cancel();
		} // if

		// 입사 달 선택
		if (e.getSource() == aed.getJcbMonthHiredate()) {
			startUpdateDays();
		} // if

		// 부서 선택
		if (e.getSource() == aed.getJcbDept()) {
			try {
				setTeam();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // catch
		} // if
	}// actionPerformed

	public void okAdd() throws SQLException {
		// 휴가일수 같이 생각해보기
		EmpVO eVO = new EmpVO();
		StringBuilder sbTemp = new StringBuilder();

		// 이름 추가
		eVO.setEname(aed.getJtfEname().getText());

		// 이메일 추가
		eVO.setEmail(aed.getJtfStartEmail().getText() + "@" + aed.getJtfEndEmail().getText());

		// 주소 추가
		eVO.setAddr(aed.getJtfAddr().getText() + "/" + aed.getJtfDetailAddr().getText());
		
		// 주민번호 추가
		eVO.setSsn(aed.getJtfStartSsn().getText() + "-" + aed.getJtfEndSsn().getText());

		// 부서추가
		eVO.setDept(aed.getJtfDept().getText());

		// 팀 추가
		eVO.setTeam(aed.getJtfTeam().getText());

		// 직무 추가
		eVO.setJob(aed.getJtfJob().getText());

		// 전화번호 추가
		eVO.setTel(aed.getJtfTelF().getText()+"-"+aed.getJtfTelS().getText()+"-"+aed.getJtfTelT().getText());

		// 회사번호 추가
		eVO.setJobTel(aed.getJtfJobTelF().getText()+"-"+aed.getJtfJobTelS().getText()+"-"+aed.getJtfJobTelT().getText()+"-");

		// 직급 추가
		eVO.setLevel(aed.getJtfLevel().getText());

		// 시작날짜 추가
		StringBuilder tempMonth = new StringBuilder();
		StringBuilder tempDay = new StringBuilder();
		if (aed.getJcbMonthHiredate().getSelectedItem().toString().length() == 1) {
			tempMonth.append("0");
		} // if
		if (aed.getJcbDayHiredate().getSelectedItem().toString().length() == 1) {
			tempDay.append("0");
		} // if
		tempMonth.append(aed.getJcbMonthHiredate().getSelectedItem().toString());
		tempDay.append(aed.getJcbDayHiredate().getSelectedItem().toString());
		sbTemp.append(aed.getJcbYearHiredate().getSelectedItem()).append("-").append(tempMonth).append("-")
				.append(tempDay);
		eVO.setHiredate(sbTemp.toString());

		// 연봉
		String sal = aed.getJtfSal().getText();
		eVO.setSal(Integer.parseInt(sal));

		// 근무지
		eVO.setDeptLoc(aed.getJtfLoc().getText());

		ManageEmpRegisterDAO.getInstance().insertEmp(eVO);

		JOptionPane.showMessageDialog(aed, "입사자 추가가 성공적으로 되었습니다.");
		refresh(aed.getMer().getHcyE().getEvent().getSelectedIndex());
	}// okAdd

	public void cancel() {
		aed.dispose();
	}// cancel

	public void startUpdateDays() {

		int selectedYear = (int) aed.getJcbYearHiredate().getSelectedItem();
		int selectedMonth = (int) aed.getJcbMonthHiredate().getSelectedItem();

		Calendar cal = Calendar.getInstance();

		cal.set(selectedYear, selectedMonth - 1, 1); // 월은 0부터 시작하므로 -1 자꾸 잊어먹냐 왜
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		aed.getJcbDayHiredate().removeAllItems();
		for (int i = 1; i <= maxDay; i++) {
			aed.getJcbDayHiredate().addItem(i);
		} // end for
	}// startUpdateDays

	private void setTeam() throws SQLException {
		aed.getJcbTeam().removeAllItems();
		List<String> teamList = ManageEmpRegisterDAO.getInstance()
				.selectTeam(aed.getJcbDept().getSelectedItem().toString());
		for (String team : teamList) {
			aed.getJcbTeam().addItem(team);
		} // for
	}// setTeam

	public void refresh(int index) throws SQLException {
		aed.getMer().getHcyE().getTabbedPane().setVisible(false);
		aed.getMer().getHcyE().addComponent();
		aed.getMer().getHcyE().getEvent().login();
		aed.getMer().getHcyE().getTabbedPane().setSelectedIndex(index);
	}// refresh
}// class
