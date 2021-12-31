package game.dialogs;

import game.frame.TetrisFrame;
import game.start.Main;
import java.awt.event.KeyEvent;

public class ResetLeaderboardDialog extends YesNoDialog {
    public ResetLeaderboardDialog(TetrisFrame parent, boolean modal) {
        super(parent, "", "You really want to reset leaderboard?", modal);
    }

    @Override
    protected void yesLabelMousePressed() {
        Main.leaderBoardPanel.resetLeaderBoardArray();
        Main.leaderBoardPanel.saveLeaderBoard();
        Main.leaderBoardPanel.setLeaderBoard();
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
