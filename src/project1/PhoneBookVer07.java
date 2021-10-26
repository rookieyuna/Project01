package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver07.MenuItem;
import project1.ver07.PhoneBookManager;
import project1.ver07.MenuSelectException;

public class PhoneBookVer07 implements MenuItem
{
	public static void printMenu() {
		System.out.println("▷선택하세요================");
		System.out.print("1.데이터 입력");
		System.out.println(" 2.데이터 검색");
		System.out.print("3.데이터 삭제");
		System.out.println(" 4.주소록 출력");
		System.out.println("5.프로그램 종료");
		System.out.print("선택: ");
	}
	
	public static void main(String[] args)
	{
		PhoneBookManager pbMgr= new PhoneBookManager();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			try{
				printMenu();
				
				int choice = scan.nextInt();
				if(choice<1 || choice>5) {
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}
				switch(choice) {
				case DATA_INPUT: 
					pbMgr.dataInput(); break;
				case DATA_SEARCH:
					pbMgr.dataSearch(); break;
				case DATA_DELETE:
					pbMgr.dataDelete(); break;
				case SHOW_ALLDATA:
					pbMgr.dataAllShow(); break;
				case PROGRAM_EXIT: 
					System.out.println("프로그램을 종료합니다.");
					return;
				}			
			}
			catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
				System.out.println("[예외]숫자만 입력하세요");
			}
			catch (Exception e) {
				System.out.println("[예외]알 수 없는 예외발생");
				e.printStackTrace();
			}
			scan.nextLine();//오류발생시 무한루프 방지용
		}
	}
}