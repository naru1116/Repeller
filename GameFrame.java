import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GameFrame extends ExitFrame {
  private Car enemyCar = new Car(500, 500, 0, 0, Color.green);
  private Car playerCar = new Car(300, 300, 0, 0, Color.red);
  private int controlX, controlY;
  public GameFrame() {
    addMouseMotionListener(new MouseMotionListener() {
      public void mouseDragged(MouseEvent e) { System.out.println("Dragged" + e.getX()); }
      public void mouseMoved(MouseEvent e) {
        controlX = e.getX();
        controlY = e.getY();
      }
    });
  }
  public void paint(Graphics g) {
    int width = getWidth();
    int height = getHeight();

    g.setColor(Color.black);
    g.fillRect(0, 0, (int)width, (int)height);


    playerCar.hitTest(enemyCar);


    final double coefficient = 0.001;
    playerCar.ddx = (controlX - (playerCar.x + (playerCar.width / 2))) * coefficient;
    playerCar.ddy = (controlY - (playerCar.y + (playerCar.width / 2))) * coefficient;

    enemyCar.chaseTarget(playerCar);
    playerCar.update(g, 0, 0, width, height);
    enemyCar.update(g, 0, 0, width, height);
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
