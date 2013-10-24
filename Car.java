import java.awt.*;
class Car extends Sprite {
  public double x = 100;
  public double y = 100;
  public double width = 100;
  public double height = 100;
  public double dx = 0, dy = 0;
  void update(Graphics g, int canvasWidth, int canvasHeight) {
    x += dx;
    y += dy;
    if(x > canvasWidth - width) {
      x = canvasWidth - width;
      dx *= -1;
    } else if(x  < 0) {
      x = 0;
      dx *= -1;
    }
    if(y > canvasHeight - height) {
      y = canvasHeight - height;
      dy *= -1;
    } else if(y  < 0) {
      y = 0;
      dy *= -1;
    }
    dx *= 0.99;
    dy *= 0.99;
    g.setColor(Color.red);
    g.fillRect((int)x, (int)y, (int)width, (int)height);
  }
}
