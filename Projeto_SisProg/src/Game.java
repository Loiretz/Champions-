import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int LARGURA = 800, ALTURA = 600;
	private Thread thread;
	
	private boolean rodando = false;
	private Handler handler;
	private HUD hud;
	private HUD_Enemy hud_enemy;
	private Spawn spawn;
	private MainMenu mainMenu;
	private BufferedImage selectionbg = null, background1 = null, background2 = null, background3 = null, background4 = null, mainbg = null;
	private BufferedImage frame1 = null, frame2 = null, frame3 = null, frame4 = null;
	private static Texture tex;
	private Animation main;
	
	/*		A enumeração STATE define o estado em que o jogo está, inicialmente no MainMeu, depois trasgredindo para
	 * 	SelectionMenu e então para Battle.
	 * 		O conceito de FSM (Finite State Machine) rege sobre esta definição e é usado com frequência em JRPGs (Japanese
	 * 	Role Playing Game), pois assim, é possível definir bem cada estágio do jogo.
	 * 
	 * */
	public enum STATE 
	{
		MainMenu,
		SelectionMenu,
		End,
		Battle;
	}
	
	public STATE gameState = STATE.MainMenu;
	

	public synchronized void start()
	{
		//thread única rodando :D
		thread = new Thread(this);
		thread.start();
		rodando = true;
	}
	
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			rodando = false;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		//LÓGICA PARA CALCULAR FRAMES POR SEGUNDO(FPS)
				//this.requestFocus();
				long lastTime = System.nanoTime();
				double amountOfTicks = 60.0;
				double ns = 1000000000 / amountOfTicks;
				double delta = 0;
				long timer = System.currentTimeMillis();
				int frames = 0;
				while(rodando)
				{
					long now = System.nanoTime();
					delta += (now - lastTime) / ns;
					lastTime = now;
					while (delta >= 1){
						tick();
						delta--;
					}
					if(rodando)
						render();
					frames++;
					
					if(System.currentTimeMillis() - timer > 1000){
						timer += 1000;
						//DESCOMENTE PARA EXIBIR O FPS
						System.out.println("FPS: "+ frames);
						frames = 0;
					}
				}
				stop();
	}

	private void tick()
	{
		handler.tick();
		
		/*Aqui define o que vai acontecer na tick() em cada estado da FSM.*/
		if (gameState == STATE.Battle){
			hud.tick();
			hud_enemy.tick();
			spawn.tick();
			
			if (HUD.HEALTH <= 0)
			{
				HUD.HEALTH = 100;
				HUD_Enemy.HEALTH = 100;
				MainMenu.Stage[0] = 0;
				MainMenu.Stage[1] = 0;
				MainMenu.Stage[2] = 0;
				MainMenu.Stage[3] = 0;
				spawn.scoreKeep = 0;
				handler.clearAll();
				gameState = STATE.End;
			}
			if(HUD_Enemy.HEALTH <= 0)
			{
				HUD.HEALTH = 100;
				HUD_Enemy.HEALTH = 100;
				MainMenu.Stage[0] = 0;
				MainMenu.Stage[1] = 0;
				MainMenu.Stage[2] = 0;
				MainMenu.Stage[3] = 0;
				spawn.scoreKeep = 0;
				handler.clearAll();
				gameState = STATE.SelectionMenu;
			}
		} 
		else if(gameState 
				== STATE.MainMenu)
		{
			mainMenu.tick();
			main.runAnimation();
		}
	}
	

	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			//
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LARGURA, ALTURA);
		
		if(gameState == STATE.Battle)
		{
			if(MainMenu.Stage[0] == 1)
			{
				g.drawImage(background3, 0, 0, 800, 600, null);
			}
			if(MainMenu.Stage[1] == 1)
			{
				g.drawImage(background2, 0, 0, 800, 600, null);
			}
			if(MainMenu.Stage[2] == 1)
			{
				g.drawImage(background4, 0, 0, 800, 600, null);
			}
			if(MainMenu.Stage[3] == 1)
			{
				g.drawImage(background1, 0, 0, 800, 600, null);
			}
		}
		
		
		handler.render(g);
		/*
		 * 	Aqui define o que vai acontecer na render() em cada estado da FSM.
		 * */
		if (gameState == STATE.Battle)
		{
			hud.render(g);
			hud_enemy.render(g);
		}
		else if(gameState == STATE.MainMenu || gameState == STATE.SelectionMenu || gameState == STATE.End)
		{
			if(gameState == STATE.SelectionMenu) 
			{
				g.drawImage(selectionbg, 0, 0, 800,580, null);
				g.drawImage(frame1,220, 100, 144, 168,null);
				g.drawImage(frame2,460, 100, 144, 168,null);
				g.drawImage(frame3,220, 300, 144, 168,null);
				g.drawImage(frame4,460, 300, 144, 168,null);
			}
			if(gameState == STATE.MainMenu)
			{
				g.drawImage(mainbg,0,0,800,580,null);
				main.drawAnimation(g, 650, 200, 64, 98);
			}
			if(gameState == STATE.End)
				g.drawImage(tex.player[10], 380, 250, 64, 96, null);
			mainMenu.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	
	
	//Essa classe define os limites da tela. Esta classe poderia muito
	//bem ser definida dentro da classe Window, porém não seria possível
	//utilizá-la em outras classses(i.e Player e Enemy).
	public static int clamp(int var, int min, int max)
	{
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	//Classe que é usada nas classes Player e Enemies para devolver a imagem.
	public static Texture getInstance()
	{
		return tex;
	}
	
	public Game()
	{
		handler = new Handler();
		this.addKeyListener(new KeyInput(this,handler));
		mainMenu = new MainMenu(this,handler);
		this.addMouseListener(mainMenu);
		
	
		tex = new Texture();
		BufferedImageLoader loader = new BufferedImageLoader();
		
		background1 = loader.loadImage("/BackGround1.png");
		background2 = loader.loadImage("/BackGround2.png");
		background3 = loader.loadImage("/BackGround3.png");
		background4 = loader.loadImage("/BackGround4.png");
		selectionbg = loader.loadImage("/SelectionBG.png");
		frame1 = loader.loadImage("/Yun_Face.png");
		frame2 = loader.loadImage("/Lei_Face.png");
		frame3 = loader.loadImage("/Samo_Face.png");
		frame4 = loader.loadImage("/Evil_Face.png");
		mainbg = loader.loadImage("/MainBG.png");
		
		main = new Animation(5,tex.player[11],tex.player[12],tex.player[13]);
		
		new Window(LARGURA, ALTURA, "Champions_TRIAL", this);
		hud = new HUD();
		hud_enemy = new HUD_Enemy();
		spawn = new Spawn(handler,hud);
		
		
	}
	

	public static void main(String args[])
	{
		new Game();
	}
}
