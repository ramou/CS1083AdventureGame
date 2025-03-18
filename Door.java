import java.util.*;

public class Door extends Obstacle {
	private Key key;
	
	@Override
	public String toString() {
		return "+";
	}
	
	public Door(Key key) {
		this.key = key;
	}
	
	public boolean canSolve(Player p) {
		
		for(Key k: p.getKeys()) {
			if(key.equals(k)) {
				Map.messages.add("You used " + key);
				return true;
			}
		}
		
		Map.sp.play(SoundPlayer.SOUNDS.BONK);
		Map.messages.add("The door is locked and you bump into it. It appears to have keyhole for " + key);
		return false;

	}
	
	
}