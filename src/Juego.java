public class Juego {
    private static boolean[][] celulas;
    private static boolean[][] buffer;
    private static Matriz matriz;

    public Juego(Matriz matriz) {
        Juego.matriz = matriz;// Inicializar celulas aquí para evitar reasignaciones
    }

    public void actualizarJuego() {
        celulas = matriz.getCelulas();
        buffer = new boolean[celulas.length][celulas[0].length]; // Inicializar buffer con el tamaño correcto
        for (int i = 0; i < celulas.length; i++) {
            for (int j = 0; j < celulas[i].length; j++) {
                int vivos = vecinosVivos(i, j);
                // Simplificar condiciones
                buffer[i][j] = vivos == 3 || (vivos == 2 && celulas[i][j]);
            }
        }
        celulas = buffer; // Asignar buffer a celulas una sola vez
        matriz.setCelulas(celulas); // Actualizar la matriz con el nuevo estado
    }

    private int vecinosVivos(int posicionX, int posicionY) {
        int vivos = 0;
        int[] desplazamientos = {-1, 0, 1};

        for (int dx : desplazamientos) {
            for (int dy : desplazamientos) {
                if (dx == 0 && dy == 0) continue;
                int xVecino = (posicionX + dx + celulas.length) % celulas.length;
                int yVecino = (posicionY + dy + celulas[0].length) % celulas[0].length;
                if (celulas[xVecino][yVecino]) vivos++;
            }
        }
        return vivos;
    }
}