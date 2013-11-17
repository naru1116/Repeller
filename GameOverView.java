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
  private boolean isClosing = false;
  private static boolean isFirst = true;
  private boolean isNormal = false;

  public GameOverView(GameFrame gameFrame, int width, int height, boolean isLooser) {
    super(gameFrame);
    if(isFirst) {
      isFirst = false;
      isNormal = true;
    }
    this.width = width;
    this.height = height;
    this.isLooser = isLooser;
  }

  public void paint(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(0, 0, (int)width, (int)height);
    Image img;
    if(isNormal) {
      img = gameFrame.getToolkit().getImage("normal.png");
    } else {
      if(!isLooser)img = gameFrame.getToolkit().getImage("ok.png");
      else img = gameFrame.getToolkit().getImage("ng.png");
    }
    int destX = width/2 - 250;
    int destY = height/2 - 250;


    if(!isClosing) {
      time += 0.04;
      if(time > 1) time = 1;
      double coefficient = (-(time - 1.0)*(time - 1.0) + 1.0);
      g.drawImage(img, destX, (int)(-300 + coefficient * (300 + destY)), null);
    } else {
      time -= 0.04;
      if(time < 0) {
        View view = new GameView(gameFrame, width, height);
        this.gameFrame.transitionToView(view);
      }
      double coefficient = (-(time - 1.0)*(time - 1.0) + 1.0);
      g.drawImage(img, destX, (int)(300 + height + coefficient * (-300 - height + destY)), null);
    }
  }

/////////////////////////////MouseListener/////////////////////////////////////////////////////
  public void mouseClicked(MouseEvent e) {
    if(time == 1) {
      this.isClosing = true;
    }
  }
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {
  }
  public void mouseReleased(MouseEvent e) {}
/////////////////////////////MouseListener////END//////////////////////////////////////////////

}


