package game.frames;
import game.frames.listener.FrameWindowListener;
import game.start.Main;
import javax.swing.*;
import java.awt.*;

public class TetrisFrame extends JFrame {

    public TetrisFrame() {
        setTitle("Tetris");
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FrameWindowListener frameWindowListener = new FrameWindowListener();
        addWindowListener(frameWindowListener);
        add(Main.menuPanel);
        pack();

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}