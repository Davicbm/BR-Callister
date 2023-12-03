package Jogo.Componentes.Fases;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Jogo.Container;
import Jogo.Componentes.Inimigos.Alien;
import Jogo.Componentes.Inimigos.Drakthar;
import Jogo.Componentes.Inimigos.Robo;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Jogadores.TiroNave;
import Jogo.Componentes.Objetos.BarraVida;
import Jogo.Componentes.Objetos.PowerUp;

public class Fase extends JPanel {

	public static boolean doisJogadores;

	private boolean vitoria;
	private boolean gameOver;

	public Fase(Container container) {
		this.vitoria = false;
		this.gameOver = false;
	}

	// Desenha os componentes iniciais da fase(Jogadores):
	public void drawComponentesIniciais(Graphics2D graficos, Jogador1 jogador1, Jogador2 jogador2, String nomeJogador1,
			String nomeJogador2, BarraVida barra, String faseAtual) {

		Font fonte = loadFont("assets//PressStart2P.ttf", 16);
		Font fonte2 = loadFont("assets//PressStart2P.ttf", 12);

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

		graficos.setFont(fonte);
		graficos.setColor(Color.WHITE);
		graficos.drawString("Fase " + faseAtual, 700, 50);

		graficos.setFont(fonte2);
		graficos.setColor(Color.WHITE);
		graficos.drawString(nomeJogador1, 15, 30);
		barra.paintBarraVida(graficos, jogador1);

		if (doisJogadores) {
			graficos.drawString("-- Pontuações -- ", 1325, 35);
			graficos.drawString(nomeJogador1 + " = " + Jogador1.pontuacaoJogador1, 1350, 60);

			graficos.drawString(nomeJogador2 + " = " + Jogador2.pontuacaoJogador2, 1350, 90);
		} else if (doisJogadores == false) {
			graficos.drawString("-- Pontuações -- ", 1325, 35);
			graficos.drawString(nomeJogador1 + " = " + Jogador1.pontuacaoJogador1, 1350, 50);
		}

		if (doisJogadores) {
			if (nomeJogador1 != null && nomeJogador1 != null) {
				graficos.setFont(fonte2);
				graficos.setColor(Color.WHITE);
				graficos.drawString(nomeJogador2, 15, 790);
				barra.paintBarraVida(graficos, jogador2);
			}
		}
	}

	public void drawTelaPausa(Graphics2D graficos, int opcaoMenuPausa) {
		Font fonteMenu = loadFont("assets//PressStart2P.ttf", 24);
		Font fonte = loadFont("assets//PressStart2P.ttf", 16);
		graficos.setFont(fonteMenu);
		graficos.setColor(Color.WHITE);
		graficos.drawString("Jogo Pausado", 620, 300);

		graficos.setFont(fonte);
		graficos.setColor(Color.WHITE);
		graficos.drawString("Continuar", 690, 350);
		graficos.drawString("Reiniciar", 690, 400);
		graficos.drawString("Sair", 720, 450);
		graficos.drawString(">", 670 + (opcaoMenuPausa * 15) - 20, 350 + opcaoMenuPausa * 50);
	}

	public void drawTelaVitoria(Graphics2D graficos, String nomeJogador1, String nomeJogador2) {
		Font fonte = loadFont("assets//PressStart2P.ttf", 16);

		graficos.setFont(fonte);
		graficos.setColor(Color.WHITE);
		ImageIcon vitoriaJogo = new ImageIcon("planosFundo//victory.png");
		graficos.drawImage(vitoriaJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
		graficos.setFont(fonte);
		graficos.setColor(Color.WHITE);
		graficos.drawString("Aperte enter para a próxima fase!", 500, 800);

		if (doisJogadores) {
			graficos.drawString("Pontuação " + nomeJogador1 + " = " + Jogador1.pontuacaoJogador1, 20, 40);
			graficos.drawString("Pontuação " + nomeJogador2 + " = " + Jogador2.pontuacaoJogador2, 1125, 40);
		} else if (doisJogadores == false) {
			graficos.drawString("Pontuação " + nomeJogador1 + " = " + Jogador1.pontuacaoJogador1, 20, 40);
		}
	}

	public void drawTelaDerrota(Graphics2D graficos, int opcaoGameOver) {
		Font fonte = loadFont("assets//PressStart2P.ttf", 16);

		ImageIcon fimJogo = new ImageIcon("planosFundo//fim_de_jogo.png");
		graficos.drawImage(fimJogo.getImage(), 0, 0, getWidth(), getHeight(), this);
		graficos.setFont(fonte);
		graficos.setColor(Color.WHITE);
		graficos.drawString("Reiniciar a fase!", 600, 700);
		graficos.drawString("Voltar ao menu principal!", 600, 750);
		graficos.drawString(">", 575 + (opcaoGameOver / 15), 700 + opcaoGameOver * 50);
	}

	// Inicializa a fonte a ser utilizada:
	public Font loadFont(String path, float size) {
		try {
			File fontFile = new File(path);
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

			return font.deriveFont(size);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			return new Font("Arial", Font.PLAIN, (int) size);
		}
	}

	// Checa o estado do Robô:
	public void checarRobo(Robo robo) {
		if (robo.getVida() == 0) {
			robo.setVisivel(false);
		}
	}

	// Checa o estado do Alien:
	public void checarAlien(Alien alien) {
		if (alien.getVida() == 0) {
			alien.setVisivel(false);
		}
	}

	// Checa o estado do Chefão:
	public void checarDrakthar(Drakthar drakthar) {
		if (drakthar.getVida() == 0) {
			drakthar.setVisivel(false);
		}
	}

	// Checa o estado dos Robôs de Colisões:
	public void checarRobos(List<Robo> robos) {
		for (int i = 0; i < robos.size(); i++) {
			Robo tempRobo = robos.get(i);
			if (tempRobo.getVida() == 0) {
				tempRobo.setVisivel(false);
			}
		}
	}

	// Checa o estado dos Jogadores:
	public boolean checarJogadores(Jogador1 jogador1, Jogador2 jogador2, boolean doisJogadores) {
		if (doisJogadores) {
			if (jogador1.getVida() <= 0 && jogador2.getVida() <= 0) {
				gameOver = true;
			}
		}
		if (jogador1.getVida() <= 0 && !doisJogadores) {
			gameOver = true;
		}
		return gameOver;
	}

	///// *SEÇÃO DE COLISÕES */////
	// Colisões de naves com power ups:

	public void colisoesPowerUps(List<PowerUp> powerUps, Jogador1 jogador1,
			Jogador2 jogador2) {
		for (int i = 0; i < powerUps.size(); i++) {
			PowerUp tempPowerUp = powerUps.get(i);
			tempPowerUp.colisaoPowerUp(jogador1);
			tempPowerUp.colisaoPowerUp(jogador2);
		}
	}

	// Colisões de naves com robôs de colisão:
	public void colisoesNavesRobos(List<Robo> robos, Jogador1 jogador1, Jogador2 jogador2) {
		for (int i = 0; i < robos.size(); i++) {
			Robo tempRobo = robos.get(i);
			tempRobo.colisaoNaveRobo(jogador1);
			tempRobo.colisaoNaveRobo(jogador2);
		}
	}

	// Colisões de tiros(naves) com robôs:
	public void colisoesTiroEmRobo1(Robo robo, Jogador1 jogador1, Jogador2 jogador2, int x) {
		List<TiroNave> tiros1 = jogador1.getTiros();
		List<TiroNave> tiros2 = jogador2.getTiros();

		for (int j = 0; j < tiros1.size(); j++) {
			if (robo.getX() == x) {
				robo.colisaoRoboTiro(jogador1, j);
			}
		}
		for (int j = 0; j < tiros2.size(); j++) {
			if (robo.getX() == x) {
				robo.colisaoRoboTiro(jogador2, j);
			}
		}
	}

	// Colisões de tiros(naves) com robôs de colisão:
	public void colisoesTiroEmRobo2(Jogador1 jogador1, Jogador2 jogador2, List<Robo> robos) {
		List<TiroNave> tiros1 = jogador1.getTiros();

		for (int j = 0; j < tiros1.size(); j++) {
			for (int i = 0; i < robos.size(); i++) {
				Robo tempRobo = robos.get(i);
				tempRobo.colisaoRoboTiro(jogador1, j);
			}
		}

		List<TiroNave> tiros2 = jogador2.getTiros();

		for (int j = 0; j < tiros2.size(); j++) {
			for (int i = 0; i < robos.size(); i++) {
				Robo tempRobo = robos.get(i);
				tempRobo.colisaoRoboTiro(jogador2, j);
			}
		}
	}

	// Colisões de tiros(naves) com aliens:
	public void colisoesTiroEmAlien(Alien alien, Jogador1 jogador1, Jogador2 jogador2, int x) {
		if (alien.getX() == x) {
			List<TiroNave> tiros1 = jogador1.getTiros();
			for (int j = 0; j < tiros1.size(); j++) {
				alien.colisaoAlienTiro(jogador1, j);
			}

			List<TiroNave> tiros2 = jogador2.getTiros();
			for (int j = 0; j < tiros2.size(); j++) {
				alien.colisaoAlienTiro(jogador2, j);
			}
		}
	}

	// Colisões de tiros(naves) com o Chefão:
	public void colisoesTiroEmDrakthar(Drakthar drakthar, Jogador1 jogador1, Jogador2 jogador2, int x) {
		
		if (drakthar.getX() == x) {
			List<TiroNave> tiros1 = jogador1.getTiros();
			for (int j = 0; j < tiros1.size(); j++) {
				drakthar.colisaoDraktharTiro(jogador1, j);
			}
	
			List<TiroNave> tiros2 = jogador2.getTiros();
			for (int j = 0; j < tiros2.size(); j++) {
				drakthar.colisaoDraktharTiro(jogador2, j);
			}
		}
	}
}