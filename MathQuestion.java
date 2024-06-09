import greenfoot.*;  
import java.util.ArrayList;
import java.util.Random;

public class MathQuestion extends Actor {
    private String currentQuestion;
    private int correctAnswer;

    public MathQuestion() {
        
    }
     @Override
    protected void addedToWorld(World world) {
        chooseNewQuestion();
    }

    private void chooseNewQuestion() {
        ArrayList<String> easyQuestions = new ArrayList<>();
        ArrayList<Integer> easyAnswers = new ArrayList<>();
        ArrayList<String> mediumQuestions = new ArrayList<>();
        ArrayList<Integer> mediumAnswers = new ArrayList<>();
        ArrayList<String> hardQuestions = new ArrayList<>();
        ArrayList<Integer> hardAnswers = new ArrayList<>();
        ArrayList<String> advancedQuestions = new ArrayList<>();
        ArrayList<Integer> advancedAnswers = new ArrayList<>();
        ArrayList<String> expertQuestions = new ArrayList<>();
        ArrayList<Integer> expertAnswers = new ArrayList<>();

        // Populate the questions and their answers
        // Easy questions (Addition, Subtraction)
        easyQuestions.add("What is 99 - 72?");
        easyAnswers.add(27);
        easyQuestions.add("What is 8 - 4?");
        easyAnswers.add(4);
        easyQuestions.add("What is 7 + 6?");
        easyAnswers.add(13);
        easyQuestions.add("What is 9 - 3?");
        easyAnswers.add(6);
        easyQuestions.add("What is 4 + 5?");
        easyAnswers.add(9);
        easyQuestions.add("What is 14 - 7?");
        easyAnswers.add(7);
        easyQuestions.add("What is 50 - 22?");
        easyAnswers.add(28);
        easyQuestions.add("What is 9 + 8?");
        easyAnswers.add(17);
        easyQuestions.add("What is 19 - 6?");
        easyAnswers.add(13);
        easyQuestions.add("What is 12 + 24?");
        easyAnswers.add(36);
        easyQuestions.add("What is 16 - 9?");
        easyAnswers.add(7);

        // Medium questions (Multiplication, Division)
        mediumQuestions.add("What is 30 / 5?");
        mediumAnswers.add(6);
        mediumQuestions.add("What is 21 / 7?");
        mediumAnswers.add(3);
        mediumQuestions.add("What is 15 / 3?");
        mediumAnswers.add(5);
        mediumQuestions.add("What is 10 * 3?");
        mediumAnswers.add(30);
        mediumQuestions.add("What is 18 / 2?");
        mediumAnswers.add(9);
        mediumQuestions.add("What is 6 * 7?");
        mediumAnswers.add(42);
        mediumQuestions.add("What is 81 / 9?");
        mediumAnswers.add(9);
        mediumQuestions.add("What is 7 * 8?");
        mediumAnswers.add(56);
        mediumQuestions.add("What is 42 / 6?");
        mediumAnswers.add(7);
        mediumQuestions.add("What is 25 / 5?");
        mediumAnswers.add(5);
        mediumQuestions.add("What is 9 x 9?");
        mediumAnswers.add(81);
        mediumQuestions.add("What is 49 / 7?");
        mediumAnswers.add(7);
        mediumQuestions.add("What is 36 / 4?");
        mediumAnswers.add(9);
        mediumQuestions.add("What is 11 * 11?");
        mediumAnswers.add(121);
        mediumQuestions.add("What is 20 * 5?");
        mediumAnswers.add(100);
        mediumQuestions.add("What is 56 / 8?");
        mediumAnswers.add(7);
        mediumQuestions.add("What is 9 x 9?"); 
        mediumAnswers.add(81);
        mediumQuestions.add("What is 49 / 7?");
        mediumAnswers.add(7);
        mediumQuestions.add("What is 36 / 4?");
        mediumAnswers.add(9);
        mediumQuestions.add("What is 11 * 11?");
        mediumAnswers.add(121);
        mediumQuestions.add("What is 20 * 5?");
        mediumAnswers.add(100);
        mediumQuestions.add("What is 56 / 8?");
        mediumAnswers.add(7);

        // Hard questions (Power, Root)
        hardQuestions.add("What is 3 + 5 * 2?");
        hardAnswers.add(13);
        hardQuestions.add("What is the square root of 25?");
        hardAnswers.add(5);
        hardQuestions.add("What is the square root of 64?");
        hardAnswers.add(8);
        hardQuestions.add("What is the square root of 36?");
        hardAnswers.add(6);
        hardQuestions.add("What is 2 raised to the power of 3?");
        hardAnswers.add(8);
        hardQuestions.add("What is 4 to the power of 1 (4^1)?");
        hardAnswers.add(4);
        hardQuestions.add("What is 9 to the power of 1 (9^1)?");
        hardAnswers.add(9);
        hardQuestions.add("What is 10 to the power of 0 (10^0)?");
        hardAnswers.add(1);
        hardQuestions.add("What is 3 x 3 x 3 x 3 (3 multiplied by itself 4 times)?");
        hardAnswers.add(81);
        hardQuestions.add("What is 2 x 2 x 2 x 2 x 2 (2 multiplied by itself 5 times)?");
        hardAnswers.add(32);
        
        // Advanced questions (Sequences)
        advancedQuestions.add("What is the next number in the sequence 2, 4, 8, 16, ...?");
        advancedAnswers.add(32);
        advancedQuestions.add("What is the next number in the sequence 3, 6, 12, 24, ...?");
        advancedAnswers.add(48);
        advancedQuestions.add("What is the next number in the sequence 2, 7, 12, 17, ...?");
        advancedAnswers.add(22);
        advancedQuestions.add("What is the next number in the sequence 1, 4, 9, 16, ...?");
        advancedAnswers.add(25);
        advancedQuestions.add("What is the next number in the sequence 5, 10, 15, 20, ...?");
        advancedAnswers.add(25);
        advancedQuestions.add("What is the next number in the sequence 1, 8, 27, 64, ...?");
        advancedAnswers.add(125);
        advancedQuestions.add("What is the next number in the sequence 16, 8, 4, 2, ...?");
        advancedAnswers.add(1); 
        
        //Expert questions (Math problems)
        expertQuestions.add("If a train travels 200 miles in 2 hours, what is its speed in miles per hour?");
        expertAnswers.add(100);
        expertQuestions.add("The bakery needs 3 cups of flour for each batch of cookies. If they want to make 4 batches, how many cups of flour do they need in total?");
        expertAnswers.add(12);
        expertQuestions.add("A movie is 105 minutes long. If Sarah watched 45 minutes already, how many minutes are left in the movie?");
        expertAnswers.add(60); 
        expertQuestions.add("At the park, there are 18 swings and 12 slides. What is the total number of swings and slides?");
        expertAnswers.add(30);  
        expertQuestions.add("A bus carries 40 passengers. If 5 get off at the first stop and 3 get on at the second stop, how many passengers are on the bus after the second stop?");
        expertAnswers.add(38);
        expertQuestions.add("At a school bake sale, they sell cookies for $1 each and brownies for $2 each. If they sell 12 cookies and 8 brownies, how much money do they make in total?");
        expertAnswers.add(32);
        expertQuestions.add("A square has an area of 36 square meters. What is the length of one side of the square (since a square all sides are the same length)?");
        expertAnswers.add(6);
        expertQuestions.add("Michael has saved $15 for a video game. If the game costs $10, how much money does he have leftover?");
        expertAnswers.add(5);  
        expertQuestions.add("A recipe yields 16 cookies. If Sarah wants to double the recipe, how many cookies will she make in total?");
        expertAnswers.add(32);
        expertQuestions.add("A train travels 210 miles in 7 hours. If it travels at a constant speed, how many miles does it travel in 2 hours?");
        expertAnswers.add(60);
        expertQuestions.add("If the sum of three consecutive odd numbers is 45, what is the middle number?");
        expertAnswers.add(15);

        int level = ((MathMaze)getWorld()).getLevel();
        ArrayList<String> selectedQuestions;
        ArrayList<Integer> selectedAnswers;

        if (level == 1) {
            selectedQuestions = easyQuestions;
            selectedAnswers = easyAnswers;
        } else if (level == 2) {
            selectedQuestions = mediumQuestions;
            selectedAnswers = mediumAnswers;
        } else if (level == 3){
            selectedQuestions = hardQuestions;
            selectedAnswers = hardAnswers;
        } else if(level == 4){
            selectedQuestions = advancedQuestions;
            selectedAnswers = advancedAnswers;
        } else{
            selectedQuestions = expertQuestions;
            selectedAnswers = expertAnswers;
        }

        Random rand = new Random();
        int index = rand.nextInt(selectedQuestions.size());
        currentQuestion = selectedQuestions.get(index);
        correctAnswer = selectedAnswers.get(index);

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
            currentWorld.removeObject(this);  
            ((MathMaze)currentWorld).handleCorrectAnswer();  
        } else {
            Greenfoot.playSound("wrong.mp3");
            ((MathMaze)getWorld()).handleWrongAnswer(); 
        }
    } catch (NumberFormatException e) {
        Greenfoot.playSound("wrong.mp3");
        ((MathMaze)getWorld()).handleWrongAnswer();  
    }
}

    
}
