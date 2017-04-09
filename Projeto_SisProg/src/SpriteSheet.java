import java.awt.image.BufferedImage;

/*A classe SpriteSheet � utilizada para pegar uma subimagem de outra.
 * Como s�o usados Sprites (colet�neas de imagens diferentes de um mesmo
 * personagem), � necess�rio clampar essa imagem original. Por�m, o m�todo
 * Game.clamp n�o seria v�lido, pois aquele tem como intuito estabelecer limites,
 * este, por sua vez, tem como intuito cortar a imagem e usar apenas um trecho dela.
 * 
 * o retorno img � justamente o tanto que se quer da imagem.
 * 
 * */

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet (BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int largura, int altura)
	{
		BufferedImage img = image.getSubimage((col*largura) - largura, (row*altura) - altura, largura, altura);
		return img;
	}
}
