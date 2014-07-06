/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package btf;

import Beans.IngredientBean;
import Beans.RawBean;
import Beans.RecipeBean;
import Beans.TransactionBean;
import DAO.Implementation.IngredientDAOImplementation;
import DAO.Implementation.RawDAOImplementation;
import DAO.Implementation.RecipeDAOImplementation;
import DAO.Implementation.SalesDAOImplementation;
import DAO.Implementation.TransactionDAOImplementation;
import DAO.Interface.IngredientDAOInterface;
import DAO.Interface.RawDAOInterface;
import DAO.Interface.RecipeDAOInterface;
import DAO.Interface.SalesDAOInterface;
import DAO.Interface.TransactionDAOInterface;
import UI.CategoryManagement;
import UI.CreatePassword;
import UI.Login;
import UI.RMManagement;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author earleenjanefuentes
 */
public class Btf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       SalesDAOInterface sImp = new SalesDAOImplementation();
       RecipeDAOInterface rImp = new RecipeDAOImplementation();
       IngredientDAOInterface rwImp = new IngredientDAOImplementation();
       RecipeBean r = rImp.getRecipeBean(20007);
       //ArrayList<RecipeBean> aRecipe = sImp.getAllSales(null)
       
       System.out.println(r.getRecipe());
       System.out.println("COST = " + r.getCost());
       System.out.println("ACTUAL PRICE = " + r.getActualPrice());
       System.out.println("QUANTITY SALES FOR 2014-07-01 = " + sImp.sumQuantityByRecipeByDay("2014-07-01", "sales", r) + " TOTAL SALES = " + sImp.sumSalesByRecipeByDay(r, "2014-07-01"));
       System.out.println("QUANTITY COMPLIMENTARY FOR 2014-07-01 = " + sImp.sumQuantityByRecipeByDay("2014-07-01", "complimentary", r) + " TOTAL SALES = " + sImp.sumComplimentarySalesByRecipeByDay(r, "2014-07-01"));
       
       
       //<--- CLARK'S CODE STARTS HERE --->
       //check if there's already a password
       /*File f = new File("btf.xml");
	  if(f.exists()){
		  System.out.println("File existed");
                  new Login().setVisible(true);
	  } else{
		  System.out.println("File not found!");
                  new CreatePassword().setVisible(true);
	  }*/
          /*try {
                    new RMManagement().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RMManagement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(RMManagement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RMManagement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(RMManagement.class.getName()).log(Level.SEVERE, null, ex);
                }*/
       new CategoryManagement().setVisible(true);
       //<--- CLARK'S CODE ENDS HERE --->
    }
    
}
