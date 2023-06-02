package model;

import java.util.*;

public class DijkstraWithFuel<T> {
    private Graph<T> graph;
    private int fuelCapacity;
    private int fuelLeft;

    public DijkstraWithFuel(Graph<T> graph, int fuelCapacity) {
        this.graph = graph;
        this.fuelCapacity = fuelCapacity;
    }

    public Map<T, Integer> cheapestPath(T start, T end) {
        Map<T, Integer> costs = new HashMap<>();
        fuelLeft = fuelCapacity;

        dfs(start, end, costs, 0);
        return costs;
    }

    private void dfs(T current, T end, Map<T, Integer> costs, int currentCost) {
        if (current.equals(end)) {
            costs.put(current, currentCost);
            return;
        }

        for (Graph.Edge<T> edge : graph.getNeighbors(current)) {
            T neighbor = edge.getDestination();
            int cost = edge.getWeight() * graph.getFuelPrice(current);
            if (cost <= fuelLeft) {
                fuelLeft -= cost;
                dfs(neighbor, end, costs, currentCost + cost);
                fuelLeft += cost;
            } else {
                int costWithRefuel = (fuelCapacity - fuelLeft) * graph.getFuelPrice(current) + cost;
                fuelLeft = fuelCapacity - cost;
                dfs(neighbor, end, costs, currentCost + costWithRefuel);
                fuelLeft = fuelCapacity;
            }
        }
    }
}
