import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * Essa classe definirá tudo que o jogo possuirá. Desde
 * inimigos, jogadores, tela, efeitos. TUDO do jogo
 * estará nesta classe.
 */

public abstract class GameObject {
	
	protected int x,y;
	protected ID id;
	protected int velX, velY;
	protected int direction;
	protected boolean punch;
	
	public GameObject(int x, int y, ID id)
	{
		//Construtor do GameObject
		this.x = x;
		this.y = y;
		this.id = id;
		this.velX = velX;
		this.velY = velY;
		this.punch = punch;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	//GETTERS E SETTERS
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setID(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	public void setVelX(int velX){
		this.velX = velX;
	}
	public void setVelY(int velY){
		this.velY = velY;
	}
	public int getVelX(){
		return velX;
	}
	public int getVelY(){
		return velY;
	}
	public int getDirection(){
		return direction;
	}
	public boolean getPunch(){
		return punch;
	}
	public void setPunch(boolean punch){
		this.punch = punch;
	}

	/*Utilizada para retornar um 'true' quando dois retangulos
	colidirem.*/
	public abstract Rectangle getBounds();
}
