package project1.ver08;

import java.io.Serializable;
import java.util.Objects;

public class PhoneInfo implements Serializable
{
	protected String name;//이름
	protected String phoneNumber; //전화번호
	
	//3개의 매개변수를 가진 생성자
	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public void showPhoneInfo() {		
		System.out.println("─ ─ ─ ─ ─ ─ ─ ─ ─ ─");
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
		System.out.println("─ ─ ─ ─ ─ ─ ─ ─ ─ ─");
	}
	
	public String getName()
	{
		return name;
	}
	public String getphoneNumber()
	{
		return phoneNumber;
	}
	

	@Override
	public int hashCode()
	{
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj)
	{
		PhoneInfo other = (PhoneInfo) obj;
		return Objects.equals(name, other.name) ;
	}
	
	
}
