import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import org.simpleframework.xml.*;

@Root
public class Room {

	@Element
	private String roomName = "test-room";
	@Attribute
	private char tiles[][];
	@Attribute
	private int ROOM_WIDTH = 5;
	@Attribute
	private int ROOM_HEIGHT = 5;
	@Element
	private MovingObject player;
	
	public Room(){
		tiles = new char[ROOM_WIDTH][ROOM_HEIGHT];
		for(int i = 0; i < ROOM_WIDTH; i++){
			for(int j = 0; j < ROOM_HEIGHT; j++){
				tiles[i][j] = '.';
			}
		}
		player = new Player();
		player.setPos(0, 0);
	}
	
	public boolean movePlayer(int dx, int dy){
		Point playerPos = player.getPos();
		int newX = playerPos.x + dx;
		int newY = playerPos.y + dy;
		if(newX < 0 || newX >= ROOM_WIDTH || newY < 0 || newY >= ROOM_HEIGHT){
			return false;
		}
		player.setPos(newX, newY);
		return true;
	}

	public void draw(Graphics2D g2d) {
		// Draw floor/background
		g2d.translate(400-(32*ROOM_WIDTH)/2, 300-(32*ROOM_HEIGHT)/2);
		g2d.setColor(Color.GREEN);
		for(int i = 0; i < ROOM_WIDTH; i++){
			for(int j = 0; j < ROOM_HEIGHT; j++){
				g2d.fillRect(i*32, j*32, 32, 32);
			}
		}
		g2d.setColor(Color.RED);
		Point playerPos = player.getPos();
		g2d.fillOval(playerPos.x*32, playerPos.y*32, 32, 32);
	}
	
	public Player getPlayer(){
		return (Player)player;
	}
	
	public void setPlayer(Player p){
		player = p;
	}
}
