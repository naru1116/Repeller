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
    return tc*td < 0 && ta*tb < 0;
  }
  void hitTest(Car targetCar) {
    double relativeDx = this.dx - targetCar.dx;
    double relativeDy = this.dy - targetCar.dy;
    double hitTestOriginX = targetCar.x + (relativeDx > 0 ? 0 : targetCar.x + targetCar.width);
    double hitTestOriginY = targetCar.y + (relativeDy > 0 ? 0 : targetCar.y + targetCar.height);
    double hitTestPointX = this.x + (relativeDx > 0 ? this.width : 0);
    double hitTestPointY = this.y + (relativeDy > 0 ? this.height : 0);
    

    double x1 = hitTestPointX - hitTestOriginX;
    double y1 = hitTestPointY - hitTestOriginY;

    double x2 = x1 + relativeDx;
    double y2 = y1 + relativeDy;

    double x3 = 0;
    double y3 = 0;
    double x4 = 0;
    double y4 = this.height + targetCar.height;
    boolean result1 = hitTestLineSegments(x1, y1, x2, y2, x3, y3, x4, y4);
    x3 = 0;
    x4 = this.width + targetCar.width;
    y3 = 0;
    y4 = 0;
    boolean result2 = hitTestLineSegments(x1, y1, x2, y2, x3, y3, x4, y4);
    if(result1) {
      this.dx *= -1;
    } else if(result2) {
      this.dy *= -1;
    }
  }
  void update(Graphics g, int canvasWidth, int canvasHeight) {
    dx += ddx;
    dy += ddy;
    x += dx;
    y += dy;
    dx *= 0.99;
    dy *= 0.99;
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
