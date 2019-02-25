/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moviedatabase.gui;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import moviedatabase.MovieTableModel;
import moviedatabase.entities.FavoriteList;
import moviedatabase.entities.Movie;

/**
 *
 * @author michael_papado
 */
public class StatisticsForm extends javax.swing.JFrame {
    
    List<Movie> movieList;

    /** Creates new form StatisticsForm */
    public StatisticsForm() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        top10MoviesBtn = new javax.swing.JButton();
        topMoviesPerListBtn = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        tablePanel.setVisible(false);
        jScrollPane1 = new javax.swing.JScrollPane();
        topMovieTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        top10MoviesBtn.setText("Οι Καλύτερες 10 Ταινίες");
        top10MoviesBtn.setPreferredSize(new java.awt.Dimension(260, 30));
        top10MoviesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                top10MoviesBtnActionPerformed(evt);
            }
        });

        topMoviesPerListBtn.setText("Οι Καλύτερες Ταινίες ανά Λίστα");
        topMoviesPerListBtn.setPreferredSize(new java.awt.Dimension(260, 30));
        topMoviesPerListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topMoviesPerListBtnActionPerformed(evt);
            }
        });

        topMovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(topMovieTable);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(top10MoviesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(topMoviesPerListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(top10MoviesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topMoviesPerListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(608, 342));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void top10MoviesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_top10MoviesBtnActionPerformed
        // TODO add your handling code here:
        TableModel movieTableModel = getTop10(); 
        topMovieTable.setModel(movieTableModel);
        tablePanel.setVisible(true);
    }//GEN-LAST:event_top10MoviesBtnActionPerformed

    private void topMoviesPerListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topMoviesPerListBtnActionPerformed
        // TODO add your handling code here:
        TableModel movieTableModel = getTopMovieFromLists();
        topMovieTable.setModel(movieTableModel);
        tablePanel.setVisible(true);
    }//GEN-LAST:event_topMoviesPerListBtnActionPerformed

    private TableModel getTop10() {
        movieList = new ArrayList();
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        movieList = em.createQuery("SELECT m from Movie m ORDER BY m.rating DESC").setMaxResults(10).getResultList();
        em.getTransaction().commit();
        String[] columnNames = {"Τίτλος ταινίας", "Βαθμολογία"};
        TableModel movieTableModel = new MovieTableModel(movieList, columnNames);
        return movieTableModel;
    }
    
    private TableModel getTopMovieFromLists() {
        List<FavoriteList> FList = new ArrayList();
        movieList = new ArrayList();
        EntityManager em;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        FList = em.createNamedQuery("FavoriteList.findAll").getResultList();
        for (FavoriteList fav: FList) {
            float q = (float) em.createQuery("select max(m.rating) from Movie m where m.favoriteListId = :favId").setParameter("favId", fav).getSingleResult();
            Movie mov = (Movie) em.createNamedQuery("Movie.findByRatingAndFavoriteList").setParameter("rating", q).setParameter("favoriteList", fav).getSingleResult();
            movieList.add(mov);
        }
        em.getTransaction().commit();
        String[] columnNames = {"Τίτλος ταινίας"};
        TableModel movieTableModel = new MovieTableModel(movieList, columnNames);
        return movieTableModel;
    }
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
            java.util.logging.Logger.getLogger(StatisticsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatisticsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatisticsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatisticsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatisticsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JButton top10MoviesBtn;
    private javax.swing.JTable topMovieTable;
    private javax.swing.JButton topMoviesPerListBtn;
    // End of variables declaration//GEN-END:variables

}
