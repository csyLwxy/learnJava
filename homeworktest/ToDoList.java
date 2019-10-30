import javax.swing.JFrame;
import java.awt.*;

public class ToDoList extends JFrame{
    private static final int WINDOW_W = 400;
    private static final int WINDOW_H = 600;

    public ToDoList() {
         // get screen dimension

         Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension screenSize = kit.getScreenSize();
         int screenHeight = screenSize.height;
         int screenWidth = screenSize.width;
 
         // set frame width, height and let platform pick screen location
 
         setSize(WINDOW_W, WINDOW_H);
         setLocation((screenWidth - WINDOW_W)/2, (screenHeight - WINDOW_H)/2);
         //setLocationByPlatform(true);
 
         // set frame icon 
 
         // Image img = new ImageIcon("./sizedFrame/java.jpg").getImage();
         // setIconImage(img);
    }
}