package program;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class ThreadPrint implements Callable<String> {
    private final String DEFAULT_THREAD_PRINT_TITLE = "threads are running";
    private Thread thread;
    private MyMusicPlayerFrame myMusicPlayerFrame;

    public ThreadPrint(MyMusicPlayerFrame myMusicPlayerFrame) {
        this.myMusicPlayerFrame = myMusicPlayerFrame;
    }

    public String call() {
        try {
            StringBuffer sb = new StringBuffer();
            do {
                Set<Thread> threads = Thread.getAllStackTraces().keySet();
                int threadSize = threads.size();
                sb.append(".");
                if (sb.length() > 5) {
                    sb.setLength(0);
                }
                String[] threadNames = threads.stream().map(Thread::getName).toArray(String[]::new);
                if (myMusicPlayerFrame != null) {
                    String threadPrintTitle = threadSize + DEFAULT_THREAD_PRINT_TITLE + sb.toString();
                    myMusicPlayerFrame.setThreadPrintTitleLabel(threadPrintTitle);
                    myMusicPlayerFrame.setThreadPrintList(threadNames);
                }

//                System.out.println(Arrays.toString(threadNames));
                Thread.sleep(500);

            } while (MyMusicPlayer.isPlay.get());
        } catch (Exception ioe) {
            //TODO
            ioe.getStackTrace();
        }
        return "";
    }

    public void print() {
        FutureTask<String> futureTask = new FutureTask<>(this);
        this.thread = new Thread(futureTask);
        this.thread.setName("Thread Print Task");
        this.thread.start();
    }

    public void stop() {
        if (this.thread != null) {
            myMusicPlayerFrame.setThreadPrintTitleLabel("0 " + DEFAULT_THREAD_PRINT_TITLE);
            myMusicPlayerFrame.setThreadPrintList(new String[]{});
            this.thread.interrupt();
        } else {
            System.out.println("Music player is not running yet");
        }
    }
}
