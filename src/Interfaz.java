import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ForkJoinPool;

public class Interfaz extends JFrame implements ActionListener {
    private Matriz matriz;
    private Juego juego;
    private JuegoES juegoExecutor;
    private JPanel panelMatriz;
    private JLabel celda[][];
    private JLabel tiempoSecuencial, tiempoJorkJoin, tiempoExecutor;
    private JButton inicializar, regresar, avanzarSecuencial, avanzarJorkJoin, avanzarExecutor;
    public Interfaz(int dimensiones) {
        setTitle("Juego de la vida");
        setSize(775, 455);
        getContentPane().setBackground(new java.awt.Color(59, 63, 65));
        setLocationRelativeTo(null);
        crearControles();
        this.matriz = new Matriz(dimensiones);
        this.juego = new Juego(matriz);
        this.juegoExecutor = new JuegoES(matriz);
        this.celda = new JLabel[dimensiones][dimensiones];
        crearTablero(1, (400 / dimensiones) - 1);

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void crearControles() {

        panelMatriz = new JPanel();
        panelMatriz.setBounds(360, 15, 400, 400400);
        panelMatriz.setBackground(new java.awt.Color(59, 63, 65));
        panelMatriz.setOpaque(true);
        panelMatriz.setLayout(null);
        add(panelMatriz);

        regresar = new JButton("Regresar");
        regresar.setBounds(25, 25, 150, 50);
        add(regresar);
        regresar.addActionListener(this);

        inicializar = new JButton("Inicializar");
        inicializar.setBounds(200, 25, 150, 50);
        add(inicializar);
        inicializar.addActionListener(this);

        avanzarSecuencial = new JButton("Avanzar Secuencial");
        avanzarSecuencial.setBounds(25, 100, 325, 50);
        add(avanzarSecuencial);
        avanzarSecuencial.addActionListener(this);

        tiempoSecuencial = new JLabel("Tiempo: ");
        tiempoSecuencial.setBounds(35, 150, 325, 30);
        tiempoSecuencial.setForeground(new java.awt.Color(255, 255, 255));
        add(tiempoSecuencial);

        avanzarJorkJoin = new JButton("Avanzar ForkJoin");
        avanzarJorkJoin.setBounds(25, 200, 325, 50);
        add(avanzarJorkJoin);
        avanzarJorkJoin.addActionListener(this);

        tiempoJorkJoin = new JLabel("Tiempo: ");
        tiempoJorkJoin.setBounds(35, 250, 325, 30);
        tiempoJorkJoin.setForeground(new java.awt.Color(255, 255, 255));
        add(tiempoJorkJoin);

        avanzarExecutor = new JButton("Avanzar ExecutorService");
        avanzarExecutor.setBounds(25, 300, 325, 50);
        add(avanzarExecutor);
        avanzarExecutor.addActionListener(this);

        tiempoExecutor = new JLabel("Tiempo: ");
        tiempoExecutor.setBounds(35, 350, 325, 30);
        tiempoExecutor.setForeground(new java.awt.Color(255, 255, 255));
        add(tiempoExecutor);
    }

    private void crearTablero(int margenCelda, int tamañoCelda) {
        for (int i = 0; i < celda.length; i++) {
            for (int j = 0; j < celda[i].length; j++) {
                celda[i][j] = new JLabel();
                celda[i][j].setOpaque(true);
                celda[i][j].setBackground(new java.awt.Color(44, 44, 44));
                celda[i][j].setBounds((i * (tamañoCelda + margenCelda)) + margenCelda, (j * (tamañoCelda + margenCelda)) + margenCelda, tamañoCelda, tamañoCelda);
                panelMatriz.add(celda[i][j]);
            }
        }
    }

    private void actualizarTablero(boolean celulas[][]) {
        for (int i = 0; i < celda.length; i++) {
            for (int j = 0; j < celda[i].length; j++) {
                if (celulas[i][j]) {
                    celda[i][j].setBackground(new java.awt.Color(245, 245, 245));
                } else {
                    celda[i][j].setBackground(new java.awt.Color(44, 44, 44));
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == regresar) {
            NuevoJuego nuevo = new NuevoJuego();
            dispose();
        }

        if (evento.getSource() == inicializar) {
            matriz.llenarAleatorio();
            actualizarTablero(matriz.getCelulas());
        }

        if (evento.getSource() == avanzarSecuencial) {
            long inicio = System.currentTimeMillis();
            juego.actualizarJuego();
            actualizarTablero(matriz.getCelulas());
            long tiempoTotal = (System.currentTimeMillis() - inicio);
            tiempoSecuencial.setText("Tiempo: " + tiempoTotal + " ms");
        }

        if (evento.getSource() == avanzarJorkJoin) {
            ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
            long inicio = System.currentTimeMillis();
            pool.invoke(new JuegoFJ(matriz));
            actualizarTablero(matriz.getCelulas());
            long tiempoTotal = (System.currentTimeMillis() - inicio);
            tiempoJorkJoin.setText("Tiempo: " + tiempoTotal + " ms");
        }

        if (evento.getSource() == avanzarExecutor) {
            long inicio = System.currentTimeMillis();
            juegoExecutor.actualizarJuego();
            actualizarTablero(matriz.getCelulas());
            long tiempoTotal = (System.currentTimeMillis() - inicio);
            tiempoExecutor.setText("Tiempo: " + tiempoTotal + " ms");
        }
    }
}
