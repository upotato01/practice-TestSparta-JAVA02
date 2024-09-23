package spartaExampleGUI;

public class BaseballGameLogic {
    private int[] randomNumbers;
    private int numberOfDigits; // 자리수 (난이도에 따라 3, 4, 5자리)
    private int attemptCount;
    private final BaseballGameScoreManager baseballGameScoreManager;
    private final BaseballGameRandomNum randomGenerator;

    public BaseballGameLogic(BaseballGameScoreManager baseballGameScoreManager, BaseballGameRandomNum randomGenerator) {
        this.numberOfDigits = 3; // 기본 레벨 1로 시작
        this.baseballGameScoreManager = baseballGameScoreManager;
        this.randomGenerator = randomGenerator;
        resetGame();
    }

    // 난이도 설정 메서드
    public void setDifficulty(int level) {
        switch (level) {
            case 1:
                numberOfDigits = 3; // 레벨 1 - 3자리
                break;
            case 2:
                numberOfDigits = 4; // 레벨 2 - 4자리
                break;
            case 3:
                numberOfDigits = 5; // 레벨 3 - 5자리
                break;
            default:
                numberOfDigits = 3; // 기본 레벨 1
        }
        resetGame(); // 난이도 변경 후 게임 초기화
    }

    // 자리수 반환 메서드
    public int getNumberOfDigits() {
        return numberOfDigits;
    }

    // 게임 초기화 메서드
    public void resetGame() {
        randomNumbers = randomGenerator.generateRandomNumbers(numberOfDigits);
        attemptCount = 0;
    }

    // 입력된 숫자와 랜덤 숫자 비교
    public String checkResult(int[] userNumbers) {
        if (userNumbers.length != numberOfDigits) {
            return "잘못된 입력입니다.";
        }

        attemptCount++;
        int strikes = 0;
        int balls = 0;

        for (int i = 0; i < numberOfDigits; i++) {
            if (userNumbers[i] == randomNumbers[i]) {
                strikes++;
            } else if (randomGenerator.contains(randomNumbers, userNumbers[i])) {
                balls++;
            }
        }

        if (strikes == numberOfDigits) {
            baseballGameScoreManager.addScore(attemptCount);
            return "정답입니다! " + strikes + " 스트라이크로 게임이 끝났습니다! 총 시도 횟수: " + attemptCount;
        } else {
            return "스트라이크: " + strikes + ", 볼: " + balls;
        }
    }

    public String getFormattedScores() {
        return baseballGameScoreManager.getFormattedScores();
    }

    public void clearScoreList() {
        baseballGameScoreManager.clearScores();
    }
}
