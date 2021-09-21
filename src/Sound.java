import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    Clip clip;
    AudioInputStream audioStream;
    File soundFile;
    String path;

    public Sound(String musicFileName){
        path = new File("").getAbsolutePath()+"\\src\\Sounds\\"+musicFileName+".wav";
        soundFile = new File(path);
            try {
                audioStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        //////////////////////////////////////////////////////
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        //////////////////////////////////////////////////////
            try {
                clip.open(audioStream);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void Start(){
        clip.start();
    }
    public void StartLoop(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
