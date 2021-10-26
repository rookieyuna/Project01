package project1;

import java.util.Scanner;

import project1.ver03.PhoneBookManager;
import project1.ver03.PhoneInfo;

public class PhoneBookVer03
{
	public static void printMenu() {
		System.out.println("▷선택하세요...");
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
			printMenu();
			
			int choice = scan.nextInt();
			switch(choice) {
			case 1: 
				pbMgr.dataInput(); break;
			case 2:
				pbMgr.dataSearch(); break;
			case 3:
				pbMgr.dataDelete(); break;
			case 4:
				pbMgr.dataAllShow(); break;
			case 5: 
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
	}
}
