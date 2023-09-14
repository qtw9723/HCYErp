package DB;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConn {

	private static DbConn dbConn;
	
	private DbConn() {
		
	}
	
	public static DbConn getInstance() {
		if(dbConn==null) {
			dbConn=new DbConn();
		}
		return dbConn;
	}
	
	/**
	 * DB연결을 얻는 일
	 * @param ip - DB서버주소
	 * @param id - DBO아이디
	 * @param pass - 비밀번호
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection(String ip,String id,String pass) throws SQLException {
		Connection con=null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url="jdbc:oracle:thin:@"+ip+":1521:orcl";
		con=DriverManager.getConnection(url,id,pass);
		
		return con;
		
	}
	
	public void dbclose(ResultSet rs,Statement stmt,Connection con) throws SQLException{
		
		if(rs!=null) {rs.close();}
		if(stmt!=null) {stmt.close();}
		if(con!=null) {con.close();}
	}
}
