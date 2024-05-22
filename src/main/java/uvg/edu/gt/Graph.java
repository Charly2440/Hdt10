package uvg.edu.gt;

import java.util.HashMap;
import java.io.*;
import java.lang.*;
import java.util.*;
public class Graph {
    private Integer[][] matrizDeAdyacencia;
    private Integer[][] matrizDeCostos;
    private HashMap<String, Integer> nombresVertices;
    private int cantidadVertices;
    private static final int inf = 99999;
    public Graph(int cantidadVertices){
        this.cantidadVertices = cantidadVertices;
        matrizDeAdyacencia = new Integer[cantidadVertices][cantidadVertices];
        matrizDeCostos = new Integer[cantidadVertices][cantidadVertices];
        nombresVertices = new HashMap<String, Integer>();
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                if (i == j) {
                    matrizDeAdyacencia[i][j] = 0;
                    matrizDeCostos[i][j] = 0;
                }
                else{
                    matrizDeAdyacencia[i][j] = inf;
                    matrizDeCostos[i][j] = inf;
                }
            }
        }
    }

    public void imprimirMatrizDeAdyacencia() {
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                if (matrizDeAdyacencia[i][j] == inf) {
                    System.out.print("INF");
                } else {
                    System.out.print(matrizDeAdyacencia[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void agregarArco(int inicio, int fin, Integer peso){
        this.matrizDeAdyacencia[inicio-1][fin-1] = peso;
    }

    public void borrarArco(int inicio, int fin){
        this.matrizDeAdyacencia[inicio-1][fin-1] = inf;
    }

    public void nombrarVertice( String nombre, Integer vertice){
        if (this.nombresVertices.keySet().contains(nombre) == false) {
            this.nombresVertices.put(nombre, vertice);
        }
    }

    //Algoritmo obtenido inspirado en el encontrado en la siguiente pagina: https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
    public void floydWarshall()
    {

        int i, j, k;

        /* Add all vertices one by one
           to the set of intermediate
           vertices.
          ---> Before start of an iteration,
               we have shortest
               distances between all pairs
               of vertices such that
               the shortest distances consider
               only the vertices in
               set {0, 1, 2, .. k-1} as
               intermediate vertices.
          ----> After the end of an iteration,
                vertex no. k is added
                to the set of intermediate
                vertices and the set
                becomes {0, 1, 2, .. k} */
        this.matrizDeCostos = this.matrizDeAdyacencia.clone();
        for (k = 0; k < this.cantidadVertices; k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < this.cantidadVertices; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < this.cantidadVertices; j++) {
                    // If vertex k is on the shortest path
                    // from i to j, then update the value of
                    // dist[i][j]
                    if (this.matrizDeCostos[i][k] + this.matrizDeCostos[k][j] < this.matrizDeCostos[i][j])
                        this.matrizDeCostos[i][j] = this.matrizDeCostos[i][k] + this.matrizDeCostos[k][j];
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(this.matrizDeCostos);
    }

    public void printSolution(Integer dist[][])
    {
        System.out.println(
                "La siguiente matriz es una representación gráfica "
                        + "que denota las distancias más cortas entre cada par de vertices usando el algoritmo de Floyd");
        for (int i = 0; i < this.cantidadVertices; ++i) {
            for (int j = 0; j < this.cantidadVertices; ++j) {
                if (dist[i][j] == inf)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public Integer encontrarCentroGrafo() {
        floydWarshall();

        Integer[] excentricidades = new Integer[cantidadVertices];

        for (int i = 0; i < cantidadVertices; i++) {
            int maxDistancia = 0;
            for (int j = 0; j < cantidadVertices; j++) {
                if (matrizDeCostos[i][j] != inf && matrizDeCostos[i][j] > maxDistancia) {
                    maxDistancia = matrizDeCostos[i][j];
                }
            }
            excentricidades[i] = maxDistancia;
        }

        // Find the vertex with the smallest eccentricity
        int centro = 0;
        for (int i = 1; i < cantidadVertices; i++) {
            if (excentricidades[i] < excentricidades[centro]) {
                centro = i;
            }
        }

        // Print the center of the graph
        System.out.println("Centro del grafo: " + centro);
        return centro;
    }

    public int calcularCentroGrafo() {
        int[][] distancias = new int[cantidadVertices][cantidadVertices];
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                distancias[i][j] = matrizDeAdyacencia[i][j];
            }
        }

        for (int k = 0; k < cantidadVertices; k++) {
            for (int i = 0; i < cantidadVertices; i++) {
                for (int j = 0; j < cantidadVertices; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }

        int[] excentricidades = new int[cantidadVertices];
        for (int i = 0; i < cantidadVertices; i++) {
            excentricidades[i] = Arrays.stream(distancias[i]).max().orElse(inf);
        }

        int centro = 0;
        for (int i = 1; i < cantidadVertices; i++) {
            if (excentricidades[i] < excentricidades[centro]) {
                centro = i;
            }
        }

        return centro;
    }


    public HashMap<String, Integer> getNombresVertices() {
        return nombresVertices;
    }


}
