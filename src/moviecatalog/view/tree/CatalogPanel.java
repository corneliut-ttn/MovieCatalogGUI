package moviecatalog.view.tree;

import moviecatalog.model.GENRE;
import moviecatalog.model.Movie;
import moviecatalog.model.MovieCatalog;
import moviecatalog.view.MainFrame;
import moviecatalog.view.panel.TablePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Cornelius on 03.04.2015.
 */
public class CatalogPanel extends JPanel {

    private MainFrame mainFrame;
    private DefaultMutableTreeNode root;
    private TreeModel treeModel;
    private JTree catalogTree;
    private java.util.List<GENRE> enumList;

    public CatalogPanel(MovieCatalog movieCatalog, JSplitPane splitPane) {
        catalogTree = new JTree();
        createTree(movieCatalog);
        catalogTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        setLayout(new BorderLayout());

        add(new JScrollPane(catalogTree), BorderLayout.CENTER);
        //add(splitPane);
    }

    public CatalogPanel(MainFrame mainFrame) {
        enumList = Arrays.asList(GENRE.values());
        this.mainFrame = mainFrame;
        createTree(mainFrame.getController().getModel());
        setupTree();

        setLayout(new BorderLayout());
        setAspect();
        add(new JScrollPane(catalogTree), BorderLayout.CENTER);
    }

    public CatalogPanel() {
        enumList = Arrays.asList(GENRE.values());

        createTree();
        setupTree();

        setLayout(new BorderLayout());
        setAspect();
        add(new JScrollPane(catalogTree), BorderLayout.CENTER);
    }

    private void setupTree() {

        catalogTree.setEditable(true);
        catalogTree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        catalogTree.setShowsRootHandles(true);

        catalogTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                listenTree();
            }
        });
    }

    private void setAspect() {
        setBackground(new Color(175, 131, 135));
        setMinimumSize(new Dimension(250, 500));
        Border innerBorder = BorderFactory.createTitledBorder("Catalog");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

    private void createTree() {
        root = new DefaultMutableTreeNode("Movies");

        for (GENRE value : enumList) {
            DefaultMutableTreeNode branch = new DefaultMutableTreeNode(value.toString());
            root.add(branch);
        }
        treeModel = new DefaultTreeModel(root);
        treeModel.addTreeModelListener(new MovieTreeModelListener());
        catalogTree = new JTree(treeModel);
    }

    private void createTree(MovieCatalog catalog) {
        root = new DefaultMutableTreeNode("Movies");

        for (GENRE value : enumList) {
            DefaultMutableTreeNode branch = new DefaultMutableTreeNode(value.toString());
            java.util.List<Movie> movies = catalog.getMoviesByGender(value);
            for (Movie movie : movies) {
                DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(movie.getName());
                branch.add(leaf);
            }
            root.add(branch);
        }
        treeModel = new DefaultTreeModel(root);
        treeModel.addTreeModelListener(new MovieTreeModelListener());
        catalogTree = new JTree(treeModel);
    }

    public void listenTree(){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) catalogTree.getLastSelectedPathComponent();

        Movie selectedMovie = new Movie();

        Object selectedObject = node.getUserObject();
        for (Movie movie : mainFrame.getController().getModel().getColection()) {
            if (movie.getName().equals(selectedObject.toString())) {
                selectedMovie = movie;
                System.out.println("Found.");
            }
        }
        if (selectedMovie.getName() != null) {
                mainFrame.addDetails(selectedMovie);

        } else {
            TablePanel table=new TablePanel(GENRE.valueOf(selectedObject.toString()),mainFrame);
            mainFrame.addTable(table);
        }
    }

    public JTree getCatalogTree() {
        return catalogTree;
    }

    public void setCatalogTree(JTree catalogTree) {
        this.catalogTree = catalogTree;
    }
}
