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

public class Fase3 extends Fase implements ActionListener {

	private Clip temaBatalha;
	private Clip musicaDerrota;
	private Clip musicaVitoria;

	private Image fundo;
	private Image alerta;

	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private BarraVida barra;
	private List<PowerUp> powerUps;
	private Timer timer;

	private List<Robo> robos;

	private boolean proximaFase;
	private boolean vitoria;
	private boolean doisJogadores;
	private boolean gameOver;

	private String nomeJogador1 = Menu.nomeJogador1;
	private String nomeJogador2 = Menu.nomeJogador2;

	private Alien alien1;
	private Alien alien2;
	private Drakthar drakthar;

	private int contador = 0;

	private boolean pausado = false;
	private int opcaoMenuPausa = 0;

	private Container container;

	TecladoAdapter teclado = new TecladoAdapter();
	Fase fase = new Fase(container);

	public Fase3(Container container) {
		super(container);
		this.container = container;

		setFocusable(true);
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("assets//fundo03.png");
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

		if (Menu.doisJogadores) {
			doisJogadores = true;
		} else {
			doisJogadores = false;
		}

		proximaFase = false;
		vitoria = false;
		gameOver = false;

		this.requestFocusInWindow();

		try {
			File audioFile = new File("assets//musica-batalha.wav");
			File audioFile2 = new File("assets//vitoria.wav");
			File audioFile3 = new File("assets//gameover.wav");

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
			AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(audioFile3);

			temaBatalha = AudioSystem.getClip();
			temaBatalha.open(audioStream);

			musicaVitoria = AudioSystem.getClip();
			musicaVitoria.open(audioStream2);

			musicaDerrota = AudioSystem.getClip();
			musicaDerrota.open(audioStream3);

		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		startSound();
	}

	public void startSound() {
		if (temaBatalha != null) {
			temaBatalha.start();
			temaBatalha.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void startSound(Clip clip) {
		if (clip != null) {
			clip.start();
		}
	}

	public void stopSound() {
		if (temaBatalha != null) {
			temaBatalha.stop();
		}
	}

	public void inicializaInimigos() {

		robos = new ArrayList<Robo>();

		for (int i = 0; i < 40; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 10;
			robos.add(new Robo(x, y));
			robos.get(i).setVida(1);
		}

		alien1 = new Alien(1800, 100);
		alien2 = new Alien(1800, 600);

		drakthar = new Drakthar(1800, 200);

		alien1.load();
		alien2.load();

		drakthar.load();
	}

	public void inicializaPowerUps() {
		powerUps = new ArrayList<PowerUp>();

		for (int i = 0; i < 20; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 10;
			int codigo = (int) (Math.random() * 4) + 1;

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
			if (alien1.isVisivel()) {
				graficos.drawImage(alien1.getImagem(), alien1.getX(), alien1.getY(), this);
				if (alien1.getX() != 1000) {
					// muda a posição para os alien
					graficos.drawImage(alerta, 1450, 100, this);
				}
			}
			if (alien2.isVisivel()) {
				graficos.drawImage(alien2.getImagem(), alien2.getX(), alien2.getY(), this);
				if (alien2.getX() != 1000) {
					graficos.drawImage(alerta, 1450, 500, this);
				}
			}
			if (drakthar.isVisivel()) {
				graficos.drawImage(drakthar.getImagem(), drakthar.getX(), drakthar.getY(), this);
				if (drakthar.getX() != 1100) {
					graficos.drawImage(alerta, 1450, 300, this);
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
			if (drakthar.getX() == 1100) {
				if (drakthar.isVisivel()) {
					drakthar.drawTiroDrakthar(graficos);
				}
			}
		}
		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);
			robo.load2();
			graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
		}

		if (gameOver == true) {
			drawTelaDerrota(graficos);
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

			drakthar.updateDrakthar(1100);
		}

		if (alien1.getX() == 1000) {
			alien1.atirar();
			alien2.atirar();
		}

		if (drakthar.getX() == 1100) {
			drakthar.atirar();
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

		// Colisões de tiro de Nave em Aliens:
		fase.colisoesTiroEmAlien(alien1, jogador1, jogador2, 1100);

		// Colisões de tiro do Alien com a Nave:
		alien1.colisaoNaveTiro(jogador1);
		alien2.colisaoNaveTiro(jogador1);
		if (doisJogadores) {
			alien1.colisaoNaveTiro(jogador2);
			alien2.colisaoNaveTiro(jogador2);
		}

		// Checagem das Entidades:
		checarJogadores(jogador1, jogador2, doisJogadores);

		checarAlien(alien1);
		checarAlien(alien2);

		checarRobos(robos);

		if (!alien1.isVisivel() && !alien2.isVisivel() && !drakthar.isVisivel()){
			stopSound();
			startSound(musicaVitoria);
		}

		gameOver = fase.checarJogadores(jogador1, jogador2, doisJogadores);

		if (gameOver) {
			stopSound();
			startSound(musicaDerrota);
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
				if (codigo == KeyEvent.VK_ENTER) {
					container.reiniciarFase();
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

	public boolean isProximaFase() {
		return this.proximaFase;
	}

	public void setProximaFase(boolean proximaFase) {
		this.proximaFase = proximaFase;
	}

}
