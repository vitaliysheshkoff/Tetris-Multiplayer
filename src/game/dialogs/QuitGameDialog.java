package game.dialogs;

import game.frame.TetrisFrame;
import game.start.Main;
import java.awt.event.KeyEvent;

public class QuitGameDialog extends YesNoDialog {

    public QuitGameDialog(TetrisFrame parent, boolean modal) {
        super(parent, "", "<html><body style='text-align: center'>You really want to <br/> leave the current game?</html>", modal);
    }

    protected void yesLabelMousePressed() {
        Main.tetrisPanel.tetrisPlayFieldPanel.myInterrupt();
        Main.tetrisPanel.saveGame();
        Main.audioPlayer.stopMusic();
        Main.tetrisFrame.remove(Main.tetrisPanel);
        Main.tetrisFrame.add(Main.menuPanel);
        Main.menuPanel.selectCurrentButton();
        Main.tetrisFrame.revalidate();
        Main.tetrisFrame.revalidateAll(Main.tetrisFrame);
        Main.tetrisFrame.repaint();
        dispose();

    }

    @Override
    protected void noLabelMousePressed() {
        super.noLabelMousePressed();
    }

    @Override
    protected void yesLabelMouseExited() {
        super.yesLabelMouseExited();
    }

    @Override
    protected void noLabelMouseExited() {
        super.noLabelMouseExited();
    }

    @Override
    protected void yesLabelMouseEntered() {
        super.yesLabelMouseEntered();
    }

    @Override
    protected void noLabelMouseEntered() {
        super.noLabelMouseEntered();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            pressRightKey();
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            pressLeftKey();
        else if (e.getKeyCode() == KeyEvent.VK_ENTER)
            pressEnterKey();
    }

    protected void pressEnterKey() {
        if (this.currentButtonSelected) {

            if (this.buttonController == YES)
                yesLabelMousePressed();
            else {
                Main.menuPanel.selectCurrentButton();
                noLabelMousePressed();
            }
        }
    }

    @Override
    protected void pressLeftKey() {
        super.pressLeftKey();
    }

    @Override
    protected void pressRightKey() {
        super.pressRightKey();
    }

    @Override
    public void selectCurrentButton() {
        super.selectCurrentButton();
    }

    @Override
    protected void unselectCurrentButton() {
        super.unselectCurrentButton();
    }
}
