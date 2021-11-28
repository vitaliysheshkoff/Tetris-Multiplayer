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

        setPreferredSize(new Dimension((int) (Main.width  * 3/4), (int) (Main.height * 3/4)));
        setMinimumSize(new Dimension(500,500));
        FrameWindowListener frameWindowListener = new FrameWindowListener();
        addWindowListener(frameWindowListener);
        add(Main.menuPanel);
        pack();



        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}