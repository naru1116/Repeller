import java.util.*;
import java.awt.GraphicsEnvironment;


public class Main {
  public static void main(String[] args) {
    GameFrame frame = new GameFrame();
    frame.setUndecorated(true);
    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
    frame.setIgnoreRepaint(true);
    frame.setVisible(true);
    frame.setFocusable(true);
    frame.requestFocus();
    frame.mainLoop();
  }
}
