package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DbConn;
import VO.EmpVO;

public class HCYErpDAO {

	private static HCYErpDAO hcyEDAO;
	private EmpVO eVO;
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
		
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		System.out.println("asd");
		
		DbConn db=DbConn.getInstance();
		
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select * from emp e,team t,dept d,job j,joblevel jl where (e.teamno=t.teamno(+) and t.deptno=d.deptno(+) and e.jobno=j.jobno(+) and e.levelno=jl.levelno(+)) and empno=?";
					
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
			eVO=new EmpVO();
			eVO.setEmpNo(rs.getInt("empno"));
			eVO.setAddr(rs.getString("addr"));
			eVO.setDept(rs.getString("dname"));
			eVO.setDeptLoc(rs.getString("loc"));
			eVO.setEmail(rs.getString("email"));
			eVO.setEname(rs.getString("ename"));
			eVO.setHiredate(rs.getString("hiredate"));
			eVO.setInputDate(rs.getDate("input_date"));
			eVO.setJob(rs.getString("jobname"));
			eVO.setLevel(rs.getString("lvname"));
			eVO.setPass(rs.getString("pass"));
			eVO.setSal(rs.getInt("sal"));
			eVO.setSsn(rs.getString("ssn"));
			eVO.setTeam(rs.getString("tname"));
			eVO.setTel(rs.getString("tel"));
			eVO.setTotalDayOff(rs.getInt("totaldayoff"));
			}
		} finally {
			db.dbclose(rs, pstmt, con);
		}
		if(eVO!=null) {
			flag=true;
		}
		return flag;
		
	}//selectLogin
	
	public void updatePassword(EmpVO eVO) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    DbConn db=DbConn.getInstance();
	    try {
	        con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
	        String sql = "UPDATE emp SET pass = ? WHERE empno = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, eVO.getSsn().substring(0,6));//비밀번호 초기화는 주민번호 앞 6자리
	        pstmt.setInt(2, eVO.getEmpNo());
	        
	        pstmt.execute();
	        
	    } finally {
	        db.dbclose(null, pstmt, con);
	    }//end finally
	}//updatePassword


	public EmpVO geteVO() {
		return eVO;
	}
	
}//class
