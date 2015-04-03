package moviecatalog.view.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cornelius on 02.04.2015.
 */
public class TextPanel extends JPanel {

    private JTextArea textArea;

    public TextPanel(){
        textArea=new JTextArea();

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(250,200));

        add(new JScrollPane(textArea),BorderLayout.CENTER);
    }

    public void appendText(String text){
        textArea.append(text);
    }
}
