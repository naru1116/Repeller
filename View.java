import java.awt.*;
import java.util.*;
import java.awt.event.*;
abstract public class View implements MouseListener {
  public GameFrame gameFrame;
  public View(GameFrame gameFrame) {
    this.gameFrame = gameFrame;
  }
  public void transitionToView(View view) {
    this.gameFrame.transitionToView(view);
  }
  abstract public void paint(Graphics g);
}

