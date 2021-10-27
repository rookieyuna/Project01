package project1.ver09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
	public void dataInput() { //데이터삽입 (PreparedStatement 클래스 이용)
		try {
			String sql = "";
			psmt = con.prepareStatement(sql);
			
//			psmt.setString(1, name);
//			psmt.setString(2, phone);
//			psmt.setString(3, birth);
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void dataSearch() { //데이터검색 (Statement 클래스 이용)
		
	}
	
	public void dataDelete() { //데이터삭제 (PreparedStatement 클래스 이용)
		
	}
}
	
	
