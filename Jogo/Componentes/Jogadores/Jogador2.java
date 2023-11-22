package Jogo.Componentes.Jogadores;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Jogador2 extends Jogador {

    private int pontuacaoJogador2 = 0;

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

    @Override
    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_BACK_SPACE && podeAtirar) {
            tiroSimples();
            podeAtirar = false;
        }

        if (codigo == KeyEvent.VK_UP) {
            dy = -4;
        } else if (codigo == KeyEvent.VK_DOWN) {
            dy = 4;
        } else if (codigo == KeyEvent.VK_LEFT) {
            dx = -4;
        } else if (codigo == KeyEvent.VK_RIGHT) {
            dx = 4;
        }

        if (codigo == KeyEvent.VK_ENTER) {
            podeAtirar = true;
        }
    }

    @Override
    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_DOWN ||
                codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_RIGHT) {
            dy = 0;
            dx = 0;
        }

        if (codigo == KeyEvent.VK_BACK_SPACE) {
            podeAtirar = true;
        }
    }

    public void setPontuacaoJogador2(int pontuacao) {
        this.pontuacaoJogador2 = pontuacao;
    }

    public int getPontuacaoJogador2() {
        return this.pontuacaoJogador2;
    }
}
