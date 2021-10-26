package project1;

import java.util.Scanner;

import project1.ver02.PhoneInfo;

public class PhoneBookVer02
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("선택하세요...");
			System.out.println("1.데이터입력");
			System.out.println("2.프로그램 종료");
			System.out.print("선택: ");
			int choice = scan.nextInt();
			scan.nextLine();
			
			if(choice==1) {
				System.out.print("이름: ");
				String name = scan.nextLine();
				System.out.print("전화번호: ");
				String phone = scan.nextLine();
				System.out.print("생년월일: ");
				String birth = scan.nextLine();
				
				PhoneInfo pi1 = new PhoneInfo(name,phone,birth);
				pi1.showPhoneInfo();
			}
			else {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}
