package Jogo.Componentes.Objetos;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Explosao {

    private ImageIcon[] explosaoFrames;
    private int x, y;
    private int frameAtual;
    private int tempoExplosao;
    private Clip somExplosao;

    public Explosao(int x, int y) {
        this.x = x;
        this.y = y;
        explosaoFrames = new ImageIcon[13];
        carregarFrames();
        frameAtual = 0;
        tempoExplosao = 60;
        inicializarSomExplosao();
    }

    private void carregarFrames() {
        for (int i = 0; i < explosaoFrames.length; i++) {
            explosaoFrames[i] = new ImageIcon("explosao//explosao" + (i + 1) + ".png");
        }
    }

    private void inicializarSomExplosao() {
        try {
            File somExplosaoFile = new File("explosao//SomExplosao.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(somExplosaoFile);
            somExplosao = AudioSystem.getClip();
            somExplosao.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renderizar(Graphics g) {
        if (tempoExplosao > 0) {
            explosaoFrames[frameAtual].paintIcon(null, g, x, y);
            if (tempoExplosao % 5 == 0) {
                frameAtual = (frameAtual + 1) % explosaoFrames.length;
            }

            tempoExplosao--;

            // Reproduzir o som quando a explosão começa
            if (tempoExplosao == 60) {
                reproduzirSomExplosao();
            }
        }
    }

    private void reproduzirSomExplosao() {
        if (somExplosao != null) {
            new Thread(() -> {
                somExplosao.stop();
                somExplosao.setFramePosition(0);
                somExplosao.start();
            }).start();
        }
    }

    public boolean isConcluida() {
        return frameAtual == explosaoFrames.length - 1 && tempoExplosao <= 0;
    }

    public void setTempoExplosao(int tempoExplosao) {
        this.tempoExplosao = tempoExplosao;
    }

    // Adicionado método para tocar o som de explosão
    public void tocarSomExplosao() {
        reproduzirSomExplosao();
    }
}
