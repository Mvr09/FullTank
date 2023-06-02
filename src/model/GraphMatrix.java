package model;

import java.util.*;

public class GraphMatrix<T> {
    private final List<T> vertices;
    private int[][] adjacencyMatrix;
    private final Map<T, Integer> fuelPrices;

    public GraphMatrix(Integer[][] adjacencyMatrix, List<T> vertices) {
        this.adjacencyMatrix = new int[adjacencyMatrix.length][adjacencyMatrix.length];
        this.vertices = new ArrayList<>(vertices);
        this.fuelPrices = new HashMap<>();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                this.adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
    }

    public void addVertex(T vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            extendAdjacencyMatrix();
        }
    }

    public void addEdge(T source, T destination, int weight) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        if (sourceIndex != -1 && destinationIndex != -1) {
            adjacencyMatrix[sourceIndex][destinationIndex] = weight;
        }
    }

    public Set<T> getNeighbors(T vertex) {
        Set<T> neighbors = new HashSet<>();
        int vertexIndex = vertices.indexOf(vertex);

        if (vertexIndex != -1) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertexIndex][i] != 0) {
                    neighbors.add(vertices.get(i));
                }
            }
        }

        return neighbors;
    }

    public int getEdgeWeight(T source, T destination) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        if (sourceIndex != -1 && destinationIndex != -1) {
            int weight = adjacencyMatrix[sourceIndex][destinationIndex];
            return weight > 0 ? weight : Integer.MAX_VALUE;
        }

        return Integer.MAX_VALUE;
    }

    public List<T> getVertices() {
        return new ArrayList<>(vertices);
    }

    public int getFuelPrice(T vertex) {
        Integer price = fuelPrices.get(vertex);
        return price != null ? price : 0;
    }

    public void setFuelPrice(T vertex, int price) {
        if (vertices.contains(vertex) && price >= 0) {
            fuelPrices.put(vertex, price);
        }
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    private void extendAdjacencyMatrix() {
        int newSize = vertices.size();
        int[][] newMatrix = new int[newSize][newSize];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, adjacencyMatrix[i].length);
        }

        adjacencyMatrix = newMatrix;
    }
}
