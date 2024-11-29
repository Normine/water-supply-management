import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CustomerDetailsDialog extends JDialog {
    private JLabel customerLabel, amountLabel, monthLabel;

    public CustomerDetailsDialog(JFrame parent, int billId) {
        super(parent, "Customer Details", true);
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        customerLabel = new JLabel("Customer: ");
        amountLabel = new JLabel("Amount: ");
        monthLabel = new JLabel("Month: ");
        add(customerLabel);
        add(amountLabel);
        add(monthLabel);

        loadCustomerDetails(billId);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton);

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void loadCustomerDetails(int billId) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:water_supply.db")) {
            String query = "SELECT customer_name, amount, month FROM bills WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customerLabel.setText("Customer: " + rs.getString("customer_name"));
                amountLabel.setText("Amount: $" + rs.getDouble("amount"));
                monthLabel.setText("Month: " + rs.getString("month"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
