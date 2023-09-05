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
		if(hcyEDAO==null) {
			hcyEDAO=new HCYErpDAO();
		}
		return;
	}//constructor
	
	public static HCYErpDAO getInstance() {
		return hcyEDAO;
	}//getInstance
	

	
	public boolean selectLogin(EmpVO eVO) throws SQLException {
		
		boolean flag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		System.out.println("asd");
		
		DbConn db=DbConn.getInstance();
		
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select empno,pass from emp where empno=?";
			
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				eVO=new EmpVO(rs.getInt("empno"),rs.getString("pass"));
				System.out.println(eVO.getEmpNo());
			}
			if(eVO!=null) {
				flag=true;
			}
		} finally {
			// 6.연결 끊기

			db.dbclose(rs, pstmt, con);

		}
		
		return flag;
		
	}//selectLogin
	
}//class
