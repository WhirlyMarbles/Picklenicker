package logger;

import java.util.ArrayList;

public class Logger {
	private final String BOLD = "\033[1m";
	private final String RESET_BOLD = "\033[0m";
	String id;
	public ArrayList<String> logs = new ArrayList<>();
	public Logger(String loggerid) {
		id = loggerid;
		this.info("created logger <" + loggerid + ">");
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
		System.out.print(BOLD + "[DEBUG] " + RESET_BOLD);
		info(msg);
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
