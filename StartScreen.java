import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

public class StartScreen extends World {

    public StartScreen() {    
        super(600, 400, 1);
        prepare();
    }

    private void prepare() {
        // Set the jungle background
        GreenfootImage background = new GreenfootImage("StartScreen.png");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        // Display game explanation
        String info = "Welcome to Math Maze! Solve math questions as an adventurer. \n"
                      + "There are 3 levels to conquer. Answer correctly to progress, \n"
                      + "and gain a life when you level up. Beware, wrong answers will \n"
                      + "cost you lives! Can you solve all the puzzles and win?";
        showText(info, getWidth() / 2, getHeight() / 2 - 50);

        // Add styled start button
        StartButton startButton = new StartButton();
        addObject(startButton, getWidth() / 2, getHeight() - 50);
    }
}

