import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class JuegoFJ extends RecursiveTask<boolean[][]> {

    private static boolean celulas[][];
    private static boolean aux[][];
    private static Matriz matriz;
    private static boolean buffer[][];
    private boolean isFork = false;
    private int posicion = 0;

    public JuegoFJ(Matriz matriz) {
        this.matriz = matriz;
        celulas = matriz.getCelulas();
    }

    public JuegoFJ(Matriz matriz, int posicion, boolean isFork) {
        this.matriz = matriz;
        this.posicion = posicion;
        this.isFork = isFork;
        celulas = matriz.getCelulas();
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
    protected boolean[][] compute() {
        if (isFork) {
            buffer = matriz.getCelulas();
            int vivos = 0;
            for(int j = 0; j < buffer[posicion].length; j++) {
                vivos = vecinosVivos(posicion, j);
                if (vivos == 3)
                    buffer[posicion][j] = true;
                else if (vivos > 1 && vivos < 4 ) {
                    buffer[posicion][j] = celulas[posicion][j];
                }
                else
                    buffer[posicion][j] = false;
            }

            return buffer;
        } else {
            JuegoFJ juego[] = new JuegoFJ[celulas.length];
            for(int i = 0; i < celulas.length; i++) {
                juego[i] = new JuegoFJ(matriz, i, true);
                juego[i].fork();
            }
            for (int i = 0; i < celulas.length; i++) {
                matriz.setCelulas(juego[i].join());
            }
        }
        return null;
    }
}