package moviecatalog.view;

import moviecatalog.controller.MovieCatalogController;
import moviecatalog.model.Movie;
import moviecatalog.view.button.ButtonListener;
import moviecatalog.view.button.Toolbar;
import moviecatalog.view.button.UserNameListener;
import moviecatalog.view.button.AddListener;
import moviecatalog.view.menu.AppMenu;
import moviecatalog.view.panel.AddMoviePanel;
import moviecatalog.view.panel.TablePanel;
import moviecatalog.view.panel.TextPanel;
import moviecatalog.view.tree.CatalogPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cornelius on 02.04.2015.
 */
public class MainFrame extends JFrame {

    private static MainFrame ourInstance;

    private MovieCatalogController controller;
    private TextPanel textPanel;
    private Toolbar toolbar;

    private JPanel inputPanel;
    private CatalogPanel catalogPanel;
    private JSplitPane splitPane;

    public MainFrame(MovieCatalogController controller) {
        super("Movie Catalog 2015 by Cornelius");
        this.controller = controller;
        initialize();
        setJMenuBar(new AppMenu(this).getMenuBar());
        events();
        setAspect();
    }

    public void initialize() {

        ourInstance = this;
        toolbar = new Toolbar(this);
        textPanel = new TextPanel();
        catalogPanel = new CatalogPanel(this);
        inputPanel = new JPanel();
    }

    public void setAspect() {
        setBackground(new Color(132, 203, 146));
        setLayout(new BorderLayout());

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, catalogPanel, inputPanel);
        add(toolbar, BorderLayout.PAGE_START);
        add(textPanel, BorderLayout.PAGE_END);
        add(splitPane);

        textPanel.setMaximumSize(new Dimension(100, 100));
        setMinimumSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void events() {
        toolbar.setButtonListener(new ButtonListener() {
            @Override
            public void textEmmited(String text) {
                textPanel.appendText(text);
            }
        });

        toolbar.setAddListener(new AddListener() {
            @Override
            public void addMovie() {
                ourInstance.addMovie();
            }
        });

        toolbar.setUserNameListener(new UserNameListener() {
            @Override
            public void userNameEmmited(String userName) {
                ourInstance.controller.startCatalog(userName);
            }
        });

    }

    public MovieCatalogController getController() {
        return controller;
    }
    public void showCatalog() {
        this.textPanel.setMinimumSize(new Dimension(500, 500));
        textPanel.appendText(controller.getModel().toString());

    }

    public CatalogPanel getCatalogPanel() {
        return catalogPanel;
    }

    public void addTable(TablePanel table) {
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(table), inputPanel);
        splitPane.add(splitPane2, JSplitPane.BOTTOM);
    }

    public void addDetails(Movie movie) {

        JButton removeButton = new JButton("Remove Selected Movie");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                removeSelectedMovie();
            }
        });
        JButton playButton = new JButton("Play Movie");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                playMovie();
            }
        });

        JButton editButton = new JButton("Edit Selected Movie");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                 editMovie();
            }
        });


        JPanel inputPanel2 = new JPanel();
        inputPanel2.add(playButton);
        inputPanel2.add(editButton);
        inputPanel2.add(removeButton);
        JTextArea textArea = new JTextArea(movie.toString());
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(textArea), inputPanel2);
        splitPane.add(splitPane2, JSplitPane.BOTTOM);

    }

    private void playMovie() {
        DefaultMutableTreeNode selectedNode = getSelectedNode();
        if (selectedNode != null) {
            this.controller.getModel().playMovie(selectedNode);
        }
    }

    private void addMovie() {
        DefaultMutableTreeNode parent = getSelectedNode();
        if (parent == null) {
            JOptionPane.showMessageDialog(this, "No category selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new AddMoviePanel(parent, this.controller.getModel(), (DefaultTreeModel)catalogPanel.getCatalogTree().getModel());
    }

    private void editMovie(){
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) getSelectedNode().getRoot();
        removeSelectedMovie();
        new AddMoviePanel(parent, controller.getModel(), (DefaultTreeModel)catalogPanel.getCatalogTree().getModel());
    }

    private void removeSelectedMovie() {
        DefaultMutableTreeNode selectedNode = getSelectedNode();
        if (selectedNode != null) {
            ((DefaultTreeModel)catalogPanel.getCatalogTree().getModel()).removeNodeFromParent(selectedNode);
            controller.getModel().removeMovie(selectedNode.toString());
           updateTreeOnRemove(selectedNode.toString());
        }
    }

    private void updateTreeOnRemove(String name) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)catalogPanel.getCatalogTree().getModel().getRoot();
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            DefaultMutableTreeNode parent2 = (DefaultMutableTreeNode) parent.getChildAt(i);
            for (int j = 0; j < parent2.getChildCount(); j++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) parent2.getChildAt(j);
                if (child != null) {
                    if (name.compareTo(child.toString()) == 0)
                        ((DefaultTreeModel)catalogPanel.getCatalogTree().getModel()).removeNodeFromParent(child);
                }
            }

        }
    }

    private DefaultMutableTreeNode getSelectedNode() {
        return (DefaultMutableTreeNode) catalogPanel.getCatalogTree().getLastSelectedPathComponent();
    }

}
