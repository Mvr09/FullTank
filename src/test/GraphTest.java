package test;

import model.*;
import org.junit.jupiter.api.*;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    Graph<String> graph;
    Dijkstra<String> dijkstra;

    @BeforeEach
    public void setup() {
        graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.setFuelPrice("A", 1);
        graph.setFuelPrice("B", 2);
        graph.setFuelPrice("C", 3);
        dijkstra = new Dijkstra<>(graph);
    }
    @Test
    public void testAddVertex() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        assertTrue(graph.getVertices().contains(1));
    }
    
    @Test
    public void testAddVertexDuplicate() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        assertFalse(graph.getVertices().contains(1));
    }

    @Test
    public void testAddEdge() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 3);
        assertTrue(graph.getNeighbors(1).stream().anyMatch(e -> e.getDestination().equals(2) && e.getWeight() == 3));
    }

    @Test
    public void testGetNeighbors() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 3);
        assertTrue(graph.getNeighbors(1).stream().anyMatch(e -> e.getDestination().equals(2) && e.getWeight() == 3));
    }

    @Test
    public void testDijkstraShortestPath() {
        Map<String, Integer> distancesFromA = dijkstra.shortestPath("A");
        assertEquals((Integer)0, distancesFromA.get("A"));
        assertEquals((Integer)1, distancesFromA.get("B"));
        assertEquals((Integer)3, distancesFromA.get("C"));
    }

    @Test
    public void testDijkstraShortestPathWithFuelRestriction() {
        Map<String, Integer> distancesFromA = dijkstra.shortestPathRestriction("A", 2);
        assertEquals((Integer)0, distancesFromA.get("A"));
        assertEquals((Integer)1, distancesFromA.get("B"));
        assertEquals((Integer)Integer.MAX_VALUE, distancesFromA.get("C"));
    }

    @Test
    public void testGetFuelPrice() {
        assertEquals((Integer)1, graph.getFuelPrice("A"));
        assertEquals((Integer)2, graph.getFuelPrice("B"));
        assertEquals((Integer)3, graph.getFuelPrice("C"));
    }

    @Test
    public void testSetFuelPrice() {
        graph.setFuelPrice("A", 10);
        assertEquals((Integer)10, graph.getFuelPrice("A"));
    }
    @Test
    public void testDijkstraShortestPathUnreachableDestination() {
        Map<String, Integer> distancesFromA = dijkstra.shortestPath("D");
        assertEquals((Integer) 0, distancesFromA.get("A"));
        assertEquals((Integer) 1, distancesFromA.get("B"));
        assertNull(distancesFromA.get("C"));
        assertNull(distancesFromA.get("D"));
    }
}
