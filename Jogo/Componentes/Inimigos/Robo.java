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

public class Robo {
	private Image imagem;
	private int x;
	private int y;
	private int largura;
	private int altura;
	private boolean isVisivel;
	private int vida;	

	private List<TiroRobo> tiros1;
    private long tempoUltimoTiro = System.currentTimeMillis();
    private long intervaloTiros = 2000;

	private static int velocidade = 5;
	
	public Robo(int x, int y) {
		this.x = x;
		this.y = y;
		this.isVisivel = true;
		this.vida = 3;

		tiros1 = new ArrayList<TiroRobo>();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//robo1.gif");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void load2(){
		ImageIcon referencia = new ImageIcon("assets//robodecolisao.gif");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void updateRoboDeColisao() {
		this.x -= velocidade;
		
		if(this.x < 0) {
			isVisivel = false;
		}
	}
	public void updateRoboAtirador(int localizacaoX, int localizacaoY){
		boolean atualizado = false;
		this.y -= velocidade;
		if (this.y < localizacaoY){
			this.y = localizacaoY;
			atualizado = true;
		}
		if (atualizado){
			this.x -= velocidade;
		
			if(this.x < localizacaoX) {
				this.x = localizacaoX;
			}
		}
	}
	
	public void tiroSimples() {
		long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiro >= intervaloTiros) {
            this.tiros1.add(new TiroRobo(x + largura, y + (altura / 2)));
            tempoUltimoTiro = tempoAtual;
        }
	}
	public void atirar() {
		tiroSimples();
		tiros1 = getTiros();
		for (int i = 0; i < tiros1.size(); i++) {
			TiroRobo m = tiros1.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros1.remove(i);
			}
		}
	}
	public void drawTiroRobo(Graphics2D graficos){
		List<TiroRobo> tiros2 = getTiros();
		for (int j = 0; j < tiros2.size(); j++) {
			TiroRobo m = tiros2.get(j);
			m.load();
			graficos.drawImage(m.getImagem(), m.getX() - 64, m.getY() - 15, null);
		}
	}

	public void colisaoRoboTiro (Jogador1 jogador, int j){
		List<TiroNave> tiros3 = jogador.getTiros();
			TiroNave tempTiro = tiros3.get(j);
			Rectangle formaTiro = tempTiro.getBounds();
			Rectangle formaRobo = getBounds();
			if (formaTiro.intersects(formaRobo)) {
				perdeVida(1);
				tempTiro.setVisivel(false);
				jogador.setPontuacaoJogador1(10); 
			}

	}
	public void colisaoRoboTiro (Jogador2 jogador, int j){
		List<TiroNave> tiros3 = jogador.getTiros();
			TiroNave tempTiro = tiros3.get(j);
			Rectangle formaTiro = tempTiro.getBounds();
			Rectangle formaRobo = getBounds();
			if (formaTiro.intersects(formaRobo)) {
				perdeVida(1);
				tempTiro.setVisivel(false);
				jogador.setPontuacaoJogador2(10);
			}
	}

	public void colisaoNaveTiro(Jogador1 jogador){
		List<TiroRobo> tiros3 = getTiros();
		for (int j = 0; j < tiros3.size(); j++) {
			TiroRobo tempTiroRobo = tiros3.get(j);
			Rectangle formaTiroRobo = tempTiroRobo.getBounds();
			Rectangle formaNave = jogador.getBounds();
			if (formaTiroRobo.intersects(formaNave)) {
				jogador.perdeVida(5);
				tempTiroRobo.setVisivel(false);
			}
		}
	}
	public void colisaoNaveTiro(Jogador2 jogador){
		List<TiroRobo> tiros3 = getTiros();
		for (int j = 0; j < tiros3.size(); j++) {
			TiroRobo tempTiroRobo = tiros3.get(j);
			Rectangle formaTiroRobo = tempTiroRobo.getBounds();
			Rectangle formaNave = jogador.getBounds();
			if (formaTiroRobo.intersects(formaNave)) {
				jogador.perdeVida(5);
				tempTiroRobo.setVisivel(false);
			}
		}
	}

	public void colisaoNaveRobo(Jogador1 jogador){
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaRobo = getBounds();
		if (formaNave.intersects(formaRobo)) {
			jogador.perdeVida(2);
		} 
	}
	public void colisaoNaveRobo(Jogador2 jogador){
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaRobo = getBounds();
		if (formaNave.intersects(formaRobo)) {
			jogador.perdeVida(2);
		} 
	}

	public void colisaoNaveRoboDeColisao(Jogador1 jogador){
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaRobo = getBounds();
		if (formaRobo.intersects(formaNave)) {
			jogador.perdeVida(2);
			setVisivel(false);
		} 
	}
	public void colisaoNaveRoboDeColisao(Jogador2 jogador){
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaRobo = getBounds();
		if (formaRobo.intersects(formaNave)) {
			jogador.perdeVida(2);
			setVisivel(false);
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
	public List<TiroRobo> getTiros() {
		return tiros1;
	} 
	public void setVida(int vida){
		this.vida = vida;
	}
	public int getVida(){
		return this.vida;
	}
}