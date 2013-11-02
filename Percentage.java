import java.awt.*;
public class Percentage {
  public int value;
  private Color color;
  private int x, y;
  Percentage(int x, int y, int value, Color color) {
    this.value = value;
    this.color = color;
  }
  void update(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g.setColor(this.color);
    g2.drawString(Integer.toString(value), this.x, this.y);
    g2.drawString("fawa[:w", this.x, this.y);
  }
}

