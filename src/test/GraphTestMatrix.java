package test;

import model.*;
import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTestMatrix {
    GraphMatrix<String> graph;
    DijkstraMatrix<String> dijkstra;

    @BeforeEach
    public void setup() {
        Integer[][] adjacencyMatrix = {
                {0, 1, 0, 0, 0},
                {0, 0, 2, 0, 0},
                {0, 0, 0, 5, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };

        List<String> vertices = Arrays.asList("A", "B", "C", "D", "E");

        graph = new GraphMatrix<>(adjacencyMatrix, vertices);
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
        assertEquals(8, distancesFromA.get("D"));  // changed this line
        assertEquals(Integer.MAX_VALUE, distancesFromA.get("E"));
    }

    @Test
    public void testGetFuelPrice() {
        assertEquals(1, graph.getFuelPrice("A"));
        assertEquals(2, graph.getFuelPrice("B"));
        assertEquals(3, graph.getFuelPrice("C"));
        assertEquals(0, graph.getFuelPrice("D"));
        assertEquals(0, graph.getFuelPrice("E"));
    }

    @Test
    public void testSetFuelPrice() {
        graph.setFuelPrice("A", 10);
        assertEquals(10, graph.getFuelPrice("A"));
    }

    @Test
    public void testAddVertex() {
        GraphMatrix<Integer> graph = new GraphMatrix<>(new Integer[0][0], new ArrayList<>());
        graph.addVertex(1);
        assertTrue(graph.getVertices().contains(1));
    }

    @Test
    public void testAddDuplicateVertex() {
        GraphMatrix<Integer> graph = new GraphMatrix<>(new Integer[0][0], new ArrayList<>());
        graph.addVertex(1);
        assertFalse(graph.getVertices().contains(1));
    }

    @Test
    public void testAddEdge() {
        GraphMatrix<String> graph = new GraphMatrix<>(new Integer[0][0], new ArrayList<>());
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addEdge("1", "2", 3);
        assertTrue(graph.getEdgeWeight("1", "2") == 3);
    }

    @Test
    public void testGetNeighbors() {
        GraphMatrix<String> graph = new GraphMatrix<>(new Integer[0][0], new ArrayList<>());
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addEdge("1", "2", 3);
        assertTrue(graph.getNeighbors("1").contains("2"));
    }

    @Test
    public void testGetEdgeWeight() {
        GraphMatrix<String> graph = new GraphMatrix<>(new Integer[0][0], new ArrayList<>());
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addEdge("1", "2", 3);
        assertEquals(3, graph.getEdgeWeight("1", "2"));
    }

    @Test
    public void testGetNonexistentEdgeWeight() {
        GraphMatrix<String> graph = new GraphMatrix<>(new Integer[0][0], new ArrayList<>());
        graph.addVertex("1");
        graph.addVertex("2");
        assertEquals(-1, graph.getEdgeWeight("1", "2"));
    }

    @Test
    public void testSetFuelPriceWithNegativeValue() {
        graph.setFuelPrice("A", -10);
        assertEquals(-10, graph.getFuelPrice("A"));
    }
}
