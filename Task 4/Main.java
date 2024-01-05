import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Question {
    String question;
    List<String> options;
    String correctAnswer;
    String userAnswer;

    Question(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.userAnswer = null;
    }
}

class QuizApp {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private final int timeDuration = 5;

    QuizApp(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        System.out.println("\nQuestion: " + currentQuestion.question);
        for (int i = 0; i < currentQuestion.options.size(); i++) {
            System.out.println((i + 1) + ". " + currentQuestion.options.get(i));
        }
    }

    void startTimer() throws InterruptedException {
        System.out.println("Time Remaining: ");
        for (int i = timeDuration; i > 0; i--) {
            System.out.println(i + " Seconds");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("\nTime's Up !!! ");
    }

    void submitAnswer(int selectOption) {
        Question currentQuestion = getCurrentQuestion();
        String selectedAnswer = currentQuestion.options.get(selectOption - 1);

        if (selectedAnswer.equals(currentQuestion.correctAnswer)) {
            System.out.println("Correct !");
            score++;
        } else {
            System.out.println("Wrong ! Correct answer : " + currentQuestion.correctAnswer);
        }

        currentQuestion.userAnswer = selectedAnswer;
    }

    void nextQuestion() {
        currentQuestionIndex++;
    }

    Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    void showResult() {
        System.out.println("\nQuiz Completed !!!!...");
        System.out.println("\nYour Final Score : " + score + "/" + questions.size());
        System.out.println("\nSummary of Answers :");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String status = question.correctAnswer.equals(question.userAnswer) ? "Correct" : "Incorrect";
            System.out.println("Question " + (i + 1) + ": " + status);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is 2 + 2 ?", List.of("3", "4", "5"), "4"));
        questions.add(new Question("Which is the capital of India ?", List.of("Berlin", "Paris", "Delhi"), "Delhi"));

        QuizApp quizApp = new QuizApp(questions);

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            quizApp.displayQuestion();
            try {
                quizApp.startTimer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int selectOption = getUserInput(sc);

            if (selectOption >= 1 && selectOption <= quizApp.getCurrentQuestion().options.size()) {
                quizApp.submitAnswer(selectOption);
            } else {
                System.out.println("Invalid option. Try again.");
                i--;
            }

            quizApp.nextQuestion();
        }

        quizApp.showResult();
    }

    private static int getUserInput(Scanner scanner) {
        System.out.print("Your answer (1, 2, 3, ...): ");

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.print("Your answer (1, 2, 3, ...): ");
            scanner.next();
        }

        return scanner.nextInt();
    }
}
