import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Menu extends JPanel implements ActionListener{
    SpiralSheet bg = new SpiralSheet().getSpiralSheetOf("Menu").SpiralName("BG.png");
    SpiralSheet menu = new SpiralSheet().getSpiralSheetOf("Menu").SpiralName("Main.png");
    BufferedImage button = new SpiralSheet().getSpiralSheetOf("Menu").SpiralName("Btn_Start_Game.png").getImage();
    JButton newGame = new JButton();
    boolean playew;

    Menu(){

        this.setPreferredSize(new Dimension(600,450));
        this.setLayout(null);
        this.setBackground(Color.red);
        newGame.setBounds(145,259,312,65);
        newGame.addActionListener(this);
        newGame.setFocusable(false);
        newGame.setIcon(new ImageIcon(button.getScaledInstance(310,64,4)));
        this.add(newGame);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg.getImage(),0,0,600,450,null);
        g.drawImage(menu.getImage(),0,0,600,450,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playew = true;
    }

    public boolean Play(){
        return playew;
    }
}
