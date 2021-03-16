import java.util.*;

public class Human {
    private boolean healthy = true;
    private boolean infected = false;
    private boolean recovered = false;
    private boolean dead = false;
    public boolean isCautious = false;
    private double fatalityR = 0.0;
    Random rand = new Random();

    /**
    * Constructor for Human class.
    * @param healthy of boolean type.
    * @param infected of boolean type.
    * @param recovered of boolean type.
    * @param dead of boolean type.
    * @param fatalityR of double type.
    */
    public Human(boolean healthy, boolean infected, boolean recovered, boolean dead, double fatalityR) {
        this.healthy = healthy;
        this.infected = infected;
        this.recovered = recovered;
        this.dead = dead;
        this.fatalityR = (fatalityR / 100);
    }

    /**
    * Sets the healthy variable of a Human.
    * @param newH of boolean type.
    */
    public void setH(boolean newH) {
        healthy = newH;
    }

    /**
    * Gets the healthy variable of a Human.
    * @return true if healthy, false otherwise.
    */
    public boolean getH() {
        return healthy;
    }

    /**
    * Sets the infected variable of a Human.
    * @param newI of boolean type.
    */
    public void setI(boolean newI) {
        infected = newI;
    }

    /**
    * Gets the infected variable of a Human.
    * @return true if healthy, false otherwise.
    */
    public boolean getI() {
        return infected;
    }

    /**
    * Sets the recovered variable of a Human.
    * @param newR of boolean type.
    */
    public void setR(boolean newR) {
        recovered = newR;
    }

    /**
    * Gets the recovered variable of a Human.
    * @return true if healthy, false otherwise.
    */
    public boolean getR() {
        return recovered;
    }

    /**
    * Sets the dead variable of a Human.
    * @param newD of boolean type.
    */
    public void setD(boolean newD) {
        dead = newD;
    }

    /**
    * Gets the dead variable of a Human.
    * @return true if healthy, false otherwise.
    */
    public boolean getD() {
        return dead;
    }

    /**
    * Shakes hand with another Human.
    * @param h2 of Human type.
    */
    public void shakeHands(Human h2) {
        int rand1 = rand.nextInt(10);
        if (this.getI() && h2.getH()) {
            if (rand1 < 7) {
                h2.setI(true);
                h2.setH(false);
                h2.setR(false);
                h2.setD(false);
            }
        } else if (h2.getI() && this.getH()) {
            if (rand1 < 7) {
                this.setI(true);
                this.setH(false);
                this.setR(false);
                this.setD(false);
            } 
        }
    }

    /**
    * Eat. 20% chance of becoming infected if healthy.
    */
    public void eat() {
        int rand2 = rand.nextInt(10);
        if (this.getH()) {
            if (rand2 < 2) {
                this.setI(true);
                this.setH(false);
                this.setR(false);
                this.setD(false);
            }
        }
    }

    /**
    * Kiss another Human.
    * @param h2 of Human type.
    */
    public void kiss(Human h2) {
        if (this.getI() && h2.getH()) {
            h2.setI(true);
            h2.setH(false);
            h2.setR(false);
            h2.setD(false);
        } else if (h2.getI() && this.getH()) {
            this.setI(true);
            this.setH(false);
            this.setR(false);
            this.setD(false);
        }
    }

    /**
    * Die. Depending on the fatality rate the user input, the Human has a certain percentage to die if infected.
    */
    public void die() {
        int rand3 = rand.nextInt(10);

        if (this.getI()) {
            if (this.fatalityR < 0.1) {
                if (rand3 < 1) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            } else if (this.fatalityR < 0.2 && this.fatalityR >= 0.1) {
                if (rand3 < 2) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            } else if (this.fatalityR < 0.3 && this.fatalityR >= 0.2) {
                if (rand3 < 3) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            } else if (this.fatalityR < 0.4 && this.fatalityR >= 0.3) {
                if (rand3 < 4) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            } else if (this.fatalityR < 0.5 && this.fatalityR >= 0.4) {
                if (rand3 < 5) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            } else if (this.fatalityR < 0.6 && this.fatalityR >= 0.5) {
                if (rand3 < 6) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            } else if (this.fatalityR < 0.7 && this.fatalityR >= 0.6) {
                if (rand3 < 7) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            } else if (this.fatalityR < 0.8 && this.fatalityR >= 0.7) {
                if (rand3 < 8) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            } else {
                if (rand3 < 9) {
                this.setI(false);
                this.setD(true);
                this.setH(false);
                this.setR(false);
                }
            }
        }
    }

    /**
    * Combines eat() and die() to simplify the code in dailyActions() in Building class.
    */
    public void action() {
        this.eat();
        this.die();
    }

    /**
    * Combines shakeHands(h2) and kiss(h2) to simplify the code in dailyActions() in Building class.
    */
    public void action(Human h2) {
        this.shakeHands(h2);
        this.kiss(h2);
    }

}