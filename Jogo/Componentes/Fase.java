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
import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private Timer timer;
	private List<Robo> robo;
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
		emJogo = true;
	}

	public void inicializaInimigos() {
		int coordenadas[] = new int[40];
		robo = new ArrayList<Robo>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			robo.add(new Robo(x, y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
			
			graficos.drawImage(jogador1.getImagem(), jogador1.getX(), jogador1.getY(), this);
			graficos.drawImage(jogador2.getImagem(), jogador2.getX(), jogador2.getY(), this);

			List<Tiro> tiros1 = jogador1.getTiros();
			List<Tiro> tiros2 = jogador2.getTiros();

			for (int i = 0; i < tiros1.size(); i++) {
				Tiro m = tiros1.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < tiros2.size(); i++) {
				Tiro m = tiros2.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int j = 0; j < robo.size(); j++) {
				Robo inimigo = robo.get(j);
				inimigo.load();
				graficos.drawImage(inimigo.getImagem(), inimigo.getX(), inimigo.getY(), this);

			} } else {
				ImageIcon fimJogo= new ImageIcon("res\\fimdejogo.png");
				graficos.drawImage(fimJogo.getImage(), 0, 0, null);
			}
			g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador1.update();
		jogador2.update();

		List<Tiro> tiros1 = jogador1.getTiros();
		List<Tiro> tiros2 = jogador2.getTiros();

		for (int i = 0; i < tiros1.size(); i++) {
			Tiro m = tiros1.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros1.remove(i);
			}
		}

		for (int i = 0; i < tiros2.size(); i++) {
			Tiro m = tiros2.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros2.remove(i);
			}
		}

		for (int j = 0; j < robo.size(); j++) {
			Robo inimigo = robo.get(j);

			if (inimigo.isVisivel() == false) {
				robo.remove(j);
			}
		}
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		//Rectangle formaNave = jogador1.getBounds();
		Rectangle formaRobo;
		Rectangle formaTiro;

		/*for (int i = 0; i < inimigo1.size(); i++) {
			Inimigo1 tempInimigo1 = inimigo1.get(i);
			formaInimigo1 = tempInimigo1.getBounds();
			if (formaNave.intersects(formaInimigo1)) {
				jogador.setVisivel(false);
				tempInimigo1.setVisivel(false);
				emJogo = false;
			}
		}*/
		List<Tiro> tiros1 = jogador1.getTiros();
		for (int j = 0; j < tiros1.size(); j++) {
			Tiro tempTiro = tiros1.get(j);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < robo.size(); o++) {
				Robo tempRobo = robo.get(o);
				formaRobo = tempRobo.getBounds();
				if (formaTiro.intersects(formaRobo)) {
					tempRobo.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
		List<Tiro> tiros2 = jogador2.getTiros();
		for (int j = 0; j < tiros2.size(); j++) {
			Tiro tempTiro = tiros2.get(j);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < robo.size(); o++) {
				Robo tempRobo = robo.get(o);
				formaRobo = tempRobo.getBounds();
				if (formaTiro.intersects(formaRobo)) {
					tempRobo.setVisivel(false);
					tempTiro.setVisivel(false);
				}
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
