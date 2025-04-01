import java.util.*;

public class Trap extends Obstacle {
	boolean visible;
	
	@Override
	public String toString() {
		return visible?"^":".";
	}
	
	public Trap(boolean visible) {
		this.visible = visible;
	}
	
	public boolean canSolve(Player p) throws GameException{		
		if(visible) {
			Map.messages.add("You can't step there, you don't want to mess up your nice body.");
			return false;
		}
	
		Map.sp.play(SoundPlayer.SOUNDS.DEATH);
		
		throw new TrapException("The floor has collapsed below you, and you plummet to a pit of spikes.");

	}
	
	
}