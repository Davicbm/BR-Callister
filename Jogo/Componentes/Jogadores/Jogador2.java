package Jogo.Componentes.Jogadores;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;;

public class Jogador2 {

	private int x;
	private int y;
	private int dx;
	private int dy;
	private Image imagem;
	private int altura;
	private int largura;
	private boolean isVisivel;
	private int vida;

	private List<TiroNave> tiros;
	private boolean podeAtirar = true;
    private long tempoUltimoTiro = System.currentTimeMillis();
    private long intervaloTiros = 300;

	public Jogador2() {
		this.x = 100;
		this.y = 700;
		this.vida = 20;
		this.isVisivel = true;

		tiros = new ArrayList<TiroNave>();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//br-callister2.gif");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}

	public void update() {
		x += dx;
		y += dy;
	}

	public void tiroSimples() {
		long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiro >= intervaloTiros) {
            this.tiros.add(new TiroNave(x + largura, y + (altura / 2)));
            tempoUltimoTiro = tempoAtual;
        }
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		if (codigo == KeyEvent.VK_ENTER && podeAtirar){
			tiroSimples();
			podeAtirar = false;
		}
		if(codigo == KeyEvent.VK_UP && getY() > 0) {
			dy=-4;
			if (codigo == KeyEvent.VK_LEFT && getX() >= 0){
				dx=-4;
			} else if (codigo == KeyEvent.VK_RIGHT && getX() <= 1300){
				dx=4;
			} else if (codigo == KeyEvent.VK_DOWN && getY() <= 720){
				dy=4;
			}
		}
			if (codigo == KeyEvent.VK_LEFT && getX() >= 0){
				dx=-4;
				if (codigo == KeyEvent.VK_UP && getY() >= 0){
					dy=-4;
				} else if (codigo == KeyEvent.VK_DOWN && getY() <= 720){
					dy=4;
				} else if(codigo == KeyEvent.VK_RIGHT && getX() <= 1300) {
					dx=4;
				}
			}
	
			if (codigo == KeyEvent.VK_RIGHT && getX() <= 1300){
				dx=4;
				if (codigo == KeyEvent.VK_UP && getY() >= 0){
					dy=-4;
				} else if (codigo == KeyEvent.VK_DOWN && getY() <= 720){
					dy=4;
				} else if(codigo == KeyEvent.VK_LEFT && getX() >= 0) {
					dx=-4;
				}
			}
	
			if (codigo == KeyEvent.VK_DOWN && getY() <= 720){
				dy=4;
				if (codigo == KeyEvent.VK_LEFT && getX() >= 0){
					dx=-4;
				} else if (codigo == KeyEvent.VK_RIGHT && getX() <= 1300){
					dx=4;
				} else if(codigo == KeyEvent.VK_UP && getY() >= 0) {
					dy=-4;
				}
			}
		if (codigo == KeyEvent.VK_ENTER) {
            podeAtirar = true;
        }
	}

	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if(codigo == KeyEvent.VK_UP && (getY() <= 0 || getY() >= 0)) {
			dy=0;
			if (codigo == KeyEvent.VK_LEFT && (getX() >= 0 || getX() <= 0)){
				dx=0;
			} else if (codigo == KeyEvent.VK_RIGHT && (getX() >= 1300 || getX() <= 1300)){
				dx=0;
			} else if (codigo == KeyEvent.VK_DOWN && (getY() >= 720 || getY() <= 720)){
				dy=0;
			}
		}
			if (codigo == KeyEvent.VK_LEFT && (getX() >= 0 || getX() <= 0)){
				dx=0;
				if (codigo == KeyEvent.VK_UNDO && (getY() <= 0 || getY() >= 0)){
					dy=0;
				} else if (codigo == KeyEvent.VK_DOWN && (getY() >= 720 || getY() <= 720)){
					dy=0;
				} else if(codigo == KeyEvent.VK_RIGHT && (getX() >= 1300 || getX() <= 1300)) {
					dx=0;
				}
			}
	
			if (codigo == KeyEvent.VK_RIGHT && (getX() >= 1300 || getX() <= 1300)){
				dx=0;
				if (codigo == KeyEvent.VK_UP && (getY() <= 0 || getY() >= 0)){
					dy=0;
				} else if (codigo == KeyEvent.VK_DOWN && (getY() >= 720 || getY() <= 720)){
					dy=0;
				} else if(codigo == KeyEvent.VK_LEFT && (getX() >= 0 || getX() <= 0)) {
					dx=0;
				}
			}
	
			if (codigo == KeyEvent.VK_DOWN && (getY() >= 720 || getY() <= 720)){
				dy=0;
				if (codigo == KeyEvent.VK_LEFT && (getX() >= 0 || getX() <= 0)){
					dx=0;
				} else if (codigo == KeyEvent.VK_RIGHT && (getX() >= 1300 || getX() <= 1300)){
					dx=0;
				} else if(codigo == KeyEvent.VK_UP && (getY() <= 0 || getY() >= 0)) {
					dy=0;
				}
			}

		if (codigo == KeyEvent.VK_ENTER) {
            podeAtirar = true;
        }
	}
	public void atirar(){
		List<TiroNave> tiros = getTiros();

		for (int i = 0; i < tiros.size(); i++) {
			TiroNave m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}
	}

	public void drawTiroNave (Graphics2D graficos){
		List<TiroNave> tiros = getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			TiroNave m = tiros.get(i);
			m.load();
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), null);
		}
	}
	public void perdeVida(int dano){
		this.vida = vida - dano;
	}

	public List<TiroNave> getTiros() {
		return tiros;
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
	public int getVida(){
		return this.vida;
	}
}