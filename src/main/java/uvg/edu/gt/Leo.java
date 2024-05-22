package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Leo {
    public ArrayList<ArrayList<String>> leerDatosDesdeArchivo(String nombreArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        String linea;
        ArrayList data = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(" ");
            String inicio = partes[0];
            String fin = partes[1];
            String peso = partes[2];
            ArrayList datos = new ArrayList<>();
            datos.add(inicio);
            datos.add(fin);
            datos.add(peso);
            data.add(datos);
            if (nombres.contains(inicio) == false){
                nombres.add(inicio);
            }
            if (nombres.contains(fin) == false){
                nombres.add(fin);
            }
        }
        br.close();
        return data;
    }
    public int cantidadVertices(String nombreArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        String linea;
        ArrayList data = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(" ");
            String inicio = partes[0];
            String fin = partes[1];
            if (nombres.contains(inicio) == false){
                nombres.add(inicio);
            }
            if (nombres.contains(fin) == false){
                nombres.add(fin);
            }
        }
        br.close();
        return nombres.size();
    }
}
