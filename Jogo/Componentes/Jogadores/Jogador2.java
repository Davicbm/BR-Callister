package Jogo.Componentes.Jogadores;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Jogador2 extends Jogador {

    public static int pontuacaoJogador2 = 0;

    public Jogador2() {
        super(300, 650);
    }

    @Override
    public void load() {
        ImageIcon referencia = new ImageIcon("assets//br-callister2.gif");
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
	
		if (codigo == KeyEvent.VK_BACK_SPACE && podeAtirar) {
			tiroSimples();
			podeAtirar = false;
		}
	
		if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN || 
			codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) {
			definirDirecao(codigo);
		}
	
		if (codigo == KeyEvent.VK_ENTER) {
			podeAtirar = true;
		}
	}
	
	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
	
		if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN || 
			codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) {
			resetarDirecao(codigo);
		}
	
		if (codigo == KeyEvent.VK_BACK_SPACE) {
			podeAtirar = true;
		}
	}
	
	private void definirDirecao(int codigo) {
		if (codigo == KeyEvent.VK_UP) {
			dy = -3;
		} else if (codigo == KeyEvent.VK_DOWN) {
			dy = 3;
		} else if (codigo == KeyEvent.VK_LEFT) {
			dx = -3;
		} else if (codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
	}
	
	private void resetarDirecao(int codigo) {
		if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		} else if (codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}
}