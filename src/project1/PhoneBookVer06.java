package project1;

import java.util.InputMismatchException;
import java.util.Scanner;

import project1.ver06.MenuItem;
import project1.ver06.PhoneBookManager;
import project1.ver06.MenuSelectException;

public class PhoneBookVer06 implements MenuItem
{	
	public static void main(String[] args)
	{
		PhoneBookManager pbMgr= new PhoneBookManager();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			try{
				pbMgr.printMenu();
				
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
			scan.nextLine();
		}
	}
}