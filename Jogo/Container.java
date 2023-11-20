package Jogo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Jogo.Componentes.Fases.Fase;
import Jogo.Componentes.Fases.Fase2;
import Jogo.Componentes.Fases.Fase3;

public class Container extends JFrame {
    private JPanel currentPanel;
    private int faseAtual;


    public Container() {
        setTitle("Br-Callister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        faseAtual = 1;
        switchFase();
    }

    private void switchFase() {
        if (currentPanel != null) {
            remove(currentPanel);
        }

        switch (faseAtual) {
            case 1:
                currentPanel = new Fase(this);
                break;
            case 2:
                currentPanel = new Fase2(this);
                break;
            case 3:
                currentPanel = new Fase3(this);
                break;
            // Adicione mais cases conforme você adiciona novas fases
            default:
                // Se não houver mais fases, encerre o jogo ou mostre uma tela de vitória, por exemplo
                System.exit(0);
        }

        add(currentPanel);
        currentPanel.requestFocusInWindow();
        revalidate();
        repaint();
    }

    public void avancarFase() {
        faseAtual++;
        switchFase();
    }

    public void reiniciarJogo() {
        faseAtual = 1;
        switchFase();
    }

    public static void main(String[] args) {
        new Container();
    }
}