import java.awt.Color;
import java.awt.Graphics;

//Mostrará a vida do inimigo.

public class HUD_Enemy {

public static int HEALTH = 100;;
	
	public int greenValue = 255;
	public int redValue = 255;
	
	public void tick()
	{
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		redValue = Game.clamp(redValue, 0, 255);
		
		redValue = (int) (HEALTH*2.5);
	}
	public void render(Graphics g)
	{
		
		g.setColor(Color.GRAY);
		g.fillRect(Game.LARGURA - 225, 15, 200, 20);
		
		
		g.setColor(new Color(redValue, 0, 75));
		g.fillRect(Game.LARGURA - 225, 15, HEALTH*2, 20);
		
		g.setColor(Color.WHITE);
		g.drawRect(Game.LARGURA - 225, 15, 200, 20);
		
		g.drawString(String.valueOf(HEALTH),Game.LARGURA - 135,30);
		
	}
}
