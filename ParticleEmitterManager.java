import java.awt.*;
import java.util.*;
public class ParticleEmitterManager {
  private static ParticleEmitterManager sharedInstance = new ParticleEmitterManager();
  public static ParticleEmitterManager getInstance() {
    return sharedInstance;
  }

  private ArrayList<ParticleEmitter> particleEmitters = new ArrayList<ParticleEmitter>();
  public void addParticleEmitter(ParticleEmitter particleEmitter) {
    particleEmitters.add(particleEmitter);
  }

  public void update(Graphics g, int canvasWidth, int canvasHeight) {
    for(ParticleEmitter particleEmitter : particleEmitters) {
      particleEmitter.update(g, canvasWidth, canvasHeight);
    }
    for (int i = particleEmitters.size() - 1; i >= 0; i--) {
      if (particleEmitters.get(i).deleted) {
        particleEmitters.remove(i);
      }
    }
  }
}


