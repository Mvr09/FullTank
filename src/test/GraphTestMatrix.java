package test;

import model.*;
import org.junit.jupiter.api.*;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTestMatrix {
    GraphMatrix<String> graph;
    DijkstraMatrix<String> dijkstra;

    @BeforeEach
    public void setup() {
        graph = new GraphMatrix<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 5);
        graph.addEdge("D", "E", 1);
        graph.setFuelPrice("A", 1);
        graph.setFuelPrice("B", 2);
        graph.setFuelPrice("C", 3);
        graph.setFuelPrice("D", 4);
        graph.setFuelPrice("E", 5);
        dijkstra = new DijkstraMatrix<>(graph);
    }
    @Test
    public void testDijkstraShortestPath() {
        Map<String, Integer> distancesFromA = dijkstra.shortestPath("A");
        assertEquals(0, distancesFromA.get("A"));
        assertEquals(1, distancesFromA.get("B"));
        assertEquals(3, distancesFromA.get("C"));
        assertEquals(8, distancesFromA.get("D"));
        assertEquals(9, distancesFromA.get("E"));
    }

    @Test
    public void testDijkstraShortestPathWithFuelRestriction() {
        Map<String, Integer> distancesFromA = dijkstra.shortestPathRestriction("A", 8);
        assertEquals(0, distancesFromA.get("A"));
        assertEquals(1, distancesFromA.get("B"));
        assertEquals(3, distancesFromA.get("C"));
        assertEquals(Integer.MAX_VALUE, distancesFromA.get("D"));
        assertEquals(Integer.MAX_VALUE, distancesFromA.get("E"));
    }

    @Test
    public void testGetFuelPrice() {
        assertEquals(1, graph.getFuelPrice("A"));
        assertEquals(2, graph.getFuelPrice("B"));
        assertEquals(3, graph.getFuelPrice("C"));
        assertEquals(4, graph.getFuelPrice("D"));
        assertEquals(5, graph.getFuelPrice("E"));
    }

    @Test
    public void testSetFuelPrice() {
        graph.setFuelPrice("A", 10);
        assertEquals(10, graph.getFuelPrice("A"));
    }

    @Test
    public void testAddVertex() {
        GraphMatrix<Integer> graph = new GraphMatrix<>();
        graph.addVertex(1);
        assertTrue(graph.getVertices().contains(1));
    }

    @Test
    public void testAddEdge() {
        GraphMatrix<Integer> graph = new GraphMatrix<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 3);
        assertTrue(graph.getEdgeWeight(1, 2) == 3);
    }

    @Test
    public void testGetNeighbors() {
        GraphMatrix<Integer> graph = new GraphMatrix<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 3);
        assertTrue(graph.getNeighbors(1).contains(2));
    }

    @Test
    public void testGetEdgeWeight() {
        GraphMatrix<Integer> graph = new GraphMatrix<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 3);
        assertEquals(3, graph.getEdgeWeight(1, 2));
    }
}
