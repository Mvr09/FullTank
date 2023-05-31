package test;

import model.*;
import org.junit.jupiter.api.*;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTestMatrix {
    private GraphMatrix<String> graph;
    private DijkstraMatrix<String> dijkstra;

    @BeforeEach
    public void setUp() {
        graph = new GraphMatrix<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 5);
        graph.addEdge("D", "E", 1);

        dijkstra = new DijkstraMatrix<>(graph);
    }

    @Test
    public void testGraphVertices() {
        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.containsVertex("B"));
        assertTrue(graph.containsVertex("C"));
        assertFalse(graph.containsVertex("Z"));
    }

    @Test
    public void testGraphEdges() {
        assertTrue(graph.containsEdge("A", "B"));
        assertTrue(graph.containsEdge("A", "C"));
        assertFalse(graph.containsEdge("A", "E"));
        assertEquals(1, graph.getEdgeWeight("A", "B"));
        assertEquals(3, graph.getEdgeWeight("A", "C"));
    }

    @Test
    public void testGraphNeighbors() {
        assertEquals(2, graph.getNeighbors("A").size());
        assertEquals(1, graph.getNeighbors("B").size());
        assertEquals(1, graph.getNeighbors("C").size());
        assertEquals(1, graph.getNeighbors("D").size());
        assertEquals(0, graph.getNeighbors("E").size());
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
}
