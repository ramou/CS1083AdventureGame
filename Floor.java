import java.util.*;

public class Floor extends Tile {
	
	private List<Key> keys = new ArrayList<>();
	
	public Floor() {
		super('.');
	}
	
	public void add(Key k) {
		keys.add(k);
	}
	
	@Override
	public String toString() {
		if(keys.isEmpty()) return super.toString();
		else return keys.get(0).getSymbol()+"";
	}	
	
	@Override
	public void enter(Player p) {
		if(!keys.isEmpty()) {
			for(Key k: keys) {
				p.add(k);
				Map.messages.add("You have found " + k);
			}
			keys.clear();
		}
	}
	
}