package moviecatalog.model;

import java.util.ArrayList;

/**
 * Created by Cornelius on 04.04.2015.
 */
public class Actor {
    private String name;
    private ArrayList<Movie> movies;

    Actor(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getFilms() {
        return movies;
    }

    public void setFilms(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
