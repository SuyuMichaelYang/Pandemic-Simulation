import java.util.*;

public class Building {
    private int numOccup = 0;
    protected int maxCapa = 0;
    public boolean isHome = false;
    private int lockLevel = 0;
    protected ArrayList<Human> newBuilding = new ArrayList<Human>();
    Random rand = new Random();

    /**
    * Constructor for Building class.
    * @param maxCapa of int type.
    * @param numOccup of int type.
    */
    public Building(int maxCapa, int numOccup) {
        this.maxCapa = maxCapa;
        this.numOccup = numOccup;
    }

    /**
    * Returns the max capacity of the Building.
    * @return maxCapa of int type.
    */
    public int getCapa() {
        return maxCapa;
    }

    /**
    * Sets the number of Humans in the Building.
    * @param numOccup of int type.
    */
    public void setNumOccup(int numOccup) {
        this.numOccup = numOccup;
    }

    /**
    * Returns the number of Humans in the Building.
    * @return the number of Humans in the Building. Of int type.
    */
    public int getNumOccup() {
        return newBuilding.size();
    }

    /**
    * Indicates if a Building is a Home or NonHome.
    * @return isHome. False by default. The constructor in Home class sets isHome to true.
    */
    public boolean isHome() {
        return isHome;
    }

    /**
    * Returns a Human at a specific index in the Building.
    * @param index of int type.
    * @return toReturn of Human type.
    */
    public Human getH(int index) {
        Human toReturn = newBuilding.get(index);
        return toReturn;
    }

    /**
    * Adds a Human to the Building. Increments the number of occupants in that Building.
    * @param newHuman of Human type.
    */
    public void addH(Human newHuman) {
        newBuilding.add(newHuman);
        numOccup++;
    }

    /**
    * Removes an ArrayList of Humans from the Building.
    * @return toReturn of ArrayList<Human> type.
    */
    public ArrayList<Human> removeH() {
        ArrayList<Human> toReturn = new ArrayList<Human>();
        int toRemove = rand.nextInt(newBuilding.size());
        for (int i = (newBuilding.size() - 1); i > toRemove; i--) {
            Human h1 = newBuilding.remove(i);
            toReturn.add(h1);
        }
        return toReturn;
    }

    /**
    * Adds an ArrayList of Humans to the Building.
    * @param hList of ArrayList<Human> type.
    */
    public void addHList(ArrayList<Human> hList) {
        newBuilding.addAll(hList);
    }

    /**
    * Indicates if the Building is full or not.
    * @return true if full, false otherwise, both of boolean type.
    */
    public boolean isFull() {
        if (numOccup >= maxCapa) {
            return true;
        }
        return false;
    }

    /**
    * Sets the lockdown level the user input.
    * @param lockLevel of int type.
    */
    public void setLockLevel(int lockLevel) {
        this.lockLevel = lockLevel;
    }

    /**
    * Returns the lockdown level the user input.
    * @return lockLevel of int type.
    */
    public int getLockLevel() {
        return lockLevel;
    }

    /**
    * Performs actions on each Human in the Building. Specific actions are found in Human class and its subclasses.
    */
    public void dailyActions() {
        for (int i = 0; i < newBuilding.size(); i++) {
            Human currH = newBuilding.get(i);
            currH.action();
            int rand1 = rand.nextInt(newBuilding.size());
            currH.action(newBuilding.get(rand1));
        }
    }

}