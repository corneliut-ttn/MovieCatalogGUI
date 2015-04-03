package moviecatalog.view.menu;

import moviecatalog.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Cornelius on 03.04.2015.
 */
public class AppMenu {

    private MainFrame mainFrame;
    private JMenuBar menuBar;

    public AppMenu(MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        menuBar = new JMenuBar();
        setMenus();

    }

    private void setMenus() {

        menuBar.add(getFileMenu());
        menuBar.add(getEditMenu());
        menuBar.add(getWindowMenu());
    }

    private JMenu getFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data ...");
        JMenuItem importDataItem = new JMenuItem("Import Data ...");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem populate = new JMenuItem("Populate Movie Catalog");

        fileMenu.add(newItem);
        fileMenu.add(populate);
        fileMenu.addSeparator();
        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to exit?",
                        "Movie Catalog - Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    mainFrame.getController().exportXML();
                    System.exit(0);
                }
            }
        });
        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getController().importXML();
            }
        });
        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getController().exportXML();
            }
        });
        populate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getController().populate();
            }
        });
        fileMenu.setMnemonic(KeyEvent.VK_F);

        return fileMenu;

    }

    private JMenu getEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        JMenuItem editMovieItem = new JMenuItem("Edit movie");
        JMenuItem editDirectorItem = new JMenuItem("Edit Director");
        JMenuItem editActorItem = new JMenuItem("Edit Actor");

        editMenu.add(editMovieItem);
        editMenu.add(editDirectorItem);
        editMenu.add(editActorItem);

        editMenu.setMnemonic(KeyEvent.VK_E);
        return editMenu;
    }

    private JMenu getWindowMenu() {

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show view");
        JMenuItem showMovieItem = new JMenuItem("Movie");
        JMenuItem showDirectorItem = new JMenuItem("Director");
        JMenuItem showActorItem = new JMenuItem("Actor");
        JMenuItem showCatalog = new JCheckBoxMenuItem("Show tree");
        showCatalog.setSelected(true);
        showMenu.add(showMovieItem);
        showMenu.add(showDirectorItem);
        showMenu.add(showActorItem);

        windowMenu.add(showMenu);
        windowMenu.add(showCatalog);

        showCatalog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                mainFrame.getCatalogPanel().setVisible(menuItem.isSelected());
            }
        });

        windowMenu.setMnemonic(KeyEvent.VK_W);
        return windowMenu;
    }


    public JMenuBar getMenuBar() {
        return menuBar;
    }

}
