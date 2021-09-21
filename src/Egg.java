import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Egg extends JLabel {
    BufferedImage[] spiralSheet = new SpiralSheet().getSpiralSheetOf("Game").SpiralName("Egg.png").getSetOfImage(1,5);
    BufferedImage egg = spiralSheet[0];
    BufferedImage eggImg = egg.getSubimage(0,0,egg.getWidth(),egg.getHeight()/2);
    ImageIcon eggIcon = new ImageIcon(eggImg);//.getScaledInstance(30,30,4));
    ImageIcon eggIcon2 = new ImageIcon(egg.getSubimage(0,egg.getHeight()/2,egg.getWidth(),egg.getHeight()/2));
    int xPos;
    int yPos;
    int[][] map;

    public Egg(Map map){
        this.map = map.getMap();
        setSize(30,30);
        xPos = map.getEggPos()[0];
        yPos = map.getEggPos()[1];
        int x = yPos/30;
        int y = xPos/30;
        RotatedIcon ri = new RotatedIcon(eggIcon,0);
        /*if( this.map[x][y+1] > 30) {
            ri.setDegrees((-90));
            setIcon(ri);
        }
        else if( this.map[x][y-1] > 30) {
            ri.setDegrees((90));
            setIcon(ri);
        }
        else if( this.map[x+1][y] > 30) {
            ri.setDegrees((180));
            setIcon(ri);
        }*/
        setIcon(ri);
        setLocation(xPos,yPos);
    }

    public synchronized void Fire(){
        ImageIcon[] eggIcon = new ImageIcon[5];
        for(int i=1;i<5;i++)
            eggIcon[i-1] = new ImageIcon(spiralSheet[i].getScaledInstance(30,30, Image.SCALE_SMOOTH));

        new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i=0;i<5;i++){
                    setIcon(eggIcon[i]);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
