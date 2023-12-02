package Jogo.Componentes.Objetos;

import javax.swing.ImageIcon;

import Jogo.Componentes.Inimigos.Robo;

import java.awt.Graphics2D;
import java.awt.Image;

public class BarraVidaInimigos {
   
	private Image barraVida2;
	private Image barraVida1;

    private int x, y;
    private boolean isVisivel;
    
    public BarraVidaInimigos(int x, int y){
        this.x = x;
        this.y = y;

        isVisivel = true;
    }

	public void load(Graphics2D graficos, Robo robo){
		
		ImageIcon referencia = new ImageIcon("assets//barra_inimigo_full.png");
        barraVida2 = referencia.getImage();

		referencia = new ImageIcon("assets//barra_inimigo_meio.png");
		barraVida1 = referencia.getImage();

		graficos.drawImage(barraVida2, robo.getX() + 10, robo.getY() - 10, null);
		if (robo.getVida() == 2){
			graficos.drawImage(barraVida2, robo.getX() + 10, robo.getY() - 10, null);
			}  else if (robo.getVida() == 1){
			    graficos.drawImage(barraVida1, robo.getX() + 10, robo.getY() - 10, null);
			} else if (robo.getVida() <= 0){
                setVisivel(false);
				robo.setVisivel(false);
		    }
	}

    public void updateBarraInimigo(){
        this.x -= 5;
            
        if(this.x < 0) {
            isVisivel = false;
        }
    }

	public Image getBarraVida2() {
		return barraVida2;
	}

	public Image getBarraVida1() {
		return barraVida1;
	}

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
}