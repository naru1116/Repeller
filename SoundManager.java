import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.net.*;
public class SoundManager {
  private AudioClip bgmSound;
  private AudioClip excellentSound;
  private AudioClip badSound;
  private AudioClip repelSound;
  private AudioClip dissapearSound;
  private static SoundManager sharedInstance = new SoundManager();
  public static SoundManager getInstance() {
    return sharedInstance;
  }
  public SoundManager() {
    try {
      bgmSound = Applet.newAudioClip(new URL("file:bgm.wav"));
      excellentSound = Applet.newAudioClip(new URL("file:excellent.wav"));
      badSound = Applet.newAudioClip(new URL("file:bad.wav"));
      repelSound = Applet.newAudioClip(new URL("file:repel.wav"));
      dissapearSound = Applet.newAudioClip(new URL("file:dissapear.wav"));
    } catch(Exception e) {
      System.out.println(e);
    }
  }
  public void bgm() {
    bgmSound.loop();
  }
  public void excellent() {
    excellentSound.play();
  }
  public void bad() {
    badSound.play();
  }
  public void repel() {
    repelSound.play();
  }
  public void dissapear() {
    dissapearSound.play();
  }
}


