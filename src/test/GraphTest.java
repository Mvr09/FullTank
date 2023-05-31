package test;

import model.*;

import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    private Graph<String> graph;
    private Dijkstra<String> dijkstra;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("A", "C", 3);
        graph.addEdge("C", "D", 2);
        graph.addEdge("D", "E", 3);
        graph.addEdge("A", "E", 8);

        dijkstra = new Dijkstra<>(graph);
    }

    @Test
    public void testGraph() {
        assertEquals(3, graph.getNeighbors("A").size());
        assertEquals(1, graph.getNeighbors("B").size());
        assertEquals(1, graph.getNeighbors("C").size());
        assertEquals(1, graph.getNeighbors("D").size());
        assertEquals(0, graph.getNeighbors("E").size());
    }


    @Test
    public void testContainsVertex() {
        assertTrue(graph.containsVertex("A"));
        assertTrue(graph.containsVertex("B"));
        assertTrue(graph.containsVertex("C"));
        assertFalse(graph.containsVertex("Z"));
    }

    @Test
    public void testContainsEdge() {
        assertTrue(graph.containsEdge("A", "B"));
        assertTrue(graph.containsEdge("B", "C"));
        assertFalse(graph.containsEdge("A", "D"));
    }

    @Test
    public void testDijkstra() {
        Map<String, Integer> distances = dijkstra.shortestPath("A");
        assertEquals(0, (int) distances.get("A"));
        assertEquals(1, (int) distances.get("B"));
        assertEquals(3, (int) distances.get("C"));
        assertEquals(5, (int) distances.get("D"));
        assertEquals(8, (int) distances.get("E"));
    }
}
