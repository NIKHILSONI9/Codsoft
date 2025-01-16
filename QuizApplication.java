import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private Question[] questions;
    private int score;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();
        
        for (Question question : questions) {
            System.out.println("\n" + question.questionText);
            for (int i = 0; i < question.options.length; i++) {
                System.out.println((i + 1) + ". " + question.options[i]);
            }

            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                }
            }, 15000);  // 15-second timer for each question

            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt();
            timer.cancel();

            if (answer == question.correctAnswer) {
                score++;
            }
        }

        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + questions.length);
        scanner.close();
    }
}
public class QuizApplication {
    public static void main(String[] args) {
        Question[] questions = {
            new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3),
            new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 2)
            // Add more questions as needed
        };
        
        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}

class Question {
    String questionText;
    String[] options;
    int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}
