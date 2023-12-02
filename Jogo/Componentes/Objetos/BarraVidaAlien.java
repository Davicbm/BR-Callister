package Jogo.Componentes.Objetos;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import Jogo.Componentes.Inimigos.Alien;

public class BarraVidaAlien {

    private boolean isVisivel;

    private Image alienVida15;
    private Image alienVida14;
    private Image alienVida13;
    private Image alienVida12;
    private Image alienVida11;
    private Image alienVida10;
    private Image alienVida9;
    private Image alienVida8;
    private Image alienVida7;
    private Image alienVida6;
    private Image alienVida5;
    private Image alienVida4;
    private Image alienVida3;
    private Image alienVida2;
    private Image alienVida1;

    public void loadBarraAlien(Graphics2D graficos, Alien alien) {

        ImageIcon referencia = new ImageIcon("barrasVida//alienVida15.png");
        alienVida15 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida14.png");
        alienVida14 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida13.png");
        alienVida13 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida12.png");
        alienVida12 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida11.png");
        alienVida11 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida10.png");
        alienVida10 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida9.png");
        alienVida9 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida8.png");
        alienVida8 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida7.png");
        alienVida7 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida6.png");
        alienVida6 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida5.png");
        alienVida5 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida4.png");
        alienVida4 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida3.png");
        alienVida3 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida2.png");
        alienVida2 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//alienVida1.png");
        alienVida1 = referencia.getImage();

        if (alien.getVida() == 15) {
            graficos.drawImage(alienVida15, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 14) {
            graficos.drawImage(alienVida14, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 13) {
            graficos.drawImage(alienVida13, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 12) {
            graficos.drawImage(alienVida12, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 11) {
            graficos.drawImage(alienVida11, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 10) {
            graficos.drawImage(alienVida10, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 9) {
            graficos.drawImage(alienVida9, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 8) {
            graficos.drawImage(alienVida8, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 7) {
            graficos.drawImage(alienVida7, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 6) {
            graficos.drawImage(alienVida6, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 5) {
            graficos.drawImage(alienVida5, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 4) {
            graficos.drawImage(alienVida4, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 3) {
            graficos.drawImage(alienVida3, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 2) {
            graficos.drawImage(alienVida2, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() == 1) {
            graficos.drawImage(alienVida1, alien.getX() + 50, alien.getY() - 10, null);
        } else if (alien.getVida() <= 0) {
            setVisivel(false);
            alien.setVisivel(false);
        }
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
}
