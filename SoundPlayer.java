import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    private Map<SOUNDS, Clip> soundMap = new HashMap<>();

    public SoundPlayer(String config_file) throws IOException, UnsupportedAudioFileException, LineUnavailableException  {
        BufferedReader br = new BufferedReader(new FileReader(config_file));
        while(br.ready()){
            String[] data = br.readLine().split("\\|");
            Clip c = AudioSystem.getClip();
            soundMap.put(SOUNDS.valueOf(data[0]), c);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(data[1]));
            c.open(audioStream);
        }
    }

    public static enum SOUNDS {
        BONK, ITEM_PICKUP
    }

    public void play(SOUNDS s) {
        Clip c = soundMap.get(s);

        //Restart the clip
        if(c.isRunning()) {
            c.stop();
        }

        //Queue it up
        c.setFramePosition(0);

        //Hit it!
        c.start();

    }


}
