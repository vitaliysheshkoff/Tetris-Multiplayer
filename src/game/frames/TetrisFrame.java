package game.frames;
import game.frames.listener.FrameWindowListener;
import game.start.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class TetrisFrame extends JFrame {

    /*String FONT_PATH = "/res/fonts/minecraft-title-cyrillic-regular3.ttf";
    Font customFont;*/

    public TetrisFrame() {
        //  setTitle("Tetris");


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
                revalidateAll(getContentPane());

            }
        });


        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void revalidateAll(Container parent) {
        System.out.println("revalidate");

        Main.FONT = Main.FONT.deriveFont(getWidth() / 90f);

        for (Component c : parent.getComponents()) {

            if (c instanceof JLabel || c instanceof JButton) {

                c.setFont(Main.FONT);
            }

            c.revalidate();

            if (c instanceof Container)
                revalidateAll((Container) c);
        }
    }
}