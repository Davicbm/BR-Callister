package Jogo.Componentes.Jogadores;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Jogador1 extends Jogador {

	public static int pontuacaoJogador1 = 0;
	public static int pontuacaoAnteriorJogador1;

	public Jogador1() {
		super(300, 150);
	}

	@Override
	public void load() {
		ImageIcon referencia = new ImageIcon("assets//br-callister.gif");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
	
		if (codigo == KeyEvent.VK_SPACE && podeAtirar) {
			tiroSimples();
			podeAtirar = false;
		}
	
		if (codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_S) {
			dy = (codigo == KeyEvent.VK_W) ? -3 : 3;
		} else if (codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_D) {
			dx = (codigo == KeyEvent.VK_A) ? -3 : 3;
		}
	}
	
	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
	
		if (codigo == KeyEvent.VK_W || codigo == KeyEvent.VK_S) {
			dy = 0;
		} else if (codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_D) {
			dx = 0;
		}
	
		if (codigo == KeyEvent.VK_SPACE) {
			podeAtirar = true;
		}
	}
}