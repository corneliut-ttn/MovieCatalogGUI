package moviecatalog.controller;

import moviecatalog.model.Movie;
import moviecatalog.model.MovieCatalog;
import moviecatalog.view.MainFrame;
import moviecatalog.xml.JavaToXML;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Cornelius on 02.04.2015.
 */
public class MovieCatalogController {

    private MainFrame view;
    private MovieCatalog model;
    private JavaToXML parser;

    public MovieCatalogController() {
        model = new MovieCatalog();
        parser = new JavaToXML();
        importXML();
        view = new MainFrame(this);
    }

    public void exportXML() {
        try {
            parser.exportToXML(model);
        } catch (JAXBException xmle) {
            xmle.printStackTrace();
        }
        System.out.println("Export successfully.");
    }

    public void importXML() {
        try {
            this.model = parser.importToJava();
        } catch (JAXBException xmle) {
            xmle.printStackTrace();
        }
        System.out.println("Import successfully.");
    }

    public void addMovie(Movie movie) {
        model.addMovie(movie);
        updateView();
    }

    public void startCatalog(String user) {
        importXML();
        model.setUser(user);
        view.showCatalog();
    }

    public void updateView() {

    }

    public void edit(Movie movie) {

    }

    public void populate() {
        Movie A = new Movie("The Imitation Game", new ArrayList<String>(Arrays.asList("Biography", "Drama", "Thriller")), new Date(114, 12, 25), 8.2, "2084970",null);
        Movie B = new Movie("Defiance", new ArrayList<String>(Arrays.asList("Action", "Drama", "History")), new Date(108, 01, 16), 7.2, "1034303",null);
        Movie C = new Movie("The Shawshank Redemption", new ArrayList<String>(Arrays.asList("Crime", "Drama")), new Date(94, 10, 14), 9.3, "0111161",null);
        Movie D = new Movie("The Godfather", new ArrayList<String>(Arrays.asList("Crime", "Drama")), new Date(72, 03, 24), 9.2, "0068646",null);
        Movie E = new Movie("The Godfather: Part II", new ArrayList<String>(Arrays.asList("Crime", "Drama")), new Date(74, 12, 20), 9.1, "0071562",null);
        Movie F = new Movie("The Dark Knight", new ArrayList<String>(Arrays.asList("Action", "Crime", "Drama")), new Date(108, 07, 18), 9.0, "0468569",null);
        Movie G = new Movie("Pulp Fiction", new ArrayList<String>(Arrays.asList("Crime", "Drama", "Thriller")), new Date(94, 10, 14), 7.2, "0110912",null);
        Movie H = new Movie("Fight Club", new ArrayList<String>(Arrays.asList("Drama")), new Date(99, 10, 15), 7.2, "0137523",null);
        Movie I = new Movie("Schindler's List", new ArrayList<String>(Arrays.asList("Biography", "Drama", "History")), new Date(93, 02, 4), 8.9, "0108052",null);
        Movie J = new Movie("The Lord of the Rings: The Return of the King", new ArrayList<String>(Arrays.asList("Adventure", "Fantasy")), new Date(103, 12, 17), 8.9, "0167260",null);
        Movie K = new Movie("Star Wars: Episode V - The Empire Strikes Back", new ArrayList<String>(Arrays.asList("Action", "Adventure", "Fantasy")), new Date(80, 06, 20), 8.8, "0080684",null);
        Movie L = new Movie("3 Idiots", new ArrayList<String>(Arrays.asList("Comedy", "Drama")), new Date(109, 12, 25), 8.5, "1187043",null);

        model.addMovie(A);
        model.addMovie(B);
        model.addMovie(C);
        model.addMovie(D);
        model.addMovie(E);
        model.addMovie(F);
        model.addMovie(G);
        model.addMovie(H);
        model.addMovie(I);
        model.addMovie(J);
        model.addMovie(K);
        model.addMovie(L);
    }

    public MovieCatalog getModel() {
        return model;
    }
}
