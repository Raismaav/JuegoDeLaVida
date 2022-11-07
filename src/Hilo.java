public class Hilo implements Runnable {

    private static boolean celulas[][];
    private static boolean buffer[][];
    private int columna = 0;
    private static Matriz matriz, matrizRes;

    public Hilo(Matriz matriz, Matriz matrizRes, int columna) {
        this.matriz = matriz;
        this.matrizRes = matrizRes;
        this.columna = columna;
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

    @Override
    public void run() {
        celulas = matriz.getCelulas();
        buffer = matrizRes.getCelulas();
        int vivos = 0;
        for(int j = 0; j < buffer[columna].length; j++) {
            vivos = vecinosVivos(columna, j);
            if (vivos == 3)
                buffer[columna][j] = true;
            else if (vivos > 1 && vivos < 4 ) {
                buffer[columna][j] = celulas[columna][j];
            }
            else
                buffer[columna][j] = false;
        }
        matriz.setCelulas(celulas);
        matrizRes.setCelulas(buffer);
    }
}
