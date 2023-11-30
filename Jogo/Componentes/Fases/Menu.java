package Jogo.Componentes.Fases;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;


import Jogo.Container;

public class Menu extends JPanel implements ActionListener {

	private Clip clip;
    private Image fundoMenu;
	private Image fundoNomes;

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

		ImageIcon referencia = new ImageIcon("assets//fundomenu.png");
		fundoMenu = referencia.getImage();

		referencia = new ImageIcon("assets//blackground.png");
		fundoNomes = referencia.getImage();

		addKeyListener(teclado);

		timer = new Timer(5, this);
		timer.start();
		try {
            File audioFile = new File("assets//interstellar.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
		playSound();
	}

	public void playSound() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopSound() {
        if (clip != null) {
            clip.stop();
        }
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
			
		
		
		if(nomesCapturados == true){
			graficos.drawImage(fundoNomes, 0, 0, getWidth(), getHeight(), this);
		}
    }

	private void capturarNomes() {
			
		// Criar um JDialog sem bordas
		JDialog dialog = new JDialog();
		dialog.setSize(600, 300);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setUndecorated(true);  // Remover a barra superior
		dialog.setLocationRelativeTo(null);
	
		try {
			// Carregar a imagem de fundo
			File backgroundImageFile = new File("assets//blackground.png");
			if (!backgroundImageFile.exists()) {
				throw new IOException("Arquivo de imagem não encontrado: " + backgroundImageFile.getAbsolutePath());
			}
	
			BufferedImage backgroundImage = ImageIO.read(backgroundImageFile);
			ImageIcon backgroundIcon = new ImageIcon(backgroundImage);
	
			// Adicionar a imagem de fundo a um JLabel
			JLabel backgroundLabel = new JLabel(backgroundIcon);
			backgroundLabel.setLayout(new GridBagLayout());
			dialog.setContentPane(backgroundLabel);
	
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: " + e.getMessage());
			return;
		}
	
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
	
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setOpaque(false);
	
		JTextField textField1 = new JTextField(10);
		JTextField textField2 = new JTextField(10);
	
		// Configurar a fonte PressStart
		Font pressStartFont = loadFont("assets//PressStart2P.ttf", 16);
		textField1.setFont(pressStartFont);
	
		// Remover a cor de fundo da caixa de texto
		textField1.setOpaque(false);
		textField1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		textField1.setForeground(Color.WHITE);
	
		JLabel label1 = new JLabel("Digite o nome do Jogador 1:");
		label1.setForeground(Color.WHITE);
		label1.setFont(pressStartFont);
	
		panel.add(label1, gbc);
		gbc.gridy++;
		panel.add(textField1, gbc);
		gbc.gridy++;
	
		if (doisJogadores) {
			textField2.setFont(pressStartFont);
	
			// Remover a cor de fundo da caixa de texto
			textField2.setOpaque(false);
			textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			textField2.setForeground(Color.WHITE);
	
			JLabel label2 = new JLabel("Digite o nome do Jogador 2:");
			label2.setForeground(Color.WHITE);
			label2.setFont(pressStartFont);
			panel.add(label2, gbc);
			gbc.gridy++;
			panel.add(textField2, gbc);
		}
	
		gbc.gridy++;
	
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Voltar");
	
		// Configurar a fonte PressStart para os botões
		okButton.setFont(pressStartFont);
		cancelButton.setFont(pressStartFont);
		gbc.gridy++;
	
		okButton.addActionListener(e -> {
			nomeJogador1 = textField1.getText();
			if (doisJogadores) {
				nomeJogador2 = textField2.getText();
			}
			stopSound();
			container.avancarFase();
			
			dialog.dispose();
		});
	
		cancelButton.addActionListener(e -> {
			container.reiniciarJogo();
			dialog.dispose();
		});
	
		panel.add(okButton, gbc);
		gbc.gridy++;
		panel.add(cancelButton, gbc);
		// Centralizar o painel no diálogo
		int panelWidth = 300;
		int panelHeight = 200;
		int dialogWidth = dialog.getWidth();
		int dialogHeight = dialog.getHeight();
		panel.setBounds((dialogWidth - panelWidth) / 2, (dialogHeight - panelHeight) / 2, panelWidth, panelHeight);
	
		dialog.add(panel);
		dialog.setVisible(true);
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

		private void selecionarOpcao() {
            switch (opcaoSelecionada) {
				case 0:
					doisJogadores = false;
					capturarNomes();
					break;
				case 1:
					doisJogadores = true;
					capturarNomes();
					break;
				case 2:
					System.exit(0);
					break;
			}
		}
}
