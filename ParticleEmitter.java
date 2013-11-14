import java.awt.*;
import java.util.*;
public class ParticleEmitter {
  private ArrayList<Particle> particles = new ArrayList<Particle>();
  private Color color1;
  private Color color2;
  private double time;
  public boolean deleted;

  private class Particle {
    public double x, y;
    public double dx, dy;
    public double ddx, ddy;
    private Color color;
    private double opacity;
    Particle(double x, double y, double dx, double dy, double ddx, double ddy, Color color) {
      this.opacity = 1.0;
      this.x = x;
      this.y = y;
      this.dx = dx;
      this.dy = dy;
      this.ddx = ddx;
      this.ddy = ddy;
      this.color = color;
    }
    public void update(Graphics g,  int canvasWidth, int canvasHeight) {
      this.dx += this.ddx;
      this.dy += this.ddy;
      this.x += this.dx;
      this.y += this.dy;
      this.opacity -= 0.01;
      if(this.opacity < 0) this.opacity = 0;
      g.setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), (int)(this.color.getAlpha() * this.opacity)));
      g.fillRect((int)this.x, (int)this.y, 4, 4);
    }
  }


  ParticleEmitter(int x1, int y1, int x2, int y2, double dx, double dy, int count, Color color1, Color color2) {
    this.deleted = false;
    this.color1 = color1;
    this.color2 = color2;
    this.time = 0;
    Random random = new Random();
    for(int i = 0;i < count;i++) {
      double angle = 2*Math.PI*random.nextDouble();
      double distance = random.nextDouble();
      double dr = random.nextDouble();
      double dl = random.nextDouble();
      double randp = random.nextDouble();
      particles.add(new Particle(x1 + randp*(x2 - x1), y1 + randp*(y2 - y1),dx, dy,  0.5*distance*Math.cos(angle) + dr, 0.5*distance*Math.sin(angle) + dl, random.nextInt(2) == 0 ? color1 : color2));
    }
  }
  void update(Graphics g, int canvasWidth, int canvasHeight) {
    time += 0.001;
    if(time > 1.0) this.deleted = true;
    for(Particle particle : particles) {
      particle.update(g, canvasWidth, canvasHeight);
    }
  }
}

