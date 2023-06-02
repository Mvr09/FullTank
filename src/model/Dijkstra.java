package model;

import java.util.*;

public class Dijkstra<T> {
    private Graph<T> graph;

    public Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    public Map<T, Integer> shortestPath(T start) {
        Map<T, Integer> distances = new HashMap<>();
        Map<T, Integer> fuelCosts = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>(Comparator.comparingInt(VertexDistance::getDistance));

        graph.getVertices().forEach(vertex -> {
            distances.put(vertex, Integer.MAX_VALUE);
            fuelCosts.put(vertex, Integer.MAX_VALUE);
        });

        distances.replace(start, 0);
        fuelCosts.replace(start, 0);
        pq.add(new VertexDistance<>(start, 0));

        while (!pq.isEmpty()) {
            VertexDistance<T> current = pq.poll();

            for (Graph.Edge<T> edge : graph.getNeighbors(current.getVertex())) {
                T neighbor = edge.getDestination();
                int altDistance = distances.get(current.getVertex()) + edge.getWeight();
                int fuelCost = fuelCosts.get(current.getVertex()) + graph.getFuelPrice(current.getVertex()) * edge.getWeight();

                if ((altDistance < distances.get(neighbor) || fuelCost < fuelCosts.get(neighbor))) {
                    distances.replace(neighbor, altDistance);
                    fuelCosts.replace(neighbor, fuelCost);
                    pq.add(new VertexDistance<>(neighbor, altDistance));
                }
            }
        }

        return distances;
    }

    public Map<T, Integer> shortestPathRestriction(T start, int fuelCapacity) {
        Map<T, Integer> distances = new HashMap<>();
        Map<T, Integer> fuelCosts = new HashMap<>();
        Set<T> visited = new HashSet<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>(Comparator.comparingInt(VertexDistance::getDistance));

        graph.getVertices().forEach(vertex -> {
            distances.put(vertex, Integer.MAX_VALUE);
            fuelCosts.put(vertex, Integer.MAX_VALUE);
        });

        distances.replace(start, 0);
        fuelCosts.replace(start, 0);
        pq.add(new VertexDistance<>(start, 0));
        visited.add(start);

        while (!pq.isEmpty()) {
            VertexDistance<T> current = pq.poll();

            for (Graph.Edge<T> edge : graph.getNeighbors(current.getVertex())) {
                T neighbor = edge.getDestination();
                int altDistance = distances.get(current.getVertex()) + edge.getWeight();
                int fuelCost = fuelCosts.get(current.getVertex()) + graph.getFuelPrice(current.getVertex()) * edge.getWeight();

                if (altDistance <= fuelCapacity && (altDistance < distances.get(neighbor) || fuelCost < fuelCosts.get(neighbor))) {
                    distances.replace(neighbor, altDistance);
                    fuelCosts.replace(neighbor, fuelCost);
                    pq.add(new VertexDistance<>(neighbor, altDistance));
                    visited.add(neighbor);
                }
            }
        }

        distances.keySet().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                distances.replace(vertex, Integer.MAX_VALUE);
            }
        });

        return distances;
    }



    private static class VertexDistance<T> {
        private T vertex;
        private int distance;

        public VertexDistance(T vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public T getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }
    }
}