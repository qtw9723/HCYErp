package manageLeave;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;
import VO.DayOffApplyVO;

public class ManageLeaveDAO {
	
	private static ManageLeaveDAO mlDAO;
	
	private ManageLeaveDAO() {
	}//Constructor
	
	public static ManageLeaveDAO getInstance() {
		if(mlDAO==null) {
			mlDAO = new ManageLeaveDAO();
		}//if
		return mlDAO;
	}//getInstance
	
	public List<DayOffApplyVO> selectDayOffApply() throws SQLException{
		List<DayOffApplyVO> doaVOList = new ArrayList<DayOffApplyVO>();
		DbConn db = DbConn.getInstance();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			String selectDayOff = "SELECT d.EMPNO, e.ename, STARTDATE, ENDDATE, DAYOFFDAYS, REASON, APPROVE, SUBMITDATE FROM DAYOFF_APPLY d, EMP e WHERE d.empno=e.empno  and APPROVE = 'W'";
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(selectDayOff);
			DayOffApplyVO doaVO = new DayOffApplyVO();
			while (rs.next()) {
				doaVO.setEmpNo(rs.getInt("empno"));
				doaVO.setEname(rs.getString("ename"));
				doaVO.setStartDate(rs.getString("startdate"));
				doaVO.setEndDate(rs.getString("enddate"));
				doaVO.setDayOffDays(rs.getInt("DAYOFFDAYS"));
				doaVO.setReason(rs.getString("REASON"));
				doaVO.setApprove(rs.getString("APPROVE"));
				doaVO.setSubmitDate(rs.getDate("SUBMITDATE"));
				doaVOList.add(doaVO);
			}//while
		}finally {
			db.dbclose(rs, stmt, con);
		}//finally
		
		return doaVOList;
	}//selectDayOffApply
	
	
	public DayOffApplyVO selectPersonalDayOffApply(int empNo) throws SQLException {
		DbConn db = DbConn.getInstance();
		DayOffApplyVO doaVO = new DayOffApplyVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			String selectDayOff = "SELECT d.EMPNO, e.ename, STARTDATE, ENDDATE, DAYOFFDAYS, REASON, APPROVE, SUBMITDATE FROM DAYOFF_APPLY d, EMP e WHERE d.empno=e.empno  and APPROVE = 'W' and d.empno = ?";
			pstmt = con.prepareStatement(selectDayOff);
			pstmt.setInt(1, empNo);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				doaVO.setEmpNo(rs.getInt("empno"));
				doaVO.setEname(rs.getString("ename"));
				doaVO.setStartDate(rs.getString("startdate"));
				doaVO.setEndDate(rs.getString("enddate"));
				doaVO.setDayOffDays(rs.getInt("DAYOFFDAYS"));
				doaVO.setReason(rs.getString("REASON"));
				doaVO.setApprove(rs.getString("APPROVE"));
				doaVO.setSubmitDate(rs.getDate("SUBMITDATE"));
			}//while
		}finally {
			db.dbclose(rs, pstmt, con);
		}//finally
		
		return doaVO;
	}//selectPersonalDayOffApply
}//class 
