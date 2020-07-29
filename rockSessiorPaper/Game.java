package rockSessiorPaper;

/* 20170969 컴퓨터학과 이지은
 * 20170971 컴퓨터학과 이채정
 */

public enum Game {
	SCISSORS(0), ROCK(1), PAPER(2), ERROR(-1);

	public static final String[][] language = { { "가위", "바위", "보" }, 
												{ "SCISSORS", "ROCK", "PAPER" }, 
												{ "ちょき", "ぐう", "ぱあ" } };
	
	//constructor
	//add whatever methods you need
	int input = 0;
	
	Game(int input) {
		this.input = input;
	}

	public static Game encode(String input) {
		// "가위" -> SCISSORS
		if(input.equals("가위")) {
			return SCISSORS;
		} else if(input.equals("바위")) {
			return ROCK;
		} else if (input.equals("보")) {
			return PAPER;
		} else {
			return ERROR;
		}
	}
	
	// Method Overloading
	public static Game encode(String input, int lang) {
		// "가위" -> SCISSORS
		if(input.equals(language[lang][0])) {
			return SCISSORS;
		} else if(input.equals(language[lang][1])) {
			return ROCK;
		} else if (input.equals(language[lang][2])) {
			return PAPER;
		} else {
			return ERROR;
		}
	}
	
	public String decode() {
		// ROCK -> "바위";
		switch(input) {
			case 0 :
				return "가위";
			case 1 :
				return "바위";
			case 2 :
				return "보";
			default :
				return "에러";
		}
	}
	
	// Method Overloading
	public String decode(int lang) {
		return language[lang][input];
	}
}
