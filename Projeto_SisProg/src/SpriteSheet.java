import java.awt.image.BufferedImage;

/*A classe SpriteSheet é utilizada para pegar uma subimagem de outra.
 * Como são usados Sprites (coletâneas de imagens diferentes de um mesmo
 * personagem), é necessário clampar essa imagem original. Porém, o método
 * Game.clamp não seria válido, pois aquele tem como intuito estabelecer limites,
 * este, por sua vez, tem como intuito cortar a imagem e usar apenas um trecho dela.
 * 
 * o retorno img é justamente o tanto que se quer da imagem.
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
