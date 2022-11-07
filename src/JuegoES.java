import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JuegoES {
    private boolean celulas[][];
    private Matriz matriz;
    private Matriz matrizRes;

    public JuegoES(Matriz matriz) {
        this.matriz = matriz;
        celulas = matriz.getCelulas();
        this.matrizRes = new Matriz(celulas.length);
    }

    public void actualizarJuego() {
        ExecutorService executor= Executors.newFixedThreadPool(celulas.length);
        for (int i = 0; i < celulas.length; i++) {
            executor.execute(new Hilo(matriz, matrizRes, i));
        }
        executor.shutdown();
        matriz.setCelulas(matrizRes.getCelulas());
    }
}
