package project1.ver09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleConnect {
	private Connection con;
	private Statement smt; 
	private PreparedStatement psmt; //동적쿼리 실행을 위한 객체
	private ResultSet rs;
	
	public OracleConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "kosmo";
			String pass = "1234";
			
			Connection con = DriverManager.getConnection(url,id,pass);
			
			if(con!=null) {
				System.out.println("오라클 연결 성공");
			}
		}
		catch(Exception e) {
			System.out.println("Oracle 연결시 예외발생");
			e.printStackTrace();
		}
	}
	
	//데이터삽입 (PreparedStatement 클래스 이용)
	public void dataInput(String name, String phone, String birth) { 
		try {
			String sql = "INSERT INTO phonebook_tb VALUES "
					+ " (seq_phonebook.nextval,?,?,?)";
			psmt = con.prepareStatement(sql);

			psmt.setString(1, name);
			psmt.setString(2, phone);
			psmt.setString(3, birth);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 입력되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(); //DB자원반납
		}
	}
	
	//데이터검색 (Statement 클래스 이용)
	public void dataSearch(String searchName) { 
		try {
			smt = con.createStatement();
			String sql = "SELECT * from phonebook_tb WHERE name LIKE '%"+ searchName +"%'";
			
			rs = smt.executeQuery(sql);
			
			while(rs.next()) {
				String idx = rs.getString(1); //id컬럼
				String name = rs.getString(2); //name컬럼
				String phone = rs.getString(3); //phone컬럼
				String birth = rs.getString(4); //birthday컬럼
				
				
				System.out.printf("%s %s %s %s\n", idx, name, phone, birth);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(); //DB자원반납
		}
	}
	
	//데이터삭제 (PreparedStatement 클래스 이용)
	public void dataDelete(String name) { 
		try {
			String sql = "DELETE FROM phonebook_tb WHERE name = '?'";
			psmt = con.prepareStatement(sql);

			psmt.setString(1, name);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 삭제되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(); //DB자원반납
		}
	}

	
	public void close() {
		try {
			if(smt!=null) smt.close();
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			System.out.println("DB자원 반납 완료");
		}
		catch (SQLException e) {
			System.out.println("자원반납 시 오류가 발생하였습니다.");
		}
	}
}
	
	
