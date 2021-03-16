import java.util.*;

public class Reckless extends Human {
    Random rand = new Random();

    /**
    * Constructor for Reckless class.
    * @param healthy of boolean type.
    * @param infected of boolean type.
    * @param recovered of boolean type.
    * @param dead of boolean type.
    * @param fatalityR of double type.
    */
    public Reckless(boolean healthy, boolean infected, boolean recovered, boolean dead, double fatalityR) {
        super(healthy, infected, recovered, dead, fatalityR);
    }

    /**
    * Used to differentiate between cautious humans and reckless humans. Always returns false for reckless humans.
    * @return false
    */
    public boolean isCautious() {
        return this.isCautious;
    }

    /**
    * Lose mask. 50% chance of becoming infected if healthy.
    */
    public void loseMask() {
        int rand1 = rand.nextInt(10);
        if (this.getH()) {
            if (rand1 < 5) {
                this.setI(true);
                this.setH(false);
                this.setR(false);
                this.setD(false);
            }
        }
    }

    /**
    * Sneeze on another Human. If this is infected and h2 is healthy, 80% chance that h2 becomes infected.
    * @param h2 of Human type.
    */
    public void sneezeOn(Human h2) {
        int rand1 = rand.nextInt(10);
        if (this.getI() && h2.getH()) {
            if (rand1 < 8) {
                h2.setH(false);
                h2.setI(true);
                h2.setR(false);
                h2.setD(false);
            }
        }
    }

    /**
    * Combines loseMask() and action() in Human superclass to simplify the code in dailyActions() in Building class.
    */
    public void action() {
        super.action();
        loseMask();
    }

    /**
    * Combines sneezeOn(h2) and action(h2) in Human superclass to simplify the code in dailyActions() in Building class.
    */
    public void action(Human h2) {
        super.action();
        sneezeOn(h2);
    }
}