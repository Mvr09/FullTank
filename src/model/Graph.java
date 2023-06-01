package model;
import java.util.*;

public class Graph<T> {

    private Map<T, List<Edge<T>>> adjacencyList;
    private Map<T, Integer> fuelPrices;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.fuelPrices = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void setFuelPrice(T vertex, int fuelPrice) {
        fuelPrices.put(vertex, fuelPrice);
    }

    public int getFuelPrice(T vertex) {
        return fuelPrices.get(vertex);
    }

    public void addEdge(T source, T destination, int weight) {
        adjacencyList.get(source).add(new Edge<>(destination, weight));
    }

    public List<Edge<T>> getNeighbors(T vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<T> getVertices() {
        return adjacencyList.keySet();
    }

    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean containsEdge(T source, T destination) {
        return adjacencyList.get(source).stream().anyMatch(edge -> edge.getDestination().equals(destination));
    }

    public static class Edge<T> {
        private T destination;
        private int weight;

        public Edge(T destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public T getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }


}