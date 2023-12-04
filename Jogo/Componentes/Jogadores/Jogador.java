package Jogo.Componentes.Jogadores;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Jogador {

    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected Image imagem;
    protected int altura;
    protected int largura;
    protected boolean isVisivel;
    protected int vida;
    protected int escudo;

    protected List<TiroNave> tiros;
    protected boolean podeAtirar = true;
    protected long tempoUltimoTiro = System.currentTimeMillis();
    protected long intervaloTiros = 200;

    public Jogador(int x, int y) {
        this.x = x;
        this.y = y;
        this.vida = 10;
        this.escudo = 0;
        this.isVisivel = true;

        tiros = new ArrayList<>();
    }

    public abstract void load();

    public void update() {
        if (x + dx >= 0 && x + dx + largura / 2 <= 1000) {
            x += dx;
        }
        if (y + dy >= 0 && y + dy + altura <= 850) {
            y += dy;
        }
    }

    public void tiroSimples() {
        long tempoAtual = System.currentTimeMillis();
        
        if (tempoAtual - tempoUltimoTiro >= intervaloTiros) {
            this.tiros.add(new TiroNave(x + largura, y + (altura / 2)));
            tempoUltimoTiro = tempoAtual;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_SPACE && podeAtirar) {
            tiroSimples();
            podeAtirar = false;
        }
    }

    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE) {
            podeAtirar = true;
        }
    }

    public void atirar() {
        List<TiroNave> tiros = getTiros();

        for (int i = 0; i < tiros.size(); i++) {
            TiroNave m = tiros.get(i);
            if (m.isVisivel()) {
                m.update();
            } else {
                tiros.remove(i);
            }
        }
    }

    public void drawTiroNave(Graphics2D graficos) {
        List<TiroNave> tiros = getTiros();
        for (int i = 0; i < tiros.size(); i++) {
            TiroNave m = tiros.get(i);
            m.load();
            graficos.drawImage(m.getImagem(), m.getX(), m.getY() + 10, null);
        }
    }

    public void perdeVida(int dano) {
        this.vida = vida - dano;
    }
    public void perdeEscudo(int dano){
        this.escudo = escudo - dano; 
        int danoExcedente = this.escudo;
        if (this.escudo < 0){
            this.escudo = 0;
            this.vida = vida + danoExcedente;
        }
    }
    public void regeneraVida(){
        if (vida <= 8){
            this.vida = vida + 2;
        } else if (vida == 9){
            this.vida = vida + 1;
        }
    }

    public void ganhaEscudo(){
        this.escudo = escudo + (2 - escudo);
    }

    public List<TiroNave> getTiros() {
        return tiros;
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getVida() {
        return vida;
    }
    public int getEscudo(){
        return escudo;
    }
}