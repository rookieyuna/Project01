package project1;

import java.util.Scanner;

import project1.ver04.PhoneBookManager;
import project1.ver04.PhoneInfo;

public class PhoneBookVer04
{
	public static void main(String[] args)
	{
		PhoneBookManager pbMgr= new PhoneBookManager();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			pbMgr.printMenu();
			
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
