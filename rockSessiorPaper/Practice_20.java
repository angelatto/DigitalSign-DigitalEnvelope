package rockSessiorPaper;

import java.util.Scanner;

/* 20170969 컴퓨터학과 이지은
 * 20170971 컴퓨터학과 이채정
 */
public class Practice_20 {

	static Score[][] scoreBoard = { { Score.EQUAL, Score.LOSE, Score.WIN },
									 { Score.WIN, Score.EQUAL, Score.LOSE },
									 { Score.LOSE, Score.WIN, Score.EQUAL } };
	
	static String[][] text = { { "컴퓨터의 입력 : ", "당신의 입력 : " }, 
							   { "Conputer : ", "You : " }, 
							   { "パソコン : ", "あなた : " } 		};
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("원하는 언어를 선택하세요 (1-한국어/2-영어/3-일본어) : ");
		int lang = s.nextInt() - 1;
		
		Game com = Game.SCISSORS;
		System.out.println(text[lang][0] + com.decode(lang));
		// 영어 >> 한글
		
		System.out.print(text[lang][1]);
		String yourInput = s.next();
		
		Game user = Game.encode(yourInput, lang); // 인코딩 오버로딩 안하구 수정
		
		Score rslt = whoswin(user, com);
		System.out.println(Score.print(rslt, lang));
		
		s.close();
	}

	public static Score whoswin(Game user, Game com) {
		return scoreBoard[user.input][com.input];
	}
}
