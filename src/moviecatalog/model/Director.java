package moviecatalog.model;

import java.util.ArrayList;

/**
 * Created by Cornelius on 04.04.2015.
 */
public class Director {
    private String name;
    private ArrayList<String> movies;

    Director(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<String> movies) {
        this.movies = movies;
    }
}
