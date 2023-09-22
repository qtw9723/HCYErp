package login;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RunHCYErp {

	public static void main(String[] args) {
		try {
			new HCYErp();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IO스트림에 문제 발생 프로그램을 종료합니다.");
			e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "데이터베이스에 문제 발생 프로그램을 종료합니다.");
			e.printStackTrace();
			System.exit(0);
		}// catch
	}//main

}//class
