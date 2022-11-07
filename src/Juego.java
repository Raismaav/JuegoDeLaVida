public class Juego {
    private static boolean celulas[][];
    private static boolean buffer[][];

    private static Matriz matriz;

    public Juego(Matriz matriz) {
        this.matriz = matriz;
    }

    public void actualizarJuego() {
        buffer = matriz.getCelulas();
        int vivos = 0;
        for(int i = 0; i < buffer.length; i++) {
            for(int j = 0; j < buffer[i].length; j++) {
                vivos = vecinosVivos(i, j);
                if (vivos == 3)
                    buffer[i][j] = true;
                else if (vivos > 1 && vivos < 4 ) {
                    buffer[i][j] = celulas[i][j];
                }
                else
                    buffer[i][j] = false;
            }
        }
        matriz.setCelulas(buffer);
        celulas = matriz.getCelulas();
        matriz.setCelulas(celulas);
    }

    private int vecinosVivos(int posicionX, int posicionY) {
        celulas = matriz.getCelulas();
        int vivos = 0;
        int arriba = posicionY - 1;
        int abajo = posicionY + 1;
        int izquierda = posicionX - 1;
        int derecha = posicionX + 1;

        if (arriba < 0)
            arriba = this.celulas.length - 1;
        if (abajo == this.celulas.length)
            abajo = 0;
        if (izquierda < 0)
            izquierda = this.celulas.length - 1;
        if (derecha == this.celulas.length)
            derecha = 0;

        if (celulas[izquierda][arriba])
            vivos++;
        if (celulas[posicionX][arriba])
            vivos++;
        if (celulas[derecha][arriba])
            vivos++;
        if (celulas[izquierda][posicionY])
            vivos++;
        if (celulas[derecha][posicionY])
            vivos++;
        if (celulas[izquierda][abajo])
            vivos++;
        if (celulas[posicionX][abajo])
            vivos++;
        if (celulas[derecha][abajo])
            vivos++;

        return vivos;
    }
}