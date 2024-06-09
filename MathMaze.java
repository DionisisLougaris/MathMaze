import greenfoot.*;  
import java.util.Random; 

public class MathMaze extends World {
    private static final int CELL_SIZE = 50;
    private static final int PLAYER_START_X = 100;
    private static final int PLAYER_START_Y = 100;
    private static final int QUESTION_SAFE_ZONE = 25; 

    private int questionX, questionY; 
    private int correctAnswers = 0;
    private int level = 1;
    private int lives;

    public MathMaze() {
        super(600, 400, 1);
        setLevelBackground();
        setPaintOrder(Player.class, Wall.class, MathQuestion.class);
        lives = 3; 
        prepare();
        showLevel(); 
        showLives();
        Greenfoot.playSound("welcome.mp3");
    }

    private void setLevelBackground() {
        String backgroundFile = level == 1 ? "River.png" : level == 2 ? "Forest.png" : level == 3 ? "Mountain.png" : level == 4 ? "Desert.png" :"Space.png";
        GreenfootImage bg = new GreenfootImage(backgroundFile);
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
    }

    private void prepare() {
        Player player = new Player();
        addObject(player, PLAYER_START_X, PLAYER_START_Y);

        spawnNewQuestion();
        createStructuredWalls();
        removeWallsNearImportantObjects();
    }

    public int getLevel(){
        return level;
    }
    
    private void spawnNewQuestion() {
        Random rand = new Random();
        do {
            questionX = rand.nextInt(getWidth() - CELL_SIZE) + CELL_SIZE;
            questionY = rand.nextInt(getHeight() - CELL_SIZE) + CELL_SIZE;
        } while (Math.abs(questionX - PLAYER_START_X) <= CELL_SIZE && Math.abs(questionY - PLAYER_START_Y) <= CELL_SIZE); // Ensure the question is not too close to the player

        addObject(new MathQuestion(), questionX, questionY);
    }

    public void handleCorrectAnswer() {
    correctAnswers++;
    if (correctAnswers >= 3) {
        correctAnswers = 0;
        level++;
        updateLives(1); // Gain 1 life when moving up a level, capped at 3
        if (level > 5) {
            showWinMessage();
            Greenfoot.stop();
        } else {
            setLevelBackground();
            showLevel(); 
        }
    }
    spawnNewQuestion();
}

public void handleWrongAnswer() {
    lives--;
    showLives();  
    if (lives <= 0) {
        showGameOver();  
    } else {
        spawnNewQuestion();  
    }
}



    private void createStructuredWalls() {
        int numStructures = 18; 
        Random rand = new Random();

        for (int n = 0; n < numStructures; n++) {
            int x = rand.nextInt((getWidth() - CELL_SIZE) / CELL_SIZE) * CELL_SIZE;
            int y = rand.nextInt((getHeight() - CELL_SIZE) / CELL_SIZE) * CELL_SIZE;

            boolean horizontal = rand.nextBoolean();
            int length = rand.nextInt(3) + 2;

            for (int i = 0; i < length; i++) {
                int dx = horizontal ? CELL_SIZE / 2 * i : 0;
                int dy = horizontal ? 0 : CELL_SIZE / 2 * i;

                Wall wall = new Wall();
                addObject(wall, x + dx, y + dy);
            }
        }
    }

    private void removeWallsNearImportantObjects() {
        removeWallsNear(PLAYER_START_X, PLAYER_START_Y, CELL_SIZE);
        removeWallsNear(questionX, questionY, QUESTION_SAFE_ZONE);
    }

    private void removeWallsNear(int x, int y, int safeZone) {
        int safeZoneStartX = x - safeZone;
        int safeZoneEndX = x + safeZone;
        int safeZoneStartY = y - safeZone;
        int safeZoneEndY = y + safeZone;

        for (Object obj : getObjects(Wall.class)) {
            Wall wall = (Wall) obj;
            if (wall.getX() >= safeZoneStartX && wall.getX() <= safeZoneEndX &&
                wall.getY() >= safeZoneStartY && wall.getY() <= safeZoneEndY) {
                removeObject(wall);
            }
        }
    }

    private void showWinMessage() {
        removeObjects(getObjects(Wall.class));
        removeObjects(getObjects(MathQuestion.class));

        GreenfootImage winImage = new GreenfootImage("GameWon.png");
        winImage.scale(getWidth(), getHeight());
        setBackground(winImage);
        Greenfoot.playSound("victory.mp3");

        Greenfoot.stop();
    }
    private void showLevel() {
    String levelText = "Level " + level; 
    String finalLevelText= "YOU ARE ON THE FINAL LEVEL!";
    showText(levelText, getWidth() / 2, 20); 
    if(level == 5){
        showText(finalLevelText, getWidth() / 2, 50); 
    }
    }
    private void updateLives(int change) {
    lives += change;
    if (lives > 3) {
        lives = 3; 
    } else if (lives < 0) {
        lives = 0; 
    }
    showLives();
    if (lives == 0) {
        showGameOver(); 
    }
    }

    private void showLives() {
    String livesText = "Lives " + lives; 
    showText(livesText, 50, 20);
    }
   private void showGameOver() {
    removeObjects(getObjects(Wall.class));
    removeObjects(getObjects(MathQuestion.class));
    Greenfoot.playSound("lesson.mp3");

    int level = getLevel(); 

    String lessonText = "";
    int x=0;
    int y=0;
    if (level == 1) {
        lessonText = "'Uh oh! Looks like you struggled with subtracting numbers. \nSubtraction helps us find the difference between two amounts. \n" +
                "Imagine you have a collection of objects (like marbles or cookies) and you take some away. How many are left? That's subtraction!\n\n" +
                "Here's how subtraction works:\n\n" +
                "1. Start with the bigger number (the whole amount). This is called the minuend.\n" +
                "2. Take away the smaller number (the amount being removed). This is called the subtrahend.\n" +
                "3. The answer you get is the difference (how many are left). This is called the difference.\n\n" +
                "Examples:\n\n" +
                "Question: What is 99 - 72?\n" +
                "Explanation: We start with 99 marbles (minuend) and take away 72 marbles (subtrahend).\n" +
                "Answer: We are left with 27 marbles (difference).\n\n" +
                "Tips:\n\n" +
                "1. Counting backwards can help with subtraction. Start at the bigger number and count back the number you are taking away.\n" +
                "2. Use manipulatives like counters or blocks to visualize subtraction.\n\n" +
                "Let's practice! Try these problems:\n\n" +
                "1. What is 15 - 8? (Start with 15 and count back 8)\n" +
                "2. What is 21 - 7? (How many are left if you take 7 away from 21?)\n\n" +
                "Keep practicing, and you'll be a subtraction whiz in no time!'";
                x= 760;
                y=420;
    }else if (level == 2){
        lessonText="Looks like you might need a brush-up on multiplication and division! \nThese operations help us work with equal groups and sharing things fairly.\n\n" +
        "Multiplication:\n\n" +
        "Imagine you have equal groups of objects. Multiplication helps us find the total number of objects when we have several identical groups.\n" +
        "For example, if you have 3 groups of 4 apples each, how many apples do you have in total? 3 x 4 = 12 apples.\n" +
        "In multiplication problems, the bigger number is often the total number of items in each group (called the multiplicand),\n and the smaller number is the number of groups (called the multiplier). \nThe answer, called the product, tells you the total number of items.\n\n" +
        "Division:\n\n" +
        "Division is the opposite of multiplication. It helps us find out how many equal groups we can create from a certain number of objects,\n or how many items we get if we share a larger amount equally among a certain number of people.\n" +
        "For instance, if you have 21 cookies and want to share them equally among 7 friends, how many cookies will each friend get? 21 / 7 = 3 cookies each.\n" +
        "In division problems, the bigger number is the total we want to split (called the dividend), \nand the smaller number is the number of groups or people we are sharing with (called the divisor).\n The answer, called the quotient, tells you how much goes into each group.\n\n" +
        "Tips:\n\n" +
        "Multiplication tables can be a helpful tool to memorize basic multiplication facts.\n" +
        "Drawing pictures or using manipulatives can help visualize both multiplication and division.\n\n" +
        "Let's practice!\n\n" +
        "Multiplication: If you have 5 boxes and each box contains 8 crayons, how many crayons do you have in total? (5 x 8 = ?)\n" +
        "Division: If you have 36 candies and want to share them equally among 4 friends, how many candies will each friend get? (36 / 4 = ?)\n" +
        "Keep practicing, and you'll be multiplying and dividing like a pro in no time!";
        x= 850;
        y=460;
    }
    else if (level == 3){
        lessonText="Looks like you encountered some questions involving exponents and square roots.\n Don't worry, these concepts might seem tricky at first, but they're quite useful!\n\n" +
    "Exponents:\n\n" +
    "Exponents (little numbers written above a base number) are a shorthand way to write repeated multiplication.\n" +
    "For example, 2 raised to the power of 3 (written as 2^3) means 2 multiplied by itself 3 times: 2 x 2 x 2 = 8.\n" +
    "The base number (2 in this case) tells you what you're multiplying, \nand the exponent (3) tells you how many times you multiply it by itself.\n\n" +
    "Special Cases:\n\n" +
    "Any number raised to the power of 1 (x^1) is simply the number itself (e.g., 4^1 = 4).\n" +
    "Any number raised to the power of 0 (x^0) equals 1, as long as the base number isn't zero (e.g., 10^0 = 1).\n\n" +
    "Square Roots:\n\n" +
    "The square root of a number is another number that, when multiplied by itself, gives you the original number.\n" +
    "For instance, the square root of 25 is 5 because 5 x 5 = 25.\n\n" +
    "Tips:\n\n" +
    "Memorizing basic multiplication facts can help with understanding exponents.\n" +
    "Practice writing out the multiplications to visualize the concept of exponents.\n" +
    "Use a calculator's square root function to find square roots of larger numbers.\n\n" +
    "Let's practice!\n\n" +
    "Exponents: What is 4 raised to the power of 2 (4^2)? (Hint: How many times do you multiply 4 by itself?)\n" +
    "Square Root: What is the square root of 36? (Hint: What number multiplied by itself gives you 36?)\n" +
    "Keep practicing, and you'll be a whiz at exponents and square roots in no time!";
        x= 650;
        y=470;
    }
    else if (level == 4){
        lessonText="Uh oh! You stumbled upon some tricky number sequences. These are patterns where each number follows a specific rule. \nLet's learn how to crack the code!\n\n" +
    "What are Number Sequences?\n\n" +
    "Number sequences are lists of numbers where each number follows a specific rule. \nThey can increase, decrease, or jump by a certain amount. \nThe key is to identify the pattern!\n\n" +
    "Tips for Recognizing Patterns:\n\n" +
    "Look for differences: See if each number is obtained by adding or subtracting a constant value from the previous number.\n" +
    "Look for multiples: Check if each number is a multiple of the previous number (e.g., multiplying by 2, 3, etc.).\n" +
    "Look for powers: See if each number is the result of raising the previous number to a power (e.g., squaring, cubing).\n\n" +
    "Let's practice!\n\n" +
    "In the sequence 2, 4, 8, 16, ... each number is obtained by multiplying the previous number by 2. What would be the next number in this sequence? \n(Hint: Multiply 16 by 2)\n" +
    "Remember: There can be multiple ways to solve a sequence problem. The key is to identify the underlying pattern!\n\n" +
    "Keep practicing, and you'll be a sequence detective in no time!";
        x= 850;
        y=360;
    }else{
        lessonText="Great job reaching the expert level! These questions involve applying your math skills to solve problems \nyou might encounter in everyday life.\n\n" +
    "Understanding the Problem:\n\n" +
    "Read the question carefully and identify what information is given.\n" +
    "Figure out what you need to find (the answer).\n\n" +
    "Choosing the Right Math Operation:\n\n" +
    "Look for keywords that suggest the operation needed (e.g., \"total,\" \"difference,\" \"speed\").\n" +
    "Some problems might involve multiple steps and require a combination of operations.\n\n" +
    "Setting Up the Solution:\n\n" +
    "Sometimes, writing things down like equations or using diagrams can help visualize the problem.\n" +
    "Substitute the given information into the equation or formula.\n\n" +
    "Solving and Checking:\n\n" +
    "Follow the order of operations (PEMDAS) if necessary (Parentheses, Exponents, Multiplication and Division from left to right,\n Addition and Subtraction from left to right).\n" +
    "Double-check your answer to make sure it makes sense in the context of the problem.\n\n" +
    "Let's practice!\n\n" +
    "Speed: A car travels 180 kilometers in 3 hours. What is its average speed in kilometers per hour? \n(Hint: Speed = Distance / Time)\n" +
    "Word Problems: If you have 6 apples and give 2 to a friend, how many apples do you have left? \n(Hint: Subtraction)\n" +
    "Remember, math is everywhere, and these skills will help you solve problems in real life! \nKeep practicing, and you'll be a math whiz in no time!";
        x= 740;
        y=490;
    }

    Greenfoot.setWorld(new LessonScreen(x,y, lessonText));
}




}