package Jogo.Componentes.Objetos;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Explosao {
    private boolean explodir = false; // Começa como falso para não explodir imediatamente
    private long startTime;
    private Image imagem;
    private int x;
    private int y;

    public Explosao(int x, int y){
        ImageIcon referencia = new ImageIcon("assets//explosao.gif");
        imagem = referencia.getImage();
        
        this.x = x;
        this.y = y;
    }

    public void iniciarExplosao() {
        explodir = true;
        startTime = System.currentTimeMillis();
    }

    public void update() {
        // Desenha a explosão por 5 segundos
        if (explodir && System.currentTimeMillis() - startTime > 3000) {
            explodir = false;
            // Você pode reinicializar startTime aqui se quiser reutilizar a explosão
            startTime = 0;
        }
    }

    public void drawExplosao(Graphics2D graficos, int x, int y) {
        if (explodir) {
            graficos.drawImage(imagem, x, y, null);
        }
    }
}