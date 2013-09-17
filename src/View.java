import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class View extends JComponent {
	
	public View(){
		setVisible(true);
	}

	public void draw(Graphics2D g2D) {
		g2D.setColor(Color.WHITE);
		g2D.drawString("S to save position,  L to load", 10, 10);
	}
}
