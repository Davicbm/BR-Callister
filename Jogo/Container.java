package Jogo;

import javax.swing.JFrame;
import javax.swing.Timer;

import Jogo.Componentes.Fase;
import Jogo.Componentes.MenuPrincipal;

public class Container extends JFrame {
	MenuPrincipal menu = new MenuPrincipal();

	public Container() {
		setTitle("Br-Callister");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

		add(menu);
		checkGameStatus();
    }

    private void checkGameStatus() {
        Timer timer = new Timer(100, e -> {
            if (menu.isInicioJogo()) {
                remove(menu);
                add(new Fase());
                revalidate(); // Atualiza o JFrame para refletir as mudanças
                repaint(); // Redesenha o JFrame
                ((Timer) e.getSource()).stop(); // Para o timer, já que o jogo começou
            }
        });
        timer.start();
    }
	public static void main(String[] args) {
		new Container();
	}
}