import java.awt.*;
import java.util.*;
public class ParticleEmitterManager {
  private ArrayList<Particle> particles = new ArrayList<Particle>();
  private Color color;
  private double time;
  public boolean deleted;

  private class Particle {
    public double x, y;
    public double dx, dy;
    public double ddx, ddy;
    Particle(double x, double y, double ddx, double ddy) {
      this.x = x;
      this.y = y;
      this.dx = 0;
      this.dy = 0;
      this.ddx = ddx;
      this.ddy = ddy;
    }
    public void update(Graphics g) {
      this.dx += this.ddx;
      this.dy += this.ddy;
      this.x += this.dx;
      this.y += this.dy;
      g.fillRect((int)this.x, (int)this.y, 2, 2);
    }
  }


  ParticleEmitter(int x, int y, int count, Color color) {
    this.deleted = false;
    this.color = color;
    this.time = 0;
    Random random = new Random();
    for(int i = 0;i < count;i++) {
      double angle = 2*Math.PI*random.nextDouble();
      double distance = random.nextDouble();
      double dr = random.nextDouble();
      double dl = random.nextDouble();
      particles.add(new Particle(x, y,  0.5*distance*Math.cos(angle) + dr, 0.5*distance*Math.sin(angle) + dl));
    }
  }
  void update(Graphics g, int canvasX, int canvasY, int canvasWidth, int canvasHeight) {
    g.setColor(this.color);
    time += 0.001;
    if(time > 1.0) this.deleted = true;
    for(Particle particle : particles) {
      particle.update(g);
    }
  }
}


