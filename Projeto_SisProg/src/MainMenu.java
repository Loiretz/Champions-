import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class MainMenu extends MouseAdapter{
	
	private int ClosureOP;
	private Game game;
	private Handler handler;
	public static int[] Stage = new int[4];
	Texture tex = Game.getInstance();
	
	public MainMenu (Game game,Handler handler){
		this.game = game;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		//BOTÕES DA MAIN MENU
		if(game.gameState == Game.STATE.MainMenu)
		{
			//BOTÃO JOGAR
			if(mouseOver(mx,my,660, 350, 100, 64))
			{
				game.gameState = Game.STATE.SelectionMenu;
			}
			
			
			//BOTÃO FECHAR
			if(mouseOver(mx,my,660, 450, 100, 64))
			{
				ClosureOP = JOptionPane.showConfirmDialog(null, "Você deseja realmente fechar o jogo?\n"+
						"Todo progresso não salvo será perdido.");
							if(ClosureOP == JOptionPane.OK_OPTION)
							{
								System.exit(1);
							}
			}
		}
		
		//BOTÕES DA TELA DE SELEÇÃO
		else if(game.gameState == Game.STATE.SelectionMenu)
		{
			//INIMIGO 1
			if(mouseOver(mx,my,220, 100, 144, 168))
			{
				Stage[0] = 1;
				game.gameState = Game.STATE.Battle;
				handler.addObject(new Player(600,430,ID.Player,handler) );
				handler.addObject(new Enemy1(100,150,ID.Enemy1,handler) );
			}
			
			//INIMIGO 2
			if(mouseOver(mx,my,460, 100, 144, 168))
			{
				Stage[1] = 1;
				game.gameState = Game.STATE.Battle;
				handler.addObject(new Player(600,430,ID.Player,handler) );
				handler.addObject(new Enemy2(100,150,ID.Enemy2,handler) );
			}
			
			//INIMIGO 3
			if(mouseOver(mx,my,240, 300, 144, 168))
			{
				Stage[2] = 1;
				game.gameState = Game.STATE.Battle;
				handler.addObject(new Player(600,430,ID.Player,handler) );
				handler.addObject(new Enemy3(100,150,ID.Enemy3,handler) );
			}
			
			//INIMIGO 4
			if(mouseOver(mx,my,460, 300, 144, 168))
			{
				Stage[3] = 1;
				game.gameState = Game.STATE.Battle;
				handler.addObject(new Player(600,430,ID.Player,handler) );
				handler.addObject(new Enemy4(100,430,ID.Enemy4,handler) );
			}
			
			//BOTÃO VOLTAR
			if(mouseOver(mx,my,660, 450, 100, 640))
			{
				game.gameState = Game.STATE.MainMenu;
			}
		}
		
		//TELA END
			else if(game.gameState == Game.STATE.End)
			{
				//BOTÃO TENTAR DE NOVO
				if(mouseOver(mx,my,350, 450, 100, 640))
				{
					game.gameState = Game.STATE.MainMenu;
				}
			}
	}
	
	public void mouseReleased(MouseEvent e){}
	
	
	/*		Esse método serve para clampar a área clicável de cada retângulo (estes criados na render() desta mesma classe.
	 * 		Caso seja clicado na área, retorna TRUE, caso contrário, retorna FALSE. Esses retornos serão usados para definir
	 *  o que acontece quando se clica na box separada.
	 *  	Esta é uma alternativa válida.
	 *
	 * */
	private boolean mouseOver(int mx, int my, int x, int y, int largura, int altura)
	{
		if(mx > x && mx < x + largura)
		{
			if(my > y && my < y + altura)
			{
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void render(Graphics g)
	{
		//MAIN MENU
		if(game.gameState == Game.STATE.MainMenu)
		{
			Font fnt = new Font("Courier",0,20);
		
			g.setColor(Color.WHITE);
			g.setFont(fnt);
			g.drawString("Jogar", 680, 380);
			g.drawRect(660, 350, 100, 64);
			g.drawString("Fechar",675, 480);
			g.drawRect(660, 450, 100, 64);
		}
		
		//TELA DE SELEÇÃO
		else if(game.gameState == Game.STATE.SelectionMenu)
		{
			Font fnt = new Font("Courier",0,12);
			
			g.setColor(Color.WHITE);
			g.setFont(fnt);
			//PRIMEIRA LINHA
			g.drawRect(220, 100, 144, 168);
			g.drawRect(460, 100, 144, 168);
			
			//SEGUNDA LINHA
			g.drawRect(220, 300, 144, 168);
			g.drawRect(460, 300, 144, 168);
			
			//VOLTAR
			Font fnt2 = new Font("Courier",0,20);
			g.setFont(fnt2);
			g.drawString("Voltar",675, 480);
			g.drawRect(660, 450, 100, 64);
		}
		
		//TELA DE GAME OVER
		else if(game.gameState == Game.STATE.End)
		{
			g.setColor(Color.WHITE);
			Font fnt2 = new Font("Courier",0,15);
			Font fnt3 = new Font("Elephant",2,40);
			
			g.setFont(fnt3);
			g.drawString("Game Over", 300, 150);
			
			g.setFont(fnt2);
			g.drawString("Reiniciar",370, 480);
			g.drawRect(360, 450, 100, 64);
			
			//g.drawImage(tex.player[10], 250, 250,64,98, null);
		}
	}
	public void tick(){}
}
