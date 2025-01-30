import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	List<Tile> myMap;
	public static List<String> messages = new ArrayList<>();
	Player p = new Player();
	int playerPosition;
	Scanner inputs = new Scanner(System.in);

private static String MAP_DATA =
" ########***\n" + 
" #a.....#***\n" + 
" #...@..#***\n" + 
" #......####\n" + 
" #b.....A..B\n" + 
" ###########\n";


	public static List<Key> allKeys = new ArrayList<>();


	public Map() {
		allKeys.add(new Key('a', "A round key"));
		allKeys.add(new Key('b', "A rectangular key"));
		
		
		myMap = new ArrayList<>();
		int pos = 0;
		for(String line : MAP_DATA.split("\n")) {
			for(char c : line.trim().toCharArray()) {
				switch(c) {
					case '#':
						myMap.add(new Wall());
					break;
					case '.':
						myMap.add(new Floor());
					break;
					case '+':
						myMap.add(new Floor());
						//We need to consider door logic!
					break;
					case '@':
						myMap.add(new Floor());
						playerPosition = pos;
					break;
					case '*':
						myMap.add(new Unplayable());
					break;
					default:
						Floor f = new Floor();
						myMap.add(f);
						for(Key key : allKeys) {
							if(c == key.getSymbol()) {
								f.add(key);
							}
						}
						
				}
				pos ++;
			}
			
			
			//System.out.println(line);
				
				
		}
		
	}
	

	
	public void doAction () throws GameException{
		System.out.println("use WASD to do stuff");
		char move = inputs.nextLine().charAt(0);
		//TODO: We're using nextLine  now, but we'll buffer moves later
		int oldPosition = playerPosition;
		try {
			switch(move) {
				case 'w':
					playerPosition += -11;
				break;
				case 'a':
					playerPosition += -1;
				break;
				case 's':
					playerPosition += +11;
				break;
				case 'd':
					playerPosition += +1;
				break;
				case 'i':
					for(Key k: p.getKeys()) {
						messages.add("You have " + k);
					}
				break;
				case ';':
					throw new QuitException("Thanks for playing! Goodbye.");
				
				default:
					
				break;
				
			}

			myMap.get(playerPosition).enter(p);
		} catch (CollisionException e) {
			playerPosition = oldPosition;
			java.awt.Toolkit.getDefaultToolkit().beep();
			messages.add(e.getMessage());
		}
		
	}
	
	public void drawMap() {
		for(int y = 0; y < 6; ++y) {
			for(int x = 0; x < 11; ++x) {
				if(y*11+x == playerPosition) System.out.print("@");
				else System.out.print(myMap.get(y*11+x));
			}
			System.out.println();
		}
	}

}