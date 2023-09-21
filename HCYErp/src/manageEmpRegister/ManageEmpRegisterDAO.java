package manageEmpRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;
import VO.AbsenceApplyVO;
import VO.AttendanceVO;
import VO.DayOffApplyVO;
import VO.EmpVO;
import VO.ResignationVO;

public class ManageEmpRegisterDAO {
	private static ManageEmpRegisterDAO merDAO;
	
	private ManageEmpRegisterDAO() { 
	}//Constructor
	
	public static ManageEmpRegisterDAO getInstance() {
		if(merDAO==null) {
			merDAO=new ManageEmpRegisterDAO();
		}//if
		return merDAO;
	}//getInstance
	
	public List<String> selectAbsenceResignation() throws SQLException {
	    List<String> empList = new ArrayList<String>();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    DbConn db = DbConn.getInstance();

	    try {
	        con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
	        String sql = "SELECT E.EMPNO, E.ENAME FROM EMP E";

	        pstmt = con.prepareStatement(sql);

	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            int empNo = rs.getInt("empno");
	            String ename = rs.getString("ename");
	            String empInfo = empNo + "/" + ename ; 
	            empList.add(empInfo);
	        } // while
	    } finally {
	        if (db != null) {
	            db.dbclose(rs, pstmt, con);
	        }//if
	    } // try
	    return empList;
	}

	
	public List<EmpVO> selectEmp() throws SQLException {
		List<EmpVO>  empVOList = new ArrayList<EmpVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			String sql = "SELECT E.EMPNO, E.ENAME, J.JOBNAME, JL.LVNAME, to_char(E.HIREDATE,'yyyy-mm-dd') hiredate, E.SAL FROM EMP E,JOB J, JOBLEVEL JL WHERE E.JOBNO = J.JOBNO(+) AND E.LEVELNO = JL.LEVELNO(+)";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpVO eVO = new EmpVO();
				eVO.setEmpNo(rs.getInt("empno"));
				eVO.setEname(rs.getString("ename"));
				eVO.setHiredate(rs.getString("hiredate"));
				eVO.setJob(rs.getString("jobname"));
				eVO.setLevel(rs.getString("lvname"));
				eVO.setSal(rs.getInt("sal"));
				empVOList.add(eVO);
			} // while
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}//if
		} // try
		return empVOList;
	}// selectEmp
	
	public void insertAbsenceApply(AbsenceApplyVO aaVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConn db=DbConn.getInstance();
		try {
			con=db.getConnection("192.168.10.145","hcytravel","boramsangjo");
			
			String sql="insert into absence(empno,startdate, enddate, absencedays, reason, submitdate) values(?,?,?,?,?,sysdate)";
		
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, aaVO.getEmpNo());
			pstmt.setString(2, aaVO.getStartDate());
			pstmt.setString(3, aaVO.getEndDate());
			pstmt.setInt(4, aaVO.getAbsenceDays());
			pstmt.setString(5, aaVO.getReason());
			
			pstmt.execute();
		}finally {
			if(db!=null) {
				db.dbclose(null, pstmt, con);
			}//end if
		}//end finally
	}//insertAbsenceApply
	
	public void deleteRsignationEmp(EmpVO eVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConn db=DbConn.getInstance();
		try {
			con=db.getConnection("192.168.10.145","hcytravel","boramsangjo");
			
			String sql="delete from emp where empno=?";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, eVO.getEmpNo());
			
			
			pstmt.execute();
		}finally {
			if(db!=null) {
				db.dbclose(null, pstmt, con);
			}//end if
		}//end finally
	}//insertAbsenceApply
	
	public void insertEmp(EmpVO eVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConn db=DbConn.getInstance();
		try {
			con=db.getConnection("192.168.10.145","hcytravel","boramsangjo");
			
			String sql="insert into emp (teamno, jobno, levelno, sal,input_date,jobtel, ename, email, addr, ssn, tel, pass, hiredate, totaldayoff )values((select teamno from team where tname=?), (select jobno from job where jobname=?),(select levelno from JOBLEVEL where lvname=?), ?, sysdate ,?,?, ?, ?, ?,?, ?, ?,15)";
		
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, eVO.getTeam());
			pstmt.setString(2, eVO.getJob());
			pstmt.setString(3, eVO.getLevel());
			pstmt.setInt(4, eVO.getSal());
			pstmt.setString(5, eVO.getJobTel());
			pstmt.setString(6, eVO.getEname());
			pstmt.setString(7, eVO.getEmail());
			pstmt.setString(8, eVO.getAddr());
			pstmt.setString(9, eVO.getSsn());
			pstmt.setString(10, eVO.getTel());
			pstmt.setString(11, eVO.getSsn().substring(0,6));
			pstmt.setString(12, eVO.getHiredate());
			pstmt.execute();
		}finally {
			if(db!=null) {
				db.dbclose(null, pstmt, con);
			}//end if
		}//end finally
	}//insertEmp
	
	public List<String> selectEmp1() throws SQLException {
	    List<String> empList = new ArrayList<String>();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    DbConn db = DbConn.getInstance();

	    try {
	        con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
	        String sql = "SELECT E.EMPNO, E.ENAME FROM EMP E";

	        pstmt = con.prepareStatement(sql);

	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            int empNo = rs.getInt("empno");
	            String ename = rs.getString("ename");
	            String empInfo = ename + "/" + empNo; // 이름과 사원 번호를 합쳐서 저장
	            empList.add(empInfo);
	        } // while
	    } finally {
	        if (db != null) {
	            db.dbclose(rs, pstmt, con);
	        }//if
	    } // try
	    return empList;
	}// selectEmp

	public List<String> selectCombo() throws SQLException{
		List<String> comboList = new ArrayList<String>();
		
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    DbConn db = DbConn.getInstance();

	    try {
	        con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
	        String sql = "SELECT DNAME FROM DEPT union all select null from dual union all SELECT JOBNAME FROM JOB union all select null from dual union all SELECT LVNAME FROM JOBLEVEL";

	        pstmt = con.prepareStatement(sql);

	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            comboList.add(rs.getString(1));
	        } // while
	    } finally {
	        if (db != null) {
	            db.dbclose(rs, pstmt, con);
	        }//if
	    } // try
		
		return comboList;
	}//selectCombo
	
	public List<String> selectTeam(String deptName) throws SQLException{
		List<String> teamList = new ArrayList<String>();
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    DbConn db = DbConn.getInstance();

	    try {
	        con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
	        String sql = "SELECT t.TNAME FROM  TEAM t WHERE t.DEPTNO =(SELECT d.DEPTNO FROM DEPT d WHERE d.DNAME = ? )";

	        pstmt = con.prepareStatement(sql);
	        
	        pstmt.setString(1, deptName);

	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	teamList.add(rs.getString("TNAME"));
	        } // while
	    } finally {
	        if (db != null) {
	            db.dbclose(rs, pstmt, con);
	        }//if
	    } // try
		return teamList;
	}//selectTeam
}//class
