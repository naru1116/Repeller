import java.awt.*;
class Car extends Sprite {
  public double x = 100;
  public double y = 100;
  public double width = 100;
  public double height = 100;
  public double dx = 0, dy = 0;
  public double ddx = 0, ddy = 0;
  public Color color;
  public Car(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }
  public Car(int x, int y, int dx, int dy, Color color) {
    this.x = x;
    this.y = y;
    this.dx = dx;
    this.dy = dy;
    this.color = color;
  }
  private boolean hitTestLineSegments(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
    //A(x1,y1),B(x2,y2)
    //C(x3,y3),D(x4,y4)
    //AB and CD
    double tc = (x1-x2)*(y3-y1)+(y1-y2)*(x1-x3);
    double td = (x1-x2)*(y4-y1)+(y1-y2)*(x1-x4);

    double ta=(x3-x4)*(y1-y3)+(y3-y4)*(x3-x1);
    double tb=(x3-x4)*(y2-y3)+(y3-y4)*(x3-x2);
    return tc*td <= 0 && ta*tb <= 0;
  }
  void hitTest(Car targetCar) {
    double dx = this.dx - targetCar.dx;
    double dy = this.dy - targetCar.dy;
    boolean isDx = dx >= 0;
    boolean isDy = dy >= 0;
    double px = this.x + (isDx ? this.width : 0);
    double py = this.y + (isDy ? this.height : 0);
    double qx = targetCar.x + (!isDx ? targetCar.width : 0);
    double qy = targetCar.y + (!isDy ? targetCar.height : 0);

    double x1 = px;
    double y1 = py;
    double x2 = px + dx;
    double y2 = py + dy;

    double x3 = qx;
    double y3 = qy;
    double x4 = qx + (this.width + targetCar.width) * (isDx ? 1 : -1);
    double y4 = qy;
    boolean isHorizontalHit = hitTestLineSegments(x1, y1, x2, y2, x3, y3, x4, y4);
    x4 = qx;
    y4 = qy + (this.height + targetCar.height) * (isDy ? 1 : -1);
    boolean isVerticalHit = hitTestLineSegments(x1, y1, x2, y2, x3, y3, x4, y4);

    if(isHorizontalHit) {
      this.dy *= -1;
      targetCar.dy *= -1;
    }

    if(isVerticalHit) {
      this.dx *= -1;
      targetCar.dx *= -1;
    }

  }
  void update(Graphics g, int canvasWidth, int canvasHeight) {
    x += dx;
    y += dy;
    dx *= 0.99;
    dy *= 0.99;
    dx += ddx;
    dy += ddy;
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
    g.setColor(this.color);
    g.fillRect((int)x, (int)y, (int)width, (int)height);
  }
}
