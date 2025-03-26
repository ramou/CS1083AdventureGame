import java.util.*;

public class Stairs extends Floor {
	
	
	public Stairs() {
		super('<');
	}
	

	@Override
	public void enter(Player p) throws WinException {
		throw new WinException("Congratulations, you have won!");
	}
	
}