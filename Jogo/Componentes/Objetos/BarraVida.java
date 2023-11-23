package Jogo.Componentes.Objetos;

import javax.swing.ImageIcon;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;

import java.awt.Graphics2D;
import java.awt.Image;

public class BarraVida {
    private Image barraVida10;
	private Image barraVida9;
	private Image barraVida8;
	private Image barraVida7;
	private Image barraVida6;
	private Image barraVida5;
	private Image barraVida4;
	private Image barraVida3;
	private Image barraVida2;
	private Image barraVida1;
	private Image barraVida0;
		
	public void paintBarraVida(Graphics2D graficos, Jogador1 jogador){
		
		ImageIcon referencia = new ImageIcon("assets//barravida_full.png");
		barraVida10 = referencia.getImage();
		

		referencia = new ImageIcon("assets//barravida_9.png");
		barraVida9 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_8.png");
		barraVida8 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_7.png");
		barraVida7 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_6.png");
		barraVida6 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_5.png");
		barraVida5 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_4.png");
		barraVida4 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_3.png");
		barraVida3 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_2.png");
		barraVida2 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_1.png");
		barraVida1 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_0.png");
		barraVida0 = referencia.getImage();

		graficos.drawImage(barraVida10, 15, 40, null);
		if (jogador.getVida() == 9){
				graficos.drawImage(barraVida9, 15, 40, null);
			} else if (jogador.getVida() == 8){
				graficos.drawImage(barraVida8, 15, 40, null);
			} else if (jogador.getVida() == 7){
				graficos.drawImage(barraVida7, 15, 40, null);
			} else if (jogador.getVida() == 6){
				graficos.drawImage(barraVida6, 15, 40, null);
			} else if (jogador.getVida() == 5){
				graficos.drawImage(barraVida5, 15, 40, null);
			} else if (jogador.getVida() == 4){
				graficos.drawImage(barraVida4, 15, 40, null);
			} else if (jogador.getVida() == 3){
				graficos.drawImage(barraVida3, 15, 40, null);
			} else if (jogador.getVida() == 2){
				graficos.drawImage(barraVida2, 15, 40, null);
			} else if (jogador.getVida() == 1){
				graficos.drawImage(barraVida1, 15, 40, null);
			} else if (jogador.getVida() <= 0){
				graficos.drawImage(barraVida0, 15, 40, null);
				jogador.setVisivel(false);
			}
	}

	public void paintBarraVida(Graphics2D graficos, Jogador2 jogador){
		
		ImageIcon referencia = new ImageIcon("assets//barravida_full.png");
		barraVida10 = referencia.getImage();
		
		referencia = new ImageIcon("assets//barravida_9.png");
		barraVida9 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_8.png");
		barraVida8 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_7.png");
		barraVida7 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_6.png");
		barraVida6 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_5.png");
		barraVida5 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_4.png");
		barraVida4 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_3.png");
		barraVida3 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_2.png");
		barraVida2 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_1.png");
		barraVida1 = referencia.getImage();

		referencia = new ImageIcon("assets//barravida_0.png");
		barraVida0 = referencia.getImage();

		graficos.drawImage(barraVida10, 15, 800, null);
		if (jogador.getVida() == 9){
				graficos.drawImage(barraVida9, 15, 800, null);
			} else if (jogador.getVida() == 8){
				graficos.drawImage(barraVida8, 15, 800, null);
			} else if (jogador.getVida() == 7){
				graficos.drawImage(barraVida7, 15, 800, null);
			} else if (jogador.getVida() == 6){
				graficos.drawImage(barraVida6, 15, 800, null);
			} else if (jogador.getVida() == 5){
				graficos.drawImage(barraVida5, 15, 800, null);
			} else if (jogador.getVida() == 4){
				graficos.drawImage(barraVida4, 15, 800, null);
			} else if (jogador.getVida() == 3){
				graficos.drawImage(barraVida3, 15, 800, null);
			} else if (jogador.getVida() == 2){
				graficos.drawImage(barraVida2, 15, 800, null);
			} else if (jogador.getVida() == 1){
				graficos.drawImage(barraVida1, 15, 800, null);
			} else if (jogador.getVida() <= 0){
				graficos.drawImage(barraVida0, 15, 800, null);
				jogador.setVisivel(false);
			}
	}

	public Image getBarraVida10() {
		return barraVida10;
	}

	public Image getBarraVida9() {
		return barraVida9;
	}

	public Image getBarraVida8() {
		return barraVida8;
	}

	public Image getBarraVida7() {
		return barraVida7;
	}

	public Image getBarraVida6() {
		return barraVida6;
	}

	public Image getBarraVida5() {
		return barraVida5;
	}

	public Image getBarraVida4() {
		return barraVida4;
	}

	public Image getBarraVida3() {
		return barraVida3;
	}

	public Image getBarraVida2() {
		return barraVida2;
	}

	public Image getBarraVida1() {
		return barraVida1;
	}

	public Image getBarraVida0() {
		return barraVida0;
	}
}