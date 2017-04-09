import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy4 extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	Texture tex = Game.getInstance();
	
	private Animation enemy4WalkRight;
	private Animation enemy4WalkLeft;
	private Animation enemy4PunchRight;
	private Animation enemy4PunchLeft;
	private Animation punch;
	
	private boolean colide;
			
	public Enemy4(int x, int y,ID id,Handler handler )
	{
		super(x,y,id);
		this.handler = handler;
		
		for (int i=0; i<handler.object.size(); i++)
		{
			if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
		}
		
		enemy4WalkRight = new Animation(3, tex.enemy4[1],tex.enemy4[0]);
		enemy4WalkLeft = new Animation(3, tex.enemy4[17],tex.enemy4[16]);
		enemy4PunchRight = new Animation(1,tex.enemy4[4]);
		enemy4PunchLeft = new Animation(1,tex.enemy4[20]);
		punch = new Animation(5,tex.punche4[0],tex.punche4[1],tex.punche4[2]);
		
		velX = 1;
		velY = 1;
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,32,48);
	}
	
	public boolean collision()
	{
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			//O objeto temporário aqui será os inimigos.
			if (tempObject.getID() == ID.Cursor)
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					HUD_Enemy.HEALTH -= 1;
					colide = true;
				}
				else colide = false;
			}
		}
		return colide;
	}
	
	public void tick()
	{
		
		
		x+=(-1.1)*player.getVelX() ;
		y+=(-1.1)*player.getVelY();
		
		if(player.getDirection() == 1) direction = -1;
		else if(player.getDirection() == -1) direction = 1;
		
		x = Game.clamp(x, 20, Game.LARGURA-100);
		y = Game.clamp(y, 280, Game.ALTURA-100);
		
		punch.runAnimation();
		enemy4PunchLeft.runAnimation();
		enemy4PunchRight.runAnimation();
		enemy4WalkLeft.runAnimation();
		enemy4WalkRight.runAnimation();
		
		collision();	
	}
	
	
	public void render(Graphics g)
	{
		g.setColor(Color.GREEN);
		//g.fillRect(x, y, 32, 32);
		
		if(player.getVelX() != 0 ||player.getVelY() != 0)
		{
			if(direction == 1)
				enemy4WalkRight.drawAnimation(g, x, y-48, 64, 98);
			else if(direction == -1)
				enemy4WalkLeft.drawAnimation(g, x, y-48, 64, 98);
		}
		
		else if(player.getVelX() == 0 && player.getVelY() == 0)
		{
			if(direction == 1)
				g.drawImage(tex.enemy4[0], x, y-48,64,98, null);
			else
				g.drawImage(tex.enemy4[16], x, y-48,64,98, null);
		}
		
		if(player.getPunch() == true ){
			if(direction == -1) enemy4PunchLeft.drawAnimation(g, x, y-48, 64, 98);
			if(direction == 1) enemy4PunchRight.drawAnimation(g, x, y-48, 64, 98);
		}		
	}

}
