package controller;

import model.*;

import java.util.*;

public class Controller<T> {
    private Graph<T> graph;
    private GraphMatrix<T> graphMatrix;
    private Dijkstra<T> dijkstra;
    private DijkstraMatrix<T> dijkstraMatrix;

    public Controller() {
        this.graph = new Graph<>();
        this.graphMatrix = new GraphMatrix<>();
    }

    // Add a vertex to both Graph and GraphMatrix
    public void addVertex(T vertex) {
        graph.addVertex(vertex);
        graphMatrix.addVertex(vertex);
    }

    // Set the fuel price for a vertex in both Graph and GraphMatrix
    public void setFuelPrice(T vertex, int fuelPrice) {
        graph.setFuelPrice(vertex, fuelPrice);
        graphMatrix.setFuelPrice(vertex, fuelPrice);
    }

    // Add an edge to both Graph and GraphMatrix
    public void addEdge(T source, T destination, int weight) {
        graph.addEdge(source, destination, weight);
        graphMatrix.addEdge(source, destination, weight);
    }

    // Compute the shortest path in Graph
    public Pair<Integer, List<T>> computeShortestPathInGraph(T start, T end, int fuelCapacity) {
        this.dijkstra = new Dijkstra<>(graph);
        return dijkstra.shortestPath(start, end, fuelCapacity);
    }

    // Compute the shortest path in GraphMatrix
    public Map<T, Integer> computeShortestPathInGraphMatrix(T start) {
        this.dijkstraMatrix = new DijkstraMatrix<>(graphMatrix);
        return dijkstraMatrix.shortestPath(start);
    }
}
