package model;

import java.util.*;

public class DijkstraMatrix<T> {
    private GraphMatrix<T> graph;

    public DijkstraMatrix(GraphMatrix<T> graph) {
        this.graph = graph;
    }

    public Map<T, Integer> shortestPath(T start) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>(Comparator.comparingInt(VertexDistance::getDistance));

        graph.getVertices().forEach(vertex -> distances.put(vertex, Integer.MAX_VALUE));

        distances.replace(start, 0);
        pq.add(new VertexDistance<>(start, 0));

        while (!pq.isEmpty()) {
            VertexDistance<T> current = pq.poll();

            for (T neighbor : graph.getNeighbors(current.getVertex())) {
                int altDistance = distances.get(current.getVertex()) + graph.getEdgeWeight(current.getVertex(), neighbor);

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