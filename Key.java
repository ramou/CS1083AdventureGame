public class Key {
	private char symbol;
	private String description;
	
	
	public Key(char s, String d) {
		this.symbol = s;
		this.description = d;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	@Override
	public String toString() {
		return description;
	}
}