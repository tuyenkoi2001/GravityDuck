import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class InitMap extends JPanel {
    Map mapLevel;
    SpiralSheet background = new SpiralSheet();
    BufferedImage BackgroundIMG;
    SpiralSheet SpiralCollect = new SpiralSheet().getSpiralSheetOf("Game").SpiralName("Tiles.png");
    BufferedImage[] brick = SpiralCollect.getSetOfImage(4,11);
    int width = 30;
    int heigh = 30;

    public InitMap(int level){
        setPreferredSize(new Dimension(600,450));
        setLayout(null);
        background.getSpiralSheetOf("Game").SpiralName("BG.png");
        BackgroundIMG = background.getImage();
        mapLevel = new Map(level);
    }


    public Map getMap(){
        return mapLevel;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BackgroundIMG,0,0,600,450,this);
        int[][] map = mapLevel.getMap();
        for(int i=0;i<15;i++)
            for(int j=0;j<20;j++)
            {
                int pos = map[i][j];
                if(pos >= 0)
                g.drawImage(brick[pos],j*this.width,i*this.heigh,width,heigh,null);
            }
    }
}
