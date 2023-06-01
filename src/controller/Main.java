package controller;

import model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Controller<String> controller = new Controller<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Add a vertex");
            System.out.println("2. Set fuel price for a vertex");
            System.out.println("3. Add an edge");
            System.out.println("4. Compute shortest path in Graph");
            System.out.println("5. Compute shortest path in GraphMatrix");
            System.out.println("6. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter the vertex:");
                    String vertex = scanner.next();
                    controller.addVertex(vertex);
                    break;

                case 2:
                    System.out.println("Enter the vertex:");
                    vertex = scanner.next();
                    System.out.println("Enter the fuel price:");
                    int fuelPrice = scanner.nextInt();
                    controller.setFuelPrice(vertex, fuelPrice);
                    break;

                case 3:
                    System.out.println("Enter the source vertex:");
                    String source = scanner.next();
                    System.out.println("Enter the destination vertex:");
                    String destination = scanner.next();
                    System.out.println("Enter the weight:");
                    int weight = scanner.nextInt();
                    controller.addEdge(source, destination, weight);
                    break;

                case 4:
                    System.out.println("Enter the start vertex:");
                    String start = scanner.next();
                    System.out.println("Enter the end vertex:");
                    String end = scanner.next();
                    System.out.println("Enter the fuel capacity:");
                    int fuelCapacity = scanner.nextInt();
                    Pair<Integer, List<String>> shortestPath = controller.computeShortestPathInGraph(start, end, fuelCapacity);
                    System.out.println("The shortest path is " + shortestPath.getSecond() + " with a total distance of " + shortestPath.getFirst());
                    break;

                case 5:
                    System.out.println("Enter the start vertex:");
                    start = scanner.next();
                    Map<String, Integer> shortestPathMatrix = controller.computeShortestPathInGraphMatrix(start);
                    System.out.println("The shortest path from " + start + " to each vertex is " + shortestPathMatrix);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
