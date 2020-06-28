package program;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyMusicTimePrint implements Callable<String> {
    private Thread thread;
    private float duration;
    private float current;


    @Override
    public String call() throws Exception {
        return "";
    }

    private float getDurationMp3(String filename) throws IOException, UnsupportedAudioFileException {
        Header h= null;
        FileInputStream file = null;
        try {
            file = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        Bitstream bitstream = new Bitstream(file);
        try {
            h = bitstream.readFrame();

        } catch (BitstreamException ex) {
            System.out.println(ex.getMessage());
        }
        int size = h.calculate_framesize();
        float ms_per_frame = h.ms_per_frame();
        int maxSize = h.max_number_of_frames(10000);
        float t = h.total_ms(size);
        long tn = 0;
        try {
            tn = file.getChannel().size();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println("Chanel: " + file.getChannel().size());
        int min = h.min_number_of_frames(500);
        return h.total_ms((int) tn)/1000;
    }

    public void print() {
        FutureTask<String> futureTask = new FutureTask<>(this);
        this.thread = new Thread(futureTask);
        this.thread.setName("MusicTime Print Task");
        this.thread.start();
    }



}
