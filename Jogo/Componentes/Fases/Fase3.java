package Jogo.Componentes.Fases;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import Jogo.Container;
import Jogo.Componentes.Inimigos.Alien;
import Jogo.Componentes.Inimigos.Drakthar;
import Jogo.Componentes.Inimigos.Robo;
import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Objetos.BarraVida;
import Jogo.Componentes.Objetos.PowerUp;
import Jogo.Componentes.Objetos.RaioLaser;

public class Fase3 extends Fase implements ActionListener {

	private Clip temaBatalha;
	private Clip musicaDerrota;
	private Clip musicaVitoria;
	private Clip musicaBoss;

	private Image fundo;
	private Image alerta;
	private Image portal;

	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private BarraVida barra;
	private List<PowerUp> powerUps;
	private PowerUp powerUp;

	private Timer timer;

	private List<Robo> robos;

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

		try {
			File audioFile = new File("assets//musica-batalha.wav");
			File audioFile2 = new File("assets//vitoria.wav");
			File audioFile3 = new File("assets//gameover.wav");
			File audioFile4 = new File("assets//DraktharTema.wav");

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
			AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(audioFile3);
			AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(audioFile4);

			temaBatalha = AudioSystem.getClip();
			temaBatalha.open(audioStream);

			musicaVitoria = AudioSystem.getClip();
			musicaVitoria.open(audioStream2);

			musicaDerrota = AudioSystem.getClip();
			musicaDerrota.open(audioStream3);

			musicaBoss = AudioSystem.getClip();
			musicaBoss.open(audioStream4);

		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		startSound1(temaBatalha);
	}

	public void startSound1(Clip clip) {
		if (clip != null) {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void startSound2(Clip clip) {
		if (clip != null) {
			clip.start();
		}
	}

	public void stopSound(Clip clip) {
		if (clip != null) {
			clip.stop();
		}
	}

	public void inicializaInimigos() {

		robos = new ArrayList<Robo>();

		for (int i = 0; i < 0; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 10;

			robos.add(new Robo(x, y));
			robos.get(i).setVida(2);
		}

		alien1 = new Alien(1800, 100);
		alien2 = new Alien(1800, 600);

		drakthar = new Drakthar(1570, 200, 2);

		investida1 = new Drakthar(600, -500, 3);
		investida2 = new Drakthar(270, 1000, 3);

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
			int y = (int) (Math.random() * 650) + 10;
			int codigo = (int) (Math.random() * 3) + 1;

			powerUps.add(new PowerUp(x, y, codigo));
		}
	}

	public void paint(Graphics g) {
		barra = new BarraVida();
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
				stopSound(temaBatalha);
				startSound1(musicaBoss);
				contador1++;
			}

			for (int j = 0; j < powerUps.size(); j++) {
				powerUp = powerUps.get(j);
				powerUp.load();

				graficos.drawImage(powerUp.getImagem(), powerUp.getX(), powerUp.getY(), this);
			}

			if (alien1.isVisivel()) {
				graficos.drawImage(alien1.getImagem(), alien1.getX(), alien1.getY(), this);
				if (alien1.getX() != 1000) {
					// muda a posição para os alien
					graficos.drawImage(alerta, 1450, alien1.getY(), this);
				}
			}
			if (alien2.isVisivel()) {
				graficos.drawImage(alien2.getImagem(), alien2.getX(), alien2.getY(), this);
				if (alien2.getX() != 1000) {
					graficos.drawImage(alerta, 1450, alien2.getY(), this);
				}
			}
			if (drakthar.isVisivel()) {
				graficos.drawImage(drakthar.getImagem(), drakthar.getX(), drakthar.getY(), this);
				if (drakthar.getX() != 1100 && drakthar.getX() < 1700) {
					graficos.drawImage(portal, 1300, 200 - 55, this);
					
				}
			}

			if (investida1.isVisivel()) {
				graficos.drawImage(investida1.getImagem(), investida1.getX(), investida1.getY(), this);
				if (investida1.getY() < 0 && drakthar.getX() >= 1750) {
					graficos.drawImage(alerta, investida1.getX() + 100, 0, this);
				}
				graficos.drawImage(investida2.getImagem(), investida2.getX(), investida2.getY(), this);
				if (investida2.getY() > 900 && drakthar.getX() >= 1750) {
					graficos.drawImage(alerta, investida2.getX() - 100, 800, this);
				}
			}

			if (investida1.getY() >= 0 && investida1.getY() <= 900) {
				if (investida1.isVisivel()) {
					investida1.drawTiroDrakthar(graficos);
				}
			}

			if (investida2.getY() >= -400 && investida2.getY() <= 900) {
				if (investida2.isVisivel()) {
					investida2.drawTiroDraktharFlip(graficos);
				}
			}

			if (alien1.getX() == 1000) {
				if (alien1.isVisivel()) {
					alien1.drawTiroAlien(graficos);
				}
			}
			if (alien2.getX() == 1000) {
				if (alien2.isVisivel()) {
					alien2.drawTiroAlien(graficos);
				}
			}
			if (drakthar.getX() == 1100){
				if (drakthar.isVisivel() && !terceiroEstagio) {
					drakthar.drawTiroDrakthar(graficos);
				}
				if (drakthar.isVisivel() && terceiroEstagio){
					laserDrakthar.drawRaioLaser(graficos);
				}	
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
			drawTelaPausa(graficos, contador);
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
			alien1.updateAlien(1000);
			alien2.updateAlien(1000);

			if (drakthar.getVida() > 25) {
				drakthar.updateDrakthar(1100);
			}
			if (drakthar.getVida() == 25 && !terceiroEstagio) {
				drakthar.updateDrakthar2(1750);
				segundoEstagio = true;
				if (drakthar.getX() == 1750) {
					investida1.updateInvestidasBaixo(900);
				}
				if (investida1.getY() >= 900) {
					investida2.updateInvestidasCima(-400);
					if (investida2.getY() == - 400){
						terceiroEstagio = true;
					}
				}
			}
			if (terceiroEstagio){
				drakthar.updateDrakthar(1100);
			
				if (!laserDrakthar.isVisible()) {
					laserDrakthar.startLaser();
					laserDrakthar.setVisible(true);
				}
				// Verifica o tempo do laser do boss e para quando necessário
				laserDrakthar.update();
				if (!laserDrakthar.isVisible()) {
					laserDrakthar.stopLaser();
				}
			} else {
				// Se não estiver no terceiro estágio, esconda o laser
				laserDrakthar.setVisible(false);
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

		if (alien1.getX() == 1000) {
			alien1.atirar();
		}

		if (alien2.getX() == 1000) {
			alien2.atirar();
		}

		if (drakthar.getX() == 1100) {
			drakthar.atirar();
		}

		if (segundoEstagio){
			drakthar.removerTiros();
		}

		if (investida1.getY() >= 0 && investida1.getY() <= 900) {
			investida1.atirar();
		}

		if (investida2.getY() > -400 && investida2.getY() < 900) {
			investida2.atirarFlip();
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

		// Colisões de tiro de Nave em Robos:
		fase.colisoesTiroEmRobo2(jogador1, jogador2, robos);

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
		drakthar.colisaoNaveTiro(jogador1, jogador2);
		
		investida1.colisaoNaveTiro(jogador1, jogador2);
		investida2.colisaoNaveTiro(jogador1, jogador2);

		laserDrakthar.colisaoLaser(jogador1);

		// Checagem das Entidades:
		fase.checarJogadores(jogador1, jogador2, doisJogadores);

		fase.checarAlien(alien1);
		fase.checarAlien(alien2);

		fase.checarRobos(robos);

		fase.checarDrakthar(drakthar);

		if (!alien1.isVisivel() && !alien2.isVisivel() && !drakthar.isVisivel()) {
			vitoria = true;
			stopSound(musicaBoss);
			startSound2(musicaVitoria);
		}

		gameOver = fase.checarJogadores(jogador1, jogador2, doisJogadores);

		if (gameOver) {
			startSound2(musicaDerrota);
			stopSound(musicaBoss);
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
				container.reiniciarFase();
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
