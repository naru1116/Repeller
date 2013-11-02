import java.awt.*;
public class Gauge {
  private int x;
  private int y;
  private int width;
  private int height;
  private double value;
  private Color color;
  Gauge(int x, int y, int width, int height, double value, Color color) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.value = value;
    this.color = color;
  }
  void update(Graphics g, int canvasX, int canvasY, int canvasWidth, int canvasHeight) {
    g.setColor(color.darker());
    g.fillRect(this.x, this.y, this.width, this.height);
    g.setColor(color.brighter());
    g.fillRect(this.x, this.y, (int)(this.width * this.value), this.height);
  }
}
