import java.util.List;
import java.util.ArrayList;

public class Player {
	private List<Key> keys = new ArrayList<>();
	
	
	public List<Key> getKeys() {
		return keys;
	}
	
	public void add(Key k) {
		keys.add(k);
	}
	
}