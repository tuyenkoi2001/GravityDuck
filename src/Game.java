import javax.swing.*;

public class Game extends JFrame {
    Duck duck;
    InitMap map;
    Egg egg;
    CheckCollision check;
    Block[] b;
    Switch[] s;
    Sound bg = new Sound("Background");
    boolean hasASwitch = false;
    boolean hasABlock = false;
    boolean completed = false;


    public Game(){
        this.setTitle("Gravity Duck");
        this.setSize(600,480);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        bg.StartLoop();
    }

    public void setLevel(int level){
        duck = new Duck();
        check = new CheckCollision(duck);
        map = new InitMap(level);
        egg = new Egg(map.getMap());

        check.AddEgg(egg);

        //kiểm tra xem map có switch hay không để nhét vào map
        thisMaphasSwitch();
        thisMapHasBlock();

        duck.putInMap(map.getMap());
        map.add(egg);
        map.add(duck);
        this.add(map);
        this.setVisible(true);
        CheckCollision();
        this.remove(map);
    }

    public void thisMaphasSwitch(){
        if(map.getMap().hasASwitch()){
            int SwitchPosX;
            int SwitchPosY;
            hasASwitch = true;
            int[] a = map.getMap().getSwitchPos();
            int size = a.length;
            s = new Switch[size/2];
            int k=0;
            for(int i=0;i<size;i+=2){
                SwitchPosX = a[i];
                SwitchPosY = a[i+1];
                s[k] = new Switch(SwitchPosX,SwitchPosY);
                map.add(s[k++]);
            }
        }
    }

    public void thisMapHasBlock(){
        if(map.getMap().hasABlock()){
            int BlockPosX;
            int BlockPosY;
            hasABlock = true;
            int[] a = map.getMap().getBlockPos();
            int size = a.length;
            b = new Block[size/2];
            int k=0;
            for(int i=0;i<size;i+=2){
                BlockPosX = a[i];
                BlockPosY = a[i+1];
                b[k] = new Block(BlockPosX,BlockPosY);
                b[k].addMap(map.getMap());
                check.addEnemies(b[k]);
                map.add(b[k++]);
            }
        }
    }


    public void CheckCollision(){
        while(true) {
            if (hasASwitch)
            {
                int size = s.length;
                for(int i=0;i<size;i++)
                    check.CheckSwitch(s[i]);
            }
            if(hasABlock){
                check.CheckEnemies();
            }
            if (completed = check.CheckEgg()){
                break;
            }
            this.setVisible(true);
        }
    }
}
