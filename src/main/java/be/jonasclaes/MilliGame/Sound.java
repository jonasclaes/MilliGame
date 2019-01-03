package be.jonasclaes.MilliGame;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
    public static final AudioClip GAME_OVER = Applet.newAudioClip(Sound.class.getResource("game_over.wav"));
    public static final AudioClip BACKGROUND = Applet.newAudioClip(Sound.class.getResource("background.wav"));
}
