import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Trail extends GameObject{

	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int largura, altura;
	private float life;
	
	
	
	public Trail(int x, int y,ID id,int largura, int altura,float life,Color color, Handler handler)
	{
		super(x,y,id);
		this.handler = handler;
		this.color = color;
		this.altura = altura;
		this.largura = largura;
		this.life = life;
	}
	
	public void tick() {
		if(alpha > life)
		{
			
		alpha -= life - 0.000001f;
		}
		else handler.removeObject(this);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect(x, y, largura, altura);
		
		g2d.setComposite(makeTransparent(1));
		
	}
	
	private AlphaComposite makeTransparent(float alpha)
	{
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		//Pode retornar null porquê não há necessidade de trabalhar com as colisões das trails.
		return null;
	}
	

}
