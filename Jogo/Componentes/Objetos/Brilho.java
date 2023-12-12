package Jogo.Componentes.Objetos;

import javax.swing.*;
import java.awt.*;

public class Brilho {

    private ImageIcon[] brilhoFrames;
    private int x, y;
    private int frameAtual;
    private int tempoBrilho;

    public Brilho(int x, int y) {
        this.x = x;
        this.y = y;

        brilhoFrames = new ImageIcon[8];
        carregarFrames();
        frameAtual = 0;
        tempoBrilho = 100;
    }

    private void carregarFrames() {
        for (int i = 0; i < brilhoFrames.length; i++) {
            brilhoFrames[i] = new ImageIcon("brilho//frame-" + (i + 1) + ".png");
        }
    }

    public void renderizar(Graphics g) {
        brilhoFrames[frameAtual].paintIcon(null, g, x, y);
        if (tempoBrilho % 4 == 0) {
            frameAtual = (frameAtual + 1) % brilhoFrames.length;
        }
        tempoBrilho--;
    }

    public boolean isConcluida() {
        return frameAtual == brilhoFrames.length - 1 && tempoBrilho <= 0;
    }

    public void setTempoBrilho(int tempoBrilho) {
        this.tempoBrilho = tempoBrilho;
    }
}
