package Jogo.Componentes.Sons;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EfeitosSonoros  extends JPanel {

	public EfeitosSonoros() {
		setDoubleBuffered(true);
	}

    public void tocarTiro() {
		try {
			URL soundFile = getClass().getResource("SomTiro.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());

			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception e) {
			JOptionPane.showInputDialog(this, e);
		}
	}


	public void tocarSomExplosao() {
		try {
			URL soundFile = getClass().getResource("SomExplosao.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());

			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception e) {
			JOptionPane.showInputDialog(this, e);
		}
	}

	public void tocarSomRugido() {
		try {
			URL soundFile = getClass().getResource("rugido.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception e) {
			JOptionPane.showInputDialog(this, e);
		}
	}

	public void tocarSomLaser() {
		try {
			URL soundFile = getClass().getResource("laser.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception e) {
			JOptionPane.showInputDialog(this, e);
		}
	}

	public void tocarSomAlerta() {
		try {
			URL soundFile = getClass().getResource("alerta.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception e) {
			JOptionPane.showInputDialog(this, e);
		}
	}
}