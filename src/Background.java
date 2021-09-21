import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends JPanel {
    Map map;
    SpiralSheet background = new SpiralSheet();
    BufferedImage BackgroundIMG;

    public Background(int level){
        setPreferredSize(new Dimension(600,450));
        setLayout(null);
        map = new Map(level);
        background.getSpiralSheetOf("Game").SpiralName("BG.png");
        BackgroundIMG = background.getImage();

    }


    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(BackgroundIMG,0,0,600,450,this);
    }
}
