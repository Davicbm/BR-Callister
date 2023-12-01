package Jogo.Componentes.Jogadores;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Jogo.Componentes.Sons.EfeitosSonoros;

public class TiroNave {
	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;

	private static int velocidade = 8;

	public TiroNave(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;

		somTiroSimples();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//novo_tiro.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void update() {
		this.x += velocidade;

		if (this.x > 1500) {
			isVisivel = false;
		}
	}
	
	public void somTiroSimples() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarTiro();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
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

	public static void setVelocidade(int vel) {
		velocidade = vel;
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