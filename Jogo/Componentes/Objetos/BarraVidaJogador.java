package Jogo.Componentes.Objetos;

import javax.swing.ImageIcon;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;

import java.awt.Graphics2D;
import java.awt.Image;

public class BarraVidaJogador {

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

	private Image barraEscudoCompleto;
	private Image barraEscudoMetade;
	private Image morte;

	public void paintBarraVida(Graphics2D graficos, Jogador1 jogador) {

		ImageIcon referencia = new ImageIcon("barrasVida//barravida_10.png");
		barraVida10 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_9.png");
		barraVida9 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_8.png");
		barraVida8 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_7.png");
		barraVida7 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_6.png");
		barraVida6 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_5.png");
		barraVida5 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_4.png");
		barraVida4 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_3.png");
		barraVida3 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_2.png");
		barraVida2 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_1.png");
		barraVida1 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_0.png");
		barraVida0 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//escudoCompleto.png");
		barraEscudoCompleto = referencia.getImage();

		referencia = new ImageIcon("barrasVida//escudoMetade.png");
		barraEscudoMetade = referencia.getImage();

		referencia = new ImageIcon("barrasVida//caveira.png");
		morte = referencia.getImage();

		graficos.drawImage(barraVida10, 70, 40, null);
		if (jogador.getVida() == 9) {
			graficos.drawImage(barraVida9, 70, 40, null);
		} else if (jogador.getVida() == 8) {
			graficos.drawImage(barraVida8, 70, 40, null);
		} else if (jogador.getVida() == 7) {
			graficos.drawImage(barraVida7, 70, 40, null);
		} else if (jogador.getVida() == 6) {
			graficos.drawImage(barraVida6, 70, 40, null);
		} else if (jogador.getVida() == 5) {
			graficos.drawImage(barraVida5, 70, 40, null);
		} else if (jogador.getVida() == 4) {
			graficos.drawImage(barraVida4, 70, 40, null);
		} else if (jogador.getVida() == 3) {
			graficos.drawImage(barraVida3, 70, 40, null);
		} else if (jogador.getVida() == 2) {
			graficos.drawImage(barraVida2, 70, 40, null);
		} else if (jogador.getVida() == 1) {
			graficos.drawImage(barraVida1, 70, 40, null);
		} else if (jogador.getVida() <= 0) {
			graficos.drawImage(barraVida0, 70, 40, null);
			jogador.setVisivel(false);
		}

		if (jogador.getEscudo() == 2){
			graficos.drawImage(barraEscudoCompleto, 70, 40, null);
		}
		if (jogador.getEscudo() == 1){
			graficos.drawImage(barraEscudoMetade, 70, 40, null);
		}
		if (jogador.getVida() <= 0){
			graficos.drawImage(morte, 70, 40, null);
		}
	}

	public void paintBarraVida(Graphics2D graficos, Jogador2 jogador) {

		ImageIcon referencia = new ImageIcon("barrasVida//barravida_10.png");
		barraVida10 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_9.png");
		barraVida9 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_8.png");
		barraVida8 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_7.png");
		barraVida7 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_6.png");
		barraVida6 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_5.png");
		barraVida5 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_4.png");
		barraVida4 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_3.png");
		barraVida3 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_2.png");
		barraVida2 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_1.png");
		barraVida1 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//barravida_0.png");
		barraVida0 = referencia.getImage();

		referencia = new ImageIcon("barrasVida//escudoCompleto.png");
		barraEscudoCompleto = referencia.getImage();

		referencia = new ImageIcon("barrasVida//escudoMetade.png");
		barraEscudoMetade = referencia.getImage();

		referencia = new ImageIcon("barrasVida//caveira.png");
		morte = referencia.getImage();

		graficos.drawImage(barraVida10, 295, 40, null);
		if (jogador.getVida() == 9) {
			graficos.drawImage(barraVida9, 295, 40, null);
		} else if (jogador.getVida() == 8) {
			graficos.drawImage(barraVida8, 295, 40, null);
		} else if (jogador.getVida() == 7) {
			graficos.drawImage(barraVida7, 295, 40, null);
		} else if (jogador.getVida() == 6) {
			graficos.drawImage(barraVida6, 295, 40, null);
		} else if (jogador.getVida() == 5) {
			graficos.drawImage(barraVida5, 295, 40, null);
		} else if (jogador.getVida() == 4) {
			graficos.drawImage(barraVida4, 295, 40, null);
		} else if (jogador.getVida() == 3) {
			graficos.drawImage(barraVida3, 295, 40, null);
		} else if (jogador.getVida() == 2) {
			graficos.drawImage(barraVida2, 295, 40, null);
		} else if (jogador.getVida() == 1) {
			graficos.drawImage(barraVida1, 295, 40, null);
		} else if (jogador.getVida() <= 0) {
			graficos.drawImage(barraVida0, 295, 40, null);
			jogador.setVisivel(false);
		}

		if (jogador.getEscudo() == 2){
			graficos.drawImage(barraEscudoCompleto, 295, 40, null);
		}
		if (jogador.getEscudo() == 1){
			graficos.drawImage(barraEscudoMetade, 295, 40, null);
		}
		if (jogador.getVida() <= 0){
			graficos.drawImage(morte, 295, 40, null);
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