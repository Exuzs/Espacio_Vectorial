import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario el tamaño de la matriz
        System.out.print("Ingrese el número de filas de la matriz: ");
        int filas = scanner.nextInt();
        System.out.print("Ingrese el número de columnas de la matriz: ");
        int columnas = scanner.nextInt();

        // Crear la matriz
        Matriz matriz = new Matriz(filas, columnas);

        // Ingresar los elementos de la matriz
        matriz.ingresarMatrizDesdeUsuario();

        // Imprimir la matriz ingresada
        System.out.println("Matriz ingresada:");
        matriz.imprimirMatriz();

        // Verificar si las filas son linealmente independientes
        if (matriz.sonFilasLinealmenteIndependientes()) {
            System.out.println("Las filas son linealmente independientes.");

            // Encontrar y mostrar la ecuación del plano
            matriz.encontrarEcuacionDelPlano();

            // Mostrar los pasos de la eliminación de Gauss
            System.out.println("Pasos de la eliminación de Gauss:");
            matriz.imprimirMatrizEnPasos();

            // Mostrar la solución del sistema de ecuaciones
            System.out.println("Solución del sistema de ecuaciones:");
            matriz.mostrarSolucion();
        } else {
            System.out.println("Las filas son linealmente dependientes.");
            // Puedes agregar más lógica aquí según sea necesario para manejar casos específicos.
        }
    }
}