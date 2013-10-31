import java.util.*;
import java.awt.GraphicsEnvironment;
public class Main {
  public static void main(String[] args) {
    GameFrame frame = new GameFrame();
    frame.setUndecorated(true);
    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
    frame.setVisible(true);
    frame.mainLoop();
  }
}
