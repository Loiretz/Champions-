import java.util.Random;


public class Spawn {

	private Handler handler;
	private HUD hud;
	public int scoreKeep = 0;
	public int totalScore = 0;
	
	private Random r = new Random();
	
	
	public Spawn(Handler handler,HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick()
	{
		scoreKeep++;
		//System.out.println("Score:"+scoreKeep);
		//System.out.println("TotalScore:"+totalScore);
		//totalScore = totalScore + scoreKeep;
		
		if(scoreKeep >= 100)
		{
			scoreKeep = 0;
			//handler.addObject(new Enemy1(r.nextInt(Game.LARGURA-32),r.nextInt(Game.ALTURA-32),ID.Enemy1,handler));
			//handler.addObject(new Enemy2(r.nextInt(Game.LARGURA-32),r.nextInt(Game.ALTURA-32),ID.Enemy2,handler));
			//handler.addObject(new Enemy3(64,64,ID.Enemy3,handler));
		}
	}
}
