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
import Jogo.Componentes.Objetos.BarraVidaInimigos;
import Jogo.Componentes.Objetos.PowerUp;

public class Fase2 extends Fase implements ActionListener {

	private Image fundo;
	private Image alerta;

	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private BarraVida barra;
	private List<BarraVidaInimigos> barras;
	private List<BarraVidaInimigos> barras2;
	private List<PowerUp> powerUps;
	private PowerUp powerUp;

	private Robo robo1;
	private Robo robo2;
	private Robo robo3;
	private Robo robo4;
	private List<Robo> robos;
	private List<Robo> robos2;
	private Alien alien1;
	private Alien alien2;
	private Alien alien3;
	private Alien alien4;
	private Alien alien5;

	private boolean vitoria;
	private boolean doisJogadores;
	private boolean gameOver;
	private boolean proximaOnda;

	private String nomeJogador1 = Menu.nomeJogador1;
	private String nomeJogador2 = Menu.nomeJogador2;

	private int contador = 0;
	private Timer timer;

	private boolean pausado = false;
	private int opcaoMenuPausa = 0;

	TecladoAdapter teclado = new TecladoAdapter();
	Fase fase = new Fase(false);

	private Container container;

	public Fase2(Container container) {
		super(false);
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
		barras = new ArrayList<BarraVidaInimigos>();

		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 10;

			robos.add(new Robo(x, y));
			robos.get(i).setVida(2);
			barras.add(new BarraVidaInimigos(x, y));
		}

		robos2 = new ArrayList<Robo>();
		barras2 = new ArrayList<BarraVidaInimigos>();

		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * 6000) + 1980;
			int y = (int) (Math.random() * 650) + 10;

			robos2.add(new Robo(x, y));
			robos2.get(i).setVida(2);
			barras2.add(new BarraVidaInimigos(x, y));
		}

		robo1 = new Robo(1800, 100);
		robo2 = new Robo(1800, 600);
		robo3 = new Robo(1800, 200);
		robo4 = new Robo(1800, 600);

		alien1 = new Alien(1800, 250);
		alien2 = new Alien(1800, 450);
		alien3 = new Alien(1800, 200);
		alien4 = new Alien(1800, 500);
		alien5 = new Alien(1800, 600);

		alien1.load();
		alien2.load();
		alien3.load();
		alien4.load();
		alien5.load();

		robo1.load();
		robo2.load();
		robo3.load();
		robo4.load();
	}

	public void inicializaPowerUps() {
		powerUps = new ArrayList<PowerUp>();

		for (int i = 0; i < 20; i++) {
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
			for (int j = 0; j < powerUps.size(); j++) {
				powerUp = powerUps.get(j);
				powerUp.load();

				graficos.drawImage(powerUp.getImagem(), powerUp.getX(), powerUp.getY(), this);
			}

			if (robo1.isVisivel()) {
				graficos.drawImage(robo1.getImagem(), robo1.getX(), robo1.getY(), this);
				if (robo1.getX() != 1000) {
					graficos.drawImage(alerta, 1450, robo1.getY(), this);
				}
			}
			if (robo2.isVisivel()) {
				graficos.drawImage(robo2.getImagem(), robo2.getX(), robo2.getY(), this);
				if (robo2.getX() != 1000) {
					graficos.drawImage(alerta, 1450, robo2.getY(), this);
				}
			}

			if (alien1.isVisivel()) {
				graficos.drawImage(alien1.getImagem(), alien1.getX(), alien1.getY(), this);
				if (alien1.getX() != 1200) {
					// muda a posição para os alien
					graficos.drawImage(alerta, 1450, alien1.getY(), this);
				}
			}
			if (alien2.isVisivel()) {
				graficos.drawImage(alien2.getImagem(), alien2.getX(), alien2.getY(), this);
				if (alien2.getX() != 1200) {
					graficos.drawImage(alerta, 1450, alien2.getY(), this);
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

		if (proximaOnda) {
			if (robo3.isVisivel()) {
				graficos.drawImage(robo3.getImagem(), robo3.getX(), robo3.getY(), this);
				if (robo3.getX() != 1000) {
					graficos.drawImage(alerta, 1450, robo3.getY(), this);
				}
			}
			if (robo4.isVisivel()) {
				graficos.drawImage(robo4.getImagem(), robo4.getX(), robo4.getY(), this);
				if (robo4.getX() != 1000) {
					graficos.drawImage(alerta, 1450, robo4.getY(), this);
				}
			}
			if (alien3.isVisivel()) {
				graficos.drawImage(alien3.getImagem(), alien3.getX(), alien3.getY(), this);
				if (alien3.getX() != 1200) {
					graficos.drawImage(alerta, 1450, alien3.getY(), this);
				}
			}
			if (alien4.isVisivel()) {
				graficos.drawImage(alien4.getImagem(), alien4.getX(), alien4.getY(), this);
				if (alien4.getX() != 1200) {
					graficos.drawImage(alerta, 1450, alien4.getY(), this);
				}
			}
			if (alien5.isVisivel()) {
				graficos.drawImage(alien5.getImagem(), alien5.getX(), alien5.getY(), this);
				if (alien5.getX() != 1200) {
					graficos.drawImage(alerta, 1450, alien5.getY(), this);
				}
			}

			if (robo3.getX() == 1000) {
				if (robo3.isVisivel()) {
					robo3.drawTiroRobo(graficos);
				}
			}
			if (robo4.getX() == 1000) {
				if (robo4.isVisivel()) {
					robo4.drawTiroRobo(graficos);
				}
			}
			if (alien3.getX() == 1200) {
				if (alien3.isVisivel()) {
					alien3.drawTiroAlien(graficos);
				}
			}
			if (alien4.getX() == 1200) {
				if (alien4.isVisivel()) {
					alien4.drawTiroAlien(graficos);
				}
			}
			if (alien5.getX() == 1200) {
				if (alien5.isVisivel()) {
					alien5.drawTiroAlien(graficos);
				}
			}

			for (int j = 0; j < robos2.size(); j++) {
				Robo robo = robos2.get(j);
				BarraVidaInimigos barraInimigos = barras2.get(j);

				robo.load2();
				graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
				barraInimigos.load(graficos, robo);
			}
		}

		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);
			BarraVidaInimigos barraInimigos = barras.get(j);

			robo.load2();
			graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
			barraInimigos.load(graficos, robo);
		}

		g.setFont(fonte);
		g.setColor(Color.WHITE);
		graficos.drawString("Fase 2", 700, 50);

		g.setFont(fonte2);
		g.setColor(Color.WHITE);

		if (doisJogadores) {
			graficos.drawString("-- Pontuações -- ", 1325, 35);
			graficos.drawString(nomeJogador1 + " = " + Jogador1.pontuacaoJogador1, 1350, 60);
			graficos.drawString(nomeJogador2 + " = " + Jogador2.pontuacaoJogador2, 1350, 90);
		} else if (doisJogadores == false) {
			graficos.drawString("-- Pontuações -- ", 1325, 35);
			graficos.drawString(nomeJogador1 + " = " + Jogador1.pontuacaoJogador1, 1350, 50);
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
			graficos.drawString("Pontuação Jogador 1 = " + Jogador1.pontuacaoJogador1, 20, 40);
			graficos.drawString("Pontuação Jogador 2 = " + Jogador2.pontuacaoJogador2, 1125, 40);
			graficos.drawString("Aperte enter para a próxima fase!", 500, 800);
		}
		if (pausado) {
			Font fonteMenu = loadFont("assets//PressStart2P.ttf", 24);
			graficos.setFont(fonteMenu);
			graficos.setColor(Color.WHITE);
			graficos.drawString("Jogo Pausado", 800, 100);

			graficos.setFont(fonte);
			graficos.setColor(Color.WHITE);
			graficos.drawString("Continuar", 850, 750);
			graficos.drawString("Reiniciar", 860, 800);
			graficos.drawString("Sair", 890, 850);
			graficos.drawString(">", 830 + (opcaoMenuPausa * 25) - 20, 750 + opcaoMenuPausa * 50);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!pausado) {
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

				for (int j = 0; j < powerUps.size(); j++) {
				powerUp = powerUps.get(j);

				if (powerUp.isVisivel()) {
					powerUp.update();
				} else {
					powerUps.remove(j);
				}
			}
			}

			if (robo1.getX() == 1000 && robo2.getX() == 1000) {
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

			if (proximaOnda) {
				alien3.updateAlien(1200);
				alien4.updateAlien(1200);
				alien5.updateAlien(1200);

				robo3.updateRoboAtirador(1000);
				robo4.updateRoboAtirador(1000);

				if (robo3.getX() == 1000 && robo4.getX() == 1000) {
					robo3.atirar();
					robo4.atirar();
				}

				if (alien3.getX() == 1200) {
					alien3.atirar();
					alien4.atirar();
					alien5.atirar();
				}

				for (int j = 0; j < robos2.size(); j++) {
					Robo robo = robos2.get(j);

					if (robo.isVisivel()) {
						robo.updateRoboDeColisao();
					} else {
						robos2.remove(j);
					}
				}
			}
			checarColisoes();
			repaint();
		}

	}

	public void checarColisoes() {
		// Colisões com Power Ups:
		fase.colisoesPowerUps(powerUps, jogador1, jogador2);

		// Colisões de Nave com Robô:
		fase.colisoesNavesRobos(robos, jogador1, jogador2);

		// Colisões de tiro da Nave com Robo:
		fase.colisoesTiroEmRobo1(robo1, jogador1, jogador2, 1000);
		fase.colisoesTiroEmRobo1(robo2, jogador1, jogador2, 1000);

		fase.colisoesTiroEmRobo2(jogador1, robos);
		fase.colisoesTiroRobo2(jogador2, robos);

		// Colisões de tiro da Nave com Alien:
		fase.colisoesTiroEmAlien(alien1, jogador1, jogador2, 1200);
		fase.colisoesTiroEmAlien(alien2, jogador1, jogador2, 1200);

		// Colisões de tiro do Robo com a Nave:
		robo1.colisaoNaveTiro(jogador1, jogador2);
		robo2.colisaoNaveTiro(jogador1, jogador2);

		// Colisões de tiro do Alien com a Nave:
		alien1.colisaoNaveTiro(jogador1);
		alien2.colisaoNaveTiro(jogador1);

		alien1.colisaoNaveTiro(jogador2);
		alien2.colisaoNaveTiro(jogador2);

		// Checa a vida das entidades:
		fase.checarRobo(robo1);
		fase.checarRobo(robo2);

		fase.checarAlien(alien1);
		fase.checarAlien(alien2);

		fase.checarRobos(robos);

		if (!robo1.isVisivel() && !robo2.isVisivel() && !alien1.isVisivel() && !alien2.isVisivel()) {
			proximaOnda = true;
		}
		if (!robo1.isVisivel() && !robo2.isVisivel() && !robo3.isVisivel() && !robo4.isVisivel() &&
				!alien1.isVisivel() && !alien2.isVisivel() && !alien3.isVisivel() && !alien4.isVisivel()
				&& !alien5.isVisivel()) {
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
			if (codigo == KeyEvent.VK_ESCAPE) {
				alternarPausa();
			}
			if (pausado) {
				switch (codigo) {
					case KeyEvent.VK_UP:
						if (opcaoMenuPausa > 0) {
							opcaoMenuPausa--;
						}
						break;
					case KeyEvent.VK_DOWN:
						if (opcaoMenuPausa < 2) {
							opcaoMenuPausa++;
						}
						break;
					case KeyEvent.VK_ENTER:
						executarAcaoMenuPausa();
						break;
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

	public void alternarPausa() {
		pausado = !pausado;
		if (pausado) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	private void executarAcaoMenuPausa() {
		switch (opcaoMenuPausa) {
			case 0:
				alternarPausa();
				break;
			case 1:
				container.reiniciarJogo();
				break;
			case 2:
				System.exit(0);
				break;
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