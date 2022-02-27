package game.frame;
import game.frame.listener.FrameWindowListener;
import game.start.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TetrisFrame extends JFrame {

    public TetrisFrame() {

        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 700));

        setTitle("TETRIS");

        BufferedImage img = null;
        try {
            img =  ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/icon/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(img != null)
        setIconImage(img);

        if (Main.applicationWidth >= Main.monitorWidth && Main.applicationHeight >= Main.monitorHeight) {
            setPreferredSize(new Dimension((int) Main.applicationWidth / 2, (int) Main.applicationHeight * 3 / 4));
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        } else
            setPreferredSize(new Dimension((int) Main.applicationWidth, (int) Main.applicationHeight));

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

        if (getWidth() < getHeight())
            Main.FONT = Main.FONT.deriveFont(getWidth() / 45f);
        else
            Main.FONT = Main.FONT.deriveFont(getHeight() / 45f);

        for (Component c : parent.getComponents()) {

              if (c instanceof JLabel ) {

                  if (((JLabel) c).getText().equals("TETRIS")) {
                      //   ((JLabel) c).setFont(Main.TITLE_FONT);
                      continue;
                  }

                  // need "==", ".equals" do not work
                  if (c.getName() == ("telegram nickname") || (c.getName() == ("telegram opponent nickname"))){

                      if (getWidth() < getHeight())
                          c.setFont(new Font("Verdana ", Font.PLAIN, (int) (getWidth() / 60F)));
                      else
                          c.setFont(new Font("Verdana ", Font.PLAIN, (int) (getHeight() / 60F)));

                      continue;
                  }
              }

              if(c instanceof JComboBox){

                  // need "==", ".equals" do not work
                  if(c.getName() == "users"){
                      if (getWidth() < getHeight())
                         c.setFont(new Font("Verdana ", Font.PLAIN, (int) (getWidth() / 45f)));
                      else
                          c.setFont(new Font("Verdana ", Font.PLAIN, (int) (getHeight() / 45f)));

                      continue;
                  }
              }

            c.setFont(Main.FONT);
            c.revalidate();

            if (c instanceof Container)
                revalidateAll((Container) c);
        }
    }
}