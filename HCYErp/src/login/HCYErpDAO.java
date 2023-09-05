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
	

	
	public boolean selectLogin(int empno,String password) throws SQLException {
		
		boolean flag=false;
		
		EmpVO eVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		System.out.println("asd");
		
		DbConn db=DbConn.getInstance();
		
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select empno,pass from emp where empno=? and pass=?";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
			eVO=new EmpVO(rs.getInt("empno"),rs.getString("pass"));
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
