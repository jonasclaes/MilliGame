package be.jonasclaes.MilliGame.objects;

import be.jonasclaes.MilliGame.Game;
import be.jonasclaes.MilliGame.Sound;

import java.awt.*;

public class Ball {
    private static final int DIAMETER = 30;

    // Constructor variables
    private Game game;

    // Ball properties
    private int ballX = 0;
    private int ballY = 0;
    private int speedX = 1;
    private int speedY = 1;

    public Ball(Game game) {
        this.game = game;
    }

    public void init() {
        ballX = 0;
        ballY = 0;
        speedX = 1;
        speedY = 1;
    }

    public void move() {
        if (ballX + speedX < 0)
            speedX = game.gameSpeed;
        else if (ballX + speedX > game.getWidth() - DIAMETER)
            speedX = -game.gameSpeed;
        else if (ballY + speedY < 0)
            speedY = game.gameSpeed;
        else if (ballY + speedY > game.getHeight() - DIAMETER)
            game.gameOver();
        else if (collision()) {
            speedY = -game.gameSpeed;
            ballY = game.racket.getTopY() - DIAMETER;
            game.gameSpeed++;
            Sound.BALL.play();
        }

        ballX = ballX + speedX;
        ballY = ballY + speedY;
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.fillOval(ballX, ballY, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(ballX, ballY, DIAMETER, DIAMETER);
    }

    private boolean collision() {
        return game.racket.getBounds().intersects(getBounds());
    }
}
