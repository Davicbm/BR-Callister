package Jogo.Componentes.Fases;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Jogo.Container;

public class Menu extends JPanel implements ActionListener {

    private Image fundoMenu;
    private Timer timer;

    private int opcaoSelecionada;
	private boolean nomesCapturados = false;
    public static boolean doisJogadores;
	public static String nomeJogador1 = "Jogador 1";
	public static String nomeJogador2 = "Jogador 2";

    TecladoAdapter teclado = new TecladoAdapter();
    private Container container;
    public Menu (Container container){
        this.container = container;
        setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("assets//fundo_menu.png");
		fundoMenu = referencia.getImage();

		addKeyListener(teclado);

		timer = new Timer(5, this);
		timer.start();
    }

    private static Font loadFont(String path, float size) {
		try {
			File fontFile = new File(path);
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

			return font.deriveFont(size);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			return new Font("Arial", Font.PLAIN, (int) size);
		}
	}
    
    public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;

		Font fonte = loadFont("assets//PressStart2P.ttf", 16);
        
		g.setFont(fonte);
		g.setColor(Color.WHITE);

		graficos.drawImage(fundoMenu, 0, 0, getWidth(), getHeight(), this);

		graficos.drawString("Single-Player", 650, 600);
		graficos.drawString("Two Players", 660, 650);
		graficos.drawString("E X I T", 690, 700);
		graficos.drawString(">", 630 + (opcaoSelecionada * 25) - 20, 600 + opcaoSelecionada * 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    private class TecladoAdapter implements KeyListener {
		
        @Override
		public void keyPressed(KeyEvent e) {
			int codigo = e.getKeyCode();

			switch (codigo) {
				case KeyEvent.VK_UP:
					if (opcaoSelecionada > 0) {
						opcaoSelecionada--;
					}
					break;
				case KeyEvent.VK_DOWN:
					if (opcaoSelecionada < 2) {
						opcaoSelecionada++;
					}
					break;
				case KeyEvent.VK_ENTER:
					selecionarOpcao();
					break;
				}
                repaint();
			}
            @Override
            public void keyTyped(KeyEvent e) {
               
            }
            @Override
            public void keyReleased(KeyEvent e) {
              
            }
		}
        private void capturarNomes() {
			if (doisJogadores) {
				nomeJogador1 = JOptionPane.showInputDialog(this, "Digite o nome do Jogador 1:");
				nomeJogador2 = JOptionPane.showInputDialog(this, "Digite o nome do Jogador 2:");
			} else {
				nomeJogador1 = JOptionPane.showInputDialog(this, "Digite o seu nome:");
			}
			nomesCapturados = true; 
	}

		private void selecionarOpcao() {
			
            switch (opcaoSelecionada) {
				case 0:
					doisJogadores = false;
					capturarNomes();
                    container.avancarFase();
					break;
				case 1:
					doisJogadores = true;
					capturarNomes();
                    container.avancarFase();
					break;
				case 2:
					System.exit(0);
					break;
			}
		}
}
