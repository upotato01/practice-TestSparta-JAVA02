package spartaExampleGUI;

import javax.swing.SwingUtilities;

public class BaseballGameMain {
    private final BaseballGameLogic gameLogic;

    public BaseballGameMain() {
        gameLogic = new BaseballGameLogic(new BaseballGameScoreManager(), new BaseballGameRandomNum());
    }

    public void setDifficulty(int level) {
        gameLogic.setDifficulty(level);
    }

    public int getNumberOfDigits() {
        return gameLogic.getNumberOfDigits();
    }

    public String checkResult(int[] userNumbers) {
        return gameLogic.checkResult(userNumbers);
    }

    public void resetGame() {
        gameLogic.resetGame();
    }

    public String getFormattedScores() {
        return gameLogic.getFormattedScores();
    }

    public void clearScoreList() {
        gameLogic.clearScoreList();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BaseballGameMain gameMain = new BaseballGameMain();
            new BaseballGameGUI(gameMain);
        });
    }
}
