package spartaExampleGUI;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BaseballGameRandomNum {  // BaseballGameRandomNum 클래스를 정의합니다.
    private final Random random;  // Random 객체 선언

    public BaseballGameRandomNum() {  // 생성자
        random = new Random();  // Random 객체 초기화
    }

    public int[] generateRandomNumbers(int numberOfDigits) {  // 난수를 생성하는 메서드
        Set<Integer> uniqueNumbers = new HashSet<>();  // 중복되지 않는 숫자를 저장할 Set 객체 생성
        while (uniqueNumbers.size() < numberOfDigits) {  // 필요한 숫자의 개수가 채워질 때까지 반복
            int randomNumber = random.nextInt(9) + 1;  // 1부터 9까지의 난수 생성
            uniqueNumbers.add(randomNumber);  // 숫자를 Set에 추가
        }
        return uniqueNumbers.stream().mapToInt(Integer::intValue).toArray();  // Set을 배열로 변환하여 반환
    }

    public boolean contains(int[] arr, int num) {  // 배열에 특정 숫자가 포함되어 있는지 확인하는 메서드
        for (int value : arr) {  // 배열을 순회
            if (value == num) {  // 숫자가 배열에 존재하면
                return true;  // true 반환
            }
        }
        return false;  // 숫자가 없으면 false 반환
    }
}
