package manageDailyReport;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import VO.DailyReportVO;

public class ManageDailyReportEvt extends MouseAdapter implements ActionListener {
	private ManageDailyReport mdr;

	public ManageDailyReportEvt(ManageDailyReport mdr) {
		this.mdr = mdr;
		
	}// constructor

	@Override
	public void mouseClicked(MouseEvent e) {
		String emp = mdr.getJcbEmp().getSelectedItem().toString();
		String content = "";
		DailyReportVO drVO = new DailyReportVO();
		try {
			if (e.getClickCount() == 2) {
				drVO.setEmpNo(Integer.parseInt(emp.substring(0, 4)));
				drVO.setReportDate(mdr.getDtmReport().getValueAt(mdr.getJtReport().getSelectedRow(), 3).toString());
				drVO = ManageDailyReportDAO.getInstance().selectDailyReport(drVO);
				content = drVO.getReportContent();
				if (e.getSource() == mdr.getJtReport()) {
					new ManageDailyReportDialog(mdr, content);
				} // if
			} // if
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} // catch
	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent e) {
		// 날짜 조회
		if (e.getSource() == mdr.getJbtnDateSearch()) {
			for (int i = mdr.getDtmReport().getRowCount() - 1; i >= 0; i--) {
				mdr.getDtmReport().removeRow(i);
			} // for
			try {
				StringBuilder sbDate = new StringBuilder();
				StringBuilder sbMonth = new StringBuilder();
				StringBuilder sbDay = new StringBuilder();

				if (mdr.getJcbMonth().getSelectedItem().toString().length() == 1) {
					sbMonth.append("0");
				} // if
				sbMonth.append(mdr.getJcbMonth().getSelectedItem());

				if (mdr.getJcbDay().getSelectedItem().toString().length() == 1) {
					sbDay.append("0");
				} // if
				sbDay.append(mdr.getJcbDay().getSelectedItem());

				sbDate.append(mdr.getJcbYear().getSelectedItem()).append("-").append(sbMonth.toString()).append("-")
						.append(sbDay.toString());
				List<DailyReportVO> drVOList = new ArrayList<DailyReportVO>();
				drVOList = ManageDailyReportDAO.getInstance().selectDailyReport(sbDate.toString());
				// 작성된 업무일지 업음
				if (drVOList.isEmpty()) {
					JOptionPane.showMessageDialog(mdr, "해당 일에는 작성된 업무일지가 없습니다.");
					return;
				} // if

				for (DailyReportVO drVO : drVOList) {
					mdr.getDtmReport()
							.addRow(new Object[] { drVO.getEmpNo(), drVO.getEname(),
									drVO.getReportContent().length() > 15 ? drVO.getReportContent().substring(0, 15)
											: drVO.getReportContent(),
									drVO.getReportDate() });
					;
				} // for
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // catch
		} // if

		// 사원별 조회
		if (e.getSource() == mdr.getJbtnEmpSearch()) {
			for (int i = mdr.getDtmReport().getRowCount() - 1; i >= 0; i--) {
				mdr.getDtmReport().removeRow(i);
			} // for
			String emp = mdr.getJcbEmp().getSelectedItem().toString();
			try {
				List<DailyReportVO> drVOList = ManageDailyReportDAO.getInstance()
						.selectDailyReport(Integer.parseInt(emp.substring(0, emp.indexOf("/"))));
				for (DailyReportVO drVO : drVOList) {
					mdr.getDtmReport()
							.addRow(new Object[] { drVO.getEmpNo(), drVO.getEname(),
									(drVO.getReportContent().length() > 14) ? drVO.getReportContent().substring(0, 15)
											: drVO.getReportContent(),
									drVO.getReportDate() });
				} // for
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // catch
		} // if
		if (e.getSource() == mdr.getJcbMonth()) {
			Calendar cal = Calendar.getInstance();
			int num = mdr.getJcbDay().getSelectedIndex();
			mdr.getJcbDay().removeAllItems();
			cal.set(Calendar.MONTH, mdr.getJcbMonth().getSelectedIndex());
			for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
				mdr.getJcbDay().addItem(i);
			} // for
			mdr.getJcbDay().setSelectedIndex(num);
		} // if
		if(e.getSource()==mdr.getJbtnLogOut()) {
			logOut();
		}//if

	}// actionPerformed
	public void logOut() {
		mdr.getHcyE().getTabbedPane().setVisible(false);
		mdr.getHcyE().addComponent();
		mdr.getHcyE().setUser(0);
	}//logOut

}// class
