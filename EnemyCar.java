import java.awt.*;
import java.util.*;
class EnemyCar extends Car {


  final int StatusPassive = 0;
  final int StatusAgressive = 1;

  private int statusTransitionCount;

  private int status;

  Random random = new Random();
  public EnemyCar(int x, int y, int dx, int dy, Color color) {
    super(x, y, dx, dy, color);
    status = StatusPassive;
    statusTransitionCount = 10 + random.nextInt(60);
  }
  void chaseTarget(Car target, int width, int height) {
    if(statusTransitionCount-- <= 0) {
      statusTransitionCount =  10 + random.nextInt(60);
      switch(status){
        case StatusPassive:
          status = StatusAgressive;
            break;
        case StatusAgressive:
            status = StatusPassive;
            break;
        default:
          break;
      }
    }

    switch(status) {
      case StatusPassive: {
        double deltaX = target.x - this.x;
        double deltaY = target.y - this.y;
        deltaX *= 0.003;
        deltaY *= 0.003;
        this.ddx = deltaX;
        this.ddy = deltaY;
      deltaX = width/2.0 + this.width/2.0 - this.x;
      deltaY = height/2.0  + this.height/2.0 - this.y;
      deltaX *= 0.006;
      deltaY *= 0.006;

      this.ddx += deltaX;
      this.ddy += deltaY;
      this.dx *= 0.9 + 0.1*this.damage;
      this.dy *= 0.9 + 0.1*this.damage;
      }
        break;
      case StatusAgressive: {
        double deltaX = target.x - this.x;
        double deltaY = target.y - this.y;
        deltaX *= 0.004;
        deltaY *= 0.004;
        double maxDelta = 0.5;
        if(deltaX > maxDelta) deltaX = maxDelta;
        if(deltaX < -maxDelta) deltaX = -maxDelta;
        if(deltaY > maxDelta) deltaY = maxDelta;
        if(deltaY < -maxDelta) deltaY = -maxDelta;
        this.ddx = deltaX;
        this.ddy = deltaY;
      }
        break;
      default:
        break;
    }
    if(hitTime == 0) {
      int limit = 100 + random.nextInt(200);
      if(this.x < limit) {
        this.ddx = 10;
        this.dx *= 0.9;
        this.dy *= 0.9;
      } else if(this.x > width - limit) {
        this.ddx = -10;
        this.dx *= 0.9;
        this.dy *= 0.9;
      }
      if(this.y < limit) {
        this.ddy = 10;
        this.dx *= 0.9;
        this.dy *= 0.9;
      } else if(this.y > height - limit) {
        this.ddy = -10;
        this.dx *= 0.9;
        this.dy *= 0.9;
      }
    }
  }
}

