# Hoja de Trabajo #10

#### Autor: Carlos Villagrán, carné 22264

#### Descripción del proyecto:

El siguiente trabajo se realiza con el objetivo de estudiar la implementación de digrafos y el algoritmo de Floyd para la obtención del camino más corto por pares. La implementación se realiza a partir de matrices de adyecencia. Además se incluye el cálculo para el centro del grafo. La simulación se realiza en el contexto de "distancia entre ciudades", una utilidad que tuvieron este tipo de estructuras en su momento para el análisis de la crisis sanitaria del Coronavirus.

#### Ejemplo de uso:

El programa busca un archivo llamado "rutas.txt" en la misma ruta que los .py, el archivo está escrito de la siguiente manera (se recomienda el uso de este archivo para la prueba):

```textile
Mixco Antigua 30
Antigua Escuintla 25
Escuintla SantaLucia 15
Solola Antigua 180
Peten SanMarcos 240
```

El programa ofrece al usuario las siguientes opciones:

```textile
Menú Principal:
1. Cargar grafo desde archivo
2. Agregar arco
3. Eliminar arco
4. Calcular centro del grafo
5. Ejecutar algoritmo de Floyd-Warshall
6. Ver matriz de adyacencia
7. Salir
```

Se necesita iniciar un grafo para empezar a trabajar, por lo que la primera opción debe ser elegida antes de iniciar con lo demás. Ejemplos del funcionamiento de las demás opciones se encuentran enseguida:

#### Agregar arco:
Recibe el nombre de la ciudad en la que se encuentra y el nombre de la ciudad a la que se dirige, por último, recibe el peso de la relación:

```textile
Ingrese el vértice de inicio: Antigua
Ingrese el vértice de fin: SantaLucia
Ingrese el peso del arco: 345
Arco agregado exitosamente.

0 30 INFINFINFINFINF
INF0 25 345 INFINFINF
INFINF0 15 INFINFINF
INFINFINF0 INFINFINF
INF180 INFINF0 INFINF
INFINFINFINFINF0 240 
INFINFINFINFINFINF0 
```
Como se puede observar, realiza exitosamente la relación y devuelve la matriz de adyacencia actualizada con la entrada agregada.

#### Eliminar arco:
Recibe el nombre de la ciudad en la que se encuentra y el nombre de la ciudad a la que se dirige:

```textile
Ingrese el vértice de inicio: Antigua
Ingrese el vértice de fin: SantaLucia
Arco eliminado exitosamente.
0 30 INFINFINFINFINF
INF0 25 INFINFINFINF
INFINF0 15 INFINFINF
INFINFINF0 INFINFINF
INF180 INFINF0 INFINF
INFINFINFINFINF0 240 
INFINFINFINFINFINF0  
```
Como se puede observar, remueve exitosamente la relación y devuelve la matriz de adyacencia actualizada.

#### Calcular centro del grafo:
Devuelve lo siguiente:

```textile
El centro del grafo es el vértice: 3
El nombre del vertice: Mixco  
```

#### Algoritmo Floyd:
Realiza los calculos pertinentes y devuelve la matriz de adyacencia con las menores distancias por pares:

```textile
La siguiente matriz es una representación gráfica que denota las distancias más cortas entre cada par de vertices usando el algoritmo de Floyd
0   30   55   70   INF INF INF 
INF 0   25   40   INF INF INF 
INF INF 0   15   INF INF INF 
INF INF INF 0   INF INF INF 
INF 180   205   220   0   INF INF 
INF INF INF INF INF 0   240   
INF INF INF INF INF INF 0   
```


