import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;

public class Duck extends JLabel{
    boolean canSwitch = true;
    int[][] map;
    SpiralSheet spiralSheet = new SpiralSheet().getSpiralSheetOf("Game").SpiralName("Player.png");
    BufferedImage[] duckSheet = spiralSheet.getSetOfImage(2,7);
    ImageIcon[] duckIcon = new ImageIcon[14];
    ImageIcon[][] duckMoveIcon = new ImageIcon[8][6];
    CountDownLatch latch = new CountDownLatch(1);

    int Horizontal;
    int Vertical = 1;
    int startX;
    int startY;
    int posX;
    int posY;

    public Duck(){
        this.setSize(30,30);
        Init();
        addAction();
    }

    public void Init(){
        for(int i=0;i<14;i++)
        {
            duckIcon[i] = new ImageIcon(duckSheet[i]);
            duckSheet[i] = null;
        }
        this.setIcon(duckIcon[13]);
    }

    public void addAction(){
        SpiralSheet[] setOfDuckMove = new SpiralSheet[8];
        BufferedImage[] duckMove;
        for(int i=0;i<8;i++)
            setOfDuckMove[i] = new SpiralSheet().getSpiralSheetOf("DuckMove").SpiralName("Player"+i+".png");

        for(int i=0;i<4;i++){
            duckMove = setOfDuckMove[i].getSetOfImage(1,6);
            for(int j=0;j<6;j++)
                duckMoveIcon[i][j] = new ImageIcon(duckMove[j]);
        }

        for(int i=4;i<8;i++){
            duckMove = setOfDuckMove[i].getSetOfImage(6,1);
            for(int j=0;j<6;j++)
                duckMoveIcon[i][j] = new ImageIcon(duckMove[j]);
        }

        Action upAction = new UpAction();
        Action downAction = new DownAction();
        Action leftAction = new LeftAction();
        Action rightAction = new RightAction();
        Action gravity = new ChangeDirection();

        getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        getActionMap().put("upAction", upAction);

        getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        getActionMap().put("downAction", downAction);

        getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        getActionMap().put("leftAction", leftAction);

        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        getActionMap().put("rightAction", rightAction);

        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "gravity");
        getActionMap().put("gravity", gravity);
    }

    public void putInMap(Map map){
        this.map = map.getMap();
        startX = posX = map.getDuckPos()[0];
        startY = posY = map.getDuckPos()[1];
        updateLocation();
        DuckAppear();
        Gravity();
    }

    public void updateLocation(){
        this.setLocation(posX,posY);
    }

    public synchronized void DuckAppear(){
        Thread app = new Thread(){
            @Override
            public void run() {
                setLocation(startX,startY);
                super.run();
                for(int i=6;i<14;i++){
                    setIcon(duckIcon[i]);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }latch.countDown();
            }
        };
        app.start();
    }
    public synchronized void DuckDisappear(){
     Thread app = new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i=13;i>5;i--){
                    setIcon(duckIcon[i]);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        app.start();
        try {
            app.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    static int i = 0;
    public void DuckMoveToRight(){
        int x = Math.round((float)posY/30);
        int y = posX/30;

                if(map[x][y+1] == -1 || map[x][y+1] >= 40){
                    if(Vertical == 1) {
                        setIcon(duckMoveIcon[0][i++ % 6]);
                    }
                    if(Vertical == -1)
                    {

                            setIcon(duckMoveIcon[2][i++%6]);

                    }
                    posX+=5;
                    //updateLocation();
                }
            }

    public void DuckMoveToLeft(){
        int x = Math.round((float)posY/30);
        int y = posX/30;
                if(map[x][y] == -1 || map[x][y] >= 40){
                    if(Vertical == 1)
                    {
                            setIcon(duckMoveIcon[1][i++%6]);

                        }

                    if(Vertical == -1)
                    {
                            setIcon(duckMoveIcon[3][i++%6]);

                        }
                    posX -= 5;
                }

}

    public void DuckMoveDown(){
        int x = posY/30;
        int y = Math.round((float)posX/30);
                if(map[x+1][y] == -1){
                    if(Horizontal == 1)
                    {

                            setIcon(duckMoveIcon[6][i++%6]);
                    }
                    if(Horizontal == -1)
                    {
                            setIcon(duckMoveIcon[7][i++%6]);

                        }
                    posY += 5;
                    }
    }

    public void DuckMoveUp() {
        int x = posY / 30;
        int y = Math.round((float) posX / 30);
        if (map[x][y] == -1) {
            if (Horizontal == 1) {

                setIcon(duckMoveIcon[5][i++%6]);
            }
            if (Horizontal == -1) {
                setIcon(duckMoveIcon[4][i++%6]);

            }
            posY -= 5;
        }
    }

    public void Gravity() {
        Thread gravity = new Thread() {
            @Override
            public void run() {
                super.run();
                while(true){
                    int x = Math.round((float) (posY + Vertical * 10) / 30);
                    int y = Math.round((float) (posX + Horizontal * 10) / 30);
                    if(map[x][y]==-1 || map[x][y] >= 40)
                        {
                            posX += Horizontal * 3;
                            posY += Vertical * 3;
                        }
                    else canSwitch =true;

                    updateLocation();

                    isTrapped();

                    try {
                        latch.await();
                        sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        };
        gravity.start();
}

    public class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            DuckMoveUp();
        }
    }
    public class DownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            DuckMoveDown();
        }
    }
    public class LeftAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            DuckMoveToLeft();
        }
    }
    public class RightAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            DuckMoveToRight();
        }
    }
    public class ChangeDirection extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            new Sound("Switch").Start();
            ChangeDirection(Horizontal * -1,Vertical * -1);
        }
    }

    //chuyển hướng trọng lực
    public void ChangeDirection(int x,int y){
        Horizontal = x;
        Vertical = y;
        if (Vertical > 0) setIcon(duckMoveIcon[0][2]);
        else if (Vertical < 0) setIcon(duckMoveIcon[2][2]);
        else if (Horizontal > 0) setIcon(duckMoveIcon[5][3]);
        else if (Horizontal < 0) setIcon(duckMoveIcon[4][4]);
    }

    public boolean isCanSwitch(){
        return canSwitch;
    }


    // kiểm tra va chạm với switch chỉ 1 lần duy nhất
    public void SwitchBanned(){
        canSwitch = false;
    }

    public int getHorizontal(){
        return Horizontal;
    }

    public int getVertical(){
        return Vertical;
    }

    public void BOOM(){
        BufferedImage boom = new SpiralSheet().getSpiralSheetOf("Game").SpiralName("Death.png").getImage();
        this.setIcon(new ImageIcon(boom.getScaledInstance(30,30, Image.SCALE_SMOOTH)));
        new Sound("Dead").Start();
        posX = startX;
        posY = startY;
        ChangeDirection(0,1);
        DuckAppear();
    }

    public void isTrapped(){
        int x = Math.round((float)posY/30);
        int y = Math.round((float)posX/30);System.out.println(map[x][y]);
        if(map[x][y] == 42)
            BOOM();
        if(map[x][y] == 40)
            BOOM();
        if(map[x][y] == 43)
            BOOM();
        if(map[x][y] == 41)
            BOOM();
    }

}
