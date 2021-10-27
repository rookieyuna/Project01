package project1.ver08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class PhoneBookManager implements SubMenuItem
{
	HashSet<PhoneInfo> phoneInfoData; //주소록 저장 배열 선언
	
	public PhoneBookManager() {
		phoneInfoData = new HashSet<PhoneInfo>();
		readData(); //역직렬화 메서드 호출
	}
	
	public void printMenu() {//선택메뉴 프린트
		System.out.println("▷선택하세요================");
		System.out.print("1.데이터 입력");
		System.out.println(" 2.데이터 검색");
		System.out.print("3.데이터 삭제");
		System.out.println(" 4.주소록 출력");		
		System.out.print("5.자동 저장");
		System.out.println(" 6.프로그램 종료");
		System.out.print("선택: ");
	}
	
	public void overlapCheck(PhoneInfo info) { //주소록 저장 중복 확인 메서드
		Scanner scan = new Scanner(System.in);
		
		System.out.println("이미 저장된 데이터입니다.");
		System.out.print("덮어쓸까요? Y(y)/N(n)=>");
		String yesOrNo = scan.nextLine();
		
		if(yesOrNo.equalsIgnoreCase("y")) {
			phoneInfoData.remove(info);
			phoneInfoData.add(info);
		}
		else if(yesOrNo.equalsIgnoreCase("n")) {
			System.out.println("==기존 주소록 출력==");
				info.showPhoneInfo();
			System.out.println("==주소록 출력 완료==");
		}
		else {
			System.out.println("※Y와 N중에서만 입력하세요");
		}
	}
	
	public void dataInput() { //1. 주소록저장
		try {
			Scanner scan = new Scanner(System.in);
			boolean check;
			System.out.println("#데이터 입력을 시작합니다.");
			System.out.println("1.일반/2.대학/3.회사");
			System.out.print("선택>>");
			int choice = scan.nextInt();
			scan.nextLine(); //버퍼삭제용
			
			if(choice<1 || choice>3) {
				MenuSelectException ex = new MenuSelectException();
				throw ex;
			}
			
			System.out.print("이름: ");
			String name = scan.nextLine();
			System.out.print("전화번호: ");
			String phone = scan.nextLine();
			
			if(choice==NORMAL_FRIEND) {
				PhoneInfo newinfo = new PhoneInfo(name, phone);
				check=phoneInfoData.add(newinfo);
				if(check==false) overlapCheck(newinfo);
			}
			else if(choice==SCHOOL_FRIEND) {
				System.out.print("전공: ");
				String major = scan.nextLine();
				System.out.print("학년(숫자): ");
				int grade = scan.nextInt();
				
				PhoneInfo newinfo = new PhoneSchoolInfo(name, phone,major,grade);
				check=phoneInfoData.add(newinfo);
				if(check==false) overlapCheck(newinfo);
				
			}
			else if(choice==COMPANY_FRIEND) {
				System.out.print("회사명: ");
				String company = scan.nextLine();
				
				PhoneInfo newinfo = new PhoneCompanyInfo(name, phone, company);
				check=phoneInfoData.add(newinfo);
				if(check==false) overlapCheck(newinfo);
			}
			System.out.println("#데이터 입력이 완료되었습니다.");
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
	}
	
	public void  dataSearch() { //2. 주소록검색(이름기준)
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
	

	public void  dataDelete() { //3. 주소록 삭제(이름기준)
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
				System.out.println("#데이터 삭제가 완료되었습니다.");
			}
		}
		if(deleteIdx==-1) {
			System.out.println("#삭제할 데이터가 없습니다.");
		}
	}
	
	public void  dataAllShow() { //4. 주소록전체출력
		System.out.println("#주소록을 출력합니다.");
		for(PhoneInfo info : phoneInfoData) {
			info.showPhoneInfo();
		}
	}
	AutoSaverT autosaver;
		
	public void autoSave(PhoneBookManager pbMgr) { //5. 자동저장옵션
		
		Scanner scan = new Scanner(System.in);
		System.out.println("#저장 옵션을 선택하세요.");
		System.out.println("1.자동저장On, 2.자동저장Off ");
		System.out.print("선택>>");
		int choice = scan.nextInt();
		
		try {
			if(choice==1) {
				if(Thread.activeCount()==1) {
					System.out.println("#자동저장을 시작합니다.");
					autosaver= new AutoSaverT(pbMgr);
					autosaver.setDaemon(true);
					autosaver.start();
				}
				else {
					System.out.println("#이미 자동저장이 실행중입니다.");
				}
			}
			else if (choice==2) {
				autosaver.interrupt();
				System.out.println("#자동저장을 종료합니다.");
			}
			
			if(choice<1 || choice>2) {
				MenuSelectException ex = new MenuSelectException();
				throw ex;
			}
		}
		catch (MenuSelectException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println("[예외]자동저장 시 문제발생");
			e.printStackTrace();
		}
	}
	
	
	public void saveData() { //주소록 데이터 저장 아웃스트림
		try {
			ObjectOutputStream dataOut = new ObjectOutputStream
					(new FileOutputStream("src/project1/ver08/PhoneBook.obj"));
			
			for(PhoneInfo info : phoneInfoData) {
				dataOut.writeObject(info);
			}
			System.out.println("obj파일로 저장되었습니다.");
			dataOut.close();
			
		}
		catch (Exception e) {
			System.out.println("주소록 저장 중 예외발생");
			e.printStackTrace();
		}
	}
	
	public void readData() { //주소록 데이터 불러오기 인스트림
		try {
			ObjectInputStream datain = new ObjectInputStream
					(new FileInputStream("src/project1/ver08/PhoneBook.obj"));
			while(true) {
				PhoneInfo info = (PhoneInfo)datain.readObject();
				phoneInfoData.add(info);
				if(info==null) break;
			}
		}
		catch (Exception e) {
			
		}
		System.out.println("★저장된 정보 불러오기 완료★");
	}
	
	public void autosavebook() {
		try{
			PrintWriter out = new PrintWriter(
					new FileWriter("src/project1/ver08/AutoSaveBook.txt"));
			
			for(PhoneInfo list : phoneInfoData) {
				if(list instanceof PhoneCompanyInfo) {
					out.printf("%s %s %s",list.getName(), list.getphoneNumber(), 
							((PhoneCompanyInfo) list).getCompany());
					out.println("");
				}
				else if(list instanceof PhoneSchoolInfo) {
					out.printf("%s %s %s %s",list.getName(), list.getphoneNumber(), 
							((PhoneSchoolInfo)list).getMajor(),((PhoneSchoolInfo)list).getGrade());
					out.println("");
				}
				else if(list instanceof PhoneInfo) {
					out.printf("%s %s",list.getName(), list.getphoneNumber());
					out.println("");
				}
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("자동저장 스트림 오류");
		}
	}
	
}
