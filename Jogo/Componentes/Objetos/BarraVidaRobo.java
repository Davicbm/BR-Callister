package Jogo.Componentes.Objetos;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import Jogo.Componentes.Inimigos.Robo;

public class BarraVidaRobo {

    private boolean isVisivel;

    private Image vidaRobo10;
    private Image vidaRobo9;
    private Image vidaRobo8;
    private Image vidaRobo7;
    private Image vidaRobo6;
    private Image vidaRobo5;
    private Image vidaRobo4;
    private Image vidaRobo3;
    private Image vidaRobo2;
    private Image vidaRobo1;

    public void loadBarraRobo(Graphics2D graficos, Robo robo) {

        ImageIcon referencia = new ImageIcon("barrasVida//vidaRobo10.png");
        vidaRobo10 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo9.png");
        vidaRobo9 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo8.png");
        vidaRobo8 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo7.png");
        vidaRobo7 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo6.png");
        vidaRobo6 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo5.png");
        vidaRobo5 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo4.png");
        vidaRobo4 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo3.png");
        vidaRobo3 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo2.png");
        vidaRobo2 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//vidaRobo1.png");
        vidaRobo1 = referencia.getImage();

        if (robo.getVida() == 10) {
            graficos.drawImage(vidaRobo10, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 9) {
            graficos.drawImage(vidaRobo9, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 8) {
            graficos.drawImage(vidaRobo8, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 7) {
            graficos.drawImage(vidaRobo7, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 6) {
            graficos.drawImage(vidaRobo6, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 5) {
            graficos.drawImage(vidaRobo5, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 4) {
            graficos.drawImage(vidaRobo4, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 3) {
            graficos.drawImage(vidaRobo3, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 2) {
            graficos.drawImage(vidaRobo2, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() == 1) {
            graficos.drawImage(vidaRobo1, robo.getX() + 20, robo.getY() - 10, null);
        } else if (robo.getVida() <= 0) {
            setVisivel(false);
            robo.setVisivel(false);
        }
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
}
