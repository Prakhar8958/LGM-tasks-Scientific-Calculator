package calci;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SwingCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String input;

    public SwingCalculator() {
        // Initialize the text field for display
        display = new JTextField(20);
        display.setEditable(false);

        // Initialize the input string
        input = "";

        // Set up the layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // Add buttons to the panel
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sin", "cos", "tan", "sqrt",
            "(", ")"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Add components to the frame
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle button clicks here
        if (command.equals("=")) {
            // Evaluate the expression
            try {
                double result = ExpressionEvaluator.evaluate(input);
                display.setText(Double.toString(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
            input = "";
        } else {
            input += command;
            display.setText(input);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingCalculator calculator = new SwingCalculator();
            calculator.setTitle("Swing Calculator");
            calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            calculator.pack();
            calculator.setVisible(true);
        });
    }
}





