package main;

public enum GameState {
	TITLE, GAME, GAME_OVER;
	public GameState next() {
		GameState[] states = values();
		int nextIndex = (this.ordinal() + 1) % states.length;
		return states[nextIndex];
	}
}
