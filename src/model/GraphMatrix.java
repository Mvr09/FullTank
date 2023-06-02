
package model;

import java.util.*;

public class GraphMatrix<T> {
    private Map<T, Map<T, Integer>> adjacencyMatrix;
    private Map<T, Integer> indexMap;
    private Map<T, Integer> fuelPrices;
    private int index;

    public GraphMatrix() {
        this.adjacencyMatrix = new HashMap<>();
        this.indexMap = new HashMap<>();
        this.fuelPrices = new HashMap<>();
        this.index = 0;
    }

    public void addVertex(T vertex) {
        adjacencyMatrix.putIfAbsent(vertex, new HashMap<>());
        indexMap.put(vertex, index++);
    }

    public void addEdge(T source, T destination, int weight) {
        adjacencyMatrix.get(source).put(destination, weight);
    }

    public Set<T> getNeighbors(T vertex) {
        return adjacencyMatrix.get(vertex).keySet();
    }

    public Set<T> getVertices() {
        return adjacencyMatrix.keySet();
    }

    public boolean containsVertex(T vertex) {
        return adjacencyMatrix.containsKey(vertex);
    }

    public boolean containsEdge(T source, T destination) {
        return adjacencyMatrix.get(source).containsKey(destination);
    }

    public int getEdgeWeight(T source, T destination) {
        return adjacencyMatrix.get(source).get(destination);
    }

    public void setFuelPrice(T vertex, int fuelPrice) {
        fuelPrices.put(vertex, fuelPrice);
    }

    public int getFuelPrice(T vertex) {
        return fuelPrices.get(vertex);
    }

    public int[][] getAdjacencyMatrix() {
        int size = adjacencyMatrix.size();
        int[][] matrix = new int[size][size];

        for (T source : adjacencyMatrix.keySet()) {
            for (T destination : adjacencyMatrix.get(source).keySet()) {
                int i = indexMap.get(source);
                int j = indexMap.get(destination);
                matrix[i][j] = adjacencyMatrix.get(source).get(destination);
            }
        }

        return matrix;
    }
}
