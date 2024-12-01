package util;

public class Logger {
	String id;
	public Logger(String loggerid) {
		id = loggerid;
	}
	public void info(String msg) {
		System.out.println(id + ": " + msg);
	}
}
