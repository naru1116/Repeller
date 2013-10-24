import java.awt.*;
import java.awt.event.*;

public class ExitFrame extends Frame {
  public ExitFrame(){
    ClosingListener cl = new ClosingListener();
    this.addWindowListener(cl);
  }
}

class ClosingListener extends WindowAdapter {
  public void windowClosing(WindowEvent e){
    System.exit(0);
  }
}
