import java.awt.Color;
import java.awt.Graphics;


public class HUD {

	//mostrará a barra de vida do jogador.
	
	public static int HEALTH = 100;;
	
	public int greenValue = 255;
	
	public void tick()
	{
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		greenValue = Game.clamp(greenValue, 0, 255);
		
		greenValue = (int) (HEALTH*2.5);
	}
	public void render(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 20);
		
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH*2, 20);
		
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 20);
		
		g.drawString(/*"Life:"+*/String.valueOf(HEALTH),105,30);
	}
}

