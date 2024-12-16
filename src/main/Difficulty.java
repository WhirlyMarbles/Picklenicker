package main;

import java.util.Arrays;
import java.util.HashMap;

public enum Difficulty {
	EASY(3), NORMAL(6), HARD(9), HACKED(100);
	int value;
	public Difficulty next() {
		Difficulty[] states = Arrays.copyOfRange(values(), 0, 3);
		int nextIndex = (this.ordinal() + 1) % states.length;
		return states[nextIndex];
	}
	Difficulty(int value) {
		this.value = value;
	}
	public int get() {
		return(value);
	}
	public static Difficulty fromString(String name) {
		for (Difficulty difficulty : values()) {
			if (difficulty.name().equalsIgnoreCase(name)) {
				return difficulty;
			}
		}
		throw new IllegalArgumentException("cannot process difficulty " + name);
	}
	@Override
	public String toString() {
		HashMap<Difficulty, String> str = new HashMap<>();
		str.put(EASY, "easy");
		str.put(NORMAL, "normal");
		str.put(HARD, "hard");
		str.put(HACKED, "HACKED!!!!!!!");
		return(str.get(this));
	}
}