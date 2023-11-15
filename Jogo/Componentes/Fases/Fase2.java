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
import javax.swing.JPanel;
import javax.swing.Timer;

import Jogo.Componentes.Inimigos.Robo;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Jogadores.TiroNave;
import Jogo.Componentes.Objetos.BarraVida;

public class Fase2 extends JPanel implements ActionListener {

	private Image fundo;
	private Image alerta;
	private Image explosao;

	private Jogador1 jogador1;
	private Jogador2 jogador2;
	private BarraVida barra;
	private Timer timer;
	private Robo robo1;
	private Robo robo2;
	private Robo robo3;
	private List<Robo> robos;
	private boolean emJogo;
	private boolean vitoria;
	private int contador = 0;
	
	public Fase2() {

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("assets//fase01.png");
		fundo = referencia.getImage();

		referencia = new ImageIcon("assets//warninggif.gif");
		alerta = referencia.getImage();

		referencia = new ImageIcon("assets//explosao.gif");
		explosao = referencia.getImage();

		jogador1 = new Jogador1();
		jogador2 = new Jogador2();

		jogador1.setDano(false);

		jogador1.load();
		jogador2.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();

		inicializaInimigos();

		emJogo = true;
		vitoria = false;

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

		robo1 = new Robo(1600, 2000);
		robo2 = new Robo(1600, 2000);
		robo3 = new Robo(1600, 2000);

		robo1.load();
		robo2.load();
		robo3.load();
	}

	private static Font loadFont(String path, float size) {
        try {
            File fontFile = new File(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            
            return font.deriveFont(size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }

	public void paint(Graphics g) {
		barra = new BarraVida();
		Graphics2D graficos = (Graphics2D) g;
		Font fonte = loadFont("assets//PressStart2P.ttf", 16);
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
	
			if(jogador1.isVisivel()){
				graficos.drawImage(jogador1.getImagem(), jogador1.getX(), jogador1.getY(), this);
				jogador1.drawTiroNave(graficos);
			}
			
			if(jogador2.isVisivel()){
				graficos.drawImage(jogador2.getImagem(), jogador2.getX(), jogador2.getY(), this);
				jogador2.drawTiroNave(graficos);
			}

			contador = 0;
			for (int i = 0; i < robos.size(); i++){
				if (robos.get(i).isVisivel() == false){
					contador += 1;
				}
			}
			
			if (contador == robos.size()){
				if (robo1.isVisivel()){
					graficos.drawImage(robo1.getImagem(), robo1.getX(), robo1.getY(), this);
					if(robo1.getX() != 1100){
						graficos.drawImage(alerta, 1450, 150, this);
					}
				} else if(robo1.isVisivel() == false){
					graficos.drawImage(explosao, robo1.getX(), robo1.getY(), this);
				}
				if (robo2.isVisivel()){
					graficos.drawImage(robo2.getImagem(), robo2.getX(), robo2.getY(), this);
					if(robo2.getX() != 1000){
						graficos.drawImage(alerta, 1450, 375, this);
					}
				} else if(robo2.isVisivel() == false){
					graficos.drawImage(explosao, robo2.getX(), robo2.getY(), this);
				}
				if (robo3.isVisivel()){
					graficos.drawImage(robo3.getImagem(), robo3.getX(), robo3.getY(), this);
					if(robo3.getX() != 1100){
						graficos.drawImage(alerta, 1450, 600, this);
					}
				} else if(robo3.isVisivel() == false){
					graficos.drawImage(explosao, robo3.getX(), robo3.getY(), this);
				}
				if (robo1.getX() == 1100){
					if (robo1.isVisivel()){
					robo1.drawTiroRobo(graficos);
					} 
				}
				if (robo2.getX() == 1000){
					if (robo2.isVisivel()){
						robo2.drawTiroRobo(graficos);
					}
				}
				if (robo3.getX() == 1100){
					if (robo3.isVisivel()){
						robo3.drawTiroRobo(graficos);
					} 
				}	
			}	
			
			for (int j = 0; j < robos.size(); j++) {
				Robo robo = robos.get(j);
				robo.load2();
				graficos.drawImage(robo.getImagem(), robo.getX(), robo.getY(), this);
			}

			for (int j = 0; j < robos.size(); j++) {
				Robo robo = robos.get(j);
				if (robo.isVisivel() == false){
					graficos.drawImage(explosao, robo.getX(), robo.getY(), this);
				}
			}

			graficos.drawImage(barra.getBarraVida10(), 10, 10, this);
			if (jogador1.getVida() == 9){
				graficos.drawImage(barra.getBarraVida9(), 10, 10, this);
			} else if (jogador1.getVida() == 8){
				graficos.drawImage(barra.getBarraVida8(), 10, 10, this);
			} else if (jogador1.getVida() == 7){
				graficos.drawImage(barra.getBarraVida7(), 10, 10, this);
			} else if (jogador1.getVida() == 6){
				graficos.drawImage(barra.getBarraVida6(), 10, 10, this);
			} else if (jogador1.getVida() == 5){
				graficos.drawImage(barra.getBarraVida5(), 10, 10, this);
			} else if (jogador1.getVida() == 4){
				graficos.drawImage(barra.getBarraVida4(), 10, 10, this);
			} else if (jogador1.getVida() == 3){
				graficos.drawImage(barra.getBarraVida3(), 10, 10, this);
			} else if (jogador1.getVida() == 2){
				graficos.drawImage(barra.getBarraVida2(), 10, 10, this);
			} else if (jogador1.getVida() == 1){
				graficos.drawImage(barra.getBarraVida1(), 10, 10, this);
			} else if (jogador1.getVida() <= 0){
				graficos.drawImage(barra.getBarraVida0(), 10, 10, this);
			} 

			graficos.drawImage(barra.getBarraVida10(), 10, 700, this);
			if (jogador2.getVida() == 9){
				graficos.drawImage(barra.getBarraVida9(), 10, 700, this);
			} else if (jogador2.getVida() == 8){
				graficos.drawImage(barra.getBarraVida8(), 10, 700, this);
			} else if (jogador2.getVida() == 7){
				graficos.drawImage(barra.getBarraVida7(), 10, 700, this);
			} else if (jogador2.getVida() == 6){
				graficos.drawImage(barra.getBarraVida6(), 10, 700, this);
			} else if (jogador2.getVida() == 5){
				graficos.drawImage(barra.getBarraVida5(), 10, 700, this);
			} else if (jogador2.getVida() == 4){
				graficos.drawImage(barra.getBarraVida4(), 10, 700, this);
			} else if (jogador2.getVida() == 3){
				graficos.drawImage(barra.getBarraVida3(), 10, 700, this);
			} else if (jogador2.getVida() == 2){
				graficos.drawImage(barra.getBarraVida2(), 10, 700, this);
			} else if (jogador2.getVida() == 1){
				graficos.drawImage(barra.getBarraVida1(), 10, 700, this);
			}
			
		} else {
			ImageIcon fimJogo = new ImageIcon("assets//fim_de_jogo.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
		} 
		if (vitoria == true){
			g.setFont(fonte);
			g.setColor(Color.WHITE);
			ImageIcon vitoriaJogo = new ImageIcon("assets//victory.png");
			graficos.drawImage(vitoriaJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
			graficos.drawString("Pontuação Jogador 1 = " + jogador1.getPontuacaoJogador1(), 20, 40);
			graficos.drawString("Pontuação Jogador 2 = " + jogador2.getPontuacaoJogador2(), 1000, 40);
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
		for (int i = 0; i < robos.size(); i++){
			if (robos.get(i).isVisivel() == false){
				contador += 1;
			}
		}
		if (contador == robos.size()){
			robo1.updateRoboAtirador(1100, 150);
			robo2.updateRoboAtirador(1000, 375);
			robo3.updateRoboAtirador(1100, 600);
		}

		robo1.atirar();
		robo2.atirar();
		robo3.atirar();

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
		if (robo1.isVisivel()){
			robo1.colisaoNaveRobo(jogador1);
			robo1.colisaoNaveRobo(jogador2);
		}
		if (robo2.isVisivel()){
			robo2.colisaoNaveRobo(jogador1);
			robo2.colisaoNaveRobo(jogador2);
		}
		if (robo3.isVisivel()){
			robo3.colisaoNaveRobo(jogador1);
			robo3.colisaoNaveRobo(jogador2);
		}
		for (int i = 0; i < robos.size(); i++) {
			Robo tempRobo = robos.get(i);
			tempRobo.colisaoNaveRobo(jogador1);
		}

		for (int i = 0; i < robos.size(); i++) {
			Robo tempRobo = robos.get(i);
			tempRobo.colisaoNaveRobo(jogador2);
		}

		//Colisões de tiro da Nave com Robo:
		List<TiroNave> tiros1 = jogador1.getTiros();
		for (int j = 0; j < tiros1.size(); j++) {
			robo1.colisaoRoboTiro(jogador1, j);
			robo2.colisaoRoboTiro(jogador1, j);
			robo3.colisaoRoboTiro(jogador1, j);
		}
		List<TiroNave> tiros2 = jogador2.getTiros();
		for (int j = 0; j < tiros2.size(); j++) {
			robo1.colisaoRoboTiro(jogador2, j);
			robo2.colisaoRoboTiro(jogador2, j);
			robo3.colisaoRoboTiro(jogador2, j);
		}

		List<TiroNave> tiros3 = jogador1.getTiros();
		for (int j = 0; j < tiros3.size(); j++) {
			for (int i = 0; i < robos.size(); i++) {
				Robo tempRobo = robos.get(i);
				tempRobo.colisaoRoboTiro(jogador1, j);
			}
		}
		List<TiroNave> tiros4 = jogador2.getTiros();
		for (int j = 0; j < tiros4.size(); j++) {
			for (int i = 0; i < robos.size(); i++) {
				Robo tempRobo = robos.get(i);
				tempRobo.colisaoRoboTiro(jogador2, j);
			}
		}

		//Colisões de tiro do Robo com a Nave:
		robo1.colisaoNaveTiro(jogador1);
		robo1.colisaoNaveTiro(jogador2);

		robo2.colisaoNaveTiro(jogador1);
		robo2.colisaoNaveTiro(jogador2);

		robo3.colisaoNaveTiro(jogador1);
		robo3.colisaoNaveTiro(jogador2);
		
		if (jogador1.getVida() == 0 ){
			jogador1.setVisivel(false);
		}
		if (jogador2.getVida() == 0 ){
			jogador2.setVisivel(false);
		} 
		if (robo1.getVida() == 0){
			robo1.setVisivel(false);
		} 
		if (robo2.getVida() == 0){
			robo2.setVisivel(false);
		} 
		if (robo3.getVida() == 0){
			robo3.setVisivel(false);
		} 
		for (int i = 0; i < robos.size(); i++){
			Robo tempRobo = robos.get(i);
			if (tempRobo.getVida() == 0 ){
				tempRobo.setVisivel(false);
			} 
		}
		if (robo1.isVisivel() == false && robo2.isVisivel() == false && robo3.isVisivel() == false){
			vitoria = true;
		} else if (jogador1.getVida() <= 0 && jogador2.getVida() <= 0){
			emJogo = false;
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