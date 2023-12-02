package Jogo.Componentes.Objetos;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Jogo.Componentes.Jogadores.Jogador1;
import Jogo.Componentes.Jogadores.Jogador2;

public class PowerUp {
    private Image imagem;

	private int x;
	private int y;
	private int largura;
	private int altura;
    private int codigo;
    private int velocidade;

	private boolean isVisivel;
   

    public PowerUp(int x, int y, int codigo){
        this.x = x;
        this.y = y;
        
        this.velocidade = 4;
        this.codigo = codigo;
        isVisivel = true;
    }

    public void load(){
        
        switch (codigo) {
            case 1:
                ImageIcon ref = new ImageIcon("assets//regenera.png");
                imagem = ref.getImage();
                this.largura = imagem.getWidth(null);
		        this.altura = imagem.getHeight(null);
                break;
        
            case 2:
                ref = new ImageIcon("assets//escudo.png");
                imagem = ref.getImage();
                this.largura = imagem.getWidth(null);
		        this.altura = imagem.getHeight(null);
                break;
        } 
    }

    public void update(){
        this.x -= velocidade;
		
		if(this.x < 0) {
			isVisivel = false;
		}
    }

    public void colisaoPowerUp(Jogador1 jogador){
        Rectangle formaNave = jogador.getBounds();
		Rectangle formaPowerUp = getBounds();
        switch (codigo) {
            case 1:
                if (formaNave.intersects(formaPowerUp) && jogador.isVisivel()) {
			        jogador.regeneraVida();
			        setVisivel(false);
		        } 
                break;
        
            case 2:
                if (formaNave.intersects(formaPowerUp) && jogador.isVisivel()) {
			        jogador.ganhaEscudo();
			        setVisivel(false);
		        } 
                break;
        }
    }
     public void colisaoPowerUp(Jogador2 jogador){
        Rectangle formaNave = jogador.getBounds();
		Rectangle formaPowerUp = getBounds();
        switch (codigo) {
            case 1:
                if (formaNave.intersects(formaPowerUp) && jogador.isVisivel()) {
			        jogador.regeneraVida();
			        setVisivel(false);
		        } 
                break;
        
            case 2:
                if (formaNave.intersects(formaPowerUp) && jogador.isVisivel()) {
			        jogador.ganhaEscudo();
			        setVisivel(false);
		        } 
                break;
        }
    }

    private void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
    public Rectangle getBounds() {
		return new Rectangle(x, y, largura,altura);
	}
    public Image getImagem() {
        return imagem;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isVisivel() {
        return isVisivel;
    }
} 