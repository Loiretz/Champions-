import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy2 extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	Texture tex = Game.getInstance();
	
	private Animation enemy2WalkRight;
	private Animation enemy2WalkLeft;
			
	public Enemy2(int x, int y,ID id,Handler handler )
	{
		super(x,y,id);
		this.handler = handler;
		
		for (int i=0; i<handler.object.size(); i++)
		{
			if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
		}
		
		enemy2WalkRight = new Animation(3, tex.enemy2[1],tex.enemy2[0]);
		enemy2WalkLeft = new Animation(3, tex.enemy2[17],tex.enemy2[16]);
		
		
		velX = 8;
		velY = 8;
		
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
		
		int diffX = x-player.getX() - 8;
		int diffY = y-player.getY() - 8;
		float distance = (float) Math.sqrt(((x-player.getX())*(x-player.getX())) + ((y-player.getY())*(y-player.getY())));
		
		if(velX < 0) direction = -1;
		else if(velX > 0) direction = 1;
		
		velX = (int) ((-2.5/distance) * (diffX+1));
		velY = (int) ((-2.5/distance) * (diffY+1));
		
		if(y <= 96 || y>= Game.ALTURA - 96) velY *= -1;
		if(x <= 96 || x>= Game.LARGURA - 96) velX *= -1;
		
		enemy2WalkLeft.runAnimation();
		enemy2WalkRight.runAnimation();
		
		collision();	
		//handler.addObject(new Trail(x,y,ID.Trail,16,16,0.08f,Color.PINK,handler));
	}
	
	
	public void render(Graphics g)
	{
		g.setColor(Color.GREEN);
		//g.fillRect(x, y, 32, 32);
		
		if(velX != 0 ||velY != 0)
		{
			if(direction == 1)
				enemy2WalkRight.drawAnimation(g, x, y-48, 64, 98);
			else if(direction == -1)
				enemy2WalkLeft.drawAnimation(g, x, y-48, 64, 98);
		}
		
		
		else if(velX == 0 && velY == 0)
		{
			if(direction == 1)
				g.drawImage(tex.enemy2[0], x, y-48,64,98, null);
			else
				g.drawImage(tex.enemy2[16], x, y-48,64,98, null);
		}
		
	}

}
