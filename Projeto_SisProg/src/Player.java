import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	Handler handler;
	Texture tex = Game.getInstance();
	
	private Animation playerWalkRight;
	private Animation playerWalkLeft;
	private Animation playerPunchRight;
	private Animation playerPunchLeft;

	public Player(int x, int y, ID id,Handler handler)
	{
		super(x,y,id);
		this.handler = handler;
		
		playerWalkRight = new Animation(3, tex.player[1],tex.player[0]);
		playerWalkLeft = new Animation(3, tex.player[17],tex.player[16]);
		playerPunchRight = new Animation(1,tex.player[4]);
		playerPunchLeft = new Animation(1,tex.player[20]);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,32,48);
	}
	
	
	public void tick() {
		x +=velX;
		y +=velY;
		
		// -1 LEFT
		// 1 RIGHT
		
		if(velX < 0) direction = -1;
		else if(velX > 0) direction = 1;
		
		x = Game.clamp(x, 0, Game.LARGURA - 60);
		y = Game.clamp(y, 50, Game.ALTURA - 100);
		
		if(MainMenu.Stage[3] == 1)
		{
			x = Game.clamp(x, 20, Game.LARGURA-100);
			y = Game.clamp(y, 280, Game.ALTURA-50);
		}		
		
		playerWalkRight.runAnimation();
		playerWalkLeft.runAnimation();
		playerPunchRight.runAnimation();
		playerPunchLeft.runAnimation();
		

		//No tick, sempre que colidir, executará o código abaixo.
		collision();		
		
	}
	
	/*
	 * O método de colisão para diminuir a vida está inserido no Player pois,
	 * a HUD só modificará quando algum objeto colidir com o objeto Player.
	 * */
	public void collision()
	{
		for(int i=0;i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			//O objeto temporário aqui será os inimigos.
			if (tempObject.getID() == ID.Enemy1|| tempObject.getID() == ID.Enemy2 || tempObject.getID() == ID.Enemy3 || tempObject.getID() == ID.Enemy4)
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH -= 2;
				}
			}
			
		}
	}

	public void render(Graphics g) {
		
		/*Quadrado branco preenchido.
		 * 
		 * */
		//g.setColor(Color.WHITE);
		//g.fillRect(x, y, 32, 48);
		if(velX != 0 ||velY != 0)
		{
			if(direction == 1)
				playerWalkRight.drawAnimation(g, x, y-48, 64, 98);
			else if(direction == -1)
				playerWalkLeft.drawAnimation(g, x, y-48, 64, 98);
		}
		if(getPunch() == true ){
			if(direction == -1) playerPunchLeft.drawAnimation(g, x, y-48, 64, 98);
			if(direction == 1) playerPunchRight.drawAnimation(g, x, y-48, 64, 98);
		}
	
		
		else if(velX == 0 && velY == 0)
		{
			if(direction == 1)
				g.drawImage(tex.player[0], x, y-48,64,98, null);
			else
				g.drawImage(tex.player[16], x, y-48,64,98, null);
		}
		
		
		
		/*Quadrado verde preenchido.
		 * 
		 * */
		//Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.GREEN);
		//g2d.draw(getBounds());
			
			
	}
}
