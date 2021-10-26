package project1.ver07;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class PhoneBookManager implements SubMenuItem
{
	private HashSet<PhoneInfo> phoneInfoData; //주소록 저장 배열 선언
	private int numOfInfo;
	
	public PhoneBookManager() {
		phoneInfoData = new HashSet<PhoneInfo>();
		//numOfInfo=0; // 배열저장 카운트용
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
		
		if(choice==NORMAL_FRIEND) {
			phoneInfoData.add(new PhoneInfo(name, phone));
		}
		else if(choice==SCHOOL_FRIEND) {
			System.out.print("전공: ");
			String major = scan.nextLine();
			System.out.print("학년: ");
			int grade = scan.nextInt();
			phoneInfoData.add(new PhoneSchoolInfo(name, phone,major,grade));
		}
		else if(choice==COMPANY_FRIEND) {
			System.out.print("회사명: ");
			String company = scan.nextLine();
			phoneInfoData.add(new PhoneCompanyInfo(name, phone, company));
		}
		
		System.out.println("#데이터 입력이 완료되었습니다.");
	}
	
	public void  dataSearch() { //주소록검색(이름기준)
		Scanner scan = new Scanner(System.in);
		System.out.println("#데이터 검색을 시작합니다.");
		System.out.print("이름: ");
		String searchName = scan.nextLine();
		
		Iterator<PhoneInfo> itr = phoneInfoData.iterator();
		while(itr.hasNext()) {
			PhoneInfo searchInfo = itr.next();
			if(searchName.equals(searchInfo.getName())) {
				searchInfo.showPhoneInfo();
			}
		}
		System.out.println("#데이터 검색이 완료되었습니다.");
	}
	

	public void  dataDelete() { //주소록 삭제(이름기준)
		Scanner scan = new Scanner(System.in);
		System.out.println("#데이터 삭제를 시작합니다.");
		System.out.print("이름: ");
		String deleteName = scan.nextLine();
		
		int deleteIdx = -1;
		
		Iterator<PhoneInfo> itr = phoneInfoData.iterator();
		while(itr.hasNext()) {
			PhoneInfo deleteInfo = itr.next();
			if(deleteName.equals(deleteInfo.getName())) {
				phoneInfoData.remove(deleteInfo);
				deleteIdx = 1;
			}
		}
		if(deleteIdx==-1) {
			System.out.println("#삭제할 데이터가 없습니다.");
		}
	}
	
	public void  dataAllShow() { //주소록전체출력
		System.out.println("#주소록을 출력합니다.");
		for(PhoneInfo info : phoneInfoData) {
			info.showPhoneInfo();
		}
	}
}
