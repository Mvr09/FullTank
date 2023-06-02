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
        return scanner.nextInt();
    }

    private static void createAdjacencyListGraph() {
        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Map<String, List<String>> adjacencyList = new HashMap<>();
        Map<String, Integer> fuelPrices = new HashMap<>();

        for (int i = 0; i < numVertices; i++) {
            System.out.print("Enter vertex " + (i + 1) + ": ");
            String vertex = scanner.nextLine();
            adjacencyList.put(vertex, new ArrayList<>());
        }

        for (int i = 0; i < numVertices; i++) {
            System.out.print("Enter the number of neighbors for vertex " + (i + 1) + ": ");
            int numNeighbors = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter neighbors for vertex " + (i + 1) + ": ");
            for (int j = 0; j < numNeighbors; j++) {
                System.out.print("Neighbor " + (j + 1) + ": ");
                String neighbor = scanner.nextLine();
                adjacencyList.get(adjacencyList.keySet().toArray()[i]).add(neighbor);
            }
        }

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
            for (int j = 0; j < numVertices; j++) {
                System.out.print("Enter weight from " + vertices.get(i) + " to " + vertices.get(j) + ": ");
                adjacencyMatrix[i][j] = scanner.nextInt();
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
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the start vertex: ");
        String startVertex = scanner.nextLine();

        Map<String, Integer> shortestPath = controller.findShortestPath(startVertex);
        printShortestPath(shortestPath);
    }

    private static void findShortestPathWithRestriction() {
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the start vertex: ");
        String startVertex = scanner.nextLine();

        System.out.print("Enter the fuel capacity: ");
        int fuelCapacity = scanner.nextInt();

        Map<String, Integer> shortestPath = controller.findShortestPathRestriction(startVertex, fuelCapacity);
        printShortestPath(shortestPath);
    }

    private static void printShortestPath(Map<String, Integer> shortestPath) {
        System.out.println("Shortest path:");
        for (Map.Entry<String, Integer> entry : shortestPath.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = controller.getAdjacencyMatrix();
        System.out.println("Adjacency Matrix:");
        for (int[] row : adjacencyMatrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private static void printVertices() {
        List<String> vertices = controller.getVertices();
        System.out.println("Vertices: " + vertices);
    }
}
