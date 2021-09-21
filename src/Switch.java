import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Switch extends JLabel{
    int xPos;
    int yPos;
    SpiralSheet ss = new SpiralSheet().getSpiralSheetOf("Game").SpiralName("Switch.png");
    BufferedImage Switch = ss.getImage();
    ImageIcon switchIcon1;


    public Switch(int x,int y){
        xPos = x;
        yPos = y;
        this.setSize(40,40);
        switchIcon1 = new ImageIcon(Switch.getScaledInstance(40,40,4));
        RotatedIcon();
        setLocation(x,y);
    }

    public void RotatedIcon(){
        RotatedIcon ri = new RotatedIcon(switchIcon1,0);
        Thread t = new Thread(){
            int i=0;
            @Override
            public void run() {
                super.run();
                while(true){
                ri.setDegrees(i+=90);
                setIcon(ri);
                repaint();
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
}
