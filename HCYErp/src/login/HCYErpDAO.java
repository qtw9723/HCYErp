package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DbConn;
import VO.EmpVO;

public class HCYErpDAO {

	private static HCYErpDAO hcyEDAO;
	
	private HCYErpDAO() {
		
	}//constructor
	
	public static HCYErpDAO getInstance() {
		if(hcyEDAO==null) {
			hcyEDAO=new HCYErpDAO();
		}
		return hcyEDAO;
	}//getInstance
	

	
	public boolean selectLogin(int empno) throws SQLException {
		
		boolean flag=false;
		
		EmpVO eVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		System.out.println("asd");
		
		DbConn db=DbConn.getInstance();
		
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select * from emp where empno=?";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
			eVO=new EmpVO();
			eVO.setEmpNo(rs.getInt("empno"));
			eVO.setAddr(rs.getString("addr"));
			eVO.setDept(rs.getString("dept"));
			eVO.setDeptLoc(rs.getString("deptloc"));
			eVO.setEmail(rs.getString("email"));
			eVO.setEname(rs.getString("ename"));
			eVO.setHiredate(rs.getDate("hiredate"));
			eVO.setInputDate(rs.getDate("inputdate"));
			eVO.setJob(rs.getString("job"));
			eVO.setLevel(rs.getString("level"));
			eVO.setPass(rs.getString("pass"));
			eVO.setSal(rs.getInt("sal"));
			eVO.setSsn(rs.getString("ssn"));
			eVO.setTeam(rs.getString("team"));
			eVO.setTel(rs.getString("tell"));
			eVO.setTotalDayOff(rs.getInt("totaldayoff"));
			eVO.setEmpNo(rs.getInt("empno"));
			}
		} finally {
			db.dbclose(rs, pstmt, con);
		}
		if(eVO!=null) {
			flag=true;
		}
		System.out.println(flag);
		return flag;
		
	}//selectLogin
	
}//class
