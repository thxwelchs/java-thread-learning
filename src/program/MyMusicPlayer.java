package program;

import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyMusicPlayer implements Callable<String> {
    private Thread thread;
    private String fileLocation;
    public static AtomicBoolean isPlay;
    private Player player;
    private int musicTime;
    private int playingTime;

    public MyMusicPlayer(String fileLocation, boolean isPlay) {
        this.fileLocation = fileLocation;
        MyMusicPlayer.isPlay = new AtomicBoolean(isPlay);
    }

    public Player getPlayer() {
        return player;
    }

    public String call() {
        try {

            do {
                File file = new File(fileLocation);
                FileInputStream buff = new FileInputStream(file);
//                getDurationMp3(file);
//                AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(file);
//                Map<String, Object> properties = baseFileFormat.properties();
//                Long duration = (Long) properties.get("duration");
                player = new Player(buff);
//                System.out.println(player.getPosition());
//                getDurationMp3(fileLocation);
                player.play();

                Thread.sleep(500);

            } while (isPlay.get());
        } catch (Exception ioe) {
            // TODO error handling
            System.out.println(ioe.getMessage());
        }

        return fileLocation;
    }



    public void play() {
        FutureTask<String> futureTask = new FutureTask<>(this);
        this.thread = new Thread(futureTask);
        this.thread.setName("Music Play Task");
        this.thread.start();
    }

    public void stop(){
        isPlay.set(false);
        if(player != null && this.thread != null) {
            player.close();
            this.thread.interrupt();
        } else {
            System.out.println("Music player is not running yet");
        }
    }
}

