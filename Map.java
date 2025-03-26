import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Map {
	public static SoundPlayer sp;
	
	List<Tile> myMap;
	public static List<String> messages = new ArrayList<>();
	Player p = new Player();
	Tile playerPosition;
	
	
	int mapWidth = 0;
	int mapHeight = 0;
	Scanner inputs = new Scanner(System.in);

private String mapData = "";


	public static List<Key> allKeys = new ArrayList<>();


	public Map(String mapFile, String keyFile) {
		try {
		sp = new SoundPlayer("sound_config.txt");
		} catch(Exception e){}
		try(BufferedReader br = new BufferedReader(new FileReader(mapFile))) {
			String line;
			while((line = br.readLine()) != null) {
				mapWidth = line.length();
				mapHeight++;
				mapData += line + "\n";
			}
		} catch (IOException e) {
			System.out.println("Your map file is broken.");
		}
		
		makeKeys(keyFile);
		
		
		myMap = new ArrayList<>();
		int pos = 0;
		for(String line : mapData.split("\n")) {
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
						Tile t = new Floor();
						myMap.add(t);
						playerPosition = t;
					break;
					case '*':
						myMap.add(new Unplayable());
					break;
					case '<':
						myMap.add(new Stairs());
					break;
					default:
						Floor f = new Floor();
						myMap.add(f);
						for(Key key : allKeys) {
							if(c == key.getSymbol()) {
								f.add(key);
							}
							if(c+32 == key.getSymbol()) {
								f.add(new Door(key));
							}
						}
						
				}
				pos ++;
			}			
				
		}
		
					for(int y = 0; y < mapHeight; ++y) {
				for(int x = 0; x < mapWidth; ++x) {
					Tile n = Tile.SENTINEL;
					Tile e = Tile.SENTINEL;
					Tile s = Tile.SENTINEL;
					Tile w = Tile.SENTINEL;
					if(y > 0) {
						n = myMap.get((y-1)*mapWidth + x);
					}
					if(y < (mapHeight-2)) {
						s = myMap.get((y+1)*mapWidth + x);
					}
					if(x > 0) {
						w = myMap.get(y*mapWidth + (x-1));
					}
					if(x < (mapWidth-2)) {
						e = myMap.get(y*mapWidth + (x+1));
					}
					
					myMap.get(y*mapWidth + x).setAdjacent(n,e,s,w);
					
					

				}			
			}
		
	}
	

	
	public void doAction () throws GameException{
		System.out.println("use WASD to do stuff");
		char move = inputs.nextLine().charAt(0);
		//TODO: We're using nextLine  now, but we'll buffer moves later
		Tile oldPosition = playerPosition;
		try {
			switch(move) {
				case 'w':
					playerPosition.getNorth().enter(p);
					playerPosition = playerPosition.getNorth();
					
				break;
				case 'a':
					playerPosition.getWest().enter(p);
					playerPosition = playerPosition.getWest();
				break;
				case 's':
					playerPosition.getSouth().enter(p);
					playerPosition = playerPosition.getSouth();
				break;
				case 'd':
					playerPosition.getEast().enter(p);
					playerPosition = playerPosition.getEast();
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

		} catch (CollisionException e) {
			playerPosition = oldPosition;
			//java.awt.Toolkit.getDefaultToolkit().beep();
			sp.play(SoundPlayer.SOUNDS.BONK);
			messages.add(e.getMessage());
		} catch (WinException e) {
			sp.play(SoundPlayer.SOUNDS.ITEM_PICKUP,
				SoundPlayer.SOUNDS.ITEM_PICKUP,
				SoundPlayer.SOUNDS.ITEM_PICKUP);

			throw new QuitException(e.toString());
		}
		
	}
	
	public void drawMap() {
		for(int y = 0; y < mapHeight; ++y) {
			for(int x = 0; x < mapWidth; ++x) {
				if(myMap.get(y*mapWidth+x) == playerPosition) System.out.print("@");
				else System.out.print(myMap.get(y*mapWidth+x));
			}
			System.out.println();
		}
	}

	public void makeKeys(String keyFile){
		
		try(BufferedReader br = new BufferedReader(new FileReader(keyFile))) {
			String line;
			while((line = br.readLine()) != null) {		
				allKeys.add(new Key(line.charAt(0), line.substring(2)));
			}
		} catch (IOException e) {
			System.out.println("Your key file is broken.");
		}
		
	}

}
