import java.util.*;

public class Floor extends Tile {
	
	private List<Key> keys = new ArrayList<>();
	
	private List<Obstacle> obstacles = new ArrayList<>();
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
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
		try {
			for(Obstacle o: obstacles) {
				if(o.canSolve(p)) {
					solved.add(o);
				}
			}
		} catch (TrapException trape) {
			throw new TrapException(trape.getMessage(), this);
		}
		
		obstacles.removeAll(solved);
		
		if(!obstacles.isEmpty()) {
			throw new CollisionException("You bumped into a " + obstacles.get(0).getClass().getSimpleName());
		}
		
		List<Obstacle> adjacent = new LinkedList<>();
		if(getNorth() instanceof Floor) {
			Floor f = (Floor) getNorth();
			adjacent.addAll(f.getObstacles());	
		}
		
		if(getSouth() instanceof Floor) {
			Floor f = (Floor) getSouth();
			adjacent.addAll(f.getObstacles());	
		}
		
		if(getWest() instanceof Floor) {
			Floor f = (Floor) getWest();
			adjacent.addAll(f.getObstacles());	
		}
		
		if(getEast() instanceof Floor) {
			Floor f = (Floor) getEast();
			adjacent.addAll(f.getObstacles());	
		}

		
		for(Obstacle o : adjacent) {
			if(o instanceof Trap) {
				Map.sp.play(SoundPlayer.SOUNDS.SQUEEK);
			}
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