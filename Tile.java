public abstract class Tile {
	private char symbol;
	
	private Tile north = SENTINEL, south = SENTINEL, east = SENTINEL, west = SENTINEL;
	
	Tile getNorth() {return north;}
	Tile getEast() {return east;}
	Tile getSouth() {return south;}
	Tile getWest() {return west;}
	
	public Tile(char c) {
		this.symbol = c;
	}
	
	@Override
	public String toString() {
		return symbol+"";
	}
	
	public void enter(Player p) throws GameException {
		throw new GameException("You can't go there!");
	}
	
	public void setAdjacent(Tile n, Tile e, Tile s, Tile w) {
		north = n;
		east = e;
		south = s;
		west = w;
	}
	
	public static Tile SENTINEL = new Sentinel();
	public static class Sentinel extends Tile {
		public Sentinel() {
			super(' ');
		}
	
		@Override
		public void enter(Player p) throws CollisionException {
			throw new CollisionException("You hit the end of the world!");
		}
	}	
	
}
