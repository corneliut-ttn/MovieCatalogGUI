package moviecatalog.view.panel;

import moviecatalog.model.GENRE;
import moviecatalog.model.Movie;
import moviecatalog.model.MovieCatalog;
import moviecatalog.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Cornelius on 03.04.2015.
 */
public class TablePanel extends JPanel {

    private JTable table;
    private MainFrame mainFrame;

    public TablePanel(GENRE genre, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        table = new JTable();
        java.util.List<Movie> movies=mainFrame.getController().getModel().getMoviesByGender(genre);
        String columnNames[] = {"Name", "Categories", "Launch Date", "Rating", "ID IMDB"};
        String dataValues[][] = new String[movies.size()][5];
        for (int i = 0; i < movies.size(); i++) {
            int j = 0;
            dataValues[i][j++] = movies.get(i).getName();
            dataValues[i][j++] = movies.get(i).getCategories().toString();
            dataValues[i][j++] = movies.get(i).getLaunchDate().toString();
            dataValues[i][j++] = String.valueOf(movies.get(i).getRating());
            dataValues[i][j++] = String.valueOf(movies.get(i).getImdbID());
        }
        table = new JTable(dataValues, columnNames);
        setLayout(new BorderLayout());
        add(table, BorderLayout.CENTER);
    }
}
