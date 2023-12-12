package Jogo.Componentes.Fases;

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
import Jogo.Componentes.Objetos.BarraVidaJogador;
import Jogo.Componentes.Objetos.PowerUp;

public class Fase2 extends Fase implements ActionListener {

	public static boolean faseCompleta2 = false;
	private Image fundo;
	private Image alerta;

	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private BarraVidaJogador barra;
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
	private int opcaoGameOver = 0;

	private Container container;
	TecladoAdapter teclado = new TecladoAdapter();

	public Fase2(Container container) {
		super(container);
		this.container = container;

		setFocusable(true);
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("planosFundo//fase02.png");
		fundo = referencia.getImage();

		referencia = new ImageIcon("assets//warninggif.gif");
		alerta = referencia.getImage();

		jogador1 = new Jogador1();
		jogador2 = new Jogador2();

		jogador1.load();
		jogador2.load();

		addKeyListener(new TecladoAdapter());

		Jogador1.pontuacaoAnteriorJogador1 = Jogador1.pontuacaoJogador1;
		Jogador2.pontuacaoAnteriorJogador2 = Jogador2.pontuacaoJogador2;

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

		startSoundBatalha();
	}

	public void inicializaInimigos() {

		robos = new ArrayList<Robo>();

		for (int i = 0; i < 30; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 100;

			robos.add(new Robo(x, y));
			robos.get(i).setVida(2);
		}

		robos2 = new ArrayList<Robo>();

		for (int i = 0; i < 30; i++) {
			int x = (int) (Math.random() * 6000) + 1980;
			int y = (int) (Math.random() * 650) + 100;

			robos2.add(new Robo(x, y));
			robos2.get(i).setVida(2);
		}

		robo1 = new Robo(1800, 130);
		robo2 = new Robo(1800, 620);

		robo3 = new Robo(1800, 130);
		robo4 = new Robo(1800, 640);

		alien1 = new Alien(1800, 270);
		alien2 = new Alien(1800, 470);

		alien3 = new Alien(1800, 270);
		alien4 = new Alien(1800, 400);
		alien5 = new Alien(1800, 495);

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
			int y = (int) (Math.random() * 650) + 100;
			int codigo = (int) (Math.random() * 3) + 1;

			powerUps.add(new PowerUp(x, y, codigo));
		}
	}

	public void paint(Graphics g) {
		barra = new BarraVidaJogador();
		Graphics2D graficos = (Graphics2D) g;

		graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);

		drawComponentesIniciais(graficos, jogador1, jogador2, nomeJogador1, nomeJogador2, barra, "2");

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
				powerUp.desenharBrilhos(graficos);
			}

			graficos.drawImage(robo1.getImagem(), robo1.getX(), robo1.getY(), this);
			if (robo1.getX() != 1000 && !robo1.isDerrotado()) {
				graficos.drawImage(alerta, 1450, robo1.getY(), this);
			}
			if (robo1.getX() == 1000 && !robo1.isDerrotado()) {
				robo1.drawTiroRobo(graficos);
			}
			if (robo1.isDerrotado()) {
				robo1.loadDerrotado();
			}

			graficos.drawImage(robo2.getImagem(), robo2.getX(), robo2.getY(), this);
			if (robo2.getX() != 1000 && !robo2.isDerrotado()) {
				graficos.drawImage(alerta, 1450, robo2.getY(), this);
			}
			if (robo2.getX() == 1000 && !robo2.isDerrotado()) {
				robo2.drawTiroRobo(graficos);
			}
			if (robo2.isDerrotado()) {
				robo2.loadDerrotado();
			}

			graficos.drawImage(alien1.getImagem(), alien1.getX(), alien1.getY(), this);
			if (alien1.getX() != 1200 && !alien1.isDerrotado()) {
				graficos.drawImage(alerta, 1450, alien1.getY(), this);
			}
			if (alien1.getX() == 1200 && !alien1.isDerrotado()) {
				alien1.drawTiroAlien(graficos);
			}
			if (alien1.isDerrotado()) {
				alien1.loadDerrotado();
			}

			graficos.drawImage(alien2.getImagem(), alien2.getX(), alien2.getY(), this);
			if (alien2.getX() != 1200 && !alien2.isDerrotado()) {
				graficos.drawImage(alerta, 1450, alien2.getY(), this);
			}
			if (alien1.getX() == 1200 && !alien2.isDerrotado()) {
				alien2.drawTiroAlien(graficos);
			}
			if (alien2.isDerrotado()) {
				alien2.loadDerrotado();
			}
		}

		if (proximaOnda) {
			graficos.drawImage(robo3.getImagem(), robo3.getX(), robo3.getY(), this);
			if (robo3.getX() != 1000 && !robo3.isDerrotado()) {
				graficos.drawImage(alerta, 1450, robo3.getY(), this);
			}
			if (robo3.getX() == 1000 && !robo3.isDerrotado()) {
				robo3.drawTiroRobo(graficos);
			}
			if (robo3.isDerrotado()) {
				robo3.loadDerrotado();
			}

			graficos.drawImage(robo4.getImagem(), robo4.getX(), robo4.getY(), this);
			if (robo4.getX() != 1000 && !robo4.isDerrotado()) {
				graficos.drawImage(alerta, 1450, robo4.getY(), this);
			}
			if (robo4.getX() == 1000 && !robo4.isDerrotado()) {
				robo4.drawTiroRobo(graficos);
			}
			if (robo4.isDerrotado()) {
				robo4.loadDerrotado();
			}

			graficos.drawImage(alien3.getImagem(), alien3.getX(), alien3.getY(), this);
			if (alien3.getX() != 1200 && !alien3.isDerrotado()) {
				graficos.drawImage(alerta, 1450, alien3.getY(), this);
			}
			if (alien3.getX() == 1200 && !alien3.isDerrotado()) {
				alien3.drawTiroAlien(graficos);
			}
			if (alien3.isDerrotado()) {
				alien3.loadDerrotado();
			}

			graficos.drawImage(alien4.getImagem(), alien4.getX(), alien4.getY(), this);
			if (alien4.getX() != 1200 && !alien4.isDerrotado()) {
				graficos.drawImage(alerta, 1450, alien4.getY(), this);
			}
			if (alien4.getX() == 1200 && !alien4.isDerrotado()) {
				alien4.drawTiroAlien(graficos);
			}
			if (alien4.isDerrotado()) {
				alien4.loadDerrotado();
			}

			graficos.drawImage(alien5.getImagem(), alien5.getX(), alien5.getY(), this);
			if (alien5.getX() != 1200 && !alien5.isDerrotado()) {
				graficos.drawImage(alerta, 1450, alien5.getY(), this);
			}
			if (alien5.getX() == 1200 && !alien5.isDerrotado()) {
				alien5.drawTiroAlien(graficos);
			}
			if (alien5.isDerrotado()) {
				alien5.loadDerrotado();
			}

			for (int j = 0; j < robos2.size(); j++) {
				Robo robo = robos2.get(j);

				robo.load2(graficos);
				graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
			}
		}

		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);

			robo.load2(graficos);
			graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
		}

		if (gameOver == true) {
			drawTelaDerrota(graficos, opcaoGameOver);
		}

		if (vitoria == true) {
			drawTelaVitoria(graficos, nomeJogador1, nomeJogador1);
		}
		if (pausado) {
			stopSoundBatalha();
			drawTelaPausa(graficos, opcaoMenuPausa);
		}
		if (!pausado && !gameOver && !vitoria) {
			startSoundBatalha();
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

				if (robo1.isDerrotado()) {
					robo1.updateDerrota();
				}
				if (robo2.isDerrotado()) {
					robo2.updateDerrota();
				}

				alien1.updateAlien(1200);
				alien2.updateAlien(1200);

				if (alien1.isDerrotado()) {
					alien1.updateDerrota();
				}
				if (alien2.isDerrotado()) {
					alien2.updateDerrota();
				}

				for (int j = 0; j < powerUps.size(); j++) {
					powerUp = powerUps.get(j);

					if (powerUp.isVisivel()) {
						powerUp.update();
					} else {
						powerUps.remove(j);
					}
				}
			}

			if (robo1.getX() == 1000) {
				robo1.atirar();
			}
			if (robo2.getX() == 1000) {
				robo2.atirar();
			}

			if (alien1.getX() == 1200) {
				alien1.atirar();
			}

			if (alien2.getX() == 1200) {
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

				if (alien3.isDerrotado()) {
					alien3.updateDerrota();
				}
				if (alien4.isDerrotado()) {
					alien4.updateDerrota();
				}
				if (alien5.isDerrotado()) {
					alien5.updateDerrota();
				}

				robo3.updateRoboAtirador(1000);
				robo4.updateRoboAtirador(1000);

				if (robo3.isDerrotado()) {
					robo3.updateDerrota();
				}
				if (robo4.isDerrotado()) {
					robo4.updateDerrota();
				}

				if (robo3.getX() == 1000) {
					robo3.atirar();
				}
				if (robo4.getX() == 1000) {
					robo4.atirar();
				}

				if (alien3.getX() == 1200) {
					alien3.atirar();
				}
				if (alien4.getX() == 1200) {
					alien4.atirar();
				}
				if (alien5.getX() == 1200) {
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
		colisoesPowerUps(powerUps, jogador1, jogador2);

		// Colisões de Nave com Robô:
		colisoesNavesRobos(robos, jogador1, jogador2);
		colisoesNavesRobos(robos2, jogador1, jogador2);

		// Colisões de tiro da Nave com Robo:
		colisoesTiroEmRobo1(robo1, jogador1, jogador2, 1000);
		colisoesTiroEmRobo1(robo2, jogador1, jogador2, 1000);

		colisoesTiroEmRobo1(robo3, jogador1, jogador2, 1000);
		colisoesTiroEmRobo1(robo4, jogador1, jogador2, 1000);

		colisoesTiroEmRobo2(jogador1, jogador2, robos);
		colisoesTiroEmRobo2(jogador1, jogador2, robos2);

		// Colisões de tiro da Nave com Alien:
		colisoesTiroEmAlien(alien1, jogador1, jogador2, 1200);
		colisoesTiroEmAlien(alien2, jogador1, jogador2, 1200);

		colisoesTiroEmAlien(alien3, jogador1, jogador2, 1200);
		colisoesTiroEmAlien(alien4, jogador1, jogador2, 1200);
		colisoesTiroEmAlien(alien5, jogador1, jogador2, 1200);

		// Colisões de tiro do Robo com a Nave:
		robo1.colisaoNaveTiro(jogador1, jogador2);
		robo2.colisaoNaveTiro(jogador1, jogador2);

		robo3.colisaoNaveTiro(jogador1, jogador2);
		robo4.colisaoNaveTiro(jogador1, jogador2);

		// Colisões de tiro do Alien com a Nave:
		alien1.colisaoNaveTiro(jogador1, jogador2);
		alien2.colisaoNaveTiro(jogador1, jogador2);

		alien3.colisaoNaveTiro(jogador1, jogador2);
		alien4.colisaoNaveTiro(jogador1, jogador2);
		alien5.colisaoNaveTiro(jogador1, jogador2);

		// Checa a vida das entidades:
		checarRobo(robo1, 130);
		checarRobo(robo2, 620);
		checarRobo(robo3, 130);
		checarRobo(robo4, 640);

		checarAlien(alien1, 270);
		checarAlien(alien2, 470);
		checarAlien(alien3, 270);
		checarAlien(alien4, 400);
		checarAlien(alien5, 495);

		checarRobos(robos);
		checarRobos(robos2);

		if (!robo1.isVisivel() && !robo2.isVisivel() && !alien1.isVisivel() && !alien2.isVisivel()) {
			proximaOnda = true;
		}
		if (!robo1.isVisivel() && !robo2.isVisivel() && !robo3.isVisivel() && !robo4.isVisivel()
				&& !alien1.isVisivel() && !alien2.isVisivel() && !alien3.isVisivel() && !alien4.isVisivel()
				&& !alien5.isVisivel()) {
			vitoria = true;
			stopSoundBatalha();
			startSoundVitoria();
			Fase2.faseCompleta2 = true;
		}

		gameOver = checarJogadores(jogador1, jogador2, doisJogadores);

		if (gameOver) {
			stopSoundBatalha();
			startSoundGameOver();
		}
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
				switch (codigo) {
					case KeyEvent.VK_UP:
						if (opcaoGameOver > 0) {
							opcaoGameOver--;
						}
						break;
					case KeyEvent.VK_DOWN:
						if (opcaoGameOver < 1) {
							opcaoGameOver++;
						}
						break;
					case KeyEvent.VK_ENTER:
						executarAcaoGameOver();
						break;
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
				container.voltarMenuPrincipal();
				Jogador1.pontuacaoJogador1 = 0;
				Jogador2.pontuacaoJogador2 = 0;
				break;
			case 2:
				System.exit(0);
				break;
		}
	}

	private void executarAcaoGameOver() {
		switch (opcaoGameOver) {
			case 0:
				Jogador1.pontuacaoJogador1 = Jogador1.pontuacaoAnteriorJogador1;
				Jogador2.pontuacaoJogador2 = Jogador2.pontuacaoAnteriorJogador2;
				container.reiniciarFase();
				break;
			case 1:
				Jogador1.pontuacaoJogador1 = 0;
				Jogador2.pontuacaoJogador2 = 0;
				container.reiniciarJogo();
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