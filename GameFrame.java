import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GameFrame extends ExitFrame {
  private ArrayList<Car> enemyCars = new ArrayList<Car>();
  private Car playerCar = new Car(300, 300, Color.red);
  private int controlX, controlY;
  public GameFrame(int width, int height) {
   setSize(width, height);
   Random random = new Random();
   for(int i = 0; i < 3; i++) {
     int x = random.nextInt((int)(width - playerCar.width));
     int y = random.nextInt((int)(height - playerCar.height));
     int dx = random.nextInt(20);
     int dy = random.nextInt(20);
     enemyCars.add(new Car(x, y, dx, dy, Color.blue));
   }
    /*
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
    */
    addMouseMotionListener(new MouseMotionListener() {
      public void mouseDragged(MouseEvent e) {
        System.out.println("Dragged" + e.getX());
      }
      public void mouseMoved(MouseEvent e) {
        controlX = e.getX();
        controlY = e.getY();
      }
    });
  }
  public void paint(Graphics g) {
    int width = getWidth();
    int height = getHeight();


    for(Car car : enemyCars) {
      playerCar.hitTest(car);
    }


    final double coefficient = 0.001;
    playerCar.ddx = (controlX - (playerCar.x + (playerCar.width / 2))) * coefficient;
    playerCar.ddy = (controlY - (playerCar.y + (playerCar.width / 2))) * coefficient;
    playerCar.update(g, width, height);
    for(Car car : enemyCars) {
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
