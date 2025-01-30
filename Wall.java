public class Wall extends Tile {
	
	//I'm not sure if this is a good idea...
	public Wall() {
		super('#');
	}

	@Override
	public void enter(Player p) throws CollisionException {
		throw new CollisionException("You hit a wall!");
	}
		
}