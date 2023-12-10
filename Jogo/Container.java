package Jogo;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import Jogo.Componentes.Fases.Fase1;
import Jogo.Componentes.Fases.Fase2;
import Jogo.Componentes.Fases.Fase3;
import Jogo.Componentes.Fases.Menu;

public class Container extends JFrame {
    private JPanel currentPanel;

    private int faseAtual;
    private int faseReinicio;

    private boolean reiniciaJogo = false;

    public Container() {
        setUndecorated(true);
        setTitle("Br-Callister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        faseAtual = 0;
        faseReinicio = 0;
        switchFase();

        setVisible(true);

        Cursor cursorInvisivel = Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(new byte[0], "transparente").getImage(),
                new Point(0, 0),
                "invisibleCursor"
        );
        getContentPane().setCursor(cursorInvisivel);
    }

    private void switchFase() {
        if (currentPanel != null) {
            remove(currentPanel);
        }

        switch (faseAtual) {
            case 0:
                currentPanel = new Menu(this);
                faseReinicio = 0;
                reiniciaJogo = false;
                break;
            case 1:
                currentPanel = new Fase1(this);
                faseReinicio = 1;
                reiniciaJogo = false;
                break;
            case 2:
                currentPanel = new Fase2(this);
                faseReinicio = 2;
                reiniciaJogo = false;
                break;
            case 3:
                currentPanel = new Fase3(this);
                faseReinicio = 3;
                reiniciaJogo = false;
                break;
            default:
                System.exit(0);
        }

        add(currentPanel);
        currentPanel.requestFocusInWindow();
        revalidate();
        repaint();
    }

    public void selecionarFase(int fase) {
        faseAtual = fase + 1;
    }
    public void avancarFase() {
        faseAtual++;
        switchFase();
    }
    public void iniciarJogo() {
        switchFase();
    }
    public void voltarMenuPrincipal(){
        faseAtual = 0;
        switchFase();
    }
    public void reiniciarFase() {
        faseAtual = faseReinicio;
        switchFase();
    }
     public void reiniciarJogo() {
        faseAtual = 0;
        switchFase();
    }
    public Boolean getReiniciaJogo(){
        return reiniciaJogo;
    }
    public int getFaseAtual() {
        return faseAtual;
    }
    public static void main(String[] args) {
        new Container();
    }
}