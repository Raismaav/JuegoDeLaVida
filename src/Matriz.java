import java.util.Arrays;

public class Matriz {
    private boolean celulas[][];

    public Matriz(int tamaño) {
        this.celulas = new boolean[tamaño][tamaño];
    }

    public Matriz(int ancho, int alto) {
        this.celulas = new boolean[ancho][alto];
    }

    public void llenar() {
        for(int i = 0; i < this.celulas.length; i++) {
            for(int j = 0; j < this.celulas[i].length; j++) {
                this.celulas[i][j] = false;
            }
        }
        this.celulas[2][1] = true;
        this.celulas[2][2] = true;
        this.celulas[2][3] = true;
    }

    public void llenarAleatorio() {
        for(int i = 0; i < this.celulas.length; i++) {
            for(int j = 0; j < this.celulas[i].length; j++) {
                if ((int) (Math.random() * 2) == 1) {
                    if ((int) (Math.random() * 2) == 1)
                        celulas[i][j] = true;
                    else
                        celulas[i][j] = false;
                } else
                    celulas[i][j] = false;
            }
        }
    }

    public void imprimir() {
        for(int i = 0; i < this.celulas.length; i++){
            for(int j = 0; j < this.celulas[i].length; j++) {
                System.out.print(this.celulas[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean[][] getCelulas() {
        boolean matriz[][] = new boolean[celulas.length][celulas[0].length];

        for (int i = 0; i < celulas.length; i++) {
            matriz[i] = Arrays.copyOf(this.celulas[i], celulas[i].length);
        }
        return matriz;
    }

    public boolean getCelula(int posX, int posY) {
        return celulas[posX][posY];
    }

    public void setCelulas(boolean celulas[][]) {
        for (int i = 0; i < celulas.length; i++) {
            this.celulas[i] = Arrays.copyOf(celulas[i], celulas[i].length);
        }
    }
}