import java.util.*;

public class Home extends Building {  
    /**
    * Constructor for Home class.
    * @param maxCapa of int type.
    * @param numOccup of int type.
    */
    public Home(int maxCapa, int numOccup) {
       super(maxCapa, numOccup);
       this.isHome = true;
    }

    /**
    * Indicates if a Building is a Home. Always returns true for Homes.
    * @return true for Homes.
    */
    public boolean isHome() {
        return this.isHome;
    }

    /**
    * Infects everyone in Home if one Human is infected.
    */
    public void infectAll() {
        boolean foundI = false;

        for (int i = 0; i < newBuilding.size(); i++) {
            Human currH = this.getH(i);
            if (currH.getI()) {
                foundI = true;
            }
        }
        if (foundI) {
            for (int i = 0; i < newBuilding.size(); i++) {
                Human currH = this.getH(i);
                if (currH.getH()) {
                    currH.setI(true);
                    currH.setH(false);
                    currH.setR(false);
                    currH.setD(false);
                }  
            }
        }
    }

    /**
    * Performs actions on each Human in the Home. In addition to dailyActions() in Building class, also calls infectAll().
    */
    public void dailyActions() {
        super.dailyActions();
        infectAll();
    }
}