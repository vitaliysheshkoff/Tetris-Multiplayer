package game.dialogs;

import game.frames.TetrisFrame;
import game.start.Main;
import java.awt.event.KeyEvent;

public class NewGameDialog extends YesNoDialog {
    public NewGameDialog(TetrisFrame parent, boolean modal) {
        super(parent, "new game", "<html>You have unfinished current game.<br/>Want to start a new game?</html>", modal);
    }

    @Override
    protected void yesLabelMousePressed() {
        Main.menuPanel.goTetrisPanel();
        Main.tetrisPanel.tetrisPlayFieldPanel.startNewGame();
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

    @Override
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
