import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuevoJuego extends JFrame implements ActionListener {
    private JTextField dimencionesTablero = new JTextField();
    private JButton crear;

    public NuevoJuego() {
        setTitle("Juego de la vida");
        setSize(375, 130);
        getContentPane().setBackground(new java.awt.Color(59, 63, 65));
        setLocationRelativeTo(null);

        crearControles();

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void crearControles() {

        dimencionesTablero = new JTextField();
        dimencionesTablero.setBounds(25, 35, 100, 30);
        add(dimencionesTablero);

        crear = new JButton("Crear");
        crear.setBounds(150, 25, 200, 50);
        add(crear);
        crear.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == crear) {
            try{
                int tam = Integer.parseInt(dimencionesTablero.getText());

                if (tam <= 100) {
                    new Interfaz(tam);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "El tamaño de la matriz, no puede superar las 100 celdas");
                }

            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Error, no es un valor numerico");
            }
        }
    }
}