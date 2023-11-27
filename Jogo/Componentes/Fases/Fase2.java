package Jogo.Componentes.Fases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Jogo.Container;
import Jogo.Componentes.Inimigos.Alien;
import Jogo.Componentes.Inimigos.Robo;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Objetos.BarraVida;
import Jogo.Componentes.Objetos.PowerUp;

public class Fase2 extends Fase implements ActionListener {

	private Image fundo;
	private Image alerta;

	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private BarraVida barra;
	private List<PowerUp> powerUps;
	private PowerUp powerUp;

	private Timer timer;
	private Robo robo1;
	private Robo robo2;
	private List<Robo> robos;

	private boolean vitoria;
	private boolean doisJogadores;
	private boolean gameOver;

	private String nomeJogador1 = Menu.nomeJogador1;
	private String nomeJogador2 = Menu.nomeJogador2;

	private Alien alien1;
	private Alien alien2;
	private int contador = 0;

	TecladoAdapter teclado = new TecladoAdapter();
	Fase fase = new Fase();
	private Container container;

	public Fase2(Container container) {
		this.container = container;

		setFocusable(true);
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("assets//fase02.png");
		fundo = referencia.getImage();

		referencia = new ImageIcon("assets//warninggif.gif");
		alerta = referencia.getImage();

		jogador1 = new Jogador1();
		jogador2 = new Jogador2();

		jogador1.load();
		jogador2.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();

		inicializaInimigos();
		inicializaPowerUps();

		if (Menu.doisJogadores) {
			doisJogadores = true;
		} else {
			doisJogadores = false;
		}

		this.requestFocusInWindow();
	}

	public void inicializaInimigos() {

		robos = new ArrayList<Robo>();

		for (int i = 0; i < 40; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 10;
			robos.add(new Robo(x, y));
			robos.get(i).setVida(2);
		}

		robo1 = new Robo(1800, 100);
		robo2 = new Robo(1800, 600);

		alien1 = new Alien(1800, 300);
		alien2 = new Alien(1800, 400);

		alien1.load();
		alien2.load();

		robo1.load();
		robo2.load();
	}

	public void inicializaPowerUps() {
		powerUps = new ArrayList<PowerUp>();

		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 10;
			int codigo = (int) (Math.random() * 3) + 1;

			powerUps.add(new PowerUp(x, y, codigo));
		}
	}

	public void paint(Graphics g) {
		barra = new BarraVida();
		Graphics2D graficos = (Graphics2D) g;
		Font fonte = loadFont("assets//PressStart2P.ttf", 16);
		Font fonte2 = loadFont("assets//PressStart2P.ttf", 12);

		graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);

		if (jogador1.isVisivel()) {
			graficos.drawImage(jogador1.getImagem(), jogador1.getX(), jogador1.getY(), this);
			jogador1.drawTiroNave(graficos);
		}
		if (doisJogadores == true) {
			if (jogador2.isVisivel()) {
				graficos.drawImage(jogador2.getImagem(), jogador2.getX(), jogador2.getY(), this);
				jogador2.drawTiroNave(graficos);
			}
		} else {
			jogador2.setVisivel(false);
		}
		contador = 0;
		for (int i = 0; i < robos.size(); i++) {
			if (robos.get(i).isVisivel() == false) {
				contador += 1;
			}
		}

		if (contador == robos.size()) {
			if (robo1.isVisivel()) {
				graficos.drawImage(robo1.getImagem(), robo1.getX(), robo1.getY(), this);
				if (robo1.getX() != 1000) {
					graficos.drawImage(alerta, 1450, 150, this);
				}
			}
			if (robo2.isVisivel()) {
				graficos.drawImage(robo2.getImagem(), robo2.getX(), robo2.getY(), this);
				if (robo2.getX() != 1000) {
					graficos.drawImage(alerta, 1450, 600, this);
				}
			}

			if (alien1.isVisivel()) {
				graficos.drawImage(alien1.getImagem(), alien1.getX(), alien1.getY(), this);
				if (alien1.getX() != 1200) {
					// muda a posição para os alien
					graficos.drawImage(alerta, 1450, 300, this);
				}
			}
			if (alien2.isVisivel()) {
				graficos.drawImage(alien2.getImagem(), alien2.getX(), alien2.getY(), this);
				if (alien2.getX() != 1200) {
					graficos.drawImage(alerta, 1450, 400, this);
				}
			}

			if (robo1.getX() == 1000) {
				if (robo1.isVisivel()) {
					robo1.drawTiroRobo(graficos);
				}
			}
			if (robo2.getX() == 1000) {
				if (robo2.isVisivel()) {
					robo2.drawTiroRobo(graficos);
				}
			}

			if (alien1.getX() == 1200) {
				if (alien1.isVisivel()) {
					alien1.drawTiroAlien(graficos);
				}
			}
			if (alien2.getX() == 1200) {
				if (alien2.isVisivel()) {
					alien2.drawTiroAlien(graficos);
				}
			}
		}
		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);
			robo.load2();
			graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
		}
		for (int j = 0; j < powerUps.size(); j++) {
			powerUp = powerUps.get(j);
			powerUp.load();

			graficos.drawImage(powerUp.getImagem(), powerUp.getX(), powerUp.getY(), this);
		}
		
		
		g.setFont(fonte);
		g.setColor(Color.WHITE);
		graficos.drawString("Fase 2", 700, 50);

		g.setFont(fonte2);
		g.setColor(Color.WHITE);

		if (doisJogadores) {
			graficos.drawString("-- Pontuações -- ", 1325, 35);
			graficos.drawString(nomeJogador1 + " = " + jogador1.getPontuacaoJogador1(), 1350, 60);
			graficos.drawString(nomeJogador2 + " = " + jogador2.getPontuacaoJogador2(), 1350, 90);
		} else if (doisJogadores == false) {
			graficos.drawString("-- Pontuações -- ", 1325, 35);
			graficos.drawString(nomeJogador1 + " = " + jogador1.getPontuacaoJogador1(), 1350, 50);
		}

		g.setFont(fonte2);
		g.setColor(Color.WHITE);
		graficos.drawString(nomeJogador1, 15, 30);
		barra.paintBarraVida(graficos, jogador1);

		if (doisJogadores) {
			if (nomeJogador1 != null && nomeJogador1 != null) {
				g.setFont(fonte2);
				g.setColor(Color.WHITE);
				graficos.drawString(nomeJogador2, 15, 790);
				barra.paintBarraVida(graficos, jogador2);
			}
		}

		if (gameOver == true) {
			ImageIcon fimJogo = new ImageIcon("assets//fim_de_jogo.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
			g.setFont(fonte);
			g.setColor(Color.WHITE);
			graficos.drawString("Aperte enter para reiniciar o jogo!", 500, 800);
		}

		if (vitoria == true) {
			g.setFont(fonte);
			g.setColor(Color.WHITE);
			ImageIcon vitoriaJogo = new ImageIcon("assets//victory.png");
			graficos.drawImage(vitoriaJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
			graficos.drawString("Pontuação Jogador 1 = " + jogador1.getPontuacaoJogador1(), 20, 40);
			graficos.drawString("Pontuação Jogador 2 = " + jogador2.getPontuacaoJogador2(), 1125, 40);
			graficos.drawString("Aperte enter para a próxima fase!", 500, 800);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador1.update();
		jogador1.atirar();

		if (doisJogadores) {
			jogador2.update();
			jogador2.atirar();
		}

		contador = 0;
		for (int i = 0; i < robos.size(); i++) {
			if (robos.get(i).isVisivel() == false) {
				contador += 1;
			}
		}

		if (contador == robos.size()) {
			robo1.updateRoboAtirador(1000);
			robo2.updateRoboAtirador(1000);

			alien1.updateAlien(1200);
			alien2.updateAlien(1200);
		}

		if (robo1.getX() == 1000 && robo2.getX() == 1000 ) {
			robo1.atirar();
			robo2.atirar();
		}

		if (alien1.getX() == 1200 && alien2.getX() == 1200) {
			alien1.atirar();
			alien2.atirar();
		}

		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);

			if (robo.isVisivel()) {
				robo.updateRoboDeColisao();
			} else {
				robos.remove(j);
			}
		}

		for (int j = 0; j < powerUps.size(); j++) {
			powerUp = powerUps.get(j);

			if (powerUp.isVisivel()) {
				powerUp.update();
			} else {
				powerUps.remove(j);
			}
		}
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		// Colisões com Power Ups:
		fase.colisoesPowerUps(powerUps, jogador1, jogador2);

		// Colisões de Nave com Robô:
		fase.colisoesNavesRobos(robos, jogador1, jogador2);

		// Colisões de tiro da Nave com Robo:
		fase.colisoesTiroRobo(robo1, jogador1, jogador2, 1000);
		fase.colisoesTiroRobo(robo2, jogador1, jogador2, 1000);

		fase.colisoesTiroRobo2(jogador1, robos);
		fase.colisoesTiroRobo2(jogador2, robos);

		// Colisões de tiro da Nave com Alien:
		fase.colisoesTiroAlien(alien1, jogador1, jogador2, 1200);
		
		// Colisões de tiro do Robo com a Nave:
		robo1.colisaoNaveTiro(jogador1, jogador2);
		robo2.colisaoNaveTiro(jogador1, jogador2);

		// Colisões de tiro do Alien com a Nave:
		alien1.colisaoNaveTiro(jogador1);
		alien2.colisaoNaveTiro(jogador1);

		alien1.colisaoNaveTiro(jogador2);
		alien2.colisaoNaveTiro(jogador2);

		//Checa a vida das entidades:
		fase.checarRobo(robo1);
		fase.checarRobo(robo2);
		
		fase.checarAlien(alien1);
		fase.checarAlien(alien2);

		fase.checarRobos(robos);

		if (robo1.isVisivel() == false && robo2.isVisivel() == false && alien1.isVisivel() == false
				&& alien2.isVisivel() == false) {
			vitoria = true;
		}
		
		gameOver = fase.checarJogadores(jogador1, jogador2, doisJogadores);
	}

	private class TecladoAdapter implements KeyListener {
		@Override

		public void keyPressed(KeyEvent e) {
			int codigo = e.getKeyCode();

			jogador1.keyPressed(e);
			if (doisJogadores) {
				jogador2.keyPressed(e);
			}

			if (vitoria) {
				if (codigo == KeyEvent.VK_ENTER) {
					container.avancarFase();
				}
			}
			if (gameOver) {
				if (codigo == KeyEvent.VK_ENTER) {
					container.reiniciarJogo();
				}
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			jogador1.keyRelease(e);
			if (doisJogadores) {
				jogador2.keyRelease(e);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
	}

	public boolean isDoisJogadores() {
		return doisJogadores;
	}

	public void setDoisJogadores(boolean doisJogadores) {
		this.doisJogadores = doisJogadores;
	}

	public void setVitoria(boolean vitoria) {
		this.vitoria = vitoria;
	}

	public boolean isVitoria() {
		return this.vitoria;
	}
}