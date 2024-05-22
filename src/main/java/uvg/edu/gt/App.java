package uvg.edu.gt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    private static Graph graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Leo leo = new Leo();

        while (true) {
            System.out.println("Menú Principal:");
            System.out.println("1. Cargar grafo desde archivo");
            System.out.println("2. Agregar arco");
            System.out.println("3. Eliminar arco");
            System.out.println("4. Calcular centro del grafo");
            System.out.println("5. Ejecutar algoritmo de Floyd-Warshall");
            System.out.println("6. Ver matriz de adyacencia");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    String nombreArchivo = "..\\Hdt10Estructura\\guateGrafo.txt";
                    try {
                        ArrayList<ArrayList<String>> datos = leo.leerDatosDesdeArchivo(nombreArchivo);
                        int vertices = leo.cantidadVertices(nombreArchivo);
                        graph = new Graph(vertices);

                        HashMap<String, Integer> vertexIndices = new HashMap<>();
                        int currentIndex = 1;

                        for (ArrayList<String> dato : datos) {
                            String inicio = dato.get(0);
                            String fin = dato.get(1);
                            int peso = Integer.parseInt(dato.get(2));

                            if (!vertexIndices.containsKey(inicio)) {
                                graph.nombrarVertice(inicio, currentIndex);
                                vertexIndices.put(inicio, currentIndex++);
                            }

                            if (!vertexIndices.containsKey(fin)) {
                                graph.nombrarVertice(fin, currentIndex);
                                vertexIndices.put(fin, currentIndex++);
                            }

                            int inicioVert = vertexIndices.get(inicio);
                            int finVert = vertexIndices.get(fin);

                            graph.agregarArco(inicioVert, finVert, peso);
                        }
                        System.out.println("Grafo cargado exitosamente.");
                        graph.imprimirMatrizDeAdyacencia();
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo: " + e.getMessage());
                    }
                    break;
                case 2:
                    if (graph == null) {
                        System.out.println("Primero debe cargar un grafo.");
                        break;
                    }
                    System.out.print("Ingrese el vértice de inicio: ");
                    String inicio = scanner.nextLine();
                    System.out.print("Ingrese el vértice de fin: ");
                    String fin = scanner.nextLine();
                    System.out.print("Ingrese el peso del arco: ");
                    int peso = scanner.nextInt();
                    scanner.nextLine();  // Consumir nueva línea

                    graph.agregarArco(graph.getNombresVertices().get(inicio), graph.getNombresVertices().get(fin), peso);
                    System.out.println("Arco agregado exitosamente.");
                    graph.imprimirMatrizDeAdyacencia();
                    break;
                case 3:
                    if (graph == null) {
                        System.out.println("Primero debe cargar un grafo.");
                        break;
                    }
                    System.out.print("Ingrese el vértice de inicio: ");
                    inicio = scanner.nextLine();
                    System.out.print("Ingrese el vértice de fin: ");
                    fin = scanner.nextLine();

                    graph.borrarArco(graph.getNombresVertices().get(inicio), graph.getNombresVertices().get(fin));
                    System.out.println("Arco eliminado exitosamente.");
                    graph.imprimirMatrizDeAdyacencia();
                    break;
                case 4:
                    if (graph == null) {
                        System.out.println("Primero debe cargar un grafo.");
                        break;
                    }
                    Integer centro = graph.encontrarCentroGrafo();
                    System.out.println("El centro del grafo es el vértice: " + centro + "\nEl nombre del vertice: " + graph.getNombresVertices().keySet().toArray()[centro-1].toString());
                    break;
                case 5:
                    if (graph == null) {
                        System.out.println("Primero debe cargar un grafo.");
                        break;
                    }
                    graph.floydWarshall();
                    break;
                case 6:
                    if (graph == null) {
                        System.out.println("Primero debe cargar un grafo.");
                        break;
                    }
                    graph.imprimirMatrizDeAdyacencia();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
