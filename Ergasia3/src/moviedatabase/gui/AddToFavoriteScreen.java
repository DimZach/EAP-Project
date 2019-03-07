package moviedatabase.gui;

import moviedatabase.entities.Genre;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import moviedatabase.service.AddToFavorites;
import moviedatabase.MoviePOJO;
import moviedatabase.MovieTableModel;
import moviedatabase.entities.FavoriteList;
import moviedatabase.entities.Movie;


/**
 *
 * @author michael_papado
 */
public class AddToFavoriteScreen extends javax.swing.JFrame {
    
    private MoviePOJO oneMovie;
    List<Movie> myList;
    private Boolean textBool = false; //Μεταβλητή που θα χρησιμοποιηθεί για έλεγχο του Year Input
    private Boolean comboBool = false; //Μεταβλητή που θα χρησιμοποιηθεί αν έχει επιλεγεί genre
    private Boolean insert = false; //Μεταβλητή που δείχνει ότι έχει επιλεγεί κάποια γραμμή στον πίνακα
    /**
     * Creates new form MainScreen
     */
    public AddToFavoriteScreen() {
        initComponents();

        genreCombo.setSelectedIndex(-1); //Ορίζουμε το index -1 ώστε να μην δείχνει κάποια κατηγορία στην αρχή
        favoriteListCombo.setSelectedIndex(-1); //Ορίζουμε το index -1 ώστε να μην δείχνει κάποια λίστα στην αρχή
        
        //Βάζουμε ένα action Listener στο combo των genre ώστε όταν γίνει αλλαγή
        //να ενεργοποιεί την μεταβλητή comboBool και να καλεί την μέθοδο enableSearch()
        genreCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBool = true;
                enableSearch();
              }
            }); 
        
    }
    
    private void enableSearch() {
        //Γίνεται έλεγχος αν οι 2 μεταβλητές είναι true
        if (textBool == true && comboBool == true) {
            //και εφόσον είναι true ενεργοποιεί το κουμπί search
            searchBtn.setEnabled(true);
        }
    }
    
    private TableModel createTable() {
        //Αρχικοποιούμε την λίστα myList
        myList = new ArrayList();
        //Αποθηκεύουμε το genre που είναι επιλεγμένο σε μεταβλητή
        Object gen = (Genre) genreCombo.getSelectedItem();
        EntityManager em; // Ο EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDatabasePU"); // Το EntityManagerFactory
        em = emf.createEntityManager(); //αρχικοποιούμε τη μεταβλητή em
        em.getTransaction().begin(); //ξεκινάμε μια καινούργια συναλλαγή για να τραβήξουμε από τη βάση δεδομένων τις ταινίες
        try {
            int year = Integer.parseInt(yearTextInput.getText()); //Μετατρέπουμε την ημερομηνία σε αριθμό
            //Με κλήση στην βάση με κάνουμε ερώτημα να μας φέρει τις ταινίες με την ημερομηνία που όρισαμε και την κατηγορία
            myList = em.createNamedQuery("Movie.findByReleaseDateAndGenre").setParameter("releaseDate", year).setParameter("genreId", gen).getResultList();
        } catch (NumberFormatException e) {
            System.out.println("Wrong");
        }
        em.getTransaction().commit(); //Κάνουμε commit την συναλλαγή
        String[] columnNames = {"Τίτλος ταινίας", "Βαθμολογία", "Περιγραφή"}; //Οι τίτλοι από τις στήλες
        TableModel movieTableModel = new MovieTableModel(myList,columnNames); //Δημιουργούμε ενα νέο tableModel με την λίστα των ταινιών και τους τίτλους από τις στήλες
        return movieTableModel; //Επιστρέφουμε το table model.
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        MovieDatabasePUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("MovieDatabasePU").createEntityManager();
        genreQuery = java.beans.Beans.isDesignTime() ? null : MovieDatabasePUEntityManager.createQuery("SELECT g FROM Genre g");
        genreList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : genreQuery.getResultList();
        addToFavorites1 = new moviedatabase.service.AddToFavorites();
        favoriteListQuery = java.beans.Beans.isDesignTime() ? null : MovieDatabasePUEntityManager.createQuery("SELECT f FROM FavoriteList f");
        favoriteListList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : favoriteListQuery.getResultList();
        jLabel1 = new javax.swing.JLabel();
        genreCombo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        yearTextInput = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        movieTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        favoriteListCombo = new javax.swing.JComboBox<>();
        removeFromListBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Είδος Ταινίας");

        genreCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Genre) {
                    Genre mec = (Genre)value;
                    setText(mec.getName());
                }
                return this;
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, genreList, genreCombo);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel2.setText("Έτος Κυκλοφορίας");

        yearTextInput.setColumns(8);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, addToFavorites1, org.jdesktop.beansbinding.ELProperty.create("${yearText}"), yearTextInput, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        yearTextInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                yearTextInputKeyTyped(evt);
            }
        });

        searchBtn.setText("Αναζήτηση");
        searchBtn.setEnabled(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Καθαρισμός Κριτηρίων");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        movieTable.setAutoCreateRowSorter(true);
        movieTable.getTableHeader().setReorderingAllowed(false);
        movieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(movieTable);

        jLabel3.setText("Προσθήκη σε Λίστα");

        favoriteListCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof FavoriteList) {
                    FavoriteList mec = (FavoriteList)value;
                    setText(mec.getName());
                }
                return this;
            }
        });

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, favoriteListList, favoriteListCombo);
        bindingGroup.addBinding(jComboBoxBinding);

        favoriteListCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                favoriteListComboItemStateChanged(evt);
            }
        });

        removeFromListBtn.setText("Αφαίρεση από Λίστα");
        removeFromListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromListBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(favoriteListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(removeFromListBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(genreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(yearTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchBtn)
                                .addGap(18, 18, 18)
                                .addComponent(clearBtn)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(genreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(yearTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBtn)
                    .addComponent(clearBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(favoriteListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeFromListBtn))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        TableModel movieTableModel = createTable(); //Δημιουργούμε ένα νέο πίνακα
        movieTable.setModel(movieTableModel); //Ορίζουμε στον νέο αυτό πίνακα το table model
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(movieTable.getModel()); //Φτιάχνουμε ένα νέο sorter
        sorter.setSortable(0, false); //Ορίζουμε ότι η πρώτη στήλη δεν θα κάνει sorting
        sorter.setSortable(2, false); //Ορίζουμε ότι ούτε η 3η στήλη θα κάνει sorting
        movieTable.setRowSorter(sorter); //Θέτουμε το sorter στον πίνακα που είχαμε δημιουργήσει
    }//GEN-LAST:event_searchBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        genreCombo.setSelectedIndex(-1); //Ορίζουμε το index του comboBox στο -1 ώστε να είναι κενό
        yearTextInput.setText(null); //Μηδενίζουμε το πεδίο που δίνουμε την χρονολογία
        textBool = false; //Θέτουμε την μεταβλητή textBool ως false
        comboBool = false; //Θέτουμε και την μεταβλητή comboBool ως false
        searchBtn.setEnabled(false); //Απενεργοποιούμε το κουμπί search
        myList = new ArrayList(); //Η λίστα με τις ταινίες μηδενίζεται
        String[] columnNames = {"Τίτλος ταινίας", "Βαθμολογία", "Περιγραφή"}; //Ορίζουμε ξανά τους τίτλους από τις στήλες του πίνακα
        TableModel movieTableModel = new MovieTableModel(myList,columnNames); //Δημιουργούμε ενα νέο tableModel με την κενή λίστα και τους τίτλους από τις στήλες
        movieTable.setModel(movieTableModel); //Επιστρέφουμε το table model.
    }//GEN-LAST:event_clearBtnActionPerformed

    private void yearTextInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearTextInputKeyTyped
        textBool = true; //Ενεργοποιούμε την μεταβλητή textBool
        enableSearch(); //Καλούμε την μέθοδο enableSearch() για να δούμε αν μπορεί να ενεργοποιηθεί το κουμπί search
    }//GEN-LAST:event_yearTextInputKeyTyped

    private void removeFromListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromListBtnActionPerformed
        Movie selected = myList.get(movieTable.getSelectedRow()); //Δημιουργούμε βοηθητική μεταβλητή movie που θα αποθηκεύει την επιλεγμένη ταινία
        if (selected.getFavoriteListId() != null) { //Ελέγχουμε αν το favorite list id της ταινίας δεν είναι null
            favoriteListCombo.setSelectedIndex(-1); //και εφόσον δεν είναι θέτουμε το index του favorite list Combo στην τιμή -1
            AddToFavorites addFav = new AddToFavorites(); //Δημιουργούμε νέο στιγμιότυπο από την κλάση AddToFavorites
            addFav.removeFromFavorite(selected); //και καλείται η μέθοδος removeFromFavorite με την επιλεγμένη ταινία
        }
    }//GEN-LAST:event_removeFromListBtnActionPerformed

    private void movieTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieTableMouseClicked
        Movie selected = myList.get(movieTable.getSelectedRow()); //Δημιουργούμε βοηθητική μεταβλητή movie που θα αποθηκεύει την επιλεγμένη ταινία
        if (selected.getFavoriteListId() != null) { //Ελέγχουμε αν το favorite list id της ταινίας δεν είναι null
            favoriteListCombo.setSelectedItem(selected.getFavoriteListId()); //και εφόσον δεν είναι Θέτουμε το index του favorite list combo στην τιμή που έχει η κατηγορία της επιλεγμένης ταινίας
            insert = true; //Έχει επιλεγεί ταινία
        } else {
            favoriteListCombo.setSelectedIndex(-1); //Αν το favorite list id της ταινίας είναι null θέτουμε το index -1 για να είναι κενή η επιλογή
            insert = true; //Έχει επιλεγεί ταινία
        }
    }//GEN-LAST:event_movieTableMouseClicked

    private void favoriteListComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_favoriteListComboItemStateChanged
        if (evt.getStateChange() == 1) { //Ελέγχουμε αν έχει αλλάξει το state του combo σε 1
            if (insert) { //Αν η μεταβλητή insert είναι αληθής
                FavoriteList favSelected = (FavoriteList) favoriteListCombo.getSelectedItem(); //Ορίζουμε μεταβλητή favList που αποθηκεύουμε την επιλεγμένη κατηγορία
                FavoriteList movSelected = (FavoriteList) myList.get(movieTable.getSelectedRow()).getFavoriteListId(); //Ορίζουμε μεταβλητή favList που αποθηκεύουμε την κατηγορία της ταινίας
                if (favSelected != movSelected) { //Αν η επιλεγμένη κατηγορία δεν είναι ίδια με της ταινίας
                    System.out.println("Drop Selected");
                    try {
                        Movie selected = myList.get(movieTable.getSelectedRow()); //αποθηκεύουμε σε μεταβλητή την επιλεγμένη ταινία
                        AddToFavorites addFav = new AddToFavorites(); //Δημιουργούμε νέο στιγμιότυπο από την κλάση AddToFavorites
                        addFav.addToFavorite(favSelected, selected); //Καλούμε την μέθοδο addToFavorite()
                        System.out.println("Added to DB");
                    } catch (Exception e) {
                        System.out.println("");            
                    }
                    insert = false; //Αντιστρέφουμε την μεταβλητή insert
                }
            }
        }
    }//GEN-LAST:event_favoriteListComboItemStateChanged
     
    private Date getDateFromText(JTextField yearSearchInput) {
        String dateText = yearSearchInput.getText(); //διαβάζουμε το text από το field yearTextInput
        Date yearDate = null; //δημιουργούμε μεταβλητή date που την ορίζουμε κενή
        try {
            SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy"); //Φτιάχνουμε μεταβλητή που θα διαβάζει ένα simple date της μορφής yyyy (πχ 2018)
            yearDate = localDateFormat.parse(dateText); //Κάνουμε cast το text από την μεταβλητή dateText σε ημερομηνία
        } catch(ParseException nfe) {
            System.err.println("Wrong Date Format");
        }
        return yearDate; //επιστρέφουμε την ημερομηνία στην σωστή μορφή
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
            java.util.logging.Logger.getLogger(AddToFavoriteScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddToFavoriteScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddToFavoriteScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddToFavoriteScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddToFavoriteScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager MovieDatabasePUEntityManager;
    private moviedatabase.service.AddToFavorites addToFavorites1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> favoriteListCombo;
    private java.util.List<moviedatabase.entities.FavoriteList> favoriteListList;
    private javax.persistence.Query favoriteListQuery;
    private javax.swing.JComboBox<String> genreCombo;
    private java.util.List<moviedatabase.entities.Genre> genreList;
    private javax.persistence.Query genreQuery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable movieTable;
    private javax.swing.JButton removeFromListBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField yearTextInput;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
