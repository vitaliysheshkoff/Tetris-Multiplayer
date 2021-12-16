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


    public TetrisFrame() {

        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //setMinimumSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500,500));
        setPreferredSize(new Dimension((int) (Main.width / 2), (int) (Main.height * 3 / 4)));
        FrameWindowListener frameWindowListener = new FrameWindowListener();
        addWindowListener(frameWindowListener);
        add(Main.menuPanel);
        pack();

        this.addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent e) {

                // This is only called when the user releases the mouse button.

              /*  if (Main.tetrisFrame.getContentPane().getComponent(0) == Main.tetrisPanel) {
                    Main.tetrisFrame.setMinimumSize(new Dimension((int) (Main.width / 1.5), (int) (Main.height * 0.6)));
                }*/
                //else
                 //   Main.tetrisFrame.setMinimumSize(new Dimension((int) (Main.width / 3), (int) (Main.height / 1.9)));


                revalidateAll(getContentPane());

            }
        });


        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void revalidateAll(Container parent) {
        System.out.println("revalidate");

        if(getWidth() < getHeight())
        Main.FONT = Main.FONT.deriveFont(getWidth() / 50f);
        else
            Main.FONT = Main.FONT.deriveFont(getHeight() / 50f);

        for (Component c : parent.getComponents()) {

            if (c instanceof JLabel || c instanceof JButton || c instanceof JCheckBox) {

                c.setFont(Main.FONT);
            }

            c.revalidate();

            if (c instanceof Container)
                revalidateAll((Container) c);
        }
    }
}