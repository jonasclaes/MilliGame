package be.jonasclaes.MilliGame.objects;

import be.jonasclaes.MilliGame.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Racket {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 10;

    // Constructor variables
    private Game game;

    // Racket properties
    private int racketX = 0;
    private int speedX = 0;
    private int racketY = 0;

    public Racket(Game game) {
        this.game = game;
    }

    public void init() {
        racketX = (game.getWidth() - WIDTH) / 2;
        racketY = game.getHeight() - HEIGHT - 20;
        speedX = 0;
    }

    public void move() {
        if (racketX + speedX > 0 && racketX + speedX < game.getWidth() - WIDTH)
            racketX = racketX + speedX;
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.fillRect(racketX, racketY, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        speedX = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            speedX = -(game.gameSpeed + 1);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            speedX = game.gameSpeed + 1;
    }

    public Rectangle getBounds() {
        return new Rectangle(racketX, racketY, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return racketY;
    }
}
