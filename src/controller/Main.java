package controller;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Controller controller = new Controller();

    public static void main(String[] args) {
        System.out.println("===== FullTank Menu =====");

        int choice;
        do {
            printMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    createAdjacencyListGraph();
                    break;
                case 2:
                    createAdjacencyMatrixGraph();
                    break;
                case 3:
                    findShortestPath();
                    break;
                case 4:
                    findShortestPathWithRestriction();
                    break;
                case 5:
                    printAdjacencyMatrix();
                    break;
                case 6:
                    printVertices();
                    break;
                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("1. Create Adjacency List Graph");
        System.out.println("2. Create Adjacency Matrix Graph");
        System.out.println("3. Find Shortest Path");
        System.out.println("4. Find Shortest Path with Fuel Restriction");
        System.out.println("5. Print Adjacency Matrix");
        System.out.println("6. Print Vertices");
        System.out.println("0. Exit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void createAdjacencyListGraph() {
        Map<String, List<String>> adjacencyList = new HashMap<>();
        Map<String, Integer> fuelPrices = new HashMap<>();

        System.out.print("Enter the number of vertices: ");
        int numVertices = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numVertices; i++) {
            System.out.print("Enter the vertex name: ");
            String vertex = scanner.nextLine();
            adjacencyList.put(vertex, new ArrayList<>());

            System.out.print("Enter the number of neighbors for vertex " + vertex + ": ");
            int numNeighbors = Integer.parseInt(scanner.nextLine());

            for (int j = 0; j < numNeighbors; j++) {
                System.out.print("Enter neighbor " + (j + 1) + ": ");
                String neighbor = scanner.nextLine();
                adjacencyList.get(vertex).add(neighbor);
            }

            System.out.print("Enter the fuel price for vertex " + vertex + ": ");
            int fuelPrice = Integer.parseInt(scanner.nextLine());
            fuelPrices.put(vertex, fuelPrice);
        }

        controller.createAdjacencyListGraph(adjacencyList, fuelPrices);
        System.out.println("Adjacency list graph created successfully.");
    }

    private static void createAdjacencyMatrixGraph() {
        System.out.print("Enter the number of vertices: ");
        int numVertices = Integer.parseInt(scanner.nextLine());

        Integer[][] adjacencyMatrix = new Integer[numVertices][numVertices];
        List<String> vertices = new ArrayList<>();
        Map<String, Integer> fuelPrices = new HashMap<>();

        // First, input all vertices and their fuel prices
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Enter vertex " + (i + 1) + ": ");
            String vertex = scanner.nextLine();
            vertices.add(vertex);

            System.out.print("Enter the fuel price for vertex " + vertex + ": ");
            int fuelPrice = Integer.parseInt(scanner.nextLine());
            fuelPrices.put(vertex, fuelPrice);
        }

        // Then, input all edges
        for (int i = 0; i < numVertices; i++) {
            String vertex = vertices.get(i);
            System.out.print("Enter the number of neighbors for vertex " + vertex + ": ");
            int numNeighbors = Integer.parseInt(scanner.nextLine());

            for (int j = 0; j < numNeighbors; j++) {
                System.out.print("Enter neighbor " + (j + 1) + ": ");
                String neighbor = scanner.nextLine();

                // Validate that the neighbor is an existing vertex
                if (!vertices.contains(neighbor)) {
                    System.out.println("The neighbor must be an existing vertex. Please try again.");
                    j--; // redo this iteration
                    continue;
                }

                System.out.print("Enter the weight for the edge between " + vertex + " and " + neighbor + ": ");
                int weight = Integer.parseInt(scanner.nextLine());
                adjacencyMatrix[i][vertices.indexOf(neighbor)] = weight;
            }
        }

        controller.createAdjacencyMatrixGraph(adjacencyMatrix, vertices, fuelPrices);
        System.out.println("Adjacency matrix graph created successfully.");
    }



    private static void findShortestPath() {
        System.out.print("Enter the start vertex: ");
        String startVertex = scanner.nextLine();
        Map<String, Integer> shortestPath = controller.findShortestPath(startVertex);

        System.out.println("Shortest path from " + startVertex + ":");
        for (Map.Entry<String, Integer> entry : shortestPath.entrySet()) {
            String vertex = entry.getKey();
            int distance = entry.getValue();
            System.out.println("Vertex: " + vertex + ", Distance: " + distance);
        }
    }

    private static void findShortestPathWithRestriction() {
        System.out.print("Enter the start vertex: ");
        String startVertex = scanner.nextLine();

        System.out.print("Enter the fuel capacity: ");
        int fuelCapacity = Integer.parseInt(scanner.nextLine());

        Map<String, Integer> shortestPath = controller.findShortestPathRestriction(startVertex, fuelCapacity);

        System.out.println("Shortest path from " + startVertex + " with fuel capacity " + fuelCapacity + ":");
        for (Map.Entry<String, Integer> entry : shortestPath.entrySet()) {
            String vertex = entry.getKey();
            int distance = entry.getValue();
            System.out.println("Vertex: " + vertex + ", Distance: " + distance);
        }
    }

    private static void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = controller.getAdjacencyMatrix();

        if (adjacencyMatrix != null) {
            System.out.println("Adjacency Matrix:");
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Adjacency list does not have an adjacency matrix representation.");
        }
    }

    private static void printVertices() {
        List<String> vertices = controller.getVertices();

        if (vertices != null) {
            System.out.println("Vertices:");
            for (String vertex : vertices) {
                System.out.println(vertex);
            }
        } else {
            System.out.println("No vertices found.");
        }
    }
}
