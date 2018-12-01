import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Sound {

    protected AudioStream audios;// = new AudioStream(music);

    public void Sound(String filepath) {

        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public void Close()
    {
        try {
            audios.close();
        }
        catch (Exception e)
        {

        }
    }
}

