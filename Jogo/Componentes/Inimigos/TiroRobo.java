package Jogo.Componentes.Inimigos;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class TiroRobo {

	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;

	private static int VELOCIDADE = 6;

	public TiroRobo(int x, int y) {
		this.x = x;
		this.y = y;
		
		isVisivel = true;
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//robo_tiro.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void update() {
		this.x -= VELOCIDADE;
		
		if (this.x < 0) {
			isVisivel = false;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x - 130, y - 38, largura, altura);
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
}