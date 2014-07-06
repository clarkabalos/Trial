/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI;

import Beans.IngredientBean;
import Beans.RawBean;
import Beans.RecipeBean;
import DAO.Implementation.IngredientDAOImplementation;
import DAO.Implementation.RawDAOImplementation;
import DAO.Implementation.RecipeDAOImplementation;
import DAO.Interface.IngredientDAOInterface;
import DAO.Interface.RawDAOInterface;
import DAO.Interface.RecipeDAOInterface;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author earleenjanefuentes
 */
public class SampleTableDisplay extends javax.swing.JFrame {

    /**
     * Creates new form SampleTableDisplay
     */
    public SampleTableDisplay() {
        initComponents();
        createTable();
        getRecipes.setBackground(Color.red);
        //getRecipes.setBorder(null);
         getRecipes.setBorderPainted(false);
          getRecipes.setOpaque(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        recipeTable = new javax.swing.JTable();
        getRecipes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        recipeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        recipeTable.setEnabled(false);
        jScrollPane1.setViewportView(recipeTable);

        getRecipes.setForeground(new java.awt.Color(255, 255, 255));
        getRecipes.setText("Get Recipes");
        getRecipes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getRecipesActionPerformed(evt);
            }
        });

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(stockTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(getRecipes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(getRecipes)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void createTable(){
        RecipeDAOInterface rcImp = new RecipeDAOImplementation();
        IngredientDAOInterface inImp = new IngredientDAOImplementation();
        RawDAOInterface rmImp = new RawDAOImplementation();
        
        ArrayList<RecipeBean> avRecipes = rcImp.getRecipeByStatus("available");
        String col[] = {"Recipe ID", "Name", "Cost", "Stock"};
        DefaultTableModel recipeModel = new DefaultTableModel(col, 0);
        int i, j;
        
        for(i=0; i  < avRecipes.size(); i++){
            RecipeBean r = avRecipes.get(i);
            Object[] recipe = {r.getRecipeID(), r.getRecipe(), r.getCost(), r.getStock()};
            recipeModel.addRow(recipe);
        }
        
        //System.out.println("hello");
        recipeTable.setModel(recipeModel);
        
        String cols[] = {"Name", "Stock"};
        DefaultTableModel stockModel = new DefaultTableModel(cols,0);
        
        for(i=0; i < avRecipes.size(); i++){
            RecipeBean rc = avRecipes.get(i);
            ArrayList<IngredientBean> ingredients = new ArrayList<IngredientBean> ();
            ingredients = inImp.getAllIngredients(rc);
            rc.setIngredients(ingredients);
            Object[] rec = {"<html><p style = 'color:red'><b>" + rc.getRecipe() + "</b></p></html>", "<html><p style = 'color:red'><b>" + String.format("%.2f", rc.computeStock()) + "</b></p></html>"};
            stockModel.addRow(rec);
            
            
            for(j=0; j< ingredients.size(); j++){
                RawBean raw = ingredients.get(j).getRaw();
                Object[] rawm = {"     " + ingredients.get(j).getAmount() + " " + raw.getRaw(), raw.getStock()};
                stockModel.addRow(rawm);
            }
            
        }
        
        stockTable.setModel(stockModel);
        
        // notification table
        DefaultTableModel notificationModel = new DefaultTableModel(cols,0);
        
        
    }
    
    
    private void getRecipesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getRecipesActionPerformed
        
        createTable();
    }//GEN-LAST:event_getRecipesActionPerformed

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
            java.util.logging.Logger.getLogger(SampleTableDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SampleTableDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SampleTableDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SampleTableDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SampleTableDisplay().setVisible(true);
                
            }
        });
        
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton getRecipes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable recipeTable;
    private javax.swing.JTable stockTable;
    // End of variables declaration//GEN-END:variables
}
