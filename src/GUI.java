import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;


import javax.swing.JFrame;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;


public class GUI extends JFrame implements KeyListener {

	@Element
	private Room room;
	private View view;
	private BufferedImage buffer;
	private Serializer serializer;
	File outputFile;

	public GUI(){
		super("Save/Load to XML example");
		this.serializer = new Persister();
		outputFile = new File("save.xml");
		this.room = new Room();
		setupWindow();
	}

	private void setupWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		setSize(800, 600);
		setLayout(new BorderLayout());
		view = new View();
		add(view, BorderLayout.CENTER);
		setVisible(true);
		updateBuffer();
	}

	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) view.getGraphics();
		if (buffer != null) {
			g2.drawImage(buffer, 0, 0, this);
		}
	}

	private void updateBuffer() {
		int viewWidth = view.getWidth();
		int viewHeight = view.getHeight();
		buffer = new BufferedImage(viewWidth, viewHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = buffer.createGraphics();
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, viewWidth, viewHeight);
		room.draw(g2D);
		view.draw(g2D);
		g2D.dispose();
		repaint();
	}

	/**
	 * KeyListener methods
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code){
		case KeyEvent.VK_UP:
			room.movePlayer(0, -1);
			updateBuffer();
			break;
		case KeyEvent.VK_DOWN:
			room.movePlayer(0, 1);
			updateBuffer();
			break;
		case KeyEvent.VK_LEFT:
			room.movePlayer(-1, 0);
			updateBuffer();
			break;
		case KeyEvent.VK_RIGHT:
			room.movePlayer(1, 0);
			updateBuffer();
			break;
		case KeyEvent.VK_S:
			save();
			break;
		case KeyEvent.VK_L:
			load();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args){
		System.out.println("Starting...");
		new GUI();
	}

	public void save(){
		try{
			serializer.write(this, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void load(){
		try{
			serializer.read(this, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateBuffer();
	}
}
