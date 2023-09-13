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
import VO.RejectDayOffVO;

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
			String selectDayOff = "SELECT d.dayoffno, d.EMPNO, e.ename, STARTDATE, ENDDATE, DAYOFFDAYS, REASON, APPROVE, SUBMITDATE FROM DAYOFF_APPLY d, EMP e WHERE d.empno=e.empno  and APPROVE = 'W'";
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(selectDayOff);
			DayOffApplyVO doaVO = null;
			while (rs.next()) {
				doaVO = new DayOffApplyVO();
				doaVO.setDayOffNo(rs.getInt("dayoffno"));
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
	
	
	public DayOffApplyVO selectPersonalDayOffApply(int dayOffNo) throws SQLException {
		DbConn db = DbConn.getInstance();
		DayOffApplyVO doaVO = new DayOffApplyVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			String selectDayOff = "SELECT d.dayoffno, d.EMPNO, e.ename, STARTDATE, ENDDATE, DAYOFFDAYS, REASON, APPROVE, SUBMITDATE FROM DAYOFF_APPLY d, EMP e WHERE d.empno=e.empno  and APPROVE = 'W' and d.dayoffno = ?";
			pstmt = con.prepareStatement(selectDayOff);
			pstmt.setInt(1, dayOffNo);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				doaVO.setDayOffNo(rs.getInt("dayoffno"));
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
	
	public int updateDayOffApply(int dayOffNo) throws SQLException {
		int cnt=0;
		DbConn db = DbConn.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			String updateDayOffApply = "UPDATE DAYOFF_APPLY set APPROVE = 'Y' Where dayoffno = ?";
			pstmt = con.prepareStatement(updateDayOffApply);
			
			pstmt.setInt(1, dayOffNo);
			cnt = pstmt.executeUpdate();
		}finally {
			db.dbclose(rs, pstmt, con);
		}//finally
		return cnt;
	}//updateDayOffApply
	
	public boolean rejectDayOff(RejectDayOffVO rdoVO) throws SQLException {
		boolean flag = false;
		DbConn db = DbConn.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//휴가 신청 반려 처리
			int cnt = 0;
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			String updateDayOffReject = "UPDATE DAYOFF_APPLY set APPROVE = 'N' Where dayoffno = ?";
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(updateDayOffReject);
			pstmt.setString(1, Integer.toString(rdoVO.getDayOffNo()));
			System.out.println(rdoVO.getDayOffNo());
			cnt = pstmt.executeUpdate();
			System.out.println("괜찮니?");
			if(cnt==0) {
				flag=true;
				con.rollback();
				System.out.println(cnt+"반려처리 안댐");
				return false;
			}//if
			
			pstmt.close();
			//메세지 추가
			System.out.println("가즈아");
			String insertMSG = "INSERT INTO MSG(EMPNO, MSGCONTENT, MSGCHECK) VALUES (?,?,?)";
			pstmt = con.prepareStatement(insertMSG);
			pstmt.setString(1, Integer.toString(rdoVO.getMsgVO().getEmpNo()));
			pstmt.setString(2, rdoVO.getMsgVO().getMsgContent());
			pstmt.setString(3, rdoVO.getMsgVO().getMsgCheck());
			System.out.println(Integer.toString(rdoVO.getMsgVO().getEmpNo())+"/"+rdoVO.getMsgVO().getMsgContent()+"/"+ rdoVO.getMsgVO().getMsgCheck());
			
			pstmt.execute();
				con.commit();
				flag=true;
		}catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		} finally {
			db.dbclose(rs, pstmt, con);
		}//finally
		
		return flag;
	}//rejectDayOff
	
	
}//class 
