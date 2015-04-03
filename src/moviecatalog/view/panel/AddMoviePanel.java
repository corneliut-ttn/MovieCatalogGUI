package moviecatalog.view.panel;

import moviecatalog.model.GENRE;
import moviecatalog.model.Movie;
import moviecatalog.model.MovieCatalog;
import moviecatalog.utils.ExtensionFilter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Cornelius on 03.04.2015.
 */
public class AddMoviePanel {

    JFrame frame;
    private String currentGenres;
    private JTextField movieTitleField;
    private JTextField genresField;
    private JSpinner movieLaunchDateSpinner;
    private JSpinner ratingField;
    private JTextField imdbIDField;
    private JDialog dialog;
    private JFileChooser fileChooser;
    private JButton browserButton;
    private JButton saveJButton;
    private JTextArea actorsField;
    private JTextField directorField;
    private final JList listbox = new JList(GENRE.values());
    private JPanel northPanel;

    public AddMoviePanel(final DefaultMutableTreeNode parent, final MovieCatalog movieCatalog, final DefaultTreeModel model) {
        saveJButton = new JButton("Save");
        browserButton=new JButton("Browse");
        northPanel = new JPanel();
        northPanel.add(saveJButton);

        initialize();

        browserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int result = fileChooser.showOpenDialog(northPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Aprove");
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("Cancel was selected");
                }
            }
        });

        listbox.setSelectedValue(parent.toString(), true);

        FileFilter type = new ExtensionFilter("Video files",
                new String[]{".avi", ".mpg", ".mpeg", ".mp4",".mkv"}) {
        };
        fileChooser.addChoosableFileFilter(type);
        fileChooser.setFileFilter(type);
        JButton close = new JButton("Close");
        northPanel.add(close);
        saveJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String movieTitle = movieTitleField.getText();
                ArrayList<String> genres = new ArrayList<String>();
                for (String genre : listbox.getSelectedValuesList().toString().replace("[", "").replace("]", "").replace(" ", "").split(","))
                    genres.add(genre);
                currentGenres=(listbox.getSelectedValuesList().toString().replace("[","").replace("]","").replace(" ",""));
                Date movieDate = (Date) movieLaunchDateSpinner.getValue();
                double rating = (Double) ratingField.getValue();
                String imdbID = imdbIDField.getText();
                String actors[]=actorsField.getText().split(",");
                String director = directorField.getText();

                System.out.println("Movie created");
                movieCatalog.addMovie(new Movie(movieTitle, genres, movieDate, rating, imdbID,fileChooser.getSelectedFile().toString()));

                updateTreeOnAdd(movieTitle, model);
                dialog.dispose();
            }
        });


        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        setAspect();
    }

    private void initialize() {
        movieTitleField = new JTextField(30);
        movieLaunchDateSpinner = new JSpinner(new SpinnerDateModel());
        ratingField = new JSpinner(new SpinnerNumberModel(5, 0, 10, 00.1));
        imdbIDField = new JTextField(30);
        dialog = new JDialog(frame, "Add Movie", true);
        fileChooser = new JFileChooser();
        actorsField = new JTextArea("", 5, 30);
        directorField = new JTextField(30);
    }

    private void setAspect() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = gbc.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JLabel("Title:"));
        panel.add(movieTitleField, gbc);
        panel.add(new JLabel("Genres:"));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(listbox);
        panel.add(scrollPane, gbc);
        panel.add(new JLabel("Release Date:"));
        panel.add(movieLaunchDateSpinner, gbc);
        panel.add(new JLabel("Rating:"));
        panel.add(ratingField, gbc);
        panel.add(new JLabel("ID IMDB:"));
        panel.add(imdbIDField, gbc);
        panel.add(new JLabel("Director:"));
        panel.add(directorField, gbc);
        panel.add(new JLabel("Actors:"));
        panel.add(actorsField, gbc);
        panel.add(new JLabel("Location:"));
        panel.add(browserButton, gbc);
        dialog.getContentPane().add(northPanel, "North");
        dialog.getContentPane().add(panel);
        dialog.pack();

        dialog.setLocation(500, 200);
        dialog.setVisible(true);
    }

    private void updateTreeOnAdd(String name, DefaultTreeModel model) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) model.getRoot();
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            DefaultMutableTreeNode parent2 = (DefaultMutableTreeNode) parent.getChildAt(i);
            if (parent2 != null) {
                if (currentGenres.contains(parent2.toString()))
                    model.insertNodeInto(new DefaultMutableTreeNode(name), parent2, parent2.getChildCount());
            }
        }
    }
}
