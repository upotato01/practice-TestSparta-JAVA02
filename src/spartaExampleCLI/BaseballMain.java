package spartaExampleCLI;

public class BaseballMain {
    public static void main(String[] args) {
        BaseballInput inputHandler = new BaseballInput();
        BaseballAnswer answerGenerator = new BaseballAnswer();
        BaseballScoreManager scoreManager = new BaseballScoreManager();

        String answer = answerGenerator.getAnswer();
        boolean isCorrect = false;

        System.out.println("< 숫자 야구 게임을 시작합니다! >");

        while (!isCorrect) {
            String input = inputHandler.getInput();

            // BaseballInput 클래스에서 validateInput 호출
            if (BaseballInput.validateInput(input)) {
                System.out.println("유효한 입력입니다.");
            } else {
                System.out.println("잘못된 입력입니다.");
            }

            // 스트라이크와 볼 계산
            int strikes = scoreManager.countStrikes(input, answer);
            int balls = scoreManager.countBalls(input, answer);

            // 결과 출력
            System.out.println(strikes + " 스트라이크, " + balls + " 볼");

            // 3 스트라이크일 경우 정답
            if (strikes == 3) {
                System.out.println("정답입니다! 게임이 종료됩니다.");
                isCorrect = true;
            }
        }
    }
}
