package logger;

import java.util.ArrayList;

public class Logger {
	private final String BOLD = "\033[1m";
	private final String RESET_BOLD = "\033[0m";
	String id;
	public ArrayList<String> logs = new ArrayList<>();
	public static void log(String msg, boolean debug) {
		String s = "";
		if(debug){s = "[DEBUGGER]: ";}
		ColoredOutput.printText(s, "cyan", "");
		ColoredOutput.printText(msg + "\n", "black", "white");
	}
	public static void log(String msg) {
		ColoredOutput.printText("LOG-> ", "cyan", "");
		ColoredOutput.printText(msg + "\n", "black", "white");
	}
	public static void log(int msg) {
		ColoredOutput.printText("LOG-> ", "cyan", "");
		ColoredOutput.printText(msg + "\n", "black", "white");
	}
	
	public static void log(int msg, boolean debug) {
		String s = "";
		if(debug){s = "[DEBUGGER]: ";}
		ColoredOutput.printText(s, "cyan", "");
		ColoredOutput.printText(msg + "\n", "black", "white");
	}
	public Logger(String loggerid) {
		id = loggerid;
	}
	public void info(String msg) {
		System.out.println(BOLD + "{" + id + "}: " + RESET_BOLD + msg);
		logs.add(msg);
	}
	public void error(String error) {
		ColoredOutput.printText("error caught from " + id + ":\n	" + error + "\n", "red", "");
	}
	public void warn(String warning) {
		ColoredOutput.printText("warning from " + id + ":\n	" + warning + "\n", "yellow", "");
	}
	public ArrayList<String> dispose() {
		return(logs);
	}
	public void debug(String msg) {
		System.out.print(BOLD+"{"+id+"}");
		ColoredOutput.printText(" [DEBUG] "+RESET_BOLD, "cyan", "");
		System.out.println(msg);
	}
	public void debug(String msg, String src) {
		System.out.print(BOLD+"{"+id+"}");
		ColoredOutput.printText(" [DEBUG] "+RESET_BOLD, "cyan", "");
		System.out.println(msg + " (" + src + ")");
	}
	public LoggerData getLoggerInfo() {
		return(new LoggerData(id, dispose()));
	}
	public class LoggerData {
		public String name;
		public ArrayList<String> logs;
		public LoggerData(String name, ArrayList<String> logs) {
			this.name=name;this.logs=logs;
		}
	}
}
