package Main;
import Entidades.Nave;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PainelJogo extends JPanel implements Runnable{
    // Variaveis:
    final int originalSkinSize = 16; //tamanho dos sprites: 16x16
    int scale = 6;
    
    public final int skinSize = originalSkinSize * scale; // 64x64
    public final int skinSizeHeight = skinSize;
    public final int skinSizeWidth = originalSkinSize * scale * 2;
    final int maxColuna = 16;
    final int maxLinha = 12;
    final int larguraTela = skinSize * maxColuna; // 1536 pixeis
    final int comprimentoTela = skinSize * maxLinha; // 1152 pixeis
    
    //FPS
    int fps = 60;

    //Instancias:
    Controles tecla = new Controles();
    Thread gameThread;
    Nave nave = new Nave(this, tecla);

    //Construtor do Painel;
    public PainelJogo(){
        this.setPreferredSize(new Dimension(larguraTela, comprimentoTela));
        //this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(tecla);
        this.setFocusable(true);
    }

    //Metodo de inicio do tempo do jogo:
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Metodo de rodar o jogo:
    @Override
    public void run() {

        double intervaloDesenho = 1000000000/fps;
        double delta = 0;
        long tempoAnterior = System.nanoTime();
        long tempoAtual;

        while(gameThread != null){
            tempoAtual = System.nanoTime();
            //Delta cria um sistema de FPS para o jogo:
            delta += (tempoAtual - tempoAnterior) / intervaloDesenho;
            tempoAnterior = tempoAtual;
            if(delta >= 1){
                //Atualiza informações:
                update();
                //Desenha na tela as informações atualizadas:
                repaint();
                delta--;
            }
        }
    }

    //Metodo de atualizar a localizacao da nave:
    public void update(){
        nave.update();
    }

    //Metodo de pintar a nave em sua nova localizacao:
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        nave.draw(g2);
        g2.dispose();
    }
}
