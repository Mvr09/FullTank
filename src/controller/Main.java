package controller;

import java.util.*;

public class Main {
    private static Scanner scanner;
    private static Controller controller;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        controller = new Controller();

        int choice;
        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

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
                    findShortestPathRestriction();
                    break;
                case 5:
                    printAdjacencyMatrix();
                    break;
                case 6:
                    printVertices();
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("===== FullTank Menu =====");
        System.out.println("1. Create Adjacency List Graph");
        System.out.println("2. Create Adjacency Matrix Graph");
        System.out.println("3. Find Shortest Path");
        System.out.println("4. Find Shortest Path with Fuel Restriction");
        System.out.println("5. Print Adjacency Matrix");
        System.out.println("6. Print Vertices");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createAdjacencyListGraph() {
        System.out.println("Enter the adjacency list in the following format:");
        System.out.println("<vertex> <neighbor1> <weight1> <neighbor2> <weight2> ...");
        System.out.println("For example, to represent the graph:");
        System.out.println("A -> B (weight 1), C (weight 2)");
        System.out.println("B -> C (weight 3)");
        System.out.println("Enter 'A B 1 C 2 B C 3'");

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Map<String, List<String>> adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Enter vertex " + (i + 1) + ": ");
            String vertex = scanner.next();
            List<String> neighbors = new ArrayList<>();

            System.out.print("Enter neighbors and weights (separated by spaces): ");
            for (int j = 0; j < numVertices; j++) {
                String neighbor = scanner.next();
                if (!neighbor.equals("-")) {
                    neighbors.add(neighbor);
                }
            }

            adjacencyList.put(vertex, neighbors);
        }

        Map<String, Integer> fuelPrices = new HashMap<>();
        for (String vertex : adjacencyList.keySet()) {
            System.out.print("Enter fuel price for vertex " + vertex + ": ");
            int fuelPrice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            fuelPrices.put(vertex, fuelPrice);
        }

        controller.createAdjacencyListGraph(adjacencyList, fuelPrices);
        System.out.println("Adjacency list graph created successfully.");
    }

    private static void createAdjacencyMatrixGraph() {
        System.out.println("Enter the adjacency matrix as comma-separated values (CSV) for each row.");
        System.out.println("For example, to input the row [1, 2, 3], enter '1,2,3'.");

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<String> vertices = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Enter vertex " + (i + 1) + ": ");
            String vertex = scanner.nextLine();
            vertices.add(vertex);
        }

        Integer[][] adjacencyMatrix = new Integer[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Enter the adjacency matrix row for vertex " + vertices.get(i) + ": ");
            String[] rowValues = scanner.nextLine().split(",");
            for (int j = 0; j < numVertices; j++) {
                adjacencyMatrix[i][j] = Integer.parseInt(rowValues[j]);
            }
        }

        Map<String, Integer> fuelPrices = new HashMap<>();
        for (String vertex : vertices) {
            System.out.print("Enter fuel price for vertex " + vertex + ": ");
            int fuelPrice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            fuelPrices.put(vertex, fuelPrice);
        }

        controller.createAdjacencyMatrixGraph(adjacencyMatrix, vertices, fuelPrices);
        System.out.println("Adjacency matrix graph created successfully.");
    }

    private static void findShortestPath() {
        System.out.print("Enter the start vertex: ");
        String startVertex = scanner.nextLine();

        Map<String, Integer> shortestPaths = controller.findShortestPath(startVertex);

        System.out.println("Shortest Paths:");
        for (Map.Entry<String, Integer> entry : shortestPaths.entrySet()) {
            System.out.println("Vertex: " + entry.getKey() + ", Distance: " + entry.getValue());
        }
    }

    private static void findShortestPathRestriction() {
        System.out.print("Enter the start vertex: ");
        String startVertex = scanner.nextLine();

        System.out.print("Enter the fuel capacity: ");
        int fuelCapacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Map<String, Integer> shortestPaths = controller.findShortestPathRestriction(startVertex, fuelCapacity);

        System.out.println("Shortest Paths with Fuel Restriction:");
        for (Map.Entry<String, Integer> entry : shortestPaths.entrySet()) {
            System.out.println("Vertex: " + entry.getKey() + ", Distance: " + entry.getValue());
        }
    }

    private static void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = controller.getAdjacencyMatrix();

        System.out.println("Adjacency Matrix:");
        for (int[] row : adjacencyMatrix) {
            for (int value : row) {
                System.out.printf("%-5s", value);
            }
            System.out.println();
        }
    }

    private static void printVertices() {
        List<String> vertices = controller.getVertices();

        System.out.println("Vertices:");
        for (String vertex : vertices) {
            System.out.println(vertex);
        }
    }
}
