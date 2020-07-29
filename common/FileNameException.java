package common;


@SuppressWarnings("serial")
public class FileNameException extends Exception  {
	
	@Override
	public String toString() {
		return 	"Exception 발생 !! 정상적인 파일 이름이 아닙니다.";
	}
}
