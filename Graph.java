import java.util.*;

public class Graph{
    private int numOfBuildings = 0;
    private int[][] adjMatrix;
    ArrayList<Building> vertexList = new ArrayList<Building>();
    private int numVertex = 0;

    /**
    * Constructor for Graph class.
    * @param numOfBuildings of int type.
    */
    public Graph(int numOfBuildings) {
        this.numOfBuildings = numOfBuildings;
        adjMatrix = new int[numOfBuildings][numOfBuildings];
    }

    /**
    * Adds a Building to the Graph. Increments the number of vertices in the Graph.
    * @param newBuilding of Building type.
    */
    public void addVertex(Building newBuilding) {
        vertexList.add(newBuilding);
        numVertex++;
    }

    /**
    * Adds an edge between two Buildings.
    * @param start of int type.
    * @param end of int type.
    */
    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    /**
    * Returns the neighbor of the current Building.
    * @param curBuild of Building type.
    * @return the neighbor of the current Building, of Building type.
    */
    public Building getNeighbor(Building curBuild) {
        int index = vertexList.indexOf(curBuild);
        Building toReturn = vertexList.get(0);
        for (int i = 0; i < numOfBuildings; i++) {
            if (adjMatrix[index][i] == 1) {
                toReturn = vertexList.get(i);
            }
        }
        return toReturn;
    }

    /**
    * Returns the number of vertices in the Graph.
    * @return numVertex of int type.
    */
    public int numVert() {
        return numVertex;
    }

    /**
    * Returns the Building at the given index.
    * @param index of int type.
    * @return the Building at the given index.
    */
    public Building getBuild(int index) {
        return vertexList.get(index);
    }
}