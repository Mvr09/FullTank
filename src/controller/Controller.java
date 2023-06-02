package controller;

import model.*;

import java.util.*;

public class Controller {
    private Graph<String> adjacencyListGraph;
    private GraphMatrix<String> adjacencyMatrixGraph;

    public Controller() {
        this.adjacencyListGraph = new Graph<>();
        this.adjacencyMatrixGraph = null;
    }

    public void createAdjacencyListGraph(Map<String, List<String>> adjacencyList, Map<String, Integer> fuelPrices) {
        for (String vertex : adjacencyList.keySet()) {
            adjacencyListGraph.addVertex(vertex);
        }

        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            String source = entry.getKey();
            List<String> destinations = entry.getValue();
            for (String destination : destinations) {
                int weight = 1;
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
        this.adjacencyMatrixGraph = new GraphMatrix<>(adjacencyMatrix, vertices);

        for (Map.Entry<String, Integer> entry : fuelPrices.entrySet()) {
            String vertex = entry.getKey();
            int fuelPrice = entry.getValue();
            this.adjacencyMatrixGraph.setFuelPrice(vertex, fuelPrice);
        }
    }

    public Map<String, Integer> findShortestPath(String startVertex) {
        if (adjacencyMatrixGraph != null) {
            DijkstraMatrix<String> dijkstra = new DijkstraMatrix<>(adjacencyMatrixGraph);
            return dijkstra.shortestPath(startVertex);
        } else {
            Dijkstra<String> dijkstra = new Dijkstra<>(adjacencyListGraph);
            return dijkstra.shortestPath(startVertex);
        }
    }

    public Map<String, Integer> findShortestPathRestriction(String startVertex, int fuelCapacity) {
        if (adjacencyMatrixGraph != null) {
            DijkstraMatrix<String> dijkstra = new DijkstraMatrix<>(adjacencyMatrixGraph);
            return dijkstra.shortestPathRestriction(startVertex, fuelCapacity);
        } else {
            Dijkstra<String> dijkstra = new Dijkstra<>(adjacencyListGraph);
            return dijkstra.shortestPathRestriction(startVertex, fuelCapacity);
        }
    }

    public int[][] getAdjacencyMatrix() {
        if (adjacencyMatrixGraph != null) {
            return adjacencyMatrixGraph.getAdjacencyMatrix();
        }
        return null;
    }

    public List<String> getVertices() {
        if (adjacencyMatrixGraph != null) {
            return adjacencyMatrixGraph.getVertices();
        } else {
            return new ArrayList<>(adjacencyListGraph.getVertices());
        }
    }

}
