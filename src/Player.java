import java.awt.Point;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Player extends MovingObject {

	@Element
	private String name;
	@Attribute
	private int hp;

	public Player(){
		this.hp = 10;
		this.name = "Bob";
	}
	
	public Player(String nm, int health, int xpos, int ypos){
		
	}

	public void hurt(int amt){
		try{
			hp-=amt;
			if(hp <= 0){
				die();
			}
		} catch (InvalidDeathException e){
			e.printStackTrace();
		}
	}
	
	public int getHP(){
		return this.hp;
	}
	
	public String getName(){
		return this.name;
	}
}
