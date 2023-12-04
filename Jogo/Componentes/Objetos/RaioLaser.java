package Jogo.Componentes.Objetos;

import javax.swing.*;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;
import Jogo.Componentes.Sons.EfeitosSonoros;

import java.awt.*;

public class RaioLaser extends JPanel {
    private boolean disparaLaser = false;
    private boolean isVisivel;
    private long startTime;
    private boolean danoLaser = true;;

    public RaioLaser() {
        
    }

    public void update() {
        if (!disparaLaser) {
            // Inicia o laser após 4 segundos
            if (System.currentTimeMillis() - startTime > 10000) {
                disparaLaser = true;
                startTime = System.currentTimeMillis();
            }
        } else {
            // Desenha o laser por 5 segundos
            if (System.currentTimeMillis() - startTime > 5000) {
                somLaser();
                disparaLaser = false;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void somLaser() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarSomLaser();
	}

    public void drawRaioLaser(Graphics2D graficos) {
        if (disparaLaser) {
            // Define a cor do laser;
            graficos.setColor(Color.RED);
    
            int larguraLaser = 100; // Defina a largura desejada
            graficos.setStroke(new BasicStroke(larguraLaser));

            // Desenha uma linha representando o laser (da esquerda à direita da tela)
            graficos.drawLine(1050, 400, 0, 400); // Altere as coordenadas conforme necessário
    
            // Outras ações relacionadas ao laser (dano ao jogador, etc.) podem ser adicionadas aqui
        }
    }

    // Método para iniciar o laser a partir da classe Fase
    public void startLaser() {
        disparaLaser = true;
        startTime = System.currentTimeMillis();
    }

    // Método para parar o laser a partir da classe Fase
    public void stopLaser() {
        disparaLaser = false;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public void colisaoLaser(Jogador1 jogador){
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaLaser = getBounds();

		if (formaNave.intersects(formaLaser) && jogador.isVisivel() && danoLaser && disparaLaser) {
			if (jogador.getEscudo() > 0){
				jogador.perdeEscudo(5);
			} else {
				jogador.perdeVida(5);
			}
			setDisparaLaser(false);
            danoLaser = false;
		} 
        if (!disparaLaser){
            danoLaser = true;
        }
	}

    public void colisaoLaser(Jogador2 jogador){
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaLaser = getBounds();

		if (formaNave.intersects(formaLaser) && jogador.isVisivel() && danoLaser && disparaLaser) {
			if (jogador.getEscudo() > 0){
				jogador.perdeEscudo(5);
			} else {
				jogador.perdeVida(5);
			}
			setDisparaLaser(false);
            danoLaser = false;
		} 
        if (!disparaLaser){
            danoLaser = true;
        }
	}

    public Rectangle getBounds() {
		return new Rectangle(0, 350, 1100, 100);
	}

    public void setDisparaLaser(boolean disparaLaser) {
        this.disparaLaser = disparaLaser;
    }
}