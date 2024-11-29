import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddEditBillDialog extends JDialog {
    private JTextField customerField, amountField, monthField;
    private JButton saveButton;
    private int billId = -1; // Default for adding new bills

    public AddEditBillDialog(JFrame parent, String title) {
        this(parent, title, -1);
    }

    public AddEditBillDialog(JFrame parent, String title, int billId) {
        super(parent, title, true);
        this.billId = billId;

        setSize(300, 200);
        setLayout(new GridLayout(4, 2));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Components
        add(new JLabel("Customer Name:"));
        customerField = new JTextField();
        add(customerField);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Month:"));
        monthField = new JTextField();
        add(monthField);

        saveButton = new JButton("Save");
        add(new JLabel());
        add(saveButton);

        saveButton.addActionListener(e -> saveBill());

        if (billId != -1) {
            loadBillDetails();
        }

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void loadBillDetails() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:water_supply.db")) {
            String query = "SELECT * FROM bills WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customerField.setText(rs.getString("customer_name"));
                amountField.setText(String.valueOf(rs.getDouble("amount")));
                monthField.setText(rs.getString("month"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveBill() {
        String customer = customerField.getText();
        double amount;
        String month = monthField.getText();

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:water_supply.db")) {
            String query;
            if (billId == -1) {
                query = "INSERT INTO bills (customer_name, amount, month) VALUES (?, ?, ?)";
            } else {
                query = "UPDATE bills SET customer_name = ?, amount = ?, month = ? WHERE id = ?";
            }

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customer);
            stmt.setDouble(2, amount);
            stmt.setString(3, month);
            if (billId != -1) {
                stmt.setInt(4, billId);
            }
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Bill saved successfully.");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
