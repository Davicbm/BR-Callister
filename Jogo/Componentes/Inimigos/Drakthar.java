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
import Jogo.Componentes.Sons.EfeitosSonoros;

public class Drakthar {
	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;
	private int vida;

	private int contador1 = 0;
	private int contador2 = 0;
	private int contador3 = 0;

	private List<TiroDrakthar> tiros1;
	private List<TiroDrakthar> tiros2;
	private List<TiroDrakthar> tiros3;
	private List<TiroDrakthar> tiros4;

	private long tempoUltimoTiro = System.currentTimeMillis();
	private long intervaloTiros = 1800;

	private int velocidade;

	public Drakthar(int x, int y, int velocidade) {
		this.x = x;
		this.y = y;
		this.isVisivel = true;
		this.vida = 36;
		this.velocidade = velocidade;

		tiros1 = new ArrayList<TiroDrakthar>();
		tiros2 = new ArrayList<TiroDrakthar>();
		tiros3 = new ArrayList<TiroDrakthar>();
		tiros4 = new ArrayList<TiroDrakthar>();
	}

	public void somRugido() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarSomRugido();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//boss256px.gif");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void load2() {
		ImageIcon referencia = new ImageIcon("assets//boss256pxFlip.png");
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

	public void tiroTriplo() {
		long tempoAtual = System.currentTimeMillis();
		if (tempoAtual - tempoUltimoTiro >= intervaloTiros) {
			this.tiros4.add(new TiroDrakthar(x + largura, y - altura));
			this.tiros2.add(new TiroDrakthar(x + largura, y));
			this.tiros3.add(new TiroDrakthar(x + largura, y + altura));
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

	public void atirarTriplo() {
		tiroTriplo();

		tiros1 = getTiros();
		for (int i = 0; i < tiros1.size(); i++) {
			tiros1.remove(i);
		}

		for (int i = 0; i < tiros4.size(); i++) {
			TiroDrakthar tiro = tiros4.get(i);
			if (tiro.isVisivel()) {
				tiro.update();
			} else {
				tiros4.remove(i);
			}
		}
		for (int i = 0; i < tiros2.size(); i++) {
			TiroDrakthar tiro = tiros2.get(i);
			if (tiro.isVisivel()) {
				tiro.update();
			} else {
				tiros2.remove(i);
			}
		}
		for (int i = 0; i < tiros3.size(); i++) {
			TiroDrakthar tiro = tiros3.get(i);
			if (tiro.isVisivel()) {
				tiro.update();
			} else {
				tiros3.remove(i);
			}
		}
	}

	public void removerTiros() {
		tiros1 = getTiros();
		for (int i = 0; i < tiros1.size(); i++) {
			tiros1.remove(i);
		}
	}

	public void drawTiroDraktharTriplo(Graphics2D graficos) {
		desenharTiros(tiros4, graficos);
		desenharTiros(tiros2, graficos);
		desenharTiros(tiros3, graficos);
	}

	private void desenharTiros(List<TiroDrakthar> tiros, Graphics2D graficos) {
		for (int j = 0; j < tiros.size(); j++) {
			TiroDrakthar m = tiros.get(j);
			m.load();

			graficos.drawImage(m.getImagem(), m.getX() - 64, m.getY(), null);
		}
	}

	public void atirarFlip() {
		tiroSimples();
		tiros1 = getTiros();
		for (int i = 0; i < tiros1.size(); i++) {
			TiroDrakthar m = tiros1.get(i);
			if (m.isVisivel()) {
				m.updateFlip();
			} else {
				tiros1.remove(i);
			}
		}
	}

	public void updateInvestidasBaixo(int destinoY) {
		this.y += velocidade;
		if (contador3 == 0) {
			somRugido();
			contador3++;
		}
		if (this.y > destinoY) {
			this.y = destinoY;
		}
	}

	public void updateInvestidasCima(int destinoY) {
		this.y -= velocidade;

		if (contador3 == 0) {
			somRugido();
			contador3++;
		}
		if (this.y < destinoY) {
			this.y = destinoY;
		}
	}

	public void drawTiroDrakthar(Graphics2D graficos) {
		List<TiroDrakthar> tiros = getTiros();

		for (int j = 0; j < tiros.size(); j++) {
			TiroDrakthar m = tiros.get(j);
			m.load();
			graficos.drawImage(m.getImagem(), m.getX() - 64, m.getY() - 15, null);
		}
	}

	public void drawTiroDraktharFlip(Graphics2D graficos) {
		List<TiroDrakthar> tiros = getTiros();

		for (int j = 0; j < tiros.size(); j++) {
			TiroDrakthar m = tiros.get(j);
			m.loadFlip();
			graficos.drawImage(m.getImagem(), m.getX() - 64, m.getY() - 15, null);
		}
	}

	public void colisaoDraktharTiro(Jogador1 jogador, int j) {
		List<TiroNave> tiros = jogador.getTiros();

		TiroNave tempTiro = tiros.get(j);
		Rectangle formaTiro = tempTiro.getBounds();
		Rectangle formaDrakthar = getBounds();
		if (formaTiro.intersects(formaDrakthar) && isVisivel()) {
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

		if (formaTiro.intersects(formaDrakthar) && isVisivel()) {
			perdeVida(1);
			tempTiro.setVisivel(false);
			if (vida == 0) {
				Jogador2.pontuacaoJogador2 += 500;
			}
		}
	}

	public void colisaoTirosTriplos1(Jogador1 jogador1, Jogador2 jogador2) {
		tiros4 = getTiros();

		for (int j = 0; j < tiros4.size(); j++) {
			TiroDrakthar tempTiro = tiros4.get(j);
			Rectangle formaTiro = tempTiro.getBounds();
			Rectangle formaNave1 = jogador1.getBounds();
			Rectangle formaNave2 = jogador2.getBounds();

			if (formaTiro.intersects(formaNave1) && jogador1.isVisivel()) {
				jogador1.perdeVida(4);
				tempTiro.setVisivel(false);
			}

			if (formaTiro.intersects(formaNave2) && jogador2.isVisivel()) {
				jogador2.perdeVida(4);
				tempTiro.setVisivel(false);
			}
		}
	}

	public void colisaoTirosTriplos2(Jogador1 jogador1, Jogador2 jogador2) {
		tiros2 = getTiros();

		for (int j = 0; j < tiros2.size(); j++) {
			TiroDrakthar tempTiro = tiros2.get(j);
			Rectangle formaTiro = tempTiro.getBounds();
			Rectangle formaNave1 = jogador1.getBounds();
			Rectangle formaNave2 = jogador2.getBounds();

			if (formaTiro.intersects(formaNave1) && jogador1.isVisivel() && tempTiro.isVisivel()) {
				jogador1.perdeVida(4);
				tempTiro.setVisivel(false);
			}

			if (formaTiro.intersects(formaNave2) && jogador2.isVisivel() && tempTiro.isVisivel()) {
				jogador2.perdeVida(4);
				tempTiro.setVisivel(false);
			}
		}
	}

	public void colisaoTirosTriplos3(Jogador1 jogador1, Jogador2 jogador2) {
		tiros3 = getTiros();

		for (int j = 0; j < tiros3.size(); j++) {
			TiroDrakthar tempTiro = tiros3.get(j);
			Rectangle formaTiro = tempTiro.getBounds();
			Rectangle formaNave1 = jogador1.getBounds();
			Rectangle formaNave2 = jogador2.getBounds();

			if (formaTiro.intersects(formaNave1) && jogador1.isVisivel() && tempTiro.isVisivel()) {
				jogador1.perdeVida(4);
				tempTiro.setVisivel(false);
			}

			if (formaTiro.intersects(formaNave2) && jogador2.isVisivel() && tempTiro.isVisivel()) {
				jogador2.perdeVida(4);
				tempTiro.setVisivel(false);
			}
		}
	}

	public void colisaoNaveTiro(Jogador1 jogador1, Jogador2 jogador2) {
		tiros1 = getTiros();

		for (int j = 0; j < tiros1.size(); j++) {
			TiroDrakthar tempTiroDrakthar = tiros1.get(j);
			Rectangle formaTiroDrakthar = tempTiroDrakthar.getBounds();
			Rectangle formaNave = jogador1.getBounds();

			if (formaTiroDrakthar.intersects(formaNave) && jogador1.isVisivel()) {
				jogador1.perdeVida(4);
				tempTiroDrakthar.setVisivel(false);
			}
		}

		for (int j = 0; j < tiros1.size(); j++) {
			TiroDrakthar tempTiroDrakthar = tiros1.get(j);
			Rectangle formaTiroDrakthar = tempTiroDrakthar.getBounds();
			Rectangle formaNave = jogador2.getBounds();
			if (formaTiroDrakthar.intersects(formaNave) && jogador2.isVisivel() && tempTiroDrakthar.isVisivel()) {
				jogador2.perdeVida(4);
				tempTiroDrakthar.setVisivel(false);
			}
		}
	}

	public void colisaoNaveDrakthar(Jogador1 jogador) {
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaDrakthar = getBounds();

		if (formaNave.intersects(formaDrakthar) && jogador.isVisivel() && contador1 == 0) {
			if (jogador.getEscudo() > 0) {
				jogador.perdeEscudo(4);
			} else {
				jogador.perdeVida(4);
			}
			contador1++;
		}
	}

	public void colisaoNaveDrakthar(Jogador2 jogador) {
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaDrakthar = getBounds();
		if (formaNave.intersects(formaDrakthar) && jogador.isVisivel() && contador2 == 0) {
			if (jogador.getEscudo() > 0) {
				jogador.perdeEscudo(4);
			} else {
				jogador.perdeVida(4);
			}
			contador2++;
		}
	}

	public void perdeVida(int dano) {
		this.vida = vida - dano;
	}

	public Rectangle getBounds() {
		return new Rectangle(x + 50, y, largura, altura);
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
