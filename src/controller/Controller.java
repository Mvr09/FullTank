package controller;

import model.*;

import java.util.*;

public class Controller {
    private Graph<String> adjacencyListGraph;
    private GraphMatrix<String> adjacencyMatrixGraph;

    public Controller() {
        this.adjacencyListGraph = new Graph<>();
        this.adjacencyMatrixGraph = new GraphMatrix<>();
    }

    public void createAdjacencyListGraph(Map<String, List<String>> adjacencyList, Map<String, Integer> fuelPrices) {
        for (String vertex : adjacencyList.keySet()) {
            adjacencyListGraph.addVertex(vertex);
        }

        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            String source = entry.getKey();
            List<String> destinations = entry.getValue();
            for (String destination : destinations) {
                int weight = 1; // Default weight for adjacency list representation
                adjacencyListGraph.addEdge(source, destination, weight);
            }
        }

        for (Map.Entry<String, Integer> entry : fuelPrices.entrySet()) {
            String vertex = entry.getKey();
            int fuelPrice = entry.getValue();
            adjacencyListGraph.setFuelPrice(vertex, fuelPrice);
        }
    }

    public void createAdjacencyMatrixGraph(Integer[][] adjacencyMatrix, List<String> vertices, Map<String, Integer> fuelPrices) {
        GraphMatrix<String> graphMatrix = new GraphMatrix<>(adjacencyMatrix, vertices);

        for (Map.Entry<String, Integer> entry : fuelPrices.entrySet()) {
            String vertex = entry.getKey();
            int fuelPrice = entry.getValue();
            graphMatrix.setFuelPrice(vertex, fuelPrice);
        }

        adjacencyMatrixGraph = graphMatrix;
    }


    public Map<String, Integer> findShortestPath(String startVertex) {
        Dijkstra<String> dijkstra = new Dijkstra<>(adjacencyListGraph);
        return dijkstra.shortestPath(startVertex);
    }

    public Map<String, Integer> findShortestPathRestriction(String startVertex, int fuelCapacity) {
        Dijkstra<String> dijkstra = new Dijkstra<>(adjacencyListGraph);
        return dijkstra.shortestPathRestriction(startVertex, fuelCapacity);
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrixGraph.getAdjacencyMatrix();
    }

    public List<String> getVertices() {
        return new ArrayList<>(adjacencyMatrixGraph.getVertices());
    }
}
