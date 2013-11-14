import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class GameOverView extends View {
  private int width;
  private int height;

  public GameOverView(GameFrame gameFrame, int width, int height) {
    super(gameFrame);
    this.width = width;
    this.height = height;
  }

  public void paint(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(0, 0, (int)width, (int)height);
  }

/////////////////////////////MouseListener/////////////////////////////////////////////////////
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {
  }
  public void mouseReleased(MouseEvent e) {}
/////////////////////////////MouseListener////END//////////////////////////////////////////////

}


