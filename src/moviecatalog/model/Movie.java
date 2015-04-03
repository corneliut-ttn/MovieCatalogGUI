package moviecatalog.model;

import moviecatalog.exception.InvalidDataException;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by cornelius on 3/4/15.
 */
@XmlRootElement
public class Movie {

    private String path="D:\\Movies and Series\\Arrow.S03E17.720p.HDTV.X264-DIMENSION\\Arrow.S03E17.720p.HDTV.X264-DIMENSION.mkv";
    private String name;
    private List<GENRE> categories;
    private Date launchDate;
    private double rating;
    private String imdbID;
    private String description;

    public Movie(String name, List<String> categories, Date launchDate, double rating, String imdbID,String path) {
        this.categories = new ArrayList<GENRE>();
        this.name = name;
        for (ListIterator<String> it = categories.listIterator(); it.hasNext(); ) {
            String aux = it.next();
            try {
                this.categories.add(GENRE.valueOf(aux));
            } catch (IllegalArgumentException ex) {
                System.out.println("Error " + ex + " genre invalid");
            }
        }
        try {
            this.setLaunchDate(launchDate);
        } catch (InvalidDataException ide) {
            System.out.println(ide.getMessage());
        }
        try {
            this.setRating(rating);
        } catch (InvalidDataException ide) {
            System.out.println(ide.getMessage());
        }
        this.imdbID = imdbID;
        this.path=path;
    }

    public Movie() {
        this.categories = new ArrayList<GENRE>();
    }

    @Override
    public String toString() {
        return "Movie:" +
                "ImdbID :" + imdbID + "\n" +
                "Movie title :" + name + "\n" +
                "Genres :" + categories + "\n" +
                "Launch Date :" + launchDate + "\n" +
                "Rating :" + rating + "\n"+
                "Description : " + description +"\n\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GENRE> getCategories() {
        return categories;
    }

    public void setCategories(List<GENRE> categories) {
        this.categories = categories;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) throws InvalidDataException {
        if ((launchDate).before(new Date()) == true) {
            this.launchDate = launchDate;
        } else {
            throw new InvalidDataException("Invalid date");
        }
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) throws InvalidDataException {
        if (rating < 0 || rating > 10) throw new InvalidDataException("Invalid rating value");
        this.rating = rating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public boolean testGenre(GENRE genre){
        return this.categories.contains(genre);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void playMovie() throws IOException {
        File file=new File(path);
        if(file.exists()){
            if(Desktop.isDesktopSupported()){
                Desktop.getDesktop().open(file);
            }
        }else
            System.out.println("File does not exist");
    }
}
