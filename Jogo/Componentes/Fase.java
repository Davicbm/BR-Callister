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
		int x = 1200;

		robo1 = new Robo(x, 150);
		robo2 = new Robo(x, 350);
		robo3 = new Robo(x, 600);

	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
			
			graficos.drawImage(jogador1.getImagem(), jogador1.getX(), jogador1.getY(), this);
			graficos.drawImage(jogador2.getImagem(), jogador2.getX(), jogador2.getY(), this);
			
			if (robo1.isVisivel()){
				graficos.drawImage(robo1.getImagem(), robo1.getX(), robo1.getY(), this);
			}
			if (robo2.isVisivel()){
				graficos.drawImage(robo2.getImagem(), robo2.getX(), robo2.getY(), this);
			}
			if (robo3.isVisivel()){
				graficos.drawImage(robo3.getImagem(), robo3.getX(), robo3.getY(), this);
			}
			
			List<TiroNave> tiros1 = jogador1.getTiros();
			List<TiroNave> tiros2 = jogador2.getTiros();

			List<TiroRobo> tiros3 = robo1.getTiros();
			List<TiroRobo> tiros4 = robo2.getTiros();
			List<TiroRobo> tiros5 = robo3.getTiros();
			
			for (int i = 0; i < tiros1.size(); i++) {
				TiroNave m = tiros1.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < tiros2.size(); i++) {
				TiroNave m = tiros2.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			
			robo1.atirar();
			for (int j = 0; j < tiros3.size(); j++) {
				TiroRobo m = tiros3.get(j);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			} 
			robo2.atirar();;
			for (int j = 0; j < tiros4.size(); j++) {
				TiroRobo m = tiros4.get(j);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			robo3.atirar();;
			for (int j = 0; j < tiros5.size(); j++) {
				TiroRobo m = tiros5.get(j);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			} 

			} else {
				ImageIcon fimJogo= new ImageIcon("res\\fimdejogo.png");
				graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			}
			g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador1.update();
		jogador2.update();

		List<TiroNave> tiros1 = jogador1.getTiros();
		List<TiroNave> tiros2 = jogador2.getTiros();
		List<TiroRobo> tiros3 = robo1.getTiros();

		for (int i = 0; i < tiros1.size(); i++) {
			TiroNave m = tiros1.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros1.remove(i);
			}
		}

		for (int i = 0; i < tiros2.size(); i++) {
			TiroNave m = tiros2.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros2.remove(i);
			}
		}
		for (int i = 0; i < tiros3.size(); i++) {
			TiroRobo m = tiros3.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros3.remove(i);
			}
		}
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
		List<TiroNave> tiros1 = jogador1.getTiros();
		for (int j = 0; j < tiros1.size(); j++) {
			TiroNave tempTiro = tiros1.get(j);
			formaTiro = tempTiro.getBounds();
				formaRobo1 = robo1.getBounds();
				if (formaTiro.intersects(formaRobo1)) {
					robo1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
				formaRobo2 = robo2.getBounds();
				if (formaTiro.intersects(formaRobo2)) {
					robo2.setVisivel(false);
					tempTiro.setVisivel(false);
				}
				formaRobo3 = robo3.getBounds();
				if (formaTiro.intersects(formaRobo3)) {
					robo3.setVisivel(false);
					tempTiro.setVisivel(false);
				}
		}
		List<TiroNave> tiros2 = jogador2.getTiros();
		for (int j = 0; j < tiros2.size(); j++) {
			TiroNave tempTiro = tiros2.get(j);
			formaTiro = tempTiro.getBounds();
				
				formaRobo1 = robo1.getBounds();
				if (formaTiro.intersects(formaRobo1)) {
					robo1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
				formaRobo2 = robo2.getBounds();
				if (formaTiro.intersects(formaRobo2)) {
					robo2.setVisivel(false);
					tempTiro.setVisivel(false);
				}
				formaRobo3 = robo3.getBounds();
				if (formaTiro.intersects(formaRobo3)) {
					robo3.setVisivel(false);
					tempTiro.setVisivel(false);
				}
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
