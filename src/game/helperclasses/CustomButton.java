package game.helperclasses;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

import game.panels.tetris.playfield.controller.Painting;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class CustomButton extends JButton {

    private int borderSize = 3;
    //  Create Animation
    private Animator animator;
    private int targetSize;
    private float animatSize;
    private Point pressedPoint;
    private float alpha;

    public CustomButton() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                pressedPoint = me.getPoint();
                alpha = 0.5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }

            @Override
            public void mouseReleased(MouseEvent me) {

            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };
        animator = new Animator(800, target);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Create Gradient Color
        float f[] = new float[]{0f, 0.5f, 1f};
        Color colors[] = new Color[]{Painting.J_COLOR,Color.WHITE, Painting.Z_COLOR};
        LinearGradientPaint gra = new LinearGradientPaint(0, 0, width, height, f, colors, MultipleGradientPaint.CycleMethod.REFLECT);
        Shape out = new Rectangle(0, 0, width, height);
        Shape in = new Rectangle(borderSize, borderSize, width - borderSize * 2, height - borderSize * 2);
        Area area = new Area(out);
        area.subtract(new Area(in));
        g2.setPaint(gra);
        g2.fill(area);
        //  Create Text String
        g2.setFont(getFont());
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(getText(), g2);
        double x = (width - r2.getWidth()) / 2;
        double y = (height - r2.getHeight()) / 2;
        g2.drawString(getText(), (int) x, (int) (y + ft.getAscent()));
        //  Create Animation when pressed
        if (pressedPoint != null) {
            g2.setColor(Color.YELLOW);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g2.fillOval((int) (pressedPoint.x - animatSize / 2), (int) (pressedPoint.y - animatSize / 2), (int) animatSize, (int) animatSize);
        }
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
    }
}