package Jogo.Componentes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.w3c.dom.css.Rect;

import Jogo.Componentes.Inimigos.Robo;
import Jogo.Componentes.Inimigos.TiroRobo;
import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Jogadores.TiroNave;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private Timer timer;
	private Robo robo1;
	private Robo robo2;
	private Robo robo3;
	private boolean emJogo;

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("assets//fase1.png");
		fundo = referencia.getImage();

		jogador1 = new Jogador1();
		jogador2 = new Jogador2();
		

		jogador1.load();
		jogador2.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();

		inicializaInimigos();
		robo1.load();
		robo2.load();
		robo3.load();
		emJogo = true;
	}

	public void inicializaInimigos() {

		robo1 = new Robo(1200, 150);
		robo2 = new Robo(1000, 350);
		robo3 = new Robo(1200, 600);

	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
			
			if(jogador1.isVisivel()){
				graficos.drawImage(jogador1.getImagem(), jogador1.getX(), jogador1.getY(), this);
				jogador1.drawTiroNave(graficos);
			}
			
			if(jogador2.isVisivel()){
				graficos.drawImage(jogador2.getImagem(), jogador2.getX(), jogador2.getY(), this);
				jogador2.drawTiroNave(graficos);
			}
			
			if (robo1.isVisivel()){
				graficos.drawImage(robo1.getImagem(), robo1.getX(), robo1.getY(), this);
			}
			if (robo2.isVisivel()){
				graficos.drawImage(robo2.getImagem(), robo2.getX(), robo2.getY(), this);
			}
			if (robo3.isVisivel()){
				graficos.drawImage(robo3.getImagem(), robo3.getX(), robo3.getY(), this);
			}
			
			if (robo1.isVisivel()){
				robo1.drawTiroRobo(graficos);
			} 

			if (robo2.isVisivel()){
				robo2.drawTiroRobo(graficos);
			}
			if (robo3.isVisivel()){
				robo3.drawTiroRobo(graficos);
			} } else {
				ImageIcon fimJogo = new ImageIcon("res\\fimdejogo.png");
				graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			}
			g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador1.update();
		jogador2.update();

		jogador1.atirar();
		jogador2.atirar();

		robo1.atirar();
		robo2.atirar();
		robo3.atirar();

		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		Rectangle formaNave1 = jogador1.getBounds();
		Rectangle formaNave2 = jogador2.getBounds();
		Rectangle formaRobo1 = robo1.getBounds();
		Rectangle formaRobo2 = robo2.getBounds();
		Rectangle formaRobo3 = robo3.getBounds();
		Rectangle formaTiro;
		Rectangle formaTiroRobo;

		//Colisões de Nave com Robô:
		if (robo1.isVisivel()){
			if (formaNave1.intersects(formaRobo1)) {
				jogador1.setVisivel(false);
				robo1.setVisivel(false);
				emJogo = false;
			} 
			if (formaNave2.intersects(formaRobo1)) {
				jogador2.setVisivel(false);
				robo1.setVisivel(false);
				emJogo = false;
			} 
		}
		if (robo2.isVisivel()){
			if (formaNave1.intersects(formaRobo2)) {
				jogador1.setVisivel(false);
				robo2.setVisivel(false);
				emJogo = false;
			} 
			if (formaNave2.intersects(formaRobo2)) {
				jogador2.setVisivel(false);
				robo2.setVisivel(false);
				emJogo = false;
			} 
		}
		if (robo3.isVisivel()){
			if (formaNave1.intersects(formaRobo3)) {
				jogador1.setVisivel(false);
				robo3.setVisivel(false);
				emJogo = false;
			} 
			if (formaNave2.intersects(formaRobo3)) {
				jogador2.setVisivel(false);
				robo3.setVisivel(false);
				emJogo = false;
			} 
		}

		//Colisões de tiro da Nave com Robo:
		List<TiroNave> tiros1 = jogador1.getTiros();
		for (int j = 0; j < tiros1.size(); j++) {
			robo1.colisaoRoboTiro(jogador1, j);
			robo2.colisaoRoboTiro(jogador1, j);
			robo3.colisaoRoboTiro(jogador1, j);
		}
		List<TiroNave> tiros2 = jogador2.getTiros();
		for (int j = 0; j < tiros2.size(); j++) {
			robo1.colisaoRoboTiro(jogador2, j);
			robo2.colisaoRoboTiro(jogador2, j);
			robo3.colisaoRoboTiro(jogador2, j);
		}

		//Colisões de tiro do Robo com a Nave:
		robo1.colisaoNaveTiro(jogador1);
		robo1.colisaoNaveTiro(jogador2);

		robo2.colisaoNaveTiro(jogador1);
		robo2.colisaoNaveTiro(jogador2);

		robo3.colisaoNaveTiro(jogador1);
		robo3.colisaoNaveTiro(jogador2);
		
		if (jogador1.getVida() == 0 ){
			jogador1.setVisivel(false);
		}
		if (jogador2.getVida() == 0 ){
			jogador2.setVisivel(false);
		} 
		if (jogador1.getVida() == 0 && jogador2.getVida() == 0){
			emJogo = false;
		}
	}

	private class TecladoAdapter implements KeyListener {
		@Override

		public void keyPressed(KeyEvent e) {
			jogador1.keyPressed(e);
			jogador2.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			jogador1.keyRelease(e);
			jogador2.keyRelease(e);
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
	}

}