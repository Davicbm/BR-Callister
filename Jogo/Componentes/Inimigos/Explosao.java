package Jogo.Componentes.Inimigos;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Explosao {
    private int x, y;
    private Image imagem;

    public Explosao(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon referencia = new ImageIcon("assets//explosion1.gif");
        imagem = referencia.getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
}


