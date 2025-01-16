import java.util.List;
import java.util.ArrayList;

public class Map {
	List<Tile> myMap;
	int playerPosition;

private static String MAP_DATA =
" ########***\n" + 
" #......#***\n" + 
" #...@..#***\n" + 
" #......####\n" + 
" #......+...\n" + 
" ###########\n";

	public Map() {
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
					default:
						System.out.println(String.format("What on earth is '%s'", c));
						myMap.add(new Unplayable());
				}
				pos ++;
			}
			
			
			System.out.println(line);
				
				
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