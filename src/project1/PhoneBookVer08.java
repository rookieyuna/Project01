package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver08.AutoSaverT;
import project1.ver08.MenuItem;
import project1.ver08.PhoneBookManager;
import project1.ver08.MenuSelectException;

public class PhoneBookVer08 implements MenuItem
{
	public static void main(String[] args)
	{
		PhoneBookManager pbMgr= new PhoneBookManager();
		AutoSaverT autosaver = new AutoSaverT(pbMgr);
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			try{
				pbMgr.printMenu();
				
				int choice = scan.nextInt();
				if(choice<1 || choice>6) {
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
				case AUTO_SAVE:	
					pbMgr.autoSave(autosaver); break;
				case PROGRAM_EXIT: 
					pbMgr.saveData(); //주소록 데이터 직렬화
					System.out.println("프로그램을 종료합니다.");
					return;
				}			
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