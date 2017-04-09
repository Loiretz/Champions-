import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private Player player;
	private int ClosureOP;
	private Game game;
	private Cursor cursor;
	
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Game game,Handler handler){
		this.handler = handler;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player)
			{
				//Coisas que irão acontecer para o Player
				/*Para parecer que a mudança de posição é meio glitchy:
				* 	usar tempObject.set_(tempObject.get_() - _);
				*/
				if(key == KeyEvent.VK_UP) {	tempObject.setVelY(-5); keyDown[0] = true;}
				if(key == KeyEvent.VK_DOWN){ tempObject.setVelY(5);	keyDown[1] = true;}	
				if(key == KeyEvent.VK_LEFT){ tempObject.setVelX(-5); keyDown[2] = true;}
				if(key == KeyEvent.VK_RIGHT){ tempObject.setVelX(5);	keyDown[3] = true;}
				if(key == KeyEvent.VK_ESCAPE)
				{
					 ClosureOP = JOptionPane.showConfirmDialog(null, "Você deseja realmente sair o jogo?\n");
					if(ClosureOP == JOptionPane.OK_OPTION)
					{
						handler.clearAll();						
						game.gameState = Game.STATE.MainMenu;
					}	
				}
				if(key == KeyEvent.VK_Z){
					if(tempObject.getDirection() == 1) handler.addObject(cursor = new Cursor(tempObject.getX() + 40,tempObject.getY(),ID.Cursor));
					if(tempObject.getDirection() == -1) handler.addObject(cursor = new Cursor(tempObject.getX() - 24,tempObject.getY(),ID.Cursor));
					tempObject.setPunch(true);
				}
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player)
			{
				//Coisas que irão acontecer para o Player
				if(key == KeyEvent.VK_UP){ tempObject.setVelY(0);	keyDown[0] = false;}
				if(key == KeyEvent.VK_DOWN){ tempObject.setVelY(0);	keyDown[1] = false;}
				if(key == KeyEvent.VK_LEFT){ tempObject.setVelX(0);	keyDown[2] = false;}
				if(key == KeyEvent.VK_RIGHT){ tempObject.setVelX(0);keyDown[3] = false;}
				if(key == KeyEvent.VK_Z){
					handler.clearCursor();
					tempObject.setPunch(false);
				}
			}
		}
		
	}

}
