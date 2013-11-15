import java.awt.*;
import java.util.*;
class EnemyCar extends Car {


  final int StatusPassive = 0;
  final int StatusAgressive = 1;
  final int StatusOdd = 2;

  private int statusTransitionCount;

  private int status;

  Random random = new Random();
  public EnemyCar(int x, int y, int dx, int dy, Color color) {
    super(x, y, dx, dy, color);
    status = StatusPassive;
    statusTransitionCount = 10 + random.nextInt(40);
  }
  void chaseTarget(Car target, int width, int height) {
    if(statusTransitionCount-- <= 0) {
      statusTransitionCount =  10 + random.nextInt(40);
      switch(status){
        case StatusPassive:
            status = random.nextInt(2) == 0 ? StatusAgressive : StatusOdd;
            break;
        case StatusAgressive:
            status = random.nextInt(2) == 0 ? StatusPassive : StatusOdd;
            break;
        case StatusOdd:
            status = random.nextInt(2) == 0 ? StatusPassive : StatusAgressive;
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
        break;
      case StatusAgressive: {
        double deltaX = target.x - this.x;
        double deltaY = target.y - this.y;
        deltaX *= 0.008;
        deltaY *= 0.008;
        double maxDelta = 1;
        if(deltaX > maxDelta) deltaX = maxDelta;
        if(deltaX < -maxDelta) deltaX = -maxDelta;
        if(deltaY > maxDelta) deltaY = maxDelta;
        if(deltaY < -maxDelta) deltaY = -maxDelta;
        this.ddx = deltaX;
        this.ddy = deltaY;
        if(hitTime == 0) {
          int limit = 100;
          if(this.x < limit) {
            this.ddx = 5;
            this.dx *= 0.9;
            this.dy *= 0.9;
          } else if(this.x > width - limit) {
            this.ddx = -5;
            this.dx *= 0.9;
            this.dy *= 0.9;
          }
          if(this.y < limit) {
            this.ddy = 5;
            this.dx *= 0.9;
            this.dy *= 0.9;
          } else if(this.y > height - limit) {
            this.ddy = -5;
            this.dx *= 0.9;
            this.dy *= 0.9;
          }
        }
      }
        break;
      case StatusOdd: {
        if(random.nextInt(30) == 0) {
          this.dx *= -1;
        }
        if(random.nextInt(30) == 0) {
          this.dy *= -1;
        }
        if(random.nextInt(30) == 0) {
          double dy = this.dy;
          double dx = this.dx;
          this.dx = dy;
          this.dy = dx;
        }
        if(hitTime == 0) {
          int limit = 100;
          if(this.x < limit) {
            this.dx = Math.abs(this.dx);
          } else if(this.x > width - limit) {
            this.dx = -Math.abs(this.dx);
          }
          if(this.y < limit) {
            this.dy = Math.abs(this.dy);
          } else if(this.y > height - limit) {
            this.dy = -Math.abs(this.dy);
          }
        }
      }
        break;
      default:
        break;
    }
  }
}

