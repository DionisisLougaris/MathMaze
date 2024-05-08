import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage
import java.util.ArrayList;
import java.util.Random;

public class MathQuestion extends Actor {
    private String currentQuestion;
    private int correctAnswer;

    public MathQuestion() {
        chooseNewQuestion();
    }

    private void chooseNewQuestion() {
        ArrayList<String> questions = new ArrayList<String>();
        ArrayList<Integer> answers = new ArrayList<Integer>();

       // Populate the questions and their answers
        questions.add("What is 2 + 2?");
        answers.add(4);
        questions.add("What is 3 * 3?");
        answers.add(9);
        questions.add("What is 15 / 3?");
        answers.add(5);
        questions.add("What is 8 - 4?");
        answers.add(4);
        questions.add("What is 7 + 6?");
        answers.add(13);
        questions.add("What is 5 * 5?");
        answers.add(25);
        questions.add("What is 21 / 7?");
        answers.add(3);
        questions.add("What is 9 - 3?");
        answers.add(6);
        questions.add("What is 4 + 5?");
        answers.add(9);
        questions.add("What is 2 * 6?");
        answers.add(12);
        questions.add("What is 30 / 5?");
        answers.add(6);
        questions.add("What is 14 - 7?");
        answers.add(7);
        questions.add("What is 10 * 3?");
        answers.add(30);
        questions.add("What is 50 - 22?");
        answers.add(28);
        questions.add("What is 9 + 8?");
        answers.add(17);
        questions.add("What is 18 / 2?");
        answers.add(9);
        questions.add("What is 6 * 7?");
        answers.add(42);
        questions.add("What is 81 / 9?");
        answers.add(9);
        questions.add("What is 19 - 6?");
        answers.add(13);
        questions.add("What is 7 * 8?");
        answers.add(56);
        questions.add("What is 12 + 24?");
        answers.add(36);
        questions.add("What is 42 / 6?");
        answers.add(7);
        questions.add("What is 16 - 9?");
        answers.add(7);
        questions.add("What is 25 / 5?");
        answers.add(5);
        questions.add("What is 32 + 18?");
        answers.add(50);
        questions.add("What is 49 / 7?");
        answers.add(7);
        questions.add("What is 36 / 4?");
        answers.add(9);
        questions.add("What is 99 - 72?");
        answers.add(27);
        questions.add("What is 11 * 11?");
        answers.add(121);
        questions.add("What is 100 / 10?");
        answers.add(10);
        questions.add("What is 20 * 5?");
        answers.add(100);
        questions.add("What is 56 / 8?");
        answers.add(7);

        
        Random rand = new Random();
        int index = rand.nextInt(questions.size());
        currentQuestion = questions.get(index);
        correctAnswer = answers.get(index);

        // Set the image and scale
        GreenfootImage image = new GreenfootImage("QuestionMark.png");
        image.scale(50, 50);
        setImage(image);
    }

    public void askQuestion() {
    String input = Greenfoot.ask(currentQuestion + " (Type your answer and press enter)");
    try {
        int answer = Integer.parseInt(input);
        if (answer == correctAnswer) {
            Greenfoot.playSound("correct.mp3");
            World currentWorld = getWorld();
            currentWorld.removeObject(this);  // Remove this question mark
            ((MathMaze)currentWorld).handleCorrectAnswer();  // Correct answer handling
        } else {
            Greenfoot.playSound("wrong.mp3");
            ((MathMaze)getWorld()).handleWrongAnswer();  // Incorrect answer handling
        }
    } catch (NumberFormatException e) {
        Greenfoot.playSound("wrong.mp3");
        ((MathMaze)getWorld()).handleWrongAnswer();  // Incorrect answer handling for non-numeric input
    }
}

    
}
