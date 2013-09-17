import java.awt.Point;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class MovingObject {

	@Attribute
	private int x;
	@Attribute
	private int y;
	@Attribute
	private boolean alive = true;
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point getPos(){
		return new Point(x, y);
	}
	
	public void die() throws InvalidDeathException{
		if(alive){
			alive = false;
		}
		else{
			throw new InvalidDeathException();
		}
	}
}
