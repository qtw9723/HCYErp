package manageEmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;

public class ManageEmpDAO {

	private static ManageEmpDAO manageEmpDAO;
	
	private ManageEmpDAO() {
	}//constructor
	
	public static ManageEmpDAO getInstance() {
		
		return manageEmpDAO;
	}//getInstance
	
	public List<String> selectDept() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select dname from dept";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dname"));
			}

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return list;
	}// selectDept
	
	public List<String> selectTeam() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select tname from team where ~~~~~";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dname"));
			}

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return list;
	}// selectDept
	
	
}//class
