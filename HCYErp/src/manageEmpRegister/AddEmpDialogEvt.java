package manageEmpRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import VO.EmpVO;

public class AddEmpDialogEvt extends MouseAdapter implements ActionListener, KeyListener {

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
		eVO.setDept(aed.getJcbDept().getSelectedItem().toString());

		// 팀 추가
		eVO.setTeam(aed.getJcbTeam().getSelectedItem().toString());

		// 직무 추가
		eVO.setJob(aed.getJcbJob().getSelectedItem().toString());

		// 전화번호 추가
		eVO.setTel(aed.getJtfTelF().getText() + "-" + aed.getJtfTelS().getText() + "-" + aed.getJtfTelT().getText());

		// 회사번호 추가
		eVO.setJobTel(aed.getJtfJobTelF().getText() + "-" + aed.getJtfJobTelS().getText() + "-"
				+ aed.getJtfJobTelT().getText());

		// 직급 추가
		eVO.setLevel(aed.getJcbLevel().getSelectedItem().toString());

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
		aed.getJtfEname().setText("");
		aed.getJtfStartEmail().setText("");
		aed.getJtfEndEmail().setText("");
		aed.getJtfAddr().setText("");
		aed.getJtfStartSsn().setText("");
		aed.getJcbDept().setSelectedIndex(0);
//		aed.getJcbTeam().setSelectedIndex(0);
		aed.getJcbTeam().removeAllItems();
		aed.getJcbTeam().addItem("--부서 먼저 선택--");
		aed.getJcbJob().setSelectedIndex(0);
		aed.getJtfTelF().setText("");
		aed.getJtfTelS().setText("");
		aed.getJtfTelT().setText("");
		aed.getJtfJobTelF().setText("");
		aed.getJtfJobTelS().setText("");
		aed.getJtfJobTelT().setText("");
		aed.getJcbLevel().setSelectedIndex(0);
		aed.getJcbYearHiredate().setSelectedItem(Calendar.getInstance().get(Calendar.YEAR));
		aed.getJcbMonthHiredate().setSelectedItem(Calendar.getInstance().get(Calendar.MONTH)+1);
		aed.getJcbDayHiredate().setSelectedItem(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		aed.getJtfSal().setText("");
		aed.getJtfLoc().setText("");
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

	@Override
	public void keyTyped(KeyEvent e) {
		// 주민번호 앞자리
		if (e.getSource() == aed.getJtfStartSsn()) {
			if (aed.getJtfStartSsn().getText().length() >= 6) {
				e.consume();
			} // if
		} // if

		// 주민번호 뒷자리
		if (e.getSource() == aed.getJtfEndSsn()) {
			if (aed.getJtfEndSsn().getText().length() >= 7) {
				e.consume();
				aed.getJtfEndSsn().requestFocus();
			} // if
		} // if

		// 전화번호 1번째
		if (e.getSource() == aed.getJtfTelF()) {
			if (aed.getJtfTelF().getText().length() >= 3) {
				e.consume();
			} // if
		} // if

		// 전화번호 2번째
		if (e.getSource() == aed.getJtfTelS()) {
			if (aed.getJtfTelS().getText().length() >= 4) {
				e.consume();
			} // if
		} // if

		// 전화번호 3번째
		if (e.getSource() == aed.getJtfTelT()) {
			if (aed.getJtfTelT().getText().length() >= 4) {
				e.consume();
			} // if
		} // if
		
		// 업무용 전화 1번째
		if (e.getSource() == aed.getJtfJobTelF()) {
			if (aed.getJtfJobTelF().getText().length() >= 3) {
				e.consume();
			} // if
		} // if
		
		// 업무용 전화 2번째
		if (e.getSource() == aed.getJtfJobTelS()) {
			if (aed.getJtfJobTelS().getText().length() >= 4) {
				e.consume();
			} // if
		} // if
		
		// 업무용 전화 3번째
		if (e.getSource() == aed.getJtfJobTelT()) {
			if (aed.getJtfJobTelT().getText().length() >= 4) {
				e.consume();
			} // if
		} // if
	}// keyTyped

	@Override
	public void keyReleased(KeyEvent e) {
		// 주민번호 앞자리
		if (e.getSource() == aed.getJtfStartSsn()) {
			if (aed.getJtfStartSsn().getText().length() == 6) {
				aed.getJtfEndSsn().requestFocus();
			} // if
		} // if

		// 주민번호 뒷자리
		if (e.getSource() == aed.getJtfEndSsn()) {
			if (aed.getJtfEndSsn().getText().length() == 7) {
				aed.getJtfTelF().requestFocus();
			} // if
		} // if

		// 전화번호 1번째
		if (e.getSource() == aed.getJtfTelF()) {
			if (aed.getJtfTelF().getText().length() == 3) {
				aed.getJtfTelS().requestFocus();
			} // if
		} // if

		// 전화번호 2번째
		if (e.getSource() == aed.getJtfTelS()) {
			if (aed.getJtfTelS().getText().length() == 4) {
				aed.getJtfTelT().requestFocus();
			} // if
		} // if

		// 전화번호 3번째
		if (e.getSource() == aed.getJtfTelT()) {
			if (aed.getJtfTelT().getText().length() == 4) {
				aed.getJtfJobTelF().requestFocus();
			} // if
		} // if
		
		// 업무용 전화 1번째
		if (e.getSource() == aed.getJtfJobTelF()) {
			if (aed.getJtfJobTelF().getText().length() == 3) {
				aed.getJtfJobTelS().requestFocus();
			} // if
		} // if
		
		// 업무용 전화 2번째
		if (e.getSource() == aed.getJtfJobTelS()) {
			if (aed.getJtfJobTelS().getText().length() == 4) {
				aed.getJtfJobTelT().requestFocus();
			} // if
		} // if
		
		// 업무용 전화 3번째
		if (e.getSource() == aed.getJtfJobTelT()) {
			if (aed.getJtfJobTelT().getText().length() == 4) {
				aed.getJtfLoc().requestFocus();
			} // if
		} // if
	}// keyReleased

	@Override
	public void keyPressed(KeyEvent e) {

	}
}// class
