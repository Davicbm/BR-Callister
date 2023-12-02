package Jogo.Componentes.Objetos;

import javax.swing.ImageIcon;

import Jogo.Componentes.Inimigos.Robo;

import java.awt.Graphics2D;
import java.awt.Image;

public class BarraVidaRobos {

    private Image robosVida2;
    private Image robosVida1;

    private int x, y;
    private boolean isVisivel;

    public BarraVidaRobos(int x, int y) {
        this.x = x;
        this.y = y;

        isVisivel = true;
    }

    public void loadBarrasRobos(Graphics2D graficos, Robo robo) {

        ImageIcon referencia = new ImageIcon("barrasVida//robosVida2.png");
        robosVida2 = referencia.getImage();

        referencia = new ImageIcon("barrasVida//robosVida1.png");
        robosVida1 = referencia.getImage();

        if (robo.getVida() == 2) {
            graficos.drawImage(robosVida2, robo.getX() + 10, robo.getY() - 10, null);
        } else if (robo.getVida() == 1) {
            graficos.drawImage(robosVida1, robo.getX() + 10, robo.getY() - 10, null);
        } else if (robo.getVida() <= 0) {
            setVisivel(false);
            robo.setVisivel(false);
        }
    }

    public void updateBarraInimigo() {
        this.x -= 5;

        if (this.x < 0) {
            isVisivel = false;
        }
    }

    public Image getRobosVida2() {
        return robosVida2;
    }

    public Image getRobosVida1() {
        return robosVida1;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
}