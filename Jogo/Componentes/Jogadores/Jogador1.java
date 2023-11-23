package Jogo.Componentes.Jogadores;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Jogador1 extends Jogador {

	private int pontuacaoJogador1 = 0;

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
			dy = (codigo == KeyEvent.VK_W) ? -4 : 4;
		} else if (codigo == KeyEvent.VK_A || codigo == KeyEvent.VK_D) {
			dx = (codigo == KeyEvent.VK_A) ? -4 : 4;
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

	public void setPontuacaoJogador1(int pontuacao) {
		this.pontuacaoJogador1 += pontuacao;
	}

	public int getPontuacaoJogador1() {
		return this.pontuacaoJogador1;
	}
}