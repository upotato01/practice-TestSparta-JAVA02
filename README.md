## 1\. CLI 버전

`BaseballInput`, `BaseballMain`, `BaseballScoreManager`, `BaseballAnswer` 클래스들 간의 관계를 **ERD (Entity-Relationship Diagram)** 형식으로 표현하는 것은 객체지향 프로그래밍에서 클래스 간의 관계를 시각적으로 나타내는 것과 유사합니다. ERD는 데이터베이스의 테이블과 관계를 나타내는 데 사용되지만, 여기서는 각 클래스를 엔티티로, 클래스 간의 관계를 일종의 "연결"로 표현할 수 있습니다.

### 각 클래스의 역할

-   **BaseballInput**: 플레이어의 입력을 처리하고 검증.
-   **BaseballMain**: 게임의 메인 흐름을 관리하고, 각 클래스의 객체를 호출하여 게임 로직을 진행.
-   **BaseballScoreManager**: 게임 점수와 기록을 관리.
-   **BaseballAnswer**: 컴퓨터가 생성한 정답을 관리.

### ERD 표현

ERD 대신 UML 클래스 다이어그램에 가깝게 표현할 수 있습니다:

-   **Entities (클래스)**
    -   **BaseballMain**: 게임의 전반적인 흐름 제어.
    -   **BaseballInput**: 입력 검증 및 처리 담당.
    -   **BaseballScoreManager**: 점수 관리 담당.
    -   **BaseballAnswer**: 정답 생성 및 관리 담당.
-   **Relationships (연관)**
    -   **BaseballMain → BaseballInput**: BaseballMain이 BaseballInput의 메서드인 `validateInput()`을 호출하여 입력을 검증합니다. (1:1 관계)
    -   **BaseballMain → BaseballScoreManager**: BaseballMain이 점수를 관리할 때 BaseballScoreManager를 사용합니다. (1:1 관계)
    -   **BaseballMain → BaseballAnswer**: BaseballMain이 정답을 생성하고 관리하기 위해 BaseballAnswer를 사용합니다. (1:1 관계)
![야구게임_ERD_다이어그램_CLI](https://github.com/user-attachments/assets/6b905aaf-c3df-404a-98dd-2a0bc1a925d1)

## 2\. GUI 버전

## 1\. **BaseballGameMain**

-   **속성:**
    -   `BaseballGameLogic gameLogic` (게임 로직 처리)
-   **메서드:**
    -   `checkResult(int[] userNumbers)` (결과 확인)
    -   `resetGame()` (게임 초기화)
    -   `getFormattedScores()` (포맷된 점수 가져오기)
    -   `clearScoreList()` (점수 리스트 초기화)
    -   `main(String[] args)` (프로그램 시작점)

**관계:**

-   `BaseballGameMain`은 `BaseballGameLogic`을 **사용**합니다.

## 2\. **BaseballGameLogic**

-   **속성:**
    -   `int[] randomNumbers` (랜덤 숫자)
    -   `int numberOfDigits` (숫자의 자리수)
    -   `int attemptCount` (시도 횟수)
    -   `BaseballGameScoreManager baseballGameScoreManager` (점수 관리)
    -   `BaseballGameRandomNum randomGenerator` (난수 생성기)
-   **메서드:**
    -   `resetGame()` (게임 초기화)
    -   `checkResult(int[] userNumbers)` (결과 확인)
    -   `getFormattedScores()` (포맷된 점수 가져오기)
    -   `clearScoreList()` (점수 리스트 초기화)
    -   `setNumberOfDigits(int level)` (난이도 설정)
    -   `getNumberOfDigits()` (현재 자리수 반환)

**관계:**

-   `BaseballGameLogic`은 `BaseballGameScoreManager`를 **가집니다**.
-   `BaseballGameLogic`은 `BaseballGameRandomNum`을 **사용**하여 난수를 생성합니다.

## 3\. **BaseballGameRandomNum**

-   **속성:**
    -   `Random random` (난수 생성기)
-   **메서드:**
    -   `generateRandomNumbers(int numberOfDigits)` (난수 생성)
    -   `contains(int[] arr, int num)` (숫자 포함 여부 확인)

**관계:**

-   `BaseballGameLogic`은 난수 생성을 위해 `BaseballGameRandomNum`을 **사용**합니다.

## 4\. **BaseballGameScoreManager**

-   **속성:**
    -   `ArrayList<Integer> scores` (점수 리스트)
-   **메서드:**
    -   `addScore(int attemptCount)` (점수 추가)
    -   `getFormattedScores()` (점수 포맷 반환)
    -   `clearScores()` (점수 리스트 초기화)

**관계:**

-   `BaseballGameLogic`은 점수 관리를 위해 `BaseballGameScoreManager`를 **사용**합니다.

## 5\. **BaseballGameGUI**

-   **속성:**
    -   `JTextArea resultArea` (결과 출력 영역)
    -   `BaseballGameMain gameMain` (게임 메인 객체)
    -   `StringBuilder userInput` (사용자 입력)
-   **메서드:**
    -   `BaseballGameGUI(BaseballGameMain gameMain)` (생성자)
    -   `createResultArea()` (결과 영역 생성)
    -   `createButtonPanel()` (버튼 패널 생성)
    -   `createButton(String label)` (버튼 생성)
    -   `handleButtonAction(String label)` (버튼 액션 처리)
    -   `handleNumberButton(int number)` (숫자 버튼 처리)
    -   `handleSubmit()` (입력 제출)
    -   `handleDelete()` (입력 수정)
    -   `handleRestart()` (게임 재시작)
    -   `handleExit()` (게임 종료)
    -   `handleScore()` (점수 보기)
    -   `handleClearScore()` (점수 초기화)
    -   `handleLevelChange()` (난이도 변경)
    -   `updateUserInputDisplay()` (사용자 입력 업데이트)
    -   `appendResult(String text)` (결과 출력)

**관계:**

-   `BaseballGameGUI`는 `BaseballGameMain`과 상호작용하여 게임 로직을 **사용**합니다.

## 관계 요약:

-   `BaseballGameMain`은 `BaseballGameLogic`을 초기화하고 관리합니다.
-   `BaseballGameLogic`은 핵심 게임 로직을 관리하며, `BaseballGameScoreManager`를 통해 점수를 기록하고 `BaseballGameRandomNum`을 통해 난수를 생성합니다.
-   `BaseballGameGUI`는 사용자 인터페이스를 담당하며 `BaseballGameMain`과 상호작용하여 게임 로직을 실행합니다.
![야구게임_ERD_다이어그램_GUI](https://github.com/user-attachments/assets/91429e84-8d44-46d7-981c-0b88ce38a7ab)
