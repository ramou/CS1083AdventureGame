import java.util.*;

public class Floor extends Tile {
	
	private List<Key> keys = new ArrayList<>();
	
	private List<Obstacle> obstacles = new ArrayList<>();
	
	public Floor(char c) {
		super(c);
	}
	
	public Floor() {
		super('.');
	}
	
	public void add(Key k) {
		keys.add(k);
	}
	
	public void add(Obstacle o) {
		obstacles.add(o);
	}
	
	
	@Override
	public String toString() {
		if(!obstacles.isEmpty()) return obstacles.get(0).toString();
		if(keys.isEmpty()) return super.toString();
		else return keys.get(0).getSymbol()+"";
	}	
	
	@Override
	public void enter(Player p) throws GameException {
		
		List<Obstacle> solved = new ArrayList<>();
		
		for(Obstacle o: obstacles) {
			if(o.canSolve(p)) {
				solved.add(o);
			}
		}
		obstacles.removeAll(solved);
		
		if(!obstacles.isEmpty()) {
			throw new CollisionException("You bumped into a " + obstacles.get(0).getClass().getSimpleName());
		}
		
		
		if(!keys.isEmpty()) {
			for(Key k: keys) {
				p.add(k);
				Map.sp.play(SoundPlayer.SOUNDS.ITEM_PICKUP);
				Map.messages.add("You have found " + k);
			}
			keys.clear();
		}
	}
	
}