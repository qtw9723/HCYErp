package manageEmpRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;
import VO.AttendanceVO;
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
	
}//class
