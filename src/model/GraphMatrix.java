package model;

import java.util.*;

public class GraphMatrix<T> {
    private Map<T, Map<T, Integer>> adjacencyMatrix;
    private Map<T, Integer> fuelPrices;

    public GraphMatrix() {
        this.adjacencyMatrix = new HashMap<>();
        this.fuelPrices = new HashMap<>();
    }

    public GraphMatrix(Integer[][] matrix, List<T> vertices) {
        this.adjacencyMatrix = new HashMap<>();
        this.fuelPrices = new HashMap<>();

        for (T vertex : vertices) {
            addVertex(vertex);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    addEdge(vertices.get(i), vertices.get(j), matrix[i][j]);
                }
            }
        }
    }

    public void addVertex(T vertex) {
        adjacencyMatrix.putIfAbsent(vertex, new HashMap<>());
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
        List<T> vertices = new ArrayList<>(adjacencyMatrix.keySet());

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                T source = vertices.get(i);
                T destination = vertices.get(j);
                if (adjacencyMatrix.containsKey(source) && adjacencyMatrix.get(source).containsKey(destination)) {
                    matrix[i][j] = adjacencyMatrix.get(source).get(destination);
                }
            }
        }

        return matrix;
    }
}
