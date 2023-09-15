package fileServer;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;

public class ServerDAO {
	private static ServerDAO sDAO;
	
	private ServerDAO() {
	}//class
	
	public static ServerDAO getInstance() {
		if(sDAO==null) {
			sDAO = new ServerDAO();
		}//if
		return sDAO;
	}//getInstance
	
	public void synchronize(File[] files) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			con.setAutoCommit(false);
			
			//실제 파일과 데이터 비교
			String sql = "select docname from doc";

			pstmt = con.prepareStatement(sql);
			

			rs = pstmt.executeQuery();
			List<String> removeFile = new ArrayList<String>();
			List<String> addFile = new ArrayList<String>();
			
			for(File file:files) {
				String filePath = file.getAbsolutePath();
				addFile.add(filePath.substring(filePath.lastIndexOf("\\")+1));
			}//for
			
			while (rs.next()) {
				String fileName = rs.getString("docname");
				removeFile.add(fileName);
				for(File file:files) {
					String filePath = file.getAbsolutePath();
					if(filePath.substring(filePath.lastIndexOf("\\")+1).equals(fileName)) {
						removeFile.remove(fileName);
						addFile.remove(fileName);
						break;
					}//if
				}//for
			} // while
			pstmt.close();
			
			//실파일 없는 데이터 지우기
			StringBuilder deleteSql = new StringBuilder();
			deleteSql.append("DELETE FROM DOC WHERE DOCNAME in (?");
			for(int i = 0 ; i<removeFile.size()-1;i++) {
				deleteSql.append(",?");
			}//for
			deleteSql.append(")");
			
			System.out.println(deleteSql.toString()+"/"+removeFile.size());
			
			pstmt = con.prepareStatement(deleteSql.toString());
			
			if(removeFile.size()!=0) {
			for(int i = 0 ; i<removeFile.size();i++) {
				System.out.println(i);
				pstmt.setString(i+1, removeFile.get(i));
			}//for
			
			pstmt.execute();

			pstmt.close();
			}
			//데이터 없는 실파일 데이터에 추가
			
			sql = "INSERT INTO DOC(DEPTNO, DOCNAME) VALUES ( ? , ? )";
			
			pstmt = con.prepareStatement(sql);
			
			for(String name:addFile) {
				pstmt.setInt(1, 10);
				pstmt.setString(2, name);
				pstmt.execute();
			}//for
			
			con.commit();
			
		} finally {
			db.dbclose(rs, pstmt, con);
		} // try
	}//synchronize
}//class
