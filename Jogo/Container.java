package Jogo;

import javax.swing.JFrame;
import javax.swing.Timer;

import Jogo.Componentes.Fase;
import Jogo.Componentes.MenuPrincipal;

public class Container extends JFrame {
    MenuPrincipal menu = new MenuPrincipal();
    Fase fase = new Fase(); // Adiciona uma instância de Fase

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
        Timer timer = new Timer(100, e -> {
            if (menu.isInicioJogo()) {
                menu.desativarKeyListener();
                remove(menu);
                add(fase);
                fase.requestFocusInWindow();
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