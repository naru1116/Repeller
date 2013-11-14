import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GameFrame extends ExitFrame {
  private View currentView;
  private Dimension dimension;
  public GameFrame() {
    this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
    currentView  = new GameView(this, this.dimension.width, this.dimension.height);
    addMouseListener(currentView);
  }


  public void transitionToView(View view) {
    removeMouseListener(currentView);
    currentView = view;
    addMouseListener(currentView);
  }

  public void paint(Graphics g) {
    currentView.paint(g);
    ParticleEmitterManager.getInstance().update(g, this.dimension.width, this.dimension.height);
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
