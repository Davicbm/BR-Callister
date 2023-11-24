package Jogo.Componentes.Fases;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
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

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Jogo.Container;
import Jogo.Componentes.Inimigos.Robo;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Objetos.BarraVida;
import Jogo.Componentes.Objetos.PowerUp;

public class Fase1 extends Fase implements ActionListener {

	private boolean doisJogadores;
	private String nomeJogador1 = Menu.nomeJogador1;
	private String nomeJogador2 = Menu.nomeJogador2;

	private Image fundo;
	private Image alerta;

	private Jogador1 jogador1;
	private Jogador2 jogador2;

	private BarraVida barra;
	private PowerUp powerUp;
	private List<PowerUp> powerUps;

	private Timer timer;

	private Robo robo1;
	private Robo robo2;
	private Robo robo3;
	private List<Robo> robos;

	private boolean emJogo;
	private boolean vitoria;
	private boolean gameOver;
	private boolean proximaFase = false;

	private int contador = 0;
	TecladoAdapter teclado = new TecladoAdapter();

	private Container container;
	Fase fase = new Fase();

	public Fase1(Container container) {
		this.container = container;

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("assets//fase01.png");
		fundo = referencia.getImage();

		referencia = new ImageIcon("assets//warninggif.gif");
		alerta = referencia.getImage();

		jogador1 = new Jogador1();
		jogador2 = new Jogador2();

		robo1 = new Robo(2000, 150);
		robo2 = new Robo(2000, 375);
		robo3 = new Robo(2000, 600);

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
	}

	public void inicializaInimigos() {

		robos = new ArrayList<Robo>();
		for (int i = 0; i < 0; i++) {
			int x = (int) (Math.random() * 8000) + 1980;
			int y = (int) (Math.random() * 650) + 10;
			robos.add(new Robo(x, y));
			robos.get(i).setVida(1);
		}

		robo1.load();
		robo2.load();
		robo3.load();
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

		Graphics2D graficos = (Graphics2D) g;
		barra = new BarraVida();
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
				if (robo1.getX() != 1100) {
					graficos.drawImage(alerta, 1450, 150, this);
				}
			}
			if (robo2.isVisivel()) {
				graficos.drawImage(robo2.getImagem(), robo2.getX(), robo2.getY(), this);
				if (robo2.getX() != 1000) {
					graficos.drawImage(alerta, 1450, 375, this);
				}
			}
			if (robo3.isVisivel()) {
				graficos.drawImage(robo3.getImagem(), robo3.getX(), robo3.getY(), this);
				if (robo3.getX() != 1100) {
					graficos.drawImage(alerta, 1450, 600, this);
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

		g.setFont(fonte2);
		g.setColor(Color.WHITE);
		graficos.drawString(nomeJogador1, 15, 30);
		barra.paintBarraVida(graficos, jogador1);

		if (doisJogadores) {
			graficos.drawString("-- Pontuações -- ", 1325, 35);
			graficos.drawString(nomeJogador1 + " = " + jogador1.getPontuacaoJogador1(), 1350, 60);
			graficos.drawString(nomeJogador2 + " = " + jogador2.getPontuacaoJogador2(), 1350, 90);
		} else if (doisJogadores == false) {
			graficos.drawString("-- Pontuações -- ", 1325, 35);
			graficos.drawString(nomeJogador1 + " = " + jogador1.getPontuacaoJogador1(), 1350, 50);
		}

		if (doisJogadores) {
			if (nomeJogador1 != null && nomeJogador1 != null) {
				g.setFont(fonte2);
				g.setColor(Color.WHITE);
				graficos.drawString(nomeJogador2, 15, 790);
				barra.paintBarraVida(graficos, jogador2);
			}
		}

		if (gameOver) {
			ImageIcon fimJogo = new ImageIcon("assets//fim_de_jogo.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
			g.setFont(fonte);
			g.setColor(Color.WHITE);
			graficos.drawString("Aperte enter para reiniciar o jogo!", 500, 800);
		}

		if (vitoria) {
			g.setFont(fonte);
			g.setColor(Color.WHITE);
			ImageIcon vitoriaJogo = new ImageIcon("assets//victory.png");
			graficos.drawImage(vitoriaJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
			g.setFont(fonte);
			g.setColor(Color.WHITE);
			graficos.drawString("Aperte enter para a próxima fase!", 500, 800);
			if (doisJogadores) {
				graficos.drawString("Pontuação " + nomeJogador1 + " = " + jogador1.getPontuacaoJogador1(), 20, 40);
				graficos.drawString("Pontuação " + nomeJogador2 + " = " + jogador2.getPontuacaoJogador2(), 1125, 40);
			} else if (doisJogadores == false) {
				graficos.drawString("Pontuação " + nomeJogador1 + " = " + jogador1.getPontuacaoJogador1(), 20, 40);
			}
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
			robo1.updateRoboAtirador(1100, 150);
			robo2.updateRoboAtirador(1000, 375);
			robo3.updateRoboAtirador(1100, 600);
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
		fase.colisoesTiroRobo(robo1, jogador1, jogador2, 1100);
		fase.colisoesTiroRobo(robo2, jogador1, jogador2, 1000);
		fase.colisoesTiroRobo(robo3, jogador1, jogador2, 1100);

		// Colisões de tiro da Nave com Robo de colisão:
		fase.colisoesTiroRobo2(jogador1, robos);
		fase.colisoesTiroRobo2(jogador2, robos);

		// Colisões de tiro do Robo com a Nave:
		robo1.colisaoNaveTiro(jogador1);
		robo1.colisaoNaveTiro(jogador2);

		robo2.colisaoNaveTiro(jogador1);
		robo2.colisaoNaveTiro(jogador2);

		robo3.colisaoNaveTiro(jogador1);
		robo3.colisaoNaveTiro(jogador2);

		//Checar vida dos robôs:
		fase.checarRobo(robo1);
		fase.checarRobo(robo2);
		fase.checarRobo(robo3);
		
		fase.checarRobos(robos);
		
		if (robo1.isVisivel() == false && robo2.isVisivel() == false && robo3.isVisivel() == false) {
			vitoria = true;
		}
		gameOver = fase.checarJogadores(jogador1, jogador2);
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

	public void desativarKeyListener() {
		removeKeyListener(teclado);
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