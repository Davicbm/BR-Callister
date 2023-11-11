package Jogo.Componentes.Inimigos;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Robo {
	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;
	
	private static int velocidade = 5;
	
	public Robo(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	public void load() {
		ImageIcon referencia = new ImageIcon("assets//robo.gif");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	public void update() {
		this.x -= velocidade;
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,largura,altura);
	}
	public boolean isVisivel() {
		return isVisivel;
	}
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	public static int getVelocidade() {
		return velocidade;
	}
	public static void setVelocidade(int vELOCIDADE) {
		velocidade = vELOCIDADE;
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