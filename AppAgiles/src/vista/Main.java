package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.CalculatorLogic;

// Importamos la lógica de otro paquete para cumplir con la modularidad

public class Main extends JFrame implements ActionListener {
    public JPanel p;
    public double current = 0, memory;
    public int dotDigits = 0;
    public String a = "n"; // s (suma), e (exponente), n (nada)

    public JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    public JButton equalsButton, sumButton, c, dotButton, circButton, bFact, bExp;
    public JLabel text;

    public static void main(String[] args) {
        try {
            Main frame = new Main();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Main() {
        setTitle("Calculator - Refactorizada UT3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 600);

        p = new JPanel();
        p.setLayout(null);
        setContentPane(p);

        text = new JLabel("0");
        text.setHorizontalAlignment(4);
        text.setBounds(50, 20, 300, 50);
        p.add(text);

        // Inicialización de botones (Mantenemos tu estructura original de GUI)
        inicializarBotones();
    }

    private void inicializarBotones() {
        b7 = crearBoton("7", 50, 100);
        b8 = crearBoton("8", 120, 100);
        b9 = crearBoton("9", 190, 100);
        b4 = crearBoton("4", 50, 170);
        b5 = crearBoton("5", 120, 170);
        b6 = crearBoton("6", 190, 170);
        b1 = crearBoton("1", 50, 240);
        b2 = crearBoton("2", 120, 240);
        b3 = crearBoton("3", 190, 240);
        b0 = crearBoton("0", 50, 310);

        sumButton = crearBoton("+", 120, 310);
        equalsButton = crearBoton("=", 190, 310);
        c = crearBoton("C", 260, 310);
        dotButton = crearBoton(".", 50, 380);
        circButton = crearBoton("Circum", 120, 380);
        bFact = crearBoton("!", 190, 380);
        bExp = crearBoton("Exp", 260, 380);
    }

    private JButton crearBoton(String t, int x, int y) {
        JButton b = new JButton(t);
        b.setBounds(x, y, 60, 60);
        if(t.equals("Circum") || t.equals("Exp")) b.setSize(70, 60);
        p.add(b);
        b.addActionListener(this);
        return b;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        Object source = ev.getSource();

        // Lógica de entrada de números
        if (source instanceof JButton) {
            String label = ((JButton) source).getText();
            if ("0123456789".contains(label)) {
                procesarNumero(Integer.parseInt(label));
                return;
            }
        }

        if (source == sumButton) {
            operarAnterior();
            a = "s";
        } else if (source == bExp) {
            operarAnterior();
            a = "e";
        } else if (source == equalsButton) {
            finalizarOperacion();
        } else if (source == c) {
            resetear();
        } else if (source == dotButton) {
            activarDecimal();
        } else if (source == circButton) {
            // Uso de la clase Logica (UT3)
            current = CalculatorLogic.calcularCircunferencia(current);
            actualizarPantalla(true);
        } else if (source == bFact) {
            // Uso de la clase Logica (UT3)
            current = CalculatorLogic.calcularFactorial(current);
            actualizarPantalla(false);
        }
    }

    private void procesarNumero(int n) {
        if (dotDigits == 0) {
            current = current * 10 + n;
        } else if (dotDigits < 10) {
            current = current + n * Math.pow(10, -dotDigits);
            dotDigits++;
        }
        actualizarPantalla(true);
    }

    private void operarAnterior() {
        if (a.equals("s")) memory = current + memory;
        else if (a.equals("e")) memory = CalculatorLogic.calcularExponente(current, memory);
        else memory = current;

        current = 0;
        dotDigits = 0;
        text.setText("0");
    }

    private void finalizarOperacion() {
        if (a.equals("s")) current = current + memory;
        else if (a.equals("e")) current = CalculatorLogic.calcularExponente(current, memory);
        actualizarPantalla(true);
        a = "n";
    }

    private void actualizarPantalla(boolean decimal) {
        if (decimal) {
            text.setText(String.valueOf(current));
            dotDigits = String.valueOf(current).split("\\.")[1].length() + 1;
        } else {
            text.setText(String.format("%.0f", current));
            dotDigits = 0;
        }
    }

    private void resetear() {
        current = 0;
        memory = 0;
        dotDigits = 0;
        a = "n";
        text.setText("0");
    }

    private void activarDecimal() {
        if (dotDigits < 10) dotDigits++;
        actualizarPantalla(true);
    }
}