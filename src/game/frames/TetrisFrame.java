package game.frames;
import game.frames.listener.FrameWindowListener;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TetrisFrame extends JFrame {

    public TetrisFrame() {
        setTitle("Tetris");
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension((int) (Main.width / 2), (int) (Main.height * 3 / 4)));
        setMinimumSize(new Dimension(500, 500));
        FrameWindowListener frameWindowListener = new FrameWindowListener();
        addWindowListener(frameWindowListener);
        add(Main.menuPanel);
        pack();


        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
               // System.out.println("componentResized");
                revalidateAll(getContentPane());
            }
        });


        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void revalidateAll(Container parent) {
        System.out.println("revalidate");
        for (Component c : parent.getComponents()) {
            c.revalidate();

            if (c instanceof Container)
                revalidateAll((Container) c);
        }
    }
}