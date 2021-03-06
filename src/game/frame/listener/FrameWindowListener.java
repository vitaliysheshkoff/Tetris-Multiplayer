package game.frame.listener;

import game.start.Main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

public  class FrameWindowListener implements WindowListener , WindowStateListener {

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("windowActivated()");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("windowClosed()");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Main.audioPlayer.playClick();
        if (Main.tetrisFrame.getContentPane().getComponent(0) == Main.tetrisPanel) {
            Main.tetrisPanel.tetrisPlayFieldPanel.mySuspend();
            if (!Main.tetrisPanel.tetrisPlayFieldPanel.gameOver) {
                Main.tetrisPanel.tetrisPlayFieldPanel.myInterrupt();
                Main.tetrisPanel.saveGame();
                System.out.println("windowClosing() and tetris panel saved");
            }
        } else if (Main.tetrisFrame.getContentPane().getComponent(0) == Main.optionPanel.scrollPane) {
            Main.optionPanel.saveOptions();
            System.out.println("windowClosing() and option panel saved");
        }

        Main.saveApplicationSize();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("windowDeactivated()");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("windowDeiconified()");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("windowIconified()");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened()");
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        System.out.println("windowStateChanged()");
    }

}