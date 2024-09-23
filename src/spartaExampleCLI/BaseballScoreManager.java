package spartaExampleCLI;

public class BaseballScoreManager {

    // 스트라이크 계산
    public int countStrikes(String input, String answer) {
        int strikes = 0;
        for (int i = 0; i < 3; i++) {
            if (input.charAt(i) == answer.charAt(i)) {
                strikes++;
            }
        }
        return strikes;
    }

    // 볼 계산
    public int countBalls(String input, String answer) {
        int balls = 0;
        for (int i = 0; i < 3; i++) {
            if (input.charAt(i) != answer.charAt(i) && answer.contains(String.valueOf(input.charAt(i)))) {
                balls++;
            }
        }
        return balls;
    }
}
