import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DashboardFrame extends JFrame {
    private JTable billTable;

    public DashboardFrame() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo bảng hiển thị hóa đơn
        billTable = new JTable();
        loadBillData();

        JScrollPane scrollPane = new JScrollPane(billTable);
        JButton addButton = new JButton("Add Bill");
        JButton editButton = new JButton("Edit Bill");
        JButton deleteButton = new JButton("Delete Bill");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Thêm sự kiện
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBill();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBill();
            }
        });
    }

    private void loadBillData() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT Id AS 'ID', CustomerName AS 'Customer', Amount AS 'Amount ($)', Month AS 'Month' FROM Bills";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            billTable.setModel(new ResultSetTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBill() {
        try (Connection conn = DBConnection.getConnection()) {
            String customerName = JOptionPane.showInputDialog("Enter Customer Name:");
            String amountStr = JOptionPane.showInputDialog("Enter Amount:");
            String month = JOptionPane.showInputDialog("Enter Month:");

            double amount = Double.parseDouble(amountStr);

            String query = "INSERT INTO Bills (CustomerName, Amount, Month) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customerName);
            stmt.setDouble(2, amount);
            stmt.setString(3, month);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Bill added successfully!");
            loadBillData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteBill() {
        try (Connection conn = DBConnection.getConnection()) {
            int selectedRow = billTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a bill to delete.");
                return;
            }

            int id = (int) billTable.getValueAt(selectedRow, 0);

            String query = "DELETE FROM Bills WHERE Id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Bill deleted successfully!");
            loadBillData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
