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
	private int pontuacaoJogador2 = 0;

	public Jogador2() {
		this.x = 100;
		this.y = 650;
		this.vida = 10;
		this.isVisivel = false;

		tiros = new ArrayList<TiroNave>();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("assets//br-callister2.gif");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}

	public void update() {
		if (x + dx >= 0 && x + dx + largura / 2 <= 750) {
			x += dx;
		}
		if (y + dy >= 0 && y + dy + altura <= 850) {
			y += dy;
		}
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
		if(codigo == KeyEvent.VK_UP) {
			dy=-4;
			if (codigo == KeyEvent.VK_LEFT){
				dx=-4;
			} else if (codigo == KeyEvent.VK_RIGHT){
				dx=4;
			} else if (codigo == KeyEvent.VK_DOWN){
				dy=4;
			}
		}
			if (codigo == KeyEvent.VK_LEFT){
				dx=-4;
				if (codigo == KeyEvent.VK_UP){
					dy=-4;
				} else if (codigo == KeyEvent.VK_DOWN){
					dy=4;
				} else if(codigo == KeyEvent.VK_RIGHT ) {
					dx=4;
				}
			}
	
			if (codigo == KeyEvent.VK_RIGHT ){
				dx=4;
				if (codigo == KeyEvent.VK_UP ){
					dy=-4;
				} else if (codigo == KeyEvent.VK_DOWN ){
					dy=4;
				} else if(codigo == KeyEvent.VK_LEFT ) {
					dx=-4;
				}
			}
	
			if (codigo == KeyEvent.VK_DOWN ){
				dy=4;
				if (codigo == KeyEvent.VK_LEFT ){
					dx=-4;
				} else if (codigo == KeyEvent.VK_RIGHT ){
					dx=4;
				} else if(codigo == KeyEvent.VK_UP) {
					dy=-4;
				}
			}
		if (codigo == KeyEvent.VK_ENTER) {
            podeAtirar = true;
        }
	}

	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if(codigo == KeyEvent.VK_UP) {
			dy=0;
			if (codigo == KeyEvent.VK_LEFT){
				dx=0;
			} else if (codigo == KeyEvent.VK_RIGHT ){
				dx=0;
			} else if (codigo == KeyEvent.VK_DOWN ){
				dy=0;
			}
		}
			if (codigo == KeyEvent.VK_LEFT ){
				dx=0;
				if (codigo == KeyEvent.VK_UNDO ){
					dy=0;
				} else if (codigo == KeyEvent.VK_DOWN ){
					dy=0;
				} else if(codigo == KeyEvent.VK_RIGHT ) {
					dx=0;
				}
			}
	
			if (codigo == KeyEvent.VK_RIGHT ){
				dx=0;
				if (codigo == KeyEvent.VK_UP){
					dy=0;
				} else if (codigo == KeyEvent.VK_DOWN){
					dy=0;
				} else if(codigo == KeyEvent.VK_LEFT) {
					dx=0;
				}
			}
	
			if (codigo == KeyEvent.VK_DOWN ){
				dy=0;
				if (codigo == KeyEvent.VK_LEFT){
					dx=0;
				} else if (codigo == KeyEvent.VK_RIGHT){
					dx=0;
				} else if(codigo == KeyEvent.VK_UP) {
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
			graficos.drawImage(m.getImagem(), m.getX(), m.getY() + 10, null);
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
	public void setPontuacaoJogador2(int pontuacaoJogador2){
		this.pontuacaoJogador2 += pontuacaoJogador2;
	}
	public int getPontuacaoJogador2() {
		return this.pontuacaoJogador2;
	}
}