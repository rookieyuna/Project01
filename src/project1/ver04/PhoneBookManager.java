package project1.ver04;

import java.util.Scanner;

public class PhoneBookManager
{
	private PhoneInfo[] phoneInfoData; //주소록 저장 배열 선언
	private int numOfInfo;
	
	public PhoneBookManager() {
		phoneInfoData = new PhoneInfo[100];
		numOfInfo=0; // 배열저장 카운트용
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
	
	public void dataInput() { //주소록저장
		Scanner scan = new Scanner(System.in);
		System.out.println("#데이터 입력을 시작합니다.");
		System.out.println("1.일반/2.대학/3.회사");
		System.out.print("선택>>");
		int choice = scan.nextInt();
		scan.nextLine(); //버퍼삭제용
		
		System.out.print("이름: ");
		String name = scan.nextLine();
		System.out.print("전화번호: ");
		String phone = scan.nextLine();
		
		if(choice==1) {
			phoneInfoData[numOfInfo++] = new PhoneInfo(name, phone);
		}
		else if(choice==2) {
			System.out.print("전공: ");
			String major = scan.nextLine();
			System.out.print("학년: ");
			int grade = scan.nextInt();
			phoneInfoData[numOfInfo++] = new PhoneSchoolInfo(name, phone,major,grade);
		}
		else if(choice==3) {
			System.out.print("회사명: ");
			String company = scan.nextLine();
			phoneInfoData[numOfInfo++] = new PhoneCompanyInfo(name, phone, company);
		}
		
		System.out.println("#데이터 입력이 완료되었습니다.");
	}
	
	public void  dataSearch() { //주소록검색(이름기준)
		Scanner scan = new Scanner(System.in);
		System.out.println("#데이터 검색을 시작합니다.");
		System.out.print("이름: ");
		String searchName = scan.nextLine();
		
		for(int i=0; i<numOfInfo; i++) {
			if(searchName.compareTo(phoneInfoData[i].name)==0) {
				phoneInfoData[i].showPhoneInfo();
				System.out.println("#데이터 검색이 완료되었습니다.");
			}
		}
	}

	public void  dataDelete() { //주소록 삭제(이름기준)
		Scanner scan = new Scanner(System.in);
		System.out.println("#데이터 삭제를 시작합니다.");
		System.out.print("이름: ");
		String deleteName = scan.nextLine();
		
		int deleteIdx = -1;
		for(int i=0; i<numOfInfo; i++) {
			if(deleteName.compareTo(phoneInfoData[i].name)==0) {
				phoneInfoData[i] = null;
				System.out.println("#데이터 삭제가 완료되었습니다.");
				deleteIdx = i;
				numOfInfo--;
			}
		}
		if(deleteIdx==-1) {
			System.out.println("#삭제할 데이터가 없습니다.");
		}
		else {
			for(int i=deleteIdx; i<numOfInfo; i++) {
				phoneInfoData[i]=phoneInfoData[i+1];
			}
		}	
	}
	
	public void  dataAllShow() { //주소록전체출력
		for(int i=0; i<numOfInfo; i++) {
			phoneInfoData[i].showPhoneInfo();
		}
	}
	
}
