import java.util.*;

public class NonHome extends Building {
    /**
    * Constructor for NonHome class.
    * @param maxCapa of int type.
    * @param numOccup of int type.
    */
    public NonHome(int maxCapa, int numOccup) {
        super(maxCapa, numOccup);
    }

    /**
    * Changes the max capacity of a NonHome based on the lockdown level.
    */
    public void changeCapa() {
        if (this.getLockLevel() == 1) {
            this.maxCapa = maxCapa;
        } else if (this.getLockLevel() == 2) {
            if ((maxCapa - 2000) > 0) {
                this.maxCapa = (maxCapa - 2000);
            } 
        } else if (this.getLockLevel() == 3) {
            if ((maxCapa - 4000) > 0) {
                this.maxCapa = (maxCapa - 4000);
            } 
        } else if (this.getLockLevel() == 4) {
            if ((maxCapa - 6000) > 0) {
                this.maxCapa = (maxCapa - 6000);
            } 
        } else if (this.getLockLevel() == 5) {
            if ((maxCapa - 8000) > 0) {
                this.maxCapa = (maxCapa - 8000);
            } 
        }
    }

    /**
    * Performs actions on each Human in the NonHome. In addition to dailyActions() in Building class, also calls changeCapa().
    */
    public void dailyActions() {
        super.dailyActions();
        changeCapa();
    }

}