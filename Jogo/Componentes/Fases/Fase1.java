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
import Jogo.Componentes.Inimigos.Robo;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Objetos.BarraVida;
import Jogo.Componentes.Objetos.BarraVidaInimigos;
import Jogo.Componentes.Objetos.PowerUp;

public class Fase1 extends Fase implements ActionListener {

	private Clip temaBatalha;
	private Clip musicaDerrota;
	private Clip musicaVitoria;

	private boolean doisJogadores;
	private String nomeJogador1 = Menu.nomeJogador1;
	private String nomeJogador2 = Menu.nomeJogador2;

	private Image fundo;
	private Image alerta;

	private Jogador1 jogador1;
	private Jogador2 jogador2;

	private BarraVida barra;
	private List<BarraVidaInimigos> barras;
	private List<BarraVidaInimigos> barras2;
	private PowerUp powerUp;
	private List<PowerUp> powerUps;

	private Timer timer;

	private Robo robo1;
	private Robo robo2;
	private Robo robo3;
	private Robo robo4;
	private Robo robo5;
	private Robo robo6;
	private Robo robo7;
	private Alien alien;
	private List<Robo> robos;
	private List<Robo> robos2;

	private boolean emJogo;
	private boolean vitoria;
	private boolean gameOver;
	private boolean proximaOndaAtiradores = false;
	private boolean proximaOndaColisoes = false;
	private boolean proximaFase = false;

	private boolean pausado = false;
	private int opcaoMenuPausa = 0;
	private int contador = 0;
	TecladoAdapter teclado = new TecladoAdapter();

	private Container container;
	Fase fase = new Fase(container);

	public Fase1(Container container) {
		super(container);
		this.container = container;

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("assets//fase01.png");
		fundo = referencia.getImage();

		referencia = new ImageIcon("assets//warninggif.gif");
		alerta = referencia.getImage();

		jogador1 = new Jogador1();
		jogador2 = new Jogador2();

		jogador1.load();
		jogador2.load();

		inicializaInimigos();
		inicializaPowerUps();

		addKeyListener(teclado);

		timer = new Timer(5, this);
		timer.start();

		if (Menu.doisJogadores) {
			doisJogadores = true;
		} else {
			doisJogadores = false;
		}

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
		barras = new ArrayList<BarraVidaInimigos>();

		for (int i = 0; i < 0; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 10;

			robos.add(new Robo(x, y));
			barras.add(new BarraVidaInimigos(x, y));
			robos.get(i).setVida(2);
		}

		robos2 = new ArrayList<Robo>();
		barras2 = new ArrayList<BarraVidaInimigos>();

		for (int i = 0; i < 0; i++) {
			int x = (int) (Math.random() * 6000) + 1980;
			int y = (int) (Math.random() * 650) + 10;

			robos2.add(new Robo(x, y));
			barras2.add(new BarraVidaInimigos(x, y));
			robos2.get(i).setVida(2);
		}

		robo1 = new Robo(2000, 90);
		robo2 = new Robo(2000, 350);
		robo3 = new Robo(2000, 650);
		robo4 = new Robo(2200, 230);
		robo5 = new Robo(2200, 470);
		robo6 = new Robo(2000, 100);
		robo7 = new Robo(2000, 600);

		alien = new Alien(2000, 325);

		robo1.load();
		robo2.load();
		robo3.load();
		robo4.load();
		robo5.load();
		robo6.load();
		robo7.load();

		alien.load();
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

		Graphics2D graficos = (Graphics2D) g;
		barra = new BarraVida();

		graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);

		fase.drawComponentesIniciais(graficos, jogador1, jogador2, nomeJogador1, nomeJogador1, barra, "1");

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
				if (robo1.getX() != 1100) {
					graficos.drawImage(alerta, 1450, 90, this);
				}
			}
			if (robo2.isVisivel()) {
				graficos.drawImage(robo2.getImagem(), robo2.getX(), robo2.getY(), this);
				if (robo2.getX() != 1000) {
					graficos.drawImage(alerta, 1450, 350, this);
				}
			}
			if (robo3.isVisivel()) {
				graficos.drawImage(robo3.getImagem(), robo3.getX(), robo3.getY(), this);
				if (robo3.getX() != 1100) {
					graficos.drawImage(alerta, 1450, 650, this);
				}
			}
			if (robo4.isVisivel()) {
				graficos.drawImage(robo4.getImagem(), robo4.getX(), robo4.getY(), this);
				if (robo4.getX() != 1200) {
					graficos.drawImage(alerta, 1450, 230, this);
				}
			}
			if (robo5.isVisivel()) {
				graficos.drawImage(robo5.getImagem(), robo5.getX(), robo5.getY(), this);
				if (robo5.getX() != 1200) {
					graficos.drawImage(alerta, 1450, 470, this);
				}
			}

			if (robo1.getX() == 1100) {
				if (robo1.isVisivel()) {
					robo1.drawTiroRobo(graficos);
				}
			}
			if (robo2.getX() == 1000) {
				if (robo2.isVisivel()) {
					robo2.drawTiroRobo(graficos);
				}
			}
			if (robo3.getX() == 1100) {
				if (robo3.isVisivel()) {
					robo3.drawTiroRobo(graficos);
				}
			}
			if (robo4.getX() == 1200) {
				if (robo4.isVisivel()) {
					robo4.drawTiroRobo(graficos);
				}
			}
			if (robo5.getX() == 1200) {
				if (robo5.isVisivel()) {
					robo5.drawTiroRobo(graficos);
				}
			}
		}

		if (proximaOndaAtiradores) {
			if (robo6.isVisivel()) {
				graficos.drawImage(robo6.getImagem(), robo6.getX(), robo6.getY(), this);
				if (robo6.getX() != 1300) {
					graficos.drawImage(alerta, 1450, 100, this);
				}
			}

			if (robo7.isVisivel()) {
				graficos.drawImage(robo7.getImagem(), robo7.getX(), robo7.getY(), this);
				if (robo7.getX() != 1300) {
					graficos.drawImage(alerta, 1450, 600, this);
				}
			}

			if (alien.isVisivel()) {
				graficos.drawImage(alien.getImagem(), alien.getX(), alien.getY(), this);
				if (alien.getX() != 1100) {
					graficos.drawImage(alerta, 1450, 325, this);
				}
			}

			if (robo6.getX() == 1300) {
				if (robo6.isVisivel()) {
					robo6.drawTiroRobo(graficos);
				}
			}
			if (robo7.getX() == 1300) {
				if (robo7.isVisivel()) {
					robo7.drawTiroRobo(graficos);
				}
			}
			if (alien.getX() == 1100) {
				if (alien.isVisivel()) {
					alien.drawTiroAlien(graficos);
				}
			}
		}

		if (proximaOndaColisoes) {
			for (int j = 0; j < robos2.size(); j++) {
				Robo robo = robos2.get(j);
				BarraVidaInimigos barraInimigos = barras2.get(j);

				robo.load2();
				barraInimigos.load(graficos, robo);
				graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
			}
		}

		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);
			BarraVidaInimigos barraInimigos = barras.get(j);

			robo.load2();
			barraInimigos.load(graficos, robo);
			graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
		}

		if (gameOver) {
			drawTelaDerrota(graficos);
		}

		if (vitoria) {
			drawTelaVitoria(graficos, nomeJogador1, nomeJogador2);
		}
		if (pausado) { 
			drawTelaPausa(graficos, opcaoMenuPausa);
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		jogador1.update();
		jogador2.update();

		jogador1.atirar();
		jogador2.atirar();

		contador = 0;
		for (int i = 0; i < robos.size(); i++) {
			if (robos.get(i).isVisivel() == false) {
				contador += 1;
			}
		}
		if (contador == robos.size()) {
			robo1.updateRoboAtirador(1100);
			robo2.updateRoboAtirador(1000);
			robo3.updateRoboAtirador(1100);
			robo4.updateRoboAtirador(1200);
			robo5.updateRoboAtirador(1200);
		}
		if (proximaOndaAtiradores) {
			robo6.updateRoboAtirador(1300);
			robo7.updateRoboAtirador(1300);
			alien.updateAlien(1100);
		}
		if (robo1.getX() == 1100) {
			robo1.atirar();
		}
		if (robo2.getX() == 1000) {
			robo2.atirar();
		}
		if (robo3.getX() == 1100) {
			robo3.atirar();
		}
		if (robo4.getX() == 1200) {
			robo4.atirar();
		}
		if (robo5.getX() == 1200) {
			robo5.atirar();
		}
		if (robo6.getX() == 1300) {
			robo6.atirar();
		}
		if (robo7.getX() == 1300) {
			robo7.atirar();
		}
		if (alien.getX() == 1100) {
			alien.atirar();
		}

		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);
			BarraVidaInimigos barraInimigo = barras.get(j);

			if (robo.isVisivel()) {
				robo.updateRoboDeColisao();
				barraInimigo.updateBarraInimigo();
			} else {
				robos.remove(j);
				barras.remove(j);
			}
		}
		if (proximaOndaColisoes) {
			for (int j = 0; j < robos2.size(); j++) {
				Robo robo = robos2.get(j);
				BarraVidaInimigos barraInimigo = barras2.get(j);

				if (robo.isVisivel()) {
					robo.updateRoboDeColisao();
					barraInimigo.updateBarraInimigo();
				} else {
					robos2.remove(j);
					barras2.remove(j);
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
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		// Colisões com Power Ups:
		fase.colisoesPowerUps(powerUps, jogador1, jogador2);

		// Colisões de Nave com Robô:
		fase.colisoesNavesRobos(robos, jogador1, jogador2);
		fase.colisoesNavesRobos(robos2, jogador1, jogador2);

		// Colisões de tiro da Nave com Robo:
		fase.colisoesTiroEmRobo1(robo1, jogador1, jogador2, 1100);
		fase.colisoesTiroEmRobo1(robo2, jogador1, jogador2, 1000);
		fase.colisoesTiroEmRobo1(robo3, jogador1, jogador2, 1100);
		fase.colisoesTiroEmRobo1(robo4, jogador1, jogador2, 1200);
		fase.colisoesTiroEmRobo1(robo5, jogador1, jogador2, 1200);
		fase.colisoesTiroEmRobo1(robo6, jogador1, jogador2, 1300);
		fase.colisoesTiroEmRobo1(robo7, jogador1, jogador2, 1300);

		// Colisões de tiro da Nave com Alien:
		fase.colisoesTiroEmAlien(alien, jogador1, jogador2, 1100);

		// Colisões de tiro da Nave com Robo de colisão:
		fase.colisoesTiroEmRobo2(jogador1, jogador2, robos);

		fase.colisoesTiroEmRobo2(jogador1, jogador2, robos2);

		// Colisões de tiro do Robo com a Nave:
		robo1.colisaoNaveTiro(jogador1, jogador2);
		robo2.colisaoNaveTiro(jogador1, jogador2);
		robo3.colisaoNaveTiro(jogador1, jogador2);
		robo4.colisaoNaveTiro(jogador1, jogador2);
		robo5.colisaoNaveTiro(jogador1, jogador2);
		robo6.colisaoNaveTiro(jogador1, jogador2);
		robo7.colisaoNaveTiro(jogador1, jogador2);

		// Colisões de tiro do Alien com a Nave:
		alien.colisaoNaveTiro(jogador1, jogador2);

		// Checar vida dos robôs:
		fase.checarRobo(robo1);
		fase.checarRobo(robo2);
		fase.checarRobo(robo3);
		fase.checarRobo(robo4);
		fase.checarRobo(robo5);
		fase.checarRobo(robo6);
		fase.checarRobo(robo7);
		fase.checarAlien(alien);

		fase.checarRobos(robos);

		if (robo6.isVisivel() == false && robo7.isVisivel() == false) {
			proximaOndaColisoes = true;
		}

		if (robo1.isVisivel() == false && robo2.isVisivel() == false && robo3.isVisivel() == false
				&& robo4.isVisivel() == false &&
				robo5.isVisivel() == false) {
			proximaOndaAtiradores = true;
		}
		if (robo1.isVisivel() == false && robo2.isVisivel() == false && robo3.isVisivel() == false
				&& robo4.isVisivel() == false && robo5.isVisivel() == false && alien.isVisivel() == false
				&& robo6.isVisivel() == false && robo7.isVisivel() == false) {
			vitoria = true;
			stopSound();
			startSound(musicaVitoria);
		}
		gameOver = fase.checarJogadores(jogador1, jogador2, doisJogadores);
		if (gameOver){
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

	public void setProximaFase(boolean proximaFase) {
		this.proximaFase = proximaFase;
	}

	public boolean isProximaFase() {
		return this.proximaFase;
	}

	public void setEmJogo(boolean emJogo) {
		this.emJogo = emJogo;
	}

	public boolean isDoisJogadores() {
		return doisJogadores;
	}

	public void setDoisJogadores(boolean doisJogadores) {
		Menu.doisJogadores = doisJogadores;
	}

	public boolean opcaoSelecionada() {
		return emJogo;
	}
}