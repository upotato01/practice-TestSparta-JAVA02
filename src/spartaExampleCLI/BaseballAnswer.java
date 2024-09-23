package spartaExampleCLI;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BaseballAnswer {
    private final String answer;

    public BaseballAnswer() {
        this.answer = generateAnswer();
    }

    // 정답 생성
    private String generateAnswer() {
        Random random = new Random();
        Set<Integer> uniqueDigits = new HashSet<>();
        StringBuilder result = new StringBuilder();

        while (uniqueDigits.size() < 3) {
            int num = random.nextInt(9) + 1;
            if (uniqueDigits.add(num)) {
                result.append(num);
            }
        }
        return result.toString();
    }

    public String getAnswer() {
        return this.answer;
    }
}
