import greenfoot.*;  

public class StartScreen extends World {

    public StartScreen() {    
        super(600, 400, 1);
        prepare();
    }

    private void prepare() {

    GreenfootImage background = new GreenfootImage("StartScreen.png");
    background.scale(getWidth(), getHeight());
    setBackground(background);


    String info = "Welcome to Math Maze!\n\n"
                  + "In this adventurous journey, you will navigate through challenging mazes by solving math questions. \n"
                  + "There are 5 levels in total, each increasing in difficulty as you progress.\n\n"
                  + "Game Rules:\n"
                  + "- Answer math questions correctly to move forward in the maze.\n"
                  + "- For each correct answer, you gain a life when you level up.\n"
                  + "- Beware! Each wrong answer will cost you a life.\n"
                  + "- Don't worry if you lose, as you'll get a fun and educational lesson to improve your skills!\n\n"
                  + "Your Objective:\n"
                  + "Solve all the puzzles to conquer the mazes and become a Math Maze master!\n\n"
                  + "Are you ready to test your math skills and embark on this exciting adventure?";

    
    int textWidth = getWidth() - 40; 
    int textHeight = 300; 
    GreenfootImage textImage = new GreenfootImage(textWidth, textHeight);


    Color backgroundColor = new Color(0, 128, 0, 180); 
    textImage.setColor(backgroundColor);
    textImage.fillRect(0, 0, textWidth, textHeight);
    textImage.setColor(Color.WHITE);
    textImage.drawString(info, 10, 25);
    
    int x = (getWidth() - textWidth) / 2;
    int y = (getHeight() - textHeight) / 2 - 50;

    background.drawImage(textImage, x, y);
    setBackground(background);

    StartButton startButton = new StartButton();
    addObject(startButton, getWidth() / 2, getHeight() - 50);
}


}

