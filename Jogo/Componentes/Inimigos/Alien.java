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

public class Alien {
	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;
	private int vida;	

	private List<TiroAlien> tiros1;
    private long tempoUltimoTiro = System.currentTimeMillis();
    private long intervaloTiros = 2800;

	private static int velocidade = 3;
	
	public Alien(int x, int y) {
		this.x = x;
		this.y = y;
		this.isVisivel = true;
		this.vida = 6;

		tiros1 = new ArrayList<TiroAlien>();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//navealiens.gif");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	
	public void updateAlien(int localizacaoX){
			this.x -= velocidade;
		
			if(this.x < localizacaoX) {
				this.x = localizacaoX;
			}
	}
	
	public void tiroSimples() {
		long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiro >= intervaloTiros) {
            this.tiros1.add(new TiroAlien(x + largura, y + (altura / 2)));
            tempoUltimoTiro = tempoAtual;
        }
	}
	public void atirar() {
		tiroSimples();
		tiros1 = getTiros();
		for (int i = 0; i < tiros1.size(); i++) {
			TiroAlien m = tiros1.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros1.remove(i);
			}
		}
	}
	public void drawTiroAlien(Graphics2D graficos){
		List<TiroAlien> tiros2 = getTiros();
		for (int j = 0; j < tiros2.size(); j++) {
			TiroAlien m = tiros2.get(j);
			m.load();
			graficos.drawImage(m.getImagem(), m.getX() - 64, m.getY() - 15, null);
		}
	}

	public void colisaoAlienTiro (Jogador1 jogador, int j){
		List<TiroNave> tiros3 = jogador.getTiros();
			TiroNave tempTiro = tiros3.get(j);
			Rectangle formaTiro = tempTiro.getBounds();
			Rectangle formaAlien = getBounds();
			if (formaTiro.intersects(formaAlien) && isVisivel() && tempTiro.isVisivel()) {
				perdeVida(1);
				tempTiro.setVisivel(false);
				if (vida == 0){
					Jogador1.pontuacaoJogador1 += 30;
				}
			}

	}
	public void colisaoAlienTiro (Jogador2 jogador, int j){
		List<TiroNave> tiros3 = jogador.getTiros();
			TiroNave tempTiro = tiros3.get(j);
			Rectangle formaTiro = tempTiro.getBounds();
			Rectangle formaAlien = getBounds();
			if (formaTiro.intersects(formaAlien) && isVisivel() && tempTiro.isVisivel()) {
				perdeVida(1);
				tempTiro.setVisivel(false);
				if (vida == 0){
					Jogador2.pontuacaoJogador2 += 30;
				}
			}
	}

	public void colisaoNaveTiro(Jogador1 jogador){
		List<TiroAlien> tiros3 = getTiros();
		for (int j = 0; j < tiros3.size(); j++) {
			TiroAlien tempTiroAlien = tiros3.get(j);
			Rectangle formaTiroAlien = tempTiroAlien.getBounds();
			Rectangle formaNave = jogador.getBounds();
			if (formaTiroAlien.intersects(formaNave) && jogador.isVisivel()) {
				if (jogador.getEscudo() > 0){
					jogador.perdeEscudo(3);
				} else {
					jogador.perdeVida(3);
				}
				tempTiroAlien.setVisivel(false);
			}
		}
	}
	public void colisaoNaveTiro(Jogador2 jogador){
		List<TiroAlien> tiros3 = getTiros();
		for (int j = 0; j < tiros3.size(); j++) {
			TiroAlien tempTiroAlien = tiros3.get(j);
			Rectangle formaTiroAlien= tempTiroAlien.getBounds();
			Rectangle formaNave = jogador.getBounds();
			if (formaTiroAlien.intersects(formaNave) && jogador.isVisivel()) {
				if (jogador.getEscudo() > 0){
					jogador.perdeEscudo(3);
				} else {
					jogador.perdeVida(3);
				}
				tempTiroAlien.setVisivel(false);
			}
		}
	}

	public void perdeVida(int dano){
		this.vida = vida - dano;
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
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImagem() {
		return imagem;
	}
	public List<TiroAlien> getTiros() {
		return tiros1;
	} 
	public void setVida(int vida){
		this.vida = vida;
	}
	public int getVida(){
		return this.vida;
	}
}