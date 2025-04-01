public class TrapException extends GameException {
	private Tile t = null;

	public Tile getTile () {return t;}

	public TrapException() {
		super();
	}

	public TrapException(String message) {
		super(message);
	}
	
		public TrapException(String message, Tile t) {
		super(message);
		this.t=t;
	}
}