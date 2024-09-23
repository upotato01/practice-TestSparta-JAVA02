package spartaExampleGUI;

import java.util.ArrayList;

public class BaseballGameScoreManager {  // BaseballGameScoreManager 클래스를 정의합니다.
    private final ArrayList<Integer> scores;  // 점수를 저장할 ArrayList 선언

    public BaseballGameScoreManager() {  // 생성자
        scores = new ArrayList<>();  // ArrayList 객체 초기화
    }

    public void addScore(int attemptCount) {  // 점수를 추가하는 메서드
        scores.add(attemptCount);  // 점수 리스트에 시도 횟수 추가
    }

    public String getFormattedScores() {  // 점수를 포맷하여 반환하는 메서드
        if (scores.isEmpty()) {  // 점수 리스트가 비어 있으면
            return "점수 리스트가 비어있습니다.\n";  // 비어있다는 메시지 반환
        }
        StringBuilder sb = new StringBuilder("게임 시도 횟수 리스트:\n");  // 결과를 담을 StringBuilder 생성
        for (int i = 0; i < scores.size(); i++) {  // 점수 리스트를 순회
            sb.append("게임 ").append(i + 1).append(": ").append(scores.get(i)).append(" 시도\n");  // 점수 추가
        }
        return sb.toString();  // 최종 문자열 반환
    }

    public void clearScores() {  // 점수 리스트를 초기화하는 메서드
        scores.clear();  // 점수 리스트 비우기
    }
}