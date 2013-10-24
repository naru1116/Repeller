import java.awt.*;
public class GameFrame extends ExitFrame {
  private ArrayList<Car> cars;
  public GameFrame() {
    cars.add(new Car);
    addEventListener(new KeyListener({
      public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
          case KeyEvent.VK_LEFT: {
          } break;
          case KeyEvent.VK_RIGHT: {
          } break;
          case KeyEvent.VK_UP: {
          } break;
          case KeyEvent.VK_DOWN: {
          } break;
        }
      }

      public void keyReleased(KeyEvent e) {}
      public void keyTyped(KeyEvent e) {}
    }));
  }
  public void paint(Graphics g) {
    g.setColor(Color.red);
    g.fillRect();
  }
  void mainLoop() {
    try {
      while(true) {
        int width = frame.getWidth();
        int height = frame.getHeight();
        Thread.sleep(20);
        frame.repaint();
      }
    } catch(Exception e) {
      System.err.println(e);
      System.exit(0);
    }
  }
}
