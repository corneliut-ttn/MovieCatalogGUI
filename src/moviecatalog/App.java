package moviecatalog;

import moviecatalog.controller.MovieCatalogController;
import moviecatalog.view.MainFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        MovieCatalogController MCC=new MovieCatalogController();

    }
}
