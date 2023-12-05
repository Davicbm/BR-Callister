package Jogo.Componentes.Inimigos;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class TiroDrakthar {

	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;

	private static int velocidade = 6;

	public TiroDrakthar(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//drakthar_tiro.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void loadCima() {
		ImageIcon referencia = new ImageIcon("assets//drakthar-tiro-cima.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void loadBaixo() {
		ImageIcon referencia = new ImageIcon("assets//drakthar-tiro-baixo.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void loadLaser() {
		ImageIcon referencia = new ImageIcon("assets//raioLaser.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void update() {
		this.x -= velocidade;

		if (this.x < 0) {
			isVisivel = false;
		}
	}

	public void updateCima() {
		this.y -= velocidade;

		if (this.y < 100) {
			isVisivel = false;
		}
	}

	public void updateBaixo() {
		this.y += velocidade;

		if (this.y > 900) {
			isVisivel = false;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x - 80, y - 20, largura, altura);
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