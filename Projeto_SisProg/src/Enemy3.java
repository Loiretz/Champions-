import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy3 extends GameObject{
	
	private Handler handler;
	Texture tex = Game.getInstance();
	
	private Animation enemy3WalkRight;
	private Animation enemy3WalkLeft;
	
	public Enemy3(int x, int y,ID id,Handler handler )
	{
		super(x,y,id);
		this.handler = handler;
		
		enemy3WalkRight = new Animation(3, tex.enemy3[1],tex.enemy3[0]);
		enemy3WalkLeft = new Animation(3, tex.enemy3[17],tex.enemy3[16]);
		
		velX = 3;
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
		
		if(x>=Game.LARGURA - 128 && y>=Game.ALTURA - 128){velX=0;velY=-4;}
		if(x>=Game.LARGURA - 128 && y<= 128){velX=-4;velY=0;}
		if(x<=128 && y>=128){velX=0;velY=4;}
		if(x<=128 && y>= Game.ALTURA - 128){velX=4;velY=0;}
		
		//handler.addObject(new Trail(x+16,y+16,ID.Trail,16,16,0.08f,new Color(70,10,180),handler));
		
		enemy3WalkLeft.runAnimation();
		enemy3WalkRight.runAnimation();
		
		collision();
	}
	public void render(Graphics g)
	{
		g.setColor(Color.CYAN);
		//g.fillRect(x, y, 32, 32);
		
		if(velX != 0 ||velY != 0)
		{
			if(direction == 1)
				enemy3WalkRight.drawAnimation(g, x, y-48, 64, 98);
			else if(direction == -1)
				enemy3WalkLeft.drawAnimation(g, x, y-48, 64, 98);
		}
		
		
		else if(velX == 0 && velY == 0)
		{
			if(direction == 1)
				g.drawImage(tex.enemy3[0], x, y-48,64,98, null);
			else
				g.drawImage(tex.enemy3[16], x, y-48,64,98, null);
		}
	}

}
