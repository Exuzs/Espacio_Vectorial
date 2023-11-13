import java.util.Scanner;

public class Matriz {
    private int filas;
    private int columnas;
    private double[][] datos;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new double[filas][columnas];
    }

    public void setElemento(int fila, int columna, double valor) {
        datos[fila][columna] = valor;
    }

    public void ingresarMatrizDesdeUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los elementos de la matriz:");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Elemento en la posición (" + (i + 1) + "," + (j + 1) + "): ");
                double elemento = scanner.nextDouble();
                setElemento(i, j, elemento);
            }
        }
    }

    public void imprimirMatriz() {
        System.out.println("Matriz ingresada:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(datos[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public boolean sonFilasLinealmenteIndependientes() {
        // Copiar la matriz para no modificar la original
        double[][] copiaDatos = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            System.arraycopy(datos[i], 0, copiaDatos[i], 0, columnas);
        }

        // Aplicar eliminación de Gauss
        for (int i = 0; i < filas; i++) {
            // Hacer ceros debajo del elemento actual en la columna actual
            for (int j = i + 1; j < filas; j++) {
                double factor = copiaDatos[j][i] / copiaDatos[i][i];
                for (int k = 0; k < columnas; k++) {
                    copiaDatos[j][k] -= factor * copiaDatos[i][k];
                }
            }
        }

        // Verificar si hay alguna fila de ceros (fila dependiente)
        for (int i = 0; i < filas; i++) {
            boolean filaCero = true;
            for (int j = 0; j < columnas; j++) {
                if (copiaDatos[i][j] != 0) {
                    filaCero = false;
                    break;
                }
            }
            if (filaCero) {
                return false; // Filas linealmente dependientes
            }
        }

        return true; // Filas linealmente independientes
    }

    public void encontrarEcuacionDelPlano() {
        if (filas != 3 || columnas != 4) {
            System.out.println("La matriz debe ser de 3x4 para representar un sistema de ecuaciones lineales en el espacio tridimensional.");
            return;
        }

        // Copiar la matriz para no modificar la original
        double[][] copiaDatos = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            System.arraycopy(datos[i], 0, copiaDatos[i], 0, columnas);
        }

        // Aplicar eliminación de Gauss
        for (int i = 0; i < filas; i++) {
            // Normalizar la fila actual
            double factor = copiaDatos[i][i];
            for (int j = 0; j < columnas; j++) {
                copiaDatos[i][j] /= factor;
            }

            // Hacer ceros debajo del elemento actual en la columna actual
            for (int j = i + 1; j < filas; j++) {
                factor = copiaDatos[j][i];
                for (int k = 0; k < columnas; k++) {
                    copiaDatos[j][k] -= factor * copiaDatos[i][k];
                }
            }
        }

        // Obtener los coeficientes de la ecuación del plano
        double A = copiaDatos[0][0];
        double B = copiaDatos[1][1];
        double C = copiaDatos[2][2];
        double D = copiaDatos[0][3];

        System.out.println("Ecuación del plano: " + A + "x + " + B + "y + " + C + "z = " + D);
    }

    public void imprimirMatrizEnPasos() {
        System.out.println("Matriz original:");
        imprimirMatriz();

        // Copiar la matriz para no modificar la original
        double[][] copiaDatos = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            System.arraycopy(datos[i], 0, copiaDatos[i], 0, columnas);
        }

        // Aplicar eliminación de Gauss
        for (int i = 0; i < filas; i++) {
            // Normalizar la fila actual
            double factor = copiaDatos[i][i];
            for (int j = 0; j < columnas; j++) {
                copiaDatos[i][j] /= factor;
            }

            System.out.println("Paso " + (i + 1) + ":");
            imprimirMatriz(copiaDatos);

            // Hacer ceros debajo del elemento actual en la columna actual
            for (int j = i + 1; j < filas; j++) {
                factor = copiaDatos[j][i];
                for (int k = 0; k < columnas; k++) {
                    copiaDatos[j][k] -= factor * copiaDatos[i][k];
                }
            }
        }

        System.out.println("Matriz después de la eliminación de Gauss:");
        imprimirMatriz(copiaDatos);
    }

    private void imprimirMatriz(double[][] matriz) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void mostrarSolucion() {
        if (!sonFilasLinealmenteIndependientes()) {
            System.out.println("El sistema de ecuaciones es linealmente dependiente.");
            // Aquí puedes mostrar la solución explícita o en términos de parámetros.
            mostrarSolucionLinealmenteDependiente();
        } else {
            if (columnas - 1 == filas) {
                System.out.println("El sistema de ecuaciones tiene una única solución:");

                // Obtener los valores de las incógnitas
                double[] solucion = new double[filas];
                for (int i = filas - 1; i >= 0; i--) {
                    double sum = datos[i][columnas - 1];
                    for (int j = i + 1; j < filas; j++) {
                        sum -= datos[i][j] * solucion[j];
                    }
                    solucion[i] = sum;
                }

                // Mostrar la solución
                for (int i = 0; i < filas; i++) {
                    System.out.println("x" + (i + 1) + " = " + solucion[i]);
                }
            } else {
                System.out.println("El sistema de ecuaciones tiene infinitas soluciones.");
                // Aquí puedes mostrar la solución en términos de parámetros.
                mostrarSolucionInfinita();
            }
        }
    }

    private void mostrarSolucionLinealmenteDependiente() {
        // Aquí puedes implementar la lógica para mostrar la solución en términos de parámetros.
        System.out.println("La solución está en términos de parámetros.");
    }

    private void mostrarSolucionInfinita() {
        // Aquí puedes implementar la lógica para mostrar la solución en términos de parámetros.
        System.out.println("La solución es infinita y está en términos de parámetros.");
    }



}

