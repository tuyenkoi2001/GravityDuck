import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckCollision implements ActionListener {
    ArrayList<JLabel> item = new ArrayList<JLabel>();
    Duck duck;
    Egg egg;
    Timer timer = new Timer(50,this);

    public CheckCollision(Duck duck){
        this.duck = duck;
    }

    public void addEnemies(JLabel item){
        this.item.add(item);
    }

    public void AddEgg(Egg egg){
        this.egg = egg;
    }

    public void CheckEnemies(){
        int numberOfItem = item.size();
        for(int i=0;i<numberOfItem;i++)
        {
            if(item.get(i).getBounds().intersects(duck.getBounds()))
                this.duck.BOOM();
        }
    }

    public void CheckSwitch(Switch s){
        int Horizontal = duck.getHorizontal();
        int Vertical = duck.getVertical();
        Rectangle Switch = new Rectangle(s.getX()+15,s.getY()+15,1,1);
        if(duck.getBounds().intersects(Switch) && duck.isCanSwitch()){
            if(Vertical < 0) {duck.ChangeDirection(1,0);}
            else if(Vertical > 0) {duck.ChangeDirection(-1,0);}
            else if(Horizontal > 0) {duck.ChangeDirection(0,1);}
            else if(Horizontal < 0) {duck.ChangeDirection(0,-1);}
            duck.SwitchBanned();
        }

    }

    public boolean CheckEgg(){
        if(egg.getBounds().intersects(duck.getBounds()))
        {
            new Sound("Pick_Egg").Start();
            egg.Fire();
            duck.DuckDisappear();
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(egg.getBounds().intersects(duck.getBounds()))
        {
            new Sound("Pick_Egg").Start();
            timer.stop();
            egg.Fire();
            duck.DuckDisappear();
        }
    }
}
