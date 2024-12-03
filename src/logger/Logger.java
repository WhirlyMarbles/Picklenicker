package logger;

import java.util.ArrayList;

public class Logger {
	private final String BOLD = "\033[1m";
	private final String RESET = "\033[0m";
	String id;
	public ArrayList<String> logs = new ArrayList<>();
	public Logger(String loggerid) {
		id = loggerid;
		this.info("created logger <" + loggerid + ">");
	}
	public void info(String msg) {
		System.out.println(id + ": " + msg);
		logs.add(msg);
	}
	public void error(String error) {
		System.out.println("error caught from " + id + ":\n" + error);
	}
	public void warn(String warning) {
		System.out.println(id + " warns:\n" + warning);
	}
	public ArrayList<String> dispose() {
		return(logs);
	}
	public void debug(String msg) {
		System.out.print(BOLD + "[DEBUG] " + RESET);
		info(msg);
	}
}
