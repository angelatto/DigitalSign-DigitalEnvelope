package common;

@SuppressWarnings("serial")
public class MakeFileException extends Exception {
	
	@Override
	public String toString() {
		return 	"Exception 발생 !! 파일 생성 오류.";
	}
}
