import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy1 extends GameObject{
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Animation enemy1WalkRight;
	private Animation enemy1WalkLeft;
			
	public Enemy1(int x, int y,ID id,Handler handler )
	{
		super(x,y,id);
		this.handler = handler;
		
		enemy1WalkRight = new Animation(3, tex.enemy1[1],tex.enemy1[0]);
		enemy1WalkLeft = new Animation(3, tex.enemy1[17],tex.enemy1[16]);
		
		velX = 2;
		velY = 2;
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,32,48);
	}
	
	public void collision()
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
				}
			}
			
		}
	}
	
	public void tick()
	{
		x+=velX;
		y+=velY;
		
		if(velX < 0) direction = -1;
		else if(velX > 0) direction = 1;
		
		if(y <= 128 || y>= Game.ALTURA - 256) velY *= -1;
		if(x <= 64 || x>= Game.LARGURA - 128) velX *= -1;
		
		collision();
		
		enemy1WalkLeft.runAnimation();
		enemy1WalkRight.runAnimation();
		
		//handler.addObject(new Trail(x,y,ID.Trail,16,16,0.08f,Color.PINK,handler));
	}
	public void render(Graphics g)
	{
		//g.setColor(Color.RED);
		//g.fillRect(x, y, 16, 16);
		
		if(velX != 0 ||velY != 0)
		{
			if(direction == 1)
				enemy1WalkRight.drawAnimation(g, x, y-48, 64, 98);
			else if(direction == -1)
				enemy1WalkLeft.drawAnimation(g, x, y-48, 64, 98);
		}
		
		
		else if(velX == 0 && velY == 0)
		{
			if(direction == 1)
				g.drawImage(tex.enemy1[0], x, y-48,64,98, null);
			else
				g.drawImage(tex.enemy1[16], x, y-48,64,98, null);
		}
		
	}

}
