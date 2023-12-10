package Jogo.Componentes.Objetos;

import javax.swing.*;
import java.awt.*;

public class Explosao {

    private ImageIcon[] explosaoFrames;
    private int x, y;
    private int frameAtual;
    private int tempoExplosao;

    public Explosao(int x, int y) {
        this.x = x;
        this.y = y;
        explosaoFrames = new ImageIcon[12]; 
        carregarFrames();
        frameAtual = 0;
        tempoExplosao = 72;
    }

    private void carregarFrames() {
        for (int i = 0; i < explosaoFrames.length; i++) {
            explosaoFrames[i] = new ImageIcon("explosao//explosao" + (i + 1) + ".png");
        }
    }

    public void renderizar(Graphics g) {
        if (tempoExplosao > 0) {
            explosaoFrames[frameAtual].paintIcon(null, g, x, y);
            if (tempoExplosao % 7 == 0) {
                frameAtual = (frameAtual + 1) % explosaoFrames.length;
            }
    
            tempoExplosao--;
        }
    }
    public boolean isConcluida() {
        return tempoExplosao <= 0;
    }
}