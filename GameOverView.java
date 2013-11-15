import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.applet.AudioClip;
import java.applet.Applet;
public class GameOverView extends View {
  private int width;
  private int height;
  private boolean isLooser;
  private double time = 0.0;

  public GameOverView(GameFrame gameFrame, int width, int height, boolean isLooser) {
    super(gameFrame);
    this.width = width;
    this.height = height;
    this.isLooser = isLooser;
  }

  public void paint(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(0, 0, (int)width, (int)height);
    Image img;
    if(!isLooser)img = gameFrame.getToolkit().getImage("ok.png");
    else img = gameFrame.getToolkit().getImage("ng.png");
    int destX = width/2 - 250;
    int destY = height/2 - 250;
    time += 0.04;
    if(time > 1) time = 1;
    double coefficient = (-(time - 1.0)*(time - 1.0) + 1.0);
    g.drawImage(img, destX, (int)(-300 + coefficient * (300 + destY)), null);
  }

/////////////////////////////MouseListener/////////////////////////////////////////////////////
  public void mouseClicked(MouseEvent e) {
    View view = new GameView(gameFrame, width, height);
    this.gameFrame.transitionToView(view);
  }
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {
  }
  public void mouseReleased(MouseEvent e) {}
/////////////////////////////MouseListener////END//////////////////////////////////////////////

}


