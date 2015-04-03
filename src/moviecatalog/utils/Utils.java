package moviecatalog.utils;

import javax.swing.*;
import java.net.URL;

/**
 * Created by Cornelius on 03.04.2015.
 */
public class Utils {

    public static ImageIcon createIcon(String path){
        URL url=System.class.getResource(path);
        if(url==null){
            System.err.println("Icon not found");
        }

        ImageIcon icon=new ImageIcon(url);
        return icon;
    }
}
