import java.awt.*;
class Car extends Sprite {
  int x = 100;
  int y = 100;
  int width = 100;
  int height = 100;
  void paint(Graphics g) {
    g.setColor(Color.red);
    g.fillRect((int)x, (int)y, (int)width, (int)height);
  }
}
