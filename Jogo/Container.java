package Jogo;

import javax.swing.JFrame;
import javax.swing.Timer;

import Jogo.Componentes.MenuPrincipal;
import Jogo.Componentes.Fases.Fase;
import Jogo.Componentes.Fases.Fase2;

public class Container extends JFrame {
    MenuPrincipal menu = new MenuPrincipal();
    public Container() {
        setTitle("Br-Callister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        add(menu);
        checkGameStatus();
    }

    private void checkGameStatus() {
        Fase fase = new Fase();
        Timer timer = new Timer(100, e -> {
            if (menu.isInicioJogo()) {
                menu.desativarKeyListener();
                remove(menu);
                add(fase);
                fase.requestFocusInWindow();
                revalidate(); 
                repaint();
            } 
            if (fase.isProximaFase()){
                Fase2 fase2 = new Fase2();
                fase.desativarKeyListener();
                remove(fase);
                add(fase2);
                fase2.requestFocusInWindow();
                revalidate(); 
                repaint();
                ((Timer) e.getSource()).stop(); 
            }
        });
        timer.start();
    }
    public static void main(String[] args) {
        new Container();
    }
}