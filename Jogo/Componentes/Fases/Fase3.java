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
import Jogo.Componentes.Inimigos.Drakthar;
import Jogo.Componentes.Inimigos.Robo;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Jogadores.TiroNave;
import Jogo.Componentes.Objetos.BarraVida;

public class Fase3 extends Fase implements ActionListener {

	private Image fundo;
	private Image alerta;

	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private BarraVida barra;
	private Timer timer;

	private List<Robo> robos;

	private boolean proximaFase;
	private boolean vitoria;
	private boolean doisJogadores;
	private boolean gameOver;
	private Alien alien1;
	private Alien alien2;

	private Drakthar drakthar;

	private int contador = 0;

	TecladoAdapter teclado = new TecladoAdapter();
	private Container container;

	public Fase3(Container container) {
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

		if (Menu.doisJogadores){
			doisJogadores = true;
		} else {
			doisJogadores = false;
		}

		proximaFase = false;
		vitoria = false;
		gameOver = false;

		this.requestFocusInWindow();
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
		alien2 = new Alien(1800, 500);

		drakthar = new Drakthar(1800, 300);

		alien1.load();
		alien2.load();

		drakthar.load();
	}

	public void paint(Graphics g) {
		barra = new BarraVida();
		Graphics2D graficos = (Graphics2D) g;
		Font fonte = loadFont("assets//PressStart2P.ttf", 16);
		Font fonte2 = loadFont("assets//PressStart2P.ttf", 12);
		graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);

		g.setFont(fonte);
		g.setColor(Color.WHITE);
		graficos.drawString("Fase 3", 1400, 50);
	
		if(jogador1.isVisivel()){
			graficos.drawImage(jogador1.getImagem(), jogador1.getX(), jogador1.getY(), this);
			jogador1.drawTiroNave(graficos);
		}
		if (doisJogadores){
			if(jogador2.isVisivel()){
				graficos.drawImage(jogador2.getImagem(), jogador2.getX(), jogador2.getY(), this);
				jogador2.drawTiroNave(graficos);
			}
		}
		contador = 0;
		for (int i = 0; i < robos.size(); i++){
			if (robos.get(i).isVisivel() == false){
				contador += 1;
			}
		}
			
		if (contador == robos.size()){
			if (alien1.isVisivel()){
				graficos.drawImage(alien1.getImagem(), alien1.getX(), alien1.getY(), this);
				if(alien1.getX() != 1100){
					//muda a posição para os alien
					graficos.drawImage(alerta, 1450, 100, this);
				}
			} 
			if (alien2.isVisivel()){
				graficos.drawImage(alien2.getImagem(), alien2.getX(), alien2.getY(), this);
				if(alien2.getX() != 1100){
					graficos.drawImage(alerta, 1450, 500, this);
				}
			}
			if (drakthar.isVisivel()){
				graficos.drawImage(drakthar.getImagem(), drakthar.getX(), drakthar.getY(), this);
				if(drakthar.getX() != 1200){
					graficos.drawImage(alerta, 1450, 300, this);
				}
			}
			
			if (alien1.getX() == 1100){
				if (alien1.isVisivel()){
				alien1.drawTiroAlien(graficos);
				} 
			}
			if (alien2.getX() == 1100){
				if (alien2.isVisivel()){
					alien2.drawTiroAlien(graficos);
				}
			}
			if (drakthar.getX() == 1200){
				if (drakthar.isVisivel()){
					drakthar.drawTiroDrakthar(graficos);
				}
			}
		}
		for (int j = 0; j < robos.size(); j++) {
			Robo robo = robos.get(j);
			robo.load2();
			graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
		}

		g.setFont(fonte2);
		g.setColor(Color.WHITE);
		graficos.drawString("Vida Jogador 1 ", 15, 30);
		barra.paintBarraVida(graficos, jogador1);
			
		if (doisJogadores){
			g.setFont(fonte2);
			g.setColor(Color.WHITE);
			graficos.drawString("Vida Jogador 2 ", 15, 100);
			barra.paintBarraVida(graficos, jogador2);
		}

		if (gameOver == true){
			ImageIcon fimJogo = new ImageIcon("assets//fim_de_jogo.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
			g.setFont(fonte);
			g.setColor(Color.WHITE);
			graficos.drawString("Aperte enter para reiniciar o jogo!", 500, 800);
		} 
		if (vitoria == true){
			g.setFont(fonte);
			g.setColor(Color.WHITE);
			ImageIcon vitoriaJogo = new ImageIcon("assets//victory.png");
			graficos.drawImage(vitoriaJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
			graficos.drawString("Pontuação Jogador 1 = " + jogador1.getPontuacaoJogador1(), 20, 40);
			graficos.drawString("Pontuação Jogador 2 = " + jogador2.getPontuacaoJogador2(), 1125, 40);
		}	
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador1.update();
		jogador1.atirar();

		if (doisJogadores){
			jogador2.update();
			jogador2.atirar();
		}
		
		contador = 0;
		for (int i = 0; i < robos.size(); i++){
			if (robos.get(i).isVisivel() == false){
				contador += 1;
			}
		}

		if (contador == robos.size()){
			alien1.updateAlien(1100);
			alien2.updateAlien(1100);
		
			drakthar.updateDrakthar(1200);
		}

		if(alien1.getX() == 1100){
			alien1.atirar();
			alien2.atirar();
		}
		
		if(drakthar.getX() == 1200){
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
		
		//Colisões de Nave com Robô:
		for (int i = 0; i < robos.size(); i++) {
			Robo tempRobo = robos.get(i);
			tempRobo.colisaoNaveRobo(jogador1);
		}

		if (doisJogadores){
			for (int i = 0; i < robos.size(); i++) {
				Robo tempRobo = robos.get(i);
				tempRobo.colisaoNaveRobo(jogador2);
			}
		}

		List<TiroNave> tiros3 = jogador1.getTiros();
		for (int j = 0; j < tiros3.size(); j++) {
			for (int i = 0; i < robos.size(); i++) {
				Robo tempRobo = robos.get(i);
				tempRobo.colisaoRoboTiro(jogador1, j);
			}
		}
		if(doisJogadores){
			List<TiroNave> tiros4 = jogador2.getTiros();
			for (int j = 0; j < tiros4.size(); j++) {
				for (int i = 0; i < robos.size(); i++) {
					Robo tempRobo = robos.get(i);
					tempRobo.colisaoRoboTiro(jogador2, j);
				}
			}	
		}

		if (alien1.getX() == 1100){
			List<TiroNave> tiros5 = jogador1.getTiros();
			for (int j = 0; j < tiros5.size(); j++) {
				alien1.colisaoAlienTiro(jogador1, j);
				alien2.colisaoAlienTiro(jogador1, j);
			}

			if (doisJogadores){
				List<TiroNave> tiros6 = jogador2.getTiros();
				for (int j = 0; j < tiros6.size(); j++) {
					alien1.colisaoAlienTiro(jogador2, j);
					alien2.colisaoAlienTiro(jogador2, j);
				}
			}
		}
		
		

		//Colisões de tiro do Robo com a Nave:
		alien1.colisaoNaveTiro(jogador1);
		alien2.colisaoNaveTiro(jogador1);
		if (doisJogadores){
			alien1.colisaoNaveTiro(jogador2);
			alien2.colisaoNaveTiro(jogador2);
		}
		
		if (jogador1.getVida() == 0 ){
			jogador1.setVisivel(false);
		}
		if (jogador2.getVida() == 0 ){
			jogador2.setVisivel(false);
		} 
		if (alien1.getVida() == 0){
			alien1.setVisivel(false);
		} 
		if (alien2.getVida() == 0){
			alien2.setVisivel(false);
		} 
		
		if (jogador1.getVida() == 0 ){
			jogador1.setVisivel(false);
		}
		if (jogador2.getVida() == 0 ){
			jogador2.setVisivel(false);
		} 

		for (int i = 0; i < robos.size(); i++){
			Robo tempRobo = robos.get(i);
			if (tempRobo.getVida() == 0 ){
				tempRobo.setVisivel(false);
			} 
		}
		if (alien1.getVida() == 0){
			alien1.setVisivel(false);
		} 
		if (alien2.getVida() == 0){
			alien2.setVisivel(false);
		} 

		if (jogador1.getVida() <= 0){
			gameOver = true;
		}
		if (doisJogadores){
			if (jogador1.getVida() <= 0 && jogador2.getVida() <= 0){
			gameOver = true;
			}
		}
	}

	private class TecladoAdapter implements KeyListener {
		@Override

		public void keyPressed(KeyEvent e) {
			int codigo = e.getKeyCode();

			jogador1.keyPressed(e);
			if (doisJogadores){
				jogador2.keyPressed(e);
			}
			
			if (vitoria){
				if (codigo == KeyEvent.VK_ENTER){
					container.avancarFase();
				}
			}
			if (gameOver){
				if (codigo == KeyEvent.VK_ENTER){
					container.reiniciarJogo();
				}
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			jogador1.keyRelease(e);
			if(doisJogadores){
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

	public boolean isProximaFase() {
		return this.proximaFase;
	}

	public void setProximaFase(boolean proximaFase) {
		this.proximaFase = proximaFase;
	}
	
}
