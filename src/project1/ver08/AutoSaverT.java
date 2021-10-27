package project1.ver08;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;

public class AutoSaverT extends Thread {
	
	@Override
	public void run()  {
		while(true) {
			System.out.println("▶주소록이 텍스트로 자동저장되었습니다.");
			
			try {
				sleep(5000);
				autosavebook();
			}
			catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public void autosavebook() {
		try{
			ObjectOutputStream out = new ObjectOutputStream
				(new FileOutputStream("src/project1/ver08/AutoSaveBook.txt"));
		
			HashSet<PhoneInfo> info = new HashSet<PhoneInfo>(100);
			info = PhoneBookManager.phoneInfoData;
			
			Iterator itr = info.iterator();
			while(itr.hasNext()) {
				out.writeObject(itr.next());	
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("자동저장 스트림 오류");
		}
		
		
	}
	
}
