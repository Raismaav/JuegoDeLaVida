import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ForkJoinPool;

public class Interfaz extends JFrame implements ActionListener {
    private int dimensiones;
    private Controlador controlador;
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
        this.dimensiones = dimensiones;
        this.controlador = new Controlador();
        this.celda = new JLabel[dimensiones][dimensiones];
        crearTablero(1, (400 / dimensiones) - 1);

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void crearControles() {

        panelMatriz = new JPanel();
        panelMatriz.setBounds(360, 15, 400, 400);
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

    private void actualizarTablero() {
        for (int i = 0; i < celda.length; i++) {
            for (int j = 0; j < celda[i].length; j++) {
                if (controlador.getCelula(i, j)) {
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
            new NuevoJuego();
            dispose();
        }

        if (evento.getSource() == inicializar) {
            controlador.inicializarJuego(dimensiones);
            actualizarTablero();
        }

        if (evento.getSource() == avanzarSecuencial) {
            controlador.avanzarSecuencial();
            actualizarTablero();
            tiempoSecuencial.setText("Tiempo: " + controlador.getTiempo() + " ms");
        }

        if (evento.getSource() == avanzarJorkJoin) {
            controlador.avanzarJorkJoin();
            actualizarTablero();
            tiempoJorkJoin.setText("Tiempo: " + controlador.getTiempo() + " ms");
        }

        if (evento.getSource() == avanzarExecutor) {
            controlador.avanzarExecutor();
            actualizarTablero();
            tiempoExecutor.setText("Tiempo: " + controlador.getTiempo() + " ms");
        }
    }
}
