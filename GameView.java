import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.applet.AudioClip;
import java.applet.Applet;
public class GameView extends View {
  private EnemyCar enemyCar = new EnemyCar(500, 500, 0, 0, Color.green);
  private Car playerCar = new Car(300, 300, 0, 0, Color.red);
  public int controlX = -1, controlY;
  private boolean isFirstPaint = false;
  private Gauge playerEnergyGauge;
  private Gauge playerDamageGauge;
  private Gauge enemyEnergyGauge;
  private Gauge enemyDamageGauge;
  private int width;
  private int height;
  private AudioClip gameOverAudio;
  public GameView(GameFrame gameFrame, int width, int height) {
    super(gameFrame);
    this.width = width;
    this.height = height;

    int gaugeHeight = 20;
    this.playerEnergyGauge = new Gauge(0, height - 2*gaugeHeight, width, gaugeHeight, 0, new Color(0, 60, 60));
    this.playerDamageGauge = new Gauge(0, height - gaugeHeight, width, gaugeHeight, 0, new Color(60, 0, 0));
    this.enemyEnergyGauge = new Gauge(0, gaugeHeight, width, gaugeHeight, 0, new Color(0, 60, 60));;
    this.enemyDamageGauge = new Gauge(0, 0, width, gaugeHeight, 0, new Color(60, 0, 0));
    gameOverAudio = Applet.newAudioClip(getClass().getResource("gameover.wav"));
  }

  public void paint(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(0, 0, (int)width, (int)height);

    playerCar.hitTest(enemyCar);

    playerDamageGauge.value = playerCar.damage;
    enemyDamageGauge.value = enemyCar.damage;


    final double coefficient = 0.003;
    if(controlX != -1) {
      playerCar.ddx = (controlX - (playerCar.x + (playerCar.width / 2))) * coefficient;
      playerCar.ddy = (controlY - (playerCar.y + (playerCar.width / 2))) * coefficient;
    } else {
      playerCar.ddx = 0;
      playerCar.ddy = 0;
    }

    enemyCar.chaseTarget(playerCar, width, height);

    int xOrigin = 0;
    int yOrigin = 0;
    int boardHeight = height;
    int boardWidth = width;
    playerCar.update(g, xOrigin, yOrigin, boardWidth, boardHeight);
    enemyCar.update(g, xOrigin, yOrigin, boardWidth, boardHeight);

    playerCar.drawBody(g, xOrigin, yOrigin, boardWidth, boardHeight);
    enemyCar.drawBody(g, xOrigin, yOrigin, boardWidth, boardHeight);
    this.playerDamageGauge.update(g, xOrigin, yOrigin, boardWidth, boardHeight);
    this.playerEnergyGauge.update(g, xOrigin, yOrigin, boardWidth, boardHeight);
    this.enemyEnergyGauge.update(g, xOrigin, yOrigin, boardWidth, boardHeight);
    this.enemyDamageGauge.update(g, xOrigin, yOrigin, boardWidth, boardHeight);

    if(enemyCar.isDead) {
      View view = new GameOverView(this.gameFrame, this.width, this.height);
      gameOverAudio.play();
      transitionToView(view);
    } else if(playerCar.isDead) {
      View view = new GameOverView(this.gameFrame, this.width, this.height);
      gameOverAudio.play();
      transitionToView(view);
    }
  }

/////////////////////////////MouseListener/////////////////////////////////////////////////////
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {
    controlX = e.getX();
    controlY = e.getY();
  }
  public void mouseReleased(MouseEvent e) {controlX = -1;}
/////////////////////////////MouseListener////END//////////////////////////////////////////////

}

