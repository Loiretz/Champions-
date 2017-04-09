import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Cursor extends GameObject{
	
	Handler handler;
	Texture tex = Game.getInstance();
	private GameObject player;
	
	private Animation punch;
	
	public Cursor(int x, int y, ID id)
	{
		super(x,y,id);
		
		punch = new Animation(1,tex.punch[0],tex.punch[1],tex.punch[2]);
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,64,48);
	}
	
	
	public void tick() {
		punch.runAnimation();
	}
	
	public void render(Graphics g) {
		
		/*Quadrado branco preenchido.
		 * 
		 * */
		g.setColor(Color.YELLOW);
		//g.drawRect(x, y, 48, 48);

		punch.drawAnimation(g, x, y-16, 64, 64);
		
	}
}