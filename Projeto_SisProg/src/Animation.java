import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Animation {
	
	private int speed;
	private int frames;
	
	private int index = 0;
	//Count é o frame em que se está.
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	/*Essa declaração (BufferedImage... args) diz que
	 * toda e qualquer argumento do tipo BufferedImage pode
	 * ser inserido aqui.
	 */
	public Animation(int speed, BufferedImage... args)
	{
		this.speed = speed;
		images = new BufferedImage[args.length];
		for(int i = 0; i < args.length; i++)
		{
			//OU SEJA, está sendo varrido aqui que todos os
			//args[i] correspondem a images[i].
			images[i] = args[i];
		}
		//	A quantidade frames é a mesma que a quantidade de
		//images varridos.
		frames = args.length;
	}
	
	public void runAnimation()
	{
		index++;
		if(index > speed)
		{
			index = 0;
			nextFrame();
		}
	}
	
	public void nextFrame()
	{
		for(int i = 0; i < frames; i++)
		{
			if(count == i) 
				currentImg = images[i];
		}
		
		count++;
		
		if(count > frames) 
			count = 0;
	}
	
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY)
	{
		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
	}

}

