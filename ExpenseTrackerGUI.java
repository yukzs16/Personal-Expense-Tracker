/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Expense {
    String description;
    double amount;

    Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return description + " - ₹" + amount;
    }
}
public class ExpenseTrackerGUI extends JFrame  {
     private ArrayList<Expense> expenses = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> expenseList = new JList<>(listModel);
    private JTextField descriptionField = new JTextField(15);
    private JTextField amountField = new JTextField(10);
    private JLabel totalLabel = new JLabel("Total: ₹0.0");

    public ExpenseTrackerGUI() {
        setTitle("Expense Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);

        JButton addButton = new JButton("Add Expense");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Expense list
        add(new JScrollPane(expenseList), BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        JButton totalButton = new JButton("Calculate Total");
        bottomPanel.add(totalButton);
        bottomPanel.add(totalLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        totalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });
    }

    private void addExpense() {
        String description = descriptionField.getText();
        String amountText = amountField.getText();

        try {
            double amount = Double.parseDouble(amountText);
            Expense expense = new Expense(description, amount);
            expenses.add(expense);
            listModel.addElement(expense.toString());
            descriptionField.setText("");
            amountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for amount.");
        }
    }

    private void calculateTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.amount;
        }
        totalLabel.setText("Total: ₹" + total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ExpenseTrackerGUI().setVisible(true);
        });
    }
}
