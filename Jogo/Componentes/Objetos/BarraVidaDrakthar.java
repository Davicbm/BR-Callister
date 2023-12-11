package Jogo.Componentes.Objetos;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import Jogo.Componentes.Inimigos.Drakthar;

public class BarraVidaDrakthar {
    private Color corVida;
    private Color corBorda;

    public BarraVidaDrakthar() {
        corVida = new Color(178, 34, 34);
        corBorda = new Color(28,28,28);
    }

    public Font loadFont(String path, float size) {
        try {
            File fontFile = new File(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            return font.deriveFont(size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }

    public void paintBarraVida(Graphics2D graficos, Drakthar drakthar) {
        graficos.setColor(corBorda);

        int larguraBarra2 = 40;
        graficos.setStroke(new BasicStroke(larguraBarra2));

        graficos.drawLine(290, 750, 1310, 750);
        graficos.setColor(corVida);

        int larguraBarra = 30;
        graficos.setStroke(new BasicStroke(larguraBarra));

        graficos.drawLine(300, 750, 1300 - drakthar.getDano() * 10, 750);

        Font fonte = loadFont("assets//PressStart2P.ttf", 16);

        graficos.setFont(fonte);
        graficos.setColor(Color.YELLOW);

        graficos.drawString("DRAKTHAR", 700, 725);
    }
}
