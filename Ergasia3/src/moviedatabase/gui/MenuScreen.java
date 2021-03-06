
package moviedatabase.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import moviedatabase.service.MovieDatabase;

/**
 *
 * @author dim_zachos
 */
public class MenuScreen extends javax.swing.JFrame {

    /**
     * Creates new form MenuScreen
     */
    public MenuScreen() {
        initComponents();
        
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        saveMovieButton = new javax.swing.JButton();
        listButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        statsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 0), 1, true));

        saveMovieButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ergasia3/images/download_blue.png"))); // NOI18N
        saveMovieButton.setText("Ανάκτηση και Αποθήκευση Ταινιών");
        saveMovieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMovieButtonActionPerformed(evt);
            }
        });

        listButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ergasia3/images/favorites-icon.png"))); // NOI18N
        listButton.setText("Διαχείριση Αγαπημένων Ταινιών");
        listButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listButtonActionPerformed(evt);
            }
        });

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ergasia3/images/search.png"))); // NOI18N
        searchButton.setText("Αναζήτηση Ταινιών");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        statsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ergasia3/images/statistics.png"))); // NOI18N
        statsButton.setText("Στατιστικά");
        statsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsButtonActionPerformed(evt);
            }
        });

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ergasia3/images/exit.png"))); // NOI18N
        exitButton.setText("Έξοδος");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveMovieButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(listButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(saveMovieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listButton)
                .addGap(18, 18, 18)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(statsButton)
                .addGap(18, 18, 18)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //κουμπί για κλείσιμο της εφαρμογής
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed
    //κουμπί διαχείρισης λιστών αγαπημένων ταινιών
    private void listButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listButtonActionPerformed
        FavoriteMovieScreen fm = new FavoriteMovieScreen();
        fm.setVisible(true);
    }//GEN-LAST:event_listButtonActionPerformed
    //κουμπί αναζήτησης ταινιών
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        AddToFavoriteScreen s = new AddToFavoriteScreen();
        s.setVisible(true);
    }//GEN-LAST:event_searchButtonActionPerformed
    //κουμπί ανάκτησης και αποθήκευσης ταινιών
    private void saveMovieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMovieButtonActionPerformed
        MovieDatabase m = new MovieDatabase();
        try {
            m.restoreMovies();
        } catch (Exception ex) {
            Logger.getLogger(MenuScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveMovieButtonActionPerformed
    //κουμπι στατιστικών
    private void statsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsButtonActionPerformed

        StatisticsForm sf = new StatisticsForm();
        sf.setVisible(true);
    }//GEN-LAST:event_statsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton listButton;
    private javax.swing.JButton saveMovieButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton statsButton;
    // End of variables declaration//GEN-END:variables
}
