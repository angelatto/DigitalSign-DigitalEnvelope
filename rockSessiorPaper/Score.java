package rockSessiorPaper;

/* 20170969 컴퓨터학과 이지은
 * 20170971 컴퓨터학과 이채정
 */
public enum Score {
	WIN, LOSE, EQUAL;
	
	public static final String[][] rslt = { { "당신이 이겼습니다.", "컴퓨터가 이겼습니다.", "비겼습니다." }, 
											{ "You Wins!", "Computer Wins!", "It's a tie." }, 
											{ "あなたが勝ちました。", "コンピューターが勝ちました。", "引き分けました。" } };
	// add whatever methods you need
	public static String print(Score rslt) {
		if(rslt == WIN) {
			return "당신이 이겼습니다.";
		} else if(rslt == LOSE) {
			return "컴퓨터가 이겼습니다.";
		} else {
			return "비겼습니다.";
		}
	}
	
	// Method Overloading
	public static String print(Score score, int lang) {
		if(score == WIN) {
			return rslt[lang][0];
		} else if(score == LOSE) {
			return rslt[lang][1];
		} else {
			return rslt[lang][2];
		}
	}
}
