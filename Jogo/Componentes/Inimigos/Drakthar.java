package Jogo.Componentes.Inimigos;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Jogadores.TiroNave;

public class Drakthar {
	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;
	private int vida;

	private List<TiroDrakthar> tiros1;
	private long tempoUltimoTiro = System.currentTimeMillis();
	private long intervaloTiros = 2800;

	private int velocidade;

	public Drakthar(int x, int y, int velocidade) {
		this.x = x;
		this.y = y;
		this.isVisivel = true;
		this.vida = 26;
		this.velocidade = velocidade;

		tiros1 = new ArrayList<TiroDrakthar>();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//boss256px.gif");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void updateDrakthar(int localizacaoX) {
		this.x -= velocidade;

		if (this.x < localizacaoX) {
			this.x = localizacaoX;
		}
	}

	public void updateDrakthar2(int localizacaoX) {
		this.x += velocidade;

		if (this.x > localizacaoX) {
			this.x = localizacaoX;
		}
	}

	public void tiroSimples() {
		long tempoAtual = System.currentTimeMillis();
		if (tempoAtual - tempoUltimoTiro >= intervaloTiros) {
			this.tiros1.add(new TiroDrakthar(x + largura, y + (altura / 2)));
			tempoUltimoTiro = tempoAtual;
		}
	}

	public void atirar() {
		tiroSimples();
		tiros1 = getTiros();
		for (int i = 0; i < tiros1.size(); i++) {
			TiroDrakthar m = tiros1.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros1.remove(i);
			}
		}
	}

	public void startEstagio2(int y, int destinoY) {
		this.y = y;

		updateInvestidas(destinoY);
	}

	public void updateInvestidas(int destinoY) {
		this.y += velocidade;

		if (this.y > destinoY) {
			this.y = destinoY;
		}
	}

	public void drawTiroDrakthar(Graphics2D graficos) {
		List<TiroDrakthar> tiros2 = getTiros();
		for (int j = 0; j < tiros2.size(); j++) {
			TiroDrakthar m = tiros2.get(j);
			m.load();
			graficos.drawImage(m.getImagem(), m.getX() - 64, m.getY() - 15, null);
		}
	}

	public void colisaoDraktharTiro(Jogador1 jogador, int j) {
		List<TiroNave> tiros = jogador.getTiros();
		TiroNave tempTiro = tiros.get(j);
		Rectangle formaTiro = tempTiro.getBounds();
		Rectangle formaDrakthar = getBounds();
		if (formaTiro.intersects(formaDrakthar) && isVisivel() && tempTiro.isVisivel()) {
			perdeVida(1);
			tempTiro.setVisivel(false);
			if (vida == 0) {
				Jogador1.pontuacaoJogador1 += 500;
			}
		}
	}

	public void colisaoDraktharTiro(Jogador2 jogador, int j) {
		List<TiroNave> tiros = jogador.getTiros();
		TiroNave tempTiro = tiros.get(j);
		Rectangle formaTiro = tempTiro.getBounds();
		Rectangle formaDrakthar = getBounds();
		if (formaTiro.intersects(formaDrakthar) && isVisivel() && tempTiro.isVisivel()) {
			perdeVida(1);
			tempTiro.setVisivel(false);
			if (vida == 0) {
				Jogador2.pontuacaoJogador2 += 500;
			}
		}
	}

	public void colisaoNaveTiro(Jogador1 jogador1, Jogador2 jogador2) {
		List<TiroDrakthar> tiros1 = getTiros();
		for (int j = 0; j < tiros1.size(); j++) {
			TiroDrakthar tempTiroDrakthar = tiros1.get(j);
			Rectangle formaTiroDrakthar = tempTiroDrakthar.getBounds();
			Rectangle formaNave = jogador1.getBounds();
			if (formaTiroDrakthar.intersects(formaNave) && jogador1.isVisivel()) {
				jogador1.perdeVida(3);
				tempTiroDrakthar.setVisivel(false);
			}
		}

		List<TiroDrakthar> tiros2 = getTiros();
		for (int j = 0; j < tiros2.size(); j++) {
			TiroDrakthar tempTiroDrakthar = tiros2.get(j);
			Rectangle formaTiroDrakthar = tempTiroDrakthar.getBounds();
			Rectangle formaNave = jogador2.getBounds();
			if (formaTiroDrakthar.intersects(formaNave) && jogador2.isVisivel()) {
				jogador2.perdeVida(4);
				tempTiroDrakthar.setVisivel(false);
			}
		}
	}

	public void perdeVida(int dano) {
		this.vida = vida - dano;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<TiroDrakthar> getTiros() {
		return tiros1;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getVida() {
		return this.vida;
	}
}
