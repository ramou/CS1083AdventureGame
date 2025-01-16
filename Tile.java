public abstract class Tile {
	private char symbol;
	
	public Tile(char c) {
		this.symbol = c;
	}
	
	@Override
	public String toString() {
		return symbol+"";
	}
}