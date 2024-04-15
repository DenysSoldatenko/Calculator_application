package org.example.calculator;

import static java.awt.Font.PLAIN;
import static java.lang.Double.parseDouble;
import static java.lang.String.valueOf;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI for a simple calculator application.
 *
 * <p>This class sets up the user interface for the calculator, including buttons for digits,
 * operations, and display. It also handles user input and performs the necessary calculations.
 */
public class CalculatorGui implements ActionListener {
  private final JTextField textField = new JTextField();
  private final JButton[] numbers = new JButton[10];
  private final JButton addButton, subButton, mulButton, divButton;
  private final JButton decButton, equButton, delButton, clrButton, negButton;

  private double num1 = 0;
  private char operator;

  /**
   * Constructs a {@code CalculatorGui} object and sets up the GUI.
   * Initializes the frame, buttons, and layout, and sets up event listeners for button actions.
   */
  public CalculatorGui() {
    JFrame frame = new JFrame("My calculator");
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setSize(450, 550);
    frame.setLayout(null);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    textField.setBounds(75, 25, 300, 50);
    Font font = new Font("Comic Sans MS", PLAIN, 30);
    textField.setFont(font);
    textField.setEditable(false);

    addButton = new JButton("+");
    subButton = new JButton("-");
    mulButton = new JButton("*");
    divButton = new JButton("/");
    decButton = new JButton(".");
    equButton = new JButton("=");
    delButton = new JButton("Delete");
    clrButton = new JButton("Clear");
    negButton = new JButton("(-)");

    JButton[] functions = new JButton[9];
    functions[0] = addButton;
    functions[1] = subButton;
    functions[2] = mulButton;
    functions[3] = divButton;
    functions[4] = decButton;
    functions[5] = equButton;
    functions[6] = delButton;
    functions[7] = clrButton;
    functions[8] = negButton;

    for (int i = 0; i < 9; i++) {
      functions[i].addActionListener(this);
      functions[i].setFont(font);
      functions[i].setFocusable(false);
    }

    for (int i = 0; i < 10; i++) {
      numbers[i] = new JButton(valueOf(i));
      numbers[i].addActionListener(this);
      numbers[i].setFont(font);
      numbers[i].setFocusable(false);
    }

    negButton.setBounds(30, 430, 127, 50);
    delButton.setBounds(156, 430, 127, 50);
    clrButton.setBounds(283, 430, 127, 50);

    JPanel panel = new JPanel();
    panel.setBounds(75, 100, 300, 300);
    panel.setLayout(new GridLayout(4, 4, 10, 10));

    panel.add(numbers[1]);
    panel.add(numbers[2]);
    panel.add(numbers[3]);
    panel.add(addButton);
    panel.add(numbers[4]);
    panel.add(numbers[5]);
    panel.add(numbers[6]);
    panel.add(subButton);
    panel.add(numbers[7]);
    panel.add(numbers[8]);
    panel.add(numbers[9]);
    panel.add(mulButton);
    panel.add(decButton);
    panel.add(numbers[0]);
    panel.add(equButton);
    panel.add(divButton);

    frame.add(panel);
    frame.add(negButton);
    frame.add(delButton);
    frame.add(clrButton);
    frame.add(textField);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < 10; i++) {
      if (e.getSource() == numbers[i]) {
        textField.setText(textField.getText().concat(valueOf(i)));
      }
    }

    if (e.getSource() == decButton) {
      textField.setText(textField.getText().concat("."));
    }

    if (e.getSource() == addButton) {
      num1 = parseDouble(textField.getText());
      operator = '+';
      textField.setText("");
    }

    if (e.getSource() == subButton) {
      num1 = parseDouble(textField.getText());
      operator = '-';
      textField.setText("");
    }

    if (e.getSource() == mulButton) {
      num1 = parseDouble(textField.getText());
      operator = '*';
      textField.setText("");
    }

    if (e.getSource() == divButton) {
      num1 = parseDouble(textField.getText());
      operator = '/';
      textField.setText("");
    }

    if (e.getSource() == equButton) {
      double num2 = parseDouble(textField.getText());
      double result;
      switch (operator) {
        case '+' -> result = num1 + num2;
        case '-' -> result = num1 - num2;
        case '*' -> result = num1 * num2;
        case '/' -> result = num1 / num2;
        default -> result = 0;
      }
      textField.setText(valueOf(result));
      num1 = result;
    }

    if (e.getSource() == clrButton) {
      textField.setText("");
    }

    if (e.getSource() == delButton) {
      String string = textField.getText();
      textField.setText("");
      for (int i = 0; i < string.length() - 1; i++) {
        textField.setText(textField.getText() + string.charAt(i));
      }
    }

    if (e.getSource() == negButton) {
      double temp = parseDouble(textField.getText()) * -1;
      textField.setText(valueOf(temp));
    }
  }
}
