package Jogo.Componentes.Objetos;

import javax.swing.*;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;

import java.awt.*;

public class RaioLaser {
    private boolean disparaLaser = false;
    private long startTime;
    private boolean danoLaser = true;
    private boolean isVisivel;

    private int largura;
    private int altura;
    private Image raio;

    public RaioLaser() {
        ImageIcon referencia = new ImageIcon("assets//raiolaser.png");
        raio = referencia.getImage();

        this.isVisivel = true;
        this.largura = raio.getWidth(null);
		this.altura = raio.getHeight(null);
    }

    public void update() {
        if (!disparaLaser) {
            if (System.currentTimeMillis() - startTime > 7000) {
                disparaLaser = true;
                startTime = System.currentTimeMillis();
            }
        } else {
            if (System.currentTimeMillis() - startTime > 5000) {
                disparaLaser = false;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void drawRaioLaser(Graphics2D graficos) {
        if (disparaLaser) {
            graficos.drawImage(raio, 0, 400, null);
        }
    }

    public void startLaser() {
        disparaLaser = true;
        startTime = System.currentTimeMillis();
    }

    public void stopLaser() {
        disparaLaser = false;
    }

    public void colisaoLaser(Jogador1 jogador) {
        Rectangle formaNave = jogador.getBounds();
        Rectangle formaLaser = getBounds();

        if (formaNave.intersects(formaLaser) && jogador.isVisivel() && danoLaser && disparaLaser) {
            if (jogador.getEscudo() > 0) {
                jogador.perdeEscudo(5);
            } else {
                jogador.perdeVida(5);
            }
            danoLaser = false;
        }
        if (!disparaLaser) {
            danoLaser = true;
        }
    }

    public void colisaoLaser(Jogador2 jogador) {
        Rectangle formaNave = jogador.getBounds();
        Rectangle formaLaser = getBounds();

        if (formaNave.intersects(formaLaser) && jogador.isVisivel() && danoLaser && disparaLaser) {
            if (jogador.getEscudo() > 0) {
                jogador.perdeEscudo(5);
            } else {
                jogador.perdeVida(5);
            }
            danoLaser = false;
        }
        if (!disparaLaser) {
            danoLaser = true;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(0, 400, largura, altura);
    }

    public boolean isVisivel() {
        return isVisivel;
    }
    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
}