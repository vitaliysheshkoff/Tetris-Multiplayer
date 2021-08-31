package game.frames.listener;

import game.start.Main;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public  class FrameWindowListener implements WindowListener {

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
        } else if (Main.tetrisFrame.getContentPane().getComponent(0) == Main.optionPanel) {
            Main.optionPanel.saveOptions();
            System.out.println("windowClosing() and option panel saved");
        }
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened()");
    }
}