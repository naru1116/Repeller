import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GameFrame extends ExitFrame {
  private ArrayList<Car> cars = new ArrayList<Car>();
  private Car playerCar = new Car();
  public GameFrame() {
    cars.add(playerCar);
    addKeyListener(new KeyListener(){
      public void keyPressed(KeyEvent e) {
        double delta = 0.7;
        switch(e.getKeyCode()) {
          case KeyEvent.VK_LEFT: {
            playerCar.dx -= delta;
          } break;
          case KeyEvent.VK_RIGHT: {
            playerCar.dx += delta;
          } break;
          case KeyEvent.VK_UP: {
            playerCar.dy -= delta;
          } break;
          case KeyEvent.VK_DOWN: {
            playerCar.dy += delta;
          } break;
        }
      }

      public void keyReleased(KeyEvent e) {}
      public void keyTyped(KeyEvent e) {}
    });
  }
  public void paint(Graphics g) {
    int width = getWidth();
    int height = getHeight();
    for(Car car : cars) {
      car.update(g, width, height);
    }
  }
  void mainLoop() {
    try {
      while(true) {
        Thread.sleep(20);
        repaint();
      }
    } catch(Exception e) {
      System.err.println(e);
      System.exit(0);
    }
  }
}
