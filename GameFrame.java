import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GameFrame extends ExitFrame {
  private Car enemyCar = new Car(500, 500, 0, 0, Color.green);
  private Car playerCar = new Car(300, 300, 0, 0, Color.red);
  private int controlX = -1, controlY;
  private boolean isFirstPaint = false;
  private Gauge playerEnergyGauge;
  private Gauge playerDamageGauge;
  private Gauge enemyEnergyGauge;
  private Gauge enemhyDamageGauge;
  private Percentage playerPercentage;

  public GameFrame() {
    int width = getWidth();
    int height = getHeight();
    this.playerPercentage = new Percentage(200, 200, 200, Color.red);
    addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {}
      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}
      public void mousePressed(MouseEvent e) {
        controlX = e.getX();
        controlY = e.getY();
      }
      public void mouseReleased(MouseEvent e) {controlX = -1;}
    });
    addMouseMotionListener(new MouseMotionListener() {
      public void mouseMoved(MouseEvent e) { System.out.println("Moved" + e.getX()); }
      public void mouseDragged(MouseEvent e) {
        controlX = e.getX();
        controlY = e.getY();
      }
    });
  }
  public void paint(Graphics g) {
    if(!this.isFirstPaint) {
      int width = getWidth();
      int height = getHeight();
      int gaugeHeight = 20;
  this.playerEnergyGauge = new Gauge(0, height - gaugeHeight, width, gaugeHeight, 0.6, new Color(82, 237, 150));
  this.playerDamageGauge = new Gauge(0, height - 2*gaugeHeight, width, gaugeHeight, 0.2, new Color(255, 237, 150));
  //this.enemyEnergyGauge;
  //this.enemhyDamageGauge;
    }
    this.isFirstPaint = true;
    int width = getWidth();
    int height = getHeight();

    g.setColor(Color.black);
    g.fillRect(0, 0, (int)width, (int)height);


    playerCar.hitTest(enemyCar);


    final double coefficient = 0.001;
    if(controlX != -1) {
      playerCar.ddx = (controlX - (playerCar.x + (playerCar.width / 2))) * coefficient;
      playerCar.ddy = (controlY - (playerCar.y + (playerCar.width / 2))) * coefficient;
    } else {
      playerCar.ddx = 0;
      playerCar.ddy = 0;
    }

    enemyCar.chaseTarget(playerCar);
    playerCar.update(g, 0, 0, width, height);
    enemyCar.update(g, 0, 0, width, height);

    playerCar.drawBody(g, 0, 0, width, height);
    enemyCar.drawBody(g, 0, 0, width, height);
 playerDamageGauge.update(g, 0, 0, width, height);

  this.playerEnergyGauge.update(g, 0, 0, width, height);
  //this.playerDamageGauge.update(g, 0, 0, width, height);
  //this.enemyEnergyGauge.update(g, 0, 0, width, height);
  //this.enemhyDamageGauge.update(g, 0, 0, width, height);
   this.playerPercentage.update(g);

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
