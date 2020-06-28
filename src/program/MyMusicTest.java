package program;

import java.util.Arrays;
import java.util.List;

public class MyMusicTest {

    public static void main(String[] args) {
        String musicFile = "C:\\Users\\LeeTaeHun\\Music\\Playlists\\SheWillBeLoved_Maroon5.mp3";
        MyMusicPlayerFrame myMusicPlayerFrame = new MyMusicPlayerFrame(new MyMusicPlayer("C:\\Users\\LeeTaeHun\\Music\\Playlists\\SheWillBeLoved_Maroon5.mp3",true));
        ThreadPrint threadPrint = new ThreadPrint(myMusicPlayerFrame);

        myMusicPlayerFrame.setThreadPrint(threadPrint);
    }
}
