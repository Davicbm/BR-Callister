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
import Jogo.Componentes.Inimigos.Drakthar;
import Jogo.Componentes.Inimigos.Robo;
import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Objetos.BarraVidaJogador;
import Jogo.Componentes.Objetos.BarraVidaDrakthar;
import Jogo.Componentes.Objetos.PowerUp;
import Jogo.Componentes.Objetos.RaioLaser;

public class Fase3 extends Fase implements ActionListener {

	private Image fundo;
	private Image alerta;
	private Image portal;
	private Image portalA;
	private Image portalB;

	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private BarraVidaJogador barra;
	private BarraVidaDrakthar draktharBarra;
	private List<PowerUp> powerUps;
	private List<PowerUp> powerUps2;
	private PowerUp powerUp;

	private Timer timer;

	private List<Robo> robos;
	private List<Robo> robos2;

	private boolean vitoria;
	private boolean doisJogadores;
	private boolean gameOver;
	private boolean segundoEstagio = false;
	private boolean terceiroEstagio = false;

	private String nomeJogador1 = Menu.nomeJogador1;
	private String nomeJogador2 = Menu.nomeJogador2;

	private Alien alien1;
	private Alien alien2;

	private Drakthar drakthar;

	private Drakthar investida1;
	private Drakthar investida2;

	private Drakthar tiroTriplo1;
	private Drakthar tiroTriplo2;
	private Drakthar tiroTriplo3;

	private RaioLaser laserDrakthar;

	private int contador = 0;
	private int contador1 = 0;

	private boolean pausado = false;
	private int opcaoMenuPausa = 0;
	private int opcaoGameOver = 0;

	private Container container;

	TecladoAdapter teclado = new TecladoAdapter();
	Fase fase = new Fase(container);

	public Fase3(Container container) {
		super(container);
		this.container = container;

		setFocusable(true);
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("planosFundo//fundo03.png");
		fundo = referencia.getImage();

		referencia = new ImageIcon("assets//warninggif.gif");
		alerta = referencia.getImage();

		referencia = new ImageIcon("assets//portal.gif");
		portal = referencia.getImage();

		referencia = new ImageIcon("assets//portal-ladoA.gif");
		portalA = referencia.getImage();

		referencia = new ImageIcon("assets//portal-ladoB.gif");
		portalB = referencia.getImage();

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

		laserDrakthar = new RaioLaser();
		add(laserDrakthar);
		laserDrakthar.setVisible(false);
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

		for (int i = 0; i < 40; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 100;

			robos2.add(new Robo(x, y));
			robos2.get(i).setVida(2);
		}

		alien1 = new Alien(1800, 120);
		alien2 = new Alien(1800, 620);

		drakthar = new Drakthar(1570, 200, 2, 1800);

		investida1 = new Drakthar(1900, 105, 4, 800);
		investida2 = new Drakthar(-800, 480, 4, 800);

		tiroTriplo1 = new Drakthar(1600, 210, 1, 1800);
		tiroTriplo2 = new Drakthar(1600, 400, 1, 1800);
		tiroTriplo3 = new Drakthar(1600, 630, 1, 1800);

		alien1.load();
		alien2.load();

		drakthar.load();

		investida1.load();
		investida2.load2();
	}

	public void inicializaPowerUps() {
		powerUps = new ArrayList<PowerUp>();

		for (int i = 0; i < 20; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 100;
			int codigo = (int) (Math.random() * 3) + 1;

			powerUps.add(new PowerUp(x, y, codigo));
		}

		powerUps2 = new ArrayList<PowerUp>();

		for (int i = 0; i < 20; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 100;
			int codigo = (int) (Math.random() * 3) + 1;

			powerUps2.add(new PowerUp(x, y, codigo));
		}
	}

	public void paint(Graphics g) {
		barra = new BarraVidaJogador();
		draktharBarra = new BarraVidaDrakthar();

		Graphics2D graficos = (Graphics2D) g;

		graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);

		fase.drawComponentesIniciais(graficos, jogador1, jogador2, nomeJogador1, nomeJogador2, barra, "3");

		contador = 0;
		for (int i = 0; i < robos.size(); i++) {
			if (robos.get(i).isVisivel() == false) {
				contador += 1;
			}
		}

		if (contador == robos.size()) {
			if (contador1 == 0) {
				stopSoundBatalha();
				startSoundBoss();
				contador1++;
			}

			for (int j = 0; j < powerUps.size(); j++) {
				powerUp = powerUps.get(j);
				powerUp.load();

				graficos.drawImage(powerUp.getImagem(), powerUp.getX(), powerUp.getY(), this);
				powerUp.desenharBrilhos(graficos);
			}

			graficos.drawImage(alien1.getImagem(), alien1.getX(), alien1.getY(), this);
			if (alien1.getX() != 1000 && !alien1.isDerrotado()) {
				graficos.drawImage(alerta, 1350, alien1.getY(), this);
			}
			if (alien1.getX() == 1000 && !alien1.isDerrotado()) {
				alien1.drawTiroAlien(graficos);
			}
			if (alien1.isDerrotado()) {
				alien1.loadDerrotado();
			}

			graficos.drawImage(alien2.getImagem(), alien2.getX(), alien2.getY(), this);
			if (alien2.getX() != 1000) {
				graficos.drawImage(alerta, 1350, alien2.getY(), this);
			}
			if (alien2.getX() == 1000 && !alien2.isDerrotado()) {
				alien2.drawTiroAlien(graficos);
			}
			if (alien2.isDerrotado()) {
				alien2.loadDerrotado();
			}

			if (drakthar.isVisivel()) {
				if (drakthar.getX() == 1100) {
					draktharBarra.paintBarraVida(graficos, drakthar);
				}
				if (drakthar.getX() != 1100 && drakthar.getX() < 1700) {
					graficos.drawImage(portalB, 1301, 90, this);
				}
				graficos.drawImage(drakthar.getImagem(), drakthar.getX(), drakthar.getY(), this);
				if (drakthar.getX() != 1100 && drakthar.getX() < 1700) {
					graficos.drawImage(portalA, 1300, 90, this);
				}
			}

			if (investida1.isVisivel()) {
				graficos.drawImage(investida1.getImagem(), investida1.getX(), investida1.getY(), this);
				if (investida1.getX() > 1300 && segundoEstagio) {
					graficos.drawImage(alerta, 1400, 150, this);
				}
				graficos.drawImage(investida2.getImagem(), investida2.getX(), investida2.getY(), this);
				if (investida2.getX() < 0 && segundoEstagio && investida1.getX() <= -500) {
					graficos.drawImage(alerta, 100, 700, this);
				}
			}

			if (investida1.getY() >= 0 && investida1.getY() <= 900) {
				if (investida1.isVisivel()) {
					investida1.drawTiroDraktharBaixo(graficos);
				}
			}

			if (investida2.getY() >= -400 && investida2.getY() <= 900) {
				if (investida2.isVisivel()) {
					investida2.drawTiroDraktharCima(graficos);
				}
			}

			if (drakthar.getX() == 1100) {
				if (drakthar.isVisivel() && !segundoEstagio) {
					drakthar.drawTiroDrakthar(graficos);
				}
				if (drakthar.isVisivel() && terceiroEstagio) {
					laserDrakthar.drawRaioLaser(graficos);

					graficos.drawImage(portal, 1100, tiroTriplo1.getY() - 40, this);
					tiroTriplo1.drawTiroDrakthar(graficos, 1100);

					if (!laserDrakthar.isDisparaLaser()) {
						tiroTriplo2.drawTiroDrakthar(graficos, 1100);
					}
					graficos.drawImage(portal, 1100, tiroTriplo3.getY() - 40, this);
					tiroTriplo3.drawTiroDrakthar(graficos, 1100);
				}
			}
		}

		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);

			robo.load2(graficos);
			graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
		}

		if (terceiroEstagio) {
			for (int j = 0; j < robos2.size(); j++) {
				Robo robo = robos2.get(j);

				robo.load2(graficos);
				graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
			}

			for (int j = 0; j < powerUps2.size(); j++) {
				powerUp = powerUps2.get(j);
				powerUp.load();

				graficos.drawImage(powerUp.getImagem(), powerUp.getX(), powerUp.getY(), this);
			}
		}

		if (gameOver == true) {
			drawTelaDerrota(graficos, opcaoGameOver);
		}
		if (vitoria == true) {
			drawVitoriaJogo(graficos, nomeJogador1, nomeJogador1);
		}
		if (pausado) {
			stopSoundBatalha();
			if (contador == robos.size()) {
				stopSoundBoss();
			}
			drawTelaPausa(graficos, opcaoMenuPausa);
		}
		if (!pausado && !gameOver && !vitoria) {
			if (contador == robos.size()) {
				startSoundBoss();
			} else {
				startSoundBatalha();
			}
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!segundoEstagio) {
			jogador1.update();
		} else {
			jogador1.updateBatalhaBoss();
		}

		jogador1.atirar();

		if (doisJogadores) {
			if (!segundoEstagio) {
				jogador2.update();
			} else {
				jogador2.updateBatalhaBoss();
			}
			jogador2.atirar();
		}

		contador = 0;
		for (int i = 0; i < robos.size(); i++) {
			if (robos.get(i).isVisivel() == false) {
				contador += 1;
			}
		}

		if (contador == robos.size()) {
			alien1.updateAlien(1000);
			alien2.updateAlien(1000);

			if (alien1.isDerrotado()) {
				alien1.updateDerrota();
			}
			if (alien2.isDerrotado()) {
				alien2.updateDerrota();
			}

			if (alien1.getX() == 1000) {
				alien1.atirar();
			}

			if (alien2.getX() == 1000) {
				alien2.atirar();
			}
			if (drakthar.getVida() > 50) {
				drakthar.updateDrakthar(1100);
			}
			if (drakthar.getVida() == 50 && !terceiroEstagio) {
				drakthar.updateDrakthar2(1700);
				if (drakthar.getX() != 1100 && !alien1.isVisivel() && !alien2.isVisivel()) {
					segundoEstagio = true;
					investida1.updateDrakthar(-500);
				}
				if (investida1.getX() <= -500) {
					investida2.updateDrakthar2(1700);
					if (investida2.getX() == 1700) {
						terceiroEstagio = true;
					}
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
		}

		if (terceiroEstagio) {
			drakthar.updateDrakthar(1100);
			if (drakthar.getX() == 1100) {
				tiroTriplo1.atirar();
				tiroTriplo2.atirar();
				tiroTriplo3.atirar();

				if (!laserDrakthar.isVisible()) {
					laserDrakthar.startLaser();
					laserDrakthar.setVisible(true);
				}
				laserDrakthar.update();
			}

			if (!laserDrakthar.isVisible()) {
				laserDrakthar.stopLaser();
			}
			for (int j = 0; j < robos2.size(); j++) {
				Robo robo = robos2.get(j);

				if (robo.isVisivel()) {
					robo.updateRoboDeColisao();
				} else {
					robos2.remove(j);
				}
			}
		} else {
			laserDrakthar.setVisible(false);
		}

		if (drakthar.getX() == 1100) {
			drakthar.atirar();
		}

		if (investida1.getY() >= 0 && investida1.getY() <= 900) {
			investida1.atirarBaixo();
		}

		if (investida2.getY() > -400 && investida2.getY() < 900) {
			investida2.atirarCima();
		}

		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);

			if (robo.isVisivel()) {
				robo.updateRoboDeColisao();
			} else {
				robos.remove(j);
			}
		}
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {

		// Colisões de Nave com Robô:
		fase.colisoesPowerUps(powerUps, jogador1, jogador2);

		// Colisões de Nave com Robô:
		fase.colisoesNavesRobos(robos, jogador1, jogador2);
		fase.colisoesNavesRobos(robos2, jogador1, jogador2);

		// Colisões de tiro de Nave em Robos:
		fase.colisoesTiroEmRobo2(jogador1, jogador2, robos);
		fase.colisoesTiroEmRobo2(jogador1, jogador2, robos2);

		// Colisões de tiro de Nave em Aliens:
		fase.colisoesTiroEmAlien(alien1, jogador1, jogador2, 1000);
		fase.colisoesTiroEmAlien(alien2, jogador1, jogador2, 1000);

		// Colisões de tiro do Alien com a Nave:
		alien1.colisaoNaveTiro(jogador1, jogador2);
		alien2.colisaoNaveTiro(jogador1, jogador2);

		// Colisões de Tiro de Nave com Drakthar:
		fase.colisoesTiroEmDrakthar(drakthar, jogador1, jogador2, 1100);

		// Colisões de Nave com Drakthar:
		drakthar.colisaoNaveDrakthar(jogador1);
		drakthar.colisaoNaveDrakthar(jogador2);

		investida1.colisaoNaveDrakthar(jogador1);
		investida1.colisaoNaveDrakthar(jogador2);

		investida2.colisaoNaveDrakthar(jogador1);
		investida2.colisaoNaveDrakthar(jogador2);

		// Colisões de tiro do Drakthar com a Nave:
		if (drakthar.getX() == 1100) {
			drakthar.colisaoNaveTiro(jogador1, jogador2);
		}

		if (terceiroEstagio) {
			tiroTriplo1.colisaoNaveTiro(jogador1, jogador2);
			if (!laserDrakthar.isDisparaLaser()) {
				tiroTriplo2.colisaoNaveTiro(jogador1, jogador2);
			}
			tiroTriplo3.colisaoNaveTiro(jogador1, jogador2);
		}

		investida1.colisaoNaveTiro(jogador1, jogador2);
		investida2.colisaoNaveTiro(jogador1, jogador2);

		laserDrakthar.colisaoLaser(jogador1);
		laserDrakthar.colisaoLaser(jogador2);

		// Checagem das Entidades:
		fase.checarJogadores(jogador1, jogador2, doisJogadores);

		fase.checarAlien(alien1, 120);
		fase.checarAlien(alien2, 620);

		fase.checarRobos(robos);
		fase.checarRobos(robos2);

		fase.checarDrakthar(drakthar);

		if (!alien1.isVisivel() && !alien2.isVisivel() && !drakthar.isVisivel()) {
			vitoria = true;
			stopSoundBoss();
			startSoundVitoria();
		}

		gameOver = fase.checarJogadores(jogador1, jogador2, doisJogadores);

		if (gameOver) {
			startSoundGameOver();
			stopSoundBoss();
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
					Jogador1.pontuacaoJogador1 = 0;
					Jogador2.pontuacaoJogador2 = 0;
					container.reiniciarJogo();
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
				stopSoundBoss();
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
}
