package moviecatalog.model;

import javax.sound.midi.MidiChannel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cornelius on 3/4/15.
 */
@XmlRootElement
public class MovieCatalog {

    private String user;
    private List<Movie> colection;

    public MovieCatalog(String user, List<Movie> colection) {
        this.user = user;
        this.colection = colection;
    }

    public MovieCatalog(String user) {
        this.user = user;
        this.colection = new ArrayList<Movie>();
    }

    public MovieCatalog() {
        this.colection = new ArrayList<Movie>();
    }

    @Override
    public String toString() {
        return "MovieCatalog{" +
                "user='" + user + '\'' +
                ", colection=" + colection +
                '}';
    }


    public void addMovie(Movie movie) {
        this.colection.add(movie);
    }

    public void addMovie() {

        this.colection.add(new Movie());
    }

    private String scanName(Scanner scanner) {
        System.out.println("Write the title");
        return scanner.next();
    }

    private String scanPath(Scanner scanner){
        System.out.println("Write the path");
        return scanner.next();
    }

    private String scanImdbID(Scanner scanner) {
        System.out.println("Write the IMDB id");
        return scanner.next();
    }

    private Date scanReleaseDate(Scanner scanner) {
        System.out.println("Write the release date");
        String dateS = scanner.next("[0-3][0-9].[01][0-2].[12][089][0-9][0-9]");
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double scanRating(Scanner scanner) {
        System.out.println("Write movie rating");
        return scanner.nextDouble();
    }

    private List<String> scanGenres(Scanner scanner) {
        List<String> genres = new ArrayList<String>();
        System.out.println("Write the genres");
        scanner.nextLine();
        String genre = scanner.nextLine();
        String[] aux = genre.split(" ");
        for (int i = 0; i < aux.length; i++)
            genres.add(aux[i]);
        return genres;
    }

    public Movie scanMovie() {
        Scanner keyboard = new Scanner(System.in);
        return new Movie(scanName(keyboard), scanGenres(keyboard), scanReleaseDate(keyboard), scanRating(keyboard), scanImdbID(keyboard),scanPath((keyboard)));
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Movie> getColection() {
        return colection;
    }

    public void setColection(List<Movie> colection) {
        this.colection = colection;
    }

    public List<Movie> getMoviesByGender(GENRE genre){
        List<Movie> list=new ArrayList<Movie>();
        for(Movie movie : this.colection){
            if(movie.testGenre(genre))
                list.add(movie);
        }
        return list;
    }

    public void playMovie(DefaultMutableTreeNode selectedNode){
        for (Movie m: getColection()){
            if (selectedNode.toString().compareTo(m.getName())==0){
                try {
                    m.playMovie();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void removeMovie(String name) {
        for (Movie m : colection) {
            if (m.getName().compareTo(name) == 0) {
                colection.remove(m);
                break;
            }
        }
    }
}

