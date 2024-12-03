package assets;

import logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class PNReader {
	public PNReader() {
		this.LOGGER = new Logger("pnsreader");
	}
	Logger LOGGER;
	public PNData readPNS(String path) {
		String highscore = "";
		String difficulty = "";
		String line;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + path)));
			while((line=reader.readLine())!=null) {
				if(line.startsWith("difficulty ")) {
					difficulty = line.substring(line.indexOf("{") + 1, line.indexOf("}")).trim();
				}
				if(line.startsWith("high ")) {
					highscore = line.substring(line.indexOf("{") + 1, line.indexOf("}")).trim();
				}
			}
		}
		catch(IOException error) {
			LOGGER.info("cannot import pns file " + path);
		}
		return(new PNData(difficulty, parseInt(highscore)));
	}
}
