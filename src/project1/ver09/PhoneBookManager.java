package project1.ver09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PhoneBookManager
{
	private Connection con;
	private Statement smt;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	public PhoneBookManager() {
		oracleConnect();
	}
	
	public void oracleConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "kosmo";
			String pass = "1234";
			
			con = DriverManager.getConnection(url,id,pass);
			
			if(con!=null) {
				System.out.println("오라클 연결 성공");
			}
		}
		catch(Exception e) {
			System.out.println("Oracle 연결시 예외발생");
			e.printStackTrace();
		}
	}
	
	public void printMenu() {
		System.out.println("▷선택하세요================");
		System.out.print("1.데이터 입력");
		System.out.println(" 2.데이터 검색");
		System.out.print("3.데이터 삭제");
		System.out.println(" 4.주소록 출력");
		System.out.println("5.프로그램 종료");
		System.out.print("선택: ");
	}
	
	
	public void dataInput() { //1. 주소록저장
		//데이터삽입 (PreparedStatement 클래스 이용)
		
		try {
			System.out.println("#데이터 입력을 시작합니다.");
			
			String sql = "INSERT INTO phonebook_tb VALUES "
					+ " (seq_phonebook.nextval,?,?,?)";
			psmt = con.prepareStatement(sql);
			
			Scanner scan = new Scanner(System.in);
			System.out.print("이름: ");
			String name = scan.nextLine();
			System.out.print("전화번호: ");
			String phone = scan.nextLine();
			System.out.print("생년월일: ");
			String birth = scan.nextLine();
			
			psmt.setString(1, name);
			psmt.setString(2, phone);
			psmt.setString(3, birth);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 입력되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void  dataSearch() { //2. 주소록검색(이름기준)
		//데이터검색 (Statement 클래스 이용)
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("#데이터 검색을 시작합니다.");
			System.out.print("이름: ");
			String searchName = scan.nextLine();
			
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
			System.out.println("#데이터 검색을 완료되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void  dataDelete() { //3. 주소록 삭제(이름기준)
		//데이터삭제 (PreparedStatement 클래스 이용)
		Scanner scan = new Scanner(System.in);
		try {
			String sql = "DELETE FROM phonebook_tb WHERE name = ?";
			psmt = con.prepareStatement(sql);
			
			System.out.println("#데이터 삭제를 시작합니다.");
			System.out.print("이름: ");
			String deleteName = scan.nextLine();
			psmt.setString(1, deleteName);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 삭제되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void  dataAllShow() { //4. 주소록전체출력
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("#주소록 전체정보를 출력합니다.");
			
			smt = con.createStatement();
			String sql = "SELECT * from phonebook_tb ORDER BY idx";
			
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
