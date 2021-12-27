package game.frames;
import game.frames.listener.FrameWindowListener;
import game.start.Main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TetrisFrame extends JFrame {

    public TetrisFrame() {

        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 700));
        setPreferredSize(new Dimension((int) (Main.width / 2), (int) (Main.height * 3 / 4)));
        FrameWindowListener frameWindowListener = new FrameWindowListener();
        addWindowListener(frameWindowListener);
        add(Main.menuPanel);
        pack();

        this.addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent e) {
                revalidateAll(getContentPane());
            }
        });

        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void revalidateAll(Container parent) {
        System.out.println("revalidate");

        if (getWidth() < getHeight())
            Main.FONT = Main.FONT.deriveFont(getWidth() / 50f);
        else
            Main.FONT = Main.FONT.deriveFont(getHeight() / 50f);

        for (Component c : parent.getComponents()) {

         //   if (c instanceof JLabel || c instanceof JButton || c instanceof JCheckBox || c instanceof JTextField ) {

                c.setFont(Main.FONT);
       //     }

            c.revalidate();

            if (c instanceof Container)
                revalidateAll((Container) c);
        }
    }
}