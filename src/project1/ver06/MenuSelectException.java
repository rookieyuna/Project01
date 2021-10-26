package project1.ver06;

public class MenuSelectException extends Exception
{
	public MenuSelectException() {
		super("[예외] 1~5 사이의 숫자만 입력하세요!");
	}
}
