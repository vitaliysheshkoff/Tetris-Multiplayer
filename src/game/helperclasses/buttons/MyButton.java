package game.helperclasses.buttons;

import game.start.Main;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButton extends JButton implements MouseListener {

    private final Color selectForegroundColor;

    public MyButton(String name) {

        selectForegroundColor = new Color(255, 255, 255, 150);
        Color transparentColor = new Color(0, 0, 0, 100);

        setFocusable(false);
        setFocusPainted(false);
        setOpaque(false);
        setBackground(transparentColor);
        setForeground(Color.WHITE);
        setBorder(new LineBorder(transparentColor, 2));
        setText(name);
        setFont(Main.FONT);

        addMouseListener(this);
    }

    public void selectButton() {
        setForeground(selectForegroundColor);
    }

    public void unselectButton() {
        setForeground(Color.WHITE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        selectButton();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        unselectButton();
    }
}
