package main;

public enum Difficulty {
	EASY(3), NORMAL(6), HARD(9);
	int value;
	public Difficulty next() {
		Difficulty[] states = values();
		int nextIndex = (this.ordinal() + 1) % states.length;
		return states[nextIndex];
	}
	private Difficulty(int value) {
		this.value = value;
	}
	public int get() {
		return(value);
	}
}