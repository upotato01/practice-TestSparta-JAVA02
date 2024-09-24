package spartaExampleGUI;

import javax.swing.*;
import java.awt.*;

public class BaseballGameGUI extends JFrame {
    private final JTextArea resultArea;
    private final BaseballGameMain gameLevel;
    private final StringBuilder userInput;

    public BaseballGameGUI(BaseballGameMain gameLevel) {
        this.gameLevel = gameLevel;
        this.userInput = new StringBuilder();

        setTitle("숫자 야구 게임");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        resultArea = createResultArea();
        add(new JScrollPane(resultArea), BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JTextArea createResultArea() {
        JTextArea area = new JTextArea(10, 30);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.BOLD, 16));
        return area;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        String[] buttons = {
                "7", "8", "9", "제출",
                "4", "5", "6", "종료",
                "1", "2", "3", "입력 수정",
                "난이도", "점수 보기", "점수 초기화", "다시 시작"
        };

        for (String label : buttons) {
            JButton button = createButton(label);
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        button.addActionListener(e -> handleButtonAction(label));
        return button;
    }

    private void handleButtonAction(String label) {
        switch (label) {
            case "제출":
                handleSubmit();
                break;
            case "종료":
                handleExit();
                break;
            case "점수 초기화":
                handleClearScore();
                break;
            case "점수 보기":
                handleScore();
                break;
            case "입력 수정":
                handleDelete();
                break;
            case "다시 시작":
                handleRestart();
                break;
            case "난이도":
                handleDifficulty();
                break;
            default:
                handleNumberButton(Integer.parseInt(label));
                break;
        }
    }

    private void handleDifficulty() {
        String[] options = {"레벨 1 (3자리)", "레벨 2 (4자리)", "레벨 3 (5자리)"};
        int choice = JOptionPane.showOptionDialog(this, "난이도를 선택하세요:", "난이도 설정",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice != -1) {
            gameLevel.setDifficulty(choice + 1);  // 난이도 설정
            appendResult("난이도가 레벨 " + (choice + 1) + "로 설정되었습니다.\n");
        }
    }

    private void handleNumberButton(int number) {
        if (userInput.length() < gameLevel.getNumberOfDigits() && userInput.indexOf(String.valueOf(number)) == -1) {
            userInput.append(number);
            updateUserInputDisplay();
        } else {
            appendResult("중복된 숫자는 입력할 수 없거나 " + gameLevel.getNumberOfDigits() + "개의 숫자만 입력 가능합니다.\n");
        }
    }

    private void handleSubmit() {
        if (userInput.length() == gameLevel.getNumberOfDigits()) { // 자리수에 맞춰 입력
            int[] userNumbers = userInput.toString().chars().map(Character::getNumericValue).toArray();
            String result = gameLevel.checkResult(userNumbers);
            appendResult(result + "\n");
            userInput.setLength(0); // 입력 초기화
            updateUserInputDisplay();
        } else {
            appendResult("숫자 " + gameLevel.getNumberOfDigits() + "개를 입력하세요.\n");
        }
    }

    private void handleDelete() {
        if (!userInput.isEmpty()) {
            userInput.deleteCharAt(userInput.length() - 1);
            updateUserInputDisplay();
        }
    }

    private void handleRestart() {
        gameLevel.resetGame();
        appendResult("게임이 다시 시작되었습니다.\n");
        userInput.setLength(0);
        updateUserInputDisplay();
    }

    private void handleExit() {
        if (JOptionPane.showConfirmDialog(this, "정말로 게임을 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    private void handleScore() {
        appendResult(gameLevel.getFormattedScores());
    }

    private void handleClearScore() {
        gameLevel.clearScoreList();
        appendResult("점수 리스트가 초기화되었습니다.\n");
    }

    private void updateUserInputDisplay() {
        appendResult("입력된 숫자: " + userInput.toString() + "\n");
    }

    private void appendResult(String text) {
        resultArea.append(text);
    }
}