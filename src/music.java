import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class music extends JPanel implements ActionListener {

    int Horizontal;
    int Vertical = 1;

    int x;
    int y;

    Timer timer = new Timer(5,this);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        frame.setSize(400,600);

        music p1 = new music();
        JLabel p2 = new JLabel();
        p2.setBounds(200,300,100,100);
        p2.setOpaque(true);
        p2.setBackground(Color.blue);

        JPanel p3 = new JPanel();
        p3.setBounds(200,300,400,400);
        p3.setBackground(Color.yellow);
        p3.setLayout(null);
        p3.add(p2);

        frame.add(p1);
        frame.add(p3);
        while(true){

            p1.CollisionDetect(p2);
            frame.setVisible(true);

        }

    }

    public music(){
        setBounds(0,0,50,10);
        setBackground(Color.red);
        addKeyListener(new al());
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(x+"/"+y);
        //if(x+Horizontal*5>=0 && x+Horizontal*5<=400)
          //  x += Horizontal*5;
        //if(y+Vertical*5>=0 && y+Vertical*5<=600)
        //    y += Vertical*5;
        //if((x>=0&&x<=400) && y<=600 && y>=0)
        setLocation(x,y);
    }

    public void CollisionDetect(JLabel p){
        if(p.getBounds().intersects(this.getBounds())){
            if(this.Horizontal > 0){
                //this.Horizontal = -1;
                this.Vertical = 1;
            }
            else if(this.Horizontal < 0){
                this.Horizontal = 1;
                //this.Vertical = -1;
            }
            else if(this.Vertical > 0){
                //this.Horizontal = 1;
                this.Horizontal = -1;
            }
            else if(this.Vertical < 0){
                this.Vertical = 1;
                this.Horizontal = 1;
            }
        }
    }

    public class al implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP){
                if(y>=0)
                y-=5;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(y<=600)
                y+=5;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if(x>=0)
                x-=5;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if(x<=400)
                x+=5;
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                Horizontal *= -1;
                Vertical *= -1;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
