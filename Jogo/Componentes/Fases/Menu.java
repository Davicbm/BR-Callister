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
import java.awt.KeyboardFocusManager;
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
import javax.swing.KeyStroke;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
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

    public Menu(Container container) {
        this.container = container;
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("planosFundo//fundomenu.png");
        fundoMenu = referencia.getImage();

        referencia = new ImageIcon("planosFundo//blackground.png");
        fundoNomes = referencia.getImage();

        addKeyListener(teclado);

        timer = new Timer(5, this);
        timer.start();

        try {
            File audioFile2 = new File("assets//interstellar.wav");
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);

            clip = AudioSystem.getClip();
            clip.open(audioStream2);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        startSound();
    }

    public void startSound() {
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

        if (!nomesCapturados) {
            graficos.drawImage(fundoMenu, 0, 0, getWidth(), getHeight(), this);
        }

        graficos.drawString("Single-Player", 650, 600);
        graficos.drawString("Two Players", 660, 650);
        graficos.drawString("E X I T", 690, 700);
        graficos.drawString(">", 630 + (opcaoSelecionada * 25) - 20, 600 + opcaoSelecionada * 50);

        if (nomesCapturados) {
            graficos.drawImage(fundoNomes, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void capturarNomes() {
        JDialog dialog = new JDialog();

        dialog.setSize(600, 300);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setUndecorated(true);
        dialog.setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("planosFundo//blackground.png");

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());
        dialog.setContentPane(backgroundLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);
        dialog.add(panel);

        JTextField textField1 = new JTextField(10);
        JTextField textField2 = new JTextField(10);

        Font pressStartFont = loadFont("assets//PressStart2P.ttf", 16);
        textField1.setFont(pressStartFont);
        textField2.setFont(pressStartFont);

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

        okButton.setFont(pressStartFont);
        cancelButton.setFont(pressStartFont);
        gbc.gridy++;

        okButton.addActionListener(e -> {
            nomeJogador1 = textField1.getText().isEmpty() ? "Player 1" : textField1.getText();
            if (doisJogadores) {
                nomeJogador2 = textField2.getText().isEmpty() ? "Player 2" : textField2.getText();
            }
            stopSound();
            container.avancarFase();
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> {
            dialog.dispose();
        });

        panel.add(okButton, gbc);
        gbc.gridy++;
        panel.add(cancelButton, gbc);

        textField1.setFocusable(true);
        textField1.requestFocusInWindow();
        textField1.setFocusTraversalKeysEnabled(false);
        textField2.setFocusTraversalKeysEnabled(false);

        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "focusNextComponent");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "focusPreviousComponent");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterPressed");

        actionMap.put("focusNextComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });

        actionMap.put("focusPreviousComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
            }
        });

        actionMap.put("enterPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (okButton.isFocusOwner()) {
                    okButton.doClick();
                } else if (cancelButton.isFocusOwner()) {
                    nomesCapturados = false;
                    cancelButton.doClick();
                }
            }
        });

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
                nomesCapturados = true;
                capturarNomes();
                break;
            case 1:
                doisJogadores = true;
                nomesCapturados = true;
                capturarNomes();
                break;
            case 2:
                System.exit(0);
                break;
        }
    }
}
