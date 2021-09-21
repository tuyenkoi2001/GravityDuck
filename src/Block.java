import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Block extends JLabel implements ActionListener {
    Timer timer = new Timer(5,this);
    int xPos;
    int yPos;
    int[][] map;
    SpiralSheet ss = new SpiralSheet().getSpiralSheetOf("Enemies").SpiralName("Block.png");
    BufferedImage block = ss.getImage();
    ImageIcon blockIcon1;
    int horizon;
    int vertical;

    public Block(int x,int y){
        xPos = x;
        yPos = y;
        this.setSize(30,30);
        blockIcon1 = new ImageIcon(block.getScaledInstance(30,30,4));
        setIcon(blockIcon1);
        setLocation(x,y);
    }

    public void addMap(Map map){
        this.map = map.getMap();
        int y = xPos/30;
        int x = yPos/30;
        timer.start();

        if(this.map[x][y+1] == -1){
            vertical = -1;
            moveVertical();
        }
        else {
            horizon = -1;
            moveHorizontal();
        }
    }

    public void moveVertical(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    if(vertical == 1) moveDown();
                    else moveUp();
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void moveUp(){
        int y = xPos/30;
        int x = yPos/30;
        if(map[x][y] == -1){
            yPos -= 1;
        }
        else vertical = 1;
    }

    public void moveDown(){
        int y = xPos/30;
        int x = yPos/30;
        if(map[x+1][y] == -1){
            yPos += 1;
        }
        else vertical = -1;
    }

    public void moveHorizontal(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    if(horizon == 1) moveRight();
                    else moveLeft();
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void moveLeft(){
        int y = xPos/30;
        int x = yPos/30;
        if(map[x][y] == -1){
            xPos -= 1;
        }
        else horizon = 1;
    }

    public void moveRight(){
        int y = xPos/30;
        int x = yPos/30;
        if(map[x][y+1] == -1){
            xPos += 1;
        }
        else horizon = -1;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        setLocation(xPos,yPos);
    }
}
