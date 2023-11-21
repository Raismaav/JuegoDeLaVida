import java.util.concurrent.ForkJoinPool;

public class Controlador {
    private Matriz matriz;
    private Juego juego;
    private JuegoES juegoExecutor;
    private long inicio;
    private long tiempoTotal;

    public boolean getCelula(int posX, int posY) {
        return matriz.getCelula(posX, posY);
    }

    public void inicializarJuego(int dimensiones) {
        this.matriz = new Matriz(dimensiones);
        this.juego = new Juego(matriz);
        this.juegoExecutor = new JuegoES(matriz);
        matriz.llenarAleatorio();
    }

    public void avanzarSecuencial() {
        this.inicio = System.currentTimeMillis();
        juego.actualizarJuego();
        this.tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public void avanzarJorkJoin() {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        this.inicio = System.currentTimeMillis();
        pool.invoke(new JuegoFJ(matriz));
        this.tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public void avanzarExecutor() {
        this.inicio = System.currentTimeMillis();
        juegoExecutor.actualizarJuego();
        this.tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public long getTiempo() {
        return tiempoTotal;
    }
}
