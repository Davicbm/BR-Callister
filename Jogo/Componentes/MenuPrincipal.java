package Jogo.Componentes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuPrincipal extends JPanel implements ActionListener, KeyListener {
    private Image fundo;
    private boolean inicio = false;
    private boolean inicioJogo = false;

    public MenuPrincipal(){
        setFocusable(true);
		setDoubleBuffered(true);
        addKeyListener(this);

		ImageIcon referencia = new ImageIcon("assets//fundo_menu.png");
		fundo = referencia.getImage();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setInicioJogo(true);
    }

    public void setInicioJogo(boolean inicioJogo){
        this.inicioJogo = inicioJogo;
    }

    public boolean isInicioJogo(){
        return this.inicioJogo;
    }

    @Override
    public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_ENTER){
            inicioJogo = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não é necessário implementar nada aqui, mas é obrigatório por conta da interface KeyListener
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Não é necessário implementar nada aqui, mas é obrigatório por conta da interface KeyListener
    }
}