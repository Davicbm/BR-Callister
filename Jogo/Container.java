package Jogo;

import javax.swing.JFrame;

import Jogo.Componentes.Fase;

public class Container extends JFrame {

	public Container() {
		add(new Fase());
		setTitle("Br-Callister");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Container();
	}
}