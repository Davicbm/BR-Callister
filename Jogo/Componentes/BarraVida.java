package Jogo.Componentes;

import javax.swing.ImageIcon;

import Jogo.Componentes.Jogadores.Jogador1;

import java.awt.Graphics;
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

	public BarraVida(){
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

	
}