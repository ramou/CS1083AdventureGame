  public class Game {
	private Map m = new Map();

	public static void main(String[] args) {
		Game g = new Game();
		g.play();

	}
	
	public void play() {
	
		try {
			while(true) {
			//Should we do this in the main method, or a play method?
			//We need a game loop!
				m.drawMap();
				for(String m: Map.messages) {
					System.out.println(m);
				}
				Map.messages.clear();
				
				m.doAction();

				
			
			}
		} catch (QuitException e) {
			System.out.println(e.getMessage());
		} catch (GameException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}