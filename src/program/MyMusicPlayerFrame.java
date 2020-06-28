package program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyMusicPlayerFrame extends JFrame {

    private JLabel threadPrintTitleLabel = new JLabel("0 threads are running");
    private JLabel musicTimeLabel = new JLabel("0");
    private JList<String> threadPrintList = new JList<>();
    private MyMusicPlayer myMusicPlayer;
    private ThreadPrint threadPrint;

    public MyMusicPlayerFrame(MyMusicPlayer myMusicPlayer) {
        this.myMusicPlayer = myMusicPlayer;
        this.setTitle("My Music Player!");
        JPanel mainPanel = new JPanel();
        JPanel musicTimePanel = new JPanel();
        JPanel musicListPanel = new JPanel();
        JPanel threadPrintPanel = new JPanel();
        musicTimePanel.setPreferredSize(new Dimension(100, 600));
        musicListPanel.setPreferredSize(new Dimension(300, 600));
        threadPrintPanel.setPreferredSize(new Dimension(300, 600));

        musicTimePanel.add(musicTimeLabel);

        JPanel buttonPanel = new JPanel();
        Button startBtn = new Button("Start");
        startBtn.addActionListener(this::onStart);
        Button stopBtn = new Button("Stop");
        stopBtn.addActionListener(this::onStop);
        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);
        JLabel musicListTitleLabel = new JLabel("Music List");
        JLabel musicListLabel = new JLabel();
        musicListPanel.add(musicListTitleLabel);
        musicListPanel.add(musicListLabel);
        JPanel threadPrintTitlePanel = new JPanel();
        JPanel threadPrintListPanel = new JPanel();
        threadPrintListPanel.add(threadPrintList);
        threadPrintTitlePanel.add(threadPrintTitleLabel);
        threadPrintPanel.add(threadPrintTitlePanel);
        threadPrintPanel.add(threadPrintListPanel);
//        threadPrintPanel.add(threadPrintLabel);

        mainPanel.add(musicTimePanel);
        mainPanel.add(musicListPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(threadPrintPanel);

        add(mainPanel);
        setPreferredSize(new Dimension(1000, 600));
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    public void setThreadPrint(ThreadPrint threadPrint) {
        this.threadPrint = threadPrint;
    }

    public JLabel getThreadPrintTitleLabel() {
        return threadPrintTitleLabel;
    }

    public JList<String> getThreadPrintList() {
        return threadPrintList;
    }

    public MyMusicPlayer getMyMusicPlayer() {
        return myMusicPlayer;
    }

    public void setMusicTimeLabel(String label) {
        this.musicTimeLabel.setText(label);
    }

    public void setThreadPrintTitleLabel(String label) {
        this.threadPrintTitleLabel.setText(label);
    }

    public void setThreadPrintList(String[] list) {
        threadPrintList.setListData(list);
    }

    public void onStart(ActionEvent e) {
        if (myMusicPlayer == null) return;
        myMusicPlayer.play();
        threadPrint.print();
    }

    public void onStop(ActionEvent e) {
        if (myMusicPlayer == null) return;
        myMusicPlayer.stop();
        threadPrint.stop();
    }
}
