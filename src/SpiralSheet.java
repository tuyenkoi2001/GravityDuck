import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpiralSheet {
    String path = new File("").getAbsolutePath();
    BufferedImage SheetOfImage;
    int width;
    int heigh;

    public SpiralSheet(){};

    public SpiralSheet(String path){
        this.path = path;
        try {
            this.SheetOfImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SpiralSheet getSpiralSheetOf(String type){
        path += "\\src\\Images\\" + type + "\\";
        return this;
    }

    public SpiralSheet SpiralName(String name){
        path += name;
        try {
            this.SheetOfImage = ImageIO.read(new File(path));
            this.width = SheetOfImage.getWidth();
            this.heigh = SheetOfImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public BufferedImage cropImage(int x,int y,int width,int heigh){
        BufferedImage cropedImage = SheetOfImage.getSubimage(x,y,width,heigh);
        return cropedImage;
    }

    public BufferedImage getImage(){
        return SheetOfImage;
    }

    public String getPath(){
        return path;
    }

    public BufferedImage[] getSetOfImage(int row, int col){
        BufferedImage[] SetOfImage = new BufferedImage[row*col];
        int width = this.width/col;
        int heigh = this.heigh/row;
        int k=0;

        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
            {
                SetOfImage[k++] = cropImage(width * j, heigh * i,width,heigh);
            }
        return SetOfImage;
    }
}
