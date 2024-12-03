package pnarchive;

public class ColoredOutput {
	static final String FGBLACK = "\033[30m";
	static final String FGRED = "\033[31m";
	static final String FGGREEN = "\033[32m";
	static final String FGYELLOW = "\033[33m";
	static final String FGBLUE = "\033[34m";
	static final String FGMAGENTA = "\033[35m";
	static final String FGCYAN = "\033[36m";
	static final String FGWHITE = "\033[37m";
	static final String BGBLACK = "\033[40m";
	static final String BGRED = "\033[41m";
	static final String BGGREEN = "\033[42m";
	static final String BGYELLOW = "\033[43m";
	static final String BGBLUE = "\033[44m";
	static final String BGMAGENTA = "\033[45m";
	static final String BGCYAN = "\033[46m";
	static final String BGWHITE = "\033[47m";
	static final String RESET = "\033[0m";
	public static void printText(String msg, String fg, String bg) {
		String bgc = switch(fg.toLowerCase()) {
			case "black" -> FGBLACK;
			case "red" -> FGRED;
			case "green" -> FGGREEN;
			case "yellow" -> FGYELLOW;
			case "blue" -> FGBLUE;
			case "magenta" -> FGMAGENTA;
			case "cyan" -> FGCYAN;
			case "white" -> FGWHITE;
			default -> "";
		};
		String fgc = switch(bg.toLowerCase()) {
			case "black" -> BGBLACK;
			case "red" -> BGRED;
			case "green" -> BGGREEN;
			case "yellow" -> BGYELLOW;
			case "blue" -> BGBLUE;
			case "magenta" -> BGMAGENTA;
			case "cyan" -> BGCYAN;
			case "white" -> BGWHITE;
			default -> "";
		};
		System.out.print(fgc + bgc + msg + RESET);
	}
}
