package entity;

public class Bomb extends Entity {
	@Override
	public String toString() {
		return(String.format("Bomb object @("+x+", "+y+")"));
	}
}
