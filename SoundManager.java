import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.net.*;
public class SoundManager {
  private HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();
  private static SoundManager sharedInstance = new SoundManager();
  public static SoundManager getInstance() {
    return sharedInstance;
  }
  public SoundManager() {
    try {
      File soundsDirectory = new File("Sounds");
      File[] files = soundsDirectory.listFiles();
      for(File file : files) {
        if(file.getName().indexOf(".") > 0) {
          String soundName = file.getName().substring(0, file.getName().lastIndexOf("."));
          this.sounds.put(soundName, Applet.newAudioClip(file.toURI().toURL()));
        }
      }
    } catch(Exception e) {
      System.out.println(e);
    }
  }
  public void play(String soundName) {
    this.sounds.get(soundName).play();
  }
  public void loop(String soundName) {
    this.sounds.get(soundName).loop();
  }
}


