import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GameFrame extends ExitFrame {
  View currentView;
  public GameFrame() {
    int width = getWidth();
    int height = getHeight();
    currentView = new GameView();
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
      public void mouseMoved(MouseEvent e) {/* System.out.println("Moved" + e.getX()); */}
      public void mouseDragged(MouseEvent e) {
        controlX = e.getX();
        controlY = e.getY();
      }
    });
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
