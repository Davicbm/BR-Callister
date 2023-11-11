package Jogo.Componentes.Inimigos;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Jogo.Componentes.Jogadores.TiroNave;

public class Robo {
	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;
	
	private List<TiroRobo> tiros;
    private long tempoUltimoTiro = System.currentTimeMillis();
    private long intervaloTiros = 1000;

	private static int velocidade = 5;
	
	public Robo(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;

		tiros = new ArrayList<TiroRobo>();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//robo1.gif");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	public void tiroSimples() {
		long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiro >= intervaloTiros) {
            this.tiros.add(new TiroRobo(x + largura, y + (altura / 2)));
            tempoUltimoTiro = tempoAtual;
        }
	}
	public void atirar() {
		tiroSimples();
		List<TiroRobo> tiros = getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				TiroRobo m = tiros.get(i);
				if (m.isVisivel()) {
					m.update();
				} else {
					tiros.remove(i);
				}
			}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura,altura);
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
	public List<TiroRobo> getTiros() {
		return tiros;
	} 
}
