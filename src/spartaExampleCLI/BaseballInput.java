package spartaExampleCLI;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseballInput {
    private final Scanner scanner = new Scanner(System.in);

    // 사용자에게 숫자를 입력받음
    public String getInput() {
        System.out.println("숫자를 입력하세요 (1-9 사이의 서로 다른 3자리 숫자): ");
        return scanner.nextLine();
    }

    public static boolean validateInput(String input) {
        if (input.length() != 3) return false;

        Set<Character> uniqueChars = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (c < '1' || c > '9' || !uniqueChars.add(c)) {
                return false;
            }
        }
        return true;
    }
}
