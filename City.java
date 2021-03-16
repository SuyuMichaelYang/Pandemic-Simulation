import java.util.*;
import java.io.*;

public class City{
    private int numHome = 0;
    private int numNonHome = 0;
    private int numCauHuman = 0;
    private int numReckHuman = 0;
    private int fatalityR = 0;
    private int lockLevel = 0;
    private int totalCapa = 0;
    Graph cityGraph;
    Random rand = new Random();

    /**
    * Constructor for City class.
    * @param numHome of int type.
    * @param numNonHome of int type.
    * @param numCauHuman of int type.
    * @param numReckHuman of int type.
    * @param fatalityR of int type.
    */
    public City(int numHome, int numNonHome, int numCauHuman, int numReckHuman, int fatalityR) {
        cityGraph = new Graph((numHome + numNonHome));
        populateCity(numHome, numNonHome, numCauHuman, numReckHuman, fatalityR);
    }

    /**
    * Returns the total capacity of all the Buildings in the City.
    * @param numHome of int type.
    * @param numNonHome of int type.
    * @return totalCapa of int type.
    */
    public int countCapa(int numHome, int numNonHome) {
        for (int i = 0; i < cityGraph.numVert(); i++) {
            Building currB = cityGraph.getBuild(i);
            int capaB = currB.getCapa();
            totalCapa = totalCapa + capaB;
        }
        return totalCapa;
    }

    /**
    * Populates the city with Buildings and Humans. Also takes in the fatality rate of the disease.
    * @param numHome of int type.
    * @param numNonHome of int type.
    * @param numCauHuman of int type.
    * @param numReckHuman of int type.
    * @param fatalityR of int type.
    */
    public void populateCity(int numHome, int numNonHome, int numCauHuman, int numReckHuman, int fatalityR) {
        // create buildings in the cityGraph.
        for (int i = 0; i < numHome; i++) {
            Building newHome = new Home(5, 0);
            cityGraph.addVertex(newHome);
        }
        for (int j = 0; j < numNonHome; j++) {
            int rand2 = rand.nextInt(100000 - 10000) + 10000;
            Building newNonHome = new NonHome(rand2, 0);
            cityGraph.addVertex(newNonHome);
        }

        // first populate Homes with Cautious humans.
        int counter1 = 0;
        while (counter1 < numCauHuman) {
            for (int k = 0; k < cityGraph.numVert(); k++) {
                Human newCau = new Cautious(true, false, false, false, Double.valueOf(fatalityR));
                Building currB1 = cityGraph.getBuild(k);
                if (currB1.isHome() && (currB1.isFull() == false)) {
                    if (counter1 < numCauHuman) {
                        currB1.addH(newCau);
                    }
                }
                counter1++;
            }
        }

        // then populate NonHomes with Cautious humans.
        int extraC = numCauHuman - (numHome * 5);
        if (extraC > 0) {
            int counter3 = 0;
            while (counter3 < extraC) {
                for (int l = 0; l < cityGraph.numVert(); l++) {
                    Human newCau = new Cautious(true, false, false, false, Double.valueOf(fatalityR));
                    Building currB2 = cityGraph.getBuild(l);
                    if (!currB2.isHome()) {
                        if (counter3 < extraC) {
                            currB2.addH(newCau);
                            counter3++;
                        }
                    }
                }
            }
        }

        // first populate Homes with Reckless humans.
        int counter2 = 0;
        while (counter2 < numReckHuman) {
            for (int m = 0; m < cityGraph.numVert(); m++) {
                Human newReck = new Reckless(true, false, false, false, Double.valueOf(fatalityR));
                Building currB3 = cityGraph.getBuild(m);
                if (currB3.isHome() && (currB3.isFull() == false)) {
                    if (counter2 < numReckHuman) {
                        currB3.addH(newReck);
                    }
                }
                counter2++;
            }
        }

        // then populate NonHomes with Reckless humans.
        int numCHome = 0;
        if (numCauHuman > numHome * 5) {
            numCHome = numHome * 5;
        } else {
            numCHome = numCauHuman;
        }
        int numRHome = (numHome * 5 - numCHome);
        int extraR = (numReckHuman - numRHome);
        if (extraR > 0) {
            int counter4 = 0;
            while (counter4 < extraR) {
                for (int n = 0; n < cityGraph.numVert(); n++) {
                    Human newReck = new Reckless(true, false, false, false, Double.valueOf(fatalityR));
                    Building currB4 = cityGraph.getBuild(n);
                    if (!currB4.isHome()) {
                        if (counter4 < extraR) {
                            currB4.addH(newReck);
                            counter4++;
                        }
                    }
                }
            }   
        }
        // connects the Buildings in the Graph by adding edges.
        for (int i = 0; i < (numHome + numNonHome); i++) {
            Random rand = new Random();
            int rand3 = rand.nextInt(numHome + numNonHome);
            while (rand3 == i) {
                rand3 = rand.nextInt(numHome + numNonHome);
            }
            cityGraph.addEdge(i, rand3);
        }
    }

    /**
    * Is called in the main method every time the user progresses through a day. Moves Humans from Building to Building, changes the max capacity of some Buildings based on the lockdown level, and performs daily actions on each Building in the City. Each Building then in turn performs actions on each Human.
    */
    public void updateDay() {
        for (int i = 0; i < cityGraph.numVert(); i++) {
            Building currB5 = cityGraph.getBuild(i);
            ArrayList<Human> homeless = currB5.removeH();
            //check capacity of building whenever I add humans
            Building neighbor = cityGraph.getNeighbor(currB5);
            if ((neighbor.getCapa() - neighbor.getNumOccup()) > homeless.size()) {
                neighbor.addHList(homeless);
            } else {
                currB5.addHList(homeless);
            }
            currB5.dailyActions();
            if (!currB5.isHome()) {
                currB5.setLockLevel(lockLevel);
            }
        }
    }

    /**
    * Returns the number of healthy Humans in the City.
    * @return counter, which is the number of healthy Humans in the City.
    */
    public int healthyCount() {
        int counter = 0;
        for (int i = 0; i < cityGraph.numVert(); i++) {
            Building currB = cityGraph.getBuild(i);
            for (int j = 0; j < currB.getNumOccup(); j++) {
                if (currB.getH(j).getH()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
    * Returns the number of infected Humans in the City.
    * @return counter, which is the number of infected Humans in the City.
    */
    public int infectedCount() {
        int counter = 0;
        for (int i = 0; i < cityGraph.numVert(); i++) {
            Building currB = cityGraph.getBuild(i);
            for (int j = 0; j < currB.getNumOccup(); j++) {
                if (currB.getH(j).getI()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
    * Returns the number of recovered Humans in the City.
    * @return counter, which is the number of recovered Humans in the City.
    */
    public int recoveredCount() {
        int counter = 0;
        for (int i = 0; i < cityGraph.numVert(); i++) {
            Building currB = cityGraph.getBuild(i);
            for (int j = 0; j < currB.getNumOccup(); j++) {
                if (currB.getH(j).getR()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
    * Returns the number of dead Humans in the City.
    * @return counter, which is the number of dead Humans in the City.
    */
    public int deadCount() {
        int counter = 0;
        for (int i = 0; i < cityGraph.numVert(); i++) {
            Building currB = cityGraph.getBuild(i);
            for (int j = 0; j < currB.getNumOccup(); j++) {
                if (currB.getH(j).getD()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
    * A static method that takes in user inputs, puts them in an ArrayList, and returns that ArrayList. Is called in the main method. Used to eliminate repeated code in the main method.
    * @return ArrayList<Integer>, which contains all the user inputs.
    */
    public static ArrayList<Integer> help() {
        System.out.print("How many Homes do you want in your city? ");
        Scanner scanner = new Scanner(System.in);

        int numHome = -1;
        while (numHome <= 0 || numHome >= 100) {
            try {
                numHome = Integer.parseInt(scanner.next());
                while (numHome <= 0 || numHome >= 100) {
                    System.err.println("Usage: please input a positive integer that is smaller than 100.");
                    System.out.print("How many Homes do you want in your city? ");
                    numHome = Integer.parseInt(scanner.next());
                }
            } catch (NumberFormatException ex) {
            System.err.println("Usage: please input a positive integer that is smaller than 100.");
            System.out.print("How many Homes do you want in your city? ");
            }
        }

        System.out.print("How many Non_homes do you want in your city? ");
        int numNonHome = -1;

        while (numNonHome <= 0 || numNonHome >= 100) {
            try {
                numNonHome = Integer.parseInt(scanner.next());
                while (numNonHome <= 0 || numNonHome >= 100) {
                    System.err.println("Usage: please input a positive integer that is smaller than 100.");
                    System.out.print("How many Non_homes do you want in your city? ");
                    numNonHome = Integer.parseInt(scanner.next());
                }
            } catch (NumberFormatException ex) {
            System.err.println("Usage: please input a positive integer that is smaller than 100.");
            System.out.print("How many Non-homes do you want in your city? ");
            }
        }

        System.out.print("How many cautious people do you want in your city? ");
        int numCauHuman = -1;

        while (numCauHuman <= 500 || numCauHuman >= 1000000) {
            try {
                numCauHuman = Integer.parseInt(scanner.next());
                while (numCauHuman <= 500 || numCauHuman >= 1000000) {
                    System.err.println("Usage: please input a positive integer that is greater than 500 and smaller than 1,000,000.");
                    System.out.print("How many cautious people do you want in your city? ");
                    numCauHuman = Integer.parseInt(scanner.next());
                }
            } catch (NumberFormatException ex) {
            System.err.println("Usage: please input a positive integer that is greater than 500 and smaller than 1,000,000.");
            System.out.print("How many cautious people do you want in your city? ");
            }
        }

        System.out.print("How many reckless people do you want in your city? ");
        int numReckHuman = -1;

        while (numReckHuman <= 500 || numReckHuman >= 1000000) {
            try {
                numReckHuman = Integer.parseInt(scanner.next());
                while (numReckHuman <= 500 || numReckHuman >= 1000000) {
                    System.err.println("Usage: please input a positive integer that is greater than 500 and smaller than 1,000,000.");
                    System.out.print("How many reckless people do you want in your city? ");
                    numReckHuman = Integer.parseInt(scanner.next());
                }
            } catch (NumberFormatException ex) {
            System.err.println("Usage: please input a positive integer that is greater than 500 and smaller than 1,000,000.");
            System.out.print("How many reckless people do you want in your city? ");
            }
        }

        System.out.print("What do you want the fatality rate of the disease to be? Please input an integer between 0 (exclusive) and 100 (exclusive) ");
        int fatalityR = -1;
        
        while (fatalityR <= 0 || fatalityR >= 100) {
            try {
                fatalityR = Integer.parseInt(scanner.next());
                while (fatalityR <= 0 || fatalityR >= 100) {
                    System.err.println("Usage: Please input an integer between 0 (exclusive) and 100 (exclusive).");
                    System.out.print("What do you want the fatality rate of the disease to be? ");
                    fatalityR = Integer.parseInt(scanner.next());
                }
            } catch (NumberFormatException ex) {
            System.err.println("Usage: Please input an integer between 0 (exclusive) and 100 (exclusive).");
            System.out.print("What do you want the fatality rate of the disease to be? ");
            }
        }

        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        toReturn.add(numHome);
        toReturn.add(numNonHome);
        toReturn.add(numCauHuman);
        toReturn.add(numReckHuman);
        toReturn.add(fatalityR);

        return toReturn;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Pandemic Simulation!");
        System.out.println("You can use this program to build a sim city,");
        System.out.println("and as you progress through each day,");
        System.out.println("you will see how many people are healthy,");
        System.out.println("how many are infected, how many are recovered,");
        System.out.println("and how many are dead. At the end of each day,");
        System.out.println("you can choose a lockdown level between 1 and 5,");
        System.out.println("which will change the capacity of some buildings.");
        System.out.println("If you wish to stop playing,");
        System.out.println("simply type \"save\" at the end of the day!");
        System.out.println("Play away, and have fun!");
        System.out.println("");

        City newCity = new City(0, 0, 0, 0, 0);

        int totalCapa = 0;
        int totalH = 0;
        while (totalCapa <= totalH) {
            ArrayList<Integer> useful1 = help();
            int numHome = useful1.get(0);
            int numNonHome = useful1.get(1);
            int numCauHuman = useful1.get(2);
            int numReckHuman = useful1.get(3);
            int fatalityR = useful1.get(4);

            newCity = new City(numHome, numNonHome, numCauHuman, numReckHuman, fatalityR);

            totalCapa = newCity.countCapa(numHome, numNonHome);
            totalH = numCauHuman + numReckHuman;

            while (totalCapa <= totalH) {
                System.out.println("");
                System.err.println("Too many Humans and not enough Buildings!");
                ArrayList<Integer> useful2 = help();
                numHome = useful2.get(0);
                numNonHome = useful2.get(1);
                numCauHuman = useful2.get(2);
                numReckHuman = useful2.get(3);
                fatalityR = useful2.get(4);
                totalCapa = newCity.countCapa(numHome, numNonHome);
                totalH = numCauHuman + numReckHuman;
            }
        }

        System.out.println("");
        System.out.println("Day 0:");
        System.out.println("Healthy individuals: " + newCity.healthyCount());
        System.out.println("Infected individuals: " + newCity.infectedCount());
        System.out.println("Recovered individuals: " + newCity.recoveredCount());
        System.out.println("Dead individuals: " + newCity.deadCount());
        System.out.println("Please type \"save\" to stop playing, anything else to continue. ");

        Scanner scanner = new Scanner(System.in);

        String saveOrNext = scanner.next().toLowerCase();
        int dayCount = 1;

        while (!saveOrNext.equals("save")) {
            System.out.println("");
            System.out.println("Day " + dayCount + ":");
            System.out.println("Healthy individuals: " + newCity.healthyCount());
            System.out.println("Infected individuals: " + newCity.infectedCount());
            System.out.println("Recovered individuals: " + newCity.recoveredCount());
            System.out.println("Dead individuals: " + newCity.deadCount());
            System.out.println("Please choose your lockdown level (input an integer between 1 and 5: ");
            int lockLevel = 0;
            while (lockLevel < 1 || lockLevel > 5) {
                try {
                lockLevel = Integer.parseInt(scanner.next());
                    while (lockLevel < 1 || lockLevel > 5) {
                        System.err.println("Usage: please input an integer between 1 and 5.");
                        System.out.print("Please choose your lockdown level (input an integer between 1 and 5: ");
                        lockLevel = Integer.parseInt(scanner.next());
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Usage: please input an integer between 1 and 5.");
                    System.out.print("Please choose your lockdown level (input an integer between 1 and 5: ");
                }
            }
            System.out.println("Please type \"save\" to stop playing, anything else to continue. ");
            saveOrNext = scanner.next().toLowerCase();
            dayCount++;
            newCity.updateDay();
        }
    }
}