package moviecatalog.view.button;

import moviecatalog.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Cornelius on 02.04.2015.
 */
public class Toolbar extends JPanel implements ActionListener {

    private JButton addButton;
    private JButton startButton;
    private ButtonListener buttonListener;
    private UserNameListener userNameListener;
    private AddListener addListener = null;
    private MainFrame mainFrame;

    public Toolbar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initialize();
        setAspect();
        setMnemonics();
    }

    public void setAspect() {
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(new Color(152, 153, 175));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        addButton.addActionListener(this);
        startButton.addActionListener(this);

        add(startButton, FlowLayout.LEFT);
        add(addButton, FlowLayout.CENTER);
    }

    public void initialize() {
        addButton = new JButton("Add movie");
        startButton = new JButton("Start!");
    }

    public void setMnemonics() {
        addButton.setMnemonic(KeyEvent.VK_A);
        startButton.setMnemonic(KeyEvent.VK_S);
    }

    public void setButtonListener(ButtonListener listener) {

        this.buttonListener = listener;
    }

    public void setAddListener(AddListener listener){
        this.addListener=listener;
    }

    public void setUserNameListener(UserNameListener listener) {
        this.userNameListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();//reference

        if (buttonListener != null) {
            if (buttonClicked == addButton) {
                buttonListener.textEmmited("Adding a movie...!\n");
                addListener.addMovie();
            } else if (buttonClicked == startButton) {
                buttonListener.textEmmited("Retrieving moovie catalog...!\n");
                JOptionPane jOptionPane = new JOptionPane();
                String userName = jOptionPane.showInputDialog(Toolbar.this,
                        "Enter your username",
                        "Movie Catalog - User Name",
                        JOptionPane.OK_CANCEL_OPTION | JOptionPane.INFORMATION_MESSAGE);
                if (userName != null) {
                    userNameListener.userNameEmmited(userName);
                } else buttonListener.textEmmited("Canceled.\n");
            }

        }
    }

}
