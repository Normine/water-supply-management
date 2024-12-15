
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;


public class customerhome extends javax.swing.JFrame {
private String id="";
 
    public customerhome(String id) {
        this.id = id;
        initComponents();
        customizeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Buying");
        setSize(1000, 768);
        setLocationRelativeTo(null);
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jCustomerNameLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultArea = new javax.swing.JTextArea();
        btnLogout = new javax.swing.JButton();
        btnBuying = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtResourceID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnOrder = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 768));

        txtSearch.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jCustomerNameLabel.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jCustomerNameLabel.setForeground(new java.awt.Color(0, 102, 204));
        jCustomerNameLabel.setText("Welcom back, ");
        jCustomerNameLabel.setToolTipText("");

        txtResultArea.setColumns(20);
        txtResultArea.setRows(5);
        jScrollPane2.setViewportView(txtResultArea);

        btnLogout.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLogout.setText("Log Out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnBuying.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnBuying.setText("Buying");
        btnBuying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyingActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Search the name of Supplier here:");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtResourceID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtResourceID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResourceIDActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Enter the amount of Water for this connection");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnOrder.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnOrder.setText("Order");
        btnOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOrderMouseClicked(evt);
            }
        });
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Enter the Resource ID you want to connect / order");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAmount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 25, Short.MAX_VALUE))
                            .addComponent(jCustomerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(118, 118, 118))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtResourceID, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(174, 174, 174))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(116, 116, 116)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(btnBuying, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCustomerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtResourceID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75)
                .addComponent(btnBuying)
                .addGap(131, 131, 131))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String supplierName = txtSearch.getText();
         btnSearch.setEnabled(false);
        txtResultArea.setText(""); 
        if (supplierName.isEmpty()) {
            JOptionPane.showMessageDialog(
                null, "Supplier name is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                btnSearch.setEnabled(true);
                return;
        }
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() {
             try {
                System.out.println("Executing DBConnection.showSearchQuery..."); // Debugging
                String result = DBConnection.showSearchQuery(supplierName);
                System.out.println("Result: " + result); // Debugging

                if (result != null && !result.isEmpty()) {
                    txtResultArea.setText(result); // Update the JTextArea
                    txtResultArea.setEditable(false); // Optional: Make the text area read-only
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Cannot find the supplier with name: "
                            + supplierName
                            + ". Please check your keyword again!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // Log any unexpected exceptions
                JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while searching. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            return null;
            }
            @Override
            protected void done() {
                btnSearch.setEnabled(true);
            }
        };
        worker.execute();
    }//GEN-LAST:event_btnSearchActionPerformed
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int option = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to log out?",
            "Confirm",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                btnLogout.setEnabled(false);
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try {
                    setVisible(false);
                    JOptionPane.showMessageDialog(
                    null,
                    "Logged out successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                    Thread.sleep(1000);
                    login.getInstance().setVisible(true);
                    setVisible(false);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    return null;
                }
                @Override
                protected void done() {
                    btnLogout.setEnabled(true);
                }
            };
        worker.execute();
    }//GEN-LAST:event_btnLogoutActionPerformed
    }
    private void btnBuyingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyingActionPerformed
        btnBuying.setEnabled(false);
        SwingWorker<Void, Void> worker =new SwingWorker<>() {
        @Override
            protected Void doInBackground() {
                buying Buying = new buying(Integer.parseInt(id));
                Buying.setVisible(true);
                setVisible(false);
                return null;
            }
            @Override
            protected void done() {
                btnBuying.setEnabled(true);
            }
        };
        worker.execute();
    }//GEN-LAST:event_btnBuyingActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        btnSearch.setEnabled(true);
        txtResultArea.setText("");
        if (txtSearch.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                null, "Supplier name is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                btnSearch.setEnabled(true);
                return;
        }
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() {
            if (!DBConnection.showSearchQuery(txtSearch.getText()).isEmpty()) {
                txtResultArea.selectAll();
                txtResultArea.replaceSelection("");
                txtResultArea.setText(DBConnection.showSearchQuery(txtSearch.getText()));
                txtResultArea.setEditable(false);

            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Cannot find the supplier with name: "
                        + txtSearch.getText()
                        + ". Please check your keyword again!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
                }
            return null;
            }
            @Override
            protected void done() {
                btnSearch.setEnabled(true);
            }
        };
        worker.execute();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtResourceIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResourceIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResourceIDActionPerformed

    private void btnOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOrderMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOrderMouseClicked

    
    // create new order entity (status pending)-> 
    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        // TODO add your handling code here:
        String supplierName = txtSearch.getText();
        if (supplierName.isEmpty() || txtResourceID.getText().isEmpty() || txtAmount.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                null, "Please fill all fields", "Warning", JOptionPane.WARNING_MESSAGE);
                btnOrder.setEnabled(true);
                return;
        }
        int resourceID = Integer.parseInt(txtResourceID.getText());
        int amount = Integer.parseInt(txtAmount.getText());

        DBConnection.createNewOrder(this.id,supplierName, resourceID,amount);
        
        
    }//GEN-LAST:event_btnOrderActionPerformed

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountActionPerformed

      private void customizeComponents() {
     String customerName = DBConnection.getCustomerName(Integer.parseInt(this.id));
    jCustomerNameLabel.setText("Welcom back, "+ customerName);
}
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuying;
    private javax.swing.JButton btnLogout;
    public javax.swing.JButton btnOrder;
    public javax.swing.JButton btnSearch;
    private javax.swing.JLabel jCustomerNameLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtResourceID;
    public javax.swing.JTextArea txtResultArea;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

