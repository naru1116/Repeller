import java.awt.*;
public class Gauge {
  private int x;
  private int y;
  private int width;
  private int height;
  public double value;
  private double animatingValue;
  private Color color;
  Gauge(int x, int y, int width, int height, double value, Color color) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.value = value;
    this.animatingValue = 1.0;
    this.color = color;
  }
  void update(Graphics g, int canvasX, int canvasY, int canvasWidth, int canvasHeight) {
    double valueDistance = this.value - this.animatingValue;
    this.animatingValue += valueDistance * 0.1;
    g.setColor(color.darker().darker());
    g.fillRect(this.x, this.y, (int)(this.width * this.animatingValue), this.height);
  }
}
