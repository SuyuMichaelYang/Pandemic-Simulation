import java.util.*;

public class Cautious extends Human {
    Random rand = new Random();

    /**
    * Constructor for Cautious class.
    * @param healthy of boolean type.
    * @param infected of boolean type.
    * @param recovered of boolean type.
    * @param dead of boolean type.
    * @param fatalityR of double type.
    */
    public Cautious(boolean healthy, boolean infected, boolean recovered, boolean dead, double fatalityR) {
        super(healthy, infected, recovered, dead, fatalityR);
        this.isCautious = true;
    }

    /**
    * Used to differentiate between cautious humans and reckless humans. Always returns true for cautious humans.
    * @return true
    */
    public boolean isCautious() {
        return this.isCautious;
    }

    /**
    * Seek cure. 90% chance of recovering if infected.
    */
    public void seekCure() {
        int rand1 = rand.nextInt(10);
        if (this.getI() && !this.getD()) {
            if (rand1 < 9) {
                this.setR(true);
                this.setI(false);
                this.setH(false);
                this.setD(false);
            }
        }
    }

    /**
    * Cure other humans. 60% chance of curing them if they are infected.
    * @param h2 of Human type.
    */
    public void cureOthers(Human h2) {
        int rand1 = rand.nextInt(10);
        if (h2.getI() && !h2.getD()) {
            if (rand1 < 6) {
                h2.setR(true);
                h2.setI(false);
                h2.setH(false);
                h2.setD(false);
            }
        }
    }

    /**
    * Combines seekCure() and action() in Human superclass to simplify the code in dailyActions() in Building class.
    */
    public void action() {
        super.action();
        seekCure();
    }

    /**
    * Combines cureOthers(h2) and action(h2) in Human superclass to simplify the code in dailyActions() in Building class.
    */
    public void action(Human h2) {
        super.action(h2);
        cureOthers(h2);
    }
}