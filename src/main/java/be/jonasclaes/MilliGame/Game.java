package be.jonasclaes.MilliGame;

import be.jonasclaes.MilliGame.objects.Ball;
import be.jonasclaes.MilliGame.objects.Racket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel {

    public Ball ball = new Ball(this);
    public Racket racket = new Racket(this);
    public int gameSpeed = 2;

    private int getScore() {
        return gameSpeed - 1;
    }

    private void move() {
        ball.move();
        racket.move();
    }

    private void init() {
        gameSpeed = 2;
        ball.init();
        racket.init();
        Sound.BALL.stop();
        Sound.GAME_OVER.stop();
        Sound.BACKGROUND.loop();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ball.paint(graphics2D);
        racket.paint(graphics2D);

        graphics2D.setColor(Color.GRAY);
        graphics2D.setFont(new Font("Verdana", Font.BOLD, 30));
        graphics2D.drawString(String.valueOf(getScore()), 10, 30);
    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                racket.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racket.keyReleased(e);
            }
        });

        setFocusable(true);
    }

    public void gameOver() {
        Sound.BACKGROUND.stop();
        Sound.GAME_OVER.loop();
        Object[] options = {
                "Yes, please!",
                "No, thanks."
        };
        int n = JOptionPane.showOptionDialog(this, "Your score is: " + getScore() + ".\nDo you want to try again?", "Game Over!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (n == 1) {
            System.exit(ABORT);
        }
        init();
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("MilliGame by Jonas Claes");
        Game game = new Game();
        frame.add(game);
        frame.setSize(600, 800);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                game.racket.init();
            }
        });

        game.init();

        while (true) {
            game.move();
            game.repaint();
            Toolkit.getDefaultToolkit().sync();
            Thread.sleep(10);
        }
    }
}
