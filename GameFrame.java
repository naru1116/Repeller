import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GameFrame extends ExitFrame {
  View currentView;
  public GameFrame() {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    GameView gameView = new GameView(dimension.width, dimension.height);
    currentView = gameView;
    addMouseListener(gameView);
   // addMouseMotionListener(new MouseMotionListener() {
   //   public void mouseMoved(MouseEvent e) {/* System.out.println("Moved" + e.getX()); */}
   //   public void mouseDragged(MouseEvent e) {
   //     controlX = e.getX();
   //     controlY = e.getY();
   //   }
   // });
  }

  public void paint(Graphics g) {
    currentView.paint(g);
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
