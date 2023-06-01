package model;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Dijkstra<T> {
    private Graph<T> graph;


    public Dijkstra(Graph<T> graph) {
        this.graph = graph;
    }

    public Pair<Integer, List<T>> shortestPath(T start, T end, int fuelCapacity) {
        Map<T, Pair<Integer, List<T>>> distances = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>(Comparator.comparingInt(VertexDistance::getDistance));

        graph.getVertices().forEach(vertex -> distances.put(vertex, new Pair<>(Integer.MAX_VALUE, new ArrayList<>())));

        distances.replace(start, new Pair<>(0, Arrays.asList(start)));
        pq.add(new VertexDistance<>(start, 0));

        while (!pq.isEmpty()) {
            VertexDistance<T> current = pq.poll();
            T currentVertex = current.getVertex();
            int currentDistance = current.getDistance();
            List<T> currentPath = new ArrayList<>(distances.get(currentVertex).getSecond());

            for (Graph.Edge<T> edge : graph.getNeighbors(currentVertex)) {
                T neighbor = edge.getDestination();
                int fuelNeeded = edge.getWeight();
                int fuelPrice = graph.getFuelPrice(currentVertex);
                int altDistance = currentDistance + (fuelNeeded * fuelPrice);

                if (fuelNeeded <= fuelCapacity && (altDistance < distances.get(neighbor).getFirst())) {
                    List<T> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    distances.replace(neighbor, new Pair<>(altDistance, newPath));
                    pq.add(new VertexDistance<>(neighbor, altDistance));
                }
            }
        }

        if (distances.get(end).getFirst() == Integer.MAX_VALUE) {
            return new Pair<>(-1, new ArrayList<>()); // return -1 and an empty path if it's impossible to reach the end
        } else {
            return distances.get(end);
        }
    }


    public Map<T, Integer> shortestPath(T start) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>(Comparator.comparingInt(VertexDistance::getDistance));

        graph.getVertices().forEach(vertex -> distances.put(vertex, Integer.MAX_VALUE));

        distances.replace(start, 0);
        pq.add(new VertexDistance<>(start, 0));

        while (!pq.isEmpty()) {
            VertexDistance<T> current = pq.poll();

            for (Graph.Edge<T> edge : graph.getNeighbors(current.getVertex())) {
                T neighbor = edge.getDestination();
                int altDistance = distances.get(current.getVertex()) + edge.getWeight();

                if (altDistance < distances.get(neighbor)) {
                    distances.replace(neighbor, altDistance);
                    pq.add(new VertexDistance<>(neighbor, altDistance));
                }
            }
        }

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