import java.awt.*;
import java.util.*;
class Car extends Sprite {
  public double x = 100;
  public double y = 100;
  private final int afterImageCount = 20;
  private int currentAfterImage = 0;
  public double[] xs;
  public double[] ys;
  public double width = 60;
  public double height = 60;
  public double dx = 0, dy = 0;
  public double ddx = 0, ddy = 0;
  public double damage = 0; //max damage is 1.0
  public boolean isDead = false;
  public double energy = 0; //energy is 1.0
  public Color color;
  public Car(int x, int y, int dx, int dy, Color color) {
    this.x = x;
    this.y = y;
    this.dx = dx;
    this.dy = dy;
    this.color = color;

    this.xs = new double[afterImageCount];
    this.ys = new double[afterImageCount];
    for(int i = 0; i < afterImageCount; i++) {
      this.xs[i] = x;
      this.ys[i] = y;
    }
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


    double mass = 1.0 - this.damage * 0.7;
    double targetMass = 1.0 - targetCar.damage * 0.7;


    if(isHorizontalHit) {
      this.y = targetCar.y  + (isDy ? -1 : 1) * targetCar.height;
      int y = (int)(this.y  + (isDy ? targetCar.height : 0));
      int[] candidates = {(int)this.x,(int)(this.x + this.width),(int)(targetCar.x),(int)(targetCar.x + targetCar.width)};
      Arrays.sort(candidates);
      int minX = candidates[1];
      int maxX = candidates[2];
      ParticleEmitterManager.getInstance().addParticleEmitter(new ParticleEmitter(minX, y, maxX, y, ((this.dx + targetCar.dx)/2.0), ((this.dy + targetCar.dy)/2.0), 100,
            new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 200),
            new Color(targetCar.color.getRed(), targetCar.color.getGreen(), targetCar.color.getBlue(), 200)));
    } else if(isVerticalHit) {
      this.x = targetCar.x  + (isDx ? -1 : 1) * targetCar.width;
      int x = (int)(this.x  + (isDx ? targetCar.width : 0));
      int[] candidates = {(int)this.y,(int)(this.y + this.height),(int)(targetCar.y),(int)(targetCar.y + targetCar.height)};
      Arrays.sort(candidates);
      int minY = candidates[1];
      int maxY = candidates[2];
      ParticleEmitterManager.getInstance().addParticleEmitter(new ParticleEmitter(x, minY, x, maxY,((this.dx + targetCar.dx)/2.0), ((this.dy + targetCar.dy)/2.0), 100,
            new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 200),
            new Color(targetCar.color.getRed(), targetCar.color.getGreen(), targetCar.color.getBlue(), 200)));
    }
    if(isHorizontalHit || isVerticalHit) {
      double thisdx = this.dx;
      double thisdy = this.dy;
      double targetCardx = targetCar.dx;
      double targetCardy = targetCar.dy;

      this.dx = ((mass - 1.0) * this.dx + 2*1.0*targetCar.dx) / (mass + 1.0);
      this.dy = ((mass - 1.0) * this.dy + 2*1.0*targetCar.dy) / (mass + 1.0);

      targetCar.dx = ((targetMass - 1.0) * targetCar.dx + 2*1.0*thisdx) / (1.0 + targetMass);
      targetCar.dy = ((targetMass - 1.0) * targetCar.dy + 2*1.0*thisdy) / (1.0 + targetMass);

      double speed = Math.hypot(thisdx, thisdy);
      double targetSpeed = Math.hypot(targetCardx, targetCardy);
      this.damage += targetSpeed * 0.01;
      if(this.damage > 1) this.damage = 1;
      targetCar.damage += speed * 0.01;
      if(targetCar.damage > 1) targetCar.damage = 1;
    }

  }
  void chaseTarget(Car target) {
    double deltaX = target.x - this.x;
    double deltaY = target.y - this.y;
    deltaX *= 0.001;
    deltaY *= 0.001;
    this.ddx = deltaX;
    this.ddy = deltaY;
  }
  void update(Graphics g, int canvasX, int canvasY, int canvasWidth, int canvasHeight) {
    x += dx;
    y += dy;
    this.xs[currentAfterImage] = x;
    this.ys[currentAfterImage] = y;
    currentAfterImage++;
    if(currentAfterImage == afterImageCount)currentAfterImage = 0;
    dx *= 0.99;
    dy *= 0.99;
    dx += ddx;
    dy += ddy;

    if(this.x > canvasWidth) {
      this.isDead = true;
      ParticleEmitterManager.getInstance().addParticleEmitter(new ParticleEmitter(canvasWidth, (int)(this.y), canvasWidth, (int)(this.y + this.height), -5.0, 0.0, 200, this.color, this.color));
    } else if(this.x + this.width < 0) {
      this.isDead = true;
    }
    if(this.y > canvasHeight) {
      this.isDead = true;
    } else if(this.y + this.height  < 0) {
      this.isDead = true;
    }


    int i = currentAfterImage;
    int j = 0;
    while(true) {
      //Color color = (new Color((float)(this.color.getRed() / 255.0), (float)(this.color.getGreen()/ 255.0), (float)(this.color.getBlue()/ 255.0), (float)0.4*((float)j/(afterImageCount - 1)))).darker();
      Color color = (new Color((float)(this.color.getRed() / 255.0), (float)(this.color.getGreen()/ 255.0), (float)(this.color.getBlue()/ 255.0), (float)1.0)).darker().darker().darker();
      g.setColor(color);
      g.fillRect((int)xs[i], (int)ys[i], (int)width, (int)height);
      i++; if(i == afterImageCount) i = 0;
      if(i == currentAfterImage) break;
      j++;
    }
  }
  void drawBody(Graphics g, int canvasX, int canvasY, int canvasWidth, int canvasHeight) {
    g.setColor(this.color);
    g.fillRect((int)x, (int)y, (int)width, (int)height);
  }
}
