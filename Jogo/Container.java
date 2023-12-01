package Jogo;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Jogo.Componentes.Fases.Fase1;
import Jogo.Componentes.Fases.Fase2;
import Jogo.Componentes.Fases.Fase3;
import Jogo.Componentes.Fases.Menu;

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

        faseAtual = 0;
        switchFase();
    }

    private void switchFase() {
        if (currentPanel != null) {
            remove(currentPanel);
        }

        switch (faseAtual) {
            case 0:
                currentPanel = new Menu(this);
                break;
            case 1:
                currentPanel = new Fase1(this);
                break;
            case 2:
                currentPanel = new Fase2(this);
                break;
            case 3:
                currentPanel = new Fase3(this);
                break;
            default:
                System.exit(0);
        }

        add(currentPanel);
        currentPanel.requestFocusInWindow();
        revalidate();
        repaint();
    }

    public void avancarFase() {
        faseAtual = 1;
        switchFase();
    }

    public void reiniciarJogo() {
        faseAtual = 0;
        switchFase();
    }
    public static void main(String[] args) {
        new Container();
    }
}