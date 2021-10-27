package project1.ver08;

public class PhoneSchoolInfo extends PhoneInfo
{
	String major;
	int grade;
	public PhoneSchoolInfo(String name, String phoneNumber, String major, int grade)
	{
		super(name, phoneNumber);
		this.major = major;
		this.grade = grade;
	}
	@Override
	public void showPhoneInfo()
	{
		super.showPhoneInfo();
		System.out.println("전공:"+ major);
		System.out.println("학년:"+ grade);
	}
	public String getMajor() {
		return major;
	}
	public int getGrade() {
		return grade;
	}
	
}
