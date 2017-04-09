import java.awt.image.BufferedImage;

public class Texture {

	
	
	SpriteSheet ss;
	SpriteSheet ps;
	SpriteSheet e1s,e2s,e3s,e4s;
	SpriteSheet bg;
	SpriteSheet punchs;
	
	private BufferedImage enemy1_sheet = null;
	private BufferedImage enemy2_sheet = null;
	private BufferedImage enemy3_sheet = null;
	private BufferedImage enemy4_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] player = new BufferedImage[32];
	public BufferedImage[] enemy1 = new BufferedImage[32];
	public BufferedImage[] enemy2 = new BufferedImage[32];
	public BufferedImage[] enemy3 = new BufferedImage[32];
	public BufferedImage[] enemy4 = new BufferedImage[32];
	public BufferedImage[] punch = new BufferedImage[3];
	public BufferedImage[] punche1 = new BufferedImage[3];
	public BufferedImage[] punche2 = new BufferedImage[3];
	public BufferedImage[] punche3 = new BufferedImage[3];
	public BufferedImage[] punche4 = new BufferedImage[3];
	
	public Texture()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
			player_sheet = loader.loadImage("/Masaru.png"); // /imagem.png
			enemy1_sheet = loader.loadImage("/Yun.png");
			enemy2_sheet = loader.loadImage("/Lei.png");
			enemy3_sheet = loader.loadImage("/Samo.png");
			enemy4_sheet = loader.loadImage("/Evil.png");
			//enemy_sheet = loader.loadImage(""); //imagem.png
			System.out.println("A imagem foi lida");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		e1s = new SpriteSheet(enemy1_sheet);
		e2s = new SpriteSheet(enemy2_sheet);
		e3s = new SpriteSheet(enemy3_sheet);
		e4s = new SpriteSheet(enemy4_sheet);
		ps = new SpriteSheet(player_sheet);
		punchs = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	
	private void getTextures()
	{
		/*	PLAYER SPRITE SHEET
		 * */
		//BAIXO DIREITA
		player[0] = ps.grabImage(1, 1, 32, 48); //Idle
		player[1] = ps.grabImage(2, 1, 32, 48); //Movement
		player[2] = ps.grabImage(3, 1, 32, 48); //Defense
		player[4] = ps.grabImage(1, 2, 32, 48);//Punch
		
		player[10] = ps.grabImage(4,4,32,48);
		player[11] = ps.grabImage(7, 4, 32, 48);
		player[12] = ps.grabImage(6, 4, 32, 48);
		player[13] = ps.grabImage(8, 2, 32, 48);
		player[14] = ps.grabImage(4, 2, 32, 48);
		player[15] = ps.grabImage(4, 2, 32, 48);
		
		//BAIXO ESQUERDA
		player[16] = ps.grabImage(5, 1, 32, 48); //Idle
		player[17] = ps.grabImage(6, 1, 32, 48); //Movement
		player[18] = ps.grabImage(7, 1, 32, 48);//Defense
		//Cast
		player[20] = ps.grabImage(5, 2, 32, 48);//Punch
		
		/*	PUNCH SPRITE SHEET;
		 * */
		punch[0] = punchs.grabImage(1, 5, 32, 48);//Punch Animation 1
		punch[1] = punchs.grabImage(2, 5, 32, 48);//Punch Animation 2
		punch[2] = punchs.grabImage(3, 5, 32, 48);//Punch Animation 3
		
		/*	ENEMY1 SPRITE SHEET
		 * */
		//BAIXO DIREITA
		enemy1[0] = e1s.grabImage(1, 1, 32, 48); //Idle
		enemy1[1] = e1s.grabImage(2, 1, 32, 48); //Movement
		enemy1[2] = e1s.grabImage(3, 1, 32, 48); //Defense
		enemy1[4] = e1s.grabImage(1, 2, 32, 48);//Punch
		
		//BAIXO ESQUERDA
		enemy1[16] = e1s.grabImage(5, 1, 32, 48); //Idle
		enemy1[17] = e1s.grabImage(6, 1, 32, 48); //Movement
		enemy1[18] = e1s.grabImage(7, 1, 32, 48);//Defense
		//Cast
		enemy1[20] = e1s.grabImage(5, 2, 32, 48);//Punch
		
		/*	ENEMY2 SPRITE SHEET
		 */
		//BAIXO DIREITA
		enemy2[0] = e2s.grabImage(1, 1, 32, 48); //Idle
		enemy2[1] = e2s.grabImage(2, 1, 32, 48); //Movement
		enemy2[2] = e2s.grabImage(3, 1, 32, 48); //Defense
		enemy2[4] = e2s.grabImage(1, 2, 32, 48);//Punch
		
		//BAIXO ESQUERDA
		enemy2[16] = e2s.grabImage(5, 1, 32, 48); //Idle
		enemy2[17] = e2s.grabImage(6, 1, 32, 48); //Movement
		enemy2[18] = e2s.grabImage(7, 1, 32, 48);//Defense
		//Cast
		enemy2[20] = e2s.grabImage(5, 2, 32, 48);//Punch
		
		/*	ENEMY3 SPRITE SHEET
		 */
		//BAIXO DIREITA
		enemy3[0] = e3s.grabImage(1, 1, 32, 48); //Idle
		enemy3[1] = e3s.grabImage(2, 1, 32, 48); //Movement
		enemy3[2] = e3s.grabImage(3, 1, 32, 48); //Defense
		enemy3[4] = e3s.grabImage(1, 2, 32, 48);//Punch
		
		//BAIXO ESQUERDA
		enemy3[16] = e3s.grabImage(5, 1, 32, 48); //Idle
		enemy3[17] = e3s.grabImage(6, 1, 32, 48); //Movement
		enemy3[18] = e3s.grabImage(7, 1, 32, 48);//Defense
		//Cast
		enemy3[20] = e3s.grabImage(5, 2, 32, 48);//Punch
		
		/*	ENEMY4 SPRITE SHEET
		 */
		//BAIXO DIREITA
		enemy4[0] = e4s.grabImage(1, 1, 32, 48); //Idle
		enemy4[1] = e4s.grabImage(2, 1, 32, 48); //Movement
		enemy4[2] = e4s.grabImage(3, 1, 32, 48); //Defense
		enemy4[4] = e4s.grabImage(1, 2, 32, 48);//Punch
		
		//BAIXO ESQUERDA
		enemy4[16] = e4s.grabImage(5, 1, 32, 48); //Idle
		enemy4[17] = e4s.grabImage(6, 1, 32, 48); //Movement
		enemy4[18] = e4s.grabImage(7, 1, 32, 48);//Defense
		//Cast
		enemy4[20] = e4s.grabImage(5, 2, 32, 48);//Punch
	
		
		/*	PUNCH SPRITE SHEET;
		 * */
		punche1[0] = punchs.grabImage(1, 5, 32, 48);//Punch Animation 1
		punche1[1] = punchs.grabImage(2, 5, 32, 48);//Punch Animation 2
		punche1[2] = punchs.grabImage(3, 5, 32, 48);//Punch Animation 3
		
		punche4[0] = e4s.grabImage(1, 5, 32, 48);
		punche4[1] = e4s.grabImage(1, 5, 32, 48);
		punche4[2] = e4s.grabImage(1, 5, 32, 48);
		
	}
}
